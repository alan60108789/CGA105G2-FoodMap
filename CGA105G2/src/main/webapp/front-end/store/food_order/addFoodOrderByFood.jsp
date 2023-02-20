<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodorder.model.*"%>
<%@ page import="com.foodorder.model.*"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.foodorder.model.Meal.pojo.Meal"%>
<%
	Meal mealVO = (Meal) request.getAttribute("mealVO");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <title>店家首頁</title>
</head>>
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
      <div class=" container p-4  mt-17" style="width: 800px;">
        <h1 class="text-center mb-3">新增餐點</h1>
        <hr>
        <div class="mx-5 mt-5 pt-5 jumbotron bg-light shadow">
          <div>
            <form METHOD="post" action="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do" class="p-4 ">
              <div class="form-group row">
                <label  class="fs-6 font-weight-bold">餐點</label>
                <input name="mealName" type="text" class="form-control" id="mealName" value="<%= (mealVO==null)? "" : mealVO.getMealName()%>">
                <c:if test="${not empty errorMealNameMsgs}">
					<p style="color:red"> ${errorMealNameMsgs} </p>
				</c:if>
              </div>
              <div class="form-group row">
                <label  class="fs-6 font-weight-bold">金額</label>
                <input name="mealPrice" type="text" class="form-control" id="mealPrice" value="<%= (mealVO==null)? "" : mealVO.getMealPrice()%>">
				<c:if test="${not empty errorMealPriceMsgs}">
					<p style="color:red"> ${errorMealPriceMsgs} </p>
				</c:if>
              </div>
              <input type="hidden" name="action" value="insert">
			  <input type="hidden" name="mealId" value="${mealVO.mealId}">
              <input class="form-group  btn btn-danger btn-block fs-5 mt-10" type="submit" value="新增" style="border-radius: 30px;">
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
  $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
  $("a:contains(🔻訂位管理)").closest("a").attr("data-toggle", "show");
  $("#pageSubmenu2").removeClass("collapse");
  $("#pageSubmenu2 a:contains(🔆餐點與時段)").closest("a").addClass("active disabled bg-white topage");
</script>
</body>
</html>