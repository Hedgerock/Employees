<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <div class="responsive-table">
            <c:set 
                var="actualValue" 
                value="${ not empty showPlacesAttributes ? showPlacesAttributes : showAttributes }" 
            />
            <table class="content-table">
                <tr class="content-table-row">
                    <c:choose>
                        <c:when test="${ not actualValue.hasSingleValue }">
                            <th class="content-table-row__header">#</th>
                        </c:when>
                    </c:choose>
                    <th class="content-table-row__header">Name</th>
                    <th class="content-table-row__header">Min_salary</th>
                    <th class="content-table-row__header">Max_salary</th>
                    <th class="content-table-row__header">Total_employees</th>
                    <th class="content-table-row__header" colspan="3">Options</th>
                </tr>

                <c:forEach 
                    var="currentPlace"
                    items="${actualValue.currentPlaces}"
                    varStatus="status"
                >
                    <c:set var = "currentPlace" value = "${ currentPlace }" scope = "request"/>
                    <c:set var = "status" value = "${ status }" scope = "request"/>
    
                    <jsp:include page="single-place-view.jsp" />
                </c:forEach>
            </table>
            <jsp:include page="../util_views/siblings-view.jsp">
                <jsp:param name="withParams" value="${ false }" />
                <jsp:param name="alterLink" value="${ showCurrentCity }" />
                <jsp:param name="paramTitle" value="${ actualValue.placeTitle }" />
            </jsp:include>
        </div>
    </body>
</html>
