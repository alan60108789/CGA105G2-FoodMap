<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="com.store.model.service.StoreService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.model.Store.pojo.Store" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <!-- Icon js -->
    <script src="https://kit.fontawesome.com/2c6d23848b.js" crossorigin="anonymous"></script>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        /* ==============搜尋頁面開始================ */
        .card {
            border: none;
            background-color: #252836;
            color: #fff;
            border-radius: 12px;
        }

        .user-timing {

            right: 9px;
            bottom: 9px;
            color: #fff;
        }

        .views-content {
            color: #606271;
        }

        .views {
            font-size: 12px;

        }


        .dots {
            display: flex;
            height: 10px;
            width: 10px;
            background-color: green;
            border-radius: 50%;
            margin-left: 5px;
            margin-bottom: 6px;
        }

        .days-ago {
            margin-top: -10px;
            color: #606271;
        }


        .snipimage img {
            height: 200px;
        }

        /* ==============搜尋頁面結束================ */
    </style>
</head>
<body>
<c:if test="${memId > 0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
    <!-- header end -->
</c:if>
<c:if test="${memId == null||memId <=0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
    <!-- header end -->
</c:if>
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <c:if test="${memId > 0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
            <!-- nav end -->
        </c:if>
        <c:if test="${memId == null||memId <=0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin00.jsp" %>
            <!-- nav end -->
        </c:if>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆搜尋結果</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-info">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-info">Export</button>
                    </div>
                </div>
            </div>
            <!-- ====================搜尋頁面開始==================== -->
            <ol style="list-style: none;">
                <!--=================for each開始==================== -->
                <%@ include file="/front-end/Member/member/page1.file" %>
                
                <c:forEach var="store" items="${list}">
                    <li id="search_result1">
                        <div class="row justify-content-center mt-10 mb-10">
                            <div class="col-md-3 border">
                                <div class="position-relative snipimage" style="height: 200px;">
                                    <!--==============這是google地圖====================== -->
                                    <img src="https://via.placeholder.com/450x350"
                                         class="rounded img-fluid img-responsive" style="width: 100%;height: 100%;">
                                </div>
                            </div>
                            <div class="col-md-5 border">
                                <div class="mt-2">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <span class="mb-1"
                                              style="font-size:35px;font-weight: 1000;">${store.storeName} </span>
                                    </div>
                                    <div class="total_information" style="font-size: 18px;">
                                        <div style="line-height: 35px;">
                                                ${store.storeName}
                                        </div>
                                        <div style="line-height: 35px;">
                                                ${store.storeCity}${store.storeDistrict}${store.storeAddress}
                                        </div>
                                        <div style="line-height: 35px;">
                                                ${store.storePhone1}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2 border d-flex flex-column justify-content-end">
                                <form method="post" action="/CGA105G2/LonginServlet" name="form1">
                                    <button name="action" value="StorePage" type="submit"
                                            class="btn btn-success btn-block mt-5"
                                            style="font-size:28px;border:0;margin-bottom:10px;">進入頁面
                                    </button>
                                    <input type="hidden" name="SearchstoreId" value="${store.storeId}">
                                </form>
                            </div>
                        </div>
                    </li>
                </c:forEach>
                <!-- =================for each結束==================== -->
                <%@ include file="/front-end/Member/member/page2.file" %>
            </ol>
            <!-- ====================搜尋頁面結束==================== -->
            <!-- 頁數開始 -->
            <nav aria-label="Page navigation example " class=" my-5 mt-20">
                <ul class="pagination justify-content-center fs-6">
                    <li class="page-item">
                        <a class="page-link" href="#">上一頁</a>
                    </li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">下一頁</a>
                    </li>
                </ul>
            </nav>
            <!-- 頁數結束 -->
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script src="/assets/js/vendor.js"></script>
<script src="/assets/js/polyfills.js"></script>
<script src="/assets/js/app.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhKclAtJHGqNeIzBRjYLisnajuzq_PCcA&callback=initMap"></script>
<!--     ===================google地圖結束======================== -->
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>
</body>
</html>