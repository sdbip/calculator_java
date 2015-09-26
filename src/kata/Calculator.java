package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class Calculator {
	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
	private String display = "";

	public String getDisplay() {
		return display;
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		dfs = new DecimalFormatSymbols(locale);
	}

	public void enterDigit(String digit) {
		display += digit;
	}

	public void enterDecimalPointer() {
		if (Objects.equals(display, ""))
			display = "0";
		display += dfs.getDecimalSeparator();
	}

	public void calculate() {
		display = "";
	}
}
