<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.waiting.model.dao.impl.StandbyDAO" %>
<%@ page import="com.waiting.model.pojo.Standby" %>
<%@ page import="com.waiting.model.service.StandbyService" %>
<%
    Standby standbyVo = (Standby) request.getAttribute("standbyVo");
    request.getAttribute("standbyVo");
    StandbyService standbySvc = new StandbyService();
    Integer staCount = standbySvc.standByCount();
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous"/>
    <!-- jquery 3.4.1  css -->
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">

    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G/assets/custom.css">
    <link rel="stylesheet"
          href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css"/>
    <!-- <link rel="stylesheet" href="/CGA105G2/src/main/webapp/assets/css/carousel.css" /> -->
    <style>
        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media ( min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }
    </style>
</head>
<body>
<c:if test="${memId > 0}">
    <%@ include file="/front-end/Member/01h/headeronlyin.jsp" %>
</c:if>
<c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
</c:if>
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <c:if test="${memId > 0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
            <!-- nav end -->
        </c:if>
        <c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
            <%@ include file="/front-end/Member/01h/nav/navin00.jsp" %>
        </c:if>
        <main role="main "
              class="col-md-9 ml-sm-auto col-lg-10 px-md-4 container ">
            <div class=" m-10 p-10  " style="">
                <section
                        class="section-content  col-6  py-10 mt-10 mb-10 card shadow bg-yellow-10  mx-auto"
                        id="contacts"
                        style="border: 2px solid rgba(19, 6, 197, 0.089); border-radius: 30px;">
                    <div class="col-12 col-lg-8 mb-14 mb-lg-0 container">
                        <h1 class="text-center mt-5">🔆候位登記</h1>
                        <h2 id="stsResult" class="text-center text-danger"></h2>
                        <%-- 錯誤表列--%>
                        <c:if test="${not empty errorMsgs}">
                            <font style="color: red">請修正以下錯誤:</font>
                            <ul>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li style="color: red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <form ACTION="<%=request.getContextPath()%>/standby"
                              class="row mt-17" METHOD="post"
                              onsubmit="addStandBy(); return true;">
                            <div class="col-12 my-5">
                                <div class="form-group text-center">
                                    <label class="form-label fs-md-6 font-black font-weight-bold">目前候位組數</label>
                                    <input name="staCount"
                                           class="form-control col-md-2   mx-auto " size="10" disabled
                                           value="<%=staCount%>">
                                </div>
                                <div class="col-12 col-sm-12 my-5">
                                    <div class="form-group">
                                        ${Storename} <label
                                            class="form-label fs-md-6  font-weight-bold ">店家名稱</label> <input
                                            type="text" class="form-control " name="Storename"
                                            value="${(Storename == null)? Storename: Storename }"
                                            disabled required/> <input type="hidden" name="sid"
                                                                       value="${(sid == null)? sid: sid }">
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 my-5">
                                    <div class="form-group">
                                        <label class="form-label fs-md-6  font-weight-bold ">姓名</label>
                                        <input type="text" class="form-control" name="staName"
                                               required placeholder="輸入姓名..." id="staName" minlength="1"/>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 my-5">
                                    <div class="form-group">
                                        <label class="form-label fs-md-6 font-black font-weight-bold">電話</label>
                                        <input type="text" class="form-control" id="staPhone"
                                               placeholder="輸入電話..." required="required" maxlength="11"
                                               pattern="09\d{2}\d{6}" name="staPhone"/>

                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 my-5">
                                    <div class="form-group">
                                        <label class="form-label fs-md-6 font-black font-weight-bold">人數</label>
                                        <input type="text" class="form-control "
                                               placeholder="輸入人數..." name="staNumber" id="staNumber"
                                               required minlength="1" pattern="^([1-9]|10)$"/>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 my-5 text-center">
                                    <input type="hidden" name="action" value="insertSta">
                                    <input type="submit" value="新增候位"
                                           class="btn btn-outline-info btn=lg fs-7 text-center "
                                           style="border-radius: 20px; width: 300px;" id="insertBtn">
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </main>

    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🌟)").closest("a").addClass("active disabled topage");
</script>

<!-- stickey bar: -->

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>

<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function addStandBy() {
        setTimeout(addStandByAlert(), 1000);
    }

    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: {
                action: "getStoreSts"
            },
            dataType: "text",
            success: function (data) {
                if (data == "5") {
                    $('#stsResult').html("店家目前尚未開放候位");
                    $('#insertBtn').attr('disabled', true);
                }

            },
            error: function () {
                console.log("error");
            }

        });

    });

    function addStandByAlert() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-outline-primary m-5 fs-5',
            },
            buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
            position: 'middle',
            icon: 'success',
            title: '登記成功',
            showConfirmButton: false,
            timer: 1500
        })
    }
</script>
</body>
</html>