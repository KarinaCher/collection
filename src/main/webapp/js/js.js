$(document).ready(function () {
    $('.pop').on('click', function () {
        path = $(this).find('img').attr('src');
        $('.imagepreview').attr('src', path.replace('/sm/', '/lg/'));
        id = $(this).find('img').attr('id');
        $("#modalDateSent").html($("#postcard" + id).attr('postcardDateSend'));
        $("#modalDateReceived").html($("#postcard" + id).attr('postcardDateReceived'));
        $("#modalCountryCity").html($("#postcard" + id).attr('postcardCountryCity'));
        $("#modalSender").html($("#postcard" + id).attr('postcardSender'));
        $("#modalSize").html($("#postcard" + id).attr('postcardSize'));
        $("#modalDescription").html($("#postcardDescription" + id).html());
        $("#modalDirectLink").attr("href", "/postcard/" + id);
        $('#imagemodal').modal('show');
    });
});