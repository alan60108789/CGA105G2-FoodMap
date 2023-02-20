<%@ page import="com.member.model.Member.pojo.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerout.jsp" %>
<!-- header end -->
<!-- main -->
<section class="jumbotron jumbotron-fluid mb-17 bg-yellow-20">
    <div class="container">
        <h1 class="jumbotron-title ">會員註冊</h1>
    </div>
</section>
<div class="container mb-17 p-4">
    <section class="shadow p-4" style="background-color: #2779e2; border-radius: 35px;">
        <div class="container p-4">
            <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                <div class="col-xl-9">
                    <h1 class="text-white text-center m-5">Member Registration</h1>
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-4">
                            <%-- 錯誤表列 --%>
                            <c:if test="${not empty errorMsgs}">
                                <font style="color: red">請修正以下錯誤:</font>
                                <ul>
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <li style="color: red">${message}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <Form method="post"
                                  action="${pageContext.request.contextPath}/LonginServlet"
                                  name="addfrom">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">用戶名稱<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" name="MEM_NAME" value="${memname}"
                                               class="form-control form-control-lg" id="memberr-fullname">
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">帳號<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" name="MEM_ACC" value="${memacc}"
                                               class="form-control form-control-lg" id="memberr-accountid">
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">密碼<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="password" name="MEM_PWD" value="${memname}"
                                               class="form-control form-control-lg" id="memberr-password"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">確認密碼<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="password" name="MEM_PWD2"
                                               class="form-control form-control-lg"
                                               id="memberr-confirmpassword"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">真實姓名<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" name="MEM_RECIPIENT" value="${memrecipient}"
                                               class="form-control form-control-lg" id="memberr-recipient"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">身分證字號<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" name="MEM_TW_ID" value="${memtwid}"
                                               class="form-control form-control-lg"
                                               id="memberr-identitycard"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">出生日期<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="date" name="MEM_BIRTHDAY" value="${membirthday}"
                                               class="form-control form-control-lg" id="memberr-birthday"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">電話號碼<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="tel" name="MEM_PHONE" value="${memphone}"
                                               class="form-control form-control-lg"
                                               id="memberr-phonenumber"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">地址<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input class="js-demeter-tw-zipcode-selector"
                                               name="MEM_POSTAL_CODE" data-city="#city4" data-dist="#dist4"
                                               placeholder="請輸入郵遞區號" value="22048"> <select
                                            id="city4" placeholder="請選擇縣市" name="MEM_CITY"></select> <select
                                            id="dist4" placeholder="請選擇鄉鎮區" name="MEM_DISTRICT"></select>
                                        <input type="text" name="MEM_ADDRESS" value="${memaddress}"
                                               class="form-control form-control-lg"
                                               id="memberstore-address"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center py-3">
                                    <div class="col-md-3 ps-5">
                                        <h4 class="mb-0">Email<p style="color: red">*必填</p></h4>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="email" name="MEM_MAIL" value="${memmail}"
                                               class="form-control form-control-lg"
                                               placeholder="example@example.com" id="memberr-emailaddress"/>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="px-5 py-4 ">
                                    <input type="hidden" name="action" value="insert">
                                    <button type="submit"
                                            class="btn btn-block btn-primary btn-lg fs-5">Send
                                        application
                                    </button>
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
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
<script>
    $("a:contains(🥙註冊)").closest("a").addClass("active disabled topage");
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
</body>
</html>