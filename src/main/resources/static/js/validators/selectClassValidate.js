document.addEventListener("DOMContentLoaded", function (){
    const form = document.querySelector("#selectClassForm");
    const group = form.querySelector("#group");

    form.addEventListener("submit", e => {
        e.preventDefault();
        if (group.value === 0){
            alert("Wybierz klasę!");
        } else {
            form.submit();
        }
    })
})