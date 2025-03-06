<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ title }</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="skeleton">
            <header>
                <jsp:include page = "../header-navbar-view.jsp" />
                <jsp:include page = "../util_views/search-setup-view.jsp" />
                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
                <div class="responsive-table">
                    <table class="content-table">
                        <c:url var="showPlace" value="/${ showRef }">
                            <c:param name="${ idTitle }" value="${ currentId }" />
                        </c:url>

                        <caption class="content-table-caption">
                            <a class="content-table-caption__link" href="${ showPlace }">
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
                                <th title = "Name" class="content-table-row__header">N</th>
                                <th title = "Min salary" class="content-table-row__header">MINS</th>
                                <th title = "Max salary" class="content-table-row__header">MAXS</th>
                                <th title = "Total employees" class="content-table-row__header">TE</th>
                                <th title = "Updated Time" class="content-table-row__header">UT</th>
                                <th title = "Updated By" class="content-table-row__header">UB</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="revision" items="${revisions}" varStatus = "status">
                                <c:set var = "minSalaryDiff" value = "${ revision.minSalary - revision.oldMinSalary }" />
                                <c:set var = "maxSalaryDiff" value = "${ revision.maxSalary - revision.oldMaxSalary }" />
                                <c:set var = "totalEmployeesDiff" value = "${ revision.totalEmployees - revision.oldTotalEmployees }" />
    
                                <c:set var = "colSpan" value = '11' />
    
                                <tr class="content-table-row">
                                    <td class="content-table-row__value" colspan="${ colSpan }"></td>
                                </tr>
                                <c:if test="${revision.revType == 1 && not empty revision.oldName }">
                                    <tr class="content-table-row">
                                        <td class="content-table-row__value" colspan="${ colSpan }">Previous Values:</td>
                                    </tr>
                                    <tr class="content-table-row">
                                        <td class="content-table-row__value">-</td>
                                        <td class="content-table-row__value">-</td>
                                        <td class="content-table-row__value"><c:out value="${revision.oldName}" /></td>
                                        <td class="content-table-row__value">
                                            <c:out value="${revision.oldMinSalary}" />
                                            (${ minSalaryDiff > 0 ? '+' : '' }${ minSalaryDiff })
                                        </td>
                                        <td class="content-table-row__value">
                                            <c:out value="${revision.oldMaxSalary}" />
                                            (${ maxSalaryDiff > 0 ? '+' : '' }${ maxSalaryDiff })
                                        </td>
                                        <td class="content-table-row__value">
                                            <c:out value="${revision.oldTotalEmployees}" />
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
                                    <td class="content-table-row__value">${ status.index + 1 }</td>
                                    <td class="content-table-row__value">
                                        <c:out value="${revision.revType == 0 ? 'Create' : (revision.revType == 1 ? 'Update' : 'Delete')}" />
                                    </td>
                                    <td class="content-table-row__value">
                                        <c:out value="${revision.name == revision.oldName ? '-' : revision.name}" />
                                    </td>
                                    <td class="content-table-row__value">
                                        <c:out value="${revision.minSalary == revision.oldMinSalary ? '-' : revision.minSalary}" />
                                    </td>
                                    <td class="content-table-row__value">
                                        <c:out value="${revision.maxSalary == revision.oldMaxSalary ? '-' : revision.maxSalary}" />
                                    </td>
                                    <td class="content-table-row__value">
                                        <c:out value="${revision.totalEmployees == revision.oldTotalEmployees ? '-' : revision.totalEmployees}" />
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
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>

