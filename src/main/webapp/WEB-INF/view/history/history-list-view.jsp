<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="responsive-table">
        <table class="content-table">
            <tr class="content-table-row">
                <th class="content-table-row__header">#</th>
                <th class="content-table-row__header" colspan="3">FULL_NAME</th>
                <th class="content-table-row__header">Department</th>
                <th class="content-table-row__header">Last changes</th>
            </tr>
            <c:forEach var = "employee" items = "${ allEmployees }" varStatus = "status">
                <c:set var="empId" value="${ employee.id }" />

                <c:url var="showHistory" value="/history">
                    <c:param name="empId" value="${ empId }" />
                </c:url>
                <c:url var="showDepartment" value="/showCurrentDepartment">
                    <c:param name="depId" value="${ employee.departmentId }" />
                </c:url>

                <tr class="content-table-row">
                    <td class="content-table-row__value">${ status.index + 1 + pageSize * paginationCurrentPage }</td>
                    <td class="content-table-row__value">
                        <a 
                            class="content-table-row__link"
                            href="${ showHistory }"
                        >
                            ${ employee.firstName }
                        </a>
                    </td>
                    <td class="content-table-row__value">
                        <a 
                            class="content-table-row__link"
                            href="${ showHistory }"
                        >
                            ${ employee.lastName }
                        </a>
                    </td>
                    <td class="content-table-row__value">
                        <a 
                            class="content-table-row__link"
                            href="${ showHistory }"
                        >
                            ${ empty employee.middleName ? '-' : employee.middleName}
                        </a>
                    </td>
                    <c:choose>
                        <c:when test="${ not empty employee.departmentName }">
                            <td 
                                class="content-table-row__value"
                            >
                                <a 
                                    class="content-table-row__link"
                                    href="${ showDepartment }"
                                >
                                    ${ employee.departmentName }
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td class="content-table-row__value">-</td>
                        </c:otherwise>
                    </c:choose>
                    <td 
                        title="${ employee.previousChanges }" 
                        class="content-table-row__value"
                    >
                        ${ employee.previousShortChanges } ago
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="pagination-view.jsp" />
</body>
</html>