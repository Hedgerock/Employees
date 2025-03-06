<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ title }</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="skeleton">
            <header>
                <jsp:include page = "../header-navbar-view.jsp" />
                <jsp:include page = "../util_views/search-setup-view.jsp">
                    <jsp:param name = "depId" value = "${ currentDepartment.id }" />
                </jsp:include>
                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
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
            </main>
            <jsp:include page = "../footer-view.jsp" />
            <script type="module" src="${pageContext.request.contextPath}/js/emails/emailMain.js"></script>
        </div>
    </body>
</html>