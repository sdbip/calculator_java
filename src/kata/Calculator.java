package kata;

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
	private Operator nextOperator = (l, r) -> r;
	private boolean nextPrecedes = false;

	public String getDisplay() {
		String buf = content.get();
		return buf != null ? buf : displayFormatter.format(value);
	}

	public void enterDigit(String digit) {
		content = content.append(digit);
	}

	public void enterDecimalPointer() {
		content = content.append(displayFormatter.getDecimalSeparator());
	}

	public void calculate() {
		value = nextOperator.call(value, fromBuffer());
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			nextOperator = precedeBy(OPERATORS.get(opLabel), nextOperator);
		} else {
			calculate();
			nextOperator = OPERATORS.get(opLabel);
		}
	}

	private Operator precedeBy(Operator next, Operator previous) {
		Double bufferValue = fromBuffer();
		return (storedValue, enteredValue) -> {
			Double intermediate = next.call(bufferValue, enteredValue);
			return previous.call(storedValue, intermediate);
		};
	}


	double fromBuffer() {
		double value = content.getValue(displayFormatter);
		content = Content.EMPTY;
		return value;
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}

	@SuppressWarnings("unused") // Needed for Locale tests
	private void setLocale(Locale locale) {
	displayFormatter = new DisplayFormatter(locale);
}
}
