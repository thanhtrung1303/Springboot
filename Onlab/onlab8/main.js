const heading = document.getElementById('heading');
console.log(heading);

const para2 = document.querySelector(".para");
console.log(para2);


// .para:nth-child(4)
const para3 = document.querySelector(".para + .para");
console.log(para3);

// const para3 = para2.nextElementSibling;
// console.log(para3);

const ul1 = document.querySelector(".box ul");
console.log(ul1);

const ul2 = document.querySelector("body > ul");
console.log(ul2);

const paras = document.querySelectorAll("p");
console.log(paras);

// su dung for qua nodelist
// for (let i = 0; i < paras.length; i++){
//     paras[i].style.color = 'red';
//     paras[i].style.backgroundColor = 'black';
// }

// su dung map
Array.from(paras).map(para => {
    para.style.color = 'red'
    para.style.backgroundColor = "black";
});

// lay ra noi dung cua phan tu
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "Xin chao";
heading.innerHTML = "<span>Xin chao cac ban</span>";
heading.innerText = "Hello";

// tao ra phan tu DOM

const newPara = document.createElement('p');
newPara.innerText = "New Para"
console.log(newPara);

//chen vao vi tri dau
// document.body.prepend(newPara);

// //chen vao vi tri cuoi
// document.body.appendChild(newPara);

// document.body.insertBefore(newPara, para2)

const box = document.querySelector('.box');
// document.body.insertBefore(newPara, box);

//targetElement.insertAdjacentElement(position, element);

// para3.insertAdjacentElement('afterend', newPara);
// box.insertAdjacentElement('beforebegin', newPara);

box.insertAdjacentHTML('beforebegin', '<p>newPara</p>');

//xoa phan tu
// document.body.removeChild(heading);
heading.parentNode.removeChild(heading);

//thay the the para1 = the a
const link = document.createElement('a');
link.innerText = 'Link google';
link.href = 'http://www.google.com';
link.target = '_blank';
document.body.replaceChild(link, para2);

const boxCopy1 = box.cloneNode(true);
const boxCopy2 = box.cloneNode(false);

console.log(boxCopy1);
console.log(boxCopy2);

document.body.appendChild(boxCopy1);

// Classlist
console.log(box.classList);

// them class
box.classList.add('text-big', 'text-bold');

// xoa class
box.classList.remove('text-big');

console.log(box.classList.contains('text-bold'));
console.log(box.classList.contains('text-big'));

setInterval(function () {
    box.classList.toggle('text-big');
}, 1500);