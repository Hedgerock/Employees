<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <c:set var="curContent" value="${ not empty employee ? employee : content }" />
        <c:set var="paramDepId" value="${ param.depId }" />
        <c:set var="isParamDepIdNotEmpty" value="${ not empty paramDepId }" />
        <c:set var="employeeId" value="${ not empty curContent.id ? curContent.id : employee.id }" />
        <c:set var="employeeDetailsId" value="${ not empty curContent.employeeDetailsId ? curContent.employeeDetailsId : employee.employeeDetailsId }" />
        <c:set var="paramPage" value="${ param.page }" />
        <c:set var="actualEmployeeId" value="${ empty employeeId ? id : employeeId }"/>
        <c:set var="employeeDepartmentId" value="${ curContent.department.id }" />
        <c:set var="cityId" value="${ param.cityId }" />
        <c:set var="isCityNotEmpty" value="${ not empty cityId }" />
        <c:set var="natId" value="${ param.natId }" />
        <c:set var="isNationalityNotEmpty" value="${ not empty natId }" />

        <c:set var="isFiredPage" value="${ isFired || not empty altAction }" />

        <c:choose>
            <c:when test = "${ isParamDepIdNotEmpty }">
                <c:url var = "updateDetailsButton" value = "/updateDetailsInfo">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "depId" value = "${ paramDepId }" />
                    <c:param name = "empDetId" value = "${ employeeDetailsId }" />
                </c:url>
            </c:when>
            <c:when test = "${ isCityNotEmpty }">
                <c:url var = "updateDetailsButton" value = "/updateDetailsInfo">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "cityId" value = "${ cityId }" />
                    <c:param name = "empDetId" value = "${ employeeDetailsId }" />
                </c:url>
            </c:when>
            <c:when test = "${ isNationalityNotEmpty }">
                <c:url var = "updateDetailsButton" value = "/updateDetailsInfo">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "natId" value = "${ natId }" />
                    <c:param name = "empDetId" value = "${ employeeDetailsId }" />
                </c:url>
            </c:when>
            <c:otherwise>
                <c:url var = "updateDetailsButton" value = "/updateDetailsInfo">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "empDetId" value = "${ employeeDetailsId }" />
                </c:url>
            </c:otherwise>
        </c:choose>
        
        <c:url var = "updateButton" value = "/updateInfo">
            <c:choose>
                <c:when test = "${ isParamDepIdNotEmpty }">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "depId" value = "${ paramDepId }" />
                </c:when>
                <c:when test = "${ isCityNotEmpty }">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "cityId" value = "${ cityId }" />
                </c:when>
                <c:when test = "${ isNationalityNotEmpty }">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "natId" value = "${ natId }" />
                </c:when>
                <c:otherwise>
                    <c:param name = "empId" value = "${ employeeId }" />
                </c:otherwise>
            </c:choose>
        </c:url>

        <c:choose>
            <c:when test = "${ not empty depId }">
                <c:url var = "showButton" value = "/showCurrentEmployeeDetails">
                    <c:param name = "empId" value = "${ actualEmployeeId }" />
                    <c:param name = "depId" value = "${ depId }" />
                </c:url>
            </c:when>
            <c:when test = "${ isCityNotEmpty }">
                <c:url var = "showButton" value = "/showCurrentEmployeeDetails">
                    <c:param name = "empId" value = "${ actualEmployeeId }" />
                    <c:param name = "cityId" value = "${ cityId }" />
                </c:url>
            </c:when>
            <c:when test = "${ isNationalityNotEmpty }">
                <c:url var = "showButton" value = "/showCurrentEmployeeDetails">
                    <c:param name = "empId" value = "${ actualEmployeeId }" />
                    <c:param name = "natId" value = "${ natId }" />
                </c:url>
            </c:when>
            <c:otherwise>
                <c:url var = "showButton" value = "${isFiredPage ? '/showFiredEmployeeDetails' : '/showCurrentEmployeeDetails'}">
                    <c:param name = "empId" value = "${ actualEmployeeId }" />
                </c:url>
            </c:otherwise>
        </c:choose>
        
        <c:set var="isValidPagCurPage" value="${ not empty paginationCurrentPage && paginationCurrentPage > 0 }" />

        <c:choose>
            <c:when test = "${ not empty paramPage && not empty cityId }">
                <c:choose>
                    <c:when test="${ isValidPagCurPage }">
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "cityId" value = "${ cityId }" />
                            <c:param name = "curPageNum" value="${ paginationCurrentPage }" />
                            <c:param name = "pageSize" value="${ allEmployees.size() }" />
                        </c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "cityId" value = "${ cityId }" />
                        </c:url>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test = "${ not empty paramPage && not empty natId }">
                <c:choose>
                    <c:when test="${ isValidPagCurPage }">
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "natId" value = "${ natId }" />
                            <c:param name = "curPageNum" value="${ paginationCurrentPage }" />
                            <c:param name = "pageSize" value="${ allEmployees.size() }" />
                        </c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "natId" value = "${ natId }" />
                        </c:url>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test = "${ not empty paramPage && not empty depId }">
                <c:choose>
                    <c:when test="${ isValidPagCurPage }">
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "depId" value = "${ depId }" />
                            <c:param name = "curPageNum" value="${ paginationCurrentPage }" />
                            <c:param name = "pageSize" value="${ allEmployees.size() }" />
                        </c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "depId" value = "${ depId }" />
                        </c:url>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${ isValidPagCurPage }">
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                            <c:param name = "pageVal" value = "${ paramPage }" />
                            <c:param name = "curPageNum" value="${ paginationCurrentPage }" />
                            <c:param name = "pageSize" value="${ allEmployees.size() }" />
                        </c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url var = "deleteButton" value = "/deleteInfo">
                            <c:param name = "empForDelId" value = "${ employeeId }" />
                        </c:url>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${ not empty depId }">
                <c:url var = "historyButton" value = "/history">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "depId" value = "${ employeeDepartmentId }" />
                </c:url>
            </c:when>
            <c:when test="${ not empty cityId }">
                <c:url var = "historyButton" value = "/history">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "cityId" value = "${ cityId }" />
                </c:url>
            </c:when>
            <c:when test="${ not empty natId }">
                <c:url var = "historyButton" value = "/history">
                    <c:param name = "empId" value = "${ employeeId }" />
                    <c:param name = "natId" value = "${ natId }" />
                </c:url>
            </c:when>
            <c:otherwise>
                <c:url var = "historyButton" value = "/history">
                    <c:param name = "empId" value = "${ employeeId }" />
                </c:url>
            </c:otherwise>
        </c:choose>
    
        <c:url var = "currentDepartment" value = "/showCurrentDepartment">
            <c:param name = "depId" value = "${ not empty employeeDepartmentId ? employeeDepartmentId : depId  }" />
        </c:url>
        <c:url var = "currentCity" value = "/showCurrentCity">
            <c:param name = "cityId" value = "${ curContent.cityId }" />
        </c:url>
        <c:url var = "currentNationality" value = "/showCurrentNationality">
            <c:param name = "natId" value = "${ curContent.nationalityId }" />
        </c:url>
    </body>
</html>