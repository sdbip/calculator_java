package kata;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private interface Operator {
		Double call(Double storedValue, Double enteredValue);
	}
	final static Map<String, Operator> OPERATORS = new HashMap<String, Operator>() {{
		put("+", (Double storedValue, Double enteredValue) -> storedValue + enteredValue);
		put("×", (Double storedValue, Double enteredValue) -> storedValue * enteredValue);
		put("−", (Double storedValue, Double enteredValue) -> storedValue - enteredValue);
		put("÷", (Double storedValue, Double enteredValue) -> storedValue / enteredValue);
	}};

	private String buffer = null;
	final Buffer buf = new Buffer();

	private double value = 0;
	private Operator nextOperator;
	private boolean nextPrecedes = false;

	public String getDisplay() {
		return buf.getDisplayedValue(value, buffer);
	}

	public void enterDigit(String digit) {
		buffer = buf.enterDigit(digit, buffer);
	}

	public void enterDecimalPointer() {
		buffer = buf.appendDecimalPointer(buffer);
	}

	public void calculate() {
		value = callOperator(nextOperator, value, readBuffer());
	}

	public void pressOperator(String opLabel) {
		if (!nextPrecedes && hasPrecedence(opLabel)) {
			nextPrecedes = true;
			Double bufferValue = readBuffer();
			Operator next = OPERATORS.get(opLabel);
			Operator previous = nextOperator;
			nextOperator = (storedValue, enteredValue) -> {
				Double intermediate = next.call(bufferValue, enteredValue);
				return callOperator(previous, storedValue, intermediate);
			};
		} else {
			calculate();
			nextOperator = OPERATORS.get(opLabel);
		}
	}

	private Double readBuffer() {
		Double value = convertBufferToValue();
		buffer = null;
		return value;
	}

	private double convertBufferToValue() {
		return buf.toValue();
	}

	private Double callOperator(Operator operator, Double storedValue, Double enteredValue) {
		return operator != null ? operator.call(storedValue, enteredValue) : enteredValue;
	}

	private boolean hasPrecedence(String opLabel) {
		return "×÷".contains(opLabel);
	}

}
