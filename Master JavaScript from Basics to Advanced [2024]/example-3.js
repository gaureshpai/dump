console.log("Script Loaded")

var bgImage = document.getElementById("bg-image")
var countdownElemet = document.getElementById('countdown')

var initial = countdownElemet.innerHTML

 var interval = setInterval(function () {
    initial = initial > 0 ?
        initial - 1 : 0;
    countdownElemet.innerHTML = initial;

    countdownElemet.style.fontSize = initial * 100 +"px";

    bgImage.style.width = initial * 10 + "%";
    if(initial<=0)
    clearInterval(interval)
}, 1000);

