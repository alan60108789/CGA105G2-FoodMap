<%@ page import="java.util.*" %>
<%@ page import="com.emp.model.Employee.dao.impl.*" %>
<%@ page import="com.emp.model.EmployeeRoot.pojo.*" %>
<%@ page import="com.emp.model.Employee.pojo.*" %>
<%@ page import="com.emp.model.service.EmployeeService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>後台</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap4.min.css">
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
                <h1 class="h2">🔆權限查詢</h1>
            </div>
            <%
                EmployeeService empSvc = new EmployeeService();
                List<EmployeeRoot> list = empSvc.getRootAll();
                pageContext.setAttribute("list", list);
            %>
            <div class="table-responsive ">
                <table id="table1" class="table table-striped  text-center" style="width:100%">
                    <thead class="col-3 text-center">
                    <tr class="col-3">
                        <td class=" text-center">員工帳號</td>
                        <td class=" text-center">員工權限</td>
                        <td class=" text-center">刪除</td>
                    </tr>
                    </thead>
                    <tbody class="code_tbody col-3">
                    <c:forEach var="empRoot" items="${list}">
                        <tr class="col-3">
                            <td>${empRoot.emp.empAcc}</td>
                            <td>${empRoot.root.rootText}</td>
                            <td>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet"
                                      style="margin-bottom: 0px;">
                                    <input type="submit" value="刪除">
                                    <input type="hidden" name="rootId" value="${empRoot.rootId}">
                                    <input type="hidden" name="empId" value="${empRoot.empId}">
                                    <input type="hidden" name="action" value="deleteRoot">
                                </FORM>
                            </td>
                        </tr>
                    </c:forEach>
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
    $("a:contains(🔻員工權限)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆權限設定)").closest("a").addClass("active disabled bg-white topage");
</script>
<!-- DataTable -->
<script>
    $(document).ready(function () {
        $('#table1').DataTable({
            "info": false,
            "lengthMenu": [5, 10, 15]
        });
    });
</script>
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function addEmpAlert() {
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
</body>
</html>