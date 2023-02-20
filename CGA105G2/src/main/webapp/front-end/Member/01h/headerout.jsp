<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
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

<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>

<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    var inputAcc = document.getElementById('inputAcc');
    var inputPwd = document.getElementById('inputPwd');
    inputAcc.onblur = function () {
        if (inputAcc.value === null || inputAcc.value === "") {
            document.getElementById('spanAcc').innerHTML = "*帳號請勿空白"
        } else if (inputAcc.value.length < 2 || inputAcc.value.length > 12) {
            document.getElementById('spanAcc').innerHTML = "*請輸入長度2~12位英文或數字"
        } else {
            document.getElementById('spanAcc').innerHTML = "	"
        }
        ;
    };
    inputPwd.oninput = function () {
        if (inputPwd.value === null || inputPwd.value === "") {
            document.getElementById('spanPwd').innerHTML = "*密碼請勿空白"
            $('#submit').attr('disabled', 'disabled');
        } else if (inputPwd.value.length < 2 || inputPwd.value.length > 12) {
            document.getElementById('spanPwd').innerHTML = "*請輸入長度2~12位英文或數字"
            $('#submit').attr('disabled', 'disabled');
        } else {
            document.getElementById('spanPwd').innerHTML = "	"
            $('#submit').removeAttr('disabled');
        }
        ;
    };
</script>
</body>
</html>
