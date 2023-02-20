<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <style>
        .thispage{
            cursor: url("/CGA105G2/assets/images/icons8-hammer-80.png"), auto;
        }
        .thispage:active{
            cursor: url("/CGA105G2/assets/images/icons8-hammer-2.png"), auto;
        }


        #time,
        #combo {
            font-weight: bolder;
            font-size: 17px;
        }
        #rule{
            border: dashed black ;
            background-color: rgba(32, 178, 171, 0.671);
        }
        .cell {
            width: 225px;
            height: 225px;
            border: 5px solid black;
            line-height: 0;
        }
    </style>
</head>

<body>

<div class="contentmmm d-inline-flex">
    <div id="rule" style="text-align:center;">
        <div class="d-block">
            <div id="time" class="col-10">
                剩餘時間:15s
            </div>
        </div>
        <div class="d-block">
            <div id="combo" class="col-10">
                成績分數:0
            </div>
        </div>
        <div class="d-inline-flex justify-content-center col-12">
            <input class="col-5 p-0" type="button" id="stareeee" value="開始">
            <form  class="col-5 p-0" METHOD="post" ACTION="/CGA105G2/LonginServlet" style="margin:0px;padding:0px;border:0px;">
                <input type="submit" value="離開"  class="p-0" style="width:100%">
                <input type="hidden" value="quithitgame" name="action" style="width:100%">
                <input type="hidden" name="memId1" value="${member1.memId}">
                <input type="hidden" name="memId2" value="${member2.memId}">
                <input type="hidden" name="SearchMemberId" value="${member2.memId}">
            </form>
        </div>
        <div>
            <b>遊戲說明</b>
            <p>敲地鼠遊戲，敲爆朋友的頭像，來獲得點數吧！（滿分 100)</p>
        </div>
    </div>
    <c:if test="${not empty member1.memPic}">
        <div class="thispage">
            <div class="rowmmmm">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(0)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(1)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(2)">
            </div>
            <div class="rowmmmm">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(3)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(4)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(5)">
            </div>
            <div class="rowmmmm">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(6)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(7)">
                <img src="${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}" class="cell" title="state" onclick="getcombo(8)">
            </div>
        </div>
    </c:if>
    <c:if test="${empty member1.memPic}">
        <div class="thispage">
            <div class="row">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(0)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(1)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(2)">
            </div>
            <div class="row">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(3)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(4)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(5)">
            </div>
            <div class="row">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(6)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(7)">
                <img src="/CGA105G2/assets/images/state.png" class="cell" title="state" onclick="getcombo(8)">

            </div>
        </div>
    </c:if>
</div>
<script>
    var btn = document.getElementById("stareeee");
    var time = document.getElementById("time");//找到時間
    var combo = document.getElementById("combo");//找到分數
    var thedog = document.getElementsByClassName("cell");
    var flag = 0; //遊戲開始為1，停止為0
    var sec = 0;
    var count = 0;
    var wait = new Array();//放生器狗狗事件，陣列有100個位置
    btn.addEventListener("click", gamestart);//規劃點選動作

    function gamestart() { //遊戲開始
        flag = 1;
        count = 0;
        sec = 15;
        combo.textContent = `成績分數:0`;
        time.textContent = `剩餘時間:15s`;
        btn.removeEventListener("click", gamestart);//關閉 btn，不要再讓人去按他觸發多餘的 gamestart()

        var start = setInterval(function () { //倒數計時開始，每秒做顯示剩餘時間
            if (sec == 0) { //0則停止重複延遲
                clearInterval(start);
                flag = 0;
                window.alert(`你的分數：\${count}`);
                btn.addEventListener("click", gamestart);//開放點選
            }
            else {
                sec--;
                time.textContent = `剩餘時間:\${sec}s`;
            }
        }, 1000);

        for (let i = 0; i < 100; i++) { //100次機會
            let timeout = Math.floor(Math.random() * 57000);  //range=0.1~56.9,放大到毫秒=>0~59999
            let who = Math.floor(Math.random() * 9); //隨機 0~8 處
            let delay = Math.floor(Math.random() * 3) + 1; //隨機 1~3 秒
            setTimeout(function () { //指定好100隻的時間軸
                showdog(who, delay, i);
            }, timeout);
        }
    }
    //觸發紅燈事件
    function showdog(who, delay, id) {
        if (thedog[who].title != "state") {//如果狗狗不是等待中，那麼往下一個位置推，並再晚0.5秒出現
            let next = (who + 1) % 9;
            setTimeout(function () {
                showdog(next, delay, id);
            }, 500);
        }
        else {//確定該位置是黃色，可以進行紅色事件
            <c:if test="${empty member2.memPic}">
            thedog[who].src = "/CGA105G2/assets/images/state.png";
            </c:if>
            <c:if test="${not empty member2.memPic}">
            thedog[who].src = "${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member2.memId}";
            </c:if>
            thedog[who].title = "angry";
            thedog[who].alt = id;
            thedog[who].style.backgroundColor = "red";

            wait[id] = setTimeout(function () {//N秒後回到等待狀態，並記下此事為wait[id]
                <c:if test="${empty member1.memPic}">
                thedog[who].src = "/CGA105G2/assets/images/state.png";
                </c:if>
                <c:if test="${not empty member1.memPic}">
                thedog[who].src = "${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}";
                </c:if>
                thedog[who].title = "state";
                thedog[who].style.backgroundColor = null;
            }, delay * 1000);
        }
    }


    function getcombo(who) {//每次按下指定鍵時 計分且將紅色變綠色  who=0~8
        if (thedog[who].title == "angry" && flag) {
            thedog[who].src = "/CGA105G2/assets/images/onepoint.jpg";
            thedog[who].title = "angry";
            thedog[who].style.backgroundColor = "green";
            id = thedog[who].alt;
            clearTimeout(wait[id]); //取消之前的倒數恢復

            /*加分*/
            count++;
            combo.textContent = `成績分數:\${count}`;

            /*1秒後改等待狀態*/
            setTimeout(function () { //red 多久之後自己變回 yellow
                <c:if test="${empty member1.memPic}">
                thedog[who].src = "/CGA105G2/assets/images/state.png";
                </c:if>
                <c:if test="${not empty member1.memPic}">
                thedog[who].src = "${pageContext.request.contextPath}/LonginServlet?action=getOtherMemberPhoto&memId=${member1.memId}";
                </c:if>
                thedog[who].title = "state";
                thedog[who].alt = null;
                thedog[who].style.backgroundColor = null;
            }, 1000);
        }
    }

</script>

</body>

</html>