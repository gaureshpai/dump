const container = document.getElementById('container');
let phase1Text = ["I", "You"];
let phase2Text = ["I", "U"];
let phase3Text = "";

let currentPhase = 0;
let interval = 4000;

function createTextGroup(text1, text2) {
            const group = document.createElement('div');
group.classList.add('text-group');

const span1 = document.createElement('span');
span1.textContent = text1;

const heart = document.createElementNS("http://www.w3.org/2000/svg", "svg");
heart.classList.add('heart');
heart.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
heart.setAttribute('viewBox', '0 0 24 24');
const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
path.setAttribute('d', "M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z");
heart.appendChild(path);

const span2 = document.createElement('span');
span2.textContent = text2;

group.appendChild(span1);
group.appendChild(heart);
group.appendChild(span2);

return group;
        }

function updateText() {
    container.innerHTML = '';

let textGroup;

if (currentPhase === 0) {
    textGroup = createTextGroup(phase1Text[0], phase1Text[1]);
currentPhase = 1;
            } else if (currentPhase === 1) {
    textGroup = createTextGroup(phase2Text[0], phase2Text[1]);
currentPhase = 2;
            } else if (currentPhase === 2) {
                const group = document.createElement('div');
group.classList.add('text-group');
const heart = createTextGroup('', phase3Text);
group.appendChild(heart);
textGroup = group;
currentPhase = 3;
            } else {
    textGroup = createTextGroup(phase1Text[0], phase1Text[1]);
currentPhase = 0;
            }

textGroup.classList.add('fade-in-out');
container.appendChild(textGroup);
        }

setInterval(updateText, interval);

updateText();