<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var = "minSalaryDiff" value = "${ content.minSalary - content.oldMinSalary }" />
    <c:set var = "maxSalaryDiff" value = "${ content.maxSalary - content.oldMaxSalary }" />
    <c:set var = "totalEmployeesDiff" value = "${ content.totalEmployees - content.oldTotalEmployees }" />

    <c:set var = "colSpan" value = '11' />

    <tr class="content-table-row">
        <td class="content-table-row__value" colspan="${ colSpan }"></td>
    </tr>
    <c:if test="${content.revType == 1 && not empty content.oldName }">
        <tr class="content-table-row">
            <td class="content-table-row__value" colspan="${ colSpan }">Previous Values:</td>
        </tr>
        <tr class="content-table-row">
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value">-</td>
            <td class="content-table-row__value"><c:out value="${content.oldName}" /></td>
            <td class="content-table-row__value">
                <c:out value="${content.oldMinSalary}" />
                (${ minSalaryDiff > 0 ? '+' : '' }${ minSalaryDiff })
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldMaxSalary}" />
                (${ maxSalaryDiff > 0 ? '+' : '' }${ maxSalaryDiff })
            </td>
            <td class="content-table-row__value">
                <c:out value="${content.oldTotalEmployees}" />
                (${ totalEmployeesDiff > 0 ? '+' : '' }${ totalEmployeesDiff })
            </td>
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
            <c:out value="${content.name == content.oldName ? '-' : content.name}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.minSalary == content.oldMinSalary ? '-' : content.minSalary}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.maxSalary == content.oldMaxSalary ? '-' : content.maxSalary}" />
        </td>
        <td class="content-table-row__value">
            <c:out value="${content.totalEmployees == content.oldTotalEmployees ? '-' : content.totalEmployees}" />
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