<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<section class="header header-fixed-xl">
    <div class="header-main bg-warning">
        <div class="container" style="max-width: 1400px">
            <div class="row" style="max-width: 1400px">
                <nav class="navbar navbar-expand-lg navbar-light fs-md-6" id="header-navbar">
                    <!-- @*Navbar(白色)*@ -->
                    <a class="navbar-brand font-weight-bold" href="/CGA105G2/index.jsp">
                        <img src="/CGA105G2/assets/images/Logo.PNG" style="width: 100px; height: 100px" alt="" />FoodMap</a>
                    <!--            ps-->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <div class="navbar-spacer"></div>
                            <div class="navbar-spacer"></div>
                            <div class="navbar-spacer"></div>
                            <div class="navbar-spacer"></div>
                            <div class="navbar-spacer"></div>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/BlankPage/contactUs.jsp">
                                    📭聯繫我們
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/front-end/Member/member/memberRegister.jsp">
                                    🥙註冊
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase " data-toggle="none" href="/CGA105G2/front-end/Member/member/memberLognIn.jsp">
                                    🚪Sign in
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- header end -->
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>
</html>
