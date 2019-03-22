<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>行李托运计算</title>
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
    <style>
        footer {
            position: fixed;
            height: 50px;
            bottom: 0;
            width: 100%;
        }
        .pt-3-half {
            padding-top: 1.4rem;
        }
    </style>
</head>
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


<body background="img/BG.png" style="width: 100%">
<form action="getdata.jsp" method="post">
<div>
    <div class="logo" style="height: 80px;width: 100%;text-align: center;border: 25px">
        <img src="img/logo.jpg" style="width: 500px;height: 80px;margin: 0px auto;">
        <br>
        <a href="https://www.csair.com/cn/tourguide/luggage_service/carryon_luggage/rules/index.shtml">办理业务前请仔细阅读相关申明！</a>
    </div>
    <div class="container" style="margin-top: 40px">
        <div class="row">
            <div class="col">
            </div>
            <div class="col-5">
                <h6>航班类型：</h6>
                <select class="form-control selectpicker" id="select1" name="select1" data-style="btn-danger" title="请选择航班类型(默认为不涉及美国航线)" style="font-size: 10px">
                    <option value="0" data-content="<span class='label label-success'>国内航班</span>">国内航班</option>
                    <option value="1" data-content="<span class='label label-success'>涉及美国航线的国际航班</span>">涉及美国航线的国际航班</option>
                    <option value="2" data-content="<span class='label label-success'>不涉及美国航线的国际航班</span>">不涉及美国航线的国际航班</option>
                </select>
                <br>
                <br>
                <h6>航线：</h6>
                <select class="form-control selectpicker" id="select2" name="select2" data-style="btn-success" title="请选择航线">
                    <option value="0" data-content="<span class='label label-success'>国内航线（港澳台构成国际航班）</span>">国内航线（港澳台构成国际航班）</option>
                    <option value="1" data-content="<span class='label label-success'>日本、美洲、澳新、迪拜(不涉及兰州/乌鲁木齐)等</span>">日本、美洲、澳新、迪拜(不涉及兰州/乌鲁木齐)、俄罗斯、新加坡始发至中国</option>
                    <option value="2" data-content="<span class='label label-success'>兰州/乌鲁木齐与迪拜之间的航线</span>">兰州/乌鲁木齐与迪拜之间的航线</option>
                    <option value="3" data-content="<span class='label label-success'>中西亚的航线</span>">中西亚的航线</option>
                    <option value="4" data-content="<span class='label label-success'>内罗毕不含毛利求斯的航线</span>">内罗毕不含毛利求斯的航线</option>
                    <option value="5" data-content="<span class='label label-success'>其他航线（包括国内港澳台航线）</span>">其他航线（包括国内港澳台航线）</option>
                    <option value="6" data-content="<span class='label label-success'>韩国始发与中国之间的航线</span>">韩国始发与中国之间的航线</option>
                </select>
                <br>
                <br>
                <h6>舱室类型：</h6>
                <select class="form-control selectpicker" id="select3" name="select3" data-style="btn-info" title="请选择舱室">
                    <option value="0" data-content="<span class='label label-success'>头等舱</span>">头等舱</option>
                    <option value="1" data-content="<span class='label label-success'>公务舱</span>">公务舱</option>
                    <option value="2" data-content="<span class='label label-success'>高端经济舱</span>">高端经济舱</option>
                    <option value="3" data-content="<span class='label label-success'>经济舱</span>">经济舱</option>
                </select>
                <br>
                <br>
                <h6>会员类型:</h6>
                <select class="form-control selectpicker" id="select4" name="select4" data-style="btn-warning" title="请选择会员类型（默认为普通用户）">
                    <option value="0" data-content="<span class='label label-success'>南航明珠金卡会员、天合联盟会员超级精英</span>">南航明珠金卡会员、天合联盟会员超级精英</option>
                    <option value="1" data-content="<span class='label label-success'>南航明珠银卡会员、天合联盟精英</span>">南航明珠银卡会员、天合联盟精英</option>
                    <option value="2" data-content="<span class='label label-success'>留学生、劳民、海员</span>">留学生、劳民、海员</option>
                    <option value="3" data-content="<span class='label label-success'>普通会员</span>">普通用户</option>
                </select>
                <br>
                <br>
                <h6>是否带小孩:</h6>
                <div class="checkbox checkbox-success">
                    <input type="checkbox" value="1" name="chackbox1">Yes
                </div>
                <br>
                <br>
                <h6>当日普通经济舱票价：</h6>
                <!-- Medium input -->
                <div class="md-form">
                    <input type="text" name="inputMDEx" id="inputMDEx" class="form-control">
                </div>

            </div>
            <div class="col-6">
                <!-- Editable table -->
                <h6>添加行李包信息:</h6>
                <div class="card">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">行李包信息</h3>
                    <div class="card-body">
                        <div id="table" class="table-editable">
      <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i class="fas fa-plus fa-2x"
                                                                                         aria-hidden="true"></i></a></span>
                            <table id="Pack_table" class="table table-bordered table-responsive-md table-striped text-center">
                                <tr>
                                    <th class="text-center">重量</th>
                                    <th class="text-center">长</th>
                                    <th class="text-center">宽</th>
                                    <th class="text-center">高</th>
                                    <th class="text-center">总长度</th>
                                    <th class="text-center">排序</th>
                                    <th class="text-center">删除</th>
                                </tr>
                                <tr class="hide">
                                    <td class="pt-3-half" contenteditable="true"><input type="text" style="width: 30px;font-size: 12px;" name="channel_" value=""></td>
                                    <td class="pt-3-half" contenteditable="true"><input type="text" style="width: 30px;font-size: 12px;" name="channel_" value=""></td>
                                    <td class="pt-3-half" contenteditable="true"><input type="text" style="width: 30px;font-size: 12px;" name="channel_" value=""></td>
                                    <td class="pt-3-half" contenteditable="true"><input type="text" style="width: 30px;font-size: 12px;" name="channel_" value=""></td>
                                    <td class="pt-3-half" contenteditable="true">
                                        <select class="browser-default custom-select" name="channel_" style="font-size: 12px;width: 90px">
                                            <option selected> </option>
                                            <option value="3" style="font-size: 12px"><=115</option>
                                            <option value="1" style="font-size: 12px"><=158</option>
                                            <option value="2" style="font-size: 12px">159-300</option>
                                        </select>
                                    </td>
                                    <td class="pt-3-half">
                                        <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>
                                        <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down"
                                                                                                     aria-hidden="true"></i></a></span>
                                    </td>
                                    <td>
                                        <span class="table-remove"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0">删除</button></span>
                                    </td>
                                </tr>
                            </table>
                            <button type="submit" class="btn btn-info" style="float: right">计算</button>
                        </div>
                    </div>
                </div>
                <!-- Editable table -->
            </div>
            <div class="col">
            </div>
        </div>
    </div>

