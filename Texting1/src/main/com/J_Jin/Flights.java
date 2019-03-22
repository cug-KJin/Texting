package J_Jin;

import javax.imageio.ImageTranscoder;
import java.util.HashMap;

/*
* Flights 属性有航班号、人、当日票价
* */
public class Flights {
    public Person person;
    /*
    * 航班号规定
    *
    * 0，国内航线
    * 1，日本、美洲、澳新、迪拜(不涉及兰州/乌鲁木齐)、俄罗斯、新加坡始发至中国
    * 2，兰州/乌鲁木齐与迪拜之间的航线
    * 3，中西亚的航线
    * 4、内罗毕不含毛利求斯的航线
    * 5、其他航线（包括国内港澳台航线）
    * 6、韩国始发与中国之间的航线
    * */
    int Fli_Num;//航线
    int Fli_l;//航班类型
    int CurrentCost;//当日票价
    HashMap<Integer,String> Index_Fli_Num;
    HashMap<Integer,String> Index_Fli_l;
    public Flights(){
        init();
    }
    public Flights(Person person){
        this.person = person;
        init();
    }
    public void init(){
        Index_Fli_Num = new HashMap<Integer,String>();
        Index_Fli_Num.put(0,"国内航线");
        Index_Fli_Num.put(1,"日本、美洲、澳新、迪拜(不涉及兰州/乌鲁木齐)、俄罗斯、新加坡始发至中国");
        Index_Fli_Num.put(2,"兰州/乌鲁木齐与迪拜之间的航线");
        Index_Fli_Num.put(3,"中西亚的航线");
        Index_Fli_Num.put(4,"内罗毕不含毛利求斯的航线");
        Index_Fli_Num.put(5,"其他航线（包括国内港澳台航线）");
        Index_Fli_Num.put(6,"韩国始发与中国之间的航线");
        Index_Fli_l = new HashMap<>();
        Index_Fli_l.put(0,"国内航线");
        Index_Fli_l.put(1,"涉美航线");
        Index_Fli_l.put(2,"不涉美航线");
    }

    public int getFli_Num() {
        return Fli_Num;
    }

    public Person getPerson() {
        return person;
    }

    public int getCurrentCost() {
        return CurrentCost;
    }

    public int getFli_l() {
        return Fli_l;
    }

    public void setFli_l(int fli_l) {
        Fli_l = fli_l;
    }

    public void setFli_Num(int fli_Num) {
        Fli_Num = fli_Num;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCurrentCost(int currentCost) {
        CurrentCost = currentCost;
    }

    public String Get_Index_Fli_Num(int key){
        return Index_Fli_Num.get(key);
    }
    public String Get_Index_Fli_l(int key){
        return Index_Fli_l.get(key);
    }
}
