<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li>
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown">
                    <h3>🔻訂位</h3>
                </a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr><a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=storelistAllFoodOrder">🔆訂位總覽</a></li>
                    <li><a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=storelistAllFoodOrderReserve">🔆訂位預約</a>
                        <hr></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
