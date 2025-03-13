<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
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
</body>
</html>