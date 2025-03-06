<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="pagination">
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

        <a 
            href="${ paginationCurrentPage == 0 ? '#' : prev }"
            class="pagination__link ${ paginationCurrentPage == 0 ? 'pagination__link_disabled' : '' }"
        >
            <i class="fa-solid fa-chevron-left"></i>
        </a>
        
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

        <div class="content">
            <c:if test="${ isMoreThanZero }">
                <a 
                    href="${ firstPage }"
                    class="pagination__link">
                    1
                </a>
                <span class="dots">...</span>
            </c:if>

            <c:forEach 
                var="i" 
                begin="${ isMoreThanZero ? paginationCurrentPage - curParam : 0 }"
                end="${ isLessThanMax ? paginationCurrentPage + curParam : actualTotal }"
            >
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
                <c:choose>
                    <c:when test="${i == paginationCurrentPage}">
                        <span class="pagination__current-page">${i + 1}</span>
                    </c:when>
                    <c:otherwise>
                    <a href="${ curPage }" class="pagination__link">
                        ${ i + 1 }
                    </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

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

            <c:if test="${ isLessThanMax }">
                <span class="dots">...</span>
                <a href="${ lastPage }" class="pagination__link">
                    ${ total }
                </a>
            </c:if>
        </div>
        <a 
            href="${ paginationCurrentPage != actualTotal ? next : '#' }" 
            class="pagination__link ${ paginationCurrentPage == actualTotal ? 'pagination__link_disabled' : '' }">
            <i class="fa-solid fa-chevron-right"></i>
        </a>
    </div>
</body>
</html>