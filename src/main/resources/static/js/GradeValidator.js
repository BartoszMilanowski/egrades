document.addEventListener("DOMContentLoaded", function (){
    const form = document.querySelector("#form");
    const desc = form.querySelector("#desc");

    form.addEventListener("submit", e => {
        e.preventDefault();

        if(desc.value.length < 5 || desc.value.length > 250){
            alert("Opis powinien zawierać od 5 do 250 znaków");
        }
    })
})