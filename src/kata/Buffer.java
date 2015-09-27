package kata;

import java.text.ParseException;
import java.util.Locale;

class Buffer {

	class Content {
		String buffer;

		public Content(String buffer) {
			this.buffer = buffer;
		}

		public Content(String buffer, String alternate) {
			if (isEmpty()) {
				Buffer.this.buffer = ""; // !isEmpty() in append()
				this.buffer = alternate;
			} else {
				this.buffer = buffer;
			}
		}

		public void append(String digit) {
			buffer = !isEmpty() ? buffer + digit : digit;
		}

		@Override
		public String toString() {
			return buffer;
		}

		public Content clear() {
			buffer = null;
			return this;
		}

		public String getDisplayedValue(double value) {
			return isEmpty() ? displayFormatter.format(value) : buffer;
		}

		public boolean isEmpty_() {
			return buffer == null;
		}
	}

	private DisplayFormatter displayFormatter = new DisplayFormatter();
	private String buffer = null;

	String getDisplayedValue(double value) {
		Content content = new Content(buffer);
		return content.getDisplayedValue(value);
	}

	void enterDigit(String digit) {
		Content content = new Content(buffer);
		content.append(digit);
		buffer = content.toString();
	}

	void appendDecimalPointer() {
		Content content = new Content(buffer, "0");
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
		String buf = buffer;
		Content content = new Content(buffer);
		content = content.clear();
		buffer = content.toString();
		return buf;
	}

	private void crashApplication(String message, Throwable e) {
		System.out.println(message);
		System.out.println(e.getLocalizedMessage());
		System.exit(1);
	}

	private boolean isEmpty() {
		Content content = new Content(buffer);
		return content.isEmpty_();
	}

	// Only intended for testing.
	void setLocale(Locale locale) {
		displayFormatter = new DisplayFormatter(locale);
	}
}