</div>

<!-- Footer -->
<footer class="page-footer font-small blue">
    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 蒋劲实习一界面:
        <a href="https://www.csair.com/cn/tourguide/luggage_service/carryon_luggage/rules/index.shtml"> 航班行李托运</a>
    </div>
</footer>
<!-- Footer -->
</form>
</body>


<script>
    // Material Select Initialization
    $(document).ready(function() {
        $('.mdb-select').materialSelect();
    });

    var $TABLE = $('#table');
    var $BTN = $('#export-btn');
    var $EXPORT = $('#export');

    $('.table-add').click(function () {
        var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
        $TABLE.find('table').append($clone);
    });

    $('.table-remove').click(function () {
        $(this).parents('tr').detach();
    });

    $('.table-up').click(function () {
        var $row = $(this).parents('tr');
        if ($row.index() === 1) return; // Don't go above the header
        $row.prev().before($row.get(0));
    });

    $('.table-down').click(function () {
        var $row = $(this).parents('tr');
        $row.next().after($row.get(0));
    });

    // A few jQuery helpers for exporting only
    jQuery.fn.pop = [].pop;
    jQuery.fn.shift = [].shift;

    $BTN.click(function () {
        var $rows = $TABLE.find('tr:not(:hidden)');
        var headers = [];
        var data = [];

// Get the headers (add special header logic here)
        $($rows.shift()).find('th:not(:empty)').each(function () {
            headers.push($(this).text().toLowerCase());
        });

// Turn all existing rows into a loopable array
        $rows.each(function () {
            var $td = $(this).find('td');
            var h = {};

// Use the headers from earlier to name our hash keys
            headers.forEach(function (header, i) {
                h[header] = $td.eq(i).text();
            });

            data.push(h);
        });

// Output the result
        $EXPORT.text(JSON.stringify(data));
    });
</script>
</html>

