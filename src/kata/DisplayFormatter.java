package kata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class DisplayFormatter {
	char decimalSeparator = '.';
	NumberFormat formatter = DecimalFormat.getInstance();

	public void setLocale(Locale locale) {
		decimalSeparator = DecimalFormatSymbols.getInstance(locale).getDecimalSeparator();
		formatter = DecimalFormat.getInstance(locale);
	}
}