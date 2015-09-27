package kata;

import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Calculator {
	private boolean nextPrecedes = false;

	private interface Operation {
		Double perform(Double value, Double next);
	}

	final Map<String, Operation> OPERATORS = new HashMap<String, Operation>() {{
		put("+", (Double i1, Double i2) -> i1 + i2);
		put("×", (Double i1, Double i2) -> i1 * i2);
		put("−", (Double i1, Double i2) -> i1 - i2);
		put("÷", (Double i1, Double i2) -> i1 / i2);
	}};

	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
	private String buffer = null;
	private double value = 0;
	private Operation nextOperation;

	public String getDisplay() {
		if (buffer == null) {
			String result = Double.toString(value);
			if (result.endsWith(".0"))
				result = Integer.toString((int) value);
			return result;
		}
		else return buffer;
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		dfs = new DecimalFormatSymbols(locale);
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
		value = performOperation(nextOperation, value, new Double(buffer));
		buffer = null;
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			Double bufferValue = new Double(buffer);
			Operation next = OPERATORS.get(opLabel);
			Operation previous = nextOperation;
			nextOperation = (i1, i2) -> {
				Double intermediate = next.perform(bufferValue, i2);
				return performOperation(previous, i1, intermediate);
			};
			buffer = null;
		} else {
			calculate();
			nextOperation = OPERATORS.get(opLabel);
		}
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
