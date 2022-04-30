document.addEventListener("DOMContentLoaded", function (){
    const form = document.querySelector("#form");
    const firstName = form.querySelector("#firstName");
    const lastName = form.querySelector("#lastName");
    const email = form.querySelector("#email");
    form.addEventListener("submit", e => {
        e.preventDefault();

        if (firstName.value === "") {
            alert("Podaj imię!");
        } else {
            if (lastName.value === ""){
                alert("Podaj nazwisko!");
            } else {
                if (email.value === ""){
                    alert("Podaj adres e-mail!")
                } else {
                        form.submit();
                }
            }
        }
    })
})