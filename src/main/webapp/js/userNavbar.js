const userContainer = document.querySelector(".user");
const userButton = document.querySelector(".user__button");
const userList = document.querySelector(".authorized-user-list_invisible");


userList.remove();
userList.className= "authorized-user-list";

userList.onclick = (e) => {
    e.preventPropagation();
}

userButton.onclick = () => {
    if (userContainer.contains(userList)) {
        userList.remove();
    } else {
        userContainer.append(userList);
        initLayout();
    }
}


function initLayout() {
    const layout = document.createElement("div");
    layout.className = "layout layout_user";

    layout.onclick = () => {
        userList.remove();
        layout.remove();
    }

    document.body.appendChild(layout);
}