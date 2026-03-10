package lab3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiTest {

    @Test
    public void TC1_InvalidScore() {
        Assert.assertEquals(XepLoai.xepLoai(-1, false),
                "Diem khong hop le");
    }

    @Test
    public void TC2_Gioi() {
        Assert.assertEquals(XepLoai.xepLoai(9, false),
                "Gioi");
    }

    @Test
    public void TC3_Kha() {
        Assert.assertEquals(XepLoai.xepLoai(7.5, false),
                "Kha");
    }

    @Test
    public void TC4_TrungBinh() {
        Assert.assertEquals(XepLoai.xepLoai(6, false),
                "Trung Binh");
    }

    @Test
    public void TC5_ThiLai() {
        Assert.assertEquals(XepLoai.xepLoai(4, true),
                "Thi lai");
    }

    @Test
    public void TC6_YeuHocLai() {
        Assert.assertEquals(XepLoai.xepLoai(4, false),
                "Yeu - Hoc lai");
    }
}