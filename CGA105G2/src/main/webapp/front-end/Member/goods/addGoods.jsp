<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.goods.model.Goods.pojo.*" %>
<%@page import="com.goods.model.service.*" %>

<%
    Goods goods = (Goods) request.getAttribute("goods");
%>

<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>🗃️管理</title>

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
            width: 580px;
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
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<%--	<table id="table-1">--%>
<%--		<tr>--%>
<%--			<td>--%>
<%--				<h3>商品資料新增 - addGoods.jsp</h3>--%>
<%--			</td>--%>
<%--			<td>--%>
<%--				<h4>--%>
<%--					<a href="select_page.jsp"><img src="images/tomcat.png"--%>
<%--						width="100" height="100" border="0">回首頁</a>--%>
<%--				</h4>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</table>--%>
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 mb-6 mt-5">
            <div class="table-responsive" style="overflow: hidden !important;">
                <h1 class="text-center mt-5">🔆新增商品</h1>

                <%-- 錯誤表列 --%>
                <c:if test="${not empty errorMsgs}">
                <font style="color: red">請修正以下錯誤:</font>
                <ul>
                    <c:forEach var="message" items="${errorMsgs}">
                        <li style="color: red">${message}</li>
                    </c:forEach>
                </ul>
                </c:if>

                <FORM METHOD="post" ACTION="goods.do" name="form1" enctype="multipart/form-data">
                    <table>
                        <jsp:useBean id="goodsSvc" scope="page"
                                     class="com.goods.model.service.GoodsService"/>
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
                            <td><input type="file" name="goodsImg"><br></td>
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
                            <td><input type="TEXT" name="goodsStatus" size="45"
                                       value="<%=(goods == null) ? "...." : goods.getGoodsStatus()%>"/></td>
                        </tr>

                        <tr>
                            <td>商品說明:</td>
                            <td><input type="TEXT" name="goodsText" size="45"
                                       value="<%=(goods == null) ? "...." : goods.getGoodsText()%>"/></td>
                        </tr>
                    </table>
                    <br> <input type="hidden" name="action" value="insert"> <input
                        type="submit" value="送出新增">
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
    $("a:contains(🔆新增商品)").closest("a").addClass("active disabled bg-white topage");
</script>
</body>
</html>