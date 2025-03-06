<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <fieldset class="current-employee-fieldset current-employee-fieldset_phones">
        <legend class="current-employee-fieldset__legend">Phone numbers</legend>
        <c:choose>
            <c:when test = "${ not empty currentEmployeeDetails.phoneNumbers }">
                <c:forEach var="phone" items="${ currentEmployeeDetails.phoneNumbers }" varStatus = "status">
                    <jsp:include page = "../../form_views/text-number-input-view.jsp">
                        <jsp:param name = "formTitle" value = "current-employee-form" />
                        <jsp:param name = "labelTitle" value = "Enter current Employee phone number" />
                        <jsp:param name = "path" value = "phonesValue" />
                        <jsp:param name = "option" value = "typing-option" />
                        <jsp:param name = "type" value = "text" />
                        <jsp:param name = "value" value = "${ phone.value }" />
                        <jsp:param name = "index" value = "${ status.index }" />
                        <jsp:param name = "placeholder" value = "+(380)-xx-xxx-xx-xx" />
                    </jsp:include>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <jsp:include page = "../../form_views/text-number-input-view.jsp">
                    <jsp:param name = "formTitle" value = "current-employee-form" />
                    <jsp:param name = "labelTitle" value = "Enter current Employee phone number" />
                    <jsp:param name = "path" value = "phonesValue" />
                    <jsp:param name = "option" value = "typing-option" />
                    <jsp:param name = "type" value = "text" />
                    <jsp:param name = "index" value = "${ 0 }" />
                    <jsp:param name = "placeholder" value = "+380xxxxxxxxx" />
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