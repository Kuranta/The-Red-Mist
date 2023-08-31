function addUser(){
    let payload ={};
    let roles = Array.from(document.getElementById("roles").selectedOptions).map(option=>option.value);
    payload['firstName'] = document.getElementById("firstName").value;
    payload['lastName'] = document.getElementById("lastName").value;
    payload['age'] = document.getElementById("age").value;
    payload['email'] = document.getElementById("email").value;
    payload['password'] = document.getElementById("password").value;
    payload['roles'] = roles;

    fetch("http://localhost:8080/api/saveUser",{
        method: "POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(payload)
    })
}