package kata;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.Function;

public class Calculator {
	private char decimalSeparator = '.';
	private String buffer = "";
	private Function<Double, Double> operation;

	public void enterDigit(char c) {
		buffer += c;
	}

	public String display() {
		return buffer;
	}

	public void enterDecimalPointer() {
		if (buffer.indexOf(decimalSeparator) < 0)
			buffer += decimalSeparator;
	}

	public void setLocale(Locale locale) {
		decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
	}

	public void add() {
		evaluate();
		double value = Double.parseDouble(buffer);
		operation = v -> value + v;
		buffer = "";
	}

	public void evaluate() {
		double value = Double.parseDouble(buffer);
		if (operation != null)
			value = operation.apply(value);
		buffer = Double.toString(value).replace(".0", "");
	}
}
