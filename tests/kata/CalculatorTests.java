package kata;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

	@Test
	public void pressedDigitIsDisplayed() {
		Calculator calculator = new Calculator();
		calculator.enterDigit("8"); // GUI identifies button by its label
		assertEquals("8", calculator.display);
	}

	@Test
	public void severalPressedDigitsAreDisplayedInOrder() {
		Calculator calculator = new Calculator();
		calculator.enterDigit("2");
		calculator.enterDigit("8");
		assertEquals("28", calculator.display);
	}

	@Test
	public void decimalPointerIsAddedToDisplay() {
		Calculator calculator = new Calculator();
		calculator.locale = Locale.US;
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2.8", calculator.display);
	}

	@Test
	public void decimalPointerHonorsLocale() {
		Calculator calculator = new Calculator();
		calculator.locale = Locale.FRANCE;
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2,8", calculator.display);
	}

}
