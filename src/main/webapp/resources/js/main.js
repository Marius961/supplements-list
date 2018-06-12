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

function getEditForm(id) {
    let form = document.getElementById('popup-form-supplement');
    let code = document.getElementById('supplementCode');
    let supplType = document.getElementById('supplementType');
    let supplContents = document.getElementById('supplementContents');
    let supplStat = document.getElementById('supplementStatus');

    let sId = document.getElementById('sId');
    let infoId = document.getElementById('infoId');
    let infoClassId = document.getElementById('infoClassId');
    let infoId2 = document.getElementById('infoId2');
    let classId2 = document.getElementById('classId2');

    code.value = document.getElementById('code' + id).textContent;
    supplType.value = document.getElementById('type' + id).textContent;
    supplContents.value = document.getElementById('contents' + id).textContent;
    supplStat.value = document.getElementById('stat' + id).textContent;
    sId.value = document.getElementById('supplId' + id).textContent;
    infoId.value = document.getElementById('supplInfoId' + id).textContent;
    infoClassId.value = document.getElementById('supplInfoClassId' + id).textContent;
    infoId2.value = document.getElementById('supplInfoId2' + id).textContent;
    classId2.value = document.getElementById('supplInfoClassId2' + id).textContent;
    console.log("message");
    form.style.display = 'block';
}

function validate(form) {
    let elems = form.elements;
    let hasErrors = false;
    resetError(elems["code"].parentNode);
    if (!elems["code"].value) {
        hasErrors = true;
        showError(elems["code"].parentNode, 'Введіть код добавки');
    }

    resetError(elems["info.name"].parentNode);
    if (!elems["info.name"].value) {
        hasErrors = true;
        showError(elems["info.name"].parentNode, 'Введіть тип добавки');
    }

    resetError(elems["info.contents"].parentNode);
    if (!elems["info.contents"].value) {
        hasErrors = true;
        showError(elems["info.contents"].parentNode, 'Введіть ознаки або вміст добавки');
    }
    resetError(elems["info.classification.name"].parentNode);
    if (!elems["info.classification.name"].value) {
        hasErrors = true;
        showError(elems["info.classification.name"].parentNode, 'Вкажіть статус добавки');
    }
    if (!hasErrors) {
        form.submit();
    }
}

function showError(container, errorMessage) {
    container.className = 'error';
    let msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className === "error-message") {
        container.removeChild(container.lastChild);
    }
}