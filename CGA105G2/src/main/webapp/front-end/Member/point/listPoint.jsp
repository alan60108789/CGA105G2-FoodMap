<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>
</head>

<body>
<div id="page-start-anchor"></div>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="py-5">
                <div
                        class="d-flex justify-content-between flex-wrap flex-md-nofjoewrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2 mt-5">💰剩餘點數<span
                            class="lrp_text_count">${Member.memPoint} <dfn>points</dfn></span></h1>
                    </Form>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group mr-2">
                            <!--                             <button type="button" class="btn btn-sm btn-outline-info">Share</button> -->
                            <!--                             <button type="button" class="btn btn-sm btn-outline-info">Export</button> -->
                        </div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>異動原因</th>
                            <th>異動點數</th>
                        </tr>
                        </thead>
                        <tbody class="code_tbody">
                        <c:forEach var="Point" items="${list}">
                            <tr>
                                <td>${Point.pointChange}</td>
                                <td>${Point.pointNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <canvas class="my-4 w-100" id="myChart" width="900" height="150">
                </canvas>
            </section>
            <br>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <nav aria-label="Page navigation example   justify-content-center" class="m-5 ">
                    <ul class="pagination">
                    </ul>
                </nav>
            </div>
        </main>
    </div>

</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(💰point)").closest("a").addClass("active disabled topage");
    $("a:contains(💰點數商城)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(🔆點數查詢)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    $(document).ready(function () {
        $("#pointgooditem").click(function () {
            //输入另一个页面的链接
            //我的跳转到controller中的toIntroduction这个方法中进行的页面跳转
            window.location.href = "pointItemPage.jsp";
        });
    });
</script>
</body>
</html>