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
