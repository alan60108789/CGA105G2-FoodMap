<%@ page import="com.member.model.Member.pojo.Member" %>
<%@ page import="com.member.model.service.MemberService" %>
<%@ page import="com.art.model.service.ArtService" %>
<%@ page import="com.art.model.Article.pojo.Article" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"/>
    <script src="https://kit.fontawesome.com/2c6d23848b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <!--     下面那條不能刪 -->
    <link rel="stylesheet" href="/CGA105G2/assets/css/profile.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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

        /* ==========收藏button=============== */
        button.liked {
            color: #0571ed;
        }

        button.liked i {
            animation: anim 0.5s ease-in-out;
            -webkit-animation: anim 0.5s ease-in-out;
        }

        @keyframes anim {
            100% {
                transform: rotate(-15deg) scale(1.3);
                -webkit-transform: rotate(-15deg) scale(1.3);
                -moz-transform: rotate(-15deg) scale(1.3);
                -ms-transform: rotate(-15deg) scale(1.3);
                -o-transform: rotate(-15deg) scale(1.3);
                filter: blur(0.5px);
                -webkit-filter: blur(0.5px);
            }
        }

        .fa-heart-o {
            color: red;
            cursor: pointer;

        }

        .fa-heart {
            color: red;
            cursor: pointer;

        }

        .friend {
            background: #eee;
            float: left;
        }

        .me {
            float: right;
            background: #0084ff;
            color: #fff;
            border-bottom-right-radius: 30px;
        }

        .friend + .me {
            border-bottom-right-radius: 5px;
        }

        .me + .me {
            border-top-right-radius: 10px;
            border-bottom-right-radius: 5px;
        }

        .me:last-of-type {
            border-bottom-right-radius: 30px;
        }

        #message {
            min-width: 50%;
            max-width: 60%;
        }


        .message-area {
            height: 70%;
            resize: none;
            box-sizing: border-box;
            overflow: auto;
            background-color: #ffffff;
        }

        .clearliststy {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .messagetoli {
            display: inline-block;
            clear: both;
            padding: 8px;
            border-radius: 30px;
            margin-bottom: 2px;
            font-family: Helvetica, Arial, sans-serif;
        }

        .rowmmmm {
            display: flex;
            flex-wrap: nowrap;

        }

        .playgames {
            margin: 0 auto;
            text-align: center;
            height: auto;
            width: auto;

        }


        .playgames .contentmmm {
            display: inline-block;
            margin: 0 auto;
            top: 10%;
            position: relative;

        }

        .playgames > .rowmmmm {
            display: flex;
        }

        .playgames .cell {
            width: 200px;
            height: 200px;
            border: 5px solid black;
            line-height: 0;
        }


    </style>
</head>
<body onunload="disconnect()">
<c:if test="${memId > 0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
    <!-- header end -->
</c:if>
<c:if test="${memId == null||memId <=0}">
    <!-- header start -->
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
    <!-- header end -->
</c:if>
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <c:if test="${memId > 0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
            <!-- nav end -->
        </c:if>
        <c:if test="${memId == null||memId <=0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin00.jsp" %>
            <!-- nav end -->
        </c:if>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4  bg-cyan-20">
            <div class="container my-5">
                <div class="profile-header">
                    <div class="profile-header-cover"
                         style="background: url(../webapp/assets/images/ex2.jpg);"></div>
                    <div class="profile-header-content">
                        <div class="profile-header-img mb-1">
                            <c:if test="${not empty member2.memPic}">
                                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}"
                                     alt="" style="width:100%;height:100%;"/>
                            </c:if>
                            <c:if test="${empty member2.memPic}">
                                <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                     alt=""/>
                            </c:if>
                        </div>
                        <div class="profile-header-info">
                            <div style="display: flex;">
                                <h3 class="m-t-sm mt-5" id="row"
                                    style="font-weight: 1000;font-size: 33px;">${member2.memName}</h3>
                                <p class="m-t-sm mt-7 ml-4" style="color: rgb(215, 235, 68);">@${member2.memAcc}</p>
                            </div>
                            <div style="display:flex;">
                                <c:if test="${followlist.size() == 0}">
                                    <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                                        <input type="hidden" name="memId1" value="${member1.memId}">
                                        <input type="hidden" name="memId2" value="${member2.memId}">
                                        <button type="submit" class="btn btn-sm btn-primary mb-4"
                                                style="font-size: 17px;">追蹤會員
                                        </button>
                                        <input type="hidden" name="action" value="insertFollow">
                                        <input type="hidden" name="SearchMemberId" value="${member2.memId}">
                                    </form>
                                </c:if>
                                <c:if test="${followlist.size() != 0}">
                                    <form method="post" action="/CGA105G2/MyFavoriteServlet" name="">
                                        <input type="hidden" name="memId1" value="${member1.memId}">
                                        <input type="hidden" name="memId2" value="${member2.memId}">
                                        <button type="submit" class="btn btn-sm btn-warning mb-4"
                                                style="font-size: 17px;">取消追蹤
                                        </button>
                                        <input type="hidden" name="action" value="deleteFollowbyPage">
                                        <input type="hidden" name="SearchMemberId" value="${member2.memId}">
                                    </form>
                                </c:if>
                                <button type="button" class="btn btn-sm btn-primary mb-4 ml-5" data-toggle="modal"
                                        data-target="#exampleModalCenter" id="imissyou" style="font-size: 17px;">
                                    聊天室
                                </button>
                                <!-- Modal -->
                                <div class="modal fade"
                                     id="exampleModalCenter" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content ">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="statusOutput" class="statusOutput"
                                                    style="color: black">${member2.memName}</h5>
                                                <p style="color: rgb(68,140,235);" id="ststemg">:未上線</p>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body  message-area "
                                                 style="color: black;overflow-y: auto;height: 50vh;"
                                                 id="messagesArea"></div>
                                            <div class="modal-footer">
                                                <div class="col-12 flex-nowrap p-0 m-0">
                                                    <input id="message" class="col-5" style="height:35px" type="text"
                                                           aria-label="Search"
                                                           placeholder="Message"
                                                           onkeydown="if(event.keyCode == 13)sendMessage()" ;>
                                                    <input type="submit" id="sendMessage"
                                                           class="button col-2 btn btn-primary p-0" value="Send"
                                                           onclick="sendMessage()" ;
                                                           style="height:35px;background-color: #ffc107; ">
                                                    <input type="button" id="connect"
                                                           class="button col-2 btn btn-primary p-0" value="上線"
                                                           onclick="connect()"
                                                           style="height:35px;background-color: #ffc107; ">
                                                    <input type="button" id="disconnect" aria-hidden="true"
                                                           class="button col-2 btn btn-primary p-0" value="下線"
                                                           onclick="disconnect()"
                                                           style="height:35px;background-color: #ffc107; ">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-sm btn-primary mb-4 ml-5" data-toggle="modal"
                                        data-target="#exampleModalCenter1" style="font-size: 17px;">遊戲室
                                </button>
                                <div class="modal fade"
                                     id="exampleModalCenter1" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered playgames" role="document">
                                        <div class="modal-content mx-auto">
                                            <%@ include file="/front-end/Member/member/hitgame.jsp" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container bg-white mt-10 p-8">
                    <div class="row">
                        <div class="col-md-12" style="height: 100px;font-size: 20px;font-weight: 800;margin-top: 5px;">
                            自我簡介:
                            <div>
                                ${member.memText}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- POST1 -->
                <!-- 這邊要開始for each -->
                <c:if test="${list.size() == 0 }">
                    <div class="container bg-white mt-10 p-8">
                        <div class="row">
                            <div class="col-md-12"
                                 style="height: 100px;font-size: 40px;font-weight: 800;margin-top: 10px;text-align: center;line-height: 100px;color: rgb(91, 91, 91);">
                                此會員目前暫無貼文
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${list.size() != 0 }">
                    <c:forEach var="article" items="${list}">
                        <div class="container bg-white mt-10 p-8">
                            <div class="row">
                                <div class="col-md-12" style="font-size: 20px;font-weight: 800;margin-top: 5px;">
                                    <!-- ====個人圖片==== -->
                                    <div class="postmember_info" style="display: flex;">
                                        <div class="postmember_img">
                                            <c:if test="${not empty member2.memPic}">
                                                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}"
                                                     style="width:70px ; height:65px;border-radius: 80%;border: 1px solid rgb(255, 216, 87);">
                                            </c:if>
                                            <c:if test="${empty member2.memPic}">
                                                <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                                     alt=""
                                                     style="width:70px ; height:65px;border-radius: 80%;border: 1px solid rgb(255, 216, 87);">
                                            </c:if>
                                        </div>
                                        <div class="postmember_text mt-3" style="margin-left: 5px;line-height: 26px;">
                                        <span class="postmember_name" style="font-size: 25px;">
                                                ${member2.memName}
                                        </span>
                                            <div>
                                                <p style="font-size: 14px;color: rgb(104, 104, 104);"><fmt:formatDate
                                                        value="${article.artTime}" pattern="yyyy-MM-dd"/></p>
                                            </div>
                                        </div>
                                        <!-- ==================評分跟店家頭像===================== -->
                                        <div class="ml-5" style="margin-top: 14px;">
                                        <span style="font-size: 20px;padding: 5px 15px;border-radius:15px ;background-color: rgb(255, 112, 60);">
                                        ${article.artScore} <i class="fa-solid fa-star"
                                                               style="color: rgb(249, 249, 106);"></i>
                                        </span>
                                        </div>
                                        <div class="poststore_info ml-8" style="display: flex;">
                                            <div class="poststore_img">
                                                <img src="/CGA105G2/assets/images/ex1.jpg"
                                                     style="width:65px ; height:60px;border: 1px solid rgb(255, 216, 87);">
                                            </div>
                                            <div class="poststore_text"
                                                 style="margin-left: 5px;align-items: center;display: flex;">
                                                <a href="${pageContext.request.contextPath}/LonginServlet?action=StorePage&SearchstoreId=${article.store.storeId}">
                                            <span class="post_name" style="font-size: 30px;font-weight: 1000;">
                                                    ${article.store.storeName}
                                            </span>
                                                </a>
                                            </div>
                                        </div>
                                        <!-- ===============評分跟店家頭像和form表單在這============================ -->
                                    </div>
                                    <!-- ====個人圖片結束==== -->
                                    <h2 class="mt-6" style="font-weight: 1000;">${article.artHeader}</h2>
                                    <div>
                                        <p>${article.artText}</p>
                                        <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberArticlePhoto&artId=${article.artId}"
                                             style="max-width:600px;max-height:450px">
                                    </div>
                                    <ul class="profile-header-tab nav nav-tabs mt-5">
                                        <li class="nav-item">
                                            <a class="todeepage"
                                               href="${member2.memName}評論了${article.store.storeName}店：${article.artHeader} http://tibame.likktw.com:8081/CGA105G2/LonginServlet?action=MemberPage&SearchMemberId=${member2.memId}">
                                                <button class=" btn btn-outline-primary align-items-center"
                                                        style="height: 46px; padding: 5px; border-radius: 0%;font-size: 20px;font-weight: 1000">
                                                    <i class="material-icons">share</i>
                                                    分享到Line
                                                </button>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <!--這邊要結束for each -->
            </div>
            <!-- =================我的最愛結束======================= -->
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<script src="/assets/js/vendor.js"></script>
<script src="/assets/js/polyfills.js"></script>
<script src="/assets/js/app.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<script>

    function liked() {
        const element = document.getElementById("like");
        element.classList.toggle("liked");
    }

    $(document).ready(function () {
        $("#heart").click(function () {
            if ($("#heart").hasClass("liked")) {
                $("#heart").html('<i class="fa fa-heart-o m-5" aria-hidden="true"><span class="icon ml-2">收藏</span></i>');
                $("#heart").removeClass("liked");
            } else {
                $("#heart").html('<i class="fa fa-heart m-5" aria-hidden="true" ><span class="icon ml-2">收藏</span></i>');
                $("#heart").addClass("liked");
            }
        });
    });
