package kata;

import java.text.ParseException;
import java.util.Locale;

public class DisplayBuffer {
	private DisplayFormatter displayFormatter = new DisplayFormatter();

	private String buffer = "";

	double readBuffer() {
		try {
			return displayFormatter.parse(buffer);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	void enterDecimalSeparator() {
		if (buffer.length() == 0)
			buffer = "0" + displayFormatter.decimalSeparator;
		else if (buffer.indexOf(displayFormatter.decimalSeparator) < 0)
			buffer += displayFormatter.decimalSeparator;
	}

	void clear() {
		buffer = "";
	}

	void enterDigit(char c) {
		assert Character.isDigit(c);
		buffer += c;
	}

	String display(double value) {
		if (buffer.length() == 0) {
			return displayFormatter.format(value);
		}
		return buffer;
	}

	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}