function editUser(id){
    fetch("http://localhost:8080/api/users/" + id, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    }).then((res)=>res.json()).then((response)=>{
        document.getElementById("id2").value = response.id;
        document.getElementById("firstName2").value = response.firstName;
        document.getElementById("lastName2").value = response.lastName;
        document.getElementById("age2").value = response.age;
        document.getElementById("email2").value = response.email;
    });

}

function editUserData() {
    let id = document.getElementById('id2').value;
    let firstName = document.getElementById('firstName2').value;
    let lastName = document.getElementById('lastName2').value;
    let age = document.getElementById('age2').value;
    let email = document.getElementById('email2').value;
    let password = document.getElementById('password2').value;
    let roles = Array.from(document.getElementById('roles2').selectedOptions).map(option => option.value);

    let user = {
        id: id,
        firstName: firstName,
        lastName: lastName,
        age: age,
        email: email,
        password: password,
        roles: roles
    };

    fetch('/api/users/' + id, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(user)
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/admin';
            }
        })
}