package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
	private String buffer = null;
	private double value = 0;

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
		value = new Double(buffer);
		buffer = null;
	}
}
