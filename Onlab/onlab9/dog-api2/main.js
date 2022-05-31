const btn = document.querySelector("#btn");
const imageEL = document.querySelector(".image img");
const selectEL = document.querySelector("#breed-list");

//api lay giong loai chinh
async function getBreedList() {
  try {
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");
    console.log(res);

    // hien thi danh sach giong loai chinh
    displayBreedList(res.data.message);
  } catch (error) {
    console.log(error);
  }
}

function displayBreedList(obj) {
  let key = Object.keys(obj);

  let html = "";
  for (let i = 0; i < key.length; i++) {
    html += `<option value="${key[i]}">${key[i]}</option>`;
  }
  selectEL.innerHTML = html;
}

btn.addEventListener("click", async function () {
    try {
    let res = await axios.get(`https://dog.ceo/api/breed/${selectEL.value}/images/random`);

    imageEL.src = res.data.message;
  } catch (error) {
    console.log(error);
  }
});

//chen lai noi dung cho phan tu
window.onload = getBreedList();
