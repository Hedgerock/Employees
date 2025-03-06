<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <jsp:include page="../util_views/options-view.jsp" />
    <form:form
        class="current-employee-form"
        modelAttribute = "${ param.attribute }"
        method = "post"
        action = "${ not empty param.updateAction ? param.updateAction : param.mainAction }"
    >
        <form:hidden path = "id"/>
        <form:hidden path = "minSalary"/>
        <form:hidden path = "maxSalary"/>
        <form:hidden path = "totalEmployees"/>

        <jsp:include page = "../form_views/text-number-input-view.jsp">
            <jsp:param name = "formTitle" value = "current-employee-form" />
            <jsp:param name = "labelTitle" value = "Enter ${ param.currentPlace } name" />
            <jsp:param name = "path" value = "name" />
            <jsp:param name = "option" value = "typing-option" />
            <jsp:param name = "type" value = "text" />
        </jsp:include>

        <button 
            type="submit"
            class="current-employee-form__submit-button"
        >   Confirm
        </button>
        
        <div class="table-wrapper">
            <table class="content-table content-table_selected-employees">
                <caption class="content-table-caption">
                    <i class="fa-solid fa-users-slash"></i>
                    <h1>Employees are not selected</h1>
                </caption>
            </table>
        </div>
    </form:form>
    <jsp:include page = "../employees/employees-view.jsp">
        <jsp:param name = "page" value = "${ page }" />
        <jsp:param name = "cityId" value = "${ cityId }" />
        <jsp:param name="tableModificator" value="content-table_potential-employees" />
        <jsp:param name="rowModificatior" value="content-table-row_values" />
        <jsp:param name = "currentPlace" value = "${ param.currentPlace }" />
    </jsp:include>

    <script type="module" src="${pageContext.request.contextPath}/js/employees.js"></script>
</body>
</html>