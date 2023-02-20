<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emp.model.service.EmployeeService" %>
<%@ page import="com.emp.model.Root.pojo.*" %>
<%@ page import="com.emp.model.EmployeeRoot.pojo.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>IBM Emp: Home</title>
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
<% List<EmployeeRoot> list = (List<EmployeeRoot>) request.getAttribute("list");%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/test">
    <b>選擇員工</b>
    <select size="1" name=getRootID>
        <% EmployeeRoot employeeRoot = new EmployeeRoot(); %>
        <% for (int i = 0; i < list.size(); i++) {
            employeeRoot = list.get(i); %>
        <option value=<%=employeeRoot.getRootId()%>> <%=employeeRoot.getEmpId()%>
                <%} %>
    </select>
    <input type="hidden" name="action" value="getRoot">
    <input type="submit" value="確定">
</FORM>
</body>
</html>