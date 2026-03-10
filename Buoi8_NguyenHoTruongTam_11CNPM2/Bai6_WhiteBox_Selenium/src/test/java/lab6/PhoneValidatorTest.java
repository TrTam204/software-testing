package lab6;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneValidatorTest {

@Test
public void testNullPhone(){
Assert.assertFalse(PhoneValidator.isValid(null));
}

@Test
public void testValidPhone84(){
Assert.assertTrue(PhoneValidator.isValid("+84901234567"));
}

@Test
public void testValidPhone0(){
Assert.assertTrue(PhoneValidator.isValid("0901234567"));
}

@Test
public void testInvalidPrefix(){
Assert.assertFalse(PhoneValidator.isValid("0212345678"));
}

@Test
public void testInvalidLength(){
Assert.assertFalse(PhoneValidator.isValid("09012345"));
}

@Test
public void testInvalidCharacter(){
Assert.assertFalse(PhoneValidator.isValid("09a1234567"));
}

}