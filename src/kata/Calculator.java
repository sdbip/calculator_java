package kata;

import java.util.function.Function;


public class Calculator {
	final DisplayBuffer displayBuffer = new DisplayBuffer();

	private double value = 0.0;
	private Function<Double, Double> low;
	private Function<Double, Double> high;

	public void enterDigit(char c) {
		displayBuffer.enterDigit(c);
	}

	public String display() {
		return displayBuffer.display(value);
	}

	public void enterDecimalSeparator() {
		displayBuffer.enterDecimalSeparator();
	}

	public void pushAddition() {
		evaluate();
		double v = value;
		low = rhs -> v + rhs;
	}

	public void pushSubtraction() {
		evaluate();
		double v = value;
		low = rhs -> v - rhs;
	}

	public void pushDivision() {
		if (low == null)
			evaluate();
		else
			evaluateHighPrecedenceOperation();

		double v = value;
		high = rhs -> v / rhs;
	}

	public void pushMultiplication() {
		if (low == null)
			evaluate();
		else
			evaluateHighPrecedenceOperation();

		double v = value;
		high = rhs -> v * rhs;
	}

	public void evaluate() {
		evaluateHighPrecedenceOperation();
		applyLowPrecedenceOperation();
	}

	private void evaluateHighPrecedenceOperation() {
		clearDisplayBuffer();
		applyHighPrecedenceOperation();
	}

	private void clearDisplayBuffer() {
		this.value = displayBuffer.readBuffer();
		displayBuffer.clear();
	}

	private void applyHighPrecedenceOperation() {
		if (high != null) value = high.apply(value);
		high = null;
	}

	private void applyLowPrecedenceOperation() {
		if (low != null) value = low.apply(value);
		low = null;
	}

}
