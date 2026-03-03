package dtm.tests;

import dtm.base.BaseTest;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        System.out.println("Opened Google successfully");
    }
}
