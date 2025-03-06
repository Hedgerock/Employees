<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:choose>
            <c:when test = "${ param.page == 'showCurrentNationality' }">
                ${ employee.nationality.name }
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test = "${ not empty employee.nationality }">
                        <a 
                            class="content-table-row__link"
                            href="${ param.currentNationality }" 
                            title = "Show nationality ${ employee.nationality.name }"
                        >
                            ${ employee.nationality.name }
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a 
                            class="content-table-row__link"
                            href="${ param.updateButton }" 
                            title = "Update info ${ employee.nationality.name }"
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