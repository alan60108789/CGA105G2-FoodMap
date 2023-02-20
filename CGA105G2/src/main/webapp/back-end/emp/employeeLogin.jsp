<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>後台</title>
</head>
<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerout.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <main role="main" class="col-md-9 m-sm-auto col-lg-10 pl-md-4 shadow">
            <div class=" d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 mt-5 border-bottom">
                <h1 class="h2">🔆員工登入</h1>
            </div>
            <div class=" shadow card-body  rounded mb-20 bg-secondary " id="div11" style="border-radius: 30px;">
                <c:if test="${not empty errorMsgs}">
                    <c:forEach var="message" items="${errorMsgs}">
                        <span style="color:red">${message}</span>
                    </c:forEach>
                </c:if>
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/LonginServlet"
                      name="form1">
                    <div class="form-group ">
                        <label for="exampleInputname">員工帳號</label><br>
                        <span id="spanAcc" style="color:red"></span>
                        <input type="TEXT" id="inputAcc" name="empAcc" class="form-control" id="exampleInputname"
                               placeholder="name">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">員工密碼</label><br>
                        <span id="spanPwd" style="color:red"></span>
                        <input type="password" id="inputPwd" name="empPwd" class="form-control"
                               id="exampleInputPassword1"
                               placeholder="Password">
                    </div>
                    <input type="hidden" name="action" value="login">
                    <button type="submit" class="btn btn-outline-dark mb-1 " data-toggle="modal"
                            data-target="#exampleModalCenter" onclick="addEmpAlert()" disabled="disabled" id="submit">
                        Submit
                    </button>
                </form>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🚪Sign in)").closest("a").addClass("active disabled topage");
</script>
<script>
    var inputAcc = document.getElementById('inputAcc');
    var inputPwd = document.getElementById('inputPwd');
    inputAcc.onblur = function () {
        if (inputAcc.value === null || inputAcc.value === "") {
            document.getElementById('spanAcc').innerHTML = "*帳號請勿空白"
        } else if (inputAcc.value.length < 2 || inputAcc.value.length > 12) {
            document.getElementById('spanAcc').innerHTML = "*請輸入長度2~12位英文或數字"
        } else {
            document.getElementById('spanAcc').innerHTML = "	"
        }
        ;
    };
    inputPwd.oninput = function () {
        if (inputPwd.value === null || inputPwd.value === "") {
            document.getElementById('spanPwd').innerHTML = "*密碼請勿空白"
            $('#submit').attr('disabled', 'disabled');
        } else if (inputPwd.value.length < 2 || inputPwd.value.length > 12) {
            document.getElementById('spanPwd').innerHTML = "*請輸入長度2~12位英文或數字"
            $('#submit').attr('disabled', 'disabled');
        } else {
            document.getElementById('spanPwd').innerHTML = "	"
            $('#submit').removeAttr('disabled');
        }
        ;
    };
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
</body>
</html>