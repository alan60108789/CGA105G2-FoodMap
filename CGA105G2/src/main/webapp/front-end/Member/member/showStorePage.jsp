<%@ page import="java.util.List" %>
<%@ page import="com.subs.model.Subscribe.pojo.Subscribe" %>
<%@ page import="com.subs.model.service.SubsService" %>
<%@ page import="com.store.model.Store.pojo.Store" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <link rel="stylesheet" href="/CGA105G2/assets/css/StorePage.css"/>
    <style>
        #map {
            height: 160px;
            width: 190px;
            margin: 20px 0px;
            border: 2px solid gainsboro;
        }
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
<main style="background-color: aliceblue;">
    <div class="container" style="padding: 60px;">
        <div class="row" style="padding: 0;margin: 0px;background-color: white;">
            <div id="store_allinformation" class="col-8 mt-5 ">
                <div class="i" style="display: flex; padding-top: 10px;">
                    <div style="margin-right: 10px;">
                        <img src="/CGA105G2/assets/images/ex1.jpg"
                             style="width: 135px;height: 130px;border:1px solid darkkhaki;">
                    </div>
                    <div>
                        <div class="i" style="padding: 0;">
                            <p style="line-height: 1; font-size: 35px;margin-top: 15px;margin-bottom: 13px;padding: 0;font-weight: 600;">${store.storeName}</p>
                        </div>
                        <c:if test="${ StoreScore >= 0 }">
                            <div class="i">
                                <span style="font-size: 20px;padding: 5px 15px;border-radius:15px ;background-color: rgb(255, 112, 60);font-weight:1000;">${StoreScore}<i
                                        class="fa-solid fa-star" style="color: rgb(249, 249, 106);"></i></span>
                                <span style="font-size: 20px;padding: 5px 5px;font-color:gray">(${commemt}則評論)</span>
                            </div>
                        </c:if>
                        <c:if test="${empty StoreScore}">
                            <div class="i">
                                <span style="font-size: 22px;font-weight:1000;">(0則評論)</span>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col-4">
                        <div>
                            <div id="map"></div>
                        </div>
                    </div>
                    <div class="col-8">
                        <div>
                            <p class="store_information" style="padding-top:20px ; font-size: 20px;">店家地址
                                | ${store.storeCity}${store.storeDistrict}${store.storeAddress}</p>
                        </div>
                        <div>
                            <p class="store_information" style="font-size: 20px;">店家電話 | ${store.storePhone1} </p>
                        </div>
                        <div>
                            <p class="store_information" style="font-size: 20px;">營業時間 | 12:00-13:00</p>
                        </div>
                        <!-- ==============訂閱按鈕開始====================== -->
                        <div class="subscribe_div" , style="margin-top: 10px;">
                            <c:if test="${subslist.size() == 0}">

                                <input type="hidden" name="subStoreId" value="${store.storeId}"
                                       id="storeId">
                                <input type="hidden" name="subMemId" value="${member.memId}"
                                       id="memId">
                                <button class="button button-like insertSubs" type="button"
                                        name="insertSubs" id="subsbutton">
                                    <i class="fa fa-heart"></i> <span id="subscribe_store">訂閱店家</span>
                                </button>

                            </c:if>
                            <c:if test="${subslist.size() != 0 }">

                                <input type="hidden" name="subStoreId" value="${store.storeId}"
                                       id="storeId">
                                <input type="hidden" name="subMemId" value="${member.memId}"
                                       id="memId">
                                <button class="button button-like liked deleteSubs"
                                        type="button" name="deleteSubs" id="subsbutton">
                                    <i class="fa fa-heart"></i> <span id="subscribe_store">已訂閱</span>
                                </button>


                            </c:if>
                            <script src="https://cdn.jsdelivr.net/npm/axios@1.1.2/dist/axios.min.js"></script>
                            <script>
                                const subsbutton = document.querySelector('#subsbutton');
                                subsbutton.addEventListener('click', (e) => {
                                    if (subsbutton.classList.contains('insertSubs')) {
                                        console.log('insertSubs');
                                        $.ajax({
                                            url: "/CGA105G2/MyFavoriteServlet",
                                            type: "post",
                                            data: {
                                                storeId: $('#storeId').val(),
                                                memId: $('#memId').val(),
                                                action: 'insertSubsAjax',
                                            },

                                            success: function (data) {
                                                console.log("test");
                                                subsbutton.classList.toggle('insertSubs');
                                                subsbutton.classList.toggle('deleteSubs');
                                                return;
                                            },
                                            error: function (err) {
                                                console.log(err)
                                            },
                                        });

                                    }
                                    if (subsbutton.classList.contains('deleteSubs')) {
                                        console.log('deleteSubs');
                                        $.ajax({
                                            url: "/CGA105G2/MyFavoriteServlet",
                                            type: "post",
                                            data: {
                                                storeId: $('#storeId').val(),
                                                memId: $('#memId').val(),
                                                action: 'deleteSubsAjax',
                                            },

                                            success: function (data) {
                                                console.log("test");
                                                subsbutton.classList.toggle('deleteSubs');
                                                subsbutton.classList.toggle('insertSubs');
                                                return;

                                            },
                                            error: function (err) {
                                                console.log(err)
                                            },
                                        });
                                    }
                                    ;
                                });
                            </script>
                            <c:if test="${subslist.size() == 0}">
                            <!-- modal開啟後之背景 -->
                            <div class="modal-overlay">
                                <!-- modal開啟後之白底方格 -->
                                <div class="modal-container">
                                    <!-- modal內容 -->
                                    <p class="subscription"
                                       style="font-weight: 1000;color: aliceblue;margin-right: 10px;">從我的最愛移除</p>
                                    <!-- 關閉按鈕 -->
                                    <button class="close-btn">
                                        <!-- 使用Font Awesome的Icon -->
                                        <i class="fa-solid fa-x"></i>
                                    </button>
                                </div>
                            </div>
                            </c:if>
                            <c:if test="${subslist.size() != 0}">
                            <!-- modal開啟後之背景 -->
                            <div class="modal-overlay">
                                <!-- modal開啟後之白底方格 -->
                                <div class="modal-container">
                                    <!-- modal內容 -->
                                    <p class="subscription"
                                       style="font-weight: 1000;color: aliceblue;margin-right: 10px;">已加入我的最愛</p>
                                    <!-- 關閉按鈕 -->
                                    <button class="close-btn">
                                        <!-- 使用Font Awesome的Icon -->
                                        <i class="fa-solid fa-x"></i>
                                    </button>
                                </div>
                            </div>
                            </c:if>
                        </div>
                        <!-- ================訂閱按鈕結束======================= -->
                    </div>
                </div>
            </div>
            <!-- ==========訂餐跟購物商城開始============= -->
            <div class="col-4 d-flex flex-column justify-content-end">
                <div>
                    <c:if test="${sts == 4}">
                        <div>
                            <FORM METHOD="post" ACTION="/CGA105G2/standby">
                                <input type="hidden" name="foodorder_storeId" value="${store.storeId}">
                                <input type="hidden" name="action" value="instandby">
                                <button id="store_standby" type="submit" class="btn btn-success btn-block"
                                        style="font-size:28px;border:0;">線上候位
                                </button>
                            </FORM>
                        </div>
                    </c:if>
                    <c:if test="${canopen > 0}">
                        <div>
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do">
                                <button id="store_order" type="submit" class="btn btn-success btn-block mt-5"
                                        style="font-size:28px;border:0;background-color: #216a51;">立即訂位
                                </button>
                                <input type="hidden" name="action" value="Member_order_button">
                                <input type="hidden" name="foodorder_storeId" value="${store.storeId}">
                            </FORM>
                        </div>
                    </c:if>
                    <div>
                        <a href="/CGA105G2/BlankPage/Loader2.jsp">
                            <button id="store_shop" type="button" class="btn btn-success btn-block mt-5 mb-5"
                                    style="font-size:28px;border:0;background-color: rgb(96, 96, 0);">查看購物商城
                            </button>
                        </a>
                    </div>
                    <div>
                        <a  class="todeepage" href="我發現FoodMap這間餐廳${article.store.storeName}好像不錯也 http://tibame.likktw.com:8081/CGA105G2/LonginServlet?action=StorePage&SearchstoreId=${store.storeId}">
                            <button id="store_line" type="button" class="btn btn-success btn-block mt-5 mb-5"
                                    style="font-size:28px;border:0;background-color: rgb(96, 96, 0);">
                                分享到Line
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- ===============店家簡介區================= -->
        <div class="container" style="margin-top: 10px;background-color: white;">
            <div class="row">
                <div class="col-md-12" style="height: 100px;font-size: 20px;font-weight: 800;margin-top: 5px;">
                    店家簡介:
                    <div style="font-weight: 100;">
                    </div>
                </div>
            </div>
        </div>
        <!-- ===============圖片輪播開始================ -->
        <div class="container"
             style="height:690px !important;margin-top: 10px;padding-top: 20px;padding-bottom: 60px;background-color: white;">
            <div class="row">
                <div class="col-md-12">
                    <div id="custCarousel" class="carousel slide">
                        <!-- slides -->
                        <div class="carousel-inner">
                            <div class="carousel-item active p-auto">
                                <img src="/CGA105G2/assets/images/ex2.jpg" alt="Hills" class="d-block"
                                     style="margin:0 auto">
                            </div>
                            <div class="carousel-item">
                                <img src="/CGA105G2/assets/images/ex3.jpg" alt="Hills" class="d-block"
                                     style="margin:0 auto">
                            </div>
                            <div class="carousel-item">
                                <img src="/CGA105G2/assets/images/ex4.jpg" alt="Hills" class="d-block"
                                     style="margin:0 auto">
                            </div>
                            <div class="carousel-item">
                                <img src="/CGA105G2/assets/images/ex5.jpg" alt="Hills" class="d-block"
                                     style="margin:0 auto">
                            </div>
                            <div class="carousel-item">
                                <img src="/CGA105G2/assets/images/ex1.jpg" alt="Hills" class="d-block"
                                     style="margin:0 auto">
                            </div>
                        </div>
                        <!-- Left right -->
                        <a class="carousel-control-prev" href="#custCarousel" data-slide="prev">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                                <path d="M15.28 5.22a.75.75 0 0 1 0 1.06L9.56 12l5.72 5.72a.749.749 0 0 1-.326 1.275.749.749 0 0 1-.734-.215l-6.25-6.25a.75.75 0 0 1 0-1.06l6.25-6.25a.75.75 0 0 1 1.06 0Z"></path>
                            </svg>
                        </a>
                        <a class="carousel-control-next" href="#custCarousel" data-slide="next">
                            <svg viewBox="0 0 24 24" width="24" height="24">
                                <path d="M8.72 18.78a.75.75 0 0 1 0-1.06L14.44 12 8.72 6.28a.751.751 0 0 1 .018-1.042.751.751 0 0 1 1.042-.018l6.25 6.25a.75.75 0 0 1 0 1.06l-6.25 6.25a.75.75 0 0 1-1.06 0Z"></path>
                            </svg>
                        </a>
                        <!-- Thumbnails下面滑動圖 -->
                        <ol class="carousel-indicators list-inline" style="margin: 0;">
                            <li class="list-inline-item active">
                                <a id="carousel-selector-0" class="selected" data-slide-to="0"
                                   data-target="#custCarousel">
                                    <img src="/CGA105G2/assets/images/ex2.jpg" class="img-fluid">
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a id="carousel-selector-1" data-slide-to="1" data-target="#custCarousel">
                                    <img src="/CGA105G2/assets/images/ex3.jpg" class="img-fluid">
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a id="carousel-selector-2" data-slide-to="2" data-target="#custCarousel">
                                    <img src="/CGA105G2/assets/images/ex4.jpg" class="img-fluid">
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a id="carousel-selector-3" data-slide-to="3" data-target="#custCarousel">
                                    <img src="/CGA105G2/assets/images/ex5.jpg" class="img-fluid">
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a id="carousel-selector-4" data-slide-to="4" data-target="#custCarousel">
                                    <img src="/CGA105G2/assets/images/ex1.jpg" class="img-fluid">
                                </a>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <!-- ==============x圖片輪播結束================ -->
        <!-- ================會員評論區開始================= -->
        <div class="container mt-5">
            <div class="row">
                <div class="col-12" style="background-color:white;padding: 15px 0px;">
                    <p style="margin-left: 15px;font-size: 30px;font-weight: 900;">會員評論區</p>
                </div>
            </div>
            <div class="d-flex justify-content-center row">
                <!-- ==============第一則評論================== -->
                <ol class="member_postinstore col-12" style="list-style: none;padding: 0;">
                    <c:if test="${articlelist.size() == 0}">
                        <div class="col-12" style="padding: 15px 0px 0px 0px;">
                            <div class="col-12" style="background-color: white;height:150px;line-height:120px;">
                                <div class="col-12 d-flex flex-row user p-2 pt-5">
                                    <p style="font-size:30px;font-weight:1000;margin-left:380px">
                                        目前暫無評論
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${articlelist.size() != 0}">
                        <c:forEach var="articlelist" items="${articlelist}">
                            <li>
                                <div class="col-12" style="padding: 15px 0px 0px 0px;">
                                    <div class="col-12" style="background-color: white;">
                                        <div class="col-12 d-flex flex-row user p-2 pt-5">
                                            <c:if test="${not empty articlelist.member.memPic}">
                                                <img class="rounded-circle"
                                                     src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${articlelist.memId}"
                                                     style="width:60px;">
                                            </c:if>
                                            <c:if test="${empty articlelist.member.memPic}">
                                                <img class="rounded-circle"
                                                     src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                                     alt="" style="width:60px;"/>
                                            </c:if>
                                            <c:if test="${member.memId == articlelist.member.memId}">
                                            <a href="<%=request.getContextPath()%>/front-end/Member/art/listArt.jsp">
                                                </c:if>
                                                <c:if test="${member.memId != articlelist.member.memId}">
                                                <a href="/CGA105G2/LonginServlet?action=MemberPage&SearchMemberId=${articlelist.member.memId}">
                                                    </c:if>
                                                    <div class="col-12 d-flex flex-column"><span
                                                            class="name font-weight-bold">${articlelist.member.memName}</span><span><fmt:formatDate
                                                            value="${articlelist.artTime}" pattern="yyyy-MM-dd"/></span>
                                                    </div>
                                                </a>
                                                <c:if test="${not empty articlelist.artTag}">
                                                <span
                                                        style="font-size: 20px;padding: 8px 12px;border-radius:15px ;margin-left: 10px;background-color: rgb(82, 206, 156);color: white;line-height:25px;height:40px;margin-top:10px">#${articlelist.artTag}
                                        </span>
                                                </c:if>
                                        </div>
                                        <!-- =================評分====================== -->
                                        <div style="padding-left:10px;font-size: 15px; ">
                                            <span style="background-color:rgb(255, 112, 60);padding: 4px 10px;border-radius: 20px;">${articlelist.artScore} <i
                                                    class="fa-solid fa-star"
                                                    style="color: rgb(249, 249, 106);"></i></span>
                                        </div>
                                        <div class="mt-2 pb-5 pl-5 pr-5">
                                            <p class="comment-content" style="font-size: 18px;">
                                                    ${articlelist.artText}.</p>
                                        </div>
                                        <div class="d-flex align-items-center p-8 border-top Thumbs">
                                            <i class="fa-regular fa-thumbs-up" style="position: absolute;right: 20px;">
                                                讚</i>
                                        </div>
                                    </div>

                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ol>
            </div>
        </div>
    </div>
    <!-- <==============會員評論區結束====================> -->
</main>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!--     ===================google地圖開始======================== -->
<script>
    function initMap() {
        var uluru = {
            lat: ${store.storeMap.split(',')[0].substring(1)},
            lng: ${store.storeMap.split(',')[1].substring(0,10)}
        };
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 18,
            center: uluru
        });
        var marker = new google.maps.Marker({
            position: uluru,
            map: map
        });
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhKclAtJHGqNeIzBRjYLisnajuzq_PCcA&callback=initMap">
</script>
<!--     ===================google地圖結束======================== -->
<script>
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
<script>
    $(".heart.fa").click(function () {
        $(this).toggleClass("fa-heart fa-heart-o");
    });
</script>
<script src="/CGA105G2/assets/js/Storepage.js"></script>
<script src="https://kit.fontawesome.com/2c6d23848b.js" crossorigin="anonymous"></script>
<script>
    <%--    將line分享連結轉utf-8--%>
    $(document).ready(function () {
        $(".todeepage").each(function () {
            let href = $(this).attr("href");
            const encodedHref = "https://social-plugins.line.me/lineit/share?text=" + encodeURIComponent(href)+"&from=line_scheme";
            $(this).attr("href", encodedHref);
        })
    })
</script>
</body>

</html>