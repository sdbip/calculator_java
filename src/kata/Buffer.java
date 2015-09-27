package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {
	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;

	String getDisplayedValue(double value) {
		return isEmpty() ? displayFormatter.format(value) : buffer;
	}

	void enterDigit(String digit) {
		buffer = !isEmpty() ? buffer + digit : digit;
	}

	void appendDecimalPointer() {
		if (isEmpty()) buffer = "0";
		buffer += displayFormatter.getDecimalSeparator();
	}

	double toValue() {
		try {
			double value = displayFormatter.parse(buffer);
			makeEmpty();
			return value;
		} catch (ParseException e) {
			crashApplication("The input buffer has grown inconsistent. Terminating application.", e);
			return 0.0; // Why do I need to put this line here? I already crashed!
		}
	}

	private void crashApplication(String message, Throwable e) {
		System.out.println(message);
		System.out.println(e.getLocalizedMessage());
		System.exit(1);
	}

	private void makeEmpty() {
		buffer = null;
	}

	private boolean isEmpty() {
		return buffer == null;
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}
