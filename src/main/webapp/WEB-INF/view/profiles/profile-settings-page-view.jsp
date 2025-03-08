<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <ul class="settings-list">
        <li class="settings-list__item">
            <jsp:include page="utils/settings_utils/change-password-view.jsp" />
        </li>
        <li class="settings-list__item">
            <h3>Update details option</h3>
            <form:form
                action="saveUserDetails"
                class="user-form"
                modelAttribute="userDetails"
            >
                <label class="user-form__label">
                    First name:
                    <form:input path="firstName" />
                </label>

                <label class="user-form__label">
                    Last name:
                    <form:input path="lastName" />
                </label>

                <label class="user-form__label">
                    Middle name:
                    <form:input path="middleName" />
                </label>

                <label class="user-form__label">
                    Date of birth:
                    <input type="date" name="dateOfBirth" />
                </label>

                <label class="user-form__label">
                    Email:
                    <input type="email" name="email"/>
                </label>

                <label class="user-form__label">
                    Phone number:
                    <input type="number" name="phoneNumber" />
                </label>

                <button 
                    type="submit"
                    class="user-form__confirm-button"
                >
                    Confirm details
                </button>
            </form:form>
        </li>
    </ul>
</body>
</html>