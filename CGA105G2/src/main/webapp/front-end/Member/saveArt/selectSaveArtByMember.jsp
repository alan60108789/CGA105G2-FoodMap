<%@ page import="java.util.List" %>
<%@ page import="com.followmem.model.FollowMem.pojo.FollowMem" %>
<%@ page import="com.followmem.model.service.FollowMemService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Integer memId1 = (Integer) request.getSession().getAttribute("memId");
    FollowMem followMem = new FollowMem();
    FollowMemService followMemService = new FollowMemService();
    List<FollowMem> list = followMemService.getAllByMemId1(memId1);
    pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <style>
        /* ==========追蹤button=============== */
        .button-like {
            transition: all ease 0.4s;
            cursor: pointer;
        }

        .button-like span {
            transition: all ease 0.4s;
        }

        .button-like:hover .aaa {
            color: #5273f7;
        }

        .liked {
            background-color: #5273f7;
            border-color: #5273f7;
        }

        .liked:focus {
            background-color: #5273f7;
        }

        .liked .aaa {
            color: #fefefe;
        }
    </style>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆追蹤會員</h1>
            </div>
            <!-- ==================我的最愛開始======================= -->
            <div class="row d-flex" style="margin-top: 40px;">
                <div class="col-12" style="display: flex;">
                    <div class="col-2"></div>
                    <div class="col-2 mr-10 ml-20">
                        <a href="/CGA105G2/front-end/Member/saveArt/selectSaveArtByStore.jsp">
                            <button type="button" class="btn btn-block btn-primary"
                                    style="height: 60px;font-size: 22px;">訂閱店家
                            </button>
                        </a>
                    </div>
                    <div class="col-2 mr-10">
                        <a href="/CGA105G2/front-end/Member/saveArt/selectSaveArtByMember.jsp">
                            <button type="button" class="btn btn-block btn-info active"
                                    style="height: 60px;font-size: 22px;">追蹤會員
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <!-- ==============button切換頁面結束=============== -->
            <ol style="list-style: none;">
                <!-- ==============表頭開始================== -->
                <li>
                    <div class="row mt-10">
                        <div class="col-2"></div>
                        <div class="col-7 border d-flex"
                             style="text-align: center; height: 50px;background-color: rgb(216, 233, 253);justify-content: center;line-height: 50px;font-size: 25px;font-weight: 800;">
                            會員名單
                        </div>
                    </div>
                </li>
                <!-- ==============表頭結束================== -->
                <% if (list == null || list.size() == 0) { %>
                <li>
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-7 border d-flex"
                             style="text-align: center; height: 100px;justify-content: center;line-height: 50px;font-size: 25px;font-weight: 800;line-height:100px;">
                            暫無追蹤名單
                        </div>
                    </div>
                </li>
                <%} else { %>
                <c:forEach var="followMem" items="${list}">
                    <li>
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-md-1 border">
                                <div class="position-relative snipimage"
                                     style="height: 100px;text-align: center;line-height: 100px;">
                                    <c:if test="${not empty followMem.member.memPic}">
                                        <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${followMem.member.memId}"
                                             style="width: 90%;height: 70%; border-radius: 60%;border: 1px solid rgb(255, 216, 87);">
                                    </c:if>
                                    <c:if test="${empty followMem.member.memPic}">
                                        <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                             alt=""
                                             style="width: 90%;height: 70%; border-radius: 60%;border: 1px solid rgb(255, 216, 87);">
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-md-5 border d-flex border-right-0">
                                <div class="mt-8">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <a href="/CGA105G2/LonginServlet?action=MemberPage&SearchMemberId=${followMem.member.memId}">
                                            <span class="mb-1"
                                                  style="font-size:22px;font-weight: 700;">${followMem.member.memName} </span>
                                        </a>
                                    </div>
                                    <div class="total_information" style="font-size: 17px;">
                                        <div>
                                            @${followMem.member.memAcc}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- =====================追蹤button開始=========================== -->

                            <div class="col-1 d-flex border border-left-0 align-items-center">
                                <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                                    <input type="hidden" name="memId1" value="${followMem.memId1}" id="memId1">
                                    <input type="hidden" name="memId2" value="${followMem.memId2}" id="memId2">
                                    <button class="button button-like" type="submit"
                                            style="border: 0cm;width: 100%;height: 45px;border-radius: 5px;font-weight: 600;">
                                        <span class="aaa">已追蹤</span>
                                    </button>
                                    <input type="hidden" name="action" value="deleteFollow">
                                </form>
                            </div>
                            <!-- =====================追蹤button結束=========================== -->
                        </div>
                    </li>
                </c:forEach>
                <%
                    }
                    ;
                %>
                <!-- =============表格結尾開始=============== -->
                <li>
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-7 border mb-10"
                             style="text-align: center; height: 30px;background-color: rgb(216, 233, 253);"></div>
                    </div>
                </li>
            </ol>
            <canvas class="my-4 w-100" id="myChart" width="900" height="100"></canvas>
        </main>
    </div>
</div>
<!-- =================我的最愛結束======================= -->
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🌟)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻我的收藏)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu1").removeClass("collapse");
    $("#pageSubmenu1 a:contains(🔆追蹤會員)").closest("a").addClass("active disabled bg-white topage");
</script>
<!-- ==========================button特效開始======================= -->
<script>
    const button = document.querySelectorAll('.button');
    for (let i = 0; i < button.length; i++) {
        button[i].addEventListener('click', function () {
            button[i].classList.toggle('liked')
        })
    }
    const aaa = document.querySelectorAll('.aaa');
    for (let i = 0; i < button.length; i++) {
        button[i].addEventListener('click', function () {
            if (aaa[i].innerHTML == "已追蹤")
                aaa[i].innerHTML = "追蹤";
            else
                aaa[i].innerHTML = "已追蹤";
        })
    }
</script>
<!-- ==========================button特效結束======================= -->
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
</body>
</html>