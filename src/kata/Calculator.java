package kata;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Calculator {
	final DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = "";
	private Function<Double, Double> operation;
	private double value = 0;

	public void enterDigit(char c) {
		buffer += c;
	}

	public String display() {
		if (buffer.length() == 0)
			return displayFormatter.format(value);
		return buffer;
	}

	public void enterDecimalPointer() {
		if (buffer.indexOf(displayFormatter.decimalSeparator) < 0)
			buffer += displayFormatter.decimalSeparator;
	}

	public void add() {
		setOperation((d1, d2) -> d1 + d2);
	}

	public void subtract() {
		setOperation((d1, d2) -> d1 - d2);
	}

	public void multiply() {
		setOperation((d1, d2) -> d1 * d2);
	}

	private void setOperation(BinaryOperator<Double> operator) {
		evaluate();
		double capturedValue = value;
		operation = v -> operator.apply(capturedValue, v);
	}

	public void evaluate() {
		value = displayFormatter.parse(buffer);
		buffer = "";

		if (operation != null)
			value = operation.apply(value);
	}
}
