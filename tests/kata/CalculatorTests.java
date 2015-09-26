package kata;

import org.junit.Test;

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

}
