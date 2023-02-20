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
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="container mb-17 p-4">
                <section class="shadow p-4"
                         style="background-color: #2779e2; border-radius: 35px;">
                    <div class="container p-4">
                        <div
                                class="row d-flex justify-content-center align-items-center h-100 p-4">
                            <div class="col-xl-9">
                                <h1 class="text-white text-center m-5">🔆基本資料</h1>
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
                                              enctype="multipart/form-data"
                                              action="${pageContext.request.contextPath}/Member/MemberServlet"
                                              name="updatee">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">用戶名稱</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_NAME"
                                                           class="form-control form-control-lg"
                                                           id="memberr-fullname" value=${ Member.memName }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">帳號</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_ACC" readonly="readonly"
                                                           class="form-control form-control-lg"
                                                           id="memberr-accountid" value=${ Member.memAcc }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">真實姓名</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_RECIPIENT" readonly="readonly"
                                                           class="form-control form-control-lg"
                                                           id="memberr-recipient" value=${ Member.memRecipient }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">身分證字號</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_TW_ID"
                                                           class="form-control form-control-lg"
                                                           id="memberr-identitycard" value=${ Member.memTwId }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">出生日期</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="date" name="MEM_BIRTHDAY" readonly="readonly"
                                                           class="form-control form-control-lg"
                                                           id="memberr-birthday" value=${ Member.memBirthday }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">電話號碼</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_PHONE"
                                                           class="form-control form-control-lg"
                                                           id="memberr-phonenumber" value=${ Member.memPhone }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">地址</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input class="js-demeter-tw-zipcode-selector"
                                                           name="MEM_POSTAL_CODE" data-city="#city4"
                                                           data-dist="#dist4" placeholder="請輸入郵遞區號"
                                                           value=${ Member.memPostalCode }> <select
                                                        id="city4" placeholder="請選擇縣市" name="MEM_CITY">
                                                    <option>${ Member.memCity }</option>
                                                </select> <select id="dist4" placeholder="請選擇鄉鎮區"
                                                                  name="MEM_DISTRICT">
                                                    <option>${ Member.memDistrict }</option>
                                                </select>
                                                    <input type="text" name="MEM_ADDRESS"
                                                           class="form-control form-control-lg"
                                                           id="memberstore-address" value=${ Member.memAddress }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">Email</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="email" name="MEM_MAIL"
                                                           class="form-control form-control-lg"
                                                           placeholder="example@example.com"
                                                           id="memberr-emailaddress" value=${ Member.memMail }>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">自我介紹</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
														<textarea class="form-control" name="MEM_TEXT" rows="3"
                                                                  placeholder="Message sent to the employer"
                                                                  id="memberr-personalinfo">${ Member.memText }</textarea>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center py-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">上傳照片</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input class="form-control form-control-lg" name="MEM_PIC"
                                                           id="formFileLg" type="file" id="memberr-uploadphoto"
                                                           value=${ Member.memPic }>
                                                    <div class="small text-muted mt-2">Upload your photo
                                                        sticker
                                                    </div>
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="px-5 py-4 ">
                                                <input type="hidden" name="action" value="update">
                                                <button type="submit"
                                                        class="btn btn-block btn-primary btn-lg fs-5">Update
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
    </div>
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
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻設定)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu4").removeClass("collapse");
    $("#pageSubmenu4 a:contains(🔆基本資料)").closest("a").addClass("active disabled bg-white topage");
</script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
</body>
</html>