document.addEventListener("DOMContentLoaded", function (){
    const form = document.querySelector("#form");
    const pass = form.querySelector("#pass");
    const pass2 = form.querySelector("#pass2");

    form.addEventListener("submit", e =>{
        e.preventDefault()
        if (pass.value.length < 8){
            alert("Hasło powinno mieć co najmniej 8 znaków!");
        } else {
            if (pass.value != pass2.value){
                alert("Podane hasła nie są takie same!");
            } else {
                form.submit();
            }
        }
    })
})