<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="isMoreThanOne" value = "${ phoneNumbers.size() > 1 }"/>
    <li
        class = "employee-details-list__item"
        data-title = "Phone number${ isMoreThanOne ? 's' : '' }"
    >
        <c:choose>
            <c:when test = "${ not empty phoneNumbers }">
                <ul class="values-list">
                    <c:forEach var = "phone" items = "${ phoneNumbers }"  varStatus = "status">
                        <li class="values-list-item">
                            <c:choose>
                                <c:when test = "${ isMoreThanOne }">
                                    <span class="values-list-item__index">${ status.index + 1 }</span>
                                </c:when>
                            </c:choose>
                            <span 
                                class="values-list-item__current-value"
                                title = "Copy phone number ${ phone.value }"
                                onclick = "
                                    const clearNumber = this.textContent.replace(/\D/g, '')
                                    navigator.clipboard.writeText(clearNumber)
                                "
                            >
                                ${ phone.value }
                            </span>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <span>Phone numbers related to "${ employee.firstName } ${ employee.lastName }" not found </span>
            </c:otherwise>
        </c:choose>
    </li>
</body>
</html>