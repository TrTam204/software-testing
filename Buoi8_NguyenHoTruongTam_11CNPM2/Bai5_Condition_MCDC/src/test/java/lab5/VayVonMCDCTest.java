package lab5;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VayVonMCDCTest {

@Test(description="MC/DC - Tuoi doc lap")
public void testMCDC_TuoiDocLap() {

boolean result = VayVon.duDieuKienVay(20,12000000,true,750);

Assert.assertFalse(result);

}

@Test(description="MC/DC - ThuNhap doc lap")
public void testMCDC_ThuNhapDocLap() {

boolean result = VayVon.duDieuKienVay(25,9000000,true,750);

Assert.assertFalse(result);

}

@Test(description="MC/DC - TaiSan doc lap")
public void testMCDC_TaiSanDocLap() {

boolean result = VayVon.duDieuKienVay(25,12000000,false,650);

Assert.assertFalse(result);

}

@Test(description="MC/DC - TinDung doc lap")
public void testMCDC_TinDungDocLap() {

boolean result = VayVon.duDieuKienVay(25,12000000,false,750);

Assert.assertTrue(result);

}

@Test(description="MC/DC - Tat ca dieu kien dung")
public void testMCDC_AllTrue() {

boolean result = VayVon.duDieuKienVay(30,15000000,true,800);

Assert.assertTrue(result);

}

}