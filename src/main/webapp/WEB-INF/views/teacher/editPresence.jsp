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
    <title>Edycja: ${presence.group.className}, ${presence.date}</title>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Edytuj obecność: ${presence.group.className},
                ${presence.subject.subjectName}, ${presence.date}</h6>
        </div>
        <div class="card-body">
            <form:form modelAttribute="presence" method="post" id="form">
                <div class="form-group"><br/>
                    <form:hidden path="id" value="${presence.id}"/>
                    <form:hidden path="teacher" value="${presence.teacher.id}"/>
                    <form:hidden path="subject" value="${presence.subject.id}"/>
                    <form:hidden path="group" value="${presence.group.id}"/>
                    <input type="hidden" value="${dateStr}" name="dateStr">
                    &nbsp;Temat zajęć:<br/><form:input path="topic" id="topic" value="${presence.topic}"/><br/><br/><br/>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">&nbsp;Nazwisko</th>
                            <th scope="col">&nbsp;Imię</th>
                            <th scope="col">&nbsp;Obecny</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${presentStudents}" var="presentStudent">
                            <tr id="student">
                                <td>${presentStudent.lastName}</td>
                                <td>${presentStudent.firstName}</td>
                                <td><input type="checkbox" name="present" value="${presentStudent.id}"
                                           checked="checked"></td>
                            </tr>
                        </c:forEach>
                        <c:forEach items="${absentStudents}" var="absentStudent">
                            <tr id="student">
                                <td>${absentStudent.lastName}</td>
                                <td>${absentStudent.firstName}</td>
                                <td><input type="checkbox" name="present" value="${absentStudent.id}"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table><br/><br/>
                    &nbsp;Uwagi dodatkowe:<br/><form:textarea path="comment" value="${presence.comment}"/>
                    <br/><br/><br/>
                    <input type="submit" value="Zapisz" class="btn btn-primary btn-user">
                </div>
            </form:form>
        </div>
    </div>
    <a href="/teacher/presence/class/${presence.group.id}/${presence.subject.id}"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm"
       onclick="return confirm('Podane dane nie zostaną zapisane!')">Anuluj</a>
</div>
</body>
</html>
