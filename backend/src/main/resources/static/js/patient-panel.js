(function () {
    var token = localStorage.getItem("authToken");
    if (!token) {
        window.location.href = "/login-pacjent.html";
    }

    async function api(url, options) {
        var response = await fetch(url, {
            method: options && options.method ? options.method : "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": token
            },
            body: options && options.body ? JSON.stringify(options.body) : undefined
        });
        if (!response.ok) throw new Error("Blad API");
        return response.status === 204 ? null : response.json();
    }

    function appointmentNode(item) {
        return '<article class="visit-item"><div><h3>' + item.date + " " + item.time +
            '</h3><p>' + item.doctorName + "</p></div><span class=\"status-badge planned\">" +
            item.status + "</span></article>";
    }

    function prescriptionNode(item) {
        return '<article class="visit-item"><div><h3>' + item.code +
            '</h3><p>' + item.medicine + " - " + item.dosage +
            '</p></div><span class="status-badge done">Pobierz e-recepte</span></article>';
    }

    async function loadData() {
        var me = await api("/api/users/me");
        if (me.role !== "PATIENT") {
            window.location.href = "/panel-lekarz.html";
            return;
        }
        document.getElementById("patient-email").textContent = "Zalogowano: " + me.email;

        var appointments = await api("/api/portal/patient/appointments");
        document.getElementById("patient-appointments-list").innerHTML = appointments.map(appointmentNode).join("");

        var prescriptions = await api("/api/portal/patient/prescriptions");
        document.getElementById("prescriptions-list").innerHTML = prescriptions.map(prescriptionNode).join("");
    }

    document.getElementById("book-appointment-form").addEventListener("submit", async function (event) {
        event.preventDefault();
        var fd = new FormData(event.target);
        await api("/api/portal/patient/appointments", {
            method: "POST",
            body: {
                date: fd.get("date"),
                time: fd.get("time"),
                reason: fd.get("reason")
            }
        });
        await loadData();
        event.target.reset();
    });

    document.getElementById("logout-btn").addEventListener("click", async function () {
        localStorage.removeItem("authToken");
        window.location.href = "/index.html";
    });

    loadData().catch(function () {
        localStorage.removeItem("authToken");
        window.location.href = "/login-pacjent.html";
    });
})();
