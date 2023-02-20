<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
</head>
<body>
<c:if test="${memId > 0}">
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
</c:if>
<c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
</c:if>
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <c:if test="${memId > 0}">
            <!-- nav start -->
            <%@ include file="/front-end/Member/01h/nav/navin02.jsp" %>
            <!-- nav end -->
        </c:if>
        <c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
            <%@ include file="/front-end/Member/01h/nav/navin00.jsp" %>
        </c:if>
        <div class="container mt-17 mb-17" id="changepage">
            <div class="col-md-9  card shadow m-5">
                <h1 class=" m-5 text-center" id="sname">${sname}</h1>
                <form class="p-4" id="form1">
                    <input type="hidden" name="sid" value="${sid}" id="sid" readonly="readonly">
                    <div class="d-flex justify-content-between">
                        <div class="input-group input-group-lg mb-5">
                            <span class="input-group-text">訂單編號</span>
                            <input class="form-control form-control-sm text-right" type="text" name="renid"
                                   value="${renid}" id="renid1" readonly="readonly">
                        </div>
                        <div class="input-group input-group-lg mb-5">
                            <span class="input-group-text">點餐人數</span>
                            <input class="form-control form-control-sm text-right" type="text" name="peopleNum"
                                   value="${foodorder_peopleNum}" id="peopleNum"
                                   readonly="readonly">
                        </div>
                    </div>
                    <table class="table" style="text-align:center; font-size: 20px;">
                        <thead class="thead-light">
                        <tr>
                            <th class="deeeebey p-0" scope="col"></th>
                            <th scope="col" class="p-0">ID</th>
                            <th scope="col" class="p-0">套餐名稱</th>
                            <th class="deeeebey p-0" scope="col">金額</th>
                            <th scope="col" class="p-0">數量</th>
                        </tr>
                        </thead>
                        <tbody id="tbody_detail">
                        <% int i = 1; %>
                        <c:forEach var="mealVO" items="${list}">
                            <tr id="tr_detail_<%= i %>">
                                <th scope="row" class="col-1 "><%= i %>
                                </th>
                                <td class="mealid col-1 ">${mealVO.mealId}</td>
                                <td class="mealn col-3 ">${mealVO.mealName}</td>
                                <td id="money_<%= i %>" class="col-3 ">${mealVO.mealPrice}</td>
                                <td class="col-4">
                                    <div class="d-flex ">
                                        <button type="button" class="btn btn-outline-primary btn-sm ">-</button>
                                        <!-- name要填入餐點id -->
                                        <input type="text" name="${mealVO.mealId}" id="count_<%= i %>"
                                               style="background-color:transparent;border:0;outline: none;"
                                               readonly="readonly" value="0"
                                               class="mealq text-center ">
                                        <button type="button" class="btn btn-outline-primary btn-sm ">+</button>
                                    </div>
                                </td>
                            </tr>
                            <% i = i + 1; %>
                        </c:forEach>
                        <tr id="tr_total" class="deeeebey">
                            <td class="col-2"></td>
                            <td></td>
                            <td class="col-2">合計</td>
                            <td></td>
                            <td id="mprice" class="col-8 text-right"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="form-group row" id="codediv">
                        <!-- 優惠劵輸入 -->
                        <div class="d-flex justify-content-between">
                            <span class="input-group-text col-4 d-block tab-content">優惠劵<br><p id="erro" style="color: red"></p></span>
                            <input id="codetext" type="text" class="form-control form-control-sm text-right col-8 h-100"
                                   name="codetext">
                        </div>
                        <div class="d-flex justify-content-between">
                            <span class="input-group-text col-4 d-block tab-content ">折扣</span>
                            <input id="codeoff" type="text" class="form-control form-control-sm text-right col-8"
                                   disabled="disabled">
                        </div>
                        <div class="d-flex justify-content-between">
                            <span class="input-group-text col-4 d-block tab-content">結帳金額</span>
                            <input id="toooo" type="text" class="form-control form-control-sm text-right col-8"
                                   disabled="disabled">
                        </div>
                    </div>
                    <input id="action" type="hidden" name="action">
                    <input id="gotobuy" type="button" class="btn btn-warning mb-1 btn-block fs-5 mb-10 deeeebey" value="送出">
                    <div id="buttonfd"></div>
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
    $("#pageSubmenu3 a:contains(🔆訂位預約)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    var total_count = 0;
    var total_coint_limit = parseInt(${foodorder_peopleNum});// jsp取得人數
    var total_money = 0;
    //     var i = 1;//之後要用jsp foreach註冊
    <c:forEach var="i" begin="1" end="${fn:length(list)}" varStatus="loop">
    document.getElementById(`tr_detail_${i}`).querySelectorAll('td')[3].querySelectorAll('button')[0].addEventListener('click', function () {
        let count = document.getElementById(`count_${i}`).value;
        if (parseInt(count) > 0) {
            document.getElementById(`count_${i}`).value = parseInt(count) - 1;
            total_count--;
            total_money = total_money - parseInt(document.getElementById(`money_${i}`).innerHTML);
            document.getElementById(`tr_total`).querySelectorAll('td')[4].innerHTML = total_money;
        }
        ;
        $("input#toooo").val($("td#mprice").text());
    })
    document.getElementById(`tr_detail_${i}`).querySelectorAll('td')[3].querySelectorAll('button')[1].addEventListener('click', function () {
        let count = document.getElementById(`count_${i}`).value;
        if (total_count < total_coint_limit) {
            document.getElementById(`count_${i}`).value = parseInt(count) + 1;
            total_count++;
            total_money = total_money + parseInt(document.getElementById(`money_${i}`).innerHTML);
            document.getElementById(`tr_total`).querySelectorAll('td')[4].innerHTML = total_money;
        }
        ;
        $("input#toooo").val($("td#mprice").text());
    })
    </c:forEach>

    $(document).ready(function () {
        $("#gotobuy").click(function () {
            // 整理有選擇的餐點
            const mlist = tosendbuy();
            let mealt = $("#tbody_detail");
            $(".deeeebey").remove();
            let a = ``;
            for (let i of mlist) {
                a +=
                    `
                    <tr>
                         <td class="mealid col-4">
                            <input type="text" class="text-center" name="mealid" value="\${i.mealid}"
                            style="background-color:transparent;border:0;outline: none;" readonly="readonly">
                         </td>
                         <td class="mealn col-4 text-center">\${i.mealn}</td>
                         <td class="col-4">
                            <input type="text" class="text-center" name="mealq"  value="\${i.mealq}"
                            style="background-color:transparent;border:0;outline: none;" readonly="readonly">
                         </td>
                    </tr>
                `;
            }
            mealt.html(a);
            $("#sid").attr("id","to1");
            $("#renid").attr("id","to2");
            $("#codetext").attr("id","to3");
            $("#codetext").attr("disabled", "disabled");
            $("#action").val("gotobuy");
            $("#form1").attr("METHOD", "post");
            $("#form1").attr("ACTION", "/CGA105G2/LonginServlet");
            let b=`<button class="btn btn-warning mb-1 btn-block fs-5 mb-10">線上付款</button>`;
            $("#buttonfd").html(b);
        })
    });
    $(document).ready(function () {
        $("input#codetext").change(function () {
            let a = $(this).val();
            let sid = $("input#sid").val();
            $.ajax({
                type: "POST",
                url: "/CGA105G2/standby",
                data: {
                    action: "codecheck",
                    codetext: a,
                    sid: sid,
                },
                success: function (response) {
                    const list = JSON.parse(JSON.stringify(response))
                    const toResult = list.toResult;
                    let offp = 0;
                    if (toResult == true) {
                        $("p#erro").html("");
                        offp = list.mon;
                    } else {
                        $("p#erro").html(list.errmag);
                    }
                    ;
                    $("input#codeoff").val(offp);
                    let stp = $("td#mprice").text();
                    let ap = stp;
                    if (stp >= offp) {
                        ap = stp - offp;
                    }
                    $("input#toooo").val(ap);
                }
            })
        });
    });

    function tosendbuy() {
        const meal = $("td.mealid");
        const buylist = [];
        meal.each(function () {
            let id = $(this).text();
            let q = $(this).closest("tr").find('input.mealq').val();
            let mealn = $(this).closest("tr").find('td.mealn').text();
            if (q > 0) {
                const obj = {
                    mealid: id,
                    mealq: q,
                    mealn: mealn,
                };
                buylist.push(obj);
            }
        });
        return buylist;
    }


</script>
</body>
</html>