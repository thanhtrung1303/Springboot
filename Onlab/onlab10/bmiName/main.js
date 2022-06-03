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
  if (bmi < 15)  return "super thin";
  else if (bmi <= 18) return "thin";
  else if (bmi <= 24) return "normal";
  else if (bmi <= 30) return "fat";
  else return "super fat";
}
