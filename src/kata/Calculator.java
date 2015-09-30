package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator {
	private interface Operator {
		double call(double value);
	}

	String buffer = "";
	double value = 0;

	private char decimalPoint = '.';
	private Operator deferred = o -> o;
	public void pushDigit(char digit) {
		buffer += digit;
	}

	public String display() {
		if (buffer.length() == 0) {
			String display = Double.toString(value);
			if (display.endsWith(".0")) {
				display = display.substring(0, display.length() - 2);
			}
			return display;
		} else {
			return buffer;
		}
	}

	public void pushDecimalPoint() {
		if (buffer.indexOf(decimalPoint) < 0)
			buffer += decimalPoint;
	}

	void setLocale(Locale locale) {
		decimalPoint = new DecimalFormatSymbols(locale).getDecimalSeparator();
	}

	public void pushPlus() {
		calculate();
		double value = this.value;
		deferred = x -> value + x;
	}

	public void pushMinus() {
		calculate();
		double value = this.value;
		deferred = x -> value - x;
	}

	public void pushTimes() {
		calculate();
		double value = this.value;
		deferred = x -> value * x;
	}

	public void pushDivide() {
		calculate();
		double value = this.value;
		deferred = x -> value / x;
	}

	public void calculate() {
		double value = buffer.length() == 0 ? this.value : new Double(buffer);
		buffer = "";
		this.value = deferred.call(value);
	}
}
