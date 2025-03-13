<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="teNotEmp" value="${not empty generalInfo.totalEmployees}" />
    <c:set var="tdNotEmp" value="${not empty generalInfo.totalDepartments}" />
    <c:set var="tcNotEmp" value="${not empty generalInfo.totalCities}" />
    <c:set var="tsNotEmp" value="${not empty generalInfo.totalNationalities}" />
    <c:set var="tsNotEmp" value="${not empty generalInfo.totalSalaryModified}" />

    <c:set var="totalEmployees" value="${ teNotEmp ? generalInfo.totalEmployees : '-' }" />
    <c:set var="totalDepartments" value="${ tdNotEmp ? generalInfo.totalDepartments : '-' }" />
    <c:set var="totalCities" value="${ tcNotEmp ? generalInfo.totalCities : '-' }" />
    <c:set var="totalNationalities" value="${ tcNotEmp ? generalInfo.totalNationalities : '-' }" />
    <c:set var="totalSalary" value="${ tsNotEmp ? generalInfo.totalSalaryModified : '-' }" />

    <tr class="content-table-row">
        <td class="content-table-row__value">${ totalEmployees }</td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ totalDepartments != 0 }">
                    <a href="departments" class="content-table-row__link">${ totalDepartments }</a>
                </c:when>
                <c:otherwise>
                    -
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ totalCities != 0 }">
                    <a href="cities" class="content-table-row__link">${ totalCities }</a>
                </c:when>
                <c:otherwise>
                    -
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">
            <c:choose>
                <c:when test="${ totalNationalities != 0 }">
                    <a href="nationalities" class="content-table-row__link">${ totalNationalities }</a>
                </c:when>
                <c:otherwise>
                    -
                </c:otherwise>
            </c:choose>
        </td>
        <td class="content-table-row__value">${ totalSalary }</td>
    </tr>
</body>
</html>