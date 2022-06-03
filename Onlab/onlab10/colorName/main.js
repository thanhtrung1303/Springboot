const btnRandomColorName = document.querySelector(".btn_name_color");
const backgroundColor = document.querySelector(".background");

btnRandomColorName.addEventListener("click", async function getRandomColor() {
  try {
    let res = await axios.get("http://localhost:8080/random-color?type=1");
      console.log(res);
      backgroundColor.style.background = res.data;
  } catch (error) {
    console.log(error.response);
  }
});
