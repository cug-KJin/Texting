package J_Jin;

import javax.rmi.ssl.SslRMIClientSocketFactory;

/*
* 费用计算就一个属性就是Flights
* */
public class Cal_Cost {
    Flights flights;
    public Cal_Cost(Flights flights){
        this.flights = flights;
    }
    public int Distribution(Flights flights){
        int getcost = 0;
        switch (flights.getFli_Num()){
            case 1:{
                getcost = CalFli1(flights);
                break;
            }
            case 2:{
                getcost = CalFli2(flights);
                break;
            }
            case 3:{
                getcost = CalFli3(flights);
                break;
            }
            case 4:{
                getcost = CalFli4(flights);
                break;
            }
            case 5:{
                getcost = CalFli5(flights);
                break;
            }
            case 0:{
                getcost = CalFli0(flights);
                break;
            }
        }
        return getcost;
    }
    /*
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：40kg，公务舱：30kg，经济舱：20kg，婴儿：10kg
     *5、逾重行李费率为每公斤按逾重行李票填开当日所适用的经济舱普通票价的1.5%计算，以人民币为单位，位数四舍五入
     *6、该航线单件行李上限50千克/件，每件尺寸限制为100*60*40
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     */
    public int CalFli0(Flights flights){
        int cost = 0;
        int Allweight = 0;

        for(int i = 0;i<flights.person.GetPackageCount();i++){
            if(flights.person.packages[i].getPak_Weight()>50)return -1;
            Allweight += flights.person.packages[i].getPak_Weight();
        }
        //会员类型
        switch (flights.person.getLevel2()){
            case 0: {
                Allweight -= 20;
                break;
            }
            case 1: {
                Allweight -= 10;
                break;
            }
            case 2: {
                break;
            }
            case 3: {


                break;
            }
        }
        //舱室类型
        switch (flights.person.getLevel())
        {
            case 0: {
                Allweight -= 40;
                break;
            }
            case 1: {
                Allweight -= 30;
                break;
            }
            case 2: {
                Allweight -= 20;
                break;
            }
            case 3: {
                Allweight -= 20;
                break;
            }
        }
        //带小孩
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    Allweight -= flights.person.packages[i].getPak_Weight();
                    break;
                }
            }
        }

        double Allcost = Allweight*0.015*flights.getCurrentCost();
        Allcost = Math.round(Allcost);
        cost = (int)Allcost;
        return cost;
    }
    /*区域一（不包括兰州和乌鲁木齐到迪拜）
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：2*32kg/件，高端经济舱：2*23kg/件，经济舱：2*23kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli1(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=2;
                break;
            }
            case 2:{
                Freepackagecount+=2;
                break;
            }
            case 3:{
                Freepackagecount+=2;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=1000;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*2000;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                if(flights.person.getLevel()==0||flights.person.getLevel()==1){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)
                                return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(PacWeight>32&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                //头等舱、公务舱
                if(flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
        }


        return cost;
    }
    /*涉及兰州/布鲁木齐与迪拜之间的航线
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：2*32kg/件，高端经济舱：1*32kg/件，经济舱：1*23kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli2(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=2;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                Freepackagecount+=1;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=1000;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*2000;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                if(flights.person.getLevel()==0||flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)
                                return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(PacWeight>32&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                //头等舱、公务舱
                if(flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
        }


        return cost;
    }
    /*区域二：涉及中西亚的航线
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：2*32kg/件，高端经济舱：1*32kg/件，经济舱：1*32kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli3(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=2;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                Freepackagecount+=1;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=450;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*1300;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                int BestFreePac = 32;
                for(int i = 0;i<flights.person.GetPackageCount();i++){
                    int PacWeight = flights.person.packages[i].getPak_Weight();
                    int Lengths = flights.person.packages[i].getPak_lengths();
                    if(flights.person.packages[i].isIschacked())continue;
                    else{
                        if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                        if(Lengths==2)cost+=1000;
                        if(Lengths==-2)return -2;
                        if(PacWeight>BestPac)
                            return -1;
                    }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                for(int i = 0;i<flights.person.GetPackageCount();i++){
                    int PacWeight = flights.person.packages[i].getPak_Weight();
                    int Lengths = flights.person.packages[i].getPak_lengths();
                    if(flights.person.packages[i].isIschacked())continue;
                    else{
                        if(Lengths==2)cost+=1000;
                        if(Lengths==-2)return -2;
                        if(PacWeight>BestPac)return -1;
                    }
                }
                break;
            }
        }


        return cost;
    }
    /*涉及内罗毕不含毛利求斯的航线
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：2*32kg/件，高端经济舱：2*23kg/件，经济舱：2*23kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli4(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=2;
                break;
            }
            case 2:{
                Freepackagecount+=2;
                break;
            }
            case 3:{
                Freepackagecount+=2;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=1000;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*2000;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                if(flights.person.getLevel()==0||flights.person.getLevel()==1){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)
                                return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=2000;
                            if(PacWeight>32&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                //头等舱、公务舱
                if(flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=2000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
        }


        return cost;
    }
    /*除日本、美洲、澳新、俄罗斯、迪拜、内罗毕、中西亚
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：3*23kg/件，高端经济舱：2*23kg/件，经济舱：2*23kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli5(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=3;
                break;
            }
            case 2:{
                Freepackagecount+=2;
                break;
            }
            case 3:{
                Freepackagecount+=2;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=450;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*1300;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                if(flights.person.getLevel()==0){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)
                                return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(PacWeight>32&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                //头等舱、公务舱
                if(flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
        }


        return cost;
    }
    /*韩国始发与中国之间的航线
     *1、南航明珠金卡会员、天合联盟会员超级精英：国内、国际或地区航班免费行李额在一般标准上额外增加20公斤（计重制）1件（计件制）
     *2、南航明珠银卡会员、天合联盟精英：国内、国际或地区航班免费行李额在一般标准上额外增加10公斤（计重制）1件（计件制）
     *3、留学生、劳民、海员：国际或地区航班免费行李额在一般标准上额外增加一件
     *4、免费行李：头等舱：3*32kg/件，公务舱：2*32kg/件，高端经济舱：1*23kg/件，经济舱：1*23kg件，婴儿：10kg
     *5、超额行李：是否为涉美航线，涉美航线最大为45kg/件，不涉32kg/件
     *6、每件尺寸三边和<=158cm，超过则需要单独收费，最大不得超过300m
     *7、以上行李的规格为航空公司所限接收的最大值
     *8、构成国际运输的航线段不得超过规定重量，建议旅客将行李进行拆开分装，若无法拆分建议联系南航货运
     * */
    public int CalFli6(Flights flights){
        int cost = 0;
        int Freepackagecount = 0;
        //先判断是否带了孩子
        if(flights.person.isWeather_Carry_Kid() == true)
        {
            for(int i = 0;i<flights.person.GetPackageCount();i++){
                if(flights.person.packages[i].getPak_Weight() <= 10) {
                    flights.person.packages[i].setIschacked(true);
                    Freepackagecount++;
                    break;
                }
            }
        }
        //舱室
        switch (flights.person.getLevel()){
            case 0:{
                Freepackagecount+=3;
                break;
            }
            case 1:{
                Freepackagecount+=2;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                Freepackagecount+=1;
                break;
            }
        }
        //会员
        switch (flights.person.getLevel2()){
            case 0:{
                Freepackagecount+=1;
                break;
            }
            case 1:{
                Freepackagecount+=1;
                break;
            }
            case 2:{
                Freepackagecount+=1;
                break;
            }
            case 3:{
                break;
            }
        }
        if(flights.person.GetPackageCount()>Freepackagecount){
            if(flights.person.GetPackageCount()-Freepackagecount==1)cost+=1000;
            else cost+=(flights.person.GetPackageCount()-Freepackagecount)*2000;
        }
        switch (flights.getFli_l()){
            case 1:{
                int BestPac = 45;
                //头等舱、公务舱
                if(flights.person.getLevel()==0||flights.person.getLevel()==1){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=BestPac)cost+=3000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)
                                return -1;
                        }
                    }
                }
                else{
                        int BestFreePac = 23;
                        for(int i = 0;i<flights.person.GetPackageCount();i++){
                            int PacWeight = flights.person.packages[i].getPak_Weight();
                            int Lengths = flights.person.packages[i].getPak_lengths();
                            if(flights.person.packages[i].isIschacked())continue;
                            else{
                                if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                                if(PacWeight>32&&PacWeight<=BestPac)cost+=3000;
                                if(Lengths==2)cost+=1000;
                                if(Lengths==-2)return -2;
                                if(PacWeight>BestPac)return -1;
                            }
                        }
                }
                break;
            }
            case 2:{
                int BestPac = 32;
                //头等舱、公务舱
                if(flights.person.getLevel()==1||flights.person.getLevel()==2){
                    int BestFreePac = 32;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                else{
                    int BestFreePac = 23;
                    for(int i = 0;i<flights.person.GetPackageCount();i++){
                        int PacWeight = flights.person.packages[i].getPak_Weight();
                        int Lengths = flights.person.packages[i].getPak_lengths();
                        if(flights.person.packages[i].isIschacked())continue;
                        else{
                            if(PacWeight>BestFreePac&&PacWeight<=32)cost+=1000;
                            if(Lengths==2)cost+=1000;
                            if(Lengths==-2)return -2;
                            if(PacWeight>BestPac)return -1;
                        }
                    }
                }
                break;
            }
        }


        return cost;
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
//        person.Add_Package(p4);
//        person.Add_Package(p5);
//        person.Add_Package(p6);
        person.setLevel(0);
        person.setLevel2(0);
        boolean b = (1==1?true:false);
        person.setWeather_Carry_Kid(b);
        Flights flights = new Flights();
        flights.setPerson(person);
        flights.setCurrentCost(2000);
        flights.setFli_l(1);
        flights.setFli_Num(1);
        Cal_Cost cal_cost = new Cal_Cost(flights);
        int a = cal_cost.Distribution(flights);
        System.out.println(cal_cost.flights.person.GetPackageCount());
        System.out.println(a);
    }
}
