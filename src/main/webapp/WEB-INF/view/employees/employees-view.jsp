<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <div class="responsive-table">
            <table class="content-table ${ param.tableModificator }">
                <thead class="content-table-head content-table-head_default">
                    <tr class="content-table-row">
                        <c:choose>
                            <c:when test="${ not empty param.currentPlace && not empty allEmployees }">
                                <th class="content-table-row__header" title="Select all">
                                    <input class="select-all" type="checkbox" />
                                </th>
                            </c:when>
                        </c:choose>
                        <th class="content-table-row__header">#</th>
                        <th class="content-table-row__header">First_name</th>
                        <th class="content-table-row__header">Last_name</th>
                        <th class="content-table-row__header">Middle_name</th>
    
                        <c:choose>
                            <c:when test="${ param.currentPlace != 'CurrentDepartment' }">
                                <th class="content-table-row__header">Department</th>
                            </c:when>
                        </c:choose>
                        <th class="content-table-row__header">Salary $</th>
                        <c:choose>
                            <c:when test="${ param.currentPlace != 'City' }">
                                <th class="content-table-row__header">City</th>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${ param.currentPlace != 'Nationality' }">
                                <th class="content-table-row__header">Nationality</th>
                            </c:when>
                        </c:choose>
                        
                        <c:choose>
                            <c:when test="${ empty param.currentPlace && empty altAction }">
                                <th class="content-table-row__header" colspan="4">Options</th>
                            </c:when>
                        </c:choose>
                    </tr>
                </thead>
    
                <c:choose>
                    <c:when test="${ not empty allEmployees }">
                        <c:forEach var = "employee" items = "${ allEmployees }" varStatus = "status">
                            <c:set var = "employee" value = "${ employee }" scope = "request"/>
                            <c:set var = "status" value = "${ status }" scope = "request"/>
                            <c:set var = "page" value = "${ param.page }" scope = "request"/>
                            <c:set var = "depId" value = "${ param.depId }" scope = "request" />
                            <jsp:include page = "single-employee-view.jsp">
                                <jsp:param name="currentPlace" value="${ param.currentPlace }" />
                                <jsp:param name="modificator" value="${ param.rowModificatior }" />
                            </jsp:include>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr class="content-table-row">
                            <td class="content-table-row__value" colspan="9">
                                <jsp:include page="../util_views/not-found-image-view.jsp">
                                    <jsp:param name="image" value="not-found.png" />
                                </jsp:include>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>

        <c:set var="curParam" value="2" scope="request" />
        <c:set var="placeTitle" value="${ placeTitle }" scope="request" />
        <c:set var="isMoreThanZero" value="${ paginationCurrentPage - curParam > 0 }" scope="request"/>
        <c:set var="actualTotal" value="${ total - 1 }" scope="request"/>
        <c:set var="isLessThanMax" value="${ paginationCurrentPage + curParam < actualTotal }" scope="request"/>

        <c:if test="${ actualTotal > 0 }">
            <jsp:include page="../util_views/pagination/pagination-view.jsp"/>
        </c:if>
    </body>
</html>