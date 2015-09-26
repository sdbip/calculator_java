package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class Calculator {
	public Locale locale = Locale.getDefault();
	public String display = "";

	public void enterDigit(String digit) {
		display += digit;
	}

	public void enterDecimalPointer() {
		if (Objects.equals(display, ""))
			display = "0";
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
		display += dfs.getDecimalSeparator();
	}

	public void calculate() {
		display = "";
	}
}
