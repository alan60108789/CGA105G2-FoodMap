<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<FORM METHOD="post" ACTION="food_order.do">
    <input type="hidden" name="action" value="Member_order_button">
    <input type="submit" value="會員訂位">
</FORM>
<FORM METHOD="post" ACTION="food_order.do">
    <input type="hidden" name="action" value="Member_order_button">
    <input type="hidden" name="foodorder_storeId" value="6">
    <input type="submit" value="詩傑-會員訂位">
</FORM>
<FORM METHOD="post" ACTION="food_order.do">
    <input type="hidden" name="action" value="listAllFoodOrder">
    <input type="submit" value="會員已預約">
</FORM>
<a href="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do?action=listAllFoodOrder">會員已預約</a>
<FORM METHOD="post" ACTION="food_order.do">
    <input type="hidden" name="action" value="listFoodOrderScore">
    <input type="submit" value="會員訂位紀錄">
</FORM>
<a href="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do?action=listFoodOrderScore">會員訂位紀錄</a>
</body>
</html>