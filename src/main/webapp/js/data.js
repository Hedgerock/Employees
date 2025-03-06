export const burgerMenuData = [
    { 
        id: 1,
        title: "Main page",
        link: "/"
    },
    {
        id: 2,
        title: "Departments",
        link: "/departments"
    },
    {
        id: 3,
        title: "Cities",
        link: "/cities"
    },
    {
        id: 4,
        title: "Nationalities",
        link: "/nationalities"
    },
    {
        id: 5,
        title: "Histories",
        link: "#",
        children: [
            {
                id: 1,
                title: "Employees history",
                link: "/historyList"
            },
            {
                id: 2,
                title: "Departments history",
                link: "/departmentHistory"
            },
            {
                id: 3,
                title: "Cities history",
                link: "/cityHistory"
            },
            {
                id: 4,
                title: "Nationalities history",
                link: "/nationalityHistory"
            }
        ]
    },
    {
        id: 6,
        title: "Fired employees",
        link: "/firedEmployees"
    },
    {
        id: 7,
        title: "Settings",
        link: "/settings"
    }
]