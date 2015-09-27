package kata;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Calculator {
	private boolean nextPrecedes = false;

	private interface Operation {
		Double perform(Double storedValue, Double enteredValue);
	}

	final Map<String, Operation> OPERATORS = new HashMap<String, Operation>() {{
		put("+", (Double storedValue, Double enteredValue) -> storedValue + enteredValue);
		put("×", (Double storedValue, Double enteredValue) -> storedValue * enteredValue);
		put("−", (Double storedValue, Double enteredValue) -> storedValue - enteredValue);
		put("÷", (Double storedValue, Double enteredValue) -> storedValue / enteredValue);
	}};

	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
	private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
	private String buffer = null;
	private double value = 0;
	private Operation nextOperation;

	public String getDisplay() {
		if (buffer == null) {
			return numberFormat.format(value);
		} else {
			return buffer;
		}
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		dfs = new DecimalFormatSymbols(locale);
		numberFormat = NumberFormat.getNumberInstance(locale);
	}

	public void enterDigit(String digit) {
		if (buffer == null) buffer = "";
		buffer += digit;
	}

	public void enterDecimalPointer() {
		if (buffer == null)
			buffer = "0";
		buffer += dfs.getDecimalSeparator();
	}

	public void calculate() {
		value = performOperation(nextOperation, value, readBuffer());
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			Double bufferValue = readBuffer();
			Operation next = OPERATORS.get(opLabel);
			Operation previous = nextOperation;
			nextOperation = (storedValue, enteredValue) -> {
				Double intermediate = next.perform(bufferValue, enteredValue);
				return performOperation(previous, storedValue, intermediate);
			};
		} else {
			calculate();
			nextOperation = OPERATORS.get(opLabel);
		}
	}

	private Double readBuffer() {
		Double value1 = convertBufferToValue();
		buffer = null;
		return value1;
	}

	private double convertBufferToValue() {
		try {
			Number number = numberFormat.parse(buffer);
			return number.doubleValue();
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

	private Double performOperation(Operation operation, Double storedValue, Double enteredValue) {
		if (operation == null)
			return enteredValue;
		return operation.perform(storedValue, enteredValue);
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}
}
