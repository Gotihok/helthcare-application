document.addEventListener("DOMContentLoaded", function () {
    var yearNode = document.querySelector("[data-current-year]");
    if (yearNode) {
        yearNode.textContent = String(new Date().getFullYear());
    }
});
