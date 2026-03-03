package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {

    WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "summary_subtotal_label")
    WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    public double layItemTotal() {
        return Double.parseDouble(
            itemTotalLabel.getText().replace("Item total: $","")
        );
    }

    public double layTax() {
        return Double.parseDouble(
            taxLabel.getText().replace("Tax: $","")
        );
    }

    public double layTotal() {
        return Double.parseDouble(
            totalLabel.getText().replace("Total: $","")
        );
    }
}