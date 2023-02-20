<%@ page import="com.store.model.Store.pojo.Store" %>
<%@ page import="com.member.model.Member.pojo.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Store store = (Store) request.getAttribute("store");
    Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/Loginstyle.css">
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            height: 100%;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .jarallax-img {
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            height: 100%;
        }

        a {
            color: black;
        }
    </style>
</head>
<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headeronlyout.jsp" %>
<!-- header end -->
<!-- main -->
<section class="scontainer-fluid" id="our-work">
    <div class="section-content text-center container">
        <div class="d-flex justify-content-center justify-content-md-between align-items-end">
            <div class="d-none d-md-block">
                <div class="btn-group" id="btg">
                    <div class="radio-buttons-group border-0  " data-toggle="shuffle-grid" data-target="#projects-grid">
                        <button class="btn btn-light selected fs-4" data-value="memberLogin">會員登入</button>
                        <button class="btn btn-light fs-4" data-value="storeLogin">店家登入</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="shuffle-grid shuffle-grid-gap-14 shuffle-grid-cols-1 shuffle-grid-cols-md-1 shuffle-grid-cols-xl-1 m-5"
             id="projects-grid">
            <!--會員登入-->
            <div class="shuffle-grid-item" data-groups="memberLogin" id="memberLogin1">
                <section>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-12 col-lg-10">
                                <div class="wrap d-md-flex">
                                    <div class="img">
                                        <img src="/CGA105G2/assets/images/ex1.jpg" alt="hero background"
                                             class="jarallax-img">
                                    </div>
                                    <div class="login-wrap p-4 p-md-5">
                                        <div class="d-flex">
                                            <div class="w-100">
                                                <h3 class="mb-5">Sign In</h3>
                                            </div>
                                        </div>
                                        <%-- 錯誤表列 --%>
                                        <c:if test="${not empty errorMsgm}">
                                            <c:forEach var="message" items="${errorMsgm}">
                                                <font style="color:red">${message}</font>
                                            </c:forEach>
                                        </c:if>
                                        <form class="signin-form" method="post"
                                              action="${pageContext.request.contextPath}/LonginServlet"
                                              name="signinm">
                                            <div class="form-group mb-3">
                                                <label class="label" for="name">Username</label>
                                                <input id="name" name="MEM_ACC" type="text" class="form-control"
                                                       placeholder="Useracc"
                                                       value="<%=(member==null)?"":member.getMemAcc()%>">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label class="label" for="password">Password</label>
                                                <input id="password" name="MEM_PWD" type="password" class="form-control"
                                                       placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <input type="hidden" name="action" value="Signinm">
                                                <button type="submit"
                                                        class="form-control btn btn-primary rounded submit px-3">Sign In
                                                </button>
                                            </div>
                                            <div class="form-group d-md-flex">
                                                <div class="w-50 text-left">
                                                    <a class="checkbox-wrap checkbox-primary mb-0"
                                                       href="/CGA105G2/front-end/Member/member/memberRegister.jsp">註冊</a>
                                                </div>
                                                <div class="w-50 text-md-right">
                                                    <a href="/CGA105G2/front-end/Member/member/forget1.jsp">忘記密碼</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <!--店家登入-->
            <div class="shuffle-grid-item hiddenBtn" data-groups="storeLogin" id="storeLogin1" style="display: none;">
                <section>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-12 col-lg-10">
                                <div class="wrap d-md-flex">
                                    <div class="img">
                                        <img src="/CGA105G2/assets/images/ex2.jpg" alt="hero background"
                                             class="jarallax-img">
                                    </div>
                                    <div class="login-wrap p-4 p-md-5">
                                        <div class="d-flex">
                                            <div class="w-100">
                                                <h3 class="mb-5">Sign In</h3>
                                            </div>
                                        </div>
                                        <%-- 錯誤表列 --%>
                                        <c:if test="${not empty errorMsgS}">
                                            <c:forEach var="message" items="${errorMsgS}">
                                                <font style="color:red">${message}</font>
                                            </c:forEach>
                                        </c:if>
                                        <form class="signin-form" method="post"
                                              action="${pageContext.request.contextPath}/LonginServlet" name="signins">
                                            <div class="form-group mb-3">
                                                <label class="label" for="name">Username</label>
                                                <input type="text" class="form-control" placeholder="Useracc"
                                                       name="STORE_ACC" value="${storeacc}">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label class="label" for="password">Password</label>
                                                <input type="password" class="form-control" placeholder="Password"
                                                       name="STORE_PWD">
                                            </div>
                                            <div class="form-group">
                                                <input type="hidden" name="action" value="Signins">
                                                <button type="submit"
                                                        class="form-control btn btn-primary rounded submit px-3">Sign In
                                                </button>
                                            </div>
                                            <div class="form-group d-md-flex">
                                                <div class="w-50 text-left">
                                                    <a href="/CGA105G2//front-end/store/Login/storeRegister0.jsp">註冊</a>
                                                </div>
                                                <div class="w-50 text-md-right">
                                                    <a href="/CGA105G2/front-end/Member/member/forget2.jsp">忘記密碼</a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</section>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<!-- Vue -->
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script>
    $("a:contains(🚪Sing in)").closest("a").addClass("active disabled topage");
    $('#btg').hover(function () {
        $(".hiddenBtn").css("display", "block");
    });
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
</body>
</html>