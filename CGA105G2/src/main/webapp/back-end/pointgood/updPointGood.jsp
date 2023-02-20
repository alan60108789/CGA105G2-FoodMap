<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>💰point</title>
    <style>
        /* 商品名稱 */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* 兌換點數 */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* 商品圖片 */
        .card-img-top {
            height: 330px;
            width: 230px;
        }

        .col mb-5::after {
            position: absolute;
            content: "";
            top: 50%;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #444444;
            transform: translateY(-50%);
        }

        .bg-warning {
            background-color: pink !important;
        }
    </style>
</head>

<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin02.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <div class="container my-20 col-6 ">
                <div class="card card-body shadow bg-cyan-20 "
                     style="border-radius: 20px;">
                    <h1 class="text-center mt-5">🔆修改商品</h1>
                    <FORM METHOD="post" ACTION="PointServlet" name="form1"
                          enctype="multipart/form-data">
                        <div class="col-8 mx-auto">
                            <img src="${pageContext.request.contextPath}/PointServlet?action=getPdImg&pdId=${param.pdId}"
                                 width=345px height=400px>
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">商品名稱:</label> <input
                                type="TEXT" name="pdName" value="${param.pdName}"/>${errorMsgs.pdName}
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">商品單價:</label> <input
                                type="TEXT" name="pdPrice" value="${param.pdPrice}"/>${errorMsgs.pdPrice}
                        </div>
                        <div class="col-8 mx-auto">
                            <label  class="font-weight-bold fs-6 ">商品介紹:</label> <input
                                type="textarea" cols="40" rows="3" name="pdText" value="${param.pdText}"
                                style="width: 100%; height: 150px;"
                                class="form-control">${errorMsgs.pdText}
                        </div>
                        <div class="col-8 mx-auto">
                            <label class="font-weight-bold fs-6 ">上傳圖片:</label> <input
                                type="file" name="pdImg" class="form-control ">
                        </div>
                        <div class="col-8 mx-auto my-10 text-center">
                            <label class="font-weight-bold fs-6 float-left mt-9">商品狀態
                                :</label>
                            <div class="radio-buttons-group mb-5 mx-auto text-center m-5 ">
                                <input id=pdStatus type="hidden" name="pdStatus" value=0>
                                <input type="button" class="btn btn-light bg-white fs-5" value="上架"
                                       onclick="already()">
                                <input type="button" class="btn btn-light bg-white fs-5 selected" value="下架"
                                       onclick="besold()">
                            </div>
                        </div>
                        <div class=" col-7 mx-auto  text-center">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="pdId" value="${param.pdId}">
                            <button type="submit"
                                    class="btn btn-warning btn-block btn-lg fs-5">送出
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(📔商城)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻商品管理)").closest("a").attr("aria-expanded", "true");
    $("#pageSubmenu3").addClass("show");
    $("#pageSubmenu3 a:contains(🔆商品總覽)").closest('a').addClass("active disabled bg-white topage");
</script>
<script>
    function already() {
        var pdStatus = document.getElementById("pdStatus").value;
        document.form1.pdStatus.value = 1;
    };

    function besold() {
        var pdStatus = document.getElementById("pdStatus").value;
        document.form1.pdStatus.value = 0;
    };
    const list = [];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list) {
        switch (e) {
            case 1:
                $("#a2").removeClass("disabled");
                $("#a3").removeClass("disabled");
                $("#a4").removeClass("disabled");
                $("#a5").removeClass("disabled");
                break;
            case 2:
                $("#a2").removeClass("disabled");
                break;
            case 3:
                $("#a3").removeClass("disabled");
                break;
            case 4:
                $("#a4").removeClass("disabled");
                break;
            case 5:
                $("#a5").removeClass("disabled");
                break;
        }
    }
</script>
</body>
</html>
