$(document).ready(function () {
    $('.pop').on('click', function () {
        path = $(this).attr('src');
        $('.imagepreview').attr('src', path.replace('/thumb/', '/lg/'));
        $('#imagemodal').modal('show');
    });
});