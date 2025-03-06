<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="current-user-options">
        <c:set var="nullFields" value="${ employee.employeeDetails.hasNullFields }" />
        <c:choose>
            <c:when test = "${ nullFields }">
                <a
                    class="current-user-options__link"
                    title = "Add details for ${ employee.firstName } ${ employee.lastName }"
                    href="${ updateDetailsButton }" 
                >
                    <i class="fa-solid fa-plus"></i>
                </a>
            </c:when>
            <c:otherwise>
                <a
                    class="current-user-options__link"
                    title = "Update details for ${ employee.firstName } ${ employee.lastName }"
                    href="${ updateDetailsButton }" 
                >
                    <i class="fa-solid fa-user-pen"></i>
                </a>
            </c:otherwise>
        </c:choose>
        <a
            class="current-user-options__link"
            title = "Update main details for ${ employee.firstName } ${ employee.lastName }"
            href="${ updateButton }" 
        >
            <i class="fa-solid fa-pen-to-square"></i>
        </a>
        <a
            class="current-user-options__link"
            title = "Show history of ${ employee.firstName } ${ employee.lastName }"
            href="${ historyButton }" 
        >
            <i class="fa-solid fa-book"></i>
        </a>
        <a
            class="current-user-options__link"
            title = "Delete employee ${ employee.firstName } ${ employee.lastName }"
            href="${ deleteButton }" 
        >
            <i class="fa-solid fa-trash"></i>
        </a>
    </div>
</body>
</html>