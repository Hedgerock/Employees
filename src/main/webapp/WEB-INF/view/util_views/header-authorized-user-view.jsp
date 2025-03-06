<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="user">
        <button class="user__button">
            <i class="fa-solid fa-user"></i>
        </button>

        <c:url var="profile" value="/profile">
            <c:param name="user" value="${ user }" />
        </c:url>

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
                <div
                    class="list-content"
                >
                    <span>
                        <i class="fa-solid fa-sun"></i>
                    </span>
                    <span>
                        Light mode
                    </span>
                    <button>
                        <i class="fa-solid fa-toggle-off"></i>
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