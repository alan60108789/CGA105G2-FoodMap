<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }
    </style>
</head>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li class="my-4">
                <a href="#pageSubmenu1" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">💰點數商城</a>
                <ul class="collapse list-unstyled" id="pageSubmenu1">
                    <li><hr><a href="${pageContext.request.contextPath}/PointServlet?action=listPoint" class="nav-link">🔆點數查詢</a></li>
                    <li><hr><a href="/CGA105G2/front-end/Member/point/listPointOrder.jsp" class="nav-link">🔆訂單查詢</a><hr></li>
                    <li><a href="/CGA105G2/front-end/Member/point/listPointGood.jsp" class="nav-link">🔆點數商品</a>
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
