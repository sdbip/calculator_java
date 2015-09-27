package kata;

import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Calculator {
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
		if (nextOperation == null)
			value = new Double(buffer);
		else {
			value = nextOperation.perform(value, new Double(buffer));
		}
		buffer = null;
	}

	public void pressOperator(String opLabel) {
		if (hasPrecedence(opLabel)) {
			Double bufferValue = new Double(buffer);
			Operation next = OPERATORS.get(opLabel);
			Operation previous = nextOperation;
			nextOperation = (i1, i2) -> {
				Double intermediate = next.perform(bufferValue, i2);
				if (previous == null)
					return intermediate;
				return previous.perform(i1, intermediate);
			};
			buffer = null;
		} else {
			calculate();
			nextOperation = OPERATORS.get(opLabel);
		}
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}
}
