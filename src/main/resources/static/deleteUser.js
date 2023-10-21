function openDeleteModal(id, firstName, lastName, age, email) {
    document.getElementById("id1").value = id;
    document.getElementById("firstName1").value = firstName;
    document.getElementById("lastName1").value = lastName;
    document.getElementById("age1").value = age;
    document.getElementById("email1").value = email;
}

function deleteUser() {
    var userId = document.getElementById("id1").value;
    fetch("http://localhost:8080/api/users/" + userId, {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    window.location.href = '/admin';
}