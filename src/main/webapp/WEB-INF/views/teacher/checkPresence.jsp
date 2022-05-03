<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <title>Obecność - ${group.className} - ${date}</title>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Sprawdź obecność: ${group.className},
            ${subject.subjectName}, ${date}</h6>
        </div>
        <div class="card-body">
            <form:form modelAttribute="presence" method="post" id="form">
                <div class="form-group"><br/>
                    <form:hidden path="teacher" value="${teacher.id}"/>
                    <form:hidden path="subject" value="${subject.id}"/>
                    <form:hidden path="group" value="${group.id}"/>
                    &nbsp;Temat zajęć:<br/><form:input path="topic" id="topic"/><br/><br/><br/>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">&nbsp;Nazwisko</th>
                            <th scope="col">&nbsp;Imię</th>
                            <th scope="col">&nbsp;Obecny</th>
                            <th scope="col">&nbsp;Nieobecny</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${students}" var="student">
                                <tr id="student">
                                    <td id="lastName">${student.lastName}</td>
                                    <td id="firstName">${student.firstName}</td>
                                    <td><input type="checkbox" name="present" value="${student.id}" id="present"></td>
                                    <td><input type="checkbox" name="absent" value="${student.id}" id="absent"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table><br/><br/>
                    &nbsp;Uwagi dodatkowe:<br/><form:textarea path="comment"/>
                    <br/><br/><br/>
                    <input type="submit" value="Zapisz" class="btn btn-primary btn-user">
                </div>
                </form:form>
        </div>
    </div>
    <a href="/teacher/presence/class/${group.id}/${subject.id}"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm"
       onclick="return confirm('Podane dane nie zostaną zapisane!')">Anuluj</a>
</div>
</body>
</html>
