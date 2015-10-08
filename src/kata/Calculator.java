package kata;

import java.text.ParseException;
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
			return displayFormatter.formatter.format(value);
		return buffer;
	}

	public void enterDecimalPointer() {
		if (buffer.indexOf(displayFormatter.decimalSeparator) < 0)
			buffer += displayFormatter.decimalSeparator;
	}

	public void add() {
		evaluate();
		double value = this.value;
		operation = v -> value + v;
	}

	public void evaluate() {
		try {
			value = displayFormatter.formatter.parse(buffer).doubleValue();
			buffer = "";

			if (operation != null)
				value = operation.apply(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
