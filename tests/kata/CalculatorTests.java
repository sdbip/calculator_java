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
	public void remembersEnteredValue() {
		calculator.enter(2.0);
		assertEquals(2.0, calculator.getValue(), 0);
	}

	@Test
	public void knowsHowToAdd() {
		calculator.enter(2.0);
		calculator.add(1.5);
		assertEquals(3.5, calculator.getValue(), 0);
	}

	@Test
	public void knowsHowToMultiply() {
		calculator.enter(2.0);
		calculator.multiply(0.5);
		assertEquals(1.0, calculator.getValue(), 0);
	}

}
