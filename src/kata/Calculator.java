package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class Calculator {
	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
	private String display = "";
	private String buffer = null;

	public String getDisplay() {
		if (buffer == null)
			return display;
		else return buffer;
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		dfs = new DecimalFormatSymbols(locale);
	}

	public void enterDigit(String digit) {
		if (buffer == null) display = "";
		display += digit;
		buffer = display;
	}

	public void enterDecimalPointer() {
		if (Objects.equals(display, ""))
			display = "0";
		display += dfs.getDecimalSeparator();
		buffer = display;
	}

	public void calculate() {
		buffer = null;
	}
}
