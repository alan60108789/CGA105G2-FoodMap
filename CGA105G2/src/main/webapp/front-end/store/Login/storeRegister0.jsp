<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🥙註冊</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerout.jsp" %>
<!-- header end -->
<!-- main -->
<section class="jumbotron jumbotron-fluid mb-17 bg-yellow-20">
    <div class="container">
        <h1 class="jumbotron-title ">🥙店家註冊</h1>
    </div>
</section>
<div class="container  mb-17 p-4">
    <section class="shadow" style="background-color: #2779e2; border-radius: 35px;">
        <div class="container p-4">
            <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                <div class="col-xl-9">
                    <h1 class="text-white text-center m-5">Store Registration</h1>
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body">
                            <Form method="post" action="${pageContext.request.contextPath}/LonginServlet">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">店家地址</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <div class="js-demeter-tw-zipcode-selector" data-city="#city"
                                             data-dist="#dist" placeholder="請輸入郵遞區號">
                                            <select id="city" placeholder="請選擇縣市" name="STORE_CITY"></select>
                                            <select id="dist" placeholder="請選擇鄉鎮區" name="STORE_DISTRICT"></select>
                                        </div>
                                        <input type="text" class="form-control form-control-lg" id="memberstore-address"
                                               name="STORE_ADDRESS"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="px-5 py-4 ">
                                    <input type="hidden" name="action" value="searchstore1">
                                    <button type="submit" class="btn btn-block btn-primary btn-lg fs-5">Search</button>
                                </div>
                            </FORM>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<a
        class="d-block btn btn-outline-danger  position-fixed position-bottom-10  position-right-10 text-center"
        href="#" data-toggle="smooth-scroll" data-target="#page-start-anchor"
        style="z-index: 9; border-radius: 50%;"> <i
        class="material-icons text-black ">arrow_upward</i>
</a>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
<script>
    $("a:contains(🥙註冊)").closest("a").addClass("active disabled topage");
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
</body>
</html>