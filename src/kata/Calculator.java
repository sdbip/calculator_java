package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
	public Locale locale = Locale.getDefault();
	public String display = "";

	public void enterDigit(String digit) {
		display += digit;
	}

	public void enterDecimalPointer() {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
		display += dfs.getDecimalSeparator();
	}

	public void calculate() {
		display = "";
	}
}
