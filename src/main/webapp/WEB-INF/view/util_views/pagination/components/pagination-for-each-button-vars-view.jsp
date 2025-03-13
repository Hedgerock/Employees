<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:choose>
        <c:when test="${ not empty showAttributes.placeTitle  }">
            <c:choose>
                <c:when test="${ empty searchEmployees.searchParams  }">
                    <c:choose>
                        <c:when test="${ i != 0 }">
                            <c:url var="curPage" value="${ path }">
                                <c:param name="page" value="${ i }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="curPage" value="${ path }" >
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${ i != 0 }">
                            <c:url var="curPage" value="${ path }">
                                <c:param name="page" value="${ i }" />
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="curPage" value="${path}">
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                                <c:param name="${ showAttributes.placeTitle }" value="${ showAttributes.idValue }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${ empty searchEmployees.searchParams  }">
                    <c:choose>
                        <c:when test="${ i != 0 }">
                            <c:url var="curPage" value="${ path }">
                                <c:param name="page" value="${ i }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="curPage" value="${ path }" />
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${ i != 0 }">
                            <c:url var="curPage" value="${ path }">
                                <c:param name="page" value="${ i }" />
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                            </c:url>
                        </c:when>
                        <c:otherwise>
                            <c:url var="curPage" value="${path}">
                                <c:param name="searchParams" value="${ searchEmployees.searchParams }" />
                            </c:url>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:set var="pageNum" value="${ i + 1 }" />
    <c:set var="isNumTheSame" value="${i == paginationCurrentPage }" />
</body>
</html>