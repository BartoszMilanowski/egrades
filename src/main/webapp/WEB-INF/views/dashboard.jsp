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
        <script type="text/javascript" src="/js/validators/selectClassValidator.js"></script>
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
                    <tr>
                        <td><b>Frekwencja</b></td>
                        <td>${totalFrequency}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')">
    <div class="card shadow mb-4">
        <div class="card-header py-3" id="selectClass">
            <h6 class="m-0 font-weight-bold text-primary">Przejdź do klasy</h6>
        </div>
        <div class="card-body">
            <form class="user" method="post" action="/teacher/select-class" id="selectClassForm">
                <div class="form-group"><br/>
                    <label>Klasa:<br/>
                        <select name="group" id="group">
                            <option value=0>--Wybierz klasę--</option>
                            <c:forEach items="${classes}" var="group">
                                <option value="${group.id}">${group.className}</option>
                            </c:forEach>
                        </select>
                    </label><br/>
                    <label>Przedmiot:<br/>
                        <select name="subject">
                            <c:forEach items="${tSubjects}" var="tSubject">
                                <option value="${tSubject.id}">${tSubject.subjectName}</option>
                            </c:forEach>
                            <c:forEach items="${oSubjects}" var="oSubject">
                                <option value="${oSubject.id}">${oSubject.subjectName}</option>
                            </c:forEach>
                        </select>
                    </label><br/><br/>
                    <input type="submit" value="Dalej"
                           class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </form>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="d-sm-flex align-items-center justify-content-between mb-4" id="adminDashboard">
            <h1 class="h3 mb-0 text-gray-800">Panel administratora</h1>
        </div>
        <div class="card shadow mb-4">
            <div class="card-header py-3" id="students">
                <h6 class="m-0 font-weight-bold text-primary">Uczniowie</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Imię</th>
                        <th scope="col">Nazwisko</th>
                        <th scope="col">Rola</th>
                        <th scope="col">Adres e-mail</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <td>${student.firstName}</td>
                            <td>${student.lastName}</td>
                            <td>${student.roles}</td>
                            <td>${student.email}</td>
                            <td><a href="/admin/user/${student.id}">Szczegóły</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/admin/add-user/student"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Dodaj ucznia</a>
                <a href="/dashboard"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
            </div>
        </div>
        <div class="card shadow mb-4">
            <div class="card-header py-3" id="teachers">
                <h6 class="m-0 font-weight-bold text-primary">Nauczyciele</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Imię</th>
                        <th scope="col">Nazwisko</th>
                        <th scope="col">Rola</th>
                        <th scope="col">Adres e-mail</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${teachers}" var="teacher">
                        <tr>
                            <td>${teacher.firstName}</td>
                            <td>${teacher.lastName}</td>
                            <td>${teacher.roles}</td>
                            <td>${teacher.email}</td>
                            <td><a href="/admin/user/${teacher.id}">Szczegóły</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/admin/add-user/teacher"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Dodaj nauczyciela</a>
                <a href="/dashboard"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
            </div>
        </div>
        <div class="card shadow mb-4">
            <div class="card-header py-3" id="classes">
            <h6 class="m-0 font-weight-bold text-primary">Klasy</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Klasa</th>
                        <th scope="col">Wychowawca</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${classes}" var="group">
                        <tr>
                            <td>${group.className}</td>
                            <td>${group.supervisingTeacher.name}</td>
                            <td><a href="/admin/class/${group.id}">Szczegóły</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/admin/add-class"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Dodaj klasę</a>
                <a href="/dashboard"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
            </div>
        </div>
        <div class="card shadow mb-4">
            <div class="card-header py-3" id="subjects">
                <h6 class="m-0 font-weight-bold text-primary">Przedmioty</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Przedmiot</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${subjectsList}" var="s">
                        <tr>
                            <td>${s.subjectName}</td>
                            <td><a href="/admin/subject/${s.id}">Nauczyciele</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/admin/add-subject"
                           class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Dodaj przedmiot</a>
                <a href="/dashboard"
                   class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
            </div>
        </div>
    </sec:authorize>
    </div>
</body>
</html>
