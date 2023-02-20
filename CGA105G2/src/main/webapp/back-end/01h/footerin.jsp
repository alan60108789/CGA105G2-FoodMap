<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<!-- footer start -->
<section class="footer "
         style="width: 100%; position: relative; bottom: 0; top: 30%; background-color: pink">
  <div class="container">
    <!-- 三張小圖 -->
    <div
            class="d-flex align-items-stretch justify-content-md-center py-10">
      <!-- 地址 -->
      <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
        <div class="card-body py-17 px-10 text-center">
          <div class="card-icon mb-6">
            <i class="material-icons">map</i>
          </div>
          <div
                  class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
            Our address
          </div>
          <p class="mb-0 text-body">
            桃園市中壢區復興路46號9樓<br/>Hollow Lane. NY 11706.
          </p>
        </div>
      </div>
      <!-- 電話 -->
      <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
        <div class="card-body py-17 px-10 text-center">
          <div class="card-icon mb-6">
            <i class="material-icons">phone</i>
          </div>
          <div
                  class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
            連絡電話
          </div>
          <p class="mb-0 text-body">0800-087-087</p>
        </div>
      </div>
      <!-- 營業時間 -->
      <div class="card border-0 bg-secondary mb-4 ml-lg-9 w-25">
        <div class="card-body py-17 px-10 text-center">
          <div class="card-icon mb-6">
            <i class="material-icons">access_time</i>
          </div>
          <div
                  class="fs-1 lh-1 my-5 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted">
            聲明
          </div>
          <p class="mb-0 text-body">此網站為TibaMe_CGA105G2專案作品，購買商品均屬虛構行為，請勿使用真實信用卡。</p>
        </div>
      </div>
    </div>
    <!-- 跳轉到社群連結 -->
    <div class="d-flex align-items-center justify-content-center">
      <a href="/CGA105G2/back-end/frontSelect/frontSelect.jsp" class="footer-brand">FoodMap</a>
      <div class="brand-icons-list ml-10 ml-sm-20">
        <!-- FB圖案 -->
        <a href="/CGA105G2/BlankPage/Loader1.jsp"
           class="brand-icon brand-icon-circle brand-icon-facebook"> <i
                class="fa fa-facebook-f"></i>
        </a>
        <!-- twitter圖案 -->
        <a href="/CGA105G2/BlankPage/Loader1.jsp" class="brand-icon brand-icon-circle brand-icon-twitter">
          <i class="fa fa-twitter "></i>
        </a>
        <!-- ig圖案-->
        <a href="/CGA105G2/BlankPage/Loader1.jsp"
           class="brand-icon brand-icon-circle brand-icon-instagram"> <i
                class="fa fa-pinterest-p"></i>
        </a>
      </div>
    </div>
    <!-- 頁底文字 -->
    <small class="d-flex align-items-center justify-content-center"
           style="margin: 0px -10px 0px -10px;"> TibaMe CGA105_2
      MapFood <a href="#" data-toggle="smooth-scroll"
                 data-target="#page-start-anchor"> <i
              class="material-icons text-black">arrow_upward</i>
      </a>
    </small>
  </div>
</section>
<!-- footer end -->

<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
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
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- jquery 3.4.1 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js">
</script>
</body>
</html>
