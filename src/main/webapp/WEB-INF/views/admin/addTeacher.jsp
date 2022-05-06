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
    <title>Dodaj nauczyciela</title>
    <script type="text/javascript" src="/js/validators/newUserValidator.js"></script>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>
<div class="container-fluid">

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Dodaj nauczyciela</h6>
        </div>
    <form:form cssClass="user" modelAttribute="user" method="post" id="form">
        <div class="form-group"><br/><br/>
            <span>&nbsp;Imię:&nbsp;<form:input path="firstName" id="firstName"/></span><br/><br/>
            <span>&nbsp;Nazwisko:&nbsp;<form:input path="lastName" id="lastName"/></span><br/><br/>
            <span>&nbsp;Adres e-mail:&nbsp;<form:input type="email" path="email" id="email"/></span><br/><br/>
            <span>&nbsp;Przedmioty:<br/>
                <c:forEach items="${subjects}" var="subject">
                    &nbsp;<input type="checkbox" name="subject" value="${subject.id}">&nbsp;${subject.subjectName}&nbsp;
                </c:forEach>
            </span><br/><br/>
            <span>&nbsp;<input type="checkbox" name="admin">&nbsp;Administrator</span><br/><br/>
            &nbsp;<input type="submit" value="Dodaj nauczyciela"
                         class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">
        </div>
    </form:form>
    </div>
    <a href="/dashboard#teachers"
       class="d-none d-inline-block btn btn-sm btn-primary shadow-sm"
       onclick="return confirm('Podane dane nie zostaną zapisane!')">Anuluj</a>
</div>
</body>
</html>
