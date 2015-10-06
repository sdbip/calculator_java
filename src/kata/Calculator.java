package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
	private String buffer = "";
	private char decimalSeparator = '.';

	public void enterDigit(char c) {
		buffer += c;
	}

	public String display() {
		return buffer;
	}

	public void enterDecimalPointer() {
		buffer += decimalSeparator;
	}

	public void setLocale(Locale locale) {
		decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
	}
}
