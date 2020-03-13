<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

</head>

<body>
<div class="container">
    <jsp:useBean id="error" scope="request" type="java.lang.String"/>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show">
            <strong>Error!</strong>
                ${error}
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-9">
            <div class="cart">
                <div class="cart-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Billing Information</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form action="checkout" method="post">
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">Name</label>
                                    <div class="col-8">
                                        <input id="name" value="${name}" name="name" placeholder="Name" class="form-control here" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">Street</label>
                                    <div class="col-8">
                                        <input id="street" value="${street}" name="street" placeholder="Street" class="form-control here" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">City</label>
                                    <div class="col-8">
                                        <input id="city" value="${city}" name="city" placeholder="City" class="form-control here" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">Phone</label>
                                    <div class="col-8">
                                        <input id="phone" value="${phone}" name="phone" placeholder="Phone Number" class="form-control here" type="text" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-4 col-form-label">Email*</label>
                                    <div class="col-8">
                                        <input id="email" value="${email}" name="email" placeholder="Email" class="form-control here" required="required" type="text">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">GO</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
