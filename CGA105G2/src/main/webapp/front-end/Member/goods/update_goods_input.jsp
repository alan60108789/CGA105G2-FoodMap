<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.goods.model.Goods.pojo.*" %>
<%@page import="com.goods.model.service.*" %>

<%
    Goods goods = (Goods) request.getAttribute("goods"); //EmpServlet.java (Concroller) 存入req的goods物件 (包括幫忙取出的goods, 也包括輸入資料錯誤時的goods物件)
%>

<!-- line 100 -->
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>店家首頁</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0px solid #CCCCFF;
        }

        th, td {
            padding: 1px;
        }
    </style>

</head>
<body bgcolor='white'>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 mt-10 mb-10">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆總覽商品</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-info">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-info">Export</button>
                    </div>
                </div>
            </div>
            <div class="table-responsive" style="overflow-y:scroll;height:400px">
                <table class="table table-striped " id="table-1">
                    <tr>
                        <td>
                            <h3>商品資料修改 - update_goods_input.jsp</h3>
                            <h4>
                                <a href="select_page.jsp"><img src="images/back1.gif"
                                                               width="100" height="32" border="0">回首頁</a>
                            </h4>
                        </td>
                    </tr>
                </table>

                <h3>資料修改:</h3>

                <%-- 錯誤表列 --%>
                <c:if test="${not empty errorMsgs}">
                    <font style="color: red">請修正以下錯誤:</font>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li style="color: red">${message}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <FORM METHOD="post" ACTION="goods.do" name="form1"
                      enctype="multipart/form-data">
                    <table>
                        <jsp:useBean id="goodsSvc" scope="page"
                                     class="com.goods.model.service.GoodsService"/>

                        <tr>
                            <td>商品編號:</td>
                            <td><%=goods.getGoodsId()%>
                            </td>
                        </tr>

                        <tr>
                            <td>店家編號:</td>
                            <td><select size="1" name="storeId">
                                <c:forEach var="goods" items="${goodsSvc.all}">
                                <option value="${goods.storeId}">${goods.storeId}
                                    </c:forEach>
                            </select></td>
                        </tr>

                        <tr>
                            <td>上傳圖片:</td>
                            <td><input type="file" name="goodsImg"
                                       value="<%=goods.getGoodsImg()%>"/><br></td>
                        </tr>

                        <tr>
                            <td>商品名稱:</td>
                            <td><input type="TEXT" name="goodsName" size="45"
                                       value="<%=(goods == null) ? "YYY" : goods.getGoodsName()%>"/></td>
                        </tr>

                        <tr>
                            <td>商品價格:</td>
                            <td><input type="TEXT" name="goodsPrice" size="45"
                                       value="<%=(goods == null) ? "000" : goods.getGoodsPrice()%>"/></td>
                        </tr>

                        <tr>
                            <td>商品狀態:</td>
                            <td><select size="1" name="goodsStatus">

                                <c:choose>
                                    <c:when test="${goodsVO.goodsStatus == '0'}">
                                        <option value="0" selected>下架</option>
                                        <option value="1">上架</option>
                                        <option value="2">審核中</option>
                                    </c:when>
                                    <c:when test="${goodsVO.goodsStatus == '1'}">
                                        <option value="0">下架</option>
                                        <option value="1" selected>上架</option>
                                        <option value="2">審核中</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">下架</option>
                                        <option value="1">上架</option>
                                        <option value="2" selected>審核中</option>
                                    </c:otherwise>
                                </c:choose>
                            </select></td>

                        </tr>

                        <tr>
                            <td>商品說明:</td>
                            <td><input type="TEXT" name="goodsText" size="45"
                                       value="<%=(goods == null) ? "...." : goods.getGoodsText()%>"/></td>
                        </tr>


                    </table>
                    <br> <input type="hidden" name="action" value="update"> <input
                        type="hidden" name="goodsId" value="<%=goods.getGoodsId()%>">
                    <input type="submit" value="送出修改">
                </FORM>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻商城管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("a:contains(🔆總覽商品)").closest("a").addClass("active disabled bg-white topage");
</script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
        src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
    .xdsoft_datetimepicker .xdsoft_datepicker {
        width: 300px; /* width:  300px; */
    }

    .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        height: 151px; /* height:  151px; */
    }
</style>

<script>
    $.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker: false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=goods.getGoodsRtime()%>
    });

</script>
</body>
</html>