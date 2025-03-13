<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <td class="content-table-row__value">
        <c:set var = "isNotEmpty" value = "${ not empty content.middleName }"/>
        <c:set var = "showTitle" value = "Show details of ${ content.firstName } ${ content.lastName }" />
        <c:set var = "updateTitle" value = "Update middle name for  ${ content.firstName } ${ content.lastName }" />

        <a
            class="content-table-row__link"
            href="${ isNotEmpty ? param.showLink : param.updateLink }"
            target="${ isNotEmpty ? param.target : '_self' }"
            title = "${ isNotEmpty ? showTitle : updateTitle }"
        >
            <c:choose>
                <c:when test = "${ not empty content.middleName }">
                    ${ content.middleName }
                </c:when>
                <c:otherwise>
                    Empty
                </c:otherwise>
            </c:choose>
        </a>
    </td>
</body>
</html>