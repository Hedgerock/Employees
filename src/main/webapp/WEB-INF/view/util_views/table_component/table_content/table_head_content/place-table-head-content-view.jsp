<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
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
</body>
</html>