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

        /* 商品名稱 */
        .fw-bolder {
            font-size: 1.3rem;
        }

        /* 兌換點數 */
        .lrp_text_count {
            font-weight: bold;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        }

        /* 商品圖片 */
        .card-img-top {
            height: 330px;
            width: 230px;
        }

        .col mb-5::after {
            position: absolute;
            content: "";
            top: 50%;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #444444;
            transform: translateY(-50%);
        }
        .bg-warning {
            background-color: pink !important;
        }
        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0px solid #CCCCFF;
        }

        th, td {
            padding: 1px;
        }

        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
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



<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li class="mb-5 mt-5">
                <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown">🔻訂單管理</a>
                <ul class="collapse list-unstyled " id="pageSubmenu2">
                    <li>
                        <hr><a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆待出貨訂單</a>
                    </li>
                    <li>
                        <a href="/CGA105G2/BlankPage/Loader1.jsp" class="nav-link">🔆訂單總覽</a>
                    </li>
                    <hr>
                    </li>
                </ul>
            </li>
            <li class="mb-5 mt-5">
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown">🔻商品管理</a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr><a href="/CGA105G2/back-end/pointgood/addPointGood.jsp" class="nav-link">🔆新增商品</a>
                    </li>
                    <li><a href="/CGA105G2/back-end/pointgood/listPointGood.jsp" class="nav-link ">🔆商品總覽</a></li>
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
