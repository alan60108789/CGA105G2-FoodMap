<%@ page import="com.emp.model.Employee.pojo.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Employee employee = (Employee) request.getAttribute("employee"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 shadow">
            <div class=" d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 mt-5 border-bottom">
                <h1 class="h2">🔆員工資料修改</h1>
            </div>
            <div class=" shadow card-body  rounded mb-20 bg-secondary " id="div11" style="border-radius: 30px;">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet" name="form1">
                    <div class="form-group ">
                        <label>員工編號</label><br>
                        <p><%=employee.getEmpId()%>
                        </p>
                    </div>
                    <div class="form-group">
                        <label>員工狀態</label><br>
                        <td>
                            <select size="1" name="empstatus">
                                <option value="<%=employee.getEmpStatus()%>"><%=(employee.getEmpStatus() == 0) ? "在職" : "離職"%>
                                </option>
                                <option value="<%=(employee.getEmpStatus() == 0) ? "1" : "0"%>"><%=(employee.getEmpStatus() == 1) ? "在職" : "離職"%>
                                </option>
                            </select>
                        </td>
                    </div>
                    <div class="form-group">
                        <label>員工帳號</label><br>
                        <input type="TEXT" name="empacc" value=<%=employee.getEmpAcc()%>>
                    </div>
                    <div class="form-group">
                        <label>員工密碼</label><br>
                        <input type="TEXT" name="emppwd" value=<%=employee.getEmpPwd()%>>
                    </div>
                    <div class="form-group">
                        <label>員工權限</label><br>
                        <td><select size="1" name="empper">
                            <option value="<%=employee.getEmpPer()%>"><%=(employee.getEmpPer() == 0) ? "員工" : "主管"%>
                            </option>
                            <option value="<%=(employee.getEmpPer() == 0) ? "1" : "0"%>"><%=(employee.getEmpPer() == 1) ? "員工" : "主管"%>
                            </option>
                        </select>
                        </td>
                    </div>
                    <div class="form-group">
                        <label>新增時間</label><br>
                        <p><%=employee.getEmpTime()%>
                        </p>
                    </div>
                    <div class="form-group">
                        <label>修改時間</label><br>
                        <input type="TEXT" name="emprtime" id="time" value=" "></input>
                    </div>
                    <input type="hidden" name="emptime" value="<%=employee.getEmpTime()%>">
                    <input type="hidden" name="empid" value="<%=employee.getEmpId()%>">
                    <input type="hidden" name="action" value="update">
                    <button type="submit" class="btn btn-outline-dark mb-1 "
                            data-toggle="modal" data-target="#exampleModalCenter"
                            onclick="addEmpAlert()">Submit
                    </button>
                </form>
            </div>
        </main>
    </div>
</div>
</main>
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

    var Today = new Date();
    console.log(Today.getMonth());
    var inp = document.getElementById("time");
    inp.setAttribute("value", Today.getFullYear() + "-" + (Today.getMonth() + 1)
        + "-" + Today.getDate());
</script>
</body>
</html>