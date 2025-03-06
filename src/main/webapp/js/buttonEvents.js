import { skeleton } from "./declaration.js";
import { validateLengthFunc } from "./functions.js";
import { field, layout } from "./initializing.js";
import { burgerContainer } from "./initializing.js";


export const burgerButtonClickHandler = () => {
    if (document.querySelector(".burger-menu")) {
        layout.remove();
        burgerContainer.remove();
    } else {
        skeleton.prepend(burgerContainer);
        document.body.appendChild(layout);
    }
}

export const closeButtonClickHandler = () => {
    layout.remove();
    burgerContainer.remove();
}

export function getNewField(e, button, parent, title, type) {
    e.preventDefault();
    const currentLabels = parent.querySelectorAll(".current-employee-form-label").length;

    if (currentLabels >= 6) {
        validateLengthFunc(currentLabels);
        return;
    }

    parent.appendChild(field(title, parent, button, type));

    validateLengthFunc(currentLabels + 1, button);
}

export function removeHandler(parent, field, buttonArg) {
    const currentLabels = field.querySelectorAll(".current-employee-form-label").length;
    parent.remove();
    validateLengthFunc(currentLabels - 1, buttonArg);
}