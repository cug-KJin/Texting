package J_Jin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Cal_CostTest {
    Flights flights;
    @BeforeAll
    @DisplayName("计算类只选取一个国内的和一个国外的测试用例")
    static void initAll(){
            System.out.println("text start");
    }

    @Test
    @DisplayName("国内托运之计算重量，(int)(31+32+40-40-20)*2000*0.015 = 1290")
    void calFli0() {
        flights = new Flights();
        Package p1 =new Package(32,0,0,0,1);
        Package p2 =new Package(31,0,0,0,2);
        Package p3 =new Package(40,0,0,0,2);
        Package p4 =new Package(40,0,0,0,1);
        Package p5 =new Package(9,0,45,34,0);
        Package p6 =new Package(31,0,45,45,0);
        Person person = new Person();
        person.Add_Package(p1);
        person.Add_Package(p2);
        person.Add_Package(p3);
        person.setLevel(0);
        person.setLevel2(0);
        boolean b = (1==1?true:false);
        person.setWeather_Carry_Kid(b);
        Flights flights = new Flights();
        flights.setPerson(person);
        flights.setCurrentCost(2000);
        flights.setFli_l(0);
        flights.setFli_Num(0);
        Cal_Cost cal_cost = new Cal_Cost(flights);
        assertEquals(1290,cal_cost.CalFli0(flights));
    }

    @Test
    @DisplayName("先添加2个正常箱子，然后分别添加错误重量、尺寸进行测试")
    void calFli1() {
        flights = new Flights();
        Package p1 =new Package(32,0,0,0,1);
        Package p2 =new Package(31,0,0,0,2);
        Package p3 =new Package(40,0,0,0,2);
        Package p4 =new Package(50,0,0,0,1);
        Package p5 =new Package(9,0,45,34,0);
        Package p6 =new Package(31,0,45,45,0);
        Person person = new Person();
        person.Add_Package(p1);
        person.Add_Package(p2);
        person.Add_Package(p3);
        person.setLevel(0);
        person.setLevel2(0);
        boolean b = (1==1?true:false);
        person.setWeather_Carry_Kid(b);
        Flights flights = new Flights();
        flights.setPerson(person);
        flights.setFli_l(1);
        flights.setFli_Num(1);
        Cal_Cost cal_cost = new Cal_Cost(flights);
        assertEquals(5000,cal_cost.CalFli1(flights));
        System.out.println("添加一只尺寸不合格的，返回-2");
        person.Add_Package(p6);
        assertEquals(-2,cal_cost.CalFli1(flights));
//        System.out.println("添加一只大于45kg的,返回-1");
//        person.Add_Package(p4);
//        assertEquals(-1,cal_cost.CalFli1(flights));


    }
}