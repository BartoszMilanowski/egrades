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
    <link href="src/main/resources/static/css/sb-admin-2.min.css" rel="stylesheet" type="text/css">
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <title>eGrades</title>
</head>
<body>

<%@include file="../../fragments/header.jsp"%>


<div class="container-fluid">
  <div class="card shadow mb-4">
      <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary"></h6>
      </div>
      <div class="card-body">
          <table class="table">
              <thead>
              <tr>
                  <th scope="col">Ocena</th>
                  <th scope="col">Nauczyciel</th>
                  <th scope="col">Opis</th>
                  <th scope="col">Data</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="grade" items="${grades}">
                  <tr>
                      <td>${grade.gradeValue}</td>
                      <td>${grade.teacher}</td>
                      <td>${grade.gradeDescription}</td>
                      <td>${grade.dateTime}</td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
</div>

</body>
</html>
