<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="empId" value="${ content.id }" />

    <c:url var="showHistory" value="/history">
        <c:param name="empId" value="${ empId }" />
    </c:url>
    <c:url var="showDepartment" value="/showCurrentDepartment">
        <c:param name="depId" value="${ content.departmentId }" />
    </c:url>

    <tr class="content-table-row">
        <td class="content-table-row__value">${ indexVal }</td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showHistory }"
            >
                ${ content.firstName }
            </a>
        </td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showHistory }"
            >
                ${ content.lastName }
            </a>
        </td>
        <td class="content-table-row__value">
            <a 
                class="content-table-row__link"
                href="${ showHistory }"
            >
                ${ empty content.middleName ? '-' : content.middleName}
            </a>
        </td>
        <c:choose>
            <c:when test="${ not empty content.departmentName }">
                <td 
                    class="content-table-row__value"
                >
                    <a 
                        class="content-table-row__link"
                        href="${ showDepartment }"
                    >
                        ${ content.departmentName }
                    </a>
                </td>
            </c:when>
            <c:otherwise>
                <td class="content-table-row__value">-</td>
            </c:otherwise>
        </c:choose>
        <td 
            title="${ content.previousChanges }" 
            class="content-table-row__value"
        >
            ${ content.previousShortChanges } ago
        </td>
    </tr>
</body>
</html>