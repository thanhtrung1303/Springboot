const btnRandomColorName = document.querySelector(".btn_name_color");
const btnRandomHexColor = document.querySelector(".btn_hex_color");
const btnRandomRgbName = document.querySelector(".btn_rgb_color");
const body = document.querySelector("body");

btnRandomColorName.addEventListener("click", async function () {
  try {
    let res = await axios.get("http://localhost:8080/random-color?type=1");
      console.log(res);
      body.style.background = res.data;
  } catch (error) {
    console.log(error.response);
  }
});

btnRandomHexColor.addEventListener("click", async function () {
  try {
    let res = await axios.get("http://localhost:8080/random-color?type=2");
    console.log(res);
    body.style.background = res.data;
  } catch (error) {
    console.log(error.response);
  }
});

btnRandomRgbName.addEventListener("click", async function () {
  try {
    let res = await axios.get("http://localhost:8080/random-color?type=3");
    console.log(res);
    body.style.background = res.data;
  } catch (error) {
    console.log(error.response);
  }
});
