<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <title>Wishes</title>
    <style>
        @import 'https://fonts.googleapis.com/css?family=Oxygen+Mono';

        .cart-row {
            margin: 10px 0;
            padding: 10px 0;
            border-bottom: 1px solid #e1e1e1;
        }

        .cart-row div {
            text-align: center;
        }

        .cart-row img.img-responsive {
            margin: 0 auto;
            height: 60%;
            width: 50%;
        }

        .h3-price {
            color: rgb(20, 80, 150);
            font-family: 'Oxygen Mono', monospace;
        }
    </style>
</head>

<body>
<jsp:useBean id="categories" scope="request" type="java.util.List"/>
<jsp:useBean id="subCategories" scope="request" type="java.util.Map"/>

<div class="container">

    <c:import url="fragments/navbar.jsp">
        <c:param name="categories" value="${categories}"/>
        <c:param name="subCategories" value="${subCategories}"/>
    </c:import>

    <div class="row">
        <div class="col-xs-12">
            <jsp:useBean id="items" scope="request" type="java.util.List"/>
            <c:forEach items="${items}" var="item" varStatus="status">
                <div class="row cart-row">
                    <div class="col-xs-12 col-sm-2">
                        <div class="pull-left">
                            <a href="wishes/remove?ItemID=${item.product.id}">
                                <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
                            </a>
                        </div>
                        <a href="product?ItemID=${item.product.id}">
                            <img src="${item.product.photo}" alt="" class="img-responsive">
                        </a>
                    </div>

                    <div class="col-xs-12 col-sm-4">
                        <h3><a href="products?search=${item.product.manufacturer}">${item.product.manufacturer}</a></h3>
                        <h3><a href="product?ItemID=${item.product.id}">${item.product.name}</a></h3>
                    </div>

                    <div class="col-xs-5 col-sm-2">
                        <small>Price</small>
                        <h3 class="h3-price">$${item.product.price - item.product.discount}</h3>
                    </div>

                    <div class="col-xs-7 col-sm-2">
                        Availability
                        <c:if test="${item.product.availability > 0}">
                            <span style="background-color: #f92400;">In Stock</span>
                        </c:if>
                        <c:if test="${item.product.availability == 0}">
                            <span style="background-color: gray;">Is Over</span>
                        </c:if>
                    </div>

                    <c:if test="${item.product.availability > 0}">
                        <button onclick="location.href = 'cart/add?ItemID=${item.product.id}&quantity=1'" class="btn btn-danger">
                            Add to Bin
                        </button>
                    </c:if>

                    <button onclick="location.href = 'wishes/remove?wishId=${item.id}'" class="btn btn-dark">
                        Remove
                    </button>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
