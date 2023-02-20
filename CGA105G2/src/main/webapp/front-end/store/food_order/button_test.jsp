<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <FORM METHOD="post" ACTION="food_order.do" >
       <input type="hidden" name="action" value="food_order_button">
       <input type="submit" value="店家資訊修改">
    </FORM>
    <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=food_order_button">店家資訊修改</a>
    <FORM METHOD="post" ACTION="food_order.do" >
       <input type="hidden" name="action" value="storelistAllFoodOrderReserve">
       <input type="submit" value="店家已預約">
    </FORM>
    <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=storelistAllFoodOrderReserve">店家已預約</a>
    <FORM METHOD="post" ACTION="food_order.do" >
       <input type="hidden" name="action" value="storelistAllFoodOrder">
       <input type="submit" value="店家訂位總覽">
    </FORM>
    <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=storelistAllFoodOrder">店家訂位總覽</a>
</body>
</html>