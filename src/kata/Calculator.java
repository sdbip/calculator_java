package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class Calculator {
	private Locale locale = Locale.getDefault();
	private String display = "";

	public String getDisplay() {
		return display;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void enterDigit(String digit) {
		display += digit;
	}

	public void enterDecimalPointer() {
		if (Objects.equals(display, ""))
			display = "0";
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(getLocale());
		display += dfs.getDecimalSeparator();
	}

	public void calculate() {
		display = "";
	}
}
