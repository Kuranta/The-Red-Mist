function getUsers(){
    fetch("http://localhost:8080/api/getUsers").then(
        (res)=>res.json()).then((response)=>{
        var tempData = '';
        console.log(response);
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
            tempData += "<td>" + "<a class='btn btn-success edit-button'>Edit" + "</td>";
            tempData +=  "<td>" + "<a class='btn btn-danger delete-button'>Delete" + "</td>";
            tempData += "</tr>";
        })
        document.getElementById("tData").innerHTML = tempData;
    })
}