<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${detailedItem.manufacturer} ${detailedItem.name}</title>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    <script>
        $(".dropdown-toggle").on("mouseenter", function () {
            if (!$(this).parent().hasClass("show")) {
                $(this).click();
            }
        });

        $(".btn-group, .dropdown").on("mouseleave", function () {
            if ($(this).hasClass("show")){
                $(this).children('.dropdown-toggle').first().click();
            }
        });
    </script>

    <style>
        .dropdown-submenu {
            position: relative;
        }

        .dropdown-submenu > a.dropdown-item:after {
            font-family: FontAwesome;
            content: "\f054";
            float: right;
        }

        .dropdown-submenu > a.dropdown-item:after {
            content: ">";
            float: right;
        }

        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: 0px;
            margin-left: 0px;
        }

        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }

        /* Style the header links */
        .header-mid a {
            float: left;
            color: black;
            text-align: center;
            padding: 12px;
            text-decoration: none;
            font-size: 18px;
            line-height: 25px;
            border-radius: 4px;
        }

        /* Change the background color on mouse-over */
        .header-mid a:hover {
            background-color: #ddd;
            color: black;
        }

        .header-mid .additional button {
            background-color: #ddd;
            padding: 20px;
            text-align: center;
            border-radius: 50%;
        }

        .header-mid .additional button:hover {
            background-color: darkslategray;
            color: black;
        }


        .header-mid input[type=text] {
            float: left;
            padding: 6px;
            border: none;
            margin-top: 8px;
            margin-right: 16px;
            font-size: 17px;
        }

        .header-mid .searching-field button {
            float: left;
            padding: 6px;
            margin-top: 8px;
            margin-right: 16px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .header-mid .searching-field button:hover {
            background: #ccc;
        }



        /* Float the link section to the right */

        /* Add media queries for responsiveness - when the screen is 500px wide or less, stack the links on top of each other */
        @media screen and (max-width: 500px) {
            .header-mid a {
                float: none;
                display: block;
                text-align: left;
            }
        }

        .product-detail-bar li.active, .product-detail-bar li:hover {
            color: #2d2d2d;
            font-weight: 500;

        }


        /* Links inside the dropdown */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }


        /* Change color of dropdown links on hover */
        .dropdown-content a:hover {background-color: #ddd;}

        .grid-item .compare {
            display: block;
            position: relative;
            top: 0%;
            left: 70%;
            visibility: hidden;
        }

        .grid-item:hover button {visibility: visible;}
        .grid-item:hover p {visibility: visible;}
        .grid-item .compare:hover {color: orange;}



        .cell__img img {
            position: absolute;
            top: 30%;
            left: 50%;
            -webkit-transform: translate(-50%,-50%);
            -ms-transform: translate(-50%,-50%);
            transform: translate(-50%,-50%);
            max-height: 140px;
            max-width: 100%;
            vertical-align: middle;
        }

        div, ul, section, li, a, ol, img, span, i, table, html, tbody {
            font-family: inherit;
            font-size: 100%;
            font-weight: inherit;
            font-style: inherit;
            margin: 0;
            padding: 0;
            border: 0;
            outline: 0;
            background: transparent;
        }

        *, ::before, ::after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        .flat-product-detail {
            padding: 30px 0px;
        }

        .container {
            width: 1170px;
            max-width: 100%;
            padding-right: 15px;
            padding-left: 15px;
            position: relative;
            margin-left: auto;
            margin-right: auto;
        }

        .row {
            margin-right: -15px;
            margin-left: -15px;
            display: flex;
            flex-wrap: wrap;
        }

        .column {
            -webkit-box-flex: 0;
            flex: 0 0 50%;
            max-width: 50%;
            padding-right: 15px;
            padding-left: 15px;
            position: relative;
            width: 100%;
            min-height: 1px;
        }

        .column2 {
            -webkit-box-flex: 0;
            flex: 0 0 50%;
            max-width: 50%;
            padding-right: 15px;
            padding-left: 15px;
            position: relative;
            width: 100%;
            min-height: 1px;
        }

        .zoom {
            display: inline-block;
            position: relative;
            max-width: 500px;
            max-height: 500px;
        }

        [role="button"], a {
            text-decoration: none;
            color: #333333;
            touch-action: manipulation;
        }

        .flexslider {
            margin: 0 0 20px;
            background: #fff;
            position: relative;
            border-radius: 4px;
            padding: 0;
        }

        .flex-view {
            max-height: 2000px;
        }

        .flex-control-thumbs {
            margin: 88px 0 0;
            position: static;
            overflow: hidden;
        }

        .flex-control-nav {
            width: 100%;
            position: absolute;
            bottom: -40px;
            text-align: center;
            padding: 0;
            list-style: none;
        }

        .flex-control-thumbs li {
            float: left;
            margin: 0;
            margin-right: 0px;
            margin-right: 32px;
        }

        .flex-control-thumbs img {
            max-width: 100%;
            height: auto;
            display: block;
            cursor: pointer;
            -moz-user-select: none;
            -webkit-transition: all 1s ease;
            -moz-transition: all 1s ease;
            -ms-transition: all 1s ease;
            -o-transition: all 1s ease;
            transition: all 1s ease;
            height: 85px;
            border: 2px solid
            #e5e5e5;
            border-radius: 10px;
        }

        .product-detail {
            background-color: #f5f5f5;
            padding: 36px 30px 38px 41px;
            border-radius: 8px;
        }

        .product-detail .header-detail h4.name {
            color:
                    #484848;
            font-size: 18px;
            font-weight: bold;
            font-family: 'Open Sans';
            margin-bottom: 3px;
        }

        .product-detail .header-detail .category {
            color:
                    #919191;
        }

        .product-detail .header-detail .reviewed {
            margin-top: 12px;
            margin-bottom: 15px;
        }

        .reviewed .review .text {
            color:
                    #838383;
            line-height: 20px;
        }

        .stars i {
            color:
                    #f28b00;
            font-size: 11px;
            margin-right: 5px;
        }

        .reviewed .status-product {
            float: right;
            padding-top: 12px;
            color:
                    #484848;
        }

        .reviewed .status-product span {
            color: #fff;
            height: 24px;
            padding: 0 15px;
            display: inline-block;
            line-height: 24px;
            border-radius: 15px;
            margin-left: 15px;
        }

        .product-detail .mid-detail {
            margin-top: 21px;
        }

        .regular {
            color:
                    #c5c5c5;
            font-size: 14px;
            text-decoration: line-through;
        }

        .product-detail .mid-detail .price .sale {
            font-size: 35px;
            margin-top: 5px;
        }

        .sale {
            color:
                    #f28b00;
            font-weight: 500;
            font-size: 22px;
            font-family: 'Nunito';
        }

        .product-detail .mid-detail .info-text {
            color:
                    #838383;
            line-height: 20px;
            margin-top: 31px;
            margin-bottom: 9px;
        }

        .product-detail .footer-detail {
            margin-top: 30px;
        }

        .product-detail .footer-detail .quantity-box > div.colors {
            margin-right: 8px;
            position: relative;
        }

        .product-detail .footer-detail .quantity-box > div {
            width: 174px;
            display: inline-block;
        }

        .product-detail .footer-detail .quantity-box > div.colors::before {
            content: '\f107';
            position: absolute;
            font-family: 'Fontawesome';
            top: 12px;
            right: 22px;
            font-size: 14px;
            color:
                    #1b1b19;
            z-index: 2;
        }

        .product-detail .footer-detail .quantity-box > div.colors select {
            background-color:
                    #fff;
            box-shadow: 0px 2px 3px 0px
            rgba(234, 234, 234, 1);
            height: 46px;
        }


        .product-detail .footer-detail .quantity-box > div.quantity input {
            background-color:
                    #fff;
            box-shadow: 0px 2px 3px 0px
            rgba(234, 234, 234, 1);
            border-color:
                    transparent;
            height: 46px;
            text-align: center;
            padding: 15px;
            color:
                    #484848;
            position: relative;
        }


        .box-cart.style2 {
            text-align: left;
            padding: 0;
            margin-top: 39px;
            margin-bottom: 48px;
        }

        .box-cart.style2 .btn-add-cart {
            display: inline-block;
            height: 55px;
            line-height: 55px;
            text-align: center;
            color:
                    #fff;
            background-color:
                    #f92400;
            border-radius: 30px;
            width: 220px;
            font-size: 16px;
            font-weight: 600;
        }

        a {
            text-decoration: none;
            color: #333333;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -ms-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

        .box-cart.style2 .btn-add-cart a img {
            padding-right: 18px;
        }

        img {
            max-width: 100%;
            height: auto;
        }

        img {
            border: 0;
            vertical-align: middle;
        }

        .box-cart.style2 .compare-wishlist a {
            color:
                    #919191;
            font-size: 12px;
            font-weight: 600;
        }

        .box-cart.style2 .compare-wishlist a {
            color:
                    #919191;
            font-size: 12px;
            font-weight: 600;
        }

        .flat-product-content {
            background-color:
                    #f5f5f5;
            margin: 48px 0 0;
        }

        section {
            display: block;
        }

        .product-detail-bar {
            padding-left: 0px;
        }

        .product-detail-bar {
            text-align: center;
            background-color: #fff;
            width: 100%;
            padding-left: 0px;
        }

        ol, ul {
            list-style: none;
        }

        .product-detail-bar li {
            display: inline-block;
            color:
                    #919191;
            font-size: 20px;
            font-weight: 300;
            font-family: 'Nunito';
            cursor: pointer;
            padding: 14px 30px 14px 26px;
            position: relative;
            z-index: 5;
        }

        .product-detail-bar li::before {
            content: '';
            position: absolute;
            background-color:
                    #f5f5f5;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            top: 0px;
            left: 0;
            width: 100%;
            height: 52px;
            z-index: -1;
            opacity: 0;
        }

        .product-detail-bar li.active, .product-detail-bar li:hover {
            color:
                    #2d2d2d;
            font-weight: 500;
        }

        .flat-product-content .container {
            padding-top: 88px;
            padding-bottom: 52px;
        }

        .container {
            width: 1170px;
            max-width: 100%;
            position: relative;
            margin-left: auto;
            margin-right: auto;
            padding-right: 15px;
            padding-left: 15px;
        }

        .row {
            margin-right: -15px;
            margin-left: -15px;
            display: flex;
            margin-right: -15px;
            margin-left: -15px;
            flex-wrap: wrap;
        }

        .col-md-12 {
            -webkit-box-flex: 0;
            -webkit-flex: 0 0 100%;
            -ms-flex: 0 0 100%;
            flex: 0 0 100%;
            max-width: 100%;
        }

        .tecnical-specs .name {
            color:
                    #484848;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 30px;
            font-family: 'Open Sans';
        }

        .tecnical-specs table {
            width: 100%;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        .tecnical-specs table tr:not(:last-child) {
            border-bottom: 1px solid
            #e5e5e5;
        }

        .rating .title, .form-review .title {
            font-size: 16px;
            color:
                    #353535;
            margin-bottom: 25px;
        }

        .rating .score .average-score {
            background-color:
                    #fff;
            border-radius: 8px;
            width: 168px;
            padding: 30px 0;
            vertical-align: middle;
            display: inline-block;
            box-shadow: 0px 2px 3px 0px
            rgba(234, 234, 234, 1);
        }

        .rating .score .average-score p.numb {
            color:
                    #484848;
            font-weight: bold;
            font-size: 30px;
        }

        .rating .score .average-score p {
            text-align: center;
        }

        .rating .score .average-score p.text {
            font-size: 14px;
            color:
                    #919191;
            margin-top: 10px;
        }

        .rating .score .average-score p {
            text-align: center;
        }

        .rating .score .queue {
            display: inline-block;
            margin-left: 25px;
        }

        .rating .queue-box li {
            margin-bottom: 10px;
        }

        .rating .queue-box li span i {
            color:
                    #f28b00;
            font-size: 14px;
            margin-right: 8px;
        }


        .rating .title, .form-review .title {
            font-size: 16px;
            color:
                    #353535;
            margin-bottom: 25px;
        }

        .form-review .your-rating span {
            color:
                    #838383;
            margin-right: 18px;
        }

        .queue i {
            color:
                    #f28b00;
            font-size: 11px;
            margin-right: 5px;
        }

        .form-review form {
            margin-top: 20px;
        }

        .form-review form > div {
            margin-bottom: 20px;
        }

        .form-review form > div > input {
            width: 355px;
            background-color:
                    #fff;
            border: none;
            height: 40px;
            box-shadow: 0px 2px 3px 0px
            rgba(234, 234, 234, 1);
        }

        .form-review form > div > textarea {
            height: 180px;
            background-color:
                    #fff;
            border-radius: 10px;
            border: none;
            padding-left: 30px;
            box-shadow: 0px 2px 3px 0px
            rgba(234, 234, 234, 1);
        }

        .form-review form .btn-submit button {
            display: inline-block;
            height: 55px;
            line-height: 55px;
            padding: 0 65px;
            font-size: 16px;
            color:
                    #fff;
            text-align: center;
            background-color:
                    #f92400;
            border-radius: 30px;
            font-family: 'Open Sans';
            font-weight: 400;
        }

        [type="reset"], [type="submit"], button, html [type="button"] {
            -webkit-appearance: button;
        }

        button, input[type="button"], input[type="reset"], input[type="submit"] {
            background:
                    #e5e5e5;
            background-color: rgb(229, 229, 229);
            position: relative;
            transition: all 0.3s ease-in-out;
        }

        button {
            cursor: pointer;
            border: none;
        }

        .col-md-12 {
            padding-right: 15px;
            padding-left: 15px;
        }

        .review-list {
            margin-top: 30px;
        }

        .review-list .review-metadata {
            margin-bottom: 15px;
        }

        .review-list .review-metadata .name {
            display: inline-block;
            font-weight: bold;
            color:
                    #484848;
        }

        .review-list .review-metadata .name span {
            color:
                    #838383;
            font-weight: 400;
        }

        .review-list .review-metadata .queue {
            float: right;
        }
    </style>
    <script>
        function addToCart() {
            location.href = 'cart/add?ItemID=${detailedItem.id}&quantity=' + document.getElementById("quantity").value
        }
        function changePhoto(photoSource) {
            document.getElementById('zoomed').src = photoSource;
        }
    </script>
</head>

<body>

<div class="container">
    <jsp:useBean id="errors" scope="request" type="java.util.List"/>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger alert-dismissible fade show">
            <strong>Error!</strong>
            <c:forEach items="${errors}" var="error">
                ${error}
            </c:forEach>
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
    </c:if>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a href="home" class="navbar-brand">TechnoStore</a>

        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
            <div class="navbar-nav">
                <div class="nav-item dropdown">
                    <a href="categories" class="nav-link dropdown-toggle" data-toggle="dropdown">
                        Categories
                    </a>

                    <ul class="dropdown-menu">
                        <jsp:useBean id="categories" scope="request" type="java.util.List"/>
                        <jsp:useBean id="subCategories" scope="request" type="java.util.Map"/>
                        <c:forEach items="${categories}" var="category">
                            <li class="dropdown-submenu">
                                <a class="dropdown-item" href="categories?categoryID=${category.id}">${category.name}</a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${subCategories[category.id]}" var="subCategory">
                                        <a class="dropdown-item" href="categories?categoryID=${subCategory.id}">${subCategory.name}</a>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <a href="profile" class="nav-item nav-link">Profile</a>
            </div>

            <form class="form-inline" action="products" method="get">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Search">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>

            <div class="navbar-nav">
                <a href="cart" class="nav-item nav-link">Cart</a>
                <a href="login" class="nav-item nav-link">Login</a>
            </div>
        </div>
    </nav>

    <section class="flat-product-detail">
        <div class="container">
            <div class="row">
                <div class="column">
                    <div class="flexslider">
                        <div class="flex-view">
                            <a id="zoom4" class="zoom" href="#">
                                <img id="zoomed" src="${detailedItem.mainPhoto}" alt="" draggable="false">
                            </a>
                        </div>

                        <ol class="flex-control-nav flex-control-thumbs">
                            <li>
                            <c:forEach items="${detailedItem.allPhotos}" var="photo">
                                <img onclick="changePhoto('${photo}')" src="${photo}" alt="" draggable="false">
                            </c:forEach>
                            </li>
                        </ol>

                        <ul></ul>
                    </div>
                </div>

                <div class="column2">
                    <div class="product-detail">
                        <div class="header-detail">
                            <h4 class="item-name">${detailedItem.manufacturer} ${detailedItem.name}</h4>
                            <div class="category"> ${detailedItem.category}</div>
                            <div class="reviewed">
                                <div class="reviews">
                                    <div class="text">
                                        <span>${fn:length(detailedItem.itemReviews)} Reviews</span>
                                    </div>
                                </div>

                                <div class="status-product">
                                    Availability
                                    <c:if test="${detailedItem.availability > 0}">
                                        <span style="background-color: #f92400;">In Stock</span>
                                    </c:if>
                                    <c:if test="${detailedItem.availability == 0}">
                                        <span style="background-color: gray;">Is Over</span>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="mid-detail">
                            <div class="price">
                                <c:if test="${detailedItem.discount > 0}">
                                    <div class="regular">$${detailedItem.price}</div>
                                </c:if>

                                <div class="sale">$${detailedItem.price - detailedItem.discount}</div>
                            </div>
                            <div class="info-text">
                                <c:forEach items="${detailedItem.itemParameters}" var="parameter">
                                    <c:if test="${parameter.itemDetailName == 'Description'}">
                                        ${parameter.itemDetailValue}
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="footer-detail">
                            <div class="quantity-box">
                                <div class="colors">
                                    <select name="color">
                                        <c:forEach items="${detailedItem.itemParameters}" var="parameter">
                                            <c:if test="${parameter.changeable == true}">
                                                <option onclick="location.href = 'product?name=${detailedItem.name}&parameter=${parameter.itemDetailValue}'">${parameter.itemDetailValue}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="quantity">
                                    <input id="quantity" type="number" name="number" value="1" min="1" max="${detailedItem.availability}" placeholder="Quantity">
                                </div>
                            </div>

                            <div class="box-cart style2">
                                <c:if test="${detailedItem.availability != 0}">
                                    <button onclick="addToCart()" name="add" class="btn-add-cart">
                                        Add to Bin
                                    </button>
                                </c:if>
                                <button onclick="location.href = 'wishes/add?ItemID=${detailedItem.id}'" name="add" class="btn-add-cart">
                                    Add to Wishes
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="flat-product-content">
        <ul class="product-detail-bar">
            <li class="active">Description</li>
        </ul>

        <div class="container">
            <div class="row" style="display: flex;">
                <div class="col-md-6">
                    <div class="description-text">
                        <div class="box-text">
                            <h4>
                                <c:forEach items="${detailedItem.itemParameters}" var="parameter">
                                <c:if test="${parameter.itemDetailName == 'Description'}">
                                    ${parameter.itemDetailValue}
                                </c:if>
                            </c:forEach>
                            </h4>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section>
        <ul class="product-detail-bar">
            <li class="active">Technical Specs</li>
        </ul>

        <div class="container">
            <div class="row" style="display: flex;">
                <div class="col-md-12">
                    <div class="tecnical-specs">
                        <h4 class="name">
                            ${detailedItem.manufacturer} ${detailedItem.name}
                        </h4>
                        <table>
                            <tbody>

                            <c:forEach items="${detailedItem.itemParameters}" var="parameter">
                                <tr>
                                    <c:choose>
                                        <c:when test="${parameter.searchable == true}">
                                            <td>${parameter.itemDetailName}</td>
                                            <td onclick="location.href = '/products?ParamID=${parameter.categoryDetailId}&ParamValue=${parameter.itemDetailValue}';" style="color: blue; cursor: context-menu">${parameter.itemDetailValue}${parameter.itemDetailSymbol}</td>
                                        </c:when>
                                        <c:when test="${parameter.itemDetailName == 'Description'}">
                                            <c:set var = "description" scope = "session" value = "${parameter.itemDetailValue}"/>
                                        </c:when>
                                        <c:when test="${parameter.changeable == true}">

                                        </c:when>
                                        <c:otherwise>
                                            <td>${parameter.itemDetailName}</td>
                                            <td>${parameter.itemDetailValue} ${parameter.itemDetailSymbol}</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section>
        <ul class="product-detail-bar">
            <li class="active">Reviews</li>
        </ul>

        <div class="container">
            <div class="row" style="display: flex;">
                <div class="col-md-6">
                    <div class="rating">
                        <div class="title">
                            Based on ${fn:length(detailedItem.itemReviews)} reviews
                        </div>
                        <div class="score">
                            <div class="average-score">
                                <p class="numb">${detailedItem.averageScore}</p>
                                <p class="text">Average score</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <c:choose>
                        <c:when test="${isReviewingGranted == true}">
                            <c:set var = "str" scope = "session" value = "inline-block"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var = "str" scope = "session" value = "none"/>
                        </c:otherwise>
                    </c:choose>

                    <div class="form-review" style="display: inline-block">
                        <form action="product?ItemID=${detailedItem.id}" method="post" accept-charset="utf-8">
                            <div class="review-form-name">
                                <input type="text" name="score" min="1" max="5" value="1" placeholder="Score">
                            </div>
                            <div class="review-form-comment">
                                <textarea name="review-text" placeholder="Your Comment"></textarea>
                            </div>
                            <div class="btn-submit">
                                <button type="submit">Add Review</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-md-12">
                    <ul class="review-list">
                        <c:forEach items="${detailedItem.itemReviews}" var="review">
                            <li>
                                <div class="review-metadata">
                                    <div class="name">
                                            ${review.userName} : <span>${review.datetime}</span>
                                    </div>
                                    <div class="queue">
                                        Score: ${review.rating}
                                    </div>
                                </div>
                                <div class="review-content">
                                    <p>
                                            ${review.description}
                                    </p>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
