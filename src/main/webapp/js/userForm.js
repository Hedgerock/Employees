const passwords = document.querySelectorAll(".user-form__button");

passwords.forEach(el => {
    const input = el.parentElement.querySelector('input');
    const child = el.querySelector("i");

    el.onclick = (e) => {
        e.preventDefault();
    }

    el.addEventListener("mouseup", () => {
        el.className = "user-form__button user-form__button_hidden"
        child.className = "fa-solid fa-eye-slash"
        input.type="password"
    })

    el.addEventListener("mousedown", () => {
        el.className = "user-form__button user-form__button_visible"
        child.className = "fa-solid fa-eye"
        input.type="text"
    })
})