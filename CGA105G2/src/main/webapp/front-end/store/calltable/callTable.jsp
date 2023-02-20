<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String selectedOption = (String) session.getAttribute("selectedOption");
    String sts = (String) request.getAttribute("onOff");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>店家首頁</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous"/>
    <!-- jquery 3.4.1  css -->
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <style>
        .cantttodo {
            pointer-events: none;
        }

        .touseeating {
            height: 100px !important;
            background-color: rgba(253, 72, 72, 0.65);
        }
    </style>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headeronly.jsp" %>
<!-- header end -->
<!-- main -->
<div class="p-0">
    <!--        篩選-->
    <div class="d-flex flex-nowrap p-0">
        <!--            日期-->
        <div class="btn-group btn-group-toggle p-0 flex-grow-1">
            <!--              today-->
            <label class="input-group-text col-2">
                <button class="btn btn-secondary form-control  p-0"
                        style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                        id="today">Today
                </button>
            </label>
            <form METHOD="post" ACTION="/CGA105G2/TableServlet"
                  class="btn-group btn-group-toggle p-0 flex-grow-1 col-6">
                <!--date-->
                <label class="input-group-text col-6" for="datepicker">
                    <input type="text" class="form-control  pl-5"
                           style="background-color: rgb(9, 148, 101, 0.42); color: white;"
                           id="datepicker" placeholder="yyyy-mm-dd" name="date"
                           value="${date}">
                </label>
                <!--time-->
                <label class="input-group-text col-4">
                    <select class="btn btn-secondary form-control  p-0"
                            style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                            id="Select01" name="totime" value=${totime}>
                    </select>
                </label>
                <%-- search--%>
                <label class="input-group-text col-2">
                    <button class="btn btn-secondary form-control  p-0"
                            style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                            id="send">
                        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35"
                             fill="currentColor" class="bi bi-search" viewBox="0 0 16 16"
                             id="IconChangeColor">
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                                  id="mainIconPathAttribute"></path>
                        </svg>
                    </button>
                    <input type="hidden" name="action" value="search" class="d-none">
                </label>
            </form>
            <%--reload--%>
            <label class="input-group-text col-1">
                <form METHOD="post" ACTION="/CGA105G2/TableServlet" class="col-12">
                    <input type="hidden" name="date" value="${date}" class="d-none">
                    <input type="hidden" name="totime" value="${totime}"
                           class="d-none">
                    <button class="btn btn-secondary form-control  p-0"
                            style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                            id="reload">
                        <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38"
                             fill="currentColor" class="bi bi-arrow-repeat"
                             viewBox="0 0 16 16" id="IconChangeColor">
                            <path d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"
                                  id="mainIconPathAttribute" stroke-width="0.7" stroke="#4162c3"></path>
                            <path fill-rule="evenodd"
                                  d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"
                                  id="mainIconPathAttribute" stroke="#4162c3"></path>
                        </svg>
                    </button>
                    <input type="hidden" name="action" value="reload" class="d-none">
                </form>
            </label>
            <div class="input-group-text col-3">
                <p>剩餘訂位數：${listq}</p>
            </div>
        </div>
    </div>
    <!--        下文-->
    <div class="d-flex">
        <!--      第一欄    -->
        <div class="col-2 p-0 border border-right">
            <!--            上-->
            <div>
                <div class="input-group-text">
                    <p class="form-control pt-5"
                       style="background-color: rgba(253, 72, 72, 0.65); color: black;">訂位表</p>
                </div>
                <div class="btn-group btn-group-toggle" style="display: block"
                     id="tablecheck">
                    <div class="table-responsive">
                        <table class="table table-striped m-0">
                            <thead>
                            <tr class="text-center">
                                <th class="col-4">電話</th>
                                <th class="col-4">姓名</th>
                                <th class="col-4">人數</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <section class="section p-0" id="faq1"
                         style="overflow-y: scroll; height: 300px">
                    <div class="section-content container">
                        <div class="row">
                            <div class="col-12 p-0 toorder"></div>
                        </div>
                    </div>
                </section>
            </div>
            <!--            下-->
            <div>
                <div class="input-group-text">
                    <p class="form-control pt-5"
                       style="background-color: rgb(255, 201, 123); color: black;">候位表</p>
                </div>
                <form>
                    <div class="p-0 flex-grow-1" id="tablenumber" style="display: none">
                        <div class="btn-group btn-group-toggle flex-grow-1" data-toggle="buttons">
                            <form>
                                <label class="btn btn-secondary active" id="btmon" for="option1">
                                    <input type="radio" name="optionsn" id="option1" value="on">on
                                </label>
                                <label class="btn btn-secondary" id="btmoff" for="option2">
                                    <input type="radio" name="options" id="option2" value="off">off
                                </label>
                            </form>
                        </div>
                    </div>
                </form>
                <div class=" btn-group btn-group-toggle" style="display: none" id="tablewait">
                    <div class="table-responsive">
                        <table class="table table-striped m-0">
                            <thead>
                            <tr class="text-center">
                                <th class="col-4">電話</th>
                                <th class="col-4">姓名</th>
                                <th class="col-4">人數</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <section class="section p-0 " id="faq2"
                             style="overflow-y: scroll; height: 300px">
                        <div class="section-content container ">
                            <div class="row">
                                <div class="col-12 p-0">
                                    <article class="faq p-0" id="selectStandByResult"></article>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!--      第二欄    -->
        <div class="col-2 p-0 border border-left " style="z-index: 1;">
            <div class="input-group-text ">
                <p class="form-control pt-5 "
                   style="background-color: rgba(253, 72, 72, 0.65); color: black;">現場桌況</p>
            </div>
            <div class="btn-group btn-group-toggle" style="display: block">
                <div class="table-responsive ">
                    <table class="table table-striped m-0">
                        <thead>
                        <tr class="text-center">
                            <th class="col-4 pl-2">桌號</th>
                            <th class="col-3 pl-0">姓名</th>
                            <th class="col-4 pl-0">人數</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <section class="section p-0 " id="faq3"
                     style="overflow-y: scroll; height: 889px">
                <div class="section-content container">
                    <div class="row">
                        <div class="col-12 p-0 nowuse">
                            <article class="faq p-0"></article>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!--      第三欄    -->
        <div class="col-8 p-0 ">
            <div class="d-flex table3CU"
                 style="flex-wrap: wrap; align-content: space-around; height: 100%"></div>
        </div>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- jquery 3.4.1 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
    //動態產生已設定的訂位時段
    let timelisto = [<c:forEach var="time" items="${time}">'${time}', </c:forEach>];
    let timelist = timelisto.filter(el => el);
    list();
    $(function () {
        let currentDate = new Date();
        $("#today").click(function () {
            $("#datepicker").val(formatDate(currentDate));
            list();
        });
        $("#datepicker").datepicker({
            showButtonPanel: false,
            dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"],
            dateFormat: "yy-mm-dd",
            maxDate: "+1m",
            minDate: "0",
        });
    });


    //動態產生右邊的桌圖區
    let tables = JSON.parse(JSON.stringify(${table}));
    tableview(tables);

    //訂位資料
    let foodorder = JSON.parse(JSON.stringify(${list}));
    orderlist(foodorder);
    //產生明細
    detail(foodorder);


    //現場用餐資料
    let noworder = JSON.parse(JSON.stringify(${usejson}));
    now(noworder);
    //產生明細
    detail(noworder);

    //已經有設定坐位的桌號
    let tablehave = JSON.parse(JSON.stringify(${tablehave}));
    //將桌號加上顏色
    tablecolor(tablehave);

    //可以帶位的桌號
    let cantable = $(tables).not(tablehave).toArray();
    //將資料更新可以帶位的選項
    tab(cantable);

    // ajax-換座位
    $(document).ready(function () {
        $('.cantab').focusin(function () {
            //改變前桌號
            let tableB = Number($(this).val());
            $(this).change(function () {
                $(".table3CU").html("");
                //產生框框
                tableview(tables)
                //取得訂單id
                let reid = $(this).closest("form").find('input[name="toid"]').val();
                //改變後桌號
                let tableA = Number($(this).val());
                let yy = "A" + tableA;
                let aa = "#ordertab_" + reid;
                //改字
                $(aa).html(yy);
                //更改隱藏input值
                $(this).closest("form").find('input[name="tableid"]').val(tableA);
                //tablehave移除舊的加入新的
                let a1 = tablehave.indexOf(tableB);
                tablehave.splice(a1, 1);
                tablehave.splice(-1, 0,tableA);
                tablehave.sort(function(a, b) {return a - b;});
                tablehave.filter((item, index) => tablehave.indexOf(item) === index);
                //cantable移除新的，加入舊的
                let a2 = cantable.indexOf(tableA);
                cantable.splice(a2, 1);
                cantable.splice(1, 0, tableB);
                cantable.sort(function(a, b) {return a - b;});
                cantable.filter((item, index) => cantable.indexOf(item) === index);
                //產生顏色
                tablecolor(tablehave);
                //產生選項
                tab(cantable);
                $.ajax({
                    type: "POST",
                    url: "/CGA105G2/TableServlet",
                    data: {
                        action: "totable",
                        toid: reid,
                        table: tableA,
                        date: "${date}",
                        totime: "${totime}",
                    },
                })
            })
        })
    });
    //離席
    function toout(a) {
        let x = "#article_" + a;
        let inputval = "#inputtotal" + a;
        let tableid = Number($(inputval).val());
        $.ajax({
            type: "POST",
            url: "/CGA105G2/TableServlet",
            data: {
                toid: a,
                action: "out",
            },
            success: function () {
                $(x).remove();
                //cantable加入
                cantable.splice(1, 0, tableid);
                cantable.sort(function(a, b) {return a - b;});
                cantable.filter((item, index) => cantable.indexOf(item) === index);
                //tablehave移除
                let a1 = tablehave.indexOf(tableid);
                tablehave.splice(a1, 1);
                //產生顏色
                tablecolor(tablehave);
                //產生選項
                tab(cantable);
            },
        });
    };
    //報到
    function tocheckin(a) {
        let text="#inputtotal" + a;
        let x = "#article_" + a;
        const b=$(text).val();
        $.ajax({
            type: "POST",
            url: "/CGA105G2/TableServlet",
            data: {
                toid: a,
                table: b,
                action: "check",
                date: "${date}",
                totime: "${totime}",
            },
            success: function (response) {
                $(x).remove();
                const list = JSON.parse(JSON.stringify(response))
                noworder = list;
                now(noworder);
                const cantable = $(tables).not(tablehave).toArray();
                tab(cantable);
            },
        });
    };


    $(function () {
        $("#datepicker").change(function () {
            list();
        })
    });
    $(function () {
        $("#option1").click(function () {
            $("#tablewait").css("display", "block");
        });
        $("#option2").click(function () {
            $("#tablewait").css("display", "none");
        })
    });

    function list() {
        $("#Select01").html("<option class=\"btn btn-secondary form-control  p-0\" selected><c:if test="${totime !=null}">${totime}</c:if><c:if test="${totime ==null}">Time</c:if></option>");
        for (let e in timelist) {
            let option = $("<option>").val(timelist[e]).text(timelist[e]);
            $("#Select01").append(option)
        }
    };

    function formatDate(date) {
        let d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();
        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        return [year, month, day].join('-');
    };

    //動態產生右邊的桌圖區
    function tableview(tb) {
        // 定義變數，在使用變數
        const table3CU = document.querySelector('.table3CU');
        table3CU.innerHTML = '';
        for (let i of tb) {
            table3CU.innerHTML +=
                `
            <div class="col-md-3 p-5">
                <div class="card border-0" id="A\${i}" style="height: 100px !important;">
                    <div class="card-body p-19 p-md-20 d-flex"
                         style="border: 1px solid black">
                        <svg width="16" height="16" viewBox="0 0 16 16">
                            <path d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                        </svg>
                        <h3 class="fs-6 fs-lg-9">A\${i}</h3>
                    </div>
                </div>
            </div>
            `;
        }
    };
    // 調整成今天才會顯示候位選項
    $(function () {
        $("#Select01").change(function () {
            if ($("#Select01").val() === "Time") {
                $("#tablenumber").css("display", "none");
            } else {
                $("#tablenumber").css("display", "flex");
            }
        })
    });

    function orderlist(obder) {
        // 定義變數，在使用變數
        let today = new Date();
        let dd = today.getDay();
        let searchdate=$("#datepicker").val();
        let seardate =new Date(searchdate);
        let finday=seardate.getDay();

        const toorder = document.querySelector('.toorder');
        toorder.innerHTML = '';
        for (let i of obder) {
            let a = "桌號";
            let bb="button";
            if (i.TABLE > 0) {
                a = i.TABLE;
            }
            if(dd!==finday){
                bb="hidden";
            }
            toorder.innerHTML += `
            <article class="faq p-0" id="article_\${i.REN_ID}">
                                    <header class="faq-header" data-toggle="collapse"
                                            data-target="#faq1-item-\${i.REN_ID}" aria-expanded="false">
                                        <table class="table table-striped m-0">
                                            <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="col-4">\${i.NAME}</td>
                                                <td class="col-4">\${i.PHONE}</td>
                                                <td class="col-4">\${i.PepQ}位</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="faq-toggle" style="display: none">
                                            <i class="material-icons faq-toggle-closed">add</i>
                                            <i class="material-icons faq-toggle-open">remove</i>
                                        </div>
                                    </header>
                                    <div class="faq-body collapse" id="faq1-item-\${i.REN_ID}">
                                        <div class="card-body pt-0 pb-1" data-mh="gift-vouchers">
                                            <div>
                                                <form class="d-flex col-12" >
                                                    <input type="hidden" name="tableid" id="inputtotal\${i.REN_ID}" value=\${i.TABLE} class="d-none">
                                                    <select class="btn btn-dark p-0 w-100 cantab">
                                                        <option value=\${i.TABLE} selected="selected">A\${a}</option>
                                                    </select>
                                                    <input type="hidden" name="toid" value="\${i.REN_ID}" class="d-none">
                                                </form>
                                            </div>
                                            <div id="orderD\${i.REN_ID}"></div>
                                            <div class="border-lighter border-top my-2"></div>
                                            <div class="d-flex align-items-center justify-content-between mt-2">
                                                <div class="text-dark">TOTAL</div>
                                                <div class="price-value fs-3">$\${i.PRICE}</div>
                                            </div>
                                            <form>
                                                <input type=\${bb} class="btn p-0 w-100 " onclick="tocheckin(\${i.REN_ID},)" style="background-color: rgba(253, 72, 72, 0.65); color: black;" value="報到">
                                            </form>
                                        </div>
                                    </div>
                                </article>
            `;
        }
    };

    function now(ulist) {
        // 定義變數，在使用變數
        const useorder = document.querySelector('.nowuse');
        useorder.innerHTML = '';
        for (let e of ulist) {
            let b = "桌號";
            if (e.TABLE > 0) {
                b = e.TABLE;
            }
            let p = "離席"
            let to = "toout(" + e.REN_ID + "," + b + ")";
            if (e.PRICE === 0) {
                p = "點餐中";
            }
            useorder.innerHTML += `
            <article class="faq p-0" id="article_\${e.REN_ID}">
                                    <header class="faq-header" data-toggle="collapse"
                                            data-target="#faq1-item-\${e.REN_ID}" aria-expanded="false">
                                        <table class="table table-striped m-0">
                                            <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="col-4" id="ordertab_\${e.REN_ID}">A\${e.TABLE}</td>
                                                <td class="col-4">\${e.NAME}</td>
                                                <td class="col-4">\${e.PepQ}位</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="faq-toggle" style="display: none">
                                            <i class="material-icons faq-toggle-closed">add</i>
                                            <i class="material-icons faq-toggle-open">remove</i>
                                        </div>
                                    </header>
                                    <div class="faq-body collapse" id="faq1-item-\${e.REN_ID}">
                                        <div class="card-body pt-0 pb-1" data-mh="gift-vouchers">
                                            <div>
                                                <form class="d-flex col-12">
                                                    <input type="hidden" name="tableid" id="inputtotal\${e.REN_ID}" value=\${e.TABLE} class="d-none">
                                                    <select class="btn btn-dark p-0 w-100 cantab">
                                                        <option value=\${e.TABLE} selected="selected">A\${b}</option>
                                                    </select>
                                                    <input type="hidden" name="toid" value="\${e.REN_ID}" class="d-none">
                                                </form>
                                            </div>
                                            <div id="orderD\${e.REN_ID}"></div>
                                            <div class="border-lighter border-top my-2"></div>
                                            <div class="d-flex align-items-center justify-content-between mt-2">
                                                <div class="text-dark">TOTAL</div>
                                                <div class="price-value fs-3">$\${e.PRICE}</div>
                                            </div>
                                            <form>
                                                <input type="button"  id="ipbut_\${e.REN_ID}"  onclick=\${to} class="btn p-0 w-100" style="background-color: rgba(253, 72, 72, 0.65); color: black;" value="\${p}">
                                            </form>
                                        </div>
                                    </div>
                                </article>
            `;
            if (e.PRICE === 0) {
                let str = "#ipbut_" + e.REN_ID;
                $(str).addClass("cantttodo");
            }
        }
    }

    //生成明細
    function detail(oderD) {
        for (let e of oderD) {
            const detil = e.DETAIL;
            const id = "orderD" + e.REN_ID;
            const order = document.getElementById(id.toString());
            order.innerHTML = '';
            for (let i of detil) {
                order.innerHTML += `<div class="text-dark">\${i.EAT_NAME}<span>×</span>\${i.EAT_Q}</div>`;
            }
        }
    }

    //改變桌子顏色
    function tablecolor(tablehave) {
        $(".touseeating").removeClass("touseeating");
        for (let e of tablehave) {
            let id = "#A" + e;
            $(id).addClass("touseeating");
        }
    }

    function tab(list) {
        list.sort(function(a, b) {return a - b;});
        list.filter((item, index) => tablehave.indexOf(item) === index);
        $(".cantab").each(function () {
            let tableid = $(this).closest("form").find('input[name="tableid"]').val();
            let text = `<option value=\${tableid} selected="selected">A\${tableid}</option>`;
            for (let e of list) {
                if (e === tableid) {
                    continue;
                }
                text += `<option value=\${e} >A\${e}</option>`;
            }
            $(this).html("");
            $(this).html(text);
        })
    };


