<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <form:form
        class="current-employee-form"
        modelAttribute = "currentEmployee"
        action = "${ not empty action ? action : 'saveCurrentEmployee' }"
    >
        <form:hidden path = "id"/>

        <c:set var="actualDepartmentTitle" value = "${ not empty departmentName ? departmentName : 'current' }" />

        <jsp:include page = "../form_views/text-number-input-view.jsp">
            <jsp:param name = "formTitle" value = "current-employee-form" />
            <jsp:param 
                name = "labelTitle" 
                value = "Enter ${ actualDepartmentTitle } Employee first name" 
            />
            <jsp:param name = "path" value = "firstName" />
            <jsp:param name = "option" value = "typing-option" />
            <jsp:param name = "type" value = "text" />
        </jsp:include>

        <jsp:include page = "../form_views/text-number-input-view.jsp">
            <jsp:param name = "formTitle" value = "current-employee-form" />
            <jsp:param 
                name = "labelTitle" 
                value = "Enter ${ actualDepartmentTitle } Employee last name" 
            />
            <jsp:param name = "path" value = "lastName" />
            <jsp:param name = "option" value = "typing-option" />
            <jsp:param name = "type" value = "text" />
        </jsp:include>

        <jsp:include page = "../form_views/text-number-input-view.jsp">
            <jsp:param name = "formTitle" value = "current-employee-form" />
            <jsp:param 
                name = "labelTitle" 
                value = "Enter ${ actualDepartmentTitle } Employee middle name" 
            />
            <jsp:param name = "path" value = "middleName" />
            <jsp:param name = "option" value = "typing-option" />
            <jsp:param name = "type" value = "text" />
        </jsp:include>
        
        <c:choose>
            <c:when test = "${ not empty optionCollection }">
                <jsp:include page = "../form_views/select-input-view.jsp">
                    <jsp:param name = "formTitle" value = "current-employee-form" />
                    <jsp:param 
                        name = "labelTitle" 
                        value = "Enter ${ actualDepartmentTitle }  Employee department" 
                    />
                    <jsp:param name = "path" value = "selectionCollector.departmentName" />
                    <jsp:param name = "option" value = "selection-option" />
                </jsp:include>
            </c:when>
            <c:otherwise>
                <form:hidden path = "selectionCollector.departmentName" value = "${ departmentName }"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test = "${ not empty citiesCollection }">
                <label class="current-employee-form-label">
                    <h3
                        class="current-employee-form-label__title"
                    >
                        Enter Employee city:
                    </h3>
        
                    <form:select
                        path = "selectionCollector.cityName"
                        class = "current-employee-form-label__input current-employee-form-label__input_selection-option"
                        type = "${ param.type }"
                    >
        
                        <form:options items = "${ citiesCollection }" />
        
                    </form:select>
        
                </label>
            </c:when>
            <c:otherwise>
                <form:hidden path = "selectionCollector.cityName" value = "${ cityName }"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test = "${ not empty citiesCollection }">
                <label class="current-employee-form-label">
                    <h3
                        class="current-employee-form-label__title"
                    >
                        Enter Employee nationality:
                    </h3>
        
                    <form:select
                        path = "selectionCollector.nationalityName"
                        class = "current-employee-form-label__input current-employee-form-label__input_selection-option"
                        type = "${ param.type }"
                    >
        
                        <form:options items = "${ nationalitiesCollection }" />
        
                    </form:select>
        
                </label>
            </c:when>
            <c:otherwise>
                <form:hidden path = "selectionCollector.nationalityName" value = "${ nationalityName }"/>
            </c:otherwise>
        </c:choose>

        <jsp:include page = "../form_views/text-number-input-view.jsp">
            <jsp:param name = "formTitle" value = "current-employee-form" />
            <jsp:param 
                name = "labelTitle" 
                value = "Enter ${ actualDepartmentTitle }  Employee salary" 
            />
            <jsp:param name = "path" value = "salary" />
            <jsp:param name = "option" value = "typing-option" />
            <jsp:param name = "type" value = "number" />
        </jsp:include>

        <jsp:include page = "../employees/hidden-details-view.jsp" />

        <form:hidden
            path="hireDate"
            value="${ currentEmployee.hireDate }"
        />

        <form:hidden path="depId" />
        <form:hidden path="cityId" />
        <form:hidden path="nationalityId" />

        <form:hidden path="idFinder.departmentId" />
        <form:hidden path="idFinder.cityId" />
        <form:hidden path="idFinder.nationalityId" />

        <form:hidden path="employeeDetailsId" />
        
        <button 
            type="submit"
            class="current-employee-form__submit-button"
        >   Confirm
        </button>
    </form:form>
</body>
</html>