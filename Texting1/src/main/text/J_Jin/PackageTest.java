package J_Jin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageTest {
    Package pac1 = new Package(11,12,13,14,0);
    Package pac2 = new Package(12,0,12,12,1);
    Package pac3 = new Package(12,12,0,12,0);
    Package pac4 = new Package();
    @BeforeAll
    @DisplayName("pac_lengths为箱子总长度，为了前端的方便我给了三种值，分别是1：<=115,2<=158,3:158-300")
    static void init() {
        System.out.println("start text!");
}

    @AfterAll
    static void End(){
        System.out.println("Text End");
    }

    @Test
    @DisplayName("测试获取行李箱重量")
    void getPak_Weight() {
        assertEquals(11,pac1.getPak_Weight());
    }

@Test
    @DisplayName("测试获取行李箱长")
    void getPak_depth() {
        assertEquals(14,pac1.getPak_depth());
    }

    @Test
    @DisplayName("测试获取行李箱宽")
    void getPak_width() {
        assertEquals(13,pac1.getPak_width());
    }

    @Test
    @DisplayName("测试获取行李箱高")
    void getPak_height() {
        assertEquals(12,pac1.getPak_height());
    }

    @Test
    @DisplayName("测试获取行李箱总长度")
    void getPak_lengths() {
        assertEquals(1,pac1.getPak_lengths());
        assertEquals(1,pac2.getPak_lengths());
        assertEquals(1,pac3.getPak_lengths());
    }

    @Test
    @DisplayName("测试设置行李箱重量")
    void setPak_Weight() {
        pac4.setPak_Weight(21);
        assertEquals(21,pac4.getPak_Weight());
    }

    @Test
    @DisplayName("测试设置行李箱长")
    void setPak_height() {
        pac4.setPak_height(22);
        assertEquals(22,pac4.getPak_height());
    }

    @Test
    @DisplayName("测试设置行李箱宽")
    void setPak_width() {
        pac4.setPak_width(23);
        assertEquals(23,pac4.getPak_width());
    }

    @Test
    @DisplayName("测试设置行李箱高")
    void setPak_depth() {
        pac4.setPak_depth(24);
        assertEquals(24,pac4.getPak_depth());
    }

    @Test
    @DisplayName("测试设置行李箱总长度")
    void setPak_lengths() {
        pac4.setPak_lengths(2);
        assertEquals(2,pac4.getPak_lengths());
    }

    @Test
    void isIschacked() {
        assertFalse(pac4.isIschacked());
    }

    @Test
    void setIschacked() {
        pac4.setIschacked(true);
        assertTrue(pac4.isIschacked());
    }
}