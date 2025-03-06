<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test = "${ not empty employee.nationality }">
            <c:url var="currentNationality" value="/showCurrentNationality">
                <c:param name="natId" value="${ employee.nationality.id }" />
            </c:url>
            <li 
                data-title = "Nationality"
                title = "Show ${ employee.nationality.name } nationality"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ currentNationality }">${ employee.nationality.name }</a>
            </li>
        </c:when>
        <c:otherwise>
            <li 
                data-title = "Nationality"
                title = "Update nationality?"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ param.updateButton }">Without naitionality</a>
            </li>
        </c:otherwise>
    </c:choose>
</body>
</html>