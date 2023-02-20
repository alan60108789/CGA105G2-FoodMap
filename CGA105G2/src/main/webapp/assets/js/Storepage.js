window.onload = function(){
    
    var a1 = document.getElementById("a1");
    var a2 = document.getElementById("a2");
    var a3 = document.getElementById("a3");
    var a4 = document.getElementById("a4");
    var a5 = document.getElementById("a5");
    var phone = document.getElementById("contentPhone");
    
    var angleleft = document.getElementById("angleleft");
    var angleright = document.getElementById("angleright");
    var pageIdx = 1;

    a1.addEventListener("click", showPhoto);
    a2.addEventListener("click", showPhoto);
    a3.addEventListener("click", showPhoto);
    a4.addEventListener("click", showPhoto);
    a5.addEventListener("click", showPhoto);
    angleleft.addEventListener("click", angleleftFn)
    angleright.addEventListener("click", anglerightFn)

    function showPhoto(){
        pageIdx = Number(this.id.substr(1));
        phone.style.backgroundImage = 'url("../images/05/big/' + this.id +'.jpg")';
    }

    function angleleftFn(){
        pageIdx = pageIdx - 1;
        if(pageIdx < 1){
            pageIdx = 5;
        }
        phone.style.backgroundImage = 'url("../images/05/big/a' + pageIdx +'.jpg")';
    }
    function anglerightFn(){
        pageIdx = pageIdx + 1;
        if(pageIdx > 5){
            pageIdx = 1;
        }
        phone.style.backgroundImage = 'url("../images/05/big/a' + pageIdx +'.jpg")';
    }};
// ==============訂閱按鈕js開始======================
const modalBtn = document.querySelector(".button");
const closeBtn = document.querySelector(".close-btn");
const modal = document.querySelector(".modal-overlay");
const subscription = document.querySelector(".subscription");

modalBtn.addEventListener('click',function(){
    modal.classList.add("ain");
    modal.classList.add("open-modal");
      setTimeout(function(){
          modal.classList.remove("open-modal");
          modal.classList.remove("ain");
      },5000)
});


const button = document.querySelector('.button');
button.addEventListener('click', function() {
    button.classList.toggle('liked')
});


const aaa = 
document.querySelector('#subscribe_store');

button.addEventListener('click', function() {
    if (aaa.innerHTML == "訂閱店家")
    	aaa.innerHTML = "已訂閱";
    else 
        aaa.innerHTML = "訂閱店家";
});



modalBtn.addEventListener('click', function() {
    if (subscription.innerHTML == "從我的最愛移除")
    	subscription.innerHTML = "已加入我的最愛";
    else if(subscription.innerHTML == "已加入我的最愛")
      subscription.innerHTML = "從我的最愛移除";
})

closeBtn.addEventListener('click',function(){
    modal.classList.remove("open-modal");
    modal.classList.remove("ain");
});

// ==============訂閱按鈕結束======================
const Thumbs = document.querySelectorAll('.Thumbs');
for(let i = 0 ; i < Thumbs.length ; i++){
Thumbs[i].addEventListener('click', function() {
    Thumbs[i].classList.toggle('Thumbsed')
})};
