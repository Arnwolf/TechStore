<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="error" scope="request" type="java.lang.String"/>

<c:if test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade show">
        <strong>Error!</strong>
            ${error}
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
</c:if>
