var btn = document.getElementById("btn-click");
var mainDiv = document.querySelector("header div")

function onBtnClick(){
    var x = (Math.random() * 255)
    var y = Math.floor(Math.random() * 255)
    
    mainDiv.style.backgroundColor = "rgb(" + x + "," + y + "," + x + ")";
}
// btn.onclick = onBtnClick;

btn.addEventListener('click',onBtnClick);