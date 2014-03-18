$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8085/mdb/project/coverage/worse"
    }).then(function(data) {

        var chart1;
            $("div", "WorsePieDiv").chart({
                gallery: cfx.Gallery.Bar,
                view3D: {
                    enabled: true,
                    rotated: true,
                    angleX: 30,
                    angleY: -20,
                    boxThickness: 10,
                    depth: 160,
                    shadow: cfx.Shadow.fixed
                },
                dataValues:data,
                titles: [{
                    text: "Configuring 3D charts"
                }]
            });
        });
});