<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.goods.model.Goods.pojo.*" %>
<%@ page import="com.goods.model.service.*" %>

<%
    GoodsService goodsSvc = new GoodsService();
    List<Goods> list = goodsSvc.getAll();
    pageContext.setAttribute("list", list);
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
            width: auto;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid pink;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }

    </style>

</head>
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<%--	<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->--%>
<%--	<table id="table-1">--%>
<%--		<tr>--%>
<%--			<td>--%>
<%--				<h3>商城</h3>--%>
<%--				<h4>--%>
<%--					<a href="/index.jsp"><img src="/CGA105G2/assets/images/Logo.PNG"--%>
<%--						width="100" height="32" border="0">回首頁</a>--%>
<%--				</h4>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</table>--%>
<div class="row">
    <!-- nav start -->
    <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
    <!-- nav end -->
    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 mb-6 mt-5">
        <div class="table-responsive" style="overflow: hidden !important;">
            <h1 class="text-center mt-5">🔆總覽商品</h1>

            <table>
                <tr>
                    <th>商品編號</th>
                    <th>店家編號</th>
                    <th>商品照片</th>
                    <th>商品名稱</th>
                    <th>商品狀態</th>
                    <th>商品價格</th>
                    <th>商品說明</th>
                    <th>上架日期</th>
                    <th>修改日期</th>
                </tr>
                <%@ include file="/front-end/Member/goods/page1.jsp" %>
                <c:forEach var="goodsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

                    <tr>
                        <td>${goodsVO.goodsId}</td>
                        <td>${goodsVO.storeId}</td>
                        <td id=img><img
                                src="${pageContext.request.contextPath}/front-end/Member/goods/goods.do?action=getGoodsImg&goodsId=${goodsVO.goodsId}"
                                height: 100px;
                                width=200px height=200px></td>
                        <td>${goodsVO.goodsName}</td>
                        <td><c:if test="${goodsVO.goodsStatus == 0}">下架</c:if>
                            <c:if test="${goodsVO.goodsStatus == 1}">上架</c:if>
                            <c:if test="${goodsVO.goodsStatus == 2}">審核中</c:if></td>
                        <td>${goodsVO.goodsPrice}</td>
                        <td>${goodsVO.goodsText}</td>
                        <td>${goodsVO.goodsTime}</td>
                        <td>${goodsVO.goodsRtime}</td>

                        <td>
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/front-end/Member/goods/goods.do"
                                  style="margin-bottom: 0px;">
                                <input type="submit" value="修改"> <input type="hidden"
                                                                          name="goodsId" value="${goodsVO.goodsId}">
                                <input
                                        type="hidden" name="action" value="getOne_For_Update">
                            </FORM>
                        </td>
                        <td>
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/front-end/Member/goods/goods.do"
                                  style="margin-bottom: 0px;">
                                <input type="submit" value="刪除"> <input type="hidden"
                                                                          name="goodsId" value="${goodsVO.goodsId}">
                                <input
                                        type="hidden" name="action" value="delete">
                            </FORM>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%@ include file="/front-end/Member/goods/page2.jsp" %>
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