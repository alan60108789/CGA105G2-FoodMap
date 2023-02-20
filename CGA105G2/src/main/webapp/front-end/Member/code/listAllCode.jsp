<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.json.simple.JSONArray" %>
<%
    JSONArray list = (JSONArray) request.getAttribute("list_memU");
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
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆有效優惠券</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                </div>
            </div>
            <div class="table-responsive ">
                <table class="table table-striped ">
                    <thead>
                    <tr>
                        <th></th>
                        <th>店家</th>
                        <th>折扣</th>
                        <th>優惠碼</th>
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
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻優惠券)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(🔆有效優惠券)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list = [
        <c:forEach var="code" items="${list_memU}">
        ${code},
        </c:forEach>
    ];

    function render(list) {
        // 定義變數，在使用變數
        const codetbody = document.querySelector('.code_tbody');
        codetbody.innerHTML = '';
        for (let item of list) {
            codetbody.innerHTML += `
             <tr>
              <td><button type="button" class="btn btn-dark p-0 " data-clipboard-action="copy" data-clipboard-target="#clipboardExample\${item.CODE_ID}">Copy</button></td>
              <td>\${item.STORE_NAME}</td>
              <td>$\${item.CODE_OFF}</td>
              <td id="clipboardExample\${item.CODE_ID}"> \${item.CODE_NUM} </td>
              <td>\${item.CODE_NTIME}</td>
            </tr>`;
        }
    }

    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
    render(list);
</script>
</body>

</html>