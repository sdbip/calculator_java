package kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

	@Test
	public void remembersEnteredValue() {
		Calculator calculator = new Calculator();
		calculator.enter(2.0);
		assertEquals(2.0, calculator.getValue(), 0);
	}

}
