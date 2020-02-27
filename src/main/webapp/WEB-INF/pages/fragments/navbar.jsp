<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    body {
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

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a href="/" class="navbar-brand">TechnoStore</a>

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
