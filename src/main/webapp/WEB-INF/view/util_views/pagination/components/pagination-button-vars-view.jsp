<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ not empty showAttributes.placeTitle && not empty showAttributes.idValue }">
            <c:choose>
                <c:when test="${ empty searchEmployees.searchParams }">
                    <c:choose>
                        <c:when test="${ paginationCurrentPage == 1 }">
                            <c:url var="prev" value="${ path }" >
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="prev" value="${ path }">
                                <c:param name="page" value="${ paginationCurrentPage - 1 }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                    <c:url var="next" value="${ path }">
                        <c:param name="page" value="${ paginationCurrentPage + 1 }" />
                        <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                    </c:url>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${ paginationCurrentPage == 1 }">
                            <c:url var="prev" value="${ path }" >
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="prev" value="${ path }">
                                <c:param name="page" value="${ paginationCurrentPage - 1 }" />
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                    <c:url var="next" value="${ path }">
                        <c:param name="page" value="${ paginationCurrentPage + 1 }" />
                        <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                        <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                    </c:url>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${ empty searchEmployees.searchParams }">
                    <c:choose>
                        <c:when test="${ paginationCurrentPage == 1 }">
                            <c:url var="prev" value="${ path }" />
                        </c:when>
                        <c:otherwise>
                            <c:url var="prev" value="${ path }">
                                <c:param name="page" value="${ paginationCurrentPage - 1 }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                    <c:url var="next" value="${ path }">
                        <c:param name="page" value="${ paginationCurrentPage + 1 }" />
                    </c:url>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${ paginationCurrentPage == 1 }">
                            <c:url var="prev" value="${ path }" >
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="prev" value="${ path }">
                                <c:param name="page" value="${ paginationCurrentPage - 1 }" />
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                    <c:url var="next" value="${ path }">
                        <c:param name="page" value="${ paginationCurrentPage + 1 }" />
                        <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                    </c:url>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${ not empty showAttributes.placeTitle }">
            <c:url var="lastPage" value="${ path }">
                <c:param name="page" value="${ actualTotal }" />
                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
            </c:url>
        </c:when>
        <c:otherwise>
            <c:url var="lastPage" value="${ path }" >
                <c:param name="page" value="${ actualTotal }" />
            </c:url>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${ not empty showAttributes.placeTitle }">
            <c:url var="firstPage" value="${ path }">
                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
            </c:url>
        </c:when>
        <c:otherwise>
            <c:url var="firstPage" value="${ path }" />
        </c:otherwise>
    </c:choose>

    <c:set var="curPrevLink" value="${ paginationCurrentPage == 0 ? '#' : prev  }" />
    <c:set var="prevClassName" value="pagination__link ${ paginationCurrentPage == 0 ? 'pagination__link_disabled' : '' }" />
    
    <c:set var="curNextLink" value="${ paginationCurrentPage != actualTotal ? next : '#' }" />
    <c:set var="nextClassName" value="pagination__link ${ paginationCurrentPage == actualTotal ? 'pagination__link_disabled' : '' }" />
    <c:set var="beginValue" value="${ isMoreThanZero ? paginationCurrentPage - curParam : 0 }" />
    <c:set var="endValue" value="${ isLessThanMax ? paginationCurrentPage + curParam : actualTotal }" />
</body>
</html>