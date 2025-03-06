const closeBtn = document.querySelector(".slider__close-button");
const slider = document.querySelector(".slider");
const img = document.querySelector(".image-layout");
const main = document.querySelector("main");

const sliderImages = document.querySelectorAll(".slider-image");
const microImages = document.querySelectorAll(".micro-image");
const prevButton = document.querySelector(".slider-image__button_prev");
const nextButton = document.querySelector(".slider-image__button_next");

let slide = 0;
slider.remove();

slider.className = "slider";

closeBtn.onclick = closeValue;



img.onclick = () => {
    main.appendChild(slider);
}


function setDefaultSlider() {
    sliderImages.forEach((el, index) => {
        if (index === 0) {
            el.className = "slider-image slider-image_visible";
            microImages[index].className = "micro-image micro-image_selected";
        } else {
            el.className = "slider-image";
            microImages[index].className = "micro-image";
        }
    })
}

addEventListener("keydown", (e) => {
    switchCase(e);
});

microImages.forEach((image, index) => {
    image.onclick = () => {
        microImages.forEach((el, ind) => {
            if (ind !== index && el.className.includes("micro-image_selected")) {
                el.className = "micro-image";
            }
        })

        image.className = "micro-image micro-image_selected";
        
    
        sliderImages.forEach((img, ind) => {
            if (ind !== index) {
                img.className = "slider-image";
            } else {
                img.className = "slider-image slider-image_visible";
                slide = ind;
            }
        })
    }
})

nextButton.onclick = nextValue;
prevButton.onclick = prevValue;


function nextValue() {
    const beforeIncrIndex = slide % sliderImages.length;
    sliderImages[beforeIncrIndex].className = "slider-image";
    microImages[beforeIncrIndex].className = "micro-image";

    slide = slide + 1 > sliderImages.length - 1 ? 0 : slide + 1;

    const afterIncrIndex = slide % sliderImages.length;
    sliderImages[afterIncrIndex].className = "slider-image slider-image_visible";
    microImages[afterIncrIndex].className = "micro-image micro-image_selected";
}

function prevValue() {
    const beforeDecrIndex = ((slide % sliderImages.length) + sliderImages.length) % sliderImages.length;
    sliderImages[beforeDecrIndex].className = "slider-image";
    microImages[beforeDecrIndex].className = "micro-image";

    slide = slide - 1 < 0 ? sliderImages.length - 1 : slide - 1;

    const afterDecrIndex = ((slide % sliderImages.length) + sliderImages.length) % sliderImages.length;
    sliderImages[afterDecrIndex].className = "slider-image slider-image_visible";
    microImages[afterDecrIndex].className = "micro-image micro-image_selected";
}

function closeValue() {
    slide = 0;
    setDefaultSlider();
    slider.remove();
}

function switchCase(e) {

    switch(e.code) {
        case "Escape":
            return closeValue();
        case "ArrowLeft":
            return prevValue();
        case "ArrowRight":
            return nextValue();
    }

}