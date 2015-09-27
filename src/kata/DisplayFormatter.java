package kata;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DisplayFormatter {
	private final DecimalFormatSymbols dfs;
	private final NumberFormat numberFormat;

	public DisplayFormatter() {
		dfs = new DecimalFormatSymbols(Locale.getDefault());
		numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
	}

	public DisplayFormatter(Locale locale) {
		dfs = new DecimalFormatSymbols(locale);
		numberFormat = NumberFormat.getNumberInstance(locale);
	}

	String format(double value) {
		return numberFormat.format(value);
	}

	double parse(String source) throws ParseException {
		Number number = numberFormat.parse(source);
		return number.doubleValue();
	}

	char getDecimalSeparator() {
		return dfs.getDecimalSeparator();
	}
}