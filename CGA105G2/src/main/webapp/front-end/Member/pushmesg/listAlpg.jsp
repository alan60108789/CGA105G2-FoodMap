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
    <title>📬店家訊息</title>
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
                <h1 class="h2">📬店家訊息</h1>
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
                        <th>標題</th>
                        <th>訊息</th>
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
    $("a:contains(🔻店家訊息)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu0").removeClass("collapse");
    $("#pageSubmenu0 a:contains(📬店家訊息)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list = JSON.parse(JSON.stringify(${list}))
    function render(list) {
        // 定義變數，在使用變數
        const codetbody = document.querySelector('.code_tbody');
        codetbody.innerHTML = '';
        for (let item of list) {
            codetbody.innerHTML += `
             <tr>
              <td class="col-2">
                   <form>
                        <input type="hidden" name="pgid" value=\${item.pgid}>
                        <button type="button" class="btn btn-dark p-0 ">Delet</button>
                   </form>
              </td>
              <td class="col-2">\${item.store}</td>
              <td class="col-2">\${item.lab}</td>
              <td class="col-6">\${item.text}</td>
            </tr>`;
        }
    }
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
    render(list);
</script>
<script>
    $(document).ready(function () {
        $("button").click(function () {
            let a=$(this).closest("form").find('input[name="pgid"]').val();
            // alert("觸發，val:"+a)
            var button = $(this);
            $.ajax({
                type:"POST",
                url:"/CGA105G2/pgServlet",
                data:{
                    action:"seeOK",
                    pgid:a,
                },
                success: function (data) {
                    button.closest('tr').remove();
                    let notifyNum = parseInt($('span.notify:first').text().trim());
                    let change=notifyNum-1;
                    const list=$('span.notify');
                    for (let i of list){
                        i.innerHTML=`\${change}`;
                        i.innerHTML+=`<span class="visually-hidden">unread messages</span>`;
                    }
                    if (change==0){
                        $('button.position-relative').remove();
                    }
                },
            })
        });
    });
</script>


</body>

</html>