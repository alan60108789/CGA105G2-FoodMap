<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="container  mb-17 p-4">
                <section class="shadow p-4" style="background-color: #2779e2; border-radius: 35px;">
                    <div class="container p-4">
                        <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                            <div class="col-xl-9">
                                <h1 class="text-white text-center m-5">🔆帳號設定</h1>
                                <div class="card" style="border-radius: 15px;">
                                    <div class="card-body p-4">
                                        <Form method="post"
                                              action="${pageContext.request.contextPath}/Member/StoreServlet"
                                              name="addfrom">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">店家名稱</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg" readonly
                                                           id="memberstore-storename" name="STORE_NAME"
                                                           value="${Store.storeName}" >
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">店家地址</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <div class="js-demeter-tw-zipcode-selector" data-city="#city"
                                                         data-dist="#dist">
                                                        <select id="city" disabled
                                                                placeholder="${Store.storeCity}"      name="STORE_CITY"></select>
                                                        <select id="dist" disabled
                                                                placeholder="${Store.storeDistrict}"  name="STORE_DISTRICT"></select>
                                                    </div>
                                                    <input type="text" class="form-control form-control-lg" readonly
                                                           id="memberstore-address" name="STORE_ADDRESS"
                                                           value=${ Store.storeAddress}>
                                                    <a style="color: red">${ errorMsgs.STORE_ADDRESS}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3 ">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">帳號</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg" readonly
                                                           id="memberstore-accountid" name="STORE_ACC"
                                                           value=${Store.storeAcc}>
                                                    <a style="color: red">${ errorMsgs.STORE_ACC}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">營業時間</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
											<textarea class="form-control" rows="3"
                                                      id="memberstore-storehours"
                                                      name="STORE_HOURS">${ Store.storeHours }</textarea>
                                                    <a style="color: red">${ errorMsgs.STORE_HOURS}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">統一編號</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg"
                                                           id="memberstore-taxid" name="STORE_COM_ID"
                                                           value=${ Store.storeComId }> <a
                                                        style="color: red">${ errorMsgs.STORE_COM_ID}</a>
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
                                                           value=${ Store.storePhone1 }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">Email</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="email" class="form-control form-control-lg"
                                                           placeholder="example@example.com" id="email"
                                                           name="STORE_MAIL" value=${ Store.storeMail }> <a
                                                        style="color: red">${ errorMsgs.STORE_MAIL}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">店家資訊</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
											<textarea class="form-control" rows="3"
                                                      placeholder="Message sent to the employer"
                                                      id="memberstore-storeinfo"
                                                      name="STORE_TEXT">${ Store.storeText } </textarea>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">店家URL</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg"
                                                           id="storeurl" name="STORE_URL"
                                                           value=${ Store.storeUrl }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">店家粉專</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg"
                                                           id="storeweb" name="STORE_WEB"
                                                           value=${ Store.storeWeb }>
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
                                                           name="STORE_COM_ADDRESS"
                                                           value=${Store.storeComAddress }>
                                                    <a style="color: red">${errorMsgs.STORE_COM_ADDRESS}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h3 class="mb-0">申請人電話</h3>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" class="form-control form-control-lg"
                                                           placeholder="Holder's Phone" id="memberstore-phone"
                                                           name="STORE_PHONE2" value=${Store.storePhone2 }>
                                                    <a style="color: red">${errorMsgs.STORE_PHONE2}</a>
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
                                                           id="memberstore-identitycard" name="STORE_TW_ID"
                                                           value=${ Store.storeTwId }>
                                                    <a style="color: red">${ errorMsgs.STORE_TW_ID}</a>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <hr class="mx-n3">
                                            <div class="px-5 py-4 ">
                                                <input type="hidden" name="action" value="update2">
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
        </main>
        <a
                class="d-block btn btn-outline-danger  position-fixed position-bottom-10  position-right-10 text-center"
                href="#" data-toggle="smooth-scroll" data-target="#page-start-anchor"
                style="z-index: 9; border-radius: 50%;"> <i
                class="material-icons text-black ">arrow_upward</i>
        </a>
        <!-- main -->
        <!-- footer start -->
        <%@ include file="/front-end/store/01h/footerin.jsp" %>
        <!-- footer end -->
        <!-- sidebar menu Class -->
        <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
        <script>
            $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
            $("a:contains(🔻帳號管理)").closest("a").attr("data-toggle", "show");
            $("#pageSubmenu7").removeClass("collapse");
            $("#pageSubmenu7 a:contains(🔆帳號設定)").closest("a").addClass("active disabled bg-white topage");
        </script>
        <script>
            $(document).ready(function () {
                new ClipboardJS('.btn');
            });
        </script>

</body>

</html>