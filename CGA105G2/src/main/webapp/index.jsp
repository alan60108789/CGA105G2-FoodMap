<%@ page import="com.store.model.Store.pojo.Store" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Store store = (Store) request.getAttribute("store");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>訪客首頁</title>
    <!-- Bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/CGA105G2/assets/css/carousel.css"/>
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
        .messagetoli{
            padding: 8px;
            border-radius: 30px;
            margin-bottom: 2px;
            font-family: Helvetica, Arial, sans-serif;
        }
    </style>
</head>
<body>
<c:if test="${empId > 0}">
    <%@ include file="/back-end/01h/headerin.jsp" %>
</c:if>
<c:if test="${storeId > 0}">
    <%@ include file="/front-end/store/01h/headeronly.jsp" %>
</c:if>
<c:if test="${memId > 0}">
    <%@ include file="/front-end/Member/01h/headeronlyin.jsp" %>
</c:if>
<c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
    <%@ include file="/front-end/Member/01h/headeronlyout.jsp" %>
</c:if>
<!-- main -->
<section class="hero hero-fullscreen jarallax py-5" data-speed="0.2">
    <div class="hero-background ">
        <img src="/CGA105G2/assets/images/resterant.jpg" alt="hero background" class="jarallax-img"/>
        <div class="d-flex align-items-center justify-content-center py-md-10 h-100">
            <div class="card border-0 text-center mx-10 mx-lg-0 bg-white-90 w-50  mb-10 h-100 ">
                <!-- 搜尋 -->
                <div class="card-body hero m-2 mb-0 h-25">
                    <h2 class="text-black fs-7 m-1">FoodMap</h2>
                    <c:if test="${empId > 0}">
                        <h1 style="color:#ef0000;"> ${empId}歡迎來到FoodMap後台</h1>
                        <P>目前壓力測試中:</P>
                        <ul>
                            <li>請~點數商品盡量新增商品。</li>
                        </ul>
                    </c:if>
                    <c:if test="${storeId > 0}">
                        <h1 style="color:#ef0000;"> ${StoreName}您好，歡迎來到FoodMap旅食地圖</h1>
                        <P>店家功能介紹:</P>
                        <ul>
                            <li>優惠券功能:訂位結帳時可使用折扣。</li>
                            <li>帶位功能:叫號帶位簡易版POS系統。</li>
                            <li>訂位功能:新增餐點、時段、提供會員訂位選購餐點。</li>
                            <li>推撥功能:新增訊息，發送給追蹤您的會員。</li>
                            <li>廣告功能:新增廣告，審核通過後顯示於首頁廣告牆上。</li>
                        </ul>
                    </c:if>
                    <c:if test="${storeId <=0&&empId<=0}">
                        <!-- ===========================form表單開始========================== -->
                        <form METHOD="post" ACTION="/CGA105G2/LonginServlet"
                              class="form-inline  justify-content-center flex-nowrap">
                            <input name="storeName" class="card-text form-control mr-sm-2 mb-1" type="text" placeholder="搜尋. ."
                                   aria-label="Search" style="width: 440px;" value="<c:if test="${not empty errorMsgs}">${errorMsgs.error1}+${errorMsgs.error2}</c:if>">
                            <select name="action" class="card-group"
                                    style="height: 44px;padding: 10px 0px;margin-bottom: 3px; border: 1px solid sandybrown; font-weight: 600;color: rgb(86, 86, 86);">
                                <option value="byStoreName">店家</option>
                                <option value="byMemName">會員</option>
                            </select>
                            <button class="btn btn-outline-warning card my-sm-0 my-5 ml-5" type="submit">開始瀏覽</button>
                        </form>
                    </c:if>
                </div>
                <c:if test="${storeId <=0&&empId<=0}">
                    <!-- 廣告牆 -->
                    <div id="carouselExampleCaptions" class="carousel slide card-body hero m-0 h-75"
                         data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="3"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="4"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="5"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="6"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="7"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="8"></li>
                            <li data-target="#carouselExampleCaptions" data-slide-to="9"></li>
                        </ol>
                        <jsp:useBean id="adSvc" scope="page" class="com.advertise.model.service.AdvertiseService"/>
                        <div class="carousel-inner" style="height: 100%">
                            <c:forEach var="ad" items="${adSvc.statusPass}">
                                <div class="carousel-item  h-100 p-auto adt">
                                <a href="${ad.store.storeWeb}">
                                    <img src="<%=request.getContextPath()%>/adServlet?action=getPhoto&adId=${ad.advId}"
                                         class="d-block card-body  h-100 w-auto "
                                         style="min-width: auto ;margin:0 auto ;position: static !important" alt="...">
                                         </a>
                                    <div class="carousel-caption card-text d-none d-md-block h-25">
                                        <p class="messagetoli" style="background-color: rgba(244,179,95,0.29)	">${ad.advText}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleCaptions" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</section>
<!-- page content end -->
<c:if test="${empId > 0}">
    <%@ include file="/back-end/01h/footerin.jsp" %>
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
</c:if>
<c:if test="${empId ==0}">
<!-- footer start -->
    <%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
</c:if>
<script>
    document.querySelectorAll('.adt')[0].setAttribute("class", "carousel-item  h-100 p-auto adt active");
</script>
<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</html>