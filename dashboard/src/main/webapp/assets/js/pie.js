$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/mdb/rs/project/coverage/worse"
    }).then(function(data) {

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