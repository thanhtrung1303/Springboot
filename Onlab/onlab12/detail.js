// lay thong tin tren url
let params = new URLSearchParams(window.location.search);
let id = params.get("id");
console.log(id);

//truy cap
const addressEl = document.querySelector("#address");
const nameEl = document.querySelector("#name");
const phoneEl = document.querySelector("#phone");
const emailEl = document.querySelector("#email");
const btnSave = document.querySelector("#btn-save");

//lay thong tin user
const getUser = async (id) => {
  try {
    // b1: goi API
    let res = await axios.get(`http://localhost:8080/api/v1/users/${id}`);
    // b2: hien thi len giao dien
    addressEl.value = res.data.address;
    nameEl.value = res.data.name;
    phoneEl.value = res.data.phone;
    emailEl.value = res.data.email;
  } catch (error) {
    console.log(error);
  }
};

// Xử lý cập nhật thông tin user
btnSave.addEventListener("click", async () => {
  try {
    // lay thong tin o tat ca cac o input
    // const nameEl = document.querySelector("#name");
    // const phoneEl = document.querySelector("#phone");
    // const addressEl = document.querySelector("#address");

    //  Gọi API để cập nhật thông tin user
    let res = await axios.put(`http://localhost:8080/api/v1/users/${id}`, {
      address: addressEl.value,
      name: nameEl.value,
      phone: phoneEl.value,
    });
    console.log(res);

    // Thông báo nếu cập nhật thông tin thành công
    if (res.data) {
      alert("cập nhật thông tin thành công");
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

// Đổi mật khẩu
const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");

// Lắng nghe sự kiện khi người dùng bấm vào nút btnChangePassword
btnChangePassword.addEventListener("click", async function () {
  try {
    // 1. Lấy 2 giá trị oldPassword, newPassword
    let res = await axios.put(
      `http://localhost:8080/api/v1/users/${id}/update-password`,
      {
        oldPassword: oldPasswordEl.value,
        newPassword: newPasswordEl.value,
      }
    );

    // 2. Kiểm tra giá trị có rỗng hay không

    // 3. Gửi API để cập nhật lại password

    // 4. Đóng modal và clear lại giá trị trong các ô input
    alert("doi mat khau thanh cong");
    oldPasswordEl.value = "";
    newPasswordEl.value = "";
  } catch (error) {
    // Xử lý nếu có lỗi xảy ra
    console.log(error.response.data.message);
    alert(error.response.data.message);
  }
});

//Truy cập vào nút quen mật khẩu
const btnForgotPassword = document.getElementById("btn-forgot-password");

// Lắng nghe sự kiện khi người dùng bấm vào nút này
// Quên mật khẩu
btnForgotPassword.addEventListener("click", async function () {
  try {
    // 1. Gọi API đến server
    let res = await axios.post(
      `http://localhost:8080/api/v1/users/${id}/forgot-password`
    );
    // 2. Thông báo password mới (sử dụng alert)
    alert("Mật khẩu mới của User: " + res.data);
  } catch (error) {
    // Xử lý nếu có lỗi xảy ra
    console.log(error);
  }
});

const init = async () => {
  await getProvinces();
  await getUser(id);
};

init();
