package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {
	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;

	String getDisplayedValue(double value) {
		return buffer == null ? displayFormatter.format(value) : buffer;
	}

	String enterDigit(String digit) {
		buffer = buffer != null ? buffer + digit : digit;
		return buffer;
	}

	String appendDecimalPointer() {
		if (buffer == null) buffer = "0";
		buffer += displayFormatter.getDecimalSeparator();
		return buffer;
	}

	double toValue() {
		try {
			double value = displayFormatter.parse(buffer);
			buffer = null;
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

	// Only intended for testing.
	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}
