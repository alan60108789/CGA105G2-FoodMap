<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.member.model.Member.pojo.Member" %>
<%@page import="com.member.model.service.MemberService" %>
<%@page import="com.art.model.Article.pojo.Article" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Article article = (Article) request.getAttribute("article");
    Integer memId = (Integer) request.getSession().getAttribute("memId");
    MemberService memberService = new MemberService();
    Member member = memberService.getById(memId);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());
    pageContext.setAttribute("member", member);
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
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

        @media ( min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        /* ========button的樣式=============== */
        .btn-secondary {
            font-family: "Noto Sans TC", sans-serif !important;
            font-size: 18px !important;
            letter-spacing: .05em !important;
            border-radius: .75em !important;
            font-weight: 500 !important;
            color: #FFFFFF !important;
            background-color: #164570 !important;
            padding: 5px 13px !important;
            cursor: pointer !important;
        }

        /* ============星星的css============== */
        .rating-wrapper {
            direction: rtl !important;
        }

        .storescorelabel {
            color: #E1E6F6 !important;
            cursor: pointer !important;
            font-size: 32px !important;
            padding: 8px 3px !important;
            transition: color 0.5s !important;
        }

        .storescore {
            height: 100% !important;
            width: 100% !important;
            display: none !important;
        }

        .storescorelabel:hover, .storescorelabel:hover ~ .storescorelabel,
        .storescore:checked ~ label {
            color: #ffe223 !important;
        }

        /* ==================上傳圖片的css==================== */
        .image-upload input {
            display: none;
        }

        .upload-field {
            cursor: pointer;
        }

        .upload-field .file-thumbnail {
            cursor: pointer !important;
            border: 1px dashed #BBD9EC !important;
            border-radius: 11px !important;
            text-align: center !important;
            padding: 10px 0px !important;
            width: 100px !important;
            height: 100px !important;
        }

        .upload-field .file-thumbnail img {
            width: 50px !important;
        }

        .upload-field .file-thumbnail h3 {
            font-size: 15px !important;
            color: #000000 !important;
            font-weight: 1000 !important;
            margin-top: 10px !important;
        }

        /* ==================上傳圖片css結束======================= */
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
                <h1 class="h2">🔆發文頁面</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                </div>
            </div>
            <!-- =======================會員頭像======================= -->
            <div class="row justify-content-center">
                <div class="col-7 mb-10">
                    <div class="postmember_info"
                         style="display: flex; margin-top: 30px;">
                        <div class="postmember_img">
                            <c:if test="${not empty member.memPic}">
                                <img src="/CGA105G2/assets/images/men.png"
                                     style="width: 65px; height: 60px; border-radius: 80%; border: 1px solid rgb(255, 216, 87);">
                            </c:if>
                            <c:if test="${empty member.memPic}">
                                <img src="https://i.pinimg.com/564x/07/c4/72/07c4720d19a9e9edad9d0e939eca304a.jpg"
                                     alt=""
                                     style="width: 65px; height: 60px; border-radius: 80%; border: 1px solid rgb(255, 216, 87);">
                            </c:if>
                        </div>
                        <div class="postmember_text" style="margin-left: 5px;">
								<span class="postmember_name" style="font-size: 20px;">
                                    ${member.memName} </span>
                            <div>
                                <time><%= today %>
                                </time>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ==================發文開始====================== -->
            <div class="row justify-content-center">
                <div class="col-7 mb-10 shadow "
                     style="padding: 30px; border: 3px solid rgba(208, 208, 208, 0.644); border-radius: 20px;">
                    <div class="poststore_info"
                         style="display: flex; margin-top: 10px;">
                        <div class="poststore_img">
                            <img src="/CGA105G2/assets/images/ex1.jpg"
                                 style="width: 65px; height: 60px; border: 1px solid rgb(255, 216, 87);">
                        </div>
                        <div class="poststore_text"
                             style="margin-left: 5px; align-items: center; display: flex;">
								<span class="post_name"
                                      style="font-size: 30px; font-weight: 1000;">${store.storeName}</span>
                        </div>
                    </div>
                    <!-- ===================店家評分星星================== -->
                    <!-- star rating -->
                    <form method="post" action="<%=request.getContextPath()%>/front-end/Member/art/ArtServlet"
                          enctype="multipart/form-data">
                        <div class="rating-wrapper pt-3">
                            <div>
                                <input type="hidden" name="memId" value="${member.memId}">
                                <input type="hidden" name="storeId" value="${store.storeId}">
                                <input type="hidden" name="pointChange" value="完成訂單評論">
                                <input type="hidden" name="pointNumber" value="10">
                                <span style="font-size: 22px; font-weight: 600; background-color: antiquewhite; margin-left: 20px; line-height: 60px;"></span>
                                <!-- star 5 -->
                                <input type="radio" id="5-star-rating" class="storescore"
                                       name="artScore" value="5"> <label
                                    for="5-star-rating" class="star-rating storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>
                                <!-- star 4 -->
                                <input type="radio" id="4-star-rating" class="storescore"
                                       name="artScore" value="4"> <label
                                    for="4-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>
                                <!-- star 3 -->
                                <input type="radio" id="3-star-rating" class="storescore"
                                       name="artScore" value="3"> <label
                                    for="3-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>
                                <!-- star 2 -->
                                <input type="radio" class="storescore" id="2-star-rating"
                                       name="artScore" value="2"> <label
                                    for="2-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label>
                                <!-- star 1 -->
                                <input type="radio" id="1-star-rating" class="storescore"
                                       name="artScore" value="1"> <label
                                    for="1-star-rating" class="star-rating star storescorelabel">
                                <i class="fa fa-star d-inline-block"></i>
                            </label> <span
                                    style="font-size: 22px; font-weight: 600; background-color: antiquewhite;">:評分</span>
                            </div>
                        </div>
                        <!-- ==============標記tag================== -->
                        <div class="tag"
                             style="margin-top: 5px;display: flex;background-color: rgb(82, 206, 156);color: white;border-radius:15px ;font-size: 22px;font-weight: 1000;padding: 5px;padding-left: 10px;">
                            店家標籤:
                            <input type="checkbox" value="銀髮族友善店家" name="artTag"
                                   style="margin-left: 10px;zoom: 180%;">&nbsp;銀髮族友善店家󠀠
                            <input type="checkbox" value="寵物友善店家" name="artTag"
                                   style="margin-left: 10px;zoom: 180%;">&nbsp;寵物友善店家
                            <input type="checkbox" value="殘障人士友善店家" name="artTag"
                                   style="margin-left: 10px;zoom: 180%;">&nbsp;殘障人士友善店家
                        </div>
                        <!-- ===========輸入欄位開始================ -->
                        <div style="margin-bottom: 30px;">
                            <div>
                                <input type="text" name="artHeader" id="tb22_title" placeholder="文章標題"
                                       value="<%= (article==null)? "" : article.getArtHeader()%>"
                                       class="form-control" style="width: 100%; margin: 20px 0px;">
                            </div>
                            <div class="mb-5">
                                <textarea class="form-control" name="artText"
                                          placeholder="給店家的評語" style="width: 100%; height: 150px;"
                                          id="tb22_comment"><%= (article==null)? "" : article.getArtText()%></textarea>
                            </div>
                        </div>
                        <!-- ==============插入圖片開始================= -->
                        <div class="image-upload">
                            <input type="file" name="artImg" id="logo"
                                   value="<%= (article==null)? "" : article.getArtImg()%>"
                                   onchange="fileValue(this)"> <label for="logo"
                                                                      class="upload-field" id="file-label">
                            <div class="file-thumbnail">
                                <img id="image-preview"
                                     src="https://www.btklsby.go.id/images/placeholder/basic.png"
                                     alt="">
                                <h3 id="filename">上傳圖片</h3>

                            </div>
                        </label>
                        </div>
                        <!-- ==================發表評論開始====================== -->
                        <div class="post_btn"
                             style="display: flex; justify-content: flex-end">
                            <button type="reset" class="btn-secondary" @click="backHome"
                                    @submit.prevent style="background-color: gray;">取消
                            </button>
                            <button type="submit" class="btn-secondary" @click="uploadData"
                                    @submit.prevent style="margin-left: 10px">發表評論
                            </button>
                            <input type="hidden" name="action" value="insertArt">
                        </div>
                    </form>
                    <%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <ul style="list-style: none;font-weight: 900;;text-align: center;font-size: 22px;">
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">！！${message}！！</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <%-- 錯誤表列 --%>
                </div>
            </div>
        </main>
    </div>
    <!-- =================發文結束======================= -->
</div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- ==================上傳圖片js=================== -->
<script>
    function fileValue(value) {
        const path = value.value;
        const extenstion = path.split('.').pop();
        if (extenstion == "jpg" || extenstion == "svg" || extenstion == "jpeg" || extenstion == "png" || extenstion == "gif" || extenstion == "JPG" || extenstion == "PNG" || extenstion == "JPEG") {
            document.getElementById('image-preview').src = window.URL.createObjectURL(value.files[0]);
            const filename = path.replace(/^.*[\\\/]/, '').split('.').slice(0, -1).join('.');
            document.getElementById("filename").innerHTML = filename;
        } else {
            alert("檔案格式錯誤，請上傳圖片格式為JPG、PNG、JPEG、SVG")
        }
    }

    // =====================上傳圖片js結束============================
</script>
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
<!-- =====================店家標籤Js==================== -->
<script>
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].addEventListener('change', function () {
            var checkboxGroup = this.parentNode.parentNode;
            var checkboxesInGroup = checkboxGroup.querySelectorAll('input[type="checkbox"]');
            for (var j = 0; j < checkboxesInGroup.length; j++) {
                if (checkboxesInGroup[j] !== this) {
                    checkboxesInGroup[j].checked = false;
                }
            }
        });
    }
</script>
<!-- ====================alert start============================= -->
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
            title: '新增成功',
            showConfirmButton: false,
            timer: 1500
        })
    }

    let toResult = null;
    toResult =
    <%= request.getAttribute("toResult") %>
    if (toResult == true) {
        // alert(toResult);
        addCupAlert();
        toResult = null;
    }
    ;
</script>
<!-- ====================alert end============================= -->
</body>
</html>