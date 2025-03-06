<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:set var = "isNotEmpty" value = "${ not empty employee.middleName }"/>
        <c:set var = "showTitle" value = "Show details of ${ employee.firstName } ${ employee.lastName }" />
        <c:set var = "updateTitle" value = "Update middle name for  ${ employee.firstName } ${ employee.lastName }" />

        <a
            class="content-table-row__link"
            href="${ isNotEmpty ? param.showLink : param.updateLink }"
            target="${ isNotEmpty ? param.target : '_self' }"
            title = "${ isNotEmpty ? showTitle : updateTitle }"
        >
            <c:choose>
                <c:when test = "${ not empty employee.middleName }">
                    ${ employee.middleName }
                </c:when>
                <c:otherwise>
                    Empty
                </c:otherwise>
            </c:choose>
        </a>
    </td>
</body>
</html>