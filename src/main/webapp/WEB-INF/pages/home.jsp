<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title title="Techno Store" about="Home Page">Techno Store</title>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

</head>

<body>
    <jsp:useBean id="categories" scope="request" type="java.util.List"/>
    <jsp:useBean id="subCategories" scope="request" type="java.util.Map"/>
    <jsp:useBean id="products" scope="request" type="java.util.List"/>
    <jsp:useBean id="error" scope="request" type="java.lang.String"/>

    <div class="container">

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show">
                <strong>Error!</strong>
                    ${error}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
        </c:if>

        <c:import url="fragments/navbar.jsp">
            <c:param name="categories" value="${categories}"/>
            <c:param name="subCategories" value="${subCategories}"/>
        </c:import>

        <c:import url="fragments/products.jsp">
            <c:param name="products" value="${products}"/>
        </c:import>

        <c:import url="fragments/footer.jsp"/>
    </div>
</body>
</html>
