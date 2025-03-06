<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:choose>
            <c:when test = "${ employee.employeeDetails.hasNullFields }">
                <a 
                    class="content-table-row__link"
                    href="${ param.updateDetailsButton }" 
                    title = "Add employee additional info for ${ employee.firstName }"
                >
                    <i class="fa-solid fa-plus"></i>
                </a>
            </c:when>
            <c:otherwise>
                <a 
                    class="content-table-row__link"
                    href="${ param.updateDetailsButton }" 
                    title = "Edit employee additional info for ${ employee.firstName }"
                >
                    <i class="fa-solid fa-user-pen"></i>
                </a>
            </c:otherwise>
        </c:choose>
    </td>
</body>
</html>