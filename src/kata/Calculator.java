package kata;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Calculator {
	private interface Operator {
		Double call(Double storedValue, Double enteredValue);
	}
	final static Map<String, Operator> OPERATORS = new HashMap<String, Operator>() {{
		put("+", (Double storedValue, Double enteredValue) -> storedValue + enteredValue);
		put("×", (Double storedValue, Double enteredValue) -> storedValue * enteredValue);
		put("−", (Double storedValue, Double enteredValue) -> storedValue - enteredValue);
		put("÷", (Double storedValue, Double enteredValue) -> storedValue / enteredValue);
	}};

	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;

	private double value = 0;
	private Operator nextOperator;
	private boolean nextPrecedes = false;

	public String getDisplay() {
		if (buffer == null) {
			return displayFormatter.format(value);
		} else {
			return buffer;
		}
	}

	public void enterDigit(String digit) {
		if (buffer == null) buffer = "";
		buffer += digit;
	}

	public void enterDecimalPointer() {
		if (buffer == null)
			buffer = "0";
		buffer += displayFormatter.getDecimalSeparator();
	}

	public void calculate() {
		value = callOperator(nextOperator, value, readBuffer());
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			Double bufferValue = readBuffer();
			Operator next = OPERATORS.get(opLabel);
			Operator previous = nextOperator;
			nextOperator = (storedValue, enteredValue) -> {
				Double intermediate = next.call(bufferValue, enteredValue);
				return callOperator(previous, storedValue, intermediate);
			};
		} else {
			calculate();
			nextOperator = OPERATORS.get(opLabel);
		}
	}

	private Double readBuffer() {
		Double value1 = convertBufferToValue();
		buffer = null;
		return value1;
	}

	private double convertBufferToValue() {
		try {
			return displayFormatter.parse(buffer);
		} catch (ParseException e) {
			crashApplication("The input buffer has grown inconsistent. Terminating application.", e);
			return 0.0; // Why do I need to put this line here? I already crashed!
		}
	}

	private void crashApplication(String message, Throwable e) {
		System.out.println(message);
		System.out.println(e.getLocalizedMessage());
		System.exit(1);
	}

	private Double callOperator(Operator operator, Double storedValue, Double enteredValue) {
		if (operator == null)
			return enteredValue;
		return operator.call(storedValue, enteredValue);
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}
