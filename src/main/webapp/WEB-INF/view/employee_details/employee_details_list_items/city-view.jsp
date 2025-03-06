<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test = "${ not empty employee.city }">
            <c:url var="currentCity" value="/showCurrentCity">
                <c:param name="cityId" value="${ employee.city.id }" />
            </c:url>
            <li 
                data-title = "City"
                title = "Show ${ employee.city.name } city"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ currentCity }">${ employee.city.name }</a>
            </li>
        </c:when>
        <c:otherwise>
            <li 
                data-title = "City"
                title = "Update city?"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ param.updateButton }">Without city</a>
            </li>
        </c:otherwise>
    </c:choose>
</body>
</html>