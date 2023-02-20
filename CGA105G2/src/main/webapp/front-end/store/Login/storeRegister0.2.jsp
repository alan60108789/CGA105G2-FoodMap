<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🥙註冊</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerout.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid container w-100" id="topass">
    <main role="main" class="container">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 border-bottom text-center">
            <h1 class="h2 text-center mx-auto mt-5">🥙店家註冊</h1>
            <div class="btn-toolbar mb-md-0">
                <div class="btn-group mr-2">
                    <button type="button" class="btn btn-sm btn-outline-info">回上頁</button>
                </div>
            </div>
        </div>
        <section class="container">
            <div class="table-responsive section-content container" style="overflow-y:scroll;height:400px;">
                <table class="table table-striped ">
                    <thead>
                    <tr>
                        <th></th>
                        <th>店家名稱</th>
                        <th>縣市</th>
                        <th>行政區</th>
                        <th>店家地址</th>
                        <th>URL</th>
                    </tr>
                    </thead>
                    <tbody class="code_tbody">
                    </tbody>
                    <div>
                        <h1>${errorMsgs.error}</h1>
                    </div>
                </table>
            </div>
        </section>
    </main>
</div>
<a
        class="d-block btn btn-outline-danger  position-fixed position-bottom-10  position-right-10 text-center"
        href="#" data-toggle="smooth-scroll" data-target="#page-start-anchor"
        style="z-index: 9; border-radius: 50%;"> <i
        class="material-icons text-black ">arrow_upward</i>
</a>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🥙註冊)").closest("a").addClass("active disabled topage");
    const list = [
        <c:forEach var="code" items="${list_out}">
        ${code},
        </c:forEach>
    ];

    function render(list) {
        // 定義變數，在使用變數
        const codetbody = document.querySelector('.code_tbody');
        codetbody.innerHTML = '';
        for (let item of list) {
            codetbody.innerHTML += `
	               <tr id="tr\${item.STORE_ID}">
	                     <td class="col-1" style="vertical-align:middle;">
	                     	<Form METHOD="post" ACTION="/CGA105G2/LonginServlet">
		                      	<input type="hidden"  name="action" value="updatest1">
		                      	<input type="hidden"  name="STORE_ID" value="\${item.STORE_ID}">
		                        <button class="btn btn-dark p-0 w-25">✔</button>
	                        </Form>
	                      </td>
	                      <td class="col-3">\${item.STORE_NAME}</td>
	                      <td class="col-1">\${item.STORE_CITY}</td>
	                      <td class="col-1">\${item.STORE_DISTRICT}</td>
	                      <td class="col-3">\${item.STORE_ADDRESS}</td>
	                      <td class="col-3">\${item.STORE_URL}</td>
	                 </tr>`;
        }
    };
    render(list);
</script>
</body>
</html>