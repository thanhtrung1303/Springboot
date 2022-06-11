// Khai báo mảng user ban đầu để hứng dữ liệu từ phía backend
let users = [];

const tableEl = document.querySelector("table");
const messageEl = document.querySelector(".message");
const tableBodyEl = document.querySelector("tbody");

// Định nghĩa API URL
const API_URL = "http://localhost:8080/api/v1";

// Định nghĩa API lấy danh sách user (bao gồm cả tìm kiếm user)
// Nếu term != "" --> tìm kiếm user
// Ngược lại --> lấy danh sách user
function getUsersAPI(term = "") {
  let url = `${API_URL}/users`;
  if (term) {
    url = `${API_URL}/users/search?name=${term}`;
  }

  return axios.get(url);
}

// Gọi API và hiển thị ra dữ liệu
async function getUsers(term = "") {
  try {
    // 1. Gọi API lấy danh sách users
    let res = await getUsersAPI(term);

    // 2. Lưu danh sách users từ API trả về vào biến users (để phục vụ chức năng thêm, xóa, hiển thị danh sách user)
    users = res.data;

    // 3. Render dữ liệu ra ngoài giao diện
    renderUser(users);
  } catch (error) {
    // Xử lý nếu có lỗi xảy ra
    console.log(error);
  }
}

const deleteUser = async (id) => {
  try {
    isConfirm = confirm("Bạn có muốn xóa không");
    if (isConfirm) {
      await axios.delete(`http://localhost:8080/api/v1/users/${id}`);

      // Xóa user ứng với id trong mảng users ban đầu
      users = users.filter((user) => user.id != id);

      // Render lại giao diện
      renderUser(users);
    }
  } catch (error) {
    alert(error.response.data.message);
  }
};

// const searchEl = document.getElementById("search");
// searchEl.addEventListener("keydown", async (e) => {
//   try {
//     let res = await axios.get(
//       `http://localhost:8080/api/v1/users/search?name=${searchEl.value}`
//     );
//     if (e.keyCode == 13) {
//       renderUser(res.data);
//     }
//   } catch (error) {
//     console.log(error);
//   }
// });

const searchEl = document.getElementById("search");
searchEl.addEventListener("keydown", function (e) {
  if (e.keyCode == 13) {
    let term = e.target.value;

    getUsers(term);
  }
});

// Function dùng để render dữ liệu bảng
function renderUser(arr) {
  // Viết code tại đây

  // Kiểm tra nếu arr rỗng
  if (arr.length == 0) {
    messageEl.classList.remove("d-none");
    tableEl.classList.add("d-none");

    messageEl.innerHTML = "Danh sách user trống!";
    return;
  }

  // Nếu arr có phần tử
  messageEl.classList.add("d-none");
  tableEl.classList.remove("d-none");

  // Sử dụng vòng lặp để hiển thị dữ liệu
  tableBodyEl.innerHTML = "";
  for (let i = 0; i < arr.length; i++) {
    const u = arr[i];
    tableBodyEl.innerHTML += `
            <tr>
                <td>${i + 1}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.address}</td>
                <td>
                    <a href="./detail.html?id=${
                      u.id
                    }" class="btn btn-success">Xem chi tiết</a>
                    <button class="btn btn-danger" onclick="deleteUser(${
                      u.id
                    })">Xóa</button>
                </td>
            </tr>
        `;
  }
}

getUsers();
