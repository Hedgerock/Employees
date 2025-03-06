<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test = "${ not empty employee.nationality }">
            <c:url var="currentDepartment" value="/showCurrentDepartment">
                <c:param name="depId" value="${ employee.department.id }" />
            </c:url>
            <li 
                data-title = "Department"
                title = "Show ${ employee.department.name } department"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ currentDepartment }">${ employee.department.name }</a>
            </li>
        </c:when>
        <c:otherwise>
            <li 
                data-title = "Department"
                title = "Show ${ employee.department.name } department"
                class = "employee-details-list__item employee-details-list__item_with-link"
            >
                <a href="${ currentDepartment }">Without department</a>
            </li>
        </c:otherwise>
    </c:choose>
</body>
</html>