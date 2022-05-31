const provinceEl = document.querySelector("#province");
const districtEl = document.querySelector("#district");
const communeEL = document.querySelector("#commune");

async function getProvinceList() {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
    console.log(res);

    displayProvinceList(res.data);
  } catch (error) {
    console.log(error);
  }
}

function displayProvinceList(array) {
  let html = "";

  for (let i = 0; i < array.length; i++) {
    html += `<option value="${array[i].code}">${array[i].name}</option>`;
  }
  provinceEl.innerHTML = html;
}

provinceEl.addEventListener("change", getDistrictList);
async function getDistrictList() {
  try {
    let res2 = await axios.get(
      `https://provinces.open-api.vn/api/p/${provinceEl.value}?depth=2`
    );

    displayDistrictList(res2.data.districts);
  } catch (error) {
    console.log(error);
  }
}

function displayDistrictList(districts) {
  let html = "";

  for (let i = 0; i < districts.length; i++) {
    html += `<option value="${districts[i].code}">${districts[i].name}</option>`;
  }
  districtEl.innerHTML = html;
}

districtEl.addEventListener("change", getCommuneList);
async function getCommuneList() {
  try {
    let res3 = await axios.get(
      `https://provinces.open-api.vn/api/d/${districtEl.value}?depth=2`
    );
    displayCommuneList(res3.data.wards);
  } catch (error) {
    console.log(error);
  }
}

function displayCommuneList(wards) {
  let html = "";

  for (let i = 0; i < wards.length; i++) {
    html += `<option value="${wards[i].code}">${wards[i].name}</option>`;
  }
  communeEL.innerHTML = html;
}

window.onload = getProvinceList();
