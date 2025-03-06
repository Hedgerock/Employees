import { createElemenFunction, initButton } from "./functions.js";
import { burgerButtonClickHandler, closeButtonClickHandler, removeHandler } from "./buttonEvents.js";
import { burgerMenuData } from "./data.js";

export const burgerButton = document.querySelector(".burger-button");
export const closeButton = initButton("fa-solid fa-xmark", "burger-menu__close-button", closeButtonClickHandler);

export const layout = createElemenFunction({
    tag: "div", 
    className: "layout"
})

layout.onclick = burgerButtonClickHandler;
burgerButton.onclick = burgerButtonClickHandler;

export const burgerContainer = createElemenFunction({
    tag: "div",
    className: "burger-menu"
})

export const burgerContainerHeader = createElemenFunction({
    tag: "div",
    className: "burger-menu__header"
})

burgerContainer.append(burgerContainerHeader);
burgerContainerHeader.append(closeButton);

burgerMenuData.forEach(el => {
    const link = createElemenFunction( { 
        tag: el.children ? "div" : "a", 
        className: el.children ? "burger-menu-element burger-menu-element_with-children" : "burger-menu-element", 
        attributes: { title: "id", value: el.id } 
        
    })

    if (!el.children) {
        link.href = el.link;
    }

    const title = createElemenFunction({
        tag: "h3",
        className: "burger-menu-element__title",
        textContent: el.title
    })

    link.append(title);

    if (el.children) {
        declareChildContainer(el.children, link);
    }

    burgerContainer.append(link);
})

export function declareChildContainer(children, link) {
    const childContainer = createElemenFunction({ tag: "ul", className: "burger-menu-element-children-list" })

    children.forEach(child => {
        const newChild = createElemenFunction({ 
            tag: "li", 
            className: "burger-menu-element-children-list-element",
            attributes: { title: "id", value: child.id } 
        });
        const newChildLink = createElemenFunction({ 
            tag:"a", 
            className: "burger-menu-element-children-list-element__link",
            link: child.link,
            textContent: child.title
        });

        newChild.append(newChildLink);
        childContainer.append(newChild);
    })

    const button = initButton("fa-solid fa-chevron-up", "burger-menu-element__button", () => {
        childHandler(link, childContainer, button)
    });
    
    link.append(button);
    link.append(childContainer);
}

export function childHandler(link, childContainer, button) {
    if (link.contains(childContainer)) {
        childContainer.remove();
        button.innerHTML = `<i class="fa-solid fa-chevron-down"></i>`
    } else {
        button.innerHTML = `<i class="fa-solid fa-chevron-up"></i>`
        link.append(childContainer);
    }
}

export function field(title, field, buttonArg, type) {
    const label = createElemenFunction({
        tag: "label",
        className: `current-employee-form-label`
    })

    const h3 = createElemenFunction({
        tag: "h3",
        className: "current-employee-form-label__title",
        textContent:  `Enter current Employee ${ title }:`
    })

    const input = createElemenFunction({
        tag: "input",
        className: "current-employee-form-label__input current-employee-form-label__input_typing-option",
        attributes: { title: "id", value: `${ title }sValue${ field.children.length }` }
    })

    const button = initButton("fa-solid fa-trash", "current-employee-form-label__delete-button", () => {
        const parent = button.parentElement;
        removeHandler(parent, field, buttonArg);
    });

    button.setAttribute("title", "Remove current field");

    input.setAttribute("name", `${ title }sValue`);
    input.setAttribute("type", type);

    label.appendChild(h3);
    label.appendChild(input);
    label.appendChild(button);

    return label;
}