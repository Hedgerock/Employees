<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <form:form 
            action="${ not empty param.altAction ? param.altAction : param.action }"
            modelAttribute = "searchEmployees"
            method = "get"
            class = "find-employee-form"
        >
            <c:choose>
                <c:when test = "${ not empty param.depId }">
                    <input type = "hidden" name="depId" value="${ param.depId }" />
                </c:when>
                <c:when test = "${ not empty param.cityId }">
                    <input type = "hidden" name="cityId" value="${ param.cityId }" />
                </c:when>
                <c:when test = "${ not empty param.natId }">
                    <input type = "hidden" name="natId" value="${ param.natId }" />
                </c:when>
            </c:choose>
            <jsp:include page = "/WEB-INF/view/form_views/search-input-view.jsp">
                <jsp:param name = "placeHolder" value = "${ param.placeHolder }"/>
                <jsp:param name = "formTitle" value = "find-employee-form"/>
            </jsp:include>

            <button 
                title="find employees" 
                type="submit"
                class = "find-employee-form__button"
            >
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
            
        </form:form>
    </body>
</html>