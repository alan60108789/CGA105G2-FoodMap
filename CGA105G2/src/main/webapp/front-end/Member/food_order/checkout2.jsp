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
        <div class="container mt-17 mb-17">
            <div class="col-md-9  card shadow m-5">
                <h1 class=" m-5 text-center">付款方式</h1>
                <form id="idFormAioCheckOut" METHOD="post"
                      action="${pageContext.request.contextPath}/front-end/Member/food_order/food_order.do"
                      style="padding: 10px 100px;">
                    <input type="hidden" name="ServiceURL"
                           value="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5"
                           class="form-control"/>
                    <input type="hidden" name="MerchantTradeNo" value="oikidA0000001"
                           class="form-control"/>
                    <input
                            type="hidden" name="MerchantTradeDate" value="2017/06/30 00:00:00"
                            class="form-control"/>
                    <input
                            type="hidden" name="PaymentType" value="aio" class="form-control"/>
                    <input
                            type="hidden" name="TotalAmount" value="29999" class="form-control"/>
                    <input
                            type="hidden" name="TradeDesc" value="Desc" class="form-control"/>
                    <input
                            type="hidden" name="ItemName" value="A#B" class="form-control"/>
                    <input
                            type="hidden" name="ReturnURL"
                            value="http://tn.sly-ha.com.tw/demo/hoyo/ECPay.php"
                            class="form-control"/>
                    <input type="hidden" name="ChoosePayment" value="ALL"/>
                    <input type="hidden" name="action" value="ecpay"/>
                    <button type="submit" class="btn btn-warning mb-1 btn-block fs-5 mb-10">信用卡線上支付</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻訂位)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆訂位預約)").closest("a").addClass("active disabled bg-white topage");
</script>
</body>
</html>