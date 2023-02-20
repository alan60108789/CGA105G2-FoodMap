<%@ page import="java.util.*" %>
<%@ page import="com.emp.model.Employee.dao.impl.*" %>
<%@ page import="com.emp.model.Employee.pojo.*" %>
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
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin03.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 border-bottom">
                <h1 class="h2">🔆員工資料</h1>
            </div>
            <form METHOD="post"
                  ACTION="<%=request.getContextPath()%>/back-end/emp/test">
                <div class="input-group">
                    <input type="search" class="form-control rounded"
                           placeholder="搜尋員工" aria-label="Search"
                           aria-describedby="search-addon" name="employee"/>
                    <input type="hidden" name="action" value="getOne">
                    <button type="submit" class="btn btn-outline-dark" data-mdb-ripple-color="dark">search</button>
                </div>
            </form>
            <%
                Employee employee = (Employee) request.getAttribute("employee"); //EmpServlet.java(Concroller), 存入req的empVO物件
            %>
            <div class="table-responsive ">
                <table class="table table-striped text-center ">
                    <thead class="col-3">
                    <tr class="col-3">
                        <td>員工狀態</td>
                        <td>員工編號</td>
                        <td>員工帳號</td>
                        <td>員工密碼</td>
                        <td>員工職等</td>
                        <td>新增日期</td>
                        <td>修改日期</td>
                        <td>修改</td>
                    </tr>
                    </thead>
                    <tbody class="code_tbody col-3">
                    <tr class="col-3">
                        <td><%=(employee.getEmpStatus() == 0) ? "在職" : "離職" %>
                        </td>
                        <td><%=employee.getEmpId()%>
                        </td>
                        <td><%=employee.getEmpAcc()%>
                        </td>
                        <td><%=employee.getEmpPwd()%>
                        </td>
                        <td><%=(employee.getEmpPer() == 0) ? "員工" : "主管" %>
                        </td>
                        <td><%=employee.getEmpTime()%>
                        </td>
                        <td><%=employee.getEmpRtime()%>
                        </td>
                        <td>
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/back-end/emp/test"
                                  style="margin-bottom: 0px;">
                                <input type="submit" value="修改">
                                <input type="hidden" name="empId" value=<%=employee.getEmpId()%>>
                                <input type="hidden" name="action" value="getOne_For_Update">
                            </FORM>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example"
                 class="d-flex justify-content-center" style="padding: 10px 0 25px">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="#">1</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻員工資料)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu2").removeClass("collapse");
    $("#pageSubmenu2 a:contains(🔆員工查詢)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
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
<script>
    document.getElementById('submit').onclick = function () {
        this.value = 'Processing…';
    }
</script>
</body>
</html>