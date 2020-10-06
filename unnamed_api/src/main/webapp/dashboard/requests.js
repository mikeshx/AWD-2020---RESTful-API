$(document).ready(function () {

    const base_url = "http://localhost:8080/unnamed_api-1.0-SNAPSHOT/rest/";

    $('#ajaxBtn').click(function () {
        $.ajax({
            url: "http://localhost:8080/unnamed_api-1.0-SNAPSHOT/rest/canali/count",
            type: "GET",
            success: function (result) {
                $("#button-1-data").append(JSON.stringify(result))
            },
            error: function (error) {
                console.log('error')
            }
        })
    })
})