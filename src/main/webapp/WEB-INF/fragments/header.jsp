    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <!-- Page Wrapper -->
    <div id="wrapper"/>

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <sec:authorize access="isAnonymous()">
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">eGrades</div>
    </a>
    </sec:authorize>


    <sec:authorize access="isAuthenticated()">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/dashboard">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">eGrades</div>
        </a>
    </sec:authorize>

    <hr class="sidebar-divider my-0">



    <sec:authorize access="isAnonymous()">
        <li class="nav-item active">
            <a class="nav-link" href="/login">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Zaloguj</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#grades">
                <span>Oceny</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#presence">
                <span>Obecności</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#students">
                <span>Lista uczniów</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#teachers">
                <span>Lista nauczycieli</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#classes">
                <span>Lista klas</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard#subjects">
                <span>Lista przedmiotów</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/admin/add-user/student">
                <span>Dodaj ucznia</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/admin/add-user/teacher">
                <span>Dodaj nauczyciela</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/admin/add-class">
                <span>Dodaj klasę</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item active">
            <a class="nav-link" href="/admin/add-subject">
                <span>Dodaj przedmiot</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <li class="nav-item active">
            <a class="nav-link" href="/user/my-account">
                <span>Mój profil</span></a>
        </li>
        <hr class="sidebar-divider my-0">
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <li class="nav-item active">
            <form action="<c:url value="/logout"/>" method="post">
                <input type="submit" value="Wyloguj" class="btn btn-primary btn-user nav-link">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </sec:authorize>

    <hr class="sidebar-divider d-none d-md-block">

    </ul>

    <div id="content-wrapper" class="d-flex flex-column"/>

    <!-- Main Content -->
    <div id="content"/>

    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.user.name"/>
        </sec:authorize>
    </nav>

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>

    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">


        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
        <li class="nav-item dropdown no-arrow d-sm-none">
            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
            </a></li></ul>

