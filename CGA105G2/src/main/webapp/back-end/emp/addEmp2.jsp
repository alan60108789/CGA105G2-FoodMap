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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 shadow">
            <div class=" d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 mt-5 border-bottom">
                <h1 class="h2">🔆員工新增</h1>
            </div>
            <div class=" shadow card-body  rounded mb-20 bg-secondary " id="div11" style="border-radius: 30px;">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet" name="form1">
                    <div class="form-group ">
                        <label for="exampleInputname">員工帳號</label><br>
                        <span id="select2" style="color:red"></span>
                        <input type="TEXT" id="select1" name="acc" class="form-control" id="exampleInputname"
                               placeholder="name">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">員工密碼</label><br>
                        <span id="select4" style="color:red"></span>
                        <input type="password" id="select3" name="pwd" class="form-control" id="exampleInputPassword1"
                               placeholder="Password">
                    </div>
                    <input type="hidden" name="action" value="insert">
                    <button type="submit" class="btn btn-outline-dark mb-1 " data-toggle="modal"
                            data-target="#exampleModalCenter" onclick="addEmpAlert()">Submit
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
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻員工資料)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu2").removeClass("collapse");
    $("#pageSubmenu2 a:contains(🔆員工新增)").closest("a").addClass("active disabled bg-white topage");
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
<script>
    document.getElementById('select1').onblur = function () {
        if (select1.value === null || select1.value === "") {
            document.getElementById('select2').innerHTML = "*帳號請勿空白"
        } else if (select1.value.length < 2 || select1.value.length > 12) {
            document.getElementById('select2').innerHTML = "*請輸入長度2~12位英文或數字"
        } else {
            document.getElementById('select2').innerHTML = "	"
        }
        ;
    };
    document.getElementById('select3').onblur = function () {
        if (select3.value === null || select3.value === "") {
            document.getElementById('select4').innerHTML = "*密碼請勿空白"
        } else if (select3.value.length < 2 || select3.value.length > 12) {
            document.getElementById('select4').innerHTML = "*請輸入長度2~12位英文或數字"
        } else {
            document.getElementById('select4').innerHTML = "	"
        }
        ;
    };

</script>
</body>
</html>