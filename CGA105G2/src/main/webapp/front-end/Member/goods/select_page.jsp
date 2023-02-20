<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods.model.Goods.pojo.*"%>
<%@ page import="com.goods.model.service.*"%>
<html>
<head>
<title>Goods : selsct_page</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>Goods:selsct_page</h3></td>
		</tr>
	</table>

	<p> FindByPrimaryKey(Goods)</p>

	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">		
	<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
	<li><a href='listAllGoods.jsp'>List</a> all Goods<br></li>
	</ul>
	
	<ul>
		<li>
			<FORM METHOD="post" ACTION="goods.do">
				<b>輸入商品編號 (如1):</b>
				 <input type="text" name="goodsId"> 
				 <input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="goodsSvc" scope="page"
			class="com.goods.model.service.GoodsService" />

		<li>
			<FORM METHOD="post" ACTION="goods.do">
				<b>選則商品名稱:</b> <select size="1" name="goodsId">
					<c:forEach var="goods" items="${goodsSvc.getAll()}">
						<option value="${goods.goodsId}">${goods.goodsName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	<h3>商品管理</h3>

<ul>
  <li><a href='addGoods.jsp'>Add</a> a new Goods.</li>
</ul>

</body>
</html>