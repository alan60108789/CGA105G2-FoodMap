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
        /* ===================會員的css=============== */
        .dots {
            height: 20px;
            width: 20px;
            background-color: green;
            border-radius: 50%;
            border: 2px solid #fff;
            right: -4px;
            bottom: 8px
        }

        .box-ellipse {
            background-color: #ebedf0;
            padding-right: 10px;
            color: #000;
            padding-left: 10px;
            padding-top: 2px;
            padding-bottom: 2px;
            border-radius: 4px;
            cursor: pointer
        }

        .box-pensil {
            background-color: blue;
            color: #fff;
            cursor: pointer;
            font-size: 15px;
            padding-right: 10px;
            padding-left: 10px;
            padding-top: 2px;
            padding-bottom: 2px;
            border-radius: 4px
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
            <!-- ==================搜尋開始======================= -->
            <ol style="list-style: none;">
                <!--==========================for each開始=========================== -->
                <c:forEach var="member" items="${list}">
                    <c:if test="${memId != member.memId}">
                        <li id="search_result1">
                            <div class="row justify-content-center mt-10 mb-10">
                                <div class="col-md-2 border">
                                    <div class="position-relative snipimage "
                                         style="height: 150px;text-align: center;line-height: 150px;">
                                        <c:if test="${not empty member.memPic}">
                                            <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member.memId}"
                                                 style="width: 65%;height: 80%; border-radius: 60%;">
                                        </c:if>
                                        <c:if test="${empty member.memPic}">
                                            <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                                 alt="" style="width: 65%;height: 80%; border-radius: 60%;">
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6 border">
                                    <div class="mt-12">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <span class="mb-1"
                                                  style="font-size:35px;font-weight: 1000;">${member.memName} </span>
                                        </div>
                                        <div class="total_information" style="font-size: 18px;">
                                            <div>
                                                @${member.memAcc}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2 border d-flex flex-column justify-content-center">
                                    <form method="post" action="/CGA105G2/LonginServlet" name="form1">
                                        <input type="hidden" name="memId1" value="${memId}">
                                        <button name="action" value="MemberPage" type="submit"
                                                class="btn btn-success btn-block mt-5"
                                                style="font-size:28px;border:0;margin-bottom:10px;">進入頁面
                                        </button>
                                        <input type="hidden" name="SearchMemberId" value="${member.memId}">
                                    </form>
                                </div>
                            </div>

                        </li>
                    </c:if>
                </c:forEach>
                <!-- ======================第二個============================ -->
                <li id="search_result3">
                    <div class="row justify-content-center mt-10 mb-10">
                        <div class="col-md-10 border">
                            <div class="position-relative snipimage "
                                 style="height: 150px;text-align: center;line-height: 150px;">
                                <b><span style="font-size: 50px;">無更多結果</span></b>
                            </div>
                        </div>
                    </div>
                </li>
            </ol>
            <!-- =================搜尋結束======================= -->
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
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
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