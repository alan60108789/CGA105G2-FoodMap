<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.goods.model.Goods.pojo.*" %>
<%@page import="com.goods.model.service.*" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    Goods goods = (Goods) request.getAttribute("goods"); //EmpServlet.java(Concroller), 存入req的goods物件
%>

<html>
<head>
    <title>商品資訊 - listOneGoods.jsp</title>

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
            width: auto;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid #CCCCFF;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body bgcolor='white'>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<%--<h4>此頁暫練習採用 Script 的寫法取值:</h4>--%>
<%--<table id="table-1">--%>
<%--	<tr><td>--%>
<%--		 <h3>商品資料 - ListOneGood.jsp</h3>--%>
<%--		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>--%>
<%--	</td></tr>--%>
<%--</table>--%>
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 mb-6 mt-5">
            <div class="table-responsive" style="overflow: hidden !important;">
                <h1 class="text-center mt-5">🔆新增商品</h1>

                <table>
                    <tr>
                        <th>商品編號</th>
                        <th>店家編號</th>
                        <th>商品照片</th>
                        <th>商品名稱</th>
                        <th>商品狀態</th>
                        <th>商品價格</th>
                        <th>商品說明</th>

                    </tr>
                    <tr>
                        <td><%=goods.getGoodsId()%>
                        </td>
                        <td><%=goods.getStoreId()%>
                        </td>
                        <td><img
                                src="${pageContext.request.contextPath}/front-end/Member/goods/goods.do?action=getGoodsImg&goodsId=${goods.goodsId}"
                                height: 100px;
                                width=200px height=200px></td>
                        <td>${goods.goodsName}</td>
                        <td><c:if test="${goods.goodsStatus == 0}">下架</c:if>
                            <c:if test="${goods.goodsStatus == 1}">上架</c:if>
                            <c:if test="${goods.goodsStatus == 2}">審核中</c:if></td>
                        <td><%=goods.getGoodsPrice()%>
                        </td>
                        <td><%=goods.getGoodsText()%>
                        </td>

                    </tr>
                </table>
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

</body>
</html>