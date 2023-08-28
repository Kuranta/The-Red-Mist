console.log("Hello from Jokerge 1984");


$.get("/api/getUsers", function(data) {
    var $userTableBody = $("#userTable tbody");

    $userTableBody.empty();
    data.forEach(function(user) {
        var userRole = "";
        user.roles.forEach(function (role){
            userRole += role.name + " ";
        });
        var row = "<tr>" +
            "<td>" + user.firstName + "</td>" +
            "<td>" + user.lastName + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.email + "</td>" +
            "<td>" + userRole + "</td>" +
            "<td>" + "<a class='btn btn-success edit-button' data-toggle='modal' data-target='#editModal' >Edit" + "</td>" +
            "<td>" + "<a class='btn btn-warning'>Delete" + "</td>" +
            "</tr>";
        $userTableBody.append(row);
    });
});

$(".table .edit-button").on("click", function (event){
    $().modal();
    console.log("second jokerge")

    var userId = $(this).data("userid")
    $.get("/api/edit/" + userId, function (user){
        $('#id').val(user.id);
        $('#firstName').val(user.firstName);
        $('#lastName').val(user.lastName);
        $('#age').val(user.age);
        $('#email').val(user.email);
        $('#password').val(user.password);
        $('#editModal').modal();
    });
});