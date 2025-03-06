<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:choose>
            <c:when test = "${ param.page == 'showCurrentCity' }">
                ${ employee.city.name }
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test = "${ not empty employee.city }">
                        <a 
                            class="content-table-row__link"
                            href="${ param.currentCity }" 
                            title = "Show city ${ employee.city.name }"
                        >
                            ${ employee.city.name }
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a 
                            class="content-table-row__link"
                            href="${ param.updateButton }" 
                            title = "Update info ${ employee.firstName } ${ employee.lastName } ${ employee.middleName }"
                        >
                            -
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </td>
</body>
</html>