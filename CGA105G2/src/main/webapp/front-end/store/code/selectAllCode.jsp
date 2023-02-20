<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    JSONArray list = (JSONArray) request.getAttribute("list_store");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>店家首頁</title>
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 mt-10 mb-10">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆總覽優惠券</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-info">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-info">Export</button>
                    </div>
                </div>
            </div>
            <div class="table-responsive" style="overflow-y:scroll;height:400px">
                <table class="table table-striped ">
                    <thead>
                    <tr>
                        <th></th>
                        <th>狀態</th>
                        <th>優惠碼</th>
                        <th>折扣</th>
                        <th>備註</th>
                        <th>期限</th>
                    </tr>
                    </thead>
                    <tbody class="code_tbody">
                    </tbody>
                </table>
            </div>
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
    $("#pageSubmenu4 a:contains(🔆總覽優惠券)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list = [
        <c:forEach var="code" items="${list_store}">
        ${code},
        </c:forEach>
    ];
    render(list);

    function render(list) {
        // 定義變數，在使用變數
        const codetbody = document.querySelector('.code_tbody');
        codetbody.innerHTML = '';
        const date = new Date();
        for (let item of list) {
            let a = null;
            let c = null;
            let d = null;
            let e = null;
            switch (item.CODE_STATUS) {
                case 1:
                    a = "can't use";
                    e = '審核中';
                    c = 'style="color:red !important;"';
                    d = 'disabled';
                    break;
                case 2:
                    if (new Date(item.CODE_NTIME) > date) {
                        a = 'send';
                        e = '已通過';
                    } else {
                        a = "can't use";
                        e = '已過期';
                        c = 'style="color:#D3D3D3 !important;"';
                        d = 'disabled';
                    }
                    break;
                case 3:
                    a = "can't use";
                    e = '已過期';
                    c = 'style="color:#D3D3D3 !important;"';
                    d = 'disabled';
                    break;
                case 4:
                    a = "can't use";
                    e = '未通過';
                    c = 'style="color:#D3D3D3 !important;"';
                    d = 'disabled';
                    break;
            }
            item.CODE_STATUS = e;
            codetbody.innerHTML += `
             <tr id="tr\${item.CODE_ID}">
              <td class="p-0" style="vertical-align:middle;">
                <form METHOD="post" ACTION="/CGA105G2/CodeServlet" >
                    <input type="hidden" name="action" value="send">
                    <input type="hidden" name="codeId" value=\${item.CODE_ID}>
                    <button class="btn btn-dark p-0 w-50" \${d} \${c} onclick="pass(\${item.CODE_ID})">\${a}</button>
                </form>
              </td>
              <td>\${item.CODE_STATUS}</td>
              <td>\${item.CODE_NUM}</td>
              <td>$\${item.CODE_OFF}</td>
              <td><p class="text-break">\${item.CODE_TEXT}</p></td>
              <td>\${item.CODE_NTIME}</td>
            </tr>`;
        }
    }

    function pass(id) {
        let i = list.map(item => Object.values(item)[0]).indexOf(id);
        alert(list[i].CODE_NUM);
    }
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
        let k = null;
        let z = null;
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
                title: '已發送' + i + "張優惠券",
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
                title: '您目前無會員追蹤，想發啥優惠券?',
                showConfirmButton: false,
                timer: 1500
            })
        }
    }

    let toResult = null;
    let comm = null;
    comm = <%= request.getAttribute("comm") %>;
    toResult =<%= request.getAttribute("ans") %>;
    if (toResult != null) {
        // alert(toResult);
        addCupAlert(toResult, comm);
        toResult = null;
    }
    ;
</script>
</body>
</html>