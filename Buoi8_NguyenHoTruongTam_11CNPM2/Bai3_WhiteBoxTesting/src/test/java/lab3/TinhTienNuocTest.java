package lab3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocTest {

@Test
public void TC1_soAm() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(-1,"dan_cu"),0);
}

@Test
public void TC2_hoNgheo() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(5,"ho_ngheo"),25000);
}

@Test
public void TC3_danCuBac1() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(5,"dan_cu"),37500);
}

@Test
public void TC4_danCuBac2() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(15,"dan_cu"),148500);
}

@Test
public void TC5_danCuBac3() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(30,"dan_cu"),342000);
}

@Test
public void TC6_kinhDoanh() {
Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(10,"kinh_doanh"),220000);
}

}