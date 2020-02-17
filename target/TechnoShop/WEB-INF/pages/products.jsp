<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title title="Techno Store" about="Home Page">Techno Store</title>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    <script>
        function loadItems() {
            alert("Not implemented yet!")
        }
    </script>

    <style>
    body {
    line-height: 24px;
    color: #656565;
    }

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

    .h3-price {
        color: rgb(20, 80, 150);
        font-family: 'Oxygen Mono', monospace;
    }
    </style>


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

</head>

<body>
<div class="container">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a href="/home" class="navbar-brand">TechnoStore</a>

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

    <div id="itemsContainer" class="row justify-content-center">
        <jsp:useBean id="items" scope="request" type="java.util.List"/>

        <c:choose>
            <c:when test="${empty items}">
                <h3 class="h3-price">There are no available items</h3>
            </c:when>
            <c:otherwise>
                <c:forEach items="${items}" var="item">
                    <div class="col-lg-4 col-md-6 col-xl-3" <c:if test="${item.availability == 0}">style="opacity: 0.5;"</c:if>>
                        <c:if test="${item.newItem == true}">
                            <span class="badge badge-secondary align-top">NEW</span>
                        </c:if>

                        <img onclick="location.href = 'product?ItemID=${item.id}'" src="${item.mainPhoto}" class="rounded img-fluid" style="max-height: 40%" alt="">

                        <c:choose>
                            <c:when test="${item.discount > 0}">
                                <h5 class="text-danger"><s>$ ${item.price}</s></h5>
                                <h5 class="text-danger">$ ${item.price - item.discount}</h5>
                            </c:when>
                            <c:otherwise>
                                <h5>$ ${item.price}</h5>
                            </c:otherwise>
                        </c:choose>

                        <p>${item.category}</p>
                        <h5>${item.manufacturer} ${item.name}</h5>

                        <c:if test="${item.availability != 0}">
                            <button type="button" class="btn btn-danger" onclick="location.href = 'cart/add?ItemID=${item.id}&quantity=1'">
                                Add to Cart
                            </button>
                        </c:if>

                        <button type="button" class="btn btn-primary btn-sm" onclick="location.href = 'wishes/add?ItemID=${item.id}'">
                            Add to Wishes
                        </button>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <button onclick="loadItems()" type="button" class="btn btn-primary btn-lg btn-block">Load more items</button>

    <!-- Footer -->
    <footer class="page-footer font-small bg-dark pt-4">
        <!-- Footer Links -->
        <div class="container-fluid text-center text-md-left">
            <div class="col-md-6 mb-4">

                <form class="input-group" action="home" method="get">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Your Email</span>
                        </div>
                        <input type="text" class="form-control" placeholder="Email">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-primary">Subscribe</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="footer-copyright text-center py-3">
            Very simple and lightweight project.
        </div>
    </footer>
</div>
</body>

</html>
