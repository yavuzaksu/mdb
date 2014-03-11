window.onload = function () {
	onLoadDoc();
}

var chart2;
	
	
function onLoadDoc() {
	chart2 = new cfx.Chart();

    chart2.getLegendBox().setVisible(false);
    var axis1 = chart2.getAxisY();
    var axis2 = chart2.getAxisY2();
    var axis3 = new cfx.AxisY();
    var axisX2 = new cfx.AxisX();
    chart2.getAxesY().add(axis3);
    chart2.getAxesX().add(axisX2);

    axis1.setTextColor("#1D2793");
    axis1.setForceZero(false);
    axis1.getTitle().setText("Bike Sales");
    axis1.getLabelsFormat().setFormat(cfx.AxisFormat.Currency);

    axis2.setTextColor("#9F1000");
    axis2.setForceZero(false);
    axis2.setVisible(true);
    axis2.setSeparation(15);
    axis2.getTitle().setText("Part Sales");
    axis2.getLabelsFormat().setFormat(cfx.AxisFormat.Currency);
    axis2.getGrids().getMajor().setVisible(false);
    axis3.setTextColor("#14650C");
    axis3.setVisible(true);
    axis3.setForceZero(false);
    axis3.setPosition(cfx.AxisPosition.Far);
    axis3.getTitle().setText("Clothing Sales");
    axis3.getLabelsFormat().setFormat(cfx.AxisFormat.Currency);
    axis3.getGrids().getMajor().setVisible(false);

    axisX2.setVisible(true);
    axisX2.setMin(0);
    axisX2.setMax(12);
    axisX2.setStep(3);
    axisX2.getLabels().setItem(3, "Q1");
    axisX2.getLabels().setItem(6, "Q2");
    axisX2.getLabels().setItem(9, "Q3");
    axisX2.getLabels().setItem(12, "Q4");
    axisX2.getGrids().setInterlaced(false);
    axisX2.getGrids().getMajor().setVisible(false);
    axisX2.setPosition(cfx.AxisPosition.Near);
    axisX2.setStyle(cfx.AxisStyles.Centered);

    var series1 = chart2.getSeries().getItem(0);
    var series2 = chart2.getSeries().getItem(1);
    var series3 = chart2.getSeries().getItem(2);

    series2.setAxisY(axis2);
    series3.setAxisY(axis3);

    series1.setColor("#1D2793");
    series2.setColor("#9F1000");
    series3.setColor("#14650C");

    chart2.getAllSeries().setMarkerShape(cfx.MarkerShape.None);
    chart2.getAllSeries().getLine().setWidth("4");

    var items = [{
        "Month": "Jan",
            "Bikes": 88123,
            "Parts": 18000,
            "Clothing": 1300
    }, {
        "Month": "Feb",
            "Bikes": 86040,
            "Parts": 17600,
            "Clothing": 900
    }, {
        "Month": "Mar",
            "Bikes": 85123,
            "Parts": 17400,
            "Clothing": 970
    }, {
        "Month": "Apr",
            "Bikes": 84200,
            "Parts": 17500,
            "Clothing": 1010
    }, {
        "Month": "May",
            "Bikes": 86600,
            "Parts": 18100,
            "Clothing": 1070
    }, {
        "Month": "Jun",
            "Bikes": 87900,
            "Parts": 19200,
            "Clothing": 1180
    }, {
        "Month": "Jul",
            "Bikes": 85000,
            "Parts": 19100,
            "Clothing": 1240
    }, {
        "Month": "Aug",
            "Bikes": 83254,
            "Parts": 18300,
            "Clothing": 1020
    }, {
        "Month": "Sep",
            "Bikes": 82132,
            "Parts": 17900,
            "Clothing": 930
    }, {
        "Month": "Oct",
            "Bikes": 85130,
            "Parts": 18570,
            "Clothing": 860
    }, {
        "Month": "Nov",
            "Bikes": 89124,
            "Parts": 19760,
            "Clothing": 990
    }, {
        "Month": "Dec",
            "Bikes": 93180,
            "Parts": 20400,
            "Clothing": 1120
    }];
    chart2.setDataSource(items);
	
	var chartDiv = document.getElementById('ChartDiv1');
	chart2.create(chartDiv);
}
