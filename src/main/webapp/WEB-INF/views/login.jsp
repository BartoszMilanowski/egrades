<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Zalogij do eGrades</title>
</head>
<body>

<%@include file="../fragments/header.jsp"%>
<div class="container-fluid">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Zaloguj do eGrades</h1>
    </div>

    <div class="card shadow mb-4">
        <form class="user" method="post">
            <div class="form-group"><br/>
            <label> Login: <input type="text" name="username" class="form-control form-control-user"/> </label><br/>
            <label> Hasło: <input type="password" name="password" class="form-control form-control-user"/> </label><br/>
            <input type="submit" value="Zaloguj" class="btn btn-primary btn-user"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
