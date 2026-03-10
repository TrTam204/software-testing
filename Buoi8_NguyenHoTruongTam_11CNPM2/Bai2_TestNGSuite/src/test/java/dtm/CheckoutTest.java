package dtm;

import org.testng.annotations.Test;

public class CheckoutTest {

    @Test(groups={"smoke","regression"})
    public void testCheckout(){
        System.out.println("Checkout");
    }

    @Test(groups={"regression"})
    public void testPayment(){
        System.out.println("Payment");
    }
}