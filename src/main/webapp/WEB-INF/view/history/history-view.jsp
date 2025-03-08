<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <div class="responsive-table">
            <table class="content-table">
                <caption class="content-table-caption">
                    <a class="content-table-caption__link" href="${ showButton }">
                        ${ title }
                    </a>
                </caption>
                <thead class="content-table-head">
                    <tr class="content-table-row">
                        <th 
                            title = "Current Number" 
                            class = "content-table-row__header"
                        >
                            #
                        </th>
                        <th title = "Revision Type" class="content-table-row__header">RT</th>
                        <th title = "First Name" class="content-table-row__header">FN</th>
                        <th title = "Last Name" class="content-table-row__header">LN</th>
                        <th title = "Middle Name" class="content-table-row__header">MN</th>
                        <th title = "Department name" class="content-table-row__header">DN</th>
                        <th title = "Hire Date" class="content-table-row__header">HD</th>
                        <th title = "Salary" class="content-table-row__header">S</th>
                        <th title = "Employee Details ID"class="content-table-row__header">ED_ID</th>
                        <th title = "Updated Time" class="content-table-row__header">UT</th>
                        <th title = "Updated By" class="content-table-row__header">UB</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="revision" items="${revisions}" varStatus = "status">
                        <c:set var = "diff" value = "${ revision.salary - revision.oldSalary }" />
                        <c:set var = "colSpan" value = '11' />
                        <c:url var = "showOldDepartment" value="/showCurrentDepartment">
                            <c:param name="depId" value="${ revision.oldDepartmentId }" />
                        </c:url> 
                        <c:url var = "showDepartment" value="/showCurrentDepartment">
                            <c:param name="depId" value="${ revision.departmentId }" />
                        </c:url> 

                        <tr class="content-table-row">
                            <td class="content-table-row__value" colspan="${ colSpan }"></td>
                        </tr>
                        <c:if test="${revision.revType == 1 && not empty revision.oldFirstName }">
                            <tr class="content-table-row">
                                <td class="content-table-row__value" colspan="${ colSpan }">Previous Values:</td>
                            </tr>
                            <tr class="content-table-row">
                                <td class="content-table-row__value">-</td>
                                <td class="content-table-row__value">-</td>
                                <td class="content-table-row__value">
                                    <c:out value="${revision.oldFirstName}" />
                                </td>
                                <td class="content-table-row__value">
                                    <c:out value="${revision.oldLastName}" />
                                </td>
                                <td class="content-table-row__value">
                                    <c:out value="${revision.oldMiddleName}" />
                                </td>
                                <td class="content-table-row__value">
                                    <c:choose>
                                        <c:when test="${ not empty revision.oldDepartmentName }">
                                            <a class="content-table-row__link" href="${ showOldDepartment }" target="_blank">
                                                <c:out value="${revision.oldDepartmentName}" />
                                            </a>
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="content-table-row__value">
                                    <c:out value="${revision.oldHireDate}" />
                                </td>
                                <td class="content-table-row__value">
                                    <c:out value="${revision.oldSalary}" />
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
                            <td class="content-table-row__value">${ status.index + 1 + pageSize * paginationCurrentPage }</td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.revType == 0 ? 'Create' : (revision.revType == 1 ? 'Update' : 'Delete')}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.firstName == revision.oldFirstName ? '-' : revision.firstName}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.lastName == revision.oldLastName ? '-' : revision.lastName}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.middleName == revision.oldMiddleName ? '-' : revision.middleName}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:choose>
                                    <c:when 
                                    test="${ revision.departmentName == revision.oldDepartmentName || empty revision.departmentName }">
                                        -
                                    </c:when>
                                    <c:otherwise>
                                        <a class="content-table-row__link" href="${ showDepartment }" target="_blank">
                                            ${ revision.departmentName }
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.hireDate == revision.oldHireDate ? '-' : revision.hireDate}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.salary == revision.oldSalary ? '-' : revision.salary}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${ not empty revision.employeeDetailsId ? revision.employeeDetailsId : '-' }" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value="${revision.revisionTimestamp}" />
                            </td>
                            <td class="content-table-row__value">
                                <c:out value = "${ revision.updatedBy }" />
                            </td>
                        </tr>
                        <tr class="content-table-row">
                            <td class="content-table-row__value" colspan="${ colSpan }"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="pagination-view.jsp" />
    </body>
</html>