</script>
<!-- 	==========候位============== -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    $(document).ready(function () {
        let open =${open};
        if (open == 1) {
            $('#tablenumber').attr("style", "display: flex;");
            $('#tablewait').attr("style", "display: block;");
            let a = $("div#tablenumber").closest("form").find('label.active>input').attr("id");
            if (a === option2) {
                $('#btmoff').removeClass("active focus");
            }
            $('#btmon').addClass("active focus");
            wattingsearch();
        }
    });
    if ($('#option2').is(':checked')) {
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: {action: "offStoreSts", onOff: "off"},
        })
    }
    $('#option2').click(function () {
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: {action: "offStoreSts", onOff: "reset"},
            success: function () {
            }
        })
    });
    // --------------------------
    let onOff = <%=request.getAttribute("onOff") %>;
    let onOff2 = <%=sts%>;
    // onOff2=${sts}
    // --------------------------
    $(document).ready(function () {
        var selectedOption = localStorage.getItem("selectedOption");
        if (selectedOption) {
            $("input[value='" + selectedOption + "']").prop("checked", true);
        }
        $("input[name='options']").change(function () {
            localStorage.setItem("selectedOption", $(this).val());
        });
        let Stasts = $('input[name="options"]:checked').val();
        if ($('#option2').is(':checked')) {
            $.ajax({
                type: "POST",
                url: "/CGA105G2/standby",
                data: {action: "offStoreSts", onOff: "off"},
            })
        }
        $('#option2').click(function () {
            $.ajax({
                type: "POST",
                url: "/CGA105G2/standby",
                data: {action: "offStoreSts", onOff: "off"},
            })
        });
        // ============Select===================
        $('#option1').click(wattingsearch());
        // ------------------------------------------
        // ---------doc.ready--------
    });
    // ---------doc.ready--------
    //顯示時間
    function showTime() {
        let callVal = ('#callMem').val();
        let sec = 10
        setInterval(function () {
            sec--;
            if (sec <= 0) {
                callMember();
            }
            callVal.val(sec);
        }, 1000);
    }

    //通知
    function callMember(a) {
        let staId = a;
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: {
                action: "tocall",
                toid: staId,
            },
            success: function () {
            }
        });
        setTimeout(function () {
                $.ajax({
                    type: "POST",
                    url: "/CGA105G2/standby",
                    data: $('#noticeStandby').serialize(),
                    success: function (data) {
                        let idclass="#staHeader-"+staId;
                        $(idclass).css("display", "none");
                        $('#callMem').attr("disabled", "disabled");
                        $("#option1").trigger('click');
                    }
                });
            }, 10000
        )
    };
    //報到
    function checkMem(a) {
        let idclass="#checkStandby-"+a;
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: $(idclass).serialize(),
            success: function (response) {
                let idclass2="#staHeader-"+a;
                $(idclass2).remove();
                const list = JSON.parse(JSON.stringify(response))
                noworder.push(list);
                now(noworder);
                const cantable = $(tables).not(tablehave).toArray();
                tab(cantable);
            },
        });
    };

    //查詢候位資料庫
    function wattingsearch() {
        $.ajax({
            type: "POST",
            url: "/CGA105G2/standby",
            data: {action: "selectStandBy", onOff: "on"},
            dataType: "json",
            success: function (data) {
                let html = "";
                for (let i = 0; i < data.length; i++) {
                    html += `<div id="staHeader-\${data[i].staId}">
                                  <header class="faq-header" data-toggle="collapse" data-target="#faq2-item-\${data[i].staId}"aria-expanded="false" >
                                    <table class="table table-striped m-0">
                                        <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="fs-2 col-5 p-2 py-3 my-auto align-middle">\${data[i].staPhone}</td>
                                                <td class="col-4 pl-0 ">\${data[i].staName}</td>
                                                <td class="">\${data[i].staNumber}位</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="faq-toggle" style="display: none">
                                        <i class="material-icons faq-toggle-closed">add</i>
                                        <i class="material-icons faq-toggle-open">remove</i>
                                    </div>
                                   </header>
                                    <div class="faq-body collapse row mx-auto" id="faq2-item-\${data[i].staId}" style="width: 100%; justify-content: center">
                                        <div class="radio-buttons-group w-100 justify-content-center">
                                            <form  class="d-block">
                                                <input type="button" id="callMem-\${data[i].staId}" value="通知" onclick="callMember(\${data[i].staId})" class="btn btn-outline-info w-auto text-dark btn-lg " data-value="call" >
                                                <input type="submit" class ="d-none">
                                                <input type="hidden" name="staId"  id="callStaId-\${data[i].staId}"   value="\${data[i].staId}">
                                                <input type="hidden" name="staStatus" value="\${data[i].staStatus}">
                                                <input type="hidden" name="action" value="delete">
                                            </form>
                                            <form id="checkStandby-\${data[i].staId}"  class="d-block">
                                                <input type="button" id="checkMemBtn-\${data[i].staId}" value="報到" onclick="checkMem(\${data[i].staId})" class="btn btn-outline-info w-auto text-dark btn-lg" data-value="come" >
                                                <input type="hidden" name="staId"  id="checkStaId-\${data[i].staId}"     value="\${data[i].staId}">
                                                <input type="hidden" name="staName"    value="\${data[i].staName}">
                                                <input type="hidden" name="staPhone"   value="\${data[i].staPhone}">
                                                <input type="hidden" name="staNumber"  value="\${data[i].staNumber}">
                                                <input type="hidden" name="date" value="${date}"     class="d-none">
                                                <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                <input type="hidden" name="action" value="checkMem">
                                                <input type= "submit" class="d-none">
                                            </form>
                                        </div>
                                    </div>
                                </div>`;
                }
                $("#selectStandByResult").html(html);
            }
        });
    }
</script>

<!-- Vue -->
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    $("a:contains(🚩帶位)").closest("a").addClass("active disabled topage");
</script>


</body>

</html>