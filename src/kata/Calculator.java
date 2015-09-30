package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
	private interface Operator {
		double call(double value);
	}
	String buffer = "";

	private char decimalPoint = '.';
	private Operator deferred;
	public void pushDigit(char digit) {
		buffer += digit;
	}

	public String display() {
		return buffer;
	}

	public void pushDecimalPoint() {
		if (buffer.indexOf(decimalPoint) < 0)
			buffer += decimalPoint;
	}

	void setLocale(Locale locale) {
		decimalPoint = new DecimalFormatSymbols(locale).getDecimalSeparator();
	}

	public void pushPlus() {
		double value = new Double(buffer);
		buffer = "";
		deferred = x -> value + x;
	}

	public void pushMinus() {
		double value = new Double(buffer);
		buffer = "";
		deferred = x -> value - x;
	}

	public void calculate() {
		double value = new Double(buffer);
		value = deferred.call(value);
		buffer = Double.toString(value);
		if (buffer.endsWith(".0"))
			buffer = buffer.substring(0, buffer.length() - 2);
	}
}
