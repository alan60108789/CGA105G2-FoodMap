<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    JSONArray list = (JSONArray) request.getAttribute("list_out");
    JSONArray empaccs = (JSONArray) request.getAttribute("empaccs");
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
        <%@ include file="/back-end/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2 mt-5">🔆待審核</h1>
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
                        <th>店號</th>
                        <th>統號</th>
                        <th>店名</th>
                        <th>縣市</th>
                        <th>地區</th>
                        <th>帳號</th>
                        <th>ID</th>
                        <th>姓名</th>
                        <th>電話</th>
                        <th>Mail</th>
                    </tr>
                    </thead>
                    <tbody class="store_tbody">
                    </tbody>
                    <div>
                        <h1>${errorMsgs.error}</h1>
                    </div>
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
    $("a:contains(✔️審核)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻店家帳號)").closest("a").attr("aria-expanded","true");
    $("#pageSubmenu2").addClass("show");
    $("#pageSubmenu2 a:contains(🔆待審核)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    let root =<%=request.getAttribute("root")%>;
    const storelist = [
        <c:forEach var="store" items="${list_out}">
        ${store},
        </c:forEach>
    ];
    const emplist = [
        <c:if test="${root == 1}">
        <c:forEach var="empaccs" items="${empaccs}">
        ${empaccs},
        </c:forEach>
        </c:if>
    ];
    function option(emplist, root) {
        if (root == 1) {
            const empselect = document.querySelectorAll('.empselect');
            for (let i of empselect) {
                i.innerHTML = '';
                for (let e of emplist) {
                    i.innerHTML += `<option value=\${e.EMP_ID} >\${e.EMP_ACC}</option>`;
                }

            }
        }
    }
    function render(list, root) {
        // 定義變數，在使用變數
        const store_tbody = document.querySelector('.store_tbody');
        store_tbody.innerHTML = '';
        if (root == 1) {
            for (let item of list) {
                store_tbody.innerHTML += `
                         <tr id="tr\${item.STORE_ID}">
                              <td class="p-0 col-2" style="vertical-align:middle;">
                                <Form METHOD="post" ACTION="/CGA105G2/Member/StoreServlet">
                                    <select class="btn btn-dark p-0 w-70 empselect" name="empId"></select>
                                    <input type="hidden"  name="storeId" value="\${item.STORE_ID}">
                                    <input type="hidden"  name="action" value="empTo">
                                    <button class="btn btn-dark p-0 w-25">✔</button>
                                </Form>
                              </td>
                              <td class="col-1">\${item.STORE_ID}</td>
                              <td class="col-1">\${item.STORE_COM_ID}</td>
                              <td class="col-2">\${item.STORE_NAME}</td>
                              <td class="col-1">\${item.STORE_CITY}</td>
                              <td class="col-1">\${item.STORE_DISTRICT}</td>
                              <td class="col-1">\${item.STORE_ACC}</td>
                              <td class="col-1">\${item.STORE_TW_ID}</td>
                              <td class="col-1">\${item.STORE_TO_NAME}</td>
                              <td class="col-1">\${item.STORE_PHONE}</td>
                              <td class="col-1">\${item.STORE_MAIL}</td>
                        </tr>`;
            }
        } else {
            for (let item of list) {
                store_tbody.innerHTML += `
                    <tr id="tr\${item.STORE_ID}">
                      <td class="p-0 col-2" style="vertical-align:middle;">
                         <Form METHOD="post" ACTION="/CGA105G2/Member/StoreServlet">
                            <select class="btn btn-dark p-0 w-70 " name="status">
                                <option value=2>通過</option>
                                <option value=0>不通過</option>
                            </select>
                              <input type="hidden"  name="storeId" value="\${item.STORE_ID}">
                              <input type="hidden"  name="action" value="storestat">
                              <button class="btn btn-dark p-0 w-25">✔</button>
                         </Form>
                      </td>
                      <td class="col-1">\${item.STORE_ID}</td>
                      <td class="col-1">\${item.STORE_COM_ID}</td>
                      <td class="col-2">\${item.STORE_NAME}</td>
                      <td class="col-1">\${item.STORE_CITY}</td>
                      <td class="col-1">\${item.STORE_DISTRICT}</td>
                      <td class="col-1">\${item.STORE_ACC}</td>
                      <td class="col-1">\${item.STORE_TW_ID}</td>
                      <td class="col-1">\${item.STORE_TO_NAME}</td>
                      <td class="col-1">\${item.STORE_PHONE}</td>
                      <td class="col-1">\${item.STORE_MAIL}</td>
                    </tr>`;
            }
        }
    }
    render(storelist, root);
    option(emplist, root);
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
</body>

</html>