gsap.registerPlugin(ScrollTrigger);
gsap.timeline({
        scrollTrigger: {
            trigger: "section.ats",
            start: "top 50%",
            end: "bottom top",
            toggleActions: "restart none none reset"
        }
    })
    .from(".box1", {
        x: innerWidth * 1
    })

gsap.timeline({
        scrollTrigger: {
            trigger: "section.bulletin",
            start: "top 50%",
            end: "bottom top",
            toggleActions: "restart none none reset"
        }
    })
    .from(".box2", {
        x: innerWidth * -1
    })
gsap.timeline({
        scrollTrigger: {
            trigger: "section.releve",
            start: "top 50%",
            end: "bottom top",
            toggleActions: "restart none none reset"
        }
    })
    .from(".box3", {
        x: innerWidth * 1
    })
gsap.timeline({
        scrollTrigger: {
            trigger: "section.cnas",
            start: "top 50%",
            end: "bottom top",
            toggleActions: "restart none none reset"
        }
    })
    .from(".box4", {
        x: innerWidth * -1
    })
    .from(".box5", {
        x: innerWidth * 1
    })

// const scroll = new SmoothScroll('.navbar a[href*="#"]', {
//     speed: 500
// });
let Scrollbar = window.ScroLLbar;
Scrollbar.init(document.querySelector('body'));