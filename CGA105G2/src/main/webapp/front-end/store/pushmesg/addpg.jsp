<%@ page import="com.code.model.Code.pojo.Code" %>
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <section class="section-content container py-10 mt-10 mb-10 card shadow" id="contacts"
                     style="border: 2px solid rgba(19, 6, 197, 0.089); border-radius: 30px;">
                <div class="col-12 col-lg-8 mb-14 mb-lg-0 container">
                    <h1 class="text-center mt-5">🔆新增推播訊息</h1>
                    <form class="row mt-17" METHOD="post" ACTION="/CGA105G2/pgServlet">
                        <div class="col-12 my-5">
                            <div class="form-group ">
                                <label for="pglab" class="form-label  p-0 fs-5 font-weight-bold">推播標題
                                    <p style="color: red">${errorMsgs.pglab}</p>
                                </label>
                                <input type="text" class="col-12 form-control" id="pglab"
                                       name="pglab"
                                       value="${pglab}">
                            </div>
                        </div>
                        <div class="col-12 my-5">
                            <div class="form-group">
                                <label for="pgtext" class="form-label fs-5 font-weight-bold">推播內容
                                    <p style="color: red">${errorMsgs.pgtext}</p>
                                </label>
                                <textarea class="form-control" rows="3" id="pgtext"
                                          name="pgtext"></textarea>
                            </div>
                            <div class="form-group mb-0">
                                <input type="hidden" name="action" value="addpg">
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
    $("a:contains(🔻推播管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu5").removeClass("collapse");
    $("#pageSubmenu5 a:contains(🔆新增推播訊息)").closest("a").addClass("active disabled bg-white topage");
</script>
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function addCupAlert(ans, i) {
        if (ans === true) {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-outline-primary m-5 fs-5',
                },
                buttonsStyling: false
            })
            swalWithBootstrapButtons.fire({
                position: 'middle',
                icon: 'success',
                title: '${errorMsgs.sendOK}' + i + '則訊息',
                showConfirmButton: false,
                timer: 1500
            })
        } else {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-outline-primary m-5 fs-5',
                },
                buttonsStyling: false
            })
            swalWithBootstrapButtons.fire({
                position: 'middle',
                icon: 'error',
                title: '${errorMsgs.sendO}',
                showConfirmButton: false,
                timer: 1500
            })
        }
    }

    let toResult =${ans};
    let comm =${comm};
    if (toResult != null) {
        // alert(toResult);
        addCupAlert(toResult, comm);
        toResult = null;
        comm = null;
    }
    ;
</script>
</body>

</html>