package kata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	@Test
	public void canEnterDigits() {
		calculator.enterDigit('1');
		calculator.enterDigit('2');
		assertEquals("12", calculator.display());
	}

}
