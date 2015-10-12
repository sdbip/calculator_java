package kata;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/* TODO: rename "posterior"
highPrecedenceOperator
lowPrecedenceOperator

applyHigh...
applyLow...
*/

/*
Algorithm:
When selecting an operator the calculator will combine the "current value" and the operator into an new operator
and store that for future input.
The "current value" is calculated by taken the last input and apply any stored operator to it.

	"1+2+3=" stores the following at each step:
		1
		1+
		1+ 2
		3+
		3+ 3
		6

If a stored operator has lower precedence than the currently entered one that operator is not applied.

	"1+2*3=" stores the following at each step:
		1
		1+
		1+ 2
		1+ 2*
		1+ 2* 3
		7
 */

public class Calculator {
	final DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = "";
	private Function<Double, Double> operation;
	private Function<Double, Double> precedentOperation;
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

	public void divide() {
		setPrecedentOperation((d1, d2) -> d1 / d2);
	}

	public void multiply() {
		setPrecedentOperation((d1, d2) -> d1 * d2);
	}

	private void setOperation(BinaryOperator<Double> operator) {
		evaluate();
		// TODO: Hidden temporal dependency
		double capturedValue = value;
		operation = v -> operator.apply(capturedValue, v);
	}

	private void setPrecedentOperation(BinaryOperator<Double> operator) {
		evaluatePrecedentOperation();
		// TODO: Hidden temporal dependency
		double capturedValue = value;
		precedentOperation = v -> operator.apply(capturedValue, v);
	}

	private void evaluatePrecedentOperation() {
		parseBuffer();
		applyPrecedentOperation();
	}

	public void evaluate() { // TODO: rename - evaluateEntireExpression
		evaluatePrecedentOperation();
		applyPosteriorOperation();
	}

	private void parseBuffer() {
		value = displayFormatter.parse(buffer);
		buffer = "";
	}

	private void applyPosteriorOperation() {
		applyOperation(operation);
		operation = null;
	}

	private void applyPrecedentOperation() {
		applyOperation(precedentOperation);
		precedentOperation = null;
	}

	private void applyOperation(Function<Double, Double> operation) {
		if (operation != null)
			value = operation.apply(value);
	}
}
