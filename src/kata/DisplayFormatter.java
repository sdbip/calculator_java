package kata;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DisplayFormatter {
	private final DecimalFormatSymbols symbols;
	private final NumberFormat numberFormat;

	public DisplayFormatter() { this(Locale.getDefault()); }

	public DisplayFormatter(Locale locale) {
		symbols = new DecimalFormatSymbols(locale);
		numberFormat = NumberFormat.getNumberInstance(locale);
	}

	String format(double value) {
		return numberFormat.format(value);
	}

	double parse(String source) {
		try {
			Number number = numberFormat.parse(source);
			return number.doubleValue();
		} catch (ParseException e) {
			throw new RuntimeException("The input buffer has grown inconsistent. Terminating application.", e);
		}
	}

	char getDecimalSeparator() {
		return symbols.getDecimalSeparator();
	}
}