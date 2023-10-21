function loginUser() {
    const addForm = document.getElementById("loginForm");
    let user = {
        email: document.getElementById("logEmail").value,
        password: document.getElementById("logPassword").value
    }
    addForm.addEventListener('submit', (e) => {
        e.preventDefault();
    });

    fetch("http://localhost:8080/auth", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user)
    }).then(response => response.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem("token", data.token);

                const tokenData = parseJwt(data.token);
                const roles = tokenData.roles;

                if (roles.includes("ROLE_ADMIN")) {
                    window.location.href = "/admin";
                } else if (roles.includes("ROLE_USER")) {
                    window.location.href = "/user";
                }
            }
        })
}

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}