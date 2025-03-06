<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <form:form 
        action="saveUser"
        class="user-form"
        modelAttribute="newUser"
    >
        <label class="user-form__label">
            Username:
            <form:input path="username" />
        </label>
       
        <label class="user-form__label user-form__label_password">
            Password:
            <form:password path="password" />
            <button class="user-form__button user-form__button_hidden">
                <i class="fa-solid fa-eye-slash"></i>
            </button>
        </label>

        <label class="user-form__label user-form__label_password">
            Repeat password:
            <form:password path="repeatPassword" />
            <button class="user-form__button user-form__button_hidden">
                <i class="fa-solid fa-eye-slash"></i>
            </button>
        </label>

        <label class="user-form__label">
            Role:
            <form:select path="authority">
                <form:options items="${ roles }" />
            </form:select>
        </label>

        <button 
            type="submit"
            class="user-form__confirm-button"
        >
            Add new user
        </button>
    </form:form>

    <script src="${ pageContext.request.contextPath }/js/userForm.js"></script>
</body>
</html>