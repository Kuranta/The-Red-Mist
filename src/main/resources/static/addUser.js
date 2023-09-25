function addUser(){
    let payload = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        age: document.getElementById("age").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        roles: Array.from(document.getElementById("roles").selectedOptions).map(option => option.value)
    }

    fetch("http://localhost:8080/api/saveUser",{
        method: "POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(payload)
    })
}