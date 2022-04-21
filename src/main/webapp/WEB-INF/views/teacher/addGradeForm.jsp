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
  <script type="text/javascript" src="/js/gradeFormValidator.js"></script>
  <title>Wstaw ocenę - ${student.lastName}</title>
</head>
<body>
<%@include file="../../fragments/header.jsp"%>


<div class="container-fluid">
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Dziennik elektroniczny eGrades</h1>
  </div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Wstaw ocenę z przedmiotu: ${subject.subjectName},
      uczeń: ${student.lastName} ${student.firstName}</h6>
    </div>
    <div class="card-body">

      <form:form action="/teacher/grade/add" method="post" modelAttribute="grade" id="gradeForm">
         <input type="hidden" name="subject" value="${subject.id}">
         <input type="hidden" name="student" value="${student.id}">
       Ocena: <form:select path="gradeValue">
          <form:option value="1.0" label="1"/>
          <form:option value="2.0" label="2"/>
          <form:option value="2.5" label="2+"/>
          <form:option value="2.75" label="3-"/>
          <form:option value="3.0" label="3"/>
          <form:option value="3.5" label="3+"/>
          <form:option value="3.75" label="4-"/>
          <form:option value="4.0" label="4"/>
          <form:option value="4.5" label="4+"/>
          <form:option value="4.75" label="5-"/>
          <form:option value="5.0" label="5"/>
          <form:option value="5.5" label="5+"/>
          <form:option value="6.0" label="6"/>
      </form:select><br/><br/>
         Opis: <form:textarea path="gradeDescription" id="desc"/>
          <form:errors path="gradeDescription" cssClass="error" element="p"/>
          <br/><br/>
          <input type="submit" value="Dodaj ocenę" class="btn btn-primary btn-user">
      </form:form>
    </div>
    </div>
    <a href="/dashboard" class="d-none d-inline-block btn btn-sm btn-primary shadow-sm">Wróć</a>
</div>
</body>
</html>
