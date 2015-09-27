package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {

	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private Content content = Content.EMPTY;

	String getDisplayedValue(double value) {
		return content.getDisplayedValue(value, displayFormatter);
	}

	void enterDigit(String digit) {
		content = content.append(digit);
	}

	void appendDecimalPointer() {
		if (content.isEmpty()) content = content.append("0");
		content = content.append("" + displayFormatter.getDecimalSeparator());
	}

	double toValue() {
		String buf = content.get();
		content = Content.EMPTY;
		try {
			return displayFormatter.parse(buf);
		} catch (ParseException e) {
			throw new RuntimeException("The input buffer has grown inconsistent. Terminating application.", e);
		}
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}
