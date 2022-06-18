const URL_API = "http://localhost:8080/api/v1";

let users = [];

// BEGIN Elements
const searchElement = document.getElementById("search");
// END Elements

// BEGIN Handle events
searchElement.addEventListener("input", () => searchUsers());
// END Handle events

// BEGIN APIs
const getUsersApi = () => {
  return axios.get(`${URL_API}/users`);
};

const searchUsersApi = (name) => {
  return axios.get(`${URL_API}/users/search?name=${name}`);
};

const deleteUserApi = (id) => {
  return axios.delete(`${URL_API}/users/${id}`);
};
// END APIs

// BEGIN Functions
async function getUsers() {
  try {
    const res = await getUsersApi();
    users = res.data;
    renderUsers(users);
  } catch (error) {
    console.log(error);
  }
}

function renderUsers(arr) {
  $(".user-table").pagination({
    dataSource: arr,
    pageSize: 2,
    callback: function (data, pagination) {
      let tbodyContent = "";
      for (let i = 0; i < data.length; i++) {
        let user = data[i];
        tbodyContent += `
            <tr>
                <td>${i + 1}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>
                    <a href="./detail.html?id=${user.id}"
                    class="btn btn-success">Xem chi tiết</a>
                    <button class="btn btn-danger"
                    onclick="deleteUser(${user.id})">Xóa</button>
                </td>
            </tr>
            `;
      }
      $(".user-table > .data-container").html(`
        <table class="table table-hover">
          <thead>
            <tr>
              <th>STT</th>
              <th>Fullname</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Address</th>
              <th></th>
            </tr>
          </thead>

          <tbody>
            ${tbodyContent}
          </tbody>
      `);
    },
  });
}

async function searchUsers() {
  try {
    const res = await searchUsersApi(searchElement.value);
    let foundUsers = res.data;
    renderUsers(foundUsers);
  } catch (error) {
    console.log(error);
  }
}

async function deleteUser(id) {
  try {
    await deleteUserApi(id);
    searchUsers();
  } catch (error) {
    console.log(error);
  }
}
// END Functions

// Main
getUsers();
