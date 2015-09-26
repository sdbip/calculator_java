package kata;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
		calculator.locale = Locale.US;
	}

	@Test
	public void pressedDigitIsDisplayed() {
		calculator.enterDigit("8"); // GUI identifies button by its label
		assertEquals("8", calculator.display);
	}

	@Test
	public void severalPressedDigitsAreDisplayedInOrder() {
		calculator.enterDigit("2");
		calculator.enterDigit("8");
		assertEquals("28", calculator.display);
	}

	@Test
	public void decimalPointerIsAddedToDisplay() {
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2.8", calculator.display);
	}

	@Test
	public void decimalPointerHonorsLocale() {
		calculator.locale = Locale.FRANCE;
		calculator.enterDigit("2");
		calculator.enterDecimalPointer();
		calculator.enterDigit("8");
		assertEquals("2,8", calculator.display);
	}

}
