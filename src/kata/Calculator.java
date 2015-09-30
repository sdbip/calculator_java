package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class Calculator {
	private class Operation {
		private double lhs;
		private Operator operator;

		Operation(double lhs, Operator operator) {
			this.lhs = lhs;
			this.operator = operator;
		}

		boolean hasPrecedence() {
			return operator.hasPrecedence;
		}

		double call(double rhs) {
			return operator.function.apply(lhs, rhs);
		}
	}

	private enum Operator {
		PLUS(false, (l, r) -> l + r),
		MINUS(false, (l, r) -> l - r),
		TIMES(true, (l, r) -> l * r),
		DIVIDE(true, (l, r) -> l / r);

		boolean hasPrecedence;
		BinaryOperator<Double> function;

		Operator(boolean hasPrecedence, BinaryOperator<Double> function) {
			this.hasPrecedence = hasPrecedence;
			this.function = function;
		}
	}

	String buffer = "";
	double value = 0;

	private char decimalPoint = '.';
	private Stack<Operation> deferred = new Stack<>();

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
		pushOperator(Operator.PLUS);
	}

	public void pushMinus() {
		pushOperator(Operator.MINUS);
	}

	public void pushTimes() {
		pushOperator(Operator.TIMES);
	}

	public void pushDivide() {
		pushOperator(Operator.DIVIDE);
	}

	private void pushOperator(Operator operator) {
		clearBuffer();
		if (!operator.hasPrecedence || !hasDeferredOperatorWithLowPrecedence())
			applyDeferredOperators();
		deferred.push(new Operation(this.value, operator));
	}

	private boolean hasDeferredOperatorWithLowPrecedence() {
		return !deferred.empty() && !deferred.peek().hasPrecedence();
	}

	public void calculate() {
		clearBuffer();
		applyDeferredOperators();
	}

	private void applyDeferredOperators() {
		while (!deferred.empty()) {
			value = deferred.pop().call(value);
		}
	}

	private void clearBuffer() {
		if (buffer.length() != 0) {
			value = new Double(buffer);
			buffer = "";
		}
	}
}
