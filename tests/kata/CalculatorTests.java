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
		calculator.displayFormatter.setLocale(Locale.US);
	}

	@Test
	public void canEnterDigits() {
		enterIntoCalculator("12");
		assertEquals("12", calculator.display());
	}

	private void enterIntoCalculator(String value) {
		for (char c : value.toCharArray()) {
			if (Character.isDigit(c))
				calculator.enterDigit(c);
			else
				calculator.enterDecimalPointer();
		}
	}

	@Test
	public void canEnterDecimal() {
		enterIntoCalculator("1.1");
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void canOnlyEnterOneDecimal() {
		enterIntoCalculator("1..1");
		assertEquals("1.1", calculator.display());
	}

	@Test
	public void honorsLocaleWhenEnteringNumbers() {
		calculator.displayFormatter.setLocale(Locale.FRANCE);

		enterIntoCalculator("1.1");
		assertEquals("1,1", calculator.display());
	}

	@Test
	public void canAdd() {
		enterIntoCalculator("1.1");
		calculator.add();
		enterIntoCalculator("2");
		calculator.evaluate();
		assertEquals("3.1", calculator.display());
	}

	@Test
	public void canAddRepeatedly() {
		enterIntoCalculator("1");
		calculator.add();
		enterIntoCalculator("2");
		calculator.add();
		enterIntoCalculator("2");
		calculator.evaluate();
		assertEquals("5", calculator.display());
	}

	@Test
	public void honorsLocaleWhenAdding() {
		calculator.displayFormatter.setLocale(Locale.FRANCE);

		enterIntoCalculator("1.1");
		calculator.add();
		enterIntoCalculator("2");
		calculator.evaluate();
		assertEquals("3,1", calculator.display());
	}

	@Test
	public void canSubtract() {
		enterIntoCalculator("6");
		calculator.subtract();
		enterIntoCalculator("2");
		calculator.subtract();
		enterIntoCalculator("3");
		calculator.evaluate();
		assertEquals("1", calculator.display());
	}

	@Test
	public void canMultiply() {
		enterIntoCalculator("6");
		calculator.multiply();
		enterIntoCalculator("2");
		calculator.multiply();
		enterIntoCalculator("3");
		calculator.evaluate();
		assertEquals("36", calculator.display());
	}

	@Test
	public void multipliesBeforeAddition() {
		enterIntoCalculator("1");
		calculator.add();
		enterIntoCalculator("2");
		calculator.multiply();
		enterIntoCalculator("3");
		calculator.evaluate();
		assertEquals("7", calculator.display());
	}

	@Test
	public void canDivide() {
		enterIntoCalculator("6");
		calculator.divide();
		enterIntoCalculator("2");
		calculator.divide();
		enterIntoCalculator("3");
		calculator.evaluate();
		assertEquals("1", calculator.display());
	}

	@Test
	public void dividesBeforeSubtraction() {
		enterIntoCalculator("5");
		calculator.subtract();
		enterIntoCalculator("4");
		calculator.divide();
		enterIntoCalculator("2");
		calculator.evaluate();
		assertEquals("3", calculator.display());
	}

}
