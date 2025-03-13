<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <form:form
            class="current-employee-form"
            modelAttribute = "currentEmployeeDetails"
            action = "saveEmployeeDetails"
        >
            <form:hidden path="potentialParentId" value="${param.empId}" />
            <form:hidden path="pictureId" />
            <form:hidden path="idFinder.departmentId" />
            <form:hidden path="idFinder.cityId" />
            <form:hidden path="idFinder.nationalityId" />
            
            <label class="current-employee-form-label">
                <h3 class="current-employee-form-label__title">
                    Enter current employee birthday:
                </h3>
                <input 
                    type="date" 
                    name="dateOfBirth" 
                    id="dateOfBirth"
                    class="current-employee-form-label__input"
                    value="${ currentEmployeeDetails.dateOfBirth }"
                />
            </label>

            <div class="current-employee-form-container">
                <jsp:include 
                    page = "../employee_details/update_details_fields/email-fields-view.jsp" 
                />
                <jsp:include 
                    page = "../employee_details/update_details_fields/phone-numbers-view.jsp" 
                />
            </div>

            <button 
                type="submit"
                class="current-employee-form__submit-button"
            >   Confirm
            </button>
        </form:form>
        <script type="module" src="${ pageContext.request.contextPath }/js/emails/emailMain.js"></script>
    </body>
</html>