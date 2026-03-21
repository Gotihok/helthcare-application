(function () {
    function setMessage(elementId, text) {
        var node = document.getElementById(elementId);
        if (node) node.textContent = text;
    }

    async function authRequest(url, payload) {
        var response = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        if (!response.ok) {
            throw new Error("Operacja nie powiodla sie");
        }
        return response.json();
    }

    function saveToken(jwt) {
        localStorage.setItem("authToken", jwt);
    }

    function redirectByRole(role) {
        if (role === "DOCTOR") {
            window.location.href = "/panel-lekarz.html";
            return;
        }
        window.location.href = "/panel-pacjent.html";
    }

    var patientLogin = document.getElementById("patient-login-form");
    if (patientLogin) {
        patientLogin.addEventListener("submit", async function (event) {
            event.preventDefault();
            var fd = new FormData(patientLogin);
            try {
                var result = await authRequest("/api/auth/login", {
                    email: fd.get("email"),
                    password: fd.get("password")
                });
                saveToken(result.jwt);
                redirectByRole(result.role);
            } catch (error) {
                setMessage("patient-auth-message", "Niepoprawne dane logowania.");
            }
        });
    }

    var patientRegister = document.getElementById("patient-register-form");
    if (patientRegister) {
        patientRegister.addEventListener("submit", async function (event) {
            event.preventDefault();
            var fd = new FormData(patientRegister);
            try {
                var result = await authRequest("/api/auth/register", {
                    firstName: fd.get("firstName"),
                    lastName: fd.get("lastName"),
                    email: fd.get("email"),
                    password: fd.get("password"),
                    role: "PATIENT"
                });
                saveToken(result.jwt);
                redirectByRole(result.role);
            } catch (error) {
                setMessage("patient-auth-message", "Rejestracja nie powiodla sie.");
            }
        });
    }

    var doctorLogin = document.getElementById("doctor-login-form");
    if (doctorLogin) {
        doctorLogin.addEventListener("submit", async function (event) {
            event.preventDefault();
            var fd = new FormData(doctorLogin);
            try {
                var result = await authRequest("/api/auth/login", {
                    email: fd.get("email"),
                    password: fd.get("password")
                });
                saveToken(result.jwt);
                redirectByRole(result.role);
            } catch (error) {
                setMessage("doctor-auth-message", "Niepoprawne dane logowania.");
            }
        });
    }

    var doctorRegister = document.getElementById("doctor-register-form");
    if (doctorRegister) {
        doctorRegister.addEventListener("submit", async function (event) {
            event.preventDefault();
            var fd = new FormData(doctorRegister);
            try {
                var result = await authRequest("/api/auth/register", {
                    firstName: fd.get("firstName"),
                    lastName: fd.get("lastName"),
                    email: fd.get("email"),
                    password: fd.get("password"),
                    role: "DOCTOR"
                });
                saveToken(result.jwt);
                redirectByRole(result.role);
            } catch (error) {
                setMessage("doctor-auth-message", "Rejestracja nie powiodla sie.");
            }
        });
    }
})();
