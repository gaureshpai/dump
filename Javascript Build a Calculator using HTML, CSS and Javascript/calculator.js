function add() {
    var act = mainform.calculator.value;
    var b = act.charAt(act.length - 1);

    if (b == '+' || b == '-' || b == '*' || b == '/') {
        mainform.calculator.value = act.substring(0, act.length - 1);
        mainform.calculator.value += '+';
    } 
    else {
        mainform.calculator.value += '+';
    }
}

function sub() {
    var act = mainform.calculator.value;
    var b = act.charAt(act.length - 1);

    if (b == '+' || b == '-' || b == '*' || b == '/') {
        mainform.calculator.value = act.substring(0, act.length - 1);
        mainform.calculator.value += '-';
    } 
    else {
        mainform.calculator.value += '-';
    }
}

function mul() {
    var act = mainform.calculator.value;
    var b = act.charAt(act.length - 1);

    if (b == '+' || b == '-' || b == '*' || b == '/') {
        mainform.calculator.value = act.substring(0, act.length - 1);
        mainform.calculator.value += '*';
    } 
    else {
        mainform.calculator.value += '*';
    }
}

function div() {
    var act = mainform.calculator.value;
    var b = act.charAt(act.length - 1);
    
    if(b == '+' || b == '-' || b == '*' || b == '/') {
        mainform.calculator.value = act.substring(0, act.length - 1);
        mainform.calculator.value += '/';
    } 
    else {
        mainform.calculator.value += '/';
    }
}