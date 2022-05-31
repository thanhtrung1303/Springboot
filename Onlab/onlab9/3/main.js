let money = 35;

const buyIphone = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33) {
      resolve("mua iphone thành công");
    } else {
      reject("Không đủ tiền mua iphone");
    }
  });
};

const buyAirPod = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33 + 5) {
      resolve("mua thêm airpod");
    } else {
      reject("Không đủ tiền mua airpod");
    }
  });
};

// async function buy1() => {

// }

const buy = async () => {
    try {
        let res = await buyIphone();
        console.log(res)

        let res1 = await buyAirPod();
        console.log(res1);
    } catch(error) {
        console.log(error)
    }
    return "về nhà"
}

// buy()

// console.log(buy())

buy()
    .then(res => console.log(res))
    .catch(error=>console.log(error))
