<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.waiting.model.dao.impl.StandbyDAO" %>
<%@ page import="com.waiting.model.pojo.Standby" %>
<%@ page import="com.waiting.model.service.StandbyService" %>
<%
    StandbyService standbySvc = new StandbyService();
    Integer staCount = standbySvc.standByCount();
    Integer stsid = (Integer) request.getAttribute("stsid");
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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"/>
    <link rel="stylesheet" href="/resources/demos/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G/assets/custom.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css"/>
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

        @media (min-width: 768px) {
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
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
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
            <%
                Standby standbyVo = (Standby) request.getAttribute("standbyVo");
            %>
            <div class="container col-lg-10 p-4">
                <div class="justify-content-center col-lg-6 mx-auto p-4">
                    <h1 class="text-center text-red p-4" id="staTitle">您已完成候位</h1>
                    <form
                          class="border p-10 text-center">
                        <div class="mx-auto" id="yourmun">
                            <label>你的號碼 :</label> <input type="text" value="<%=stsid%>" id="myStaId" disabled
                                                             size=3>
                        </div>
                        <div class="mx-auto" id="nowwatttting">
                            <label>目前候位組數 :</label> <input type="text" value="<%=staCount%>" disabled size=3>
                        </div>
                        <h1 id="waitSta">請稍等叫號</h1>
                        <div>
                            <h1 class="text-danger"
                                id="staResult"></h1>
                        </div>
                    </form>
                </div>
            </div>
            <canvas height=100></canvas>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
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
    $(document).ready(function () {
        let myStaId = $('#myStaId').val();
        var id = setInterval(function () {
            $.ajax({
                type: "POST",
                url: "/CGA105G2/standby",
                data: {
                    action: "checkNum",
                    myStaId: myStaId
                },
                success: function (response) {
                    const sts = response;
                    if (sts == 1) {
                        let a = `您的座位已備妥<br>請於5分鐘內<br>完成報到<br><p id="countdown"></p>`;
                        $("#staResult").html(a);
                        $('#waitSta').html("");
                        $('#staTitle').html("");
                        clearInterval(id);
                        tottime(myStaId);
                    }
                }
            });
        }, 5000);
    });

    function tottime(myStaId) {
        let count = 5 * 60;
        const display = document.querySelector('#countdown');
        var intervalID = setInterval(function () {
            const minutes = Math.floor(count / 60);
            const seconds = count % 60;
            display.innerHTML = `\${minutes}分\${seconds}秒`;
            count--;
            // 當倒數為0時停止計時器
            if (seconds>0) {
                $.ajax({
                    type: "POST",
                    url: "/CGA105G2/standby",
                    data: {
                        action: "checkNum",
                        myStaId: myStaId
                    },
                    success: function (response) {
                        const sts = response;
                        if (sts == 2) {
                            let a = `
                                    <form METHOD="post" action="<%=request.getContextPath()%>/standby" style="padding: 10px 100px;" autocomplete="off">
                                        <input type="hidden" name="action" value="buypage">
                                        <input type="hidden" name="sid" value=${sid}>
                                        <input type="hidden" name="staPhone" value=${staPhone}>
                                        <input type="hidden" name="staNumber" value=${staNumber}>
                                        <button type="submit" class="btn btn-warning mb-1 btn-block fs-5 mb-10">線上點餐</button>
                                    </form>
                                    `;
                            $("#staResult").html(a);
                            $('#waitSta').html("");
                            $('#staTitle').html("");
                            clearInterval(intervalID);
                        }
                    }
                });
            }
        }, 1000);
    }

    <c:if test="${Result > 0}">
        let Result=0;
        Result=${Result};
        let overpage = `${sname}<br>訂單編號:${rid}<br>`;
        if (Result == 1) {
            overpage += `此單筆免付款<br>`;
        }
        ;
        if (Result == 2) {
            overpage += `付款成功!<br>`;
        }
        overpage += `餐點準備中請稍候~`;
        $("#staResult").html(overpage);
        $('#waitSta').html("");
        $('#staTitle').html("");
        $("#yourmun").remove();
        $("#nowwatttting").remove();
    </c:if>

</script>
</body>
</html>