const button1 = document.querySelector('#btn-1');
const button2 = document.querySelector('#btn-2');
const button3 = document.querySelector('#btn-3');

const text = document.querySelector('#text');

button1.addEventListener('click', randomQuote);

button2.addEventListener('click', function () {
    let ranHex = randomHex();
    text.style.color = ranHex;
});

button3.addEventListener("click", function () {
  let ranRgb = randomRgb();
  text.style.backgroundColor = ranRgb;
});

function randomQuote() {
    let quotes = ["Quote1", "Quote2", "Quote3"];
    let randomIndex = Math.floor(Math.random() * quotes.length);
    let randomQuote = quotes[randomIndex];

    text.innerText = randomQuote;
}

function randomHex () {
    let randomHex = "#" + Math.floor(Math.random() * 16777215).toString(16);
    return randomHex;
}

function randomRgb() {
    let o = Math.round, r = Math.random, s = 255;
    return ("rgba(" +o(r() * s) + "," + o(r() * s) + "," + o(r() * s) + "," + r().toFixed(1) + ")"
    );
}