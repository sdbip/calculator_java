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
	private Content content = Content.EMPTY;

	private double value = 0;
	private Operator nextOperator;
	private boolean nextPrecedes = false;

	public String getDisplay() {
		String buf = getDisplayedValue();
		return buf != null ? buf : formatValue(value);
	}

	public void enterDigit(String digit) {
		content = content.append(digit);
	}

	public void enterDecimalPointer() {
		appendDecimalPointer();
	}

	public void calculate() {
		value = callOperator(nextOperator, value, toValue());
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			Double bufferValue = toValue();
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

	String getDisplayedValue() {
		return content.get();
	}

	String formatValue(double value) {
		return displayFormatter.format(value);
	}


	void appendDecimalPointer() {
		if (content.isEmpty()) content = content.append("0");
		content = content.append("" + displayFormatter.getDecimalSeparator());
	}

	double toValue() {
		String buf = content.get();
		content = Content.EMPTY;
		try {
			return displayFormatter.parse(buf);
		} catch (ParseException e) {
			throw new RuntimeException("The input buffer has grown inconsistent. Terminating application.", e);
		} catch (NullPointerException _) {
			return 0;
		}
	}

	private Double callOperator(Operator operator, Double storedValue, Double enteredValue) {
		return operator != null ? operator.call(storedValue, enteredValue) : enteredValue;
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}

	private void setLocale(Locale locale) {
	displayFormatter = new DisplayFormatter(locale);
}
}
