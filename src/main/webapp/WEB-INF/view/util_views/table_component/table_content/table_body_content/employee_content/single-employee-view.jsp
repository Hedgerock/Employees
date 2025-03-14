<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../../../../button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <tr class="${ content.employeeDetails.hasNullFields && empty param.modificator && empty content.fireDate
        ? 'content-table-row content-table-row_not-full-details' 
        : 'content-table-row'} ${ param.modificator }"
    >
        <c:choose>
            <c:when test="${ not empty param.currentPlace }">
                <td class="content-table-row__value">
                    <form:checkbox 
                        class="current-checkbox" 
                        path="idCollector.idCollection"
                        id="${ content.id }"
                        value="${ content.id }"
                    />
                </td>
            </c:when>
        </c:choose>

        <td class="content-table-row__value">${ indexVal }</td>
        <c:set var="target" value="_blank" />

        <jsp:include page = "employee_rows/first-name-row-view.jsp">
            <jsp:param name = "link" value = "${ showButton }" />
            <jsp:param name = "target" value = "${ target }" />
        </jsp:include>

        <jsp:include page = "employee_rows/last-name-row-view.jsp">
            <jsp:param name = "link" value = "${ showButton }" />
            <jsp:param name = "target" value = "${ target }" />
        </jsp:include>

        <jsp:include page = "employee_rows/middle-name-row-view.jsp">
            <jsp:param name = "showLink" value = "${ showButton }" />
            <jsp:param name = "updateLink" value = "${ updateButton }" />
            <jsp:param name = "target" value = "${ target }" />
        </jsp:include>

        <c:choose>
            <c:when test="${ param.currentPlace != 'Department' }">
                <jsp:include page = "employee_rows/department-row-view.jsp">
                    <jsp:param name = "currentDepartment" value = "${ currentDepartment }" />
                    <jsp:param name = "updateButton" value = "${ updateButton }" />
                    <jsp:param name = "page" value = "${ param.page }" />
                </jsp:include>
            </c:when>
        </c:choose>

        <jsp:include page = "employee_rows/salary-row-view.jsp" />

        <c:choose>
            <c:when test="${ param.currentPlace != 'City' }">
                <jsp:include page = "employee_rows/city-row-view.jsp">
                    <jsp:param name = "currentCity" value="${ currentCity }" />
                    <jsp:param name = "updateButton" value = "${ updateButton }" />
                    <jsp:param name = "page" value = "${ param.page }" />
                </jsp:include>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${ param.currentPlace != 'Nationality' }">
                <jsp:include page = "employee_rows/nationality-row-view.jsp">
                    <jsp:param name = "currentNationality" value="${ currentNationality }" />
                    <jsp:param name = "updateButton" value = "${ updateButton }" />
                    <jsp:param name = "page" value = "${ param.page }" />
                </jsp:include>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${ empty param.currentPlace && empty content.fireDate }">
                <jsp:include page = "employee_rows/employee-details-option-view.jsp">
                    <jsp:param name = "updateDetailsButton" value = "${ updateDetailsButton }" />
                </jsp:include>

                <jsp:include page = "employee_rows/update-employee-option-view.jsp">
                    <jsp:param name = "updateButton" value = "${ updateButton }" />
                </jsp:include>

                <jsp:include page = "employee_rows/show-employee-history-option-view.jsp">
                    <jsp:param name = "historyButton" value = "${ historyButton }" />
                </jsp:include>

                <jsp:include page = "employee_rows/delete-employee-option-view.jsp">
                    <jsp:param name = "deleteButton" value = "${ deleteButton }" />
                </jsp:include>
            </c:when>
        </c:choose>
    </tr>
</body>
</html>