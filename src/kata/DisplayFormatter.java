package kata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DisplayFormatter {
	private NumberFormat format;
	char decimalSeparator; // TODO private

	public DisplayFormatter() {
		this(Locale.getDefault());
	}

	public DisplayFormatter(Locale locale) {
		decimalSeparator = new DecimalFormatSymbols(locale).getDecimalSeparator();
		format = DecimalFormat.getInstance(locale);
	}

	public double parse(String buffer) throws ParseException {
		return format.parse(buffer).doubleValue();
	}

	public String format(double value) {
		return format.format(value);
	}
}
