package lab4;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhiShipBasisPathTest {

@Test(description = "Path 1: Trọng lượng không hợp lệ")
public void testPath1_InvalidWeight() {

Assert.assertThrows(
IllegalArgumentException.class,
() -> TinhPhiShip.tinhPhiShip(-1, "noi_thanh", false)
);

}

@Test(description = "Path 2: Nội thành, <=5kg, không member")
public void testPath2_NoiThanhNheKhongMember() {

double expected = 15000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(3, "noi_thanh", false),
expected,
0.01
);

}

@Test(description = "Path 3: Nội thành, >5kg, không member")
public void testPath3_NoiThanhNangKhongMember() {

double expected = 19000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(7, "noi_thanh", false),
expected,
0.01
);

}

@Test(description = "Path 4: Ngoại thành, <=3kg")
public void testPath4_NgoaiThanhNhe() {

double expected = 25000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(2, "ngoai_thanh", false),
expected,
0.01
);

}

@Test(description = "Path 5: Ngoại thành, >3kg")
public void testPath5_NgoaiThanhNang() {

double expected = 31000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(5, "ngoai_thanh", false),
expected,
0.01
);

}

@Test(description = "Path 6: Tỉnh khác, <=2kg")
public void testPath6_TinhKhacNhe() {

double expected = 50000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(2, "tinh_khac", false),
expected,
0.01
);

}

@Test(description = "Path 7: Tỉnh khác, >2kg")
public void testPath7_TinhKhacNang() {

double expected = 60000;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(4, "tinh_khac", false),
expected,
0.01
);

}

@Test(description = "Path 8: Nội thành, member giảm giá")
public void testPath8_MemberDiscount() {

double expected = 13500;

Assert.assertEquals(
TinhPhiShip.tinhPhiShip(3, "noi_thanh", true),
expected,
0.01
);

}

}