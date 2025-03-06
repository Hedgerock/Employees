import { getNewField, removeHandler } from "../buttonEvents.js";
import { emailAddButton, emailDelButtons, emailFields, emailLabels, phoneAddButton, phoneDelButtons, phoneFields, phoneLabels } from "./emailDeclaration.js";
import { validateLengthFunc } from "../functions.js";

function setRemoveHandler(buttons, field, buttonArg) {
    buttons.forEach(button => {
        button.onclick = function() {
            const parent = this.parentElement;
            removeHandler(parent, field, buttonArg);
        };
    })
}

function setAddHandler(button, parent, title, type) {
    button.onclick = (e) => {
        getNewField(e, button, parent, title, type)
    };
}

setAddHandler(emailAddButton, emailFields, "email", "email");
setAddHandler(phoneAddButton, phoneFields, "phone", "text");

setRemoveHandler(emailDelButtons, emailFields, emailAddButton);
setRemoveHandler(phoneDelButtons, phoneFields, phoneAddButton);

validateLengthFunc(emailLabels.length, emailAddButton);
validateLengthFunc(phoneLabels.length, phoneAddButton);