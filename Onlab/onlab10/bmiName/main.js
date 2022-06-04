const height = document.getElementById("height");
const weight = document.getElementById("weight");
const btngetbmi = document.getElementById("btn-getbmi");
const btnpostbmi = document.getElementById("btn-postbmi");
const textContentGet = document.querySelector(".textContent-get");
const textContentPost = document.querySelector(".textContent-post");
const textDecription = document.querySelector(".textDecription");

btngetbmi.addEventListener("click", async function () {
  try {
    let res = await axios.get(
      `http://localhost:8080/bmi-get?height=${height.value}&weight=${weight.value}`
    );
    textContentGet.textContent = res.data;
    textDecription.textContent = displayContent(res.data);
  } catch (error) {
    console.log(error.response);
  }
});

btnpostbmi.addEventListener("click", async function () {
  try {
    let res = await axios.post(`http://localhost:8080/bmi-post`, {
      height: height.value,
      weight: weight.value,
    });
    textContentPost.textContent = res.data;
    textDecription.textContent = displayContent(res.data);
  } catch (error) {
    console.log(error.response.data);
  }
});

function displayContent(bmi) {
  if (bmi < 18.5) return "Thiếu cân";
  else if (bmi <= 18) return "Bình thường";
  else if (bmi <= 29.9) return "Thừa cân";
  else return "Béo phì";
}

function checkValidate() {
  let height = height.value;
  let weight = weight.value;

  let isCheck = true;

  if (height == "") {
    setError(height, "Chieu cao không được để trống");
    isCheck = false;
  } else if (!isHeight(height)) {
    setError(height, "Chieu cao không đúng định dạng");
    isCheck = false;
  } else {
    setSuccess(height);
  }

  if (weight == "") {
    setError(weight, "can nang không được để trống");
    isCheck = false;
  } else if (!isWeight(emailValue)) {
    setError(weight, "can nang không đúng định dạng");
    isCheck = false;
  } else {
    setSuccess(weight);
  }

  return isCheck;
}

function setError(ele, message) {
  let parentEle = ele.parentNode;
  parentEle.classList.add("error");
  parentEle.querySelector("small").innerText = message;
}

function setSuccess(ele) {
  ele.parentNode.classList.add("success");
}

function isWeight(weight) {
  return isNaN(number);
}

function isHeight(height) {
  return isNAN(number);
}

const container = document.querySelectorAll('.container');

btnRegister.addEventListener('click', function () {
    Array.from(container).map((ele) =>
        ele.classList.remove('success', 'error')
    );
});
