function displayForm(name) {
    let formName;
    if (name === 'supplement') {
        formName = 'popup-form-supplement';
    }
    let form = document.getElementById(formName);
    if (form.style.display === "block") {
        form.style.display = "none";
    } else {
        form.style.display = "block";
    }
}