</script>
<!-- ==========================button特效開始======================= -->
<script>
    const button = document.querySelectorAll('.button');
    for (let i = 0; i < button.length; i++) {
        button[i].addEventListener('click', function () {
            button[i].classList.toggle('liked')
        })
    }
</script>
<!-- ==========================button特效結束======================= -->
<script>
    function addCupAlert() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-outline-primary m-5 fs-5',
            },
            buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
            position: 'middle',
            icon: 'success',
            title: '遊戲獎勵1點',
            showConfirmButton: false,
            timer: 1500
        })
    }

    let toResult = null;
    toResult =
    <%= request.getAttribute("toResult") %>;
    if (toResult == true) {
        addCupAlert();
        toResult = null;
    }
    ;
</script>
<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>


<script>

    <%--======================    聊天室  ==================--%>
    const host = window.location.host;//localhost
    const path = window.location.pathname;//8081
    const webCtx = path.substring(0, path.indexOf('/', 1));//CGA105G2
    const MyPoint = "/FriendWS/${member1.memName}";
    const URL = "ws://" + host + webCtx + MyPoint;
    const endPointURL = URL.trim();
    connect();
    const statusOutput = $("#statusOutput");
    const messagesArea = $("#messagesArea");
    const self = '${member1.memName}';   //self是自己的意思
    let friend = '${member2.memName}';
    var webSocket;

    ///載入區
    function connect() {
        // create a websocket
        webSocket = new WebSocket(endPointURL);
        webSocket.onopen = function (event) {
            $("#sendMessage").prop("disabled", false);
            $("#disconnect").prop("disabled", false);
        };
        webSocket.onmessage = function (event) {
            const jsonObj = JSON.parse(event.data);
            if ("open" === jsonObj.type) {
                addListener();
            } else if ("history" === jsonObj.type) { //歷史聊天紀錄
                messagesArea.html("");
                const ul = $('<ul>', {
                    id: 'area',
                    class: 'clearliststy',
                    text: ''
                });
                messagesArea.append(ul);
                // 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
                const messages = JSON.parse(jsonObj.message);
                for (let i = 0; i < messages.length; i++) {
                    const historyData = JSON.parse(messages[i]);
                    const showMsg = historyData.message;
                    const li = $('<li>', {
                        id: '',
                        class: 'messagetoli',
                        text: ''
                    });
                    // 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
                    historyData.sender === self ? li.addClass("me") : li.addClass("friend"); //去看css style 有分左右聊天
                    li.html(showMsg);
                    ul.append(li);
                }
                messagesArea.scrollTop = messagesArea.scrollHeight;
            } else if ("chat" === jsonObj.type) {	//線上的聊天
                $("#ststemg").html(":已上線");
                const li = $('<li>', {
                    id: '',
                    class: 'messagetoli',
                    text: ''
                });
                jsonObj.sender === self ? li.addClass("me") : li.addClass("friend"); //去看css style 有分左右聊天
                li.html(jsonObj.message);
                $("#area").append(li);
                messagesArea.scrollTop = messagesArea.scrollHeight;
            } else if ("close" === jsonObj.type) {
                addListener();
            }
        };
        window.webSocket.onclose = function (event) {
            console.log("Disconnected!");
        };
    }

    //結束


    // 註冊列表點擊事件並抓取好友名字以取得歷史訊息
    function addListener() {
        const container = $("#imissyou");
        container.click(function (e) {
            const jsonObj = {
                "type": "history", //歷史聊天紀錄
                "sender": self,	//自己
                "receiver": friend, //對象
                "message": ""		//先給空字串     //ChatMessage.java要對應
            };
            window.webSocket.send(JSON.stringify(jsonObj));	//轉成文字 用Websocket發送到後台
        })
    };

    function sendMessage() {
        friend = statusOutput.html();
        const inputMessage = $("#message");
        const message = inputMessage.val().trim();
        if (message === "") {

            inputMessage.focus();
        } else {
            var jsonObj = {
                "type": "chat",
                "sender": self,
                "receiver": friend,
                "message": message
            };
            window.webSocket.send(JSON.stringify(jsonObj));
            inputMessage.val("");
            inputMessage.focus();
        }
    }

    function disconnect() {
        webSocket.close();
        $("#sendMessage").prop("disabled", true);
        $("#disconnect").prop("disabled", true);
    }

    function updateFriendName(name) {
        statusOutput.html(name);
    }
</script>
<script>
    <%--    將line分享連結轉utf-8--%>
    $(document).ready(function () {
        $(".todeepage").each(function () {
            let href = $(this).attr("href");
            const encodedHref = "https://social-plugins.line.me/lineit/share?text=" + encodeURIComponent(href)+"&from=line_scheme";
            $(this).attr("href", encodedHref);
        })
    })
</script>

</body>
</html>