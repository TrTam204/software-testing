package lab6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTextBoxWhiteBoxTest {

@Test
public void testValidInput(){

WebDriver driver = new ChromeDriver();

driver.get("https://demoqa.com/text-box");

TextBoxPage page = new TextBoxPage(driver);

page.fillAndSubmit("Tam","tam@gmail.com","HCM");

Assert.assertTrue(page.isOutputDisplayed());

driver.quit();

}

}