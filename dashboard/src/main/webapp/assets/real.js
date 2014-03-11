var chart3;
chart3 = new cfx.Chart();

chart3.getData().setSeries(1);
chart3.getData().setPoints(181);

chart3.getAxisY().getLabelsFormat().setDecimals(1);
chart3.getAxisY().setMin(2.05);
chart3.getAxisY().setMax(2.45);

chart3.getAxisX().getGrids().getMajor().setTickMark(cfx.TickMark.None);
chart3.getAxisX().getGrids().getMinor().setTickMark(cfx.TickMark.None);

chart3.getAxisX().getGrids().getMajor().setVisible(true);


var realTime = chart3.getRealTime();
realTime.getLoopMarker().setColor("#00E402");
realTime.setBufferSize(181);
realTime.setMode(cfx.RealTimeMode.Loop);

chart3.setGallery(cfx.Gallery.Lines);

chart3.getAllSeries().setMarkerShape(cfx.MarkerShape.None);
chart3.getLegendBox().setVisible(false);

offset = 2.15;
nTick = 0;
forceR = false;

var myVar = setInterval(function () {
    setNewPoint();
}, 100);

function setNewPoint() {

    // This function simulates the behavior of a heartbeat
    var dPotential = 0;
    var randomDouble = Math.random();

    nTick += 25;

    //Generate p
    if (nTick < 200) dPotential = Math.min((randomDouble / 75) + offset, 2.17);
    if (nTick >= 200 && nTick < 400) dPotential = Math.max(offset - (randomDouble / 75), 2.15);

    //Generate p-q
    if (nTick >= 400 && nTick < 800) {
        var dRandom = (randomDouble / 50);
        dPotential = dRandom + 2.14;
    }

    //generate q
    if (nTick >= 800 && nTick < 850) {
        dPotential = Math.min((randomDouble / 75) + offset, 2.151);
        forceR = true;
    }

    //generate r
    if ((nTick >= 850 && nTick < 900) || forceR) {
        dPotential = Math.max((randomDouble / 75) + offset, 2.397);
        forceR = false;
    }

    //generate r-s
    if (nTick >= 900 && nTick < 950) dPotential = Math.min(offset - (randomDouble / 75), 2.12);

    //generate t
    if (nTick >= 950 && nTick < 1050) dPotential = Math.min((randomDouble / 75) + offset, 2.18);
    if (nTick >= 1050 && nTick < 1200) dPotential = Math.max(offset - (randomDouble / 75), 2.15);

    //generate t-end
    if (nTick >= 1200 && nTick <= 1500) {
        var dRandom = (randomDouble / 50);
        dPotential = dRandom + 2.14;
    }

    offset = dPotential;
    if (nTick >= 1500) nTick = 0;

    chart3.getRealTime().beginAddData(1, cfx.RealTimeAction.Append);
    chart3.getData().setItem(0, 0, dPotential);
    chart3.getRealTime().endAddData(true, false);
}

var chart
 = document.getElementById('ChartDiv2');
chart3.create(chartDiv);
