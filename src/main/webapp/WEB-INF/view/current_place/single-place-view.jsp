<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../current_place/current-place-operations.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <tr class="content-table-row" >
       <c:choose>
            <c:when test="${ not showAttributes.hasSingleValue }">
                <td class="content-table-row__value">${indexVal}</td>
            </c:when>
       </c:choose>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test = "${ showAttributes.hasSingleValue }">
                    ${ currentPlace.name }
                </c:when>
                <c:otherwise>
                    <a
                        class="content-table-row__link"
                        href="${ showPlace }" 
                        title = "Show ${ currentPlace.name }">
                        ${currentPlace.name}
                    </a>
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ currentPlace.minSalary == 0 || empty currentPlace.minSalary }">
                    -
                </c:when>
                <c:otherwise>
                    ${ currentPlace.minSalary } $
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ currentPlace.maxSalary == 0 || empty currentPlace.maxSalary}">
                    -
                </c:when>
                <c:otherwise>
                    ${ currentPlace.maxSalary } $
                </c:otherwise>
            </c:choose>
        </td>

        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ currentPlace.totalEmployees == 0 }">
                    -
                </c:when>
                <c:otherwise>
                    ${isZero ? '-' : currentPlace.totalEmployees} ${ isZero ? '' : value }
                </c:otherwise>
            </c:choose>
        </td>

        <td class="content-table-row__value" tabindex="-1">
            <a 
                class="content-table-row__link"
                href="${ updateButton }" 
                title = "Edit ${ currentPlace.name }"
            >
                <i class="fa-solid fa-pen-to-square"></i>
            </a>
        </td>

        <td class="content-table-row__value" tabindex="-1">
            <a 
                class="content-table-row__link"
                href="${ showHistoryButton }" 
                title = "Show history ${ currentPlace.name }"
            >
                <i class="fa-solid fa-book"></i>
            </a>
        </td>
        <td class="content-table-row__value"  tabindex="-1">
            <a 
                class="content-table-row__link"
                href="${ deleteButton }" 
                title = "Delete ${ currentPlace.name }"
            >
                <i class="fa-solid fa-trash"></i>
            </a>
        </td>
    </tr>
</body>
</html>