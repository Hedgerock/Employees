<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test = "${ depId != null }">
            <form:hidden 
                path = "currentDepartmentId"
                value = "${ depId}" 
            />
        </c:when>
        <c:otherwise>
            <form:hidden 
                path = "currentDepartmentId"
                value = "${ not empty currentEmployee.currentDepartmentId 
                    ? currentEmployee.currentDepartmentId 
                    : currentDepartmentId 
                }" 
            />
        </c:otherwise>
    </c:choose>
</body>
</html>