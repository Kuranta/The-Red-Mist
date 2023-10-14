function addUser(){
    const addForm = document.getElementById("addUser");
    let user = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        age: document.getElementById("age").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        roles: Array.from(document.getElementById("roles").selectedOptions).map(option => option.value)
    }

    addForm.addEventListener('submit', (e)=>{
        e.preventDefault();
    });

    fetch("http://localhost:8080/api/saveUser", {
        method: "POST",
        headers:{
            "Content-Type":"application/json"
        },
        body: JSON.stringify(user)
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/admin';
            }
        })
}