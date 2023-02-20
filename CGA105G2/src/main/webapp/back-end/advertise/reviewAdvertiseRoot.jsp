<%@ page import="com.advertise.model.service.AdvertiseService" %>
<%@ page import="com.advertise.model.Advertise.pojo.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>後台</title>
</head>
<body>
    <div id="page-start-anchor"></div>
  <!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
    <!-- main -->
    <div class="container-fluid">
        <div class="row">
          <%@ include file="/back-end/01h/nav/navin01.jsp" %>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
                <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">🔆待審核</h1>
                </div>
                <jsp:useBean id="adSvc" scope="page" class="com.advertise.model.service.AdvertiseService" />
                <jsp:useBean id="empSvc" scope="page" class="com.emp.model.service.EmployeeService" />
                <div class="table-responsive ">
                    <table class="table table-striped text-center ">	
                        <thead class="col-3">
                            <tr class="col-3">
                                <th>編號</th>
                                <th>店家</th>
                                <th>圖片</th>
                                <th>狀態</th>
                                <th>內容</th>
                                <th>新增時間</th>
                                <th>開始時間</th>
                                <th>到期時間</th>
                                <th></th>
                                <th></th>
                            
                            </tr>
                        </thead>
                        <tbody class="code_tbody col-3">
                        <c:forEach var="ad" items="${adSvc.status}" > 
                        <c:if test ="${ad.empId == 0}">
						<tr class="col-3">
							<td>${ad.advId}		</td>
							<td>${ad.storeId}	</td>
							<td><img src="<%=request.getContextPath()%>/adServlet?action=getPhoto&adId=${ad.advId}"
	                             style="max-width:60px;max-height:45px">
	                        </td>
							<td>${ad.advStatus}	</td>
							<td>${ad.advText}	</td>
							<td>${ad.advTime}	</td>
							<td>${ad.advStime}	</td>
							<td>${ad.advNtime}	</td>
							<td>
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/adServlet" style="margin-bottom: 0px;">
										
										 <select size="1" name="emp" id="selectEmp">
		                          			  <c:forEach var="emp" items="${empSvc.rootEmpBy}">
		                          			  <c:if test ="${emp.rootId!=1}">
		                           			  <option value="${emp.emp.empId}">${emp.emp.empAcc}
		                           			  </c:if>

			                                </c:forEach>
	                        			</select>

									<input type="hidden" name="action" value="forAd">
									<input type="hidden" name="advId" value="${ad.advId}">
									<input type="submit" value="送出">
								</FORM>
							</td>
						
						  </c:if>
						</c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <canvas class="my-4 w-100" id="myChart" width="900" height="200"></canvas>
            </main>
        </div>
    </div>



    <!-- main -->

 <!-- footer start -->
   <%@ include file="/back-end/01h/footerin.jsp" %>
    <!-- footer end -->
    <script>
        $("a:contains(✔️審核)").closest("a").addClass("active disabled topage");
        $("a:contains(🔻店家廣告)").closest("a").attr("aria-expanded", "true");
        $("#pageSubmenu3").addClass("show");
        $("#pageSubmenu3 a:contains(🔆待審核)").closest('a').addClass("active disabled bg-white topage");
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