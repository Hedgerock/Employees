<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <c:set var="firstName" value="${ authorizedUser.userDetails.firstName }" />
    <c:set var="lastName" value="${ authorizedUser.userDetails.lastName}" />
    <c:set var="middleName" value="${ authorizedUser.userDetails.middleName}" />
    <c:set var="dateOfBirth" value="${ authorizedUser.userDetails.dateOfBirth}" />
    <c:set var="email" value="${ authorizedUser.userDetails.email}" />
    <c:set var="phoneNumber" value="${ authorizedUser.userDetails.phoneNumber}" />

    <div class="current-user">
        <div class="head">
            <div class="user-img">
                <i class="fa-solid fa-user"></i>
            </div>
            <div class="description-content">
                <span class="current-user__value">${ user }</span>
                <div class="roles">
                    <h3>${ userDetails.authorities.size() > 1 ? 'Roles:' : 'Role:' }</h3>
                    <c:forEach var="authority" items="${ userDetails.authorities }">
                        <span class="roles__value">${ authority }</span>
                    </c:forEach>
                </div>
            </div>
        </div>
        <ul class="user-details" data-title="${ user } details">
            <li class="user-details__item" data-title="Firstname">
                ${ empty firstName ? 'Not specified' : firstName }
            </li>
            <li class="user-details__item" data-title="Lastname">
                ${ empty firstName ? 'Not specified' : lastName }
            </li>
            <li class="user-details__item" data-title="Middlename">
                ${ empty firstName ? 'Not specified' : middleName }
            </li>
            <li class="user-details__item" data-title="Date of birth">
                ${ empty firstName ? 'Not specified' : dateOfBirth }
            </li>
            <li class="user-details__item" data-title="Email">
                ${ empty firstName ? 'Not specified' : email }
            </li>
            <li class="user-details__item" data-title="Phone number">
                ${ empty firstName ? 'Not specified' : phoneNumber }
            </li>
        </ul>
    </div>
</body>
</html>