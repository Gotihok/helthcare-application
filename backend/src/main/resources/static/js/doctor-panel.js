(function () {
    var token = localStorage.getItem("authToken");
    if (!token) {
        window.location.href = "/login-lekarz.html";
    }

    async function api(url) {
        var response = await fetch(url, {
            headers: { "Authorization": token }
        });
        if (!response.ok) throw new Error("Blad API");
        return response.json();
    }

    function appointmentNode(item) {
        return '<article class="visit-item"><div><h3>' + item.date + " " + item.time +
            '</h3><p>Pacjent: ' + item.patientName + '</p></div><span class="status-badge planned">' +
            item.status + "</span></article>";
    }

    async function loadData() {
        var me = await api("/api/users/me");
        if (me.role !== "DOCTOR") {
            window.location.href = "/panel-pacjent.html";
            return;
        }
        document.getElementById("doctor-email").textContent = "Zalogowano: " + me.email;
        var appointments = await api("/api/portal/doctor/appointments");
        document.getElementById("doctor-appointments-list").innerHTML = appointments.map(appointmentNode).join("");
    }

    document.getElementById("logout-btn").addEventListener("click", function () {
        localStorage.removeItem("authToken");
        window.location.href = "/index.html";
    });

    loadData().catch(function () {
        localStorage.removeItem("authToken");
        window.location.href = "/login-lekarz.html";
    });
})();
