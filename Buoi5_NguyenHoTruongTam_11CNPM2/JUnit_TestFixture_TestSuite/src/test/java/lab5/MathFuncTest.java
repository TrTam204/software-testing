package lab5;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathFuncTest {
    private MathFunc math;

    @Before
    public void init() { math = new MathFunc(); }

    @After
    public void tearDown() { math = null; }

    @Test
    public void calls() {
        assertEquals(0, math.getCalls());
        math.factorial(1);
        assertEquals(1, math.getCalls());
        math.factorial(1);
        assertEquals(2, math.getCalls());
    }

    @Test
    public void factorial() {
        assertTrue(math.factorial(0) == 1);
        assertTrue(math.factorial(1) == 1);
        assertTrue(math.factorial(5) == 120);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        math.factorial(-1);
    }

    @Ignore
    @Test
    public void todo() {
        assertTrue(math.plus(1, 1) == 2);
    }

    // ===== BAI 2 - Boundary Value Tests =====
    @Test
    public void testIsValidScore_Boundary() {
        assertTrue(math.isValidScore(0));    // bi?n d??i
        assertTrue(math.isValidScore(100));  // bi?n tr?n
    }

    @Test
    public void testIsValidScore_OutsideBoundary() {
        assertFalse(math.isValidScore(-1));  // d??i bi?n
        assertFalse(math.isValidScore(101)); // tr?n bi?n
    }

    // ===== BAI 3 - Exception Tests =====
    @Test
    public void testDivideNormal() {
        assertEquals(2, math.divide(4, 2));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        math.divide(4, 0);
    }
}

