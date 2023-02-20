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
            <li class="mb-5 mt-5">
                <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown">🔻員工資料</a>
                <ul class="collapse list-unstyled " id="pageSubmenu2">
                    <li>
                        <hr>
                        <a href="/CGA105G2/back-end/emp/updateEmp2.jsp" class="nav-link">🔆員工查詢</a>
                    </li>
                    <li>
                        <a href="/CGA105G2/back-end/emp/addEmp2.jsp" class="nav-link">🔆員工新增</a>
                    </li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="mb-5 mt-5">
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown">🔻員工權限</a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr>
                        <a href="/CGA105G2/back-end/emp/updateEmpAuthorization2.jsp" class="nav-link">🔆權限查詢</a>
                    </li>
                    <li><a href="/CGA105G2/back-end/emp/addEmpAuthorization2.jsp" class="nav-link">🔆新增權限</a>
                    </li>
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
