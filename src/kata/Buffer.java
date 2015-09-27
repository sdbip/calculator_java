package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {

	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;
	private Content content = Content.empty();

	String getDisplayedValue(double value) {
		Content content = new Content(buffer);
		return content.getDisplayedValue(value, displayFormatter);
	}

	void enterDigit(String digit) {
		content = new Content(buffer);
		content.append(digit);
		buffer = content.toString();
	}

	void appendDecimalPointer() {
		content = new Content(buffer, "0");
		content.append("" + displayFormatter.getDecimalSeparator());
		buffer = content.toString();
	}

	double toValue() {
		String buf = clear();
		try {
			return displayFormatter.parse(buf);
		} catch (ParseException e) {
			crashApplication("The input buffer has grown inconsistent. Terminating application.", e);
			return 0.0; // Why do I need to put this line here? I already crashed!
		}
	}

	private String clear() {
		String buf = content.toString();
		content = Content.empty();
		buffer = content.toString();
		return buf;
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
