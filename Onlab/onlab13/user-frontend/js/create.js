const URL_API = "http://localhost:8080/api/v1";

// Elements
const btnSaveElement = document.getElementById("btn-save");
const fullNameElement = document.getElementById("fullname");
const emailElement = document.getElementById("email");
const phoneElement = document.getElementById("phone");
const addressElement = document.getElementById("address");
const passwordElement = document.getElementById("password");

// Tạo user
btnSaveElement.addEventListener("click", async function () {
  try {
    // Lấy thông tin ở tất cả các ô input
    const name = fullNameElement.value;
    const email = emailElement.value;
    const phone = phoneElement.value;
    const address = addressElement.value;
    const password = passwordElement.value;

    // Gọi API
    let res = await axios.post(`${URL_API}/users`, {
      name,
      email,
      phone,
      address,
      password,
    });
    console.log(res);

    // Nếu thành công thì điều hướng về trang index để hiển thị
    if (res.data) {
      window.location.href = "/";
    }
  } catch (error) {
    console.log(error);
  }
});

const getProvinces = async () => {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p");
    let provinces = res.data;
    renderProvinces(provinces);
  } catch (error) {
    console.log(error);
  }
};

const renderProvinces = (arr) => {
  let html = "";
  arr.forEach((province) => {
    html += `<option value="${province.name}">${province.name}</option>`;
  });
  addressElement.innerHTML = html;
};

getProvinces();
