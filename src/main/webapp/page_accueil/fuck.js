window.onload = function() {
    CanvasJS.addColorSet("greenShades", [ //colorSet Array

        "#3ed0d0",
        "#9f37d5",
        "#c4d937",
        "#ef3657",
        "#90EE90"
    ]);
    CanvasJS.addCultureInfo("fr", {
        decimalSeparator: ",", // Observe ToolTip Number Format
        digitGroupSeparator: ".", // Observe axisY labels
        months: [
            "janvier",
            "février",
            "mars",
            "avril",
            "mai",
            "juin",
            "juillet",
            "août",
            "septembre",
            "octobre",
            "novembre",
            "décembre",
        ],
        days: [
            "lundi",
            "mardi",
            "mercredi",
            "jeudi",
            "vendredi",
            "samedi",
            "dimanche",
        ],
    });

    var chart = new CanvasJS.Chart("chartContainer", {
        colorSet: "greenShades",
        animationEnabled: true,
        animationDuration: 4000,
        culture: "fr",
        backgroundColor: "rgba(0, 0, 0, 0)",
        theme: "dark2",
        title: {
            text: "Nombre De Telechargement Des Documents",
            fontColor: "#FFFFFF"
        },
        axisX: {
            valueFormatString: "MMMM",
            interval: 1,
            intervalType: "month",
            titleFontColor: "#FFFFFF"
        },
        axisY: {
            title: "nombre de telechargement",
            titleFontColor: "#FFFFFF"
        },
        legend: {
            cursor: "pointer",
            fontSize: 16,
            itemclick: toggleDataSeries,
        },
        toolTip: {
            shared: true,
        },
        data: [{
                name: "ATS",
                type: "spline",
                yValueFormatString: "#0.## Telechargement",

                showInLegend: true,
                dataPoints: [

                ],
            },
            {
                name: "BP",
                type: "spline",
                yValueFormatString: "#0.## Telechargement",
                xValueFormatString: "MMMM",
                showInLegend: true,
                dataPoints: [

                ],
            },
            {
                name: "RE",
                type: "spline",
                yValueFormatString: "#0.## Telechargement",
                xValueFormatString: "MMMM",
                showInLegend: true,
                dataPoints: [
                    { x: new Date(2021, 0, 1), y: 10 },
                    { x: new Date(2021, 1, 1), y: 12 },
                    { x: new Date(2021, 2, 1), y: 6 },
                    { x: new Date(2021, 3, 1), y: 2 },
                    { x: new Date(2021, 4, 1), y: 5 },
                    { x: new Date(2021, 5, 1), y: 10 },
                    { x: new Date(2021, 6, 1), y: 2 },
                    { x: new Date(2021, 7, 1), y: 5 },
                    { x: new Date(2021, 8, 1), y: 6 },
                    { x: new Date(2021, 9, 1), y: 5 },
                    { x: new Date(2021, 10, 1), y: 2 },
                    { x: new Date(2021, 11, 1), y: 3 },
                ],
            },
            {
                name: "ATS CNAS",
                type: "spline",
                yValueFormatString: "#0.## Telechargement",
                xValueFormatString: "MMMM",
                showInLegend: true,
                dataPoints: [
                    { x: new Date(2021, 0, 1), y: 4 },
                    { x: new Date(2021, 1, 1), y: 5 },
                    { x: new Date(2021, 2, 1), y: 3 },
                    { x: new Date(2021, 3, 1), y: 1 },
                    { x: new Date(2021, 4, 1), y: 5 },
                    { x: new Date(2021, 5, 1), y: 1 },
                    { x: new Date(2021, 6, 1), y: 4 },
                    { x: new Date(2021, 7, 1), y: 2 },
                    { x: new Date(2021, 8, 1), y: 6 },
                    { x: new Date(2021, 9, 1), y: 2 },
                    { x: new Date(2021, 10, 1), y: 10 },
                    { x: new Date(2021, 11, 1), y: 12 },
                ],
            },
        ],
    });

    var chart1 = new CanvasJS.Chart("chart", {
        theme: "dark1", // "light1", "light2", "dark1", "dark2"
        culture: "fr",
        backgroundColor: "rgba(0, 0, 0, 0)",
        animationEnabled: true,
        title: {
            text: "Pourcentage télechargement documents "
        },
        data: [{
            type: "pie",
            startAngle: 25,
            toolTipContent: "<b>{label}</b>: {y}%",
            showInLegend: "true",
            legendText: "{label}",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}%",
            dataPoints: [
                { y: 51.08, label: "AT" },
                { y: 27.34, label: "BP" },
                { y: 10.62, label: "RE" },
                { y: 5.02, label: "ATS" },

            ]
        }]
    });

    chart.render();
    chart1.render();


    function toggleDataSeries(e) {
        if (
            typeof e.dataSeries.visible === "undefined" ||
            e.dataSeries.visible
        ) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        chart.render();
    }
};