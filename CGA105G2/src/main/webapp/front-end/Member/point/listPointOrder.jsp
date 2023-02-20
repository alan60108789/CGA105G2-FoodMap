<%@page import="org.hibernate.internal.build.AllowSysOut" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@page import="com.member.model.Member.pojo.Member" %>
<%@page import="com.member.model.service.MemberService" %>
<%@ page import="com.point.model.PointOrder.pojo.PointOrder" %>
<%@ page import="com.point.model.PointGoods.pojo.PointGoods" %>
<%@ page import="com.point.model.service.*" %>
<jsp:useBean id="pointgoodsSvc" scope="page" class="com.point.model.service.PointGoodsService"/>
<%
    Integer memId = (Integer) request.getSession().getAttribute("memId");
    PointOrderService pointorderSvc = new PointOrderService();
    List<PointOrder> list = pointorderSvc.getMemOrder(memId);
    pageContext.setAttribute("list", list);

%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>💰point</title>
    <style>
        /* 商品名稱 */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* 兌換點數 */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* 商品圖片 */
        .card-img-top {
            height: 330px;
            width: 230px;
        }

        .col mb-5::after {
            position: absolute;
            content: "";
            top: 50%;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #444444;
            transform: translateY(-50%);
        }

        .bg-warning {
            background-color: pink !important;
        }
    </style>
</head>

<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="py-5">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">🔆訂單查詢</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
                        </div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>編號</th>
                            <th>商品名稱</th>
                            <th>點數</th>
                            <th>備註</th>
                            <th>新增日期</th>
                            <th>出貨日期</th>
                        </tr>
                        </thead>
                        <tbody class="code_tbody">
                        <c:forEach var="PointOrder" items="${list}">
                            <tr>
                                <td>${PointOrder.poId}</td>
                                <td><c:forEach var="pointgoods" items="${pointgoodsSvc.all}">
                                    <c:if test="${PointOrder.pdId==pointgoods.pdId}">
                                        ${pointgoods.pdName}
                                    </c:if>
                                </c:forEach>
                                </td>
                                <td>${PointOrder.poPrice}</td>
                                <td>${PointOrder.poText}</td>
                                <td>${PointOrder.poTime}</td>
                                <td>${PointOrder.poUtime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <canvas class="my-4 w-100" id="myChart" width="900" height="150"></canvas>
            </section>
            <br>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                    <ul class="pagination">
                    </ul>
                </nav>
            </div>

        </main>
    </div>

</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(💰point)").closest("a").addClass("active disabled topage");
    $("a:contains(💰點數商城)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(🔆訂單查詢)").closest("a").addClass("active disabled bg-white topage");
</script>


</body>

</html>