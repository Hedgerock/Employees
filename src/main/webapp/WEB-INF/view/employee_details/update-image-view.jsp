<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../util_views/button-commands-view.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${ employee.department.name }: ${ employee.firstName } ${ employee.lastName } update image</title>
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
                <h1>Choose new picture</h1>
                <form 
                    method="POST" 
                    enctype="multipart/form-data"
                    class="current-employee-form" 
                    action="/updateImage"
                >
                    <label class="current-employee-form-label">
                        <div class="preview">
                           <c:choose>
                                <c:when test="${ imageExists }">
                                    <img
                                        class="preview__src"
                                        src="${pageContext.request.contextPath}/tmp/${ pictureSrc }" 
                                        alt=""
                                    />

                                </c:when>
                                <c:otherwise>
                                    <img class="preview__src"/>
                                </c:otherwise>
                           </c:choose>
                        </div>
                        <input
                            class="current-employee-form-label__input current-employee-form-label__input_file"
                            type="file" 
                            name="file" 
                            accept=".png, .jpeg, .webp, .jpg"
                        />
                    </label>

                    <input type="hidden" name="empId" value="${employee.id}" />
                    <input type="hidden" name="empDetId" value="${employee.employeeDetails.id}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button 
                        type="submit"
                        class="current-employee-form__submit-button"
                    >
                        Upload
                    </button>
                </form>
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
        <script src="${ pageContext.request.contextPath }/js/picturePreview.js"></script>
    </body>
</html>