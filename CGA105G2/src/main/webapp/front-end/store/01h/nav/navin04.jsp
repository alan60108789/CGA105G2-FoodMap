<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li class="my-3">
                <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=food_order_button">
                    <h1>Home</h1>
                </a>
            </li>
            <li class="my-3">
                <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown fs-5">🔻訂位管理</a>
                <ul class="list-unstyled collapse" id="pageSubmenu2">
                    <li>
                        <hr>
                        <a href="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do?action=food_order_button" class="nav-link">🔆餐點與時段</a>
                    </li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="my-3">
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown fs-5">🔻商城管理</a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr>
                        <a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆新增商品</a>
                    </li>
                    <li><a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆總覽商品</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <li class="my-3">
                <a href="#pageSubmenu4" data-toggle="collapse" aria-expanded="false"
                   class="dropdown fs-5">🔻優惠券管理</a>
                <ul class="collapse list-unstyled" id="pageSubmenu4">
                    <li>
                        <hr>
                        <a href="/CGA105G2/front-end/store/code/addCode.jsp" class="nav-link">🔆新增優惠券</a>
                    </li>
                    <li>
                        <a href="/CGA105G2/CodeServlet?action=storeCodeAll" class="nav-link">🔆總覽優惠券</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <c:if test="${storeplan >= 2}">
            <li class="my-3">
                <a href="#pageSubmenu5" data-toggle="collapse" aria-expanded="false" class="dropdown fs-5">🔻推播管理</a>
                <ul class="collapse list-unstyled" id="pageSubmenu5">
                    <li>
                        <hr>
                        <a href="/CGA105G2/front-end/store/pushmesg/addpg.jsp" class="nav-link">🔆新增推播訊息</a>
                        <hr>
                    </li>
                </ul>
            </li>
            </c:if>
            <li class="my-3">
                <a href="#pageSubmenu6" data-toggle="collapse" aria-expanded="false" class="dropdown fs-5">🔻方案管理</a>
                <ul class="collapse list-unstyled" id="pageSubmenu6">
                    <li>
                        <hr>
                        <a href="/CGA105G2/front-end/store/Login/planchang.jsp" class="nav-link">🔆方案查詢</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <li class="my-3">
                <a href="#pageSubmenu7" data-toggle="collapse" aria-expanded="false" class="dropdown fs-5">🔻帳號管理
                </a>
                <ul class="collapse list-unstyled" id="pageSubmenu7">
                    <li>
                        <hr>
                        <a href="/CGA105G2/Member/StoreServlet?action=searchinfo" class="nav-link">🔆帳號設定</a>
                    </li>
                    <li><a href="/CGA105G2/front-end/store/Login/changepwd2.jsp" class="nav-link">🔆變更密碼</a>
                        <hr>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
