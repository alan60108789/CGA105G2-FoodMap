<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>🗃️管理</title>
    <!-- Bootstrap css -->
    <style>
        /* ========button的樣式=============== */
        .btn-secondary {
            font-family: "Noto Sans TC", sans-serif;
            font-size: 18px;
            letter-spacing: .05em;
            border-radius: .75em;
            font-weight: 500;
            color: #FFFFFF;
            background-color: #164570;
            padding: 5px 13px;
            cursor: pointer;
        }

        /* ============星星的css============== */
        .rating-wrapper {
            direction: rtl !important;
        }


        .storescorelabel {
            color: #E1E6F6;
            cursor: pointer;
            font-size: 32px;
            padding: 8px 3px;
            transition: color 0.5s;
        }

        .storescore {
            height: 100%;
            width: 100%;
            display: none;
        }

        .storescorelabel:hover,
        .storescorelabel:hover ~ .storescorelabel,
        .storescore:checked ~ label {
            color: #ffe223;
        }

        /* ==================上傳圖片的css==================== */
        .image-upload input {
            display: none;
        }

        .upload-field {
            cursor: pointer;
        }

        .upload-field .file-thumbnail {
            cursor: pointer;
            border: 1px dashed #BBD9EC;
            border-radius: 11px;
            text-align: center;
            padding: 10px 0px;
            width: 100px;
            height: 100px;
        }

        .upload-field .file-thumbnail img {
            width: 50px;
        }

        .upload-field .file-thumbnail h3 {
            font-size: 15px;
            color: #000000;
            font-weight: 1000;
            margin-top: 10px;
        }

        /* ==================上傳圖片css結束======================= */
    </style>
</head>

<body>
<div id="page-start-anchor"></div>

<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">🔆店家廣告上傳頁面</h1>
            </div>
            <!-- ==================發文開始======================= -->
            <div class="row justify-content-center">
                <div class="col-8 mb-10 "
                     style="padding: 30px;border: 3px solid rgb(208, 208, 208);border-radius: 20px;">
                    <div class="poststore_info" style="display: flex;margin-top: 10px;">
                        <div class="poststore_img">
                        </div>
                        <div class="poststore_text" style="margin-left: 5px;align-items: center;display: flex;">

                        </div>
                    </div>
                    <!-- ===================店家上傳圖片================== -->
                    <form METHOD="post" action="<%=request.getContextPath()%>/adServlet" enctype="multipart/form-data">
                        <!-- ===========輸入欄位開始================ -->
                        <div style="margin-bottom: 30px;text-align: center;">
                            <!-- ==============插入圖片開始================= -->
                            <div class="image-upload">
                                <input type="file" name="adImg" id="logo" onchange="fileValue(this)">
                                <label for="logo" class="upload-field" id="file-label">
                                    <div class="file-thumbnail" style="width: 550px;height: 520px;">
                                        <img id="image-preview"
                                             src="https://www.btklsby.go.id/images/placeholder/basic.png" alt=""
                                             style="width: 550px;height: 470px;">
                                        <h3 id="filename" style="font-weight: 1000;font-size: 25px;">
                                            上傳圖片
                                        </h3>
                                        <div style="font-size: 25px;font-weight: 1000;">廣告文字說明:</div>
                                        <textarea class="col-12" type="text" placeholder="廣告文字說明" name="adTEXT" style="height:70px ;"></textarea>
                                        <div>
                                            <input type="hidden" name="action" value="addAD">
                                            <button type="submit" class="col-12 m-0" @click="uploadData" @submit.prevent
                                                    style="margin-left: 10px">上傳廣告圖
                                            </button>
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- ==================上傳圖片js=================== -->
<script>
    function fileValue(value) {
        var path = value.value;
        var extenstion = path.split('.').pop();
        if (extenstion == "jpg" || extenstion == "svg" || extenstion == "jpeg" || extenstion == "png" || extenstion == "gif" || extenstion == "JPG") {
            document.getElementById('image-preview').src = window.URL.createObjectURL(value.files[0]);
            var filename = path.replace(/^.*[\\\/]/, '').split('.').slice(0, -1).join('.');
            document.getElementById("filename").innerHTML = filename;
        } else {
            alert("檔案格式錯誤，請上傳圖片格式為JPG、PNG、JPEG、SVG")
        }
    }

    // =====================上傳圖片js結束============================
</script>
</body>

</html>