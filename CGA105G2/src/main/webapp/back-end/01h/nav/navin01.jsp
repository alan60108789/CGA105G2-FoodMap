<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li class="my-4">
                <a href="/CGA105G2/back-end/frontSelect/frontSelect.jsp">
                    <h1>Home</h1>
                </a>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻店家帳號</a>
                <ul class="collapse list-unstyled" id="pageSubmenu2">
                    <li>
                        <hr>
                        <a href="/CGA105G2/Member/StoreServlet?action=reviewStore" class="nav-link">🔆待審核</a>
                    </li>
                    <li><a href="/CGA105G2/Member/StoreServlet?action=StorePass" class="nav-link">🔆已審核</a></li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻店家廣告</a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr>
                        <a href="/CGA105G2/adServlet?action=rootTest" class="nav-link">🔆待審核</a>
                    </li>
                    <li><a href="/CGA105G2/back-end/advertise/adPass.jsp" class="nav-link">🔆已審核</a></li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu4" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻店家商品</a>
                <ul class="collapse list-unstyled" id="pageSubmenu4">
                    <li>
                        <hr>
                        <a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆待審核</a>
                    </li>
                    <li><a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆已審核</a></li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu5" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻店家優惠券</a>
                <ul class="collapse list-unstyled" id="pageSubmenu5">
                    <li>
                        <hr>
                        <a href="/CGA105G2/CodeServlet?action=reviewCoupon" class="nav-link">🔆待審核</a>
                    </li>
                    <li><a href="/CGA105G2/CodeServlet?action=CouponPass" class="nav-link">🔆已審核</a></li>
                    <hr>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
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


</html>
