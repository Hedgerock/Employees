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
                <jsp:include page = "../util_views/search-setup-view.jsp" />
                <jsp:include page = "../util_views/header-authorized-user-view.jsp" />
            </header>
            <main>
                <div class="responsive-table">
                    <table class="content-table">
                        <tr class="content-table-row">
                            <th class="content-table-row__header">#</th>
                            <th class="content-table-row__header">Name</th>
                            <th class="content-table-row__header">Min_salary</th>
                            <th class="content-table-row__header">Max_salary</th>
                            <th class="content-table-row__header">Total_employees</th>
                            <th class="content-table-row__header">Previous_changes</th>
                        </tr>
                        <c:forEach var = "currentPlace" items = "${ currentPlaces }" varStatus = "status">
                            <tr class="content-table-row">
                                <td class="content-table-row__value">${ status.index + 1 }</td>

                                <c:url var="showCurrentHistory" value="/${ showHistory }">
                                    <c:param name="${ idTitle }" value="${ currentPlace.id }"/>
                                </c:url>

                                <td class="content-table-row__value">
                                    <a 
                                        class="content-table-row__link"
                                        href="${ showCurrentHistory }"
                                    >
                                        ${ currentPlace.name }
                                    </a>
                                </td>
                                <td class="content-table-row__value">
                                    <a 
                                        class="content-table-row__link"
                                        href="${ showCurrentHistory }"
                                    >
                                        ${ currentPlace.minSalary == 0 ? '-' : currentPlace.minSalary }
                                    </a>
                                </td>
                                <td class="content-table-row__value">
                                    <a 
                                        class="content-table-row__link"
                                        href="${ showCurrentHistory }"
                                    >
                                        ${ currentPlace.maxSalary == 0 ? '-' : currentPlace.maxSalary }
                                    </a>
                                </td>
                                <td class="content-table-row__value">
                                    <a 
                                        class="content-table-row__link"
                                        href="${ showCurrentHistory }"
                                    >
                                        ${ currentPlace.totalEmployees == 0 ? '-' : currentPlace.totalEmployees }
                                        ${ currentPlace.totalEmployees == 0 ? '' : (currentPlace.totalEmployees == 0 ? 'employee' : 'employees') }
                                    </a>
                                </td>
                                <td 
                                    title="${ currentPlace.previousChanges }" 
                                    class="content-table-row__value"
                                >
                                    ${ currentPlace.previousShortChanges } ago
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <jsp:include page="pagination-view.jsp" />
            </main>
            <jsp:include page = "../footer-view.jsp" />
        </div>
    </body>
</html>