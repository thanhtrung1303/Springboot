const btn = document.querySelector(".btn");
const imageEL = document.querySelector(".image img");``
btn.addEventListener("click", async function () {
    try {
        //B1 goi api
        let res = await axios.get("https://dog.ceo/api/breeds/image/random");

        //B2 lay ket qua tra ve tu api => hien thi
        imageEL.src = res.data.message;
    } catch (error) {
        console.log(error);
    }
})