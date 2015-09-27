package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {
	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;

	String getDisplayedValue(double value, String buffer) {
		return buffer == null ? displayFormatter.format(value) : buffer;
	}

	String enterDigit(String digit, String buffer) {
		this.buffer = buffer != null ? buffer + digit : digit;
		return this.buffer;
	}

	String appendDecimalPointer(String buffer) {
		if (buffer == null) buffer = "0";
		buffer += displayFormatter.getDecimalSeparator();
		this.buffer = buffer;
		return this.buffer;
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
