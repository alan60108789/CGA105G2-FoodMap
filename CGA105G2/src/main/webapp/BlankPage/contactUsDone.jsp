<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
</head>
<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid container">
    <main role="main" class="col-md-9 m-sm-auto col-lg-10 px-md-4 my-5 container">
        <!-- "contacts" section start -->
        <section class="section container" id="contacts">
            <div class="section-content container">
                <div class="">
                    <div class="col-12 col-lg-12 mb-14 mb-lg-0 text-center card shadow-lg">
                        <div class="p-5 m-5">
                            <h1>感謝你的意見</h1> <br>
                            <span  id="count"></span><span>秒後跳轉至上頁</span>
                            <a href="./contactUs.jsp" class="btn btn-block btn-primary my-5">或點此回上一頁</a>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </main>
</div>
 <canvas height = "200"></canvas>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(📭聯繫我們)").closest("a").addClass("active disabled topage");
    //設定倒數秒數
    let t = 5;
    //顯示倒數秒數
    function showTime() {
        t -= 1;
        document.getElementById('count').innerHTML = t;
        if (t == 0) {
            location.href = 'contactUs.jsp';
        }
        //每秒執行一次,showTime()
        setTimeout("showTime()", 1000);
    }

    //執行showTime()
    showTime();
</script>
</body>
</html>