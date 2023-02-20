<%@ page import="com.code.model.Code.pojo.Code" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Code code = (Code) request.getAttribute("Code");
%>
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <section class="section-content container py-10 mt-10 mb-10 card shadow" id="contacts"
                     style="border: 2px solid rgba(19, 6, 197, 0.089); border-radius: 30px;">
                <div class="col-12 col-lg-8 mb-14 mb-lg-0 container">
                    <h1 class="text-center mt-5">🔆新增優惠券</h1>
                    <%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color:red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color:red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <form class="row mt-17" METHOD="post" ACTION="/CGA105G2/CodeServlet" id="CodeServlet">
                        <div class="col-12 col-sm-6 my-5">
                            <div class="form-group">
                                <label for="CODE_NUM" class="form-label fs-5 font-weight-bold">優惠券碼</label>
                                <input type="text" class="form-control" id="CODE_NUM"
                                       name="codeNum"
                                       value="<%= (code==null)?  " " : code.getCodeNum()%>"/>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 my-5">
                            <div class="form-group">
                                <label for="CODE_OFF" class="form-label fs-5 font-weight-bold">折扣</label>
                                <input type="text" class="form-control" id="CODE_OFF"
                                       name="codeOff"
                                       value="<%= (code==null)?  " " : code.getCodeOff()%>"/>
                            </div>
                        </div>
                        <div class="col-12 my-5">
                            <div class="form-group ">
                                <label for="CODE_NTIME" class="form-label  p-0 fs-5 font-weight-bold">到期日期</label>
                                <input type="text" class="col-12 form-control" id="CODE_NTIME"
                                       name="codeNtime"
                                       value="<%= (code==null)?  " " : code.getCodeRtime()%>"/>
                            </div>
                        </div>
                        <div class="col-12 my-5">
                            <div class="form-group">
                                <label for="CODE_TEXT" class="form-label fs-5 font-weight-bold">備註</label>
                                <textarea class="form-control" rows="3" id="CODE_TEXT"
                                          name="codeText"
                                          value="<%= (code==null)?  " " : code.getCodeText()%>"></textarea>
                            </div>
                            <div class="form-group mb-0">
                                <input type="hidden" name="action" value="addCode">
                                <button class="btn btn-outline-warning btn-lg fs-10 container">送出</button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻優惠券管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu4").removeClass("collapse");
    $("#pageSubmenu4 a:contains(🔆新增優惠券)").closest("a").addClass("active disabled bg-white topage");
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
            title: '新增成功',
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