<%@page import="com.member.model.Member.pojo.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🚪Sing in</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerout.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="container mb-17 p-4">
                <section class="shadow p-4" style="background-color: #2779e2; border-radius: 35px;">
                    <div class="container p-4">
                        <div class="row d-flex justify-content-center align-items-center h-100 p-4">
                            <div class="col-xl-9">
                                <h1 class="text-white text-center m-5">Forget Password</h1>
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
                                              action="${pageContext.request.contextPath}/LonginServlet"
                                              name="forget1">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">帳號名稱</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_ACC"
                                                           class="form-control form-control-lg"
                                                    />
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">更新密碼</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_PWD"
                                                           class="form-control form-control-lg"
                                                    />
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="row align-items-center pt-4 pb-3">
                                                <div class="col-md-3 ps-5">
                                                    <h4 class="mb-0">確認密碼</h4>
                                                </div>
                                                <div class="col-md-9 pe-5">
                                                    <input type="text" name="MEM_PWD2"
                                                           class="form-control form-control-lg"
                                                    />
                                                </div>
                                            </div>
                                            <hr class="mx-n3">
                                            <div class="px-5 py-4 ">
                                                <input type="hidden" name="action" value="forget1">
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
    $("a:contains(🚪Sing in)").closest("a").addClass("active disabled topage");
</script>
</body>
</html>