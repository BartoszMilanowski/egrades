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
    <title>Klasa ${group.className}</title>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>
<div class="container-fluid">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Klasa ${group.className}</h6>
        </div>
        <div class="card-header py-3">
            Wychowawca: <a href="/admin/user/${group.supervisingTeacher.id}">${group.supervisingTeacher.name}</a>
        </div>
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista uczniów</h6>
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Imię: </th>
                        <th scope="col">Nazwisko: </th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td><a href="/admin/user/${student.id}">Szczegóły</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <a href="/admin/edit-class/${group.id}"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Edytuj</a>
    <a href="/dashboard#classes"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
</div>
</body>
</html>
