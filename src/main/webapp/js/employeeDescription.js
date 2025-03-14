const employeeDescription = document.querySelector(".employee-details-list__item_description");
const content = employeeDescription.querySelector("span");
const height = employeeDescription.clientHeight;

employeeDescription.onclick = initTextArea;
const textArea = document.querySelector(".employee-details-list__item_textarea");
let prevTextAreContent;

function initTextArea() {
    const parent = employeeDescription.parentElement;
    const path = window.location.href.split("?")[1];
    const id = path.split("=")[1].replace(/\D/g, "");

    const textAreaBox = document.createElement("form");
    textAreaBox.action = "/saveDescription";

    textAreaBox.className = "text-area-box";

    const textArea = initCurTextArea();

    const buttonsBox = document.createElement("div");
    buttonsBox.className = "buttons-box";

    const confirmButton = initConfirmButton();
    const declineButton = initDeclineButton(textAreaBox, parent);

    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.name = "empId";
    input.value = id;

    buttonsBox.append(confirmButton);
    buttonsBox.append(declineButton);
    buttonsBox.append(input);

    textAreaBox.append(textArea);
    textAreaBox.append(buttonsBox);

    employeeDescription.remove();

    parent.append(textAreaBox);
}

function initCurTextArea() {
    const textArea = document.createElement("textarea");
    textArea.className = "employee-details-list__item employee-details-list__item_textarea";
    textArea.name = "employeeDescriptionAttribute";
    textArea.id = "employeeDescriptionAttribute";
    textArea.style.height = height + 10 + "px";

    const currentContent = content.textContent + " ";

    if (currentContent.trim().toLowerCase() !== "empty description") {
        textArea.value = currentContent;
    } else {
        textArea.placeholder = currentContent;
    }

    textArea.addEventListener("input", () => {
        textArea.style.height = 'auto';
        textArea.style.height = textArea.scrollHeight + "px";
    
        if (textArea.value.trim() === "") {
            textArea.style.height = height + "px";
        }
    
        prevTextAreContent = textArea.value;
    });
    
    textArea.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            textArea.style.height = 'auto';
            textArea.style.height = textArea.scrollHeight + "px";
        }

        if (e.key === "Tab") {
            e.preventDefault();
            textArea.value += "\t";
        }
    });

    return textArea;
}

function initConfirmButton() {
    const confirmButton = document.createElement("button");
    confirmButton.className = "text-area-box__button"
    confirmButton.textContent = "Confirm";
    confirmButton.setAttribute("type", "submit");

    return confirmButton;
}

function initDeclineButton(textAreaBox, parent) {
    const declineButton = document.createElement("button");
    declineButton.textContent = "Decline";
    declineButton.className = "text-area-box__button";

    declineButton.onclick = () => {
        textAreaBox.remove();
        parent.append(employeeDescription);
    }

    return declineButton
}