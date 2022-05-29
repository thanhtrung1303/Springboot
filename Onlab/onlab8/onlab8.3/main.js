const text = document.getElementById("text");
const btn1 = document.getElementById("btn-1");
const btn2 = document.getElementById("btn-2");
const btn3 = document.getElementById("btn-3");

btn1.addEventListener("click", function () {
  //B1: ramdom quote
  let quotes = ["Quote1", "Quote", "Quote3", "Quote4"];

  let randomIndex = Math.floor(Math.random() * quotes.length);
  let randomQuote = quotes[randomIndex];

  //B2: Chen lai noi dung cho the
  text.innerText = randomQuote;
});

btn2.addEventListener("click", function () {
  //B1: random color
  let hexColor = randomHexColor();

  //B2: thay doi mau cho the p
  text.style.color = hexColor;
});

function randomHexColor() {
  return "#" + Math.floor(Math.random() * 16777215).toString(16);
}

btn3.addEventListener("click", function () {
  //B1: random color
  let rgbColor = randomRgbColor();

  //B2: thay doi mau cho the p
  text.style.backgroundColor = rgbColor;
});

function randomRgbColor() {
  var o = Math.round,
    r = Math.random,
    s = 255;
  return (
    "rgba(" +
    o(r() * s) +
    "," +
    o(r() * s) +
    "," +
    o(r() * s) +
    "," +
    r().toFixed(1) +
    ")"
  );
}
