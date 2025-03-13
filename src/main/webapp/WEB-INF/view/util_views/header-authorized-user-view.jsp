<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="user">
        <button class="user__button">
            <i class="fa-solid fa-user"></i>
        </button>

        <c:url var="profile" value="/profile" />
        <c:choose>
            <c:when test="${ mode }">
                <c:url var="themeMode" value="/lightMode" />
            </c:when>
            <c:otherwise>
                <c:url var="themeMode" value="/darkMode" />
            </c:otherwise>
        </c:choose>

        <ul class="authorized-user-list authorized-user-list_invisible">
            <li class="authorized-user-list__item">
                <a 
                    class="list-content"
                    href="${ profile }" 
                    title="${ user } profile"
                >
                    <span>
                        <i class="fa-solid fa-user"></i>
                    </span>
                    <span>${ user }</span>
                </a>
            </li>
            <li class="authorized-user-list__item">
                <a 
                    class="list-content"
                    href="${ themeMode }" 
                    title="${ user } theme"
                >
                    <span>
                        <c:choose>
                            <c:when test="${ mode }">
                                <i class="fa-solid fa-moon"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-sun"></i>
                            </c:otherwise>
                        </c:choose>
                    </span>
                    <span>
                        ${ mode ? 'Dark mode' : 'Light mode' }
                    </span>
                    <button>
                        <c:choose>
                            <c:when test="${ mode }">
                                <i class="fa-solid fa-toggle-on"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-toggle-off"></i>
                            </c:otherwise>
                        </c:choose>
                    </button>
                </a>
            </li>
            <li class="authorized-user-list__item">
                <a 
                    class="list-content"
                    href="/settings" 
                    title="${ user } account settings"
                >
                    <span>
                        <i class="fa-solid fa-gear"></i>
                    </span>
                    <span>
                        Settings
                    </span>
                </a>
            </li>
            <li class="authorized-user-list__item">
                <a 
                    class="list-content"
                    href="/logout" 
                    title="Logout from ${ user } account"
                >
                    <span>
                        <i class="fa-solid fa-arrow-right-from-bracket"></i>
                    </span>
                    <span>Logout</span>
                </a>
            </li>
        </ul>
    </div>

    <script src="${ pageContext.request.contextPath }/js/userNavbar.js">

    </script>
</body>
</html>