<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    JSONArray list_stat = (JSONArray) request.getAttribute("list_stat");
%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>後台</title>
</head>
<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- header start -->
        <%@ include file="/back-end/01h/nav/navin01.jsp" %>
        <!-- header end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 my-5">
            <div class="table-responsive" style="overflow: hidden !important;">
                <h1 class="h2">🔆已審核</h1>
                <table id="code" class="table table-striped display">
                </table>
            </div>
        </main>

    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(✔️審核)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻店家優惠券)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu5").removeClass("collapse");
    $("#pageSubmenu5 a:contains(🔆已審核)").closest("a").addClass("active disabled bg-white topage");
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js">
</script>
<script>
    const list = [];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list) {
        switch (e) {
            case 1:
                $("#a2").removeClass("disabled");
                $("#a3").removeClass("disabled");
                $("#a4").removeClass("disabled");
                $("#a5").removeClass("disabled");
                break;
            case 2:
                $("#a2").removeClass("disabled");
                break;
            case 3:
                $("#a3").removeClass("disabled");
                break;
            case 4:
                $("#a4").removeClass("disabled");
                break;
            case 5:
                $("#a5").removeClass("disabled");
                break;
        }
    }
</script>


<script>
    $(document).ready(function () {
        const dataSet = [
            <c:forEach var="code" items="${list_stat}">
            ${code},
            </c:forEach>
        ];
        $('#code').DataTable({
            // 設定資料來源區塊(data or ajax….等),
            data: dataSet,
            // 設定資料欄位區塊(columns),
            columns: [
                {data: 'CODE_RTIME', title: '通過日期'},
                {data: 'STORE_NAME', title: '店家'},
                {data: 'CODE_NUM', title: '優惠碼'},
                {data: 'CODE_OFF', title: '折扣'},
                {data: 'CODE_TEXT', title: '備註'},
                {data: 'CODE_NTIME', title: '到期日期'},
            ],
            // 設定欄位元素定義區塊(columnDefs),
            /*設定屬性(預設功能)區塊*/
            searching: true, // 預設為true 搜尋功能，若要開啟不用特別設定
            paging: true, // 預設為true 分頁功能，若要開啟不用特別設定
            ordering: true, // 預設為true 排序功能，若要開啟不用特別設定
            sPaginationType: "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
            // 在初始表格的左上有個可選擇的每頁列數的選單設定
            lengthChange: false, // 呈現選單
            lengthMenu: [5, 10],//顯示筆數設定 預設為[10, 25, 50, 100]
            pageLength: 5, // 預設為'10'，若需更改初始每頁顯示筆數，才需設定
            stateSave: false, // 預設為false 在頁面刷新時，是否要保存當前表格資料與狀態
            destroy: true, // 預設為false 是否銷毀當前暫存資料
            info: true, // 預設為true　是否要顯示"目前有 x  筆資料"
            autoWidth: true, // 預設為true　設置是否要自動調整表格寬度(false代表不要自適應)
            scrollCollapse: false, // 預設為false 是否開始滾軸功能控制X、Y軸
            scrollX: false,
            scrollY: false,
            "dom": "<'col-xs-6'f>" + "<'eight wide column'l>" + 'lrtip', // 設置搜尋div、頁碼div...等基本位置/外觀..等，詳細可看官網
            // 語言區塊(language),
            language: {
                lengthMenu: "顯示 _MENU_ 筆資料",
                sProcessing: "處理中...",
                sZeroRecords: "資料不存在",
                sInfo: "共有 _TOTAL_ 筆資料",
                sInfoEmpty: "目前共有 0 筆紀錄",
                sInfoFiltered: " ",
                sInfoPostFix: "",
                sSearch: "搜尋",
                sUrl: "",
                sEmptyTable: "尚未有資料紀錄存在",
                sLoadingRecords: "載入資料中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "第一頁",
                    sPrevious: "上一頁",
                    sNext: "下一頁",
                    sLast: "最後一頁",
                },
                order: [
                    [0, "desc"]
                ],
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列",
                },
            },
        });
    });
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
</body>

</html>