package kata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.function.Function;

public class Calculator {
	private char decimalSeparator = '.';
	private NumberFormat formatter = DecimalFormat.getInstance();
	private String buffer = "";
	private Function<Double, Double> operation;
	private double value = 0;

	public void enterDigit(char c) {
		buffer += c;
	}

	public String display() {
		if (buffer.length() == 0)
			return formatter.format(value);
		return buffer;
	}

	public void enterDecimalPointer() {
		if (buffer.indexOf(decimalSeparator) < 0)
			buffer += decimalSeparator;
	}

	public void setLocale(Locale locale) {
		decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
		formatter = DecimalFormat.getInstance(locale);
	}

	public void add() {
		evaluate();
		double value = this.value;
		operation = v -> value + v;
	}

	public void evaluate() {
		try {
			value = formatter.parse(buffer).doubleValue();
			buffer = "";

			if (operation != null)
				value = operation.apply(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
