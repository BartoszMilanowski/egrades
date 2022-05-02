    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">
           <title>eGrades ${user.name}</title>
    </head>
    <body>
    <%@include file="../fragments/header.jsp"%>
    <div class="container-fluid">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Twoje oceny</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Przedmiot</th>
                            <th scope="col">Oceny</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.subjectName}</td>
                            <td><a href="/student/grades/${subject.subjectName}">Pokaż</a></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><b>Średnia semestralna</b></td>
                        <td>${avg}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_TEACHER')">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista klas</h6>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Klasa</th>
                    <th scope="col">Wychowawca</th>
                    <c:forEach var="subject" items="${subjects}">
                    <th scope="col"></th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="group" items="${classes}">
                    <tr>
                        <td>${group.className}</td>
                        <td>${group.supervisingTeacher}</td>
                        <c:forEach var="subject" items="${subjects}">
                        <td><a href="/teacher/class/${group.id}/${subject.id}">${subject.subjectName}</a></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/admin/user/students">Uczniowie</a><br/>
            <a href="/admin/user/teachers">Nauczyciele</a><br/>
            <a href="/admin/subjects">Przedmioty</a><br/>
            <a href="/admin/classes">Klasy</a>
        </sec:authorize>
    </div>
</body>
</html>
