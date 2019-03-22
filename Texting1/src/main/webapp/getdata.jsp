<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/16
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="J_Jin.Flights,J_Jin.Package,J_Jin.Cal_Cost,J_Jin.Person,java.io.*,java.util.*"%>
<%!
    private Person person = new Person();
    private Flights flights = new Flights();
    public Cal_Cost cal_cost;
    private String alter_meg = null;
    public String str6 = null;
%>
<html>
<head>
    <title>getdata</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="short icon"  href="img/ico.ico">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
    <!-- bootstrap-select -->
    <link href="css/bootstrap-select.min.css" rel="stylesheet" />
    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.js"></script>
    <!-- bootstrap-select -->
    <script src="js/bootstrap-select.min.js"></script>
    <script src="js/defaults-zh_CN.min.js"></script>
    <script src="js/bootstrap-checkbox.min.js"></script>
</head>
<body background="img/BG.png" style="width: 100%">
<div class="container">
    <div class="row">
        <div class="col">
        </div>
        <div class="col-10" style="margin-top: 150px">
            <%
                //航班类型
                String str1 = request.getParameter("select1");
                if(str1.equals(""))str1="0";
                flights.setFli_l(Integer.parseInt(str1));

                //航线
                String str2 = request.getParameter("select2");
                if(str2.equals(""))str1="0";
                flights.setFli_Num(Integer.parseInt(str2));

                //舱室类型
                String str3 = request.getParameter("select3");
                if(str3.equals(""))str1="3";
                person.setLevel(Integer.parseInt(str3));

                //会员类型
                String str4 = request.getParameter("select4");
                if(str4.equals(""))str4="3";
                person.setLevel2(Integer.parseInt(str4));

                //是否带小孩
                String str5 = request.getParameter("chackbox1");
                if(str4.equals(""))str4="0";
                boolean b = (Integer.parseInt(str5)==1?true:false);
                person.setWeather_Carry_Kid(b);

                //当日票价
                str6 = request.getParameter("inputMDEx");
                if(str6.equals(""))str6="0";
                flights.setCurrentCost(Integer.parseInt(str6));

                //行李箱
                String[] channels = request.getParameterValues("channel_");
                for(int j = 0;j<channels.length;j++){
                    if(channels[j].equals(""))channels[j] = "0";
                }
                for (int i = 0;i<channels.length/5;i++){
                    Package pac = new Package(Integer.parseInt(channels[i*5]),Integer.parseInt(channels[i*5+1]),Integer.parseInt(channels[i*5+2]),Integer.parseInt(channels[i*5+3]),Integer.parseInt(channels[i*5+4]));
                    person.Add_Package(pac);
                }
                flights.setPerson(person);
                cal_cost = new Cal_Cost(flights);
                int cost = cal_cost.Distribution(flights);

            %>
            <table>
                <tbody style="font-family: 微软雅黑;font-size: 16px">
                <tr>
                    <td style="width: 50px">
                        航班类型
                    </td>
                    <td style="width: 200px">
                        <%

                            out.print(flights.Get_Index_Fli_l(Integer.parseInt(str1)));
                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        航线
                    </td>
                    <td>
                        <%
                            out.print(flights.Get_Index_Fli_Num(Integer.parseInt(str2)));

                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        舱室类型
                    </td>
                    <td>
                        <%
                            out.print(person.Get_Index_Level(Integer.parseInt(str3)));
                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        会员类型
                    </td>
                    <td>
                        <%
                            out.print(person.Get_Index_Level2(Integer.parseInt(str4)));

                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        是否带孩
                    </td>
                    <td>
                        <%
                            out.print(person.Get_Index_kids(Integer.parseInt(str5)));

                        %>
                    </td>
                </tr>
                <tr>
                    <td>
                        当日经济舱普通票价
                    </td>
                    <td>
                        <%

                            out.print(flights.getCurrentCost());
                        %>
                    </td>

                </tr>
                <tr>
                    <td>
                        行李信息
                    </td>
                    <td>
                        <%
                            out.print(flights.person.Get_Pac_msg());
                        %>
                    </td>

                </tr>
                </tbody>

            </table>
            <h2>
                <%
                    if(cost == -1)out.print("行李箱已超出最大载重量，请仔细阅读<a href=\"https://www.csair.com/cn/tourguide/luggage_service/carryon_luggage/rules/index.shtml\">行李托运规则！</a>，或联系相关工作人员！");
                    else if(cost == -2)out.print("行李箱尺寸不明确，请仔细阅读<a href=\"https://www.csair.com/cn/tourguide/luggage_service/carryon_luggage/rules/index.shtml\">行李托运规则！</a>，或联系相关工作人员！");
                    else out.print("计算结果为:"+cost);
                %>
            </h2>
        </div>
        <div class="col">
        </div>
    </div>
</div>

</body>
</html>
