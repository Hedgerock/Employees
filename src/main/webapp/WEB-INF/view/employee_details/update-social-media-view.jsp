<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ employee.department.name }: ${ employee.firstName } ${ employee.lastName } update social media</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <c:set var="pictureSrc" value="${ employee.employeeDetails.picture.pictureSrc }" />
        <div class="skeleton">
            <header>
                <jsp:include page = "../header-navbar-view.jsp" />
                <jsp:include page = "../util_views/search-setup-view.jsp" />
            </header>
            <main>
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
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>