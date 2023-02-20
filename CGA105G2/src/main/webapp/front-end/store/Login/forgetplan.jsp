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
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="container mb-17 p-4">
                <section class="shadow p-4" style="background-color: #2779e2; border-radius: 35px;">
                    <div class="container p-4">
                        <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                            <div class="col-xl-9">
                                <h1 class="text-white text-center m-5">選擇方案</h1>
                                <div class="card" style="border-radius: 15px;">
                                    <div class="card-body p-4">
                                        <div class="container">
                                            <div class="card-deck mb-3 text-center">
                                                <div class="card mb-4 box-shadow">
                                                    <div class="card-header">
                                                        <h4 class="my-0 font-weight-normal">基礎方案</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <h2 class="card-title pricing-card-title">
                                                            $1,000 <small class="text-muted">/ 月</small>
                                                        </h2>
                                                        <ul class="list-unstyled mt-3 mb-4">
                                                            <li>開啟訂位/候位功能</li>
                                                            <li>開啟商城功能</li>
                                                            <li>開啟優惠券功能</li>
                                                            <li><br></li>
                                                        </ul>
                                                        <Form method="post"
                                                              action="${pageContext.request.contextPath}/LonginServlet">
                                                            <input type="hidden" name="action" value="ecpay">
                                                            <input type="hidden" name="planMoney" value=1000>
                                                            <input type="hidden" name="plan" value=1>
                                                            <button type="submit"
                                                                    class="btn btn-lg btn-block btn-primary align-bottom">
                                                                選擇此方案
                                                            </button>
                                                        </Form>
                                                    </div>
                                                </div>
                                                <div class="card mb-4 box-shadow">
                                                    <div class="card-header">
                                                        <h4 class="my-0 font-weight-normal">推播方案</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <h2 class="card-title pricing-card-title">
                                                            $5,000 <small class="text-muted">/ 月</small>
                                                        </h2>
                                                        <ul class="list-unstyled mt-3 mb-4">
                                                            <li>開啟訂位/候位功能</li>
                                                            <li>開啟商城功能</li>
                                                            <li>開啟優惠券功能</li>
                                                            <li style="color: red">開啟推播功能</li>
                                                        </ul>
                                                        <Form method="post"
                                                              action="${pageContext.request.contextPath}/LonginServlet">
                                                            <input type="hidden" name="action" value="ecpay">
                                                            <input type="hidden" name="planMoney" value=5000>
                                                            <input type="hidden" name="plan" value=2>
                                                            <button type="submit"
                                                                    class="btn btn-lg btn-block btn-primary align-bottom">
                                                                選擇此方案
                                                            </button>
                                                        </Form>
                                                    </div>
                                                </div>
                                                <div class="card mb-4 box-shadow">
                                                    <div class="card-header">
                                                        <h4 class="my-0 font-weight-normal">廣告方案</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <h2 class="card-title pricing-card-title">
                                                            $10,000<small class="text-muted">/ 月</small>
                                                        </h2>
                                                        <ul class="list-unstyled mt-3 mb-4">
                                                            <li>開啟訂位/候位功能</li>
                                                            <li>開啟商城功能</li>
                                                            <li>開啟優惠券功能</li>
                                                            <li style="color: red">開啟廣告功能</li>
                                                        </ul>
                                                        <c:if test="${plan3q > 0}">
                                                        <Form method="post"
                                                              action="${pageContext.request.contextPath}/LonginServlet">
                                                            <input type="hidden" name="action" value="ecpay">
                                                            <input type="hidden" name="planMoney" value=10000>
                                                            <input type="hidden" name="plan" value=3>
                                                            <button type="submit"
                                                                    class="btn btn-lg btn-block btn-primary align-bottom">
                                                                剩餘數量:${plan3q}<br>選擇此方案
                                                            </button>
                                                        </Form>
                                                        </c:if>
                                                        <c:if test="${plan3q == 0}">
                                                            <button type="submit"
                                                                    style="color: red"
                                                                    class="btn btn-lg btn-block btn-primary align-bottom">
                                                                剩餘數量:${plan3q}<br>已售完
                                                            </button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
</script>
</body>
</html>