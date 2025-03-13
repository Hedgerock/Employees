<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var = "diff" value = "${ content.salary - content.oldSalary }" />
    <c:set var = "colSpan" value = '11' />
    <c:url var = "showOldDepartment" value="/showCurrentDepartment">
        <c:param name="depId" value="${ content.oldDepartmentId }" />
    </c:url> 
    <c:url var = "showDepartment" value="/showCurrentDepartment">
        <c:param name="depId" value="${ content.departmentId }" />
    </c:url> 

    <tr class="content-table-row">
        <td class="content-table-row__value" colspan="${ colSpan }"></td>
    </tr>
    <c:if test="${content.revType == 1 && not empty content.oldFirstName }">
        <tr class="content-table-row">
            <td class="content-table-row__value" colspan="${ colSpan }">Previous Values:</td>
        </tr>
        <tr class="content-table-row">
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value">
                <c:out value="${content.oldFirstName}" />
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldLastName}" />
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldMiddleName}" />
            </td>
            <td class="content-table-row__value">
                <c:choose>
                    <c:when test="${ not empty content.oldDepartmentName }">
                        <a class="content-table-row__link" href="${ showOldDepartment }" target="_blank">
                            <c:out value="${content.oldDepartmentName}" />
                        </a>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldHireDate}" />
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldSalary}" />
                <c:choose>
                    <c:when test="${ diff != 0 }">
                        (${ diff > 0 ? '+' : '' }${ diff })
                    </c:when>
                </c:choose>
            </td>
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value">-</td>
        </tr>
    </c:if>
    <tr class="content-table-row">
        <td class="content-table-row__value" colspan="${ colSpan }">New Values:</td>
    </tr>
    <tr class="content-table-row">
        <td class="content-table-row__value">${ indexVal }</td>
        <td class="content-table-row__value">
            <c:out value="${content.revType == 0 ? 'Create' : (content.revType == 1 ? 'Update' : 'Delete')}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.firstName == content.oldFirstName ? '-' : content.firstName}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.lastName == content.oldLastName ? '-' : content.lastName}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.middleName == content.oldMiddleName ? '-' : content.middleName}" />
        </td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when 
                test="${ content.departmentName == content.oldDepartmentName || empty content.departmentName }">
                    -
                </c:when>
                <c:otherwise>
                    <a class="content-table-row__link" href="${ showDepartment }" target="_blank">
                        ${ content.departmentName }
                    </a>
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.hireDate == content.oldHireDate ? '-' : content.hireDate}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.salary == content.oldSalary ? '-' : content.salary}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${ not empty content.employeeDetailsId ? content.employeeDetailsId : '-' }" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.revisionTimestamp}" />
        </td>
        <td class="content-table-row__value">
            <c:out value = "${ content.updatedBy }" />
        </td>
    </tr>
    <tr class="content-table-row">
        <td class="content-table-row__value" colspan="${ colSpan }"></td>
    </tr>
</body>
</html>