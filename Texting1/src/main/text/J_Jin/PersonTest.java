package J_Jin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("测试person类添加多个package行李箱的函数")
    void setPackages() {
        Package p1 =new Package(32,0,0,0,1);
        Package p2 =new Package(31,0,0,0,2);
        Package p3 =new Package(40,0,0,0,2);
        Package p4 =new Package(40,0,0,0,1);
        Package p5 =new Package(9,0,0,0,1);
        Package p6 =new Package(31,0,0,0,2);
        Package[] p = new Package[6];
        p[0] = p1;
        p[1] = p2;
        p[2] = p3;
        p[3] = p4;
        p[4] = p5;
        p[5] = p6;
        Person person = new Person();
        person.setPackages(p,6);
        assertEquals(6,person.GetPackageCount());
        assertEquals(32,person.packages[0].getPak_Weight());

    }

    @Test
    @DisplayName("测试person类添加多个package行李箱的函数")
    void add_Package() {
        Package p1 =new Package(32,0,0,0,1);
        Person person = new Person();
        person.Add_Package(p1);
        assertEquals(32,person.packages[0].getPak_Weight());
        assertEquals(1,person.GetPackageCount());
        person.Add_Package(p1);
        assertEquals(2,person.GetPackageCount());
    }
}