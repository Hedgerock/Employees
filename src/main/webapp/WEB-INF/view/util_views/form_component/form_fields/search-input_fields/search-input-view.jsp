<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test = "${ not empty param.depId }">
            <input type = "hidden" name="depId" value="${ param.depId }" />
        </c:when>
        <c:when test = "${ not empty param.cityId }">
            <input type = "hidden" name="cityId" value="${ param.cityId }" />
        </c:when>
        <c:when test = "${ not empty param.natId }">
            <input type = "hidden" name="natId" value="${ param.natId }" />
        </c:when>
    </c:choose>
    
    <form:input
        path = "searchParams"
        type="search"
        placeHolder="${ param.placeHolder }"
        class = "find-employee-form__input"
        required = "true"
    />
</body>
</html>