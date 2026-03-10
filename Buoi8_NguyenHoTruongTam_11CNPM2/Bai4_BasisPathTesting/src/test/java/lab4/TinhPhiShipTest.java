package lab4;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhPhiShipTest {

@Test
public void TC1_invalidWeight() {
try {
TinhPhiShip.tinhPhiShip(0,"noi_thanh",false);
Assert.fail("Expected exception");
} catch (IllegalArgumentException e) {
Assert.assertTrue(true);
}
}

@Test
public void TC2_noiThanh_nhoHon5() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(4,"noi_thanh",false),
15000
);
}

@Test
public void TC3_noiThanh_lonHon5() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(7,"noi_thanh",false),
19000
);
}

@Test
public void TC4_ngoaiThanh_nhoHon3() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(2,"ngoai_thanh",false),
25000
);
}

@Test
public void TC5_ngoaiThanh_lonHon3() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(5,"ngoai_thanh",false),
31000
);
}

@Test
public void TC6_tinhKhac() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(4,"tinh_khac",false),
60000
);
}

@Test
public void TC7_tinhKhac_lonHon2() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(4,"tinh_khac",false),
60000
);
}

@Test
public void TC8_member_discount() {
Assert.assertEquals(
TinhPhiShip.tinhPhiShip(4,"noi_thanh",true),
13500
);
}

}