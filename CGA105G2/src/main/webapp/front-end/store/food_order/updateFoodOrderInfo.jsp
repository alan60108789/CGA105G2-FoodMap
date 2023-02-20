<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>店家首頁</title>
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
        <div class="col-md-9 mx-5 mt-5 pt-5 custom-table-width jumbotron"
             style="border-radius: 30px; background-color: ghostwhite;">
            <h1>餐點</h1>
            <% int i = 1;%>
            <table class="table table-striped table-sm" width="100%"
                   style="text-align:center; font-size: 20px; word-break:break-all; word-wrap:break-word;">
                <thead class="thead-light">
                <th scope="col" class="text-nowrap align-middle" width="5%"></th>
                <th scope="col" class="text-nowrap align-middle" width="10%">餐點編號</th>
                <th scope="col" class="text-nowrap align-middle" width="10%">餐點</th>
                <th scope="col" class="text-nowrap align-middle" width="10%">金額</th>
                <th scope="col" class="text-nowrap align-middle" width="30%">修改</th>
                <th scope="col" class="text-nowrap align-middle" width="30%">刪除</th>
                </thead>
                <tbody id="tbody_detail">
                <c:forEach var="mealVO" items="${list}">
                    <tr>
                        <th scope="row" class="text-nowrap align-middle"><%= i %>
                        </th>
                        <td id="mealid1" class="text-nowrap align-middle">${mealVO.mealId}</td>
                        <td id="meal1" class="text-nowrap align-middle">${mealVO.mealName}</td>
                        <td id="money1" class="text-nowrap align-middle">${mealVO.mealPrice}</td>
                        <td class="text-nowrap">
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do"
                                  style="margin-bottom: 0px;">
                                <input type="hidden" name="mealId" value="${mealVO.mealId}">
                                <input type="hidden" name="action" value="getOne_For_Update">
                                <input class="btn btn-danger btn-sm" type="submit" value="修改">
                            </FORM>
                        </td>
                        <td class="text-nowrap">
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do"
                                  style="margin-bottom: 0px;">
                                <input type="hidden" name="mealId" value="${mealVO.mealId}">
                                <input type="hidden" name="action" value="delete">
                                <input class="btn btn-danger btn-sm" type="submit" value="刪除">
                            </FORM>
                        </td>
                    </tr>
                    <% i = i + 1; %>
                </c:forEach>
                </tbody>
            </table>
            <form METHOD="post"
                  action="<%=request.getContextPath()%>/front-end/store/food_order/addFoodOrderByFood.jsp">
                <input class="btn btn-danger btn-sm" type="submit" value="新增">
            </form>
            <h1 class="mt-5">訂位資訊</h1>
            <div class="jumbotron pt-4 pb-4" style="background-color: lightgray; width: 400px;">
                <div>⏰開放時段 : <%= request.getAttribute("timestr") %>
                </div>
                <br>
                <div>🪑總桌數 : <%= request.getAttribute("tablecount") %>
                </div>
                <br>
                <div>🪑訂位上限 : <%= request.getAttribute("orderlimit") %>
                </div>
            </div>
            <form METHOD="post" action="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do">
                <input type="hidden" name="action" value="updateStoreSetting">
                <input class="btn btn-danger btn-sm" type="submit" value="修改">
            </form>
        </div>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻訂位管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu2").removeClass("collapse");
    $("#pageSubmenu2 a:contains(🔆餐點與時段)").closest("a").addClass("active disabled bg-white topage");
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
            title: '付款成功，請設定訂位資訊',
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