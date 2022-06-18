const URL_API = "http://localhost:8080/api/v1";

// Lấy thông tin id trên url
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

let user;
let images = [];
let selectedFileId;

// BEGIN Elements
const nameElement = document.getElementById("fullname");
const emailElement = document.getElementById("email");
const phoneElement = document.getElementById("phone");
const addressElement = document.getElementById("address");
const btnSaveElement = document.getElementById("btn-save");

const oldPasswordElement = document.getElementById("old-password");
const newPasswordElement = document.getElementById("new-password");
const btnChangePasswordElement = document.getElementById("btn-change-password");

const btnForgotPasswordElement = document.getElementById("btn-forgot-password");

const imageContainerElement = document.querySelector(".image-container");
const btnShowImagesElement = document.getElementById("btn-modal-image");
const btnUpdateImageElement = document.getElementById("btn-chose-image");
const btnDeleteImageElement = document.getElementById("btn-delete-image");
const btnUploadImageElement = document.getElementById("avatar");
const avatarPreviewElement = document.getElementById("avatar-preview");
// END Elements

// BEGIN Handle events
// Update user
btnSaveElement.addEventListener("click", async function () {
  try {
    let res = await axios.put(`${URL_API}/users/${id}`, {
      name: nameElement.value,
      phone: phoneElement.value,
      address: addressElement.value,
    });
    if (res.data) {
      window.location.href = "/";
    }
  } catch (error) {
    console.log(error);
  }
});

// Update password
btnChangePasswordElement.addEventListener("click", async function () {
  try {
    await axios.put(`${URL_API}/users/${id}/update-password`, {
      oldPassword: oldPasswordElement.value,
      newPassword: newPasswordElement.value,
    });
    alert("Đổi mật khẩu thành công");
  } catch (error) {
    let message = error.response.data.message;
    alert(message);
  } finally {
    oldPasswordElement.value = "";
    newPasswordElement.value = "";
  }
});

// Forgot password
btnForgotPasswordElement.addEventListener("click", async function () {
  try {
    let res = await axios.put(`${URL_API}/users/${id}/forgot-password`);
    alert(`Mật khẩu mới là: ${res.data}`);
  } catch (error) {
    console.log(error);
  }
});

// Reset selected image
btnShowImagesElement.addEventListener("click", () => {
  resetSelectImage();
  renderImages(images);
});

// Update avatar
btnUpdateImageElement.addEventListener("click", () =>
  updateAvatar(selectedFileId)
);

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

// Delete image
btnDeleteImageElement.addEventListener("click", async () => {
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
btnUploadImageElement.addEventListener("change", async () => {
  try {
    let formData = new FormData();
    formData.append("file", btnUploadImageElement.files[0]);
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
  nameElement.value = user.name;
  emailElement.value = user.email;
  phoneElement.value = user.phone;
  addressElement.value = user.address;
  if (user.avatar == null || user.avatar == "") {
    avatarPreviewElement.src = "https://via.placeholder.com/200";
  } else {
    avatarPreviewElement.src = `${URL_API}/users/${id}/files/${user.avatar}`;
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
  addressElement.innerHTML = html;
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
  imageContainerElement.innerHTML = html;
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
  btnUpdateImageElement.disabled = false;
  btnDeleteImageElement.disabled = false;
};

const resetSelectImage = () => {
  selectedFileId = null;
  btnUpdateImageElement.disabled = true;
  btnDeleteImageElement.disabled = true;
};

const init = async () => {
  await getProvinces();
  await getUser(id);
  await getImages(id);
};

init();
