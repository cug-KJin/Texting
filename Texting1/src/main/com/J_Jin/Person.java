package J_Jin;

import java.util.HashMap;

/*
* person 的属性有舱室，带的包，是否带孩子
**/
public class Person {
    Package[] packages;
    int level;//舱室
    int level2;//会员类型
    int Count = 0;
    boolean Weather_Carry_Kid = false;
    HashMap<Integer,String> Index_Level;
    HashMap<Integer,String> Index_Level2;
    HashMap<Integer,String> Index_kids;
    String Pac_msg = "";
    public  Person(){
        init();
    }
    public Person(Package[] packages,int Count){
        this.packages = packages;
        this.Count = Count;
        init();
    }

    public Person(Package[] packages,int count,int level,boolean weather_Carry_Kid){
        this.packages = packages;
        this.Count = count;
        this.level = level;
        Weather_Carry_Kid = weather_Carry_Kid;
        init();
    }

    public void init(){
        packages = new Package[15];
        Index_Level = new HashMap<>();
        Index_Level.put(0,"头等舱");
        Index_Level.put(1,"公务舱");
        Index_Level.put(2,"明珠经济舱");
        Index_Level.put(3,"经济舱");
        Index_Level2 = new HashMap<>();
        Index_Level2.put(0,"南航明珠金卡会员、天合联盟会员超级精英");
        Index_Level2.put(1,"南航明珠银卡会员、天合联盟精英");
        Index_Level2.put(2,"留学生、劳民、海员");
        Index_Level2.put(3,"普通会员");
        Index_kids = new HashMap<>();
        Index_kids.put(0,"没带");
        Index_kids.put(1,"带了");
    }

    public String Get_Index_Level(int key){
        return Index_Level.get(key);
    }
    public String Get_Index_Level2(int key){
        return Index_Level2.get(key);
    }
    public String Get_Index_kids(int key){
        return Index_kids.get(key);
    }

    public boolean isWeather_Carry_Kid() {
        return Weather_Carry_Kid;
    }

    public int getLevel() {
        return level;
    }

    public Package[] getPackages() {
        return packages;
    }

    public int getCount() {
        return Count;
    }

    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPackages(Package[] packages,int Count) {
        this.packages = packages;
        this.Count = Count;
    }

    public void setWeather_Carry_Kid(boolean weather_Carry_Kid) {
        Weather_Carry_Kid = weather_Carry_Kid;
    }

    public void Add_Package(Package pa){
        packages[Count++]= pa;
    }

    public String Get_Pac_msg(){
        for(int i = 0;i<Count;i++){
            String str1 = "重量："+packages[i].getPak_Weight();
            String str2 = "    长："+packages[i].getPak_height();
            String str3 = "    宽："+packages[i].getPak_width();
            String str4 = "    高："+packages[i].pak_depth;
            String str5 = "    总长度：";
            if(packages[i].getPak_lengths()==1)str5 += "<=158";
            else if(packages[i].getPak_lengths()==2)str5 += "158-300";
            else if(packages[i].getPak_lengths()==3)str5 += "<=115";
            else str5 +=packages[i].getPak_height()+packages[i].getPak_width()+packages[i].getPak_depth() ;
            Pac_msg += str1+str2+str3+str4+str5+"<br/>";
        }
        return Pac_msg;
    }

    public int GetPackageCount(){
        return Count;
    }

    public static void main(String args[]){
        Package p1 =new Package(32,0,0,0,1);
        Package p2 =new Package(31,0,0,0,2);
        Package p3 =new Package(40,0,0,0,2);
        Package p4 =new Package(40,0,0,0,1);
        Package p5 =new Package(9,0,0,0,1);
        Package p6 =new Package(31,0,0,0,2);
        Person person = new Person();
        person.Add_Package(p1);
        person.Add_Package(p2);
        person.Add_Package(p3);
        person.Add_Package(p4);
        person.Add_Package(p5);
        person.Add_Package(p6);
        person.setLevel(0);
        person.setLevel2(2);
        boolean b = (1==1?true:false);
        person.setWeather_Carry_Kid(b);
        System.out.println(person.packages[1].getPak_Weight());
        System.out.println(person.Get_Pac_msg());
    }
}
