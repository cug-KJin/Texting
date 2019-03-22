package J_Jin;
/*
*Parkage的属性主要有重量、长宽高、总长度
*/
public class Package {
    int pak_Weight = 0;
    int pak_lengths = 0;
    int pak_height = 0;
    int pak_width = 0;
    int pak_depth = 0;
    boolean ischacked = false;

    public Package(){
    }

    public Package(int pak_Weight,int pak_height,int pak_width,int pak_depth,int pak_lengths){
        this.pak_depth = pak_depth;
        this.pak_height = pak_height;
        this.pak_lengths = pak_lengths;
        this.pak_Weight = pak_Weight;
        this.pak_width = pak_width;
    }

    public void setPackage(Package pa){
        this.pak_depth = pa.getPak_depth();
        this.pak_height = pa.getPak_height();
        this.pak_lengths = pa.getPak_lengths();
        this.pak_Weight = pa.getPak_Weight();
        this.pak_width = pa.getPak_width();
    }

    public int getPak_depth() {
        return pak_depth;
    }

    public int getPak_height() {
        return pak_height;
    }

    public int getPak_lengths() {
        if(pak_lengths == 0){
            if(pak_depth== 0||pak_width== 0||pak_height == 0)return -2;
            else{
                if(pak_depth+pak_width+pak_height<=115)return 1;
                else if(pak_depth+pak_width+pak_height>115&&pak_depth+pak_width+pak_height<=158)return 2;
                else if(pak_depth+pak_width+pak_height>158&&pak_depth+pak_width+pak_height<=300)return 3;
                else return -2;
            }
        }
        else return pak_lengths;
    }

    public int getPak_Weight() {
        return pak_Weight;
    }

    public int getPak_width() {
        return pak_width;
    }

    public void setPak_depth(int pak_depth) {
        this.pak_depth = pak_depth;
    }

    public void setPak_height(int pak_height) {
        this.pak_height = pak_height;
    }

    public void setPak_lengths(int pak_lengths) {
        this.pak_lengths = pak_lengths;
    }

    public void setPak_Weight(int pak_Weight) {
        this.pak_Weight = pak_Weight;
    }

    public void setPak_width(int pak_width) {
        this.pak_width = pak_width;
    }

    public boolean isIschacked() {
        return ischacked;
    }

    public void setIschacked(boolean ischacked) {
        this.ischacked = ischacked;
    }

    public static void main(String args[]){
        Package p1 =new Package(32,0,0,0,1);
        System.out.println(p1.getPak_Weight());
    }
}
