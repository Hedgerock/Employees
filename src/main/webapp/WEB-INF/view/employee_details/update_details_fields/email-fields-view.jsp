<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <fieldset class="current-employee-fieldset current-employee-fieldset_emails">
        <legend class="current-employee-fieldset__legend">Emails</legend>
        <c:choose>
            <c:when test = "${ not empty currentEmployeeDetails.emails }">
                <c:forEach var="email" items="${ currentEmployeeDetails.emails }" varStatus = "status">
                    <jsp:include page = "../../form_views/text-number-input-view.jsp">
                        <jsp:param name = "formTitle" value = "current-employee-form" />
                        <jsp:param name = "labelTitle" value = "Enter current Employee email" />
                        <jsp:param name = "path" value = "emailsValue" />
                        <jsp:param name = "option" value = "typing-option" />
                        <jsp:param name = "type" value = "email" />
                        <jsp:param name = "value" value = "${ email.value }" />
                        <jsp:param name = "index" value = "${ status.index }" />
                    </jsp:include>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <jsp:include page = "../../form_views/text-number-input-view.jsp">
                    <jsp:param name = "formTitle" value = "current-employee-form" />
                    <jsp:param name = "labelTitle" value = "Enter current Employee email" />
                    <jsp:param name = "path" value = "emailsValue" />
                    <jsp:param name = "option" value = "typing-option" />
                    <jsp:param name = "type" value = "email" />
                    <jsp:param name = "index" value = "${ 0 }" />
                </jsp:include>
            </c:otherwise>
        </c:choose>
        <button
            title = "Add new field for content"
            class="current-employee-fieldset__add-button"
        >
            <i class="fa-solid fa-plus"></i>
        </button>
    </fieldset>
</body>
</html>