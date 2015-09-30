package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Stack;

public class Calculator {
	private interface Operator {
		double call(double value);
	}

	String buffer = "";
	double value = 0;

	private char decimalPoint = '.';
	private Stack<Operator> deferred = new Stack<>();
	private Stack<Boolean> deferredPrecedes = new Stack<>();

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
		deferred.push(x -> value + x);
		deferredPrecedes.push(false);
	}

	public void pushMinus() {
		calculate();
		double value = this.value;
		deferred.push(x -> value - x);
		deferredPrecedes.push(false);
	}

	public void pushTimes() {
		if (!deferredPrecedes.empty() && !deferredPrecedes.peek()) {
			if (buffer.length() != 0) {
				this.value = new Double(buffer);
				buffer = "";
			}
		} else {
			calculate();
		}

		double value = this.value;
		deferred.push(x -> value * x);
		deferredPrecedes.push(true);
	}

	public void pushDivide() {
		calculate();
		double value = this.value;
		deferred.push(x -> value / x);
		deferredPrecedes.push(true);
	}

	public void calculate() {
		double value = buffer.length() == 0 ? this.value : new Double(buffer);
		buffer = "";
		while (!deferred.empty()) {
			deferredPrecedes.pop();
			value = deferred.pop().call(value);
		}
		this.value = value;
	}
}
