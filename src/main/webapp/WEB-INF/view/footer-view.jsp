<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <footer>
        <span>Made by hedgerock</span>
        <span>2025</span>
        <ul class="footer-list">
            <li class="footer-list__item" title="Github">
                <a href="https://github.com/Hedgerock?tab=repositories" target="_blank">
                    <i class="fa-brands fa-github"></i>
                </a>
            </li>
            <li class="footer-list__item" title="CodeWars">
                <a href="https://www.codewars.com/users/Hedgerock" target="_blank">
                    <i class="fa-solid fa-laptop-code"></i>
                </a>
            </li>
            <li class="footer-list__item" title="Linkedin" target="_blank">
                <a href="https://in/dmitro-vasyliev-215334236">
                    <i class="fa-brands fa-linkedin"></i>
                </a>
            </li>
        </ul>
    </footer>
    <script type="module" src="${pageContext.request.contextPath}/js/burger/burgerMain.js"></script>
</body>
</html>