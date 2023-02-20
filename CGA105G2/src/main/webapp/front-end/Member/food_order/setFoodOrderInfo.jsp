<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <div class="container mt-17 mb-17">
            <div class="col-md-9  card shadow m-5">
                <h1 class=" m-5 text-center"><i class="fa-solid fa-user"></i>訂位資訊</h1>
                <form METHOD="post" action="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do"
                      style="padding: 10px 100px;" autocomplete="off">
                    <div class="input-group input-group-lg mb-5">
                        <span class="input-group-text" id="inputGroup-sizing-lg1"><i class="fa-solid fa-store"></i>店家名稱 : </span>
                        <input type="text" name="order_shop_name" id="order_shop_name1" readonly="readonly"
                               value="${storename}" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg">
                        <span></span>
                    </div>
                    <br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text"><i class="fa-regular fa-user"></i>姓名 : </span>
                        <input type="text" name="nameInput" placeholder="姓名(必填)" class="form-control"
                               aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg"
                               value="${nameInput}">
                    </div>
                    <p style="color:red"> ${errorMsgs.name} </p>
                    <br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text"><i
                                class="fa-solid fa-phone-volume"></i>手機 : </span>
                        <input type="text" name="phoneInput" placeholder="手機(必填)" class="form-control"
                               aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg"
                               value="${phoneInput}">
                    </div>
                    <p style="color:red"> ${errorMsgs.phone} </p>
                    <br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text" id="inputGroup-sizing-lg2"><i class="fa-solid fa-users"></i>人數 : </span>
                        <select id="peopleNum1" name="peopleNum1">
                            <option value='0' disabled ${peopleNum1 eq null? "selected":""}>--請選擇人數--</option>
                            <option value="1" ${peopleNum1 eq 1? "selected":""}>1人</option>
                            <option value="2" ${peopleNum1 eq 2? "selected":""}>2人</option>
                            <option value="3" ${peopleNum1 eq 3? "selected":""}>3人</option>
                            <option value="4" ${peopleNum1 eq 4? "selected":""}>4人</option>
                        </select>
                    </div>
                    <p style="color:red"> ${errorMsgs.peopleNum1} </p>
                    <br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text" id="inputGroup-sizing-lg3"><i
                                class="fa-solid fa-calendar-days"></i>日期 : </span>
                        <input type="text" name="dateInput" id="datepicker1_1" placeholder="請選擇日期(必填)"
                               class="form-control"
                               aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                    </div>
                    <p style="color:red"> ${errorMsgs.dateInput} </p>
                    <br>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text" id="inputGroup-sizing-lg4"><i
                                class="fa-solid fa-calendar-days"></i>時段 : </span>
                        <select id="select_order_time1" name="time1">
                            <option value="0" disabled selected>--請選擇時段--</option>
                        </select>
                    </div>
                    <p style="color:red"> ${errorMsgs.time1} </p>
                    <br>
                    <p style="color:red"> ${errorMsgs.overlimit} </p>
                    <br>
                    <p style="color:red"> ${errorMsgs.meal} </p>
                    <div>
                        <input type="hidden" name="action" value="Member_order_to_meal">
                        <input class="btn btn-warning mb-1 btn-block fs-5 mb-10" type="submit" value="送出">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻訂位)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆預約查詢)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    var order_time_list = [
        <c:forEach var="storetimeVar" items="${storetime}">
        `${storetimeVar}`,
        </c:forEach>
    ];
    // Part1 : 填入表單各種註冊
    const checkList = {
        order_shop_name: '',
        peopleNum: '',
        dateInput: '',
        select_order_time: ''
    }

    $(function () {
        <c:if test="${empty dateInput}">
        $("#datepicker1_1")
            .datepicker({hangeMonth: true})
            .datepicker("option", "dateFormat", "yy-mm-dd")
            .datepicker("option", "minDate", 0)
            .datepicker("option", "maxDate", "+1M")
        </c:if>
        <c:if test="${not empty dateInput}">
        $("#datepicker1_1")
            .datepicker({hangeMonth: true})
            .datepicker("option", "dateFormat", "yy-mm-dd")
            .datepicker("option", "minDate", 0)
            .datepicker("option", "maxDate", "+1M")
            .datepicker("setDate", `${dateInput}`)
        </c:if>

    });
    document.addEventListener('DOMContentLoaded', selectEventListener);
    let select_time_temp = 0;

    function selectEventListener(event) {
        if (select_time_temp == 0) {
            for (let order_time of order_time_list) {
                let option = document.createElement("option");
                option.setAttribute("value", order_time.split(':')[0]);
                if (order_time.split(':')[0] == `${time1}`) {
                    option.setAttribute("selected", "selected");
                    document.getElementById(`select_order_time1`).removeAttribute("selected");
                }
                option.textContent = order_time;
                document.getElementById(`select_order_time1`).appendChild(option);
            }
        }
        select_time_temp++;
    }
</script>
<script src="https://kit.fontawesome.com/e952f26fd6.js" crossorigin="anonymous"></script>
</body>
</html>