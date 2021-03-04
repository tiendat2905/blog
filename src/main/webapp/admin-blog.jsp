<!-- COPPY CODE TU TEMPLATE COOLADMIN, PAGE INDEX -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">


<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Admin Category Manage</title>

    <!-- Fontfaces CSS-->
    <link href="admin-assets/css/font-face.css" rel="stylesheet" media="all">
    <link
            href="admin-assets/vendor/font-awesome-4.7/css/font-awesome.min.css"
            rel="stylesheet" media="all">
    <link
            href="admin-assets/vendor/font-awesome-5/css/fontawesome-all.min.css"
            rel="stylesheet" media="all">
    <link
            href="admin-assets/vendor/mdi-font/css/material-design-iconic-font.min.css"
            rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="admin-assets/vendor/bootstrap-4.1/bootstrap.min.css"
          rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="admin-assets/vendor/animsition/animsition.min.css"
          rel="stylesheet" media="all">
    <link
            href="admin-assets/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css"
            rel="stylesheet" media="all">
    <link href="admin-assets/vendor/wow/animate.css" rel="stylesheet"
          media="all">
    <link href="admin-assets/vendor/css-hamburgers/hamburgers.min.css"
          rel="stylesheet" media="all">
    <link href="admin-assets/vendor/slick/slick.css" rel="stylesheet"
          media="all">
    <link href="admin-assets/vendor/select2/select2.min.css"
          rel="stylesheet" media="all">
    <link href="admin-assets/vendor/perfect-scrollbar/perfect-scrollbar.css"
          rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="admin-assets/css/theme.css" rel="stylesheet" media="all">

</head>

<body class="animsition" style="opacity:1">
<div class="page-wrapper">
    <!-- HEADER MOBILE-->
    <header class="header-mobile d-block d-lg-none">
        <div class="header-mobile__bar">
            <div class="container-fluid">
                <div class="header-mobile-inner">
                    <a class="logo" href="/dashboard"> <img
                            src="admin-assets/images/icon/logo.png" alt="CoolAdmin"/>
                    </a>
                    <button class="hamburger hamburger--slider" type="button">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
                    </button>
                </div>
            </div>
        </div>
        <nav class="navbar-mobile">
            <div class="container-fluid">
                <ul class="navbar-mobile__list list-unstyled">

                    <li><a href="admin-category"> <i class="fas fa-chart-bar"></i>Quản
                        lý danh mục
                    </a></li>
                    <li><a href="admin-blog"> <i class="fas fa-table"></i>Quản
                        lý Blogs
                    </a></li>
                    <li><a href="form.html"> <i class="far fa-check-square"></i>Quản
                        lý User
                    </a></li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- END HEADER MOBILE-->

    <!-- MENU SIDEBAR-->
    <aside class="menu-sidebar d-none d-lg-block">
        <div class="logo">
            <a href="/dashboard"> <img src="admin-assets/images/icon/logo.png"
                                       alt="Cool Admin"/>
            </a>
        </div>
        <div class="menu-sidebar__content js-scrollbar1">
            <nav class="navbar-sidebar">
                <ul class="list-unstyled navbar__list">
                    <li><a href="admin-category"> <i class="fas fa-chart-bar"></i>Quản
                        lý Danh mục
                    </a></li>
                    <li><a href="admin-blog"> <i class="fas fa-table"></i>Quản
                        lý Blogs
                    </a></li>
                    <li><a href="form.html"> <i class="far fa-check-square"></i>Quản
                        lý User
                    </a></li>
                </ul>
            </nav>
        </div>
    </aside>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <!-- HEADER DESKTOP-->
        <header class="header-desktop">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="header-wrap">
                        <form class="form-header" action="" method="POST"></form>
                        <div class="header-button">
                            <div class="noti-wrap"></div>
                            <div class="account-wrap">
                                <div class="account-item clearfix js-item-menu">
                                    <div class="image">
                                        <img src="admin-assets/images/icon/avatar-01.jpg"
                                             alt="${sessionScope.user.fullName}"/>
                                    </div>
                                    <div class="content">
                                        <a class="js-acc-btn" href="#">${sessionScope.user.fullName}</a>
                                    </div>
                                    <div class="account-dropdown js-dropdown">
                                        <div class="info clearfix">
                                            <div class="image">
                                                <a href="#"> <img
                                                        src="admin-assets/images/icon/avatar-01.jpg"
                                                        alt="John Doe"/>
                                                </a>
                                            </div>
                                            <div class="content">
                                                <h5 class="name">
                                                    <a href="#">${sessionScope.user.fullName}</a>
                                                </h5>
                                                <span class="email">${sessionScope.username}</span>
                                            </div>
                                        </div>
                                        <div class="account-dropdown__body">

                                            <div class="account-dropdown__item">
                                                <a href="#"> <i class="zmdi zmdi-settings"></i>Setting
                                                </a>
                                            </div>

                                        </div>
                                        <div class="account-dropdown__footer">
                                            <a href="authentication?action=logout"> <i
                                                    class="zmdi zmdi-power"></i>Logout
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- HEADER DESKTOP-->

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-12 col-md-12">
                            <h3 class="title-5 m-b-35">Blog Manager</h3>
                        </div>
                        <div class="table-data__tool col-12">
                            <div class="table-data__tool-left">
                                <a href="admin-blog?action=add"
                                   class="au-btn au-btn-icon au-btn--green au-btn--small"> <i
                                        class="zmdi zmdi-plus"></i>add item
                                </a>
                            </div>
                            <div class="table-data__tool-right">
                                <form class="form-header" action="#">
                                    <input type="hidden" name="action" value="find"/>
                                    <input class="au-input au-input--xl" type="text" name="name"
                                           placeholder="Search">
                                    <button class="au-btn--submit" type="submit">
                                        <i class="zmdi zmdi-search"></i>
                                    </button>
                                </form>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <c:if test="${message!=null}">
                            <div class="col-12 col-md-12">
                                <div class="alert alert-primary"><i>${message}</i></div>
                            </div>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="table-responsive table-responsive-data2">
                            <table class="table table-data2">
                                <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Publish Date</th>
                                    <th>ImageUrl</th>
                                    <th>Category</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${list}" var="blog">
                                <tr class="tr-shadow">
                                    <td>${blog.title}</td>
                                    <td>${blog.publishDate}</td>
                                    <td><img src="${blog.imageUrl}" alt="" style="width: 100px;"></td>
                                    <td>${blog.categoryName}</td>
                                    <td>
                                        <div class="table-data-feature">

                                            <a href="admin-blog?action=edit&id=${blog.id}"
                                               class="item" data-toggle="tooltip" data-placement="top"
                                               title="Edit"> <i class="zmdi zmdi-edit"></i>
                                            </a> <a href="admin-blog?action=delete&id=${blog.id}"
                                                    onclick="return confirm('Nếu bạn xóa mục ${blog.categoryName} thì những bài post liên quan tới mục ${blog.categoryName} sẽ bị xóa theo....Are You Sure...!!!')"
                                                    class="item" data-toggle="tooltip" data-placement="top"
                                                    title="Delete"> <i class="zmdi zmdi-delete"></i>
                                        </a> <a href="admin-blog?action=view&id=${blog.id}"
                                                class="item" data-toggle="tooltip" data-placement="top"
                                                title="View"> <i class="zmdi zmdi-more"></i>
                                        </a>
                                        </div>
                                    </td>

                                </tr>
                                <tr class="spacer"></tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="copyright">
                                <p>
                                    Copyright © 2018 Colorlib. All rights reserved. Template by <a
                                        href="https://colorlib.com">Colorlib</a>.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<!-- Jquery JS-->
<script src="admin-assets/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="admin-assets/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="admin-assets/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="admin-assets/vendor/slick/slick.min.js">

</script>
<script src="admin-assets/vendor/wow/wow.min.js"></script>
<script src="admin-assets/vendor/animsition/animsition.min.js"></script>
<script
        src="admin-assets/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">

</script>
<script src="admin-assets/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="admin-assets/vendor/counter-up/jquery.counterup.min.js">

</script>
<script
        src="admin-assets/vendor/circle-progress/circle-progress.min.js"></script>
<script
        src="admin-assets/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="admin-assets/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="admin-assets/vendor/select2/select2.min.js">

</script>

<!-- Main JS-->
<script src="admin-assets/js/main.js"></script>

</body>

</html>
<!-- end document-->