import { MAXIMUM_FIELDS } from "./constants.js";
import { header} from "./declaration.js";
import { burgerButton } from "./initializing.js";

export function createElemenFunction ({ tag, className, link = "#", attributes = { title: '#', value: '#' }, textContent }) {
    const element = document.createElement(tag);
    element.className = className;

    if (link !== "#") {
        element.href = link;
    }

    if (attributes.title !== "#" && attributes.value !== "#") {
        element.setAttribute(attributes.title, attributes.value);
    }

    if (textContent) {
        element.textContent = textContent;
    }

    return element;
}

export function initButton (iconValue, className, onClick) {

    const currentButton = createElemenFunction({
        tag: "button",
        className: className
    })

    const icon = createElemenFunction({
        tag: "i",
        className: iconValue
    })

    currentButton.onclick = onClick;
    currentButton.append(icon);

    return currentButton;
}

export function initBurger() {
    header.prepend(burgerButton);
}

export function validateLengthFunc(value, button) {
    if (value >= MAXIMUM_FIELDS) {
        button.setAttribute("disabled", true);
    } else {
        button.removeAttribute("disabled");
    }
}