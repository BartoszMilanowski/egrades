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
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>
<div class="container-fluid">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">${user.firstName} ${user.lastName}</h6>
        </div>
    <sec:authorize access="hasRole('ROLE_STUDENT')">
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Imię:</td>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <td>Nazwisko:</td>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <td>Adres e-mail:</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Klasa:</td>
                    <td>${group.className}</td>
                </tr>
                </tbody>
            </table>
            W przypadku zmiany danych skontaktuj się z: /admin/
        </div>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')">
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Imię:</td>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <td>Nazwisko:</td>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <td>Adres e-mail</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Przedmioty:</td>
                    <td></td>
                </tr>
                <c:forEach items="${subjects}" var="subject">
                    <tr>
                        <td></td>
                        <td>${subject.subjectName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        <sec:authorize access="hasRole('ROLE_TEACHER')">
            W przypadku zmiany danych skontaktuj się z /admin/
        </sec:authorize>
        </div>
    </sec:authorize>
    </div>
    <a href="/user/change-password"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Zmień hasło</a>
    <a href="/dashboard"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
</div>
</body>
</html>
