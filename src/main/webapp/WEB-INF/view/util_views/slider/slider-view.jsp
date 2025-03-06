<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="slider slider_invisible">
        <div class="slider-header">
            <button class="slider__close-button">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <button class="slider-image__button slider-image__button_prev">
            <i class="fa-solid fa-chevron-left"></i>
        </button>

        <c:forEach var="image" items="${ slider }" varStatus = "status">
            <div 
                class="slider-image ${ status.index == 0 ? 'slider-image_visible' : '' }" 
                data-index ="${ status.index }"
            >
                <img
                    class="slider-image__src"
                    src="${pageContext.request.contextPath}/tmp/${ image }" 
                    alt=""
                >
            </div>
        </c:forEach>

        <button class="slider-image__button slider-image__button_next">
            <i class="fa-solid fa-chevron-right"></i>
        </button>
        <div class="micro-slider">
            <c:forEach var="image" items="${ slider }" varStatus = "status">
                <div class="micro-image ${ status.index == 0 ? 'micro-image_selected' : '' }">
                    <img
                        class="micro-image__src"
                        src="${pageContext.request.contextPath}/tmp/${ image }" 
                        alt=""
                    >
                </div>
            </c:forEach>
        </div>
        <div class="slider-footer"></div>
    </div>
    <script src="${pageContext.request.contextPath}/js/slider.js"></script>
</body>
</html>