function editUser(id){
    fetch("http://localhost:8080/api/editUser/" + id, {
        method: "GET"
    }).then((res)=>res.json()).then((response)=>{
        console.log("call",response);
        document.getElementById("id").value = response.id;
        document.getElementById("firstName").value = response.firstName;
        document.getElementById("lastName").value = response.lastName;
        document.getElementById("age").value = response.age;
        document.getElementById("email").value = response.email;
    })
}