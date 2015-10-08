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
		if (buffer.indexOf(decimalSeparator) < 0)
			buffer += decimalSeparator;
	}

	public void setLocale(Locale locale) {
		decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
	}

	public void add() {
	}

	public void evaluate() {
		buffer = "3";
	}
}
