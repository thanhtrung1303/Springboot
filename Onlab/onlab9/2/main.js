// Promise : Lời hứa
// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max

// let promise = new Promise(function (resolve, reject) {
//     // resolve("thực hiện thành công")
//     reject("thực hiện thất bại")
// })

// console.log(promise);

let money = 40;

const buyIphone = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33) {
      resolve("mua iphone thành công");
    } else {
      reject("Không đủ tiền mua iphone");
    }
  });
};

// console.log(buyIphone());

buyIphone()
  .then((res) => console.log(res))
  .catch((error) => console.log(error));

const buyAirPod = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33 + 5) {
      resolve("mua thêm airpod");
    } else {
      reject("Không đủ tiền mua airpod");
    }
  });
};

// buyIphone()
//     .then((res) => {
//         console.log(res);
//         return buyAirPod();
//     })
//     .then(res => {
//         console.log(res)
//     })
//     .catch((error) => {
//         console.log(error)
//     })
//     .finally(() => {
//         console.log("về nhà")
//     }) ;

Promise.all([buyIphone(), buyAirPod()])
    .then((res) => console.log(res))
    .catch((error) => console.log(error))
