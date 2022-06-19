const URL_API = "/api/v1";


const fullNameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const addressEl = document.getElementById("address");
const passwordEl = document.getElementById("password");
const btnSaveEl = document.getElementById("btn-save");

// Tạo user
btnSaveEl.addEventListener("click", async function () {
  try {
    // Lấy thông tin ở tất cả các ô input
    const name = fullNameEl.value;
    const email = emailEl.value;
    const phone = phoneEl.value;
    const address = addressEl.value;
    const password = passwordEl.value;

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
  addressEl.innerHTML = html;
};

getProvinces();
