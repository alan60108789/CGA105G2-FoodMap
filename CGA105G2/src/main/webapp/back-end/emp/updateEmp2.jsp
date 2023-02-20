<%@ page import="java.util.*" %>
<%@ page import="com.emp.model.Employee.dao.impl.*" %>
<%@ page import="com.emp.model.Employee.pojo.*" %>
<%@ page import="com.emp.model.service.EmployeeService" %>
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
                <h1 class="h2">🔆員工查詢</h1>

            </div>
            <% EmployeeService empSvc = new EmployeeService();
                List<Employee> list = empSvc.getAll();
                pageContext.setAttribute("list", list);%>
            <div class="table-responsive ">
                <table class="table table-striped text-center " id="table1" style="width:100%">
                    <thead class="col-3 ">
                    <tr class="col-3">
                        <td class="text-center">員工狀態</td>
                        <td class="text-center">員工編號</td>
                        <td class="text-center">員工帳號</td>
                        <td class="text-center">員工密碼</td>
                        <td class="text-center">員工職等</td>
                        <td class="text-center">新增日期</td>
                        <td class="text-center">修改日期</td>
                        <td class="text-center">修改</td>
                    </tr>
                    </thead>
                    <tbody class="code_tbody col-3">
                    <% Employee emp = new Employee(); %>
                    <% for (int i = 0; i < list.size(); i++) {
                        emp = list.get(i); %>

                    <tr class="col-3" >
                        <td>
                            <%=(emp.getEmpStatus() == 0) ? "在職" : "離職" %>
                        </td>
                        <td>
                            <%=emp.getEmpId()%>
                        </td>
                        <td>
                            <%=emp.getEmpAcc()%>
                        </td>
                        <td>
                            <%=emp.getEmpPwd()%>
                        </td>
                        <td>
                            <%=(emp.getEmpPer() == 0) ? "員工" : "主管" %>
                        </td>
                        <td>
                            <%=emp.getEmpTime()%>
                        </td>
                        <td>
                            <%=emp.getEmpRtime()%>
                        </td>
                        <td>
                            <FORM METHOD="post"
                                  ACTION="<%=request.getContextPath()%>/EmployeeServlet"
                                  style="margin-bottom: 0px;">
                                <input type="submit" value="修改"> <input
                                    type="hidden" name="empId"
                                    value=<%=emp.getEmpId()%>> <input
                                    type="hidden" name="action"
                                    value="getOne_For_Update">
                            </FORM>
                        </td>
                        <% } %>
                    </tr>
                    </tbody>
                </table>
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
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻員工資料)").closest("a").attr("data-toggle","show");
    $("#pageSubmenu2").removeClass("collapse");
    $("#pageSubmenu2 a:contains(🔆員工查詢)").closest("a").addClass("active disabled bg-white topage");
</script>

<script>

    document.getElementById('submit').onclick = function () {
        this.value = 'Processing…';
    }
</script>
<script>
    document.getElementById('status');
</script>
<script>
    const list=[];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list){
        switch (e){
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
	$(document).ready(function () {
    	$('#table1').DataTable({
   			 "info":false,
			 "lengthMenu": [5, 10, 15]
    	});
    });
</script>
</body>

</html>