<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:choose>
            <c:when test = "${ param.page == 'showCurrentDepartment' }">
                ${ employee.department.name }
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test = "${ not empty employee.department }">
                        <a 
                            class="content-table-row__link"
                            href="${ param.currentDepartment }" 
                            title = "Show department ${ employee.department.name }"
                        >
                            ${ employee.department.name }
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a 
                            class="content-table-row__link"
                            href="${ param.updateButton }" 
                            title = "Update info ${ employee.department.name }"
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