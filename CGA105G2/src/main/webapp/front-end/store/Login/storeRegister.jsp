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
        <h1 class="jumbotron-title">🥙店家註冊</h1>
    </div>
</section>
<div class="container  mb-17 p-4">
    <section class="shadow"
             style="background-color: #2779e2; border-radius: 35px;">
        <div class="container p-4">
            <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                <div class="col-xl-9">
                    <h1 class="text-white text-center m-5">Store Registration</h1>
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body">
                            <Form method="post"
                                  action="${pageContext.request.contextPath}/LonginServlet"
                                  name="addfrom">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">名稱</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" disabled class="form-control form-control-lg"
                                               id="memberstore-storename" name="STORE_NAME" value=${Store.storeName}>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">帳號</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               id="memberstore-accountid" name="STORE_ACC">
                                        <a style="color: red">${ errorMsgs.STORE_ACC}</a>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">密碼</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               id="memberstore-password" name="STORE_PWD">
                                        <a style="color: red">${ errorMsgs.STORE_PWD}</a>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">確認密碼</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               id="memberstore-confirmpassword" name="STORE_PWD2">
                                        <a style="color: red">${ errorMsgs.STORE_PWD2}</a>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">店家電話</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               id="memberstore-phonenumber" name="STORE_PHONE1"
                                               value=${Store.storePhone1}>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center py-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">申請人姓名</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               placeholder="Holder's Name" id="memberstore-name"
                                               name="STORE_COM_ADDRESS">
                                        <a style="color: red">${ errorMsgs.STORE_COM_ADDRESS}</a>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center py-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">申請人電話</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               placeholder="Holder's Phone" id="memberstore-phone" name="STORE_PHONE2">
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <div class="row align-items-center py-3">
                                    <div class="col-md-3 ps-5">
                                        <h3 class="mb-0">身分證字號</h3>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <input type="text" class="form-control form-control-lg"
                                               placeholder="Holder's Identity Card"
                                               id="memberstore-identitycard" name="STORE_TW_ID">
                                        <a style="color: red">${ errorMsgs.STORE_TW_ID}</a>
                                    </div>
                                </div>
                                <hr class="mx-n3">
                                <hr class="mx-n3">
                                <div class="px-5 py-4 ">
                                    <input type="hidden" name="storeId" value=${storeId}>
                                    <input type="hidden" name="action" value="inserts">
                                    <button type="submit" class="btn btn-block btn-primary btn-lg fs-5">Send
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
<script>
    $("a:contains(🥙註冊)").closest("a").addClass("active disabled topage");
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
<script>
    function addCupAlert() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-outline-primary m-5 fs-5',
            },
            buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
            position: 'middle',
            icon: 'success',
            title: '註冊成功，請等待審核',
            showConfirmButton: false,
            timer: 1500
        })
    }

    let toResult = null;
    toResult =
    <%= request.getAttribute("toResult") %>
    if (toResult == true) {
        // alert(toResult);
        addCupAlert();
        toResult = null;
    }
    ;
</script>
</body>
</html>