<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>店家首頁</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <div class="container">
            <div class="col-md-9 mx-5 mt-10 pt-5 jumbotron bg-light card shadow ">
                <h1 class="mt-5 text-center"><i class="fa-solid fa-pencil"></i>修改訂位資訊</h1>
                <hr>
                <div class="p-4  ">
                    <form METHOD="post" action="<%=request.getContextPath()%>/front-end/store/food_order/food_order.do"
                          style="padding: 10px 100px;">
                        <div class="form-group  row ">
                            <label for="time_frame1" class="col-form-label pr-2 fs-6 font-weight-bold"><i
                                    class="fa-regular fa-clock"></i>時段一 : </label>
                            <div>
                                <select class="form-control form-control-sm mt-2" id="time_frame1" name="timeSelect1">
                                    <c:forEach var="i" begin="0" end="24" varStatus="loop">
                                        <c:if test="${i == 0}">
                                            <option value="0" ${timestrArrayList[0] eq 0 ? "selected=\"selected\"" : ""} >
                                                不設定
                                            </option>
                                        </c:if>
                                        <c:if test="${i > 9}">
                                            <option value="${i}" ${timestrArrayList[0] eq i ? "selected=\"selected\"" : ""}>${i}:00</option>
                                        </c:if>
                                        <c:if test="${i < 10 && i>0}">
                                            <option value="${i}" ${timestrArrayList[0] eq i ? "selected=\"selected\"" : ""}>
                                                0${i}:00
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="time_frame2" class="col-form-label pr-2 fs-6 font-weight-bold"><i
                                    class="fa-regular fa-clock"></i>時段二 : </label>
                            <div>
                                <select class="form-control form-control-sm mt-2" id="time_frame2" name="timeSelect2">
                                    <c:forEach var="i" begin="0" end="24" varStatus="loop">
                                        <c:if test="${i == 0}">
                                            <option value="0" ${timestrArrayList[1] eq 0 ? "selected=\"selected\"" : ""} >
                                                不設定
                                            </option>
                                        </c:if>
                                        <c:if test="${i > 9}">
                                            <option value="${i}" ${timestrArrayList[1] eq i ? "selected=\"selected\"" : ""}>${i}:00</option>
                                        </c:if>
                                        <c:if test="${i < 10 && i>0}">
                                            <option value="${i}" ${timestrArrayList[1] eq i ? "selected=\"selected\"" : ""}>
                                                0${i}:00
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="time_frame3" class="col-form-label pr-2 fs-6 font-weight-bold"><i
                                    class="fa-regular fa-clock"></i>時段三 : </label>
                            <div>
                                <select class="form-control form-control-sm mt-2" id="time_frame3" name="timeSelect3">
                                    <c:forEach var="i" begin="0" end="24" varStatus="loop">
                                        <c:if test="${i == 0}">
                                            <option value="0" ${timestrArrayList[2] eq 0 ? "selected=\"selected\"" : ""} >
                                                不設定
                                            </option>
                                        </c:if>
                                        <c:if test="${i > 9}">
                                            <option value="${i}" ${timestrArrayList[2] eq i ? "selected=\"selected\"" : ""}>${i}:00</option>
                                        </c:if>
                                        <c:if test="${i < 10 && i>0}">
                                            <option value="${i}" ${timestrArrayList[2] eq i ? "selected=\"selected\"" : ""}>
                                                0${i}:00
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="time_frame4" class="col-form-label pr-2 fs-6 font-weight-bold"><i
                                    class="fa-regular fa-clock"></i>時段四 : </label>
                            <div>
                                <select class="form-control form-control-sm mt-2" id="time_frame4" name="timeSelect4">
                                    <c:forEach var="i" begin="0" end="24" varStatus="loop">
                                        <c:if test="${i == 0}">
                                            <option value="0" ${timestrArrayList[3] eq 0 ? "selected=\"selected\"" : ""} >
                                                不設定
                                            </option>
                                        </c:if>
                                        <c:if test="${i > 9}">
                                            <option value="${i}" ${timestrArrayList[3] eq i ? "selected=\"selected\"" : ""}>${i}:00</option>
                                        </c:if>
                                        <c:if test="${i < 10 && i>0}">
                                            <option value="${i}" ${timestrArrayList[3] eq i ? "selected=\"selected\"" : ""}>
                                                0${i}:00
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <c:if test="${not empty errortimeMsgs}">
                            <p style="color:red"> ${errortimeMsgs} </p>
                        </c:if>
                        <div class="form-group row">
                            <label for="tableCount" class="col-form-label pr-2 fs-6 font-weight-bold"><i
                                    class="fa-solid fa-table"></i>總桌數 : </label>
                            <div>
                                <input type="text" class="form-control form-control-sm mt-2" id="tablecount"
                                       name="tablecount" value="${tablecount}">
                            </div>
                        </div>
                        <c:if test="${not empty errortablecountMsgs}">
                            <p style="color:red"> ${errortablecountMsgs} </p>
                        </c:if>
                        <div class="form-group row">
                            <label class="col-form-label pr-2 fs-6 font-weight-bold"><i class="fa-solid fa-person"></i>訂位上限
                                : </label>
                            <div>
                                <input type="text" class="form-control form-control-sm mt-2" id="orderlimit"
                                       name="orderlimit" value="${orderlimit}">
                            </div>
                        </div>
                        <c:if test="${not empty errororderlimitMsgs}">
                            <p style="color:red"> ${errororderlimitMsgs} </p>
                        </c:if>
                        <input type="hidden" name="action" value="updateStoreSettingButton">
                        <input id="input1" class="form-group row btn btn-danger btn-block fs-6" type="submit"
                               value="送出">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="orderRule" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content" style="width:400px; height:400px;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><i class="fa-solid fa-circle-exclamation"></i>注意事項
                </h5>
            </div>
            <div class="modal-body">
                <p>
                    <i class="fa-regular fa-star"></i>1. 如果為首次設定，第二點及第三點可忽略。<br>
                    <br>
                    <i class="fa-regular fa-star"></i>2. 時段修改，原時段訂位人的訂單依舊存在，如需取消客戶之訂單，請至訂單管理做修改。<br>
                    <br>
                    <i class="fa-regular fa-star"></i>3. 桌數及上線人數修改，先前訂單是以舊桌數及上線人數做訂單接取。
                    <br>
                </p>
            </div>
            <div class="modal-footer">
                <button id="closebutton" type="button" class="btn btn-primary">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻訂位管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu2").removeClass("collapse");
    $("#pageSubmenu2 a:contains(🔆餐點與時段)").closest("a").addClass("active disabled bg-white topage");
</script>

<script src="https://kit.fontawesome.com/e952f26fd6.js" crossorigin="anonymous"></script>
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
    document.addEventListener('DOMContentLoaded', function () {
        <c:if test="${empty isShowinfo}">
        document.getElementById("orderRule").setAttribute("style", "position:absolute; top:20%; left:40%;");
        </c:if>
    });
    document.getElementById("closebutton").addEventListener("click", function () {
        document.getElementById("orderRule").setAttribute("style", "display: none;");
    });
</script>
</body>
</html>