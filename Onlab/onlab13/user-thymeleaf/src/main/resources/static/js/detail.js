const URL_API = "/api/v1";

let selectedFileId;

// BEGIN Elements
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

btnSaveEl.addEventListener("click", async function () {
  try {
    let res = await axios.put(`${URL_API}/users/${user.id}`, {
      name: nameEl.value,
      phone: phoneEl.value,
      address: addressEl.value,
    });
    if (res.data) {
      window.location.href = "/";
    }
  } catch (error) {
    console.log(error);
  }
});

// Update password
btnChangePasswordEl.addEventListener("click", async function () {
  try {
    await axios.put(`${URL_API}/users/${user.id}/update-password`, {
      oldPassword: oldPasswordEl.value,
      newPassword: newPasswordEl.value,
    });
    alert("Đổi mật khẩu thành công");
  } catch (error) {
    let message = error.response.data.message;
    alert(message);
  } finally {
    oldPasswordEl.value = "";
    newPasswordEl.value = "";
  }
});

// Forgot password
btnForgotPasswordEl.addEventListener("click", async function () {
  try {
    let res = await axios.put(`${URL_API}/users/${user.id}/forgot-password`);
    alert(`Mật khẩu mới là: ${res.data}`);
  } catch (error) {
    console.log(error);
  }
});

// Reset selected image
btnShowImagesEl.addEventListener("click", () => {
  resetSelectImage();
  renderImages(images);
});

// Update avatar
btnUpdateImageEl.addEventListener("click", () => updateAvatar(selectedFileId));

async function updateAvatar(fileId) {
  try {
    let res = await axios.put(
      `${URL_API}/users/${user.id}/update-avatar`,
      null,
      {
        params: {
          fileId,
        },
      }
    );
    resetSelectImage();
    user = res.data;
    renderUser();
  } catch (error) {
    console.log(error);
  }
}

// Delete image
btnDeleteImageEl.addEventListener("click", async () => {
  // Xoá trên server
  try {
    await axios.delete(`${URL_API}/users/${user.id}/files/${selectedFileId}`);
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
    let res = await axios.post(
      `${URL_API}/users/${user.id}/upload-file`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    // Thêm vào mảng images
    images.unshift(res.data); // add item to front of array

    // Render lại
    renderImages(images);
  } catch (error) {
    console.log(error);
  }
});
// END Handle events

// Hiển thị thông tin user
const renderUser = () => {
  nameEl.value = user.name;
  emailEl.value = user.email;
  phoneEl.value = user.phone;
  addressEl.value = user.address;
  if (user.avatar == null || user.avatar == "") {
    avatarPreviewEl.src = "https://via.placeholder.com/200";
  } else {
    avatarPreviewEl.src = `${URL_API}/users/${user.id}/files/${user.avatar}`;
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

const renderImages = (arr) => {
  let html = "";
  for (let image of arr) {
    let fileId = getFileId(image);
    html += `
      <div class="image-item">
        <img
          src="/${image}"
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
  return `api/v1/users/${user.id}/files/${fileId}`;
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
  addressEl.value = user.address;
};

init();
