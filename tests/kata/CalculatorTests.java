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
		assertEquals(2.0, calculator.calculate(), 0);
	}

	@Test
	public void knowsHowToAdd() {
		calculator.enter(2.0);
		calculator.add(1.5);
		assertEquals(3.5, calculator.calculate(), 0);
	}

	@Test
	public void knowsHowToSubtract() {
		calculator.enter(2.0);
		calculator.subtract(1.5);
		assertEquals(0.5, calculator.calculate(), 0);
	}

	@Test
	public void knowsHowToDivide() {
		calculator.enter(2.0);
		calculator.divide(0.5);
		assertEquals(4.0, calculator.calculate(), 0);
	}

	@Test
	public void knowsHowToMultiply() {
		calculator.enter(2.0);
		calculator.multiply(0.5);
		assertEquals(1.0, calculator.calculate(), 0);
	}

	@Test
	public void precedesMultiplicationOverAddition() {
		calculator.enter(1.0);
		calculator.add(2.0);
		calculator.multiply(3.0);
		assertEquals(7.0, calculator.calculate(), 0);
	}

	@Test
	public void precedesDivisionOverSubtraction() {
		calculator.enter(1.0);
		calculator.subtract(3.0);
		calculator.divide(2.0);
		assertEquals(-0.5, calculator.calculate(), 0);
	}

	@Test
	public void canAddMultipleNumbers() {
		calculator.enter(1.0);
		calculator.add(3.0);
		calculator.add(3.0);
		calculator.add(3.0);
		assertEquals(10, calculator.calculate(), 0);
	}

}
