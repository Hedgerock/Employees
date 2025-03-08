<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<body>
    <h3>Change password option</h3>
    <form:form 
        action="changePassword"
        class="user-form"
        modelAttribute="passwords"
    >
        <label class="user-form__label user-form__label_password">
            New Password:
            <form:password path="password" />
            <button class="user-form__button user-form__button_hidden">
                <i class="fa-solid fa-eye-slash"></i>
            </button>
        </label>

        <label class="user-form__label user-form__label_password">
            Repeat new password:
            <form:password path="repeatPassword" />
            <button class="user-form__button user-form__button_hidden">
                <i class="fa-solid fa-eye-slash"></i>
            </button>
        </label>

        <button 
            type="submit"
            class="user-form__confirm-button"
        >
            Change password
        </button>
    </form:form>

    <script src="${ pageContext.request.contextPath }/js/userForm.js"></script>
</body>
</html>