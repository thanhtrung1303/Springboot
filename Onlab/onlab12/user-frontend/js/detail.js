const URL_API = "http://localhost:8080/api/v1";

// Lấy thông tin id trên url
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

let user;
let images = [];
let selectedFileId;

// truy cap
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const addressEl = document.getElementById("address");
const btnSaveEl = document.getElementById("btn-save");

const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePasswordEl = document.getElementById("btn-change-password");

const btnForgotPasswordEl = document.getElementById("btn-forgot-password");

const imageContainerEl = document.querySelector(".image-container");
const btnShowImagesEl = document.getElementById("btn-modal-image");
const btnUpdateImageEl = document.getElementById("btn-chose-image");
const btnDeleteImageEl = document.getElementById("btn-delete-image");
const btnUploadImageEl = document.getElementById("avatar");
const avatarPreviewEl = document.getElementById("avatar-preview");

btnSaveEl.addEventListener("click", async () => {
  try {
    let res = await axios.put(`${URL_API}/users/${id}`, {
      name: nameEl.value,
      phone: phoneEl.value,
      address: addressEl.value,
    });
    if (res.data) {
      alert("cập nhật thông tin thành công");
    }
  } catch (error) {
    console.log(error);
  }
});

btnChangePasswordEl.addEventListener("click", async () => {
  try {
    await axios.put(`${URL_API}/users/${id}/update-password`, {
      oldPassword: oldPasswordEl.value,
      newPassword: newPasswordEl.value,
    });
    alert("Đổi mật khẩu thành công");
    oldPasswordEl.value = "";
    newPasswordEl.value = "";
  } catch (error) {
    // Xử lý nếu có lỗi xảy ra
    console.log(error.response.data.message);
    alert(error.response.data.message);
  }
});

btnForgotPasswordEl.addEventListener("click", async () => {
  try {
    let res = await axios.put(`${URL_API}/users/${id}/forgot-password`);
    alert("Mật khẩu mới của User: " + res.data);
  } catch (error) {
    // Xử lý nếu có lỗi xảy ra
    console.log(error);
  }
});

btnShowImagesEl.addEventListener("click", () => {
  resetSelectImage();
  renderImages(images);
});

btnUpdateImageEl.addEventListener("click", () => updateAvatar(selectedFileId));

async function updateAvatar(fileId) {
  try {
    let res = await axios.put(`${URL_API}/users/${id}/update-avatar`, null, {
      params: {
        fileId,
      },
    });
    resetSelectImage();
    user = res.data;
    renderUser();
  } catch (error) {
    console.log(error);
  }
}

btnDeleteImageEl.addEventListener("click", async () => {
  // Xoá trên server
  try {
    await axios.delete(`${URL_API}/users/${id}/files/${selectedFileId}`);
  } catch (error) {
    console.log(error);
  }

  // Nếu ảnh bị xoá là avatar -> xoá avatar
  if (selectedFileId == user.avatar) {
    updateAvatar("");
  }

  // Xoá trong mảng images
  const index = images.indexOf(imageFromFileId(selectedFileId));
  if (index > -1) {
    images.splice(index, 1);
  }

  // Render lại
  resetSelectImage();
  renderImages(images);
});

// Upload image
btnUploadImageEl.addEventListener("change", async () => {
  try {
    let formData = new FormData();
    formData.append("file", btnUploadImageEl.files[0]);
    // Upload lên server
    let res = await axios.post(`${URL_API}/users/${id}/upload-file`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    // Thêm vào mảng images
    images.unshift(res.data); // add item to front of array

    // Render lại
    renderImages(images);
  } catch (error) {
    console.log(error);
  }
});
// END Handle events

// Lấy thông tin user
const getUser = async (id) => {
  try {
    let res = await axios.get(`${URL_API}/users/${id}`);
    user = res.data;
    renderUser();
  } catch (error) {
    console.log(error);
  }
};

// Hiển thị thông tin user
const renderUser = () => {
  nameEl.value = user.name;
  emailEl.value = user.email;
  phoneEl.value = user.phone;
  addressEl.value = user.address;
  if (user.avatar == null || user.avatar == "") {
    avatarPreviewEl.src = "https://via.placeholder.com/200";
  } else {
    avatarPreviewEl.src = `${URL_API}/users/${id}/files/${user.avatar}`;
  }
};

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

const getImages = async (id) => {
  try {
    let res = await axios.get(`${URL_API}/users/${id}/files`);
    images = res.data;
    renderImages(images);
  } catch (error) {
    console.log(error);
  }
};

const renderImages = (arr) => {
  let html = "";
  for (let image of arr) {
    let fileId = getFileId(image);
    html += `
      <div class="image-item">
        <img
          src="http://localhost:8080/${image}"
          alt="ảnh"
          onclick=selectImage(${fileId})
          class="${
            fileId == selectedFileId ? "border border-primary shadow" : ""
          }"
        />
      </div>
    `;
  }
  imageContainerEl.innerHTML = html;
};

const getFileId = (image) => {
  let slashIndex = image.lastIndexOf("/");
  return image.substring(slashIndex + 1);
};

const imageFromFileId = (fileId) => {
  return `api/v1/users/${id}/files/${fileId}`;
};

const selectImage = (fileId) => {
  selectedFileId = fileId;
  renderImages(images);
  btnUpdateImageEl.disabled = false;
  btnDeleteImageEl.disabled = false;
};

const resetSelectImage = () => {
  selectedFileId = null;
  btnUpdateImageEl.disabled = true;
  btnDeleteImageEl.disabled = true;
};

const init = async () => {
  await getProvinces();
  await getUser(id);
  await getImages(id);
};

init();
