package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Stack;

public class Calculator {
	private class Operator {
		boolean hasPrecedence;
		Operation operation;

		Operator(boolean hasPrecedence, Operation operation) {
			this.hasPrecedence = hasPrecedence;
			this.operation = operation;
		}
	}
	private interface Operation {
		double call(double value);
	}

	String buffer = "";
	double value = 0;

	private char decimalPoint = '.';
	private Stack<Operator> deferred = new Stack<>();

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
		deferred.push(new Operator(false, x -> value + x));
	}

	public void pushMinus() {
		calculate();
		double value = this.value;
		deferred.push(new Operator(false, x -> value - x));
	}

	public void pushTimes() {
		calculateForHighPrecedence();
		double value = this.value;
		deferred.push(new Operator(true, x -> value * x));
	}

	public void pushDivide() {
		calculateForHighPrecedence();
		double value = this.value;
		deferred.push(new Operator(true, x -> value / x));
	}

	private void calculateForHighPrecedence() {
		if (hasDeferredOperatorWithLowPrecedence())
			clearBuffer();
		else
			calculate();
	}

	private boolean hasDeferredOperatorWithLowPrecedence() {
		return !deferred.empty() && !deferred.peek().hasPrecedence;
	}

	public void calculate() {
		clearBuffer();
		while (!deferred.empty()) {
			value = deferred.pop().operation.call(value);
		}
	}

	private void clearBuffer() {
		if (buffer.length() != 0) {
			value = new Double(buffer);
			buffer = "";
		}
	}
}
