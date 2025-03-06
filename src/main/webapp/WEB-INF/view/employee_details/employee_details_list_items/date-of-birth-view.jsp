<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <li 
        class="employee-details-list__item"
        data-title = "Date of birth"
    >
        <c:choose>
            <c:when test = "${ not empty birthday }">
                <span>${ birthday } (${ yearsOld } y.o)</span>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${ isFired }">
                        <span>Birthday not found</span>
                    </c:when>
                    <c:otherwise>
                        <span>Please specify date of birth</span>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </li>
</body>
</html>