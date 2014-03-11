window.onload = function () {
	onLoadDoc();
}

var chart1;
	
	
function onLoadDoc() {
	chart1 = new cfx.Chart();

	var items1 = [
                 { "Transactions": 18,"Sales":20000},
                 { "Transactions": 15, "Sales": 18000 },
                 { "Transactions": 21, "Sales": 22000 },
                 { "Transactions": 18, "Sales": 21000 },
                 { "Transactions": 56, "Sales": 56000 },
                 { "Transactions": 36, "Sales": 39000 },
                 { "Transactions": 41, "Sales": 39000 },
                 { "Transactions": 22, "Sales": 23000 },
                 { "Transactions": 51, "Sales": 57000 },
                 { "Transactions": 47, "Sales": 42000 },
                 { "Transactions": 26, "Sales": 22000 },
                 { "Transactions": 18, "Sales": 21000 }
                ];

	 chart1.getAxisY().setMax(80);
	 chart1.getAxisY().setMin(0);
	 var data1 = chart1.getData();
	 data1.setSeries(2);
	 var series11 = chart1.getSeries().getItem(0);
	 var series21 = chart1.getSeries().getItem(1);

	 series11.setGallery(cfx.Gallery.Bar);
	 series11.setFillMode(cfx.FillMode.Pattern);
	 series11.setVolume(100);

	 series21.setGallery(cfx.Gallery.Lines);
	 series21.setMarkerShape(cfx.MarkerShape.None);
	 series21.getLine().setWidth(4);

	 var axis21 = chart1.getAxisY2();
	 axis21.getGrids().getMajor().setVisible(false);
	 series21.setAxisY(axis21);
	 

	 chart1.setDataSource(items1);
	
	var chartDiv2 = document.getElementById('ChartDiv2');
	chart1.create(chartDiv2);
}
