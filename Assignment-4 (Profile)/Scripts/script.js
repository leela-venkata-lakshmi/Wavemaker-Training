const skillBtn=document.getElementById("skillBtn");
const eduBtn=document.getElementById("eduBtn");
const xpBtn=document.getElementById("xpBtn");
const skills=document.getElementById("skills");
const edu=document.getElementById("edu");
const xp=document.getElementById("xp");
const btnHtml=document.getElementById("btnHtml");
const btnCSS=document.getElementById("btnCSS");
const btnBoot=document.getElementById("btnBoot");
const btnJS=document.getElementById("btnJS");
const btnC=document.getElementById("btnC");
const btnCC=document.getElementById("btnCC");
const btnJava=document.getElementById("btnJava");
const btnSql=document.getElementById("btnSql");


btnHtml.addEventListener('mouseenter', function() {
    btnHtml.innerText = 'HTML';
});
btnHtml.addEventListener('mouseleave', function() {
    btnHtml.innerHTML = `<img src="https://img.icons8.com/?size=96&id=20909&format=png" class="w-50">`;
});
btnCSS.addEventListener('mouseenter', function() {
    btnCSS.innerText = 'CSS';
});
btnCSS.addEventListener('mouseleave', function() {
    btnCSS.innerHTML = `<img src="https://img.icons8.com/?size=120&id=3BTBsJs5myRy&format=png" class="w-50">`;
});
btnBoot.addEventListener('mouseenter', function() {
    btnBoot.innerText = 'BootStrap';
});
btnBoot.addEventListener('mouseleave', function() {
    btnBoot.innerHTML = `<img src="https://img.icons8.com/?size=128&id=ZMc42tPbG32H&format=png" class="w-50">`;
});
btnJS.addEventListener('mouseenter', function() {
    btnJS.innerText = 'JavaScript';
});
btnJS.addEventListener('mouseleave', function() {
    btnJS.innerHTML = `<img src="https://img.icons8.com/?size=128&id=gYCTehfTlYk5&format=png" class="w-50">`;
});
btnC.addEventListener('mouseenter', function() {
    btnC.innerText = 'C Programming';
});
btnC.addEventListener('mouseleave', function() {
    btnC.innerHTML = `<img src="https://img.icons8.com/?size=100&id=40670&format=png" class="w-50">`;
});
btnCC.addEventListener('mouseenter', function() {
    btnCC.innerText = 'C++';
});
btnCC.addEventListener('mouseleave', function() {
    btnCC.innerHTML = `<img src="https://img.icons8.com/?size=96&id=40669&format=png" class="w-50">`;
});
btnJava.addEventListener('mouseenter', function() {
    btnJava.innerText = 'Java';
});
btnJava.addEventListener('mouseleave', function() {
    btnJava.innerHTML = `<img src="https://img.icons8.com/?size=96&id=13679&format=png" class="w-75">`;
});
btnSql.addEventListener('mouseenter', function() {
    btnSql.innerText = 'SQL';
});
btnSql.addEventListener('mouseleave', function() {
    btnSql.innerHTML = `<img src="https://img.icons8.com/?size=100&id=11400&format=png" class="w-50">`;
});

skillBtn.addEventListener("click",()=>{
    skills.classList.remove("hidden");
    edu.classList.add("hidden");
    xp.classList.add("hidden");
})
eduBtn.addEventListener("click",()=>{
    skills.classList.add("hidden");
    edu.classList.remove("hidden");
    xp.classList.add("hidden");
})
xpBtn.addEventListener("click",()=>{
    skills.classList.add("hidden");
    edu.classList.add("hidden");
    xp.classList.remove("hidden");
})