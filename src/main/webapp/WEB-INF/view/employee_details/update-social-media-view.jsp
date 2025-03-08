<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <c:set var="pictureSrc" value="${ employee.employeeDetails.picture.pictureSrc }" />
        <h1>Social media Links</h1>
        <form:form
            class="current-employee-form"
            modelAttribute = "socialMedia"
            action = "saveSocialMedia"
        >   
            <form:hidden  path="id"/>

            <label class="current-employee-form-label">
                <h3 class="current-employee-form-label__title">
                    Enter current employee telegram:
                </h3>
                <form:hidden path="telegram.id" />
                <form:input 
                    path = "telegram.link"
                    placeHolder="Telegram"
                    class = "current-employee-form-label__input current-employee-form-label__input_typing-option"
                />
            </label>

            <label class="current-employee-form-label">
                <h3 class="current-employee-form-label__title">
                    Enter current employee viber:
                </h3>
                <form:hidden path="viber.id" />
                <form:input 
                    path = "viber.link"
                    placeHolder="Viber"
                    class = "current-employee-form-label__input current-employee-form-label__input_typing-option"
                />
            </label>

            <label class="current-employee-form-label">
                <h3 class="current-employee-form-label__title">
                    Enter current employee whatsapp:
                </h3>
                <form:hidden path="whatsApp.id" />
                <form:input 
                    path = "whatsApp.link"
                    placeHolder="WhatsApp"
                    class = "current-employee-form-label__input current-employee-form-label__input_typing-option"
                />
            </label>

            <label class="current-employee-form-label">
                <h3 class="current-employee-form-label__title">
                    Enter current employee linkedin:
                </h3>
                <form:hidden path="linkedIn.id" />
                <form:input 
                    path = "linkedIn.link"
                    placeHolder="LinkedIn"
                    class = "current-employee-form-label__input current-employee-form-label__input_typing-option"
                />
            </label>
            
            <form:hidden path="idFinder.departmentId" />
            <form:hidden path="idFinder.cityId" />
            <form:hidden path="idFinder.nationalityId" />

            <input type="hidden" name="empId" value="${employee.id}" />
            <input type="hidden" name="empDetId" value="${employee.employeeDetails.id}" />
            <button 
                type="submit"
                class="current-employee-form__submit-button"
            >
                Confirm
            </button>
        </form:form>
    </body>
</html>