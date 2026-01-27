package lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentCalculatorTest {

    @Test
    public void child_age0_17() {
        assertEquals(50, PaymentCalculator.calc(0, PaymentCalculator.Type.CHILD));
        assertEquals(50, PaymentCalculator.calc(17, PaymentCalculator.Type.CHILD));
    }

    @Test
    public void male_18_35() {
        assertEquals(100, PaymentCalculator.calc(18, PaymentCalculator.Type.MALE));
        assertEquals(100, PaymentCalculator.calc(35, PaymentCalculator.Type.MALE));
    }

    @Test
    public void male_36_50() {
        assertEquals(120, PaymentCalculator.calc(36, PaymentCalculator.Type.MALE));
        assertEquals(120, PaymentCalculator.calc(50, PaymentCalculator.Type.MALE));
    }

    @Test
    public void male_51_145() {
        assertEquals(140, PaymentCalculator.calc(51, PaymentCalculator.Type.MALE));
        assertEquals(140, PaymentCalculator.calc(145, PaymentCalculator.Type.MALE));
    }

    @Test
    public void female_18_35() {
        assertEquals(80, PaymentCalculator.calc(18, PaymentCalculator.Type.FEMALE));
        assertEquals(80, PaymentCalculator.calc(35, PaymentCalculator.Type.FEMALE));
    }

    @Test
    public void female_36_50() {
        assertEquals(110, PaymentCalculator.calc(36, PaymentCalculator.Type.FEMALE));
        assertEquals(110, PaymentCalculator.calc(50, PaymentCalculator.Type.FEMALE));
    }

    @Test
    public void female_51_145() {
        assertEquals(140, PaymentCalculator.calc(51, PaymentCalculator.Type.FEMALE));
        assertEquals(140, PaymentCalculator.calc(145, PaymentCalculator.Type.FEMALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void age_negative_throw() {
        PaymentCalculator.calc(-1, PaymentCalculator.Type.MALE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void age_too_big_throw() {
        PaymentCalculator.calc(146, PaymentCalculator.Type.FEMALE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void type_null_throw() {
        PaymentCalculator.calc(20, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void child_type_but_age_18_throw() {
        PaymentCalculator.calc(18, PaymentCalculator.Type.CHILD);
    }
}
