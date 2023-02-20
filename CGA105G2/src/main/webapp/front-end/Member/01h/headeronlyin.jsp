<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<!-- header start -->
<section class="header header-fixed-xl">
    <div class="header-main bg-warning">
        <div class="container" style="max-width: 1400px">
            <div class="row" style="max-width: 1400px">
                <nav class="navbar navbar-expand-lg navbar-light fs-md-6" id="header-navbar">
                    <!-- @*Navbar(白色)*@ -->
                    <div>
                        <a class="navbar-brand font-weight-bold" href="/CGA105G2/index.jsp">
                            <img src="/CGA105G2/assets/images/Logo.PNG" style="width: 100px; height: 100px" alt=""/></a>
                    </div>
                    <form METHOD="post" ACTION="/CGA105G2/LonginServlet" class="form-inline my-2 my-md-0 bg-white p-1 " style="border-radius: 30px;" >
                        <div class="single-icon" data-toggle="tooltip" title="" data-original-title="search"
                             style="border: 0; "><i
                                class="material-icons">search</i>
                        </div>
                        <input class="form-control " type="text" placeholder="Search" name="storeName"
                               style="border: 0; border-radius: 30px;">
                        <input type="hidden" name="action" value="byStoreName">
                    </form>
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/BlankPage/contactUs.jsp">
                                    📭聯繫我們
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/front-end/Member/art/listArt.jsp">
                                    🌟
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/front-end/Member/point/listPointGood.jsp">
                                    💰point
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/BlankPage/Loader2.jsp">
                                    🛒
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase " data-toggle="none" href="/CGA105G2/CodeServlet?action=memCodeAllU">🗃️管理
                                    <c:if test="${notify >0}">
                                    <button class="position-relative border-0 bg-warning">
                                        <span class="position-absolute position-bottom-1 position-left-0 rounded-pill badge bg-danger">
                                                ${notify}<span class="visually-hidden">unread messages</span>
                                        </span>
                                    </button>
                                    </c:if>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none"
                                   href="/CGA105G2/LonginServlet?action=out">
                                    🚪Sign out
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- header end -->
</body>
</html>
