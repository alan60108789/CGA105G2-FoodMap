<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point.model.PointOrder.pojo.PointOrder"%>
<%@ page import="com.point.model.service.PointOrderService"%>
<%
	PointOrderService pointorderSvc = new PointOrderService();
	List<PointOrder> list = pointorderSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>π°point</title>
    <style>
        /* εεεη¨± */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* εζι»ζΈ */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* εεεη */
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
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin02.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="py-5">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">πθ¨ε?ηΈ½θ¦½</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2"></div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>η·¨θ</th>
                            <th>ζε‘η·¨θ</th>
                            <th>εεη·¨θ</th>
                            <th>ι»ζΈ</th>
                            <th>εθ¨»</th>
                            <th>ζ°ε’ζ₯ζ</th>
                            <th>εΊθ²¨ζ₯ζ</th>
                        </tr>
                        </thead>
                        <tbody class="code_tbody">
                        <c:forEach var="PointOrder" items="${list}">
                            <tr>
                                <td>${PointOrder.poId}</td>
                                <td>${PointOrder.memId}</td>
                                <td>${PointOrder.pdId}</td>
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
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(πεε)").closest("a").addClass("active disabled topage");
    $("a:contains(π»θ¨ε?η?‘η)").closest("a").attr("aria-expanded", "true");
    $("#pageSubmenu2").addClass("show");
    $("#pageSubmenu2 a:contains(πθ¨ε?ηΈ½θ¦½)").closest('a').addClass("active disabled bg-white topage");
</script>
<script>
    const list = [];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list) {
        switch (e) {
            case 1:
                $("#a2").removeClass("disabled");
                $("#a3").removeClass("disabled");
                $("#a4").removeClass("disabled");
                $("#a5").removeClass("disabled");
                break;
            case 2:
                $("#a2").removeClass("disabled");
                break;
            case 3:
                $("#a3").removeClass("disabled");
                break;
            case 4:
                $("#a4").removeClass("disabled");
                break;
            case 5:
                $("#a5").removeClass("disabled");
                break;
        }
    }
</script>
</body>
</html>