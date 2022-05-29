// Sử dụng HTML-attribute
function sayHello() {
    alert('Xin chao cac ban 1');
}

//Sử dụng DOM property
const btn2 = document.getElementById('btn-2');
// btn2.onclick = function () {
//     alert('Xin chao cac ban 2');
// }

function sayHello2() {
    alert('Xin chao cac ban 2')
}
btn2.onclick = sayHello2;

// Sử dụng addEventListener
const btn3 = document.getElementById("btn-3");
btn3.addEventListener('click', function () {
    alert("Xin chao cac ban 3");
})
