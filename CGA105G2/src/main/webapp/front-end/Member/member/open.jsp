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
                    <h1 class="text-center text-red p-4" id="staTitle">帳號開通</h1>
                    <form
                          class="border p-10 text-center">
                        <h1 id="waitSta">結果:</h1>
                        <div>
                            <h1 class="text-danger"
                                id="staResult">
                                <c:if test="${Rust==true}">
                                    成功
                                </c:if>
                                <c:if test="${Rust ==false}">
                                    失敗
                                </c:if>
                            </h1>
                        </div>
                    </form>
                </div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>

</body>
</html>