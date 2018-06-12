<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>
<header class="header">
    <div class="header-elem" onclick="displayForm('supplement')">Додати добавку</div>
    <form style="display: inline-block; float: right; padding: 0.5%">
        <input type="search" class="search-field">
        <input type="submit" class="search-btn" value="Знайти">
    </form>
</header>
<main>
    <div class="list">
        <c:forEach items="${supplements}" var="supplement">
            <div class="list-elem">
                <div class="list-col-1" id="code${supplement.id}">${supplement.code}</div>
                <div class="list-col-2" id="type${supplement.id}">${supplement.info.name}</div>
                <div class="list-col-3" id="contents${supplement.id}">${supplement.info.contents}</div>
                <div class="list-col-4" id="stat${supplement.id}">${supplement.info.classification.name}</div>
                <div class="list-col-img">
                    <img src="<%=request.getContextPath()%>/resources/images/edit.png" class="col-img-1" onclick="getEditForm(${supplement.id})">
                </div>
                <div class="list-col-img" onclick="location.href='/remove-supplement/${supplement.id}'">
                    <img src="<%=request.getContextPath()%>/resources/images/delete.png" class="col-img-2">
                </div>
                <span hidden id="supplId${supplement.id}">${supplement.id}</span>
                <span hidden id="supplInfoId${supplement.id}">${supplement.info.id}</span>
                <span hidden id="supplInfoClassId${supplement.id}">${supplement.info.classification.id}</span>
                <span hidden id="supplInfoId2${supplement.id}">${supplement.infoId}</span>
                <span hidden id="supplInfoClassId2${supplement.id}">${supplement.info.classificationId}</span>
            </div>
        </c:forEach>
    </div>
</main>
<div class="b-popup" id="popup-form-supplement">
    <div class="b-popup-content">
        <form:form action="/add-supplement" method="post" modelAttribute="supplement">
            <div class="form-group">
                <form:label path="code" for="supplementCode">Код добавки</form:label>
                <form:input path="code" type="text" class="form-control" id="supplementCode" placeholder="Введіть код добавки"/>
            </div>
            <div class="form-group">
                <form:label path="info.name" for="supplementType">Тип добавки</form:label>
                <form:input path="info.name" type="text" class="form-control" id="supplementType" placeholder="Введіть тип добавки"/>
            </div>
            <div class="form-group">
                <form:label path="info.contents" for="supplementContents">Вміст / ознаки добавки</form:label>
                <form:input path="info.contents" type="text" class="form-control" id="supplementContents" placeholder="Введіть вміст або ознаки добавки"/>
            </div>
            <div class="form-group">
                <form:label path="info.classification.name" for="supplementStatus">Статус</form:label>
                <form:input path="info.classification.name" type="text" class="form-control" id="supplementStatus" placeholder="Введіть статус"/>
            </div>
            <form:hidden path="id" id="sId"/>
            <form:hidden path="info.id" id="infoId"/>
            <form:hidden path="info.classification.id" id="infoClassId"/>
            <form:hidden path="infoId" id="infoId2"/>
            <form:hidden path="info.classificationId" id="classId2"/>
            <br>
            <button type="button" class="btn btn-primary" style="background-color: #cd7700;" onclick="validate(this.form)">Зберегти</button>
            <button type="button" class="btn btn-primary" style="border: 1px solid #cd7700; background: #a6a6a6" onclick="displayForm('supplement')">Скасувати</button>
        </form:form>
    </div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/main.js" rel="script"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>