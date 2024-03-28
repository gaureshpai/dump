console.log("Script Loaded")

var countdownElemet = document.getElementById('countdown')
var bgimage = document.getElementById('bg-image')
var initial =  countdownElemet.innerHTML
console.log(bgimage)

setInterval(function(){
    initial>0?
    initial -=1:0;
    countdownElemet.innerHTML = initial;
    var back = initial % 2 == 0 ? 'Certificate 22.jpg' :'Certificate 21.jpg';
    bgimage.src = back;
    console.log(bgimage)
    console.log('Running')
},1000);

