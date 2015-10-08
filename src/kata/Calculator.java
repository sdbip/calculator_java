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
		formatter = DecimalFormat.getInstance(locale);
	}

	public void add() {
		evaluate();
		try {
			double value = formatter.parse(buffer).doubleValue();
			operation = v -> value + v;
			buffer = "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void evaluate() {
		try {
			double value = formatter.parse(buffer).doubleValue();
			if (operation != null)
				value = operation.apply(value);
			buffer = formatter.format(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
