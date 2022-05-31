// Một số công việc thực hiện:
// Đi làm:: 4s
// Nghỉ trưa: 2s
// Đi nhậu : 3s

// function sum(a,b){
// return a + b;
// }

// const sum1 = function (a, b) {
//     return a + b;
// }

// const sum3 = (a, b) => a + b;

function doTask1(taskName, callback) {
    console.log("Bắt đầu công việc hàng ngày");
    console.log(`Thực hiện công việc ${taskName}`);

    setTimeout(function () {
        console.log(`Thực hiện xong công việc ${taskName}`);
        callback();
    }, 4000)
}

function doTask2(name, callback) {
  console.log(`Thực hiện công việc ${name}`);
  setTimeout(function () {
    callback();
  }, 2000);
}

function doTask3(name, callback) {
  console.log(`Thực hiện công việc ${name}`);
  setTimeout(function () {
    callback();
  }, 3000);
}

function finish() {
  console.log("Kết thúc công việc");
}

// doTask1("đi làm", function () {
//     doTask2("nghỉ trưa", function () {
//         doTask3("đi nhậu", finish)
//     })
// })

doTask1("đi làm", finish)
doTask2("nghỉ trưa", finish)
doTask3("đi nhậu", finish)

