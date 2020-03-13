<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="itemsContainer" class="row justify-content-center">
    <jsp:useBean id="products" scope="request" type="java.util.List"/>
    <c:forEach items="${products}" var="product">
        <div class="col-lg-4 col-md-6 col-xl-3" <c:if test="${product.availability == 0}">style="opacity: 0.5;"</c:if>>
            <c:if test="${product.isNew == true}">
                <span class="badge badge-secondary align-top">NEW</span>
            </c:if>

            <img onclick="location.href = 'product?ItemID=${product.id}'" src="${product.photo}" class="rounded img-fluid" style="max-height: 40%" alt="">

            <c:choose>
                <c:when test="${product.discount > 0}">
                    <h5 class="text-danger"><s>$ ${product.price}</s></h5>
                    <h5 class="text-danger">$ ${product.price - product.discount}</h5>
                </c:when>
                <c:otherwise>
                    <h5>$ ${product.price}</h5>
                </c:otherwise>
            </c:choose>

            <p>${product.categoryName}</p>
            <h5>${product.manufacturer} ${product.name}</h5>

            <c:if test="${product.availability != 0}">
                <button type="button" class="btn btn-danger" onclick="location.href = 'cart/add?ItemID=${product.id}&quantity=1'">
                    Add to Cart
                </button>
            </c:if>

            <button type="button" class="btn btn-primary btn-sm" onclick="location.href = 'wishes/add?ItemID=${product.id}'">
                Add to Wishes
            </button>
        </div>
    </c:forEach>
</div>

<button type="button" class="btn btn-primary btn-lg btn-block">Load more products</button>


