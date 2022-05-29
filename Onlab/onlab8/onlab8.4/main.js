// // Lắng nghe sự kiện keydown
// document.addEventListener("keydown", function () {
//   console.log("keydown");
// });

// // Lắng nghe sự kiện keyup
// document.addEventListener("keyup", function () {
//   console.log("keyup");
// });

// // Lắng nghe sự kiện keypress
// document.addEventListener("keypress", function () {
//   console.log("keypress");
// });

// // Lắng nghe sự kiện keydown
// document.addEventListener("keydown", function (event) {
//     console.log(event);
// });

const input = document.querySelector('input');
input.addEventListener("keydown", function (event) {
    if (event.keyCode === 13) {
        // alert(input.value);
        // alert(this.value);
        alert(event.target.value);
    }
})