// API:  https://provinces.open-api.vn/api/p/

// Truy cap
const addressEl = document.querySelector("#address");
const btnSave = document.querySelector("#btn-save");

// tao user
btnSave.addEventListener("click", async () => {
  try {
    // lay thong tin o tat ca cac o input
      const nameEl = document.querySelector("#fullname");
      const phoneEl = document.querySelector("#phone");
      const emailEl = document.querySelector("#email");
      const passwordEl = document.querySelector("#password");
      
    // goi api
    let res = await axios.post("http://localhost:8080/api/v1/users", {
      name : nameEl.value,
      address : addressEl.value,
      phone : phoneEl.value,
      email : emailEl.value,
      password : passwordEl.value,
    });
      console.log(res);

    // neu thanh cong thi dieu huong ve trang index
    if (res.data) {
      window.location.href = "/";
    }
  } catch (error) {
    console.log(error);
  }
});

const getProvinces = async () => {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
    renderProvice(res.data);
  } catch (error) {
    console.log(error);
  }
};

const renderProvice = (array) => {
  let html = "";
  array.forEach((province) => {
    html += `<option value="${province.name}">${province.name}</option>`;
  });
  addressEl.innerHTML = html;
};

getProvinces();
