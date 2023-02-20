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
                <h1 class=" m-5 text-center">訂餐資訊</h1>
                <form METHOD="post" action="<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do"
                      class="p-4 ">
                    <table class="table" style="text-align:center; font-size: 20px;">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">編號</th>
                            <th scope="col">套餐名稱</th>
                            <th scope="col">金額</th>
                            <th scope="col">數量</th>
                        </tr>
                        </thead>
                        <tbody id="tbody_detail">
                        <!-- 這邊tr_detail+i 1記得寫jsp要改成i-->
                        <% int i = 1; %>
                        <c:forEach var="mealVO" items="${list}">
                            <tr id="tr_detail_<%= i %>">
                                <th scope="row"><%= i %>
                                </th>
                                <td>${mealVO.mealId}</td>
                                <td>${mealVO.mealName}</td>
                                <td id="money_<%= i %>">${mealVO.mealPrice}</td>
                                <td>
                                    <button type="button" class="btn btn-outline-primary btn-sm">-</button>
                                    <!-- name要填入餐點id -->
                                    <input type="text" name="${mealVO.mealId}" id="count_<%= i %>"
                                           style="background-color:transparent;border:0;outline: none;"
                                           readonly="readonly" value="0">
                                    <button type="button" class="btn btn-outline-primary btn-sm">+</button>
                                </td>
                            </tr>
                            <% i = i + 1; %>
                        </c:forEach>
                        <tr id="tr_total">
                            <td>合計 :</td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <p style="color:red"> ${errorMealNumMsgs} </p>
                    <div class="form-group row">
                        <!-- 優惠劵輸入 -->
                        <label class="col-form-label pr-2">優惠劵:</label>
                        <div>
                            <input type="text" class="form-control form-control-sm" id="codevalue" name="codeinput"
                                   value="${codeinput}">
                        </div>
                    </div>
                    <div class="input-group input-group-lg mb-5">
                        <span class="input-group-text">折扣金額 : </span>
                        <input type="text" id="codemoney" readonly="readonly"
                               value="" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg">
                    </div>
                    <input type="hidden" name="action" value="Member_order_meal">
                    <input id="submit_0" class="btn btn-danger btn-sm" type="submit" value="結帳">
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
            document.getElementById(`tr_total`).querySelectorAll('td')[2].innerHTML = total_money;
        }
        ;
    })
    document.getElementById(`tr_detail_${i}`).querySelectorAll('td')[3].querySelectorAll('button')[1].addEventListener('click', function () {
        let count = document.getElementById(`count_${i}`).value;
        if (total_count < total_coint_limit) {
            document.getElementById(`count_${i}`).value = parseInt(count) + 1;
            total_count++;
            total_money = total_money + parseInt(document.getElementById(`money_${i}`).innerHTML);
            document.getElementById(`tr_total`).querySelectorAll('td')[2].innerHTML = total_money;
        }
    })
    </c:forEach>
    document.getElementById("codevalue").addEventListener("blur",function(){
    	var codeValue = document.getElementById("codevalue").value;
    	var sJson = {code: codeValue};
    	console.log(sJson);
        $.ajax({
            url: '<%=request.getContextPath()%>/front-end/Member/food_order/food_order.do?action=getCodemoneyByAjax',
            type: 'POST',
            traditional: true,
            data: sJson,
            success: function(data) {
                console.log(data);
                document.getElementById("codemoney").value=data[0].codeMoney;
            },
            error: function(XMLHttpRequest, status, error) {
                console.log("error")
            }
        })
    })
</script>
</body>
</html>