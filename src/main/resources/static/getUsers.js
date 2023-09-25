function getUsers(){
    fetch("http://localhost:8080/api/getUsers").then(
        (res)=>res.json()).then((response)=>{
        var tempData = '';
        response.forEach((user)=>{
            var userRole = "";
            user.roles.forEach(function (role) {
                userRole += role.name + " ";
            })
            tempData += "<tr>";
            tempData += "<td>" + user.firstName + "</td>";
            tempData += "<td>" + user.lastName + "</td>";
            tempData += "<td>" + user.age + "</td>";
            tempData += "<td>" + user.email + "</td>";
            tempData += "<td>" + userRole + "</td>";
            tempData += "<td>" + "<a href='#editModal' data-toggle='modal' data-target='#editModal' class='btn btn-success edit-button' data-id='" + user.id + "' onclick='editUser(" + user.id + ")'>Edit" + "</td>";
            tempData += "<td>" + "<a href='#deleteModal' data-toggle='modal' class='btn btn-danger delete-button' data-id='" + user.id + "' " +
                "onclick='openDeleteModal(" + user.id + ", \"" + user.firstName + "\", \"" + user.lastName + "\", " + user.age + ", \"" + user.email + "\")'>Delete" + "</td>";
            tempData += "</tr>";
        })
        document.getElementById("tData").innerHTML = tempData;
    })
}

function getSimpleUsers(){
    fetch("http://localhost:8080/api/getUsers").then(
        (res)=>res.json()).then((response)=>{
        var tempData = '';
        response.forEach((user)=>{
            var userRole = "";
            user.roles.forEach(function (role) {
                userRole += role.name + " ";
            })
            tempData += "<tr>";
            tempData += "<td>" + user.firstName + "</td>";
            tempData += "<td>" + user.lastName + "</td>";
            tempData += "<td>" + user.age + "</td>";
            tempData += "<td>" + user.email + "</td>";
            tempData += "<td>" + userRole + "</td>";
            tempData += "</tr>";
        })
        document.getElementById("tData").innerHTML = tempData;
    })
}