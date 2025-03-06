<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="isMoreThanOne" value = "${ fn:length(employee.employeeDetails.emails) > 1 }"/>
    <li
        class = "employee-details-list__item"
        data-title = "Email${ isMoreThanOne ? 's' : '' }"
    >
        <c:choose>
            <c:when test = "${ not empty employee.employeeDetails.emails }">
                <ul class="values-list">
                    <c:forEach var = "email" items = "${ employee.employeeDetails.emails }"  varStatus = "status">
                        <li class="values-list-item">
                            <c:choose>
                                <c:when test = "${ isMoreThanOne }">
                                    <span class="values-list-item__index">${ status.index + 1 }</span>
                                </c:when>
                            </c:choose>
                            <span 
                                class="values-list-item__current-value"
                                title = "Copy email ${ email.value }"
                                onclick = "navigator.clipboard.writeText(`${ email.value }`)"
                            >
                                ${ email.value }
                            </span>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <span>Emails related to "${ employee.firstName } ${ employee.lastName }" not found </span>
            </c:otherwise>
        </c:choose>
    </li>
</body>
</html>