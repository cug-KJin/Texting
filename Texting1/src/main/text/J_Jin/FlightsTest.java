package J_Jin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightsTest {

    @Test
    void get_Index_Fli_Num() {

        Flights f = new Flights();
        assertEquals("国内航线",f.Get_Index_Fli_Num(0));
    }

    @Test
    void get_Index_Fli_l() {
        Flights f = new Flights();
        assertEquals("涉美航线",f.Get_Index_Fli_l(1));
    }
}