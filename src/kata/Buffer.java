package kata;

import java.text.ParseException;

class Buffer {
	String getDisplayedValue(DisplayFormatter formatter, double value, String buffer) {
		return buffer == null ? formatter.format(value) : buffer;
	}

	String enterDigit(String digit, String buffer) {
		buffer = buffer != null ? buffer + digit : digit;
		return buffer;
	}

	String appendDecimalPointer(String buffer, DisplayFormatter displayFormatter) {
		if (buffer == null) buffer = "0";
		buffer += displayFormatter.getDecimalSeparator();
		return buffer;
	}

	double toValue(DisplayFormatter displayFormatter, String buffer, Calculator calculator) {
		try {
			return displayFormatter.parse(buffer);
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
}
