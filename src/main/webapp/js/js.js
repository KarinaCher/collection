$(document).ready(function () {
    $('.pop').on('click', function () {
        path = $(this).attr('src');
        currentIndex = $(this).attr('current-index');
        prevIndex = parseInt(currentIndex) - 1;
        nextIndex = parseInt(currentIndex) + 1;
        
        $('.imagepreview').css('background-image', 'url("../../image/lg/' + imagesList[parseInt(currentIndex)] + '")');
        
        if (currentIndex === '0') {
            $('#modal-body-prevImg').hide();
        }
        else {
            $('#modal-body-prevImg').show();
            $('#modal-body-prevImg').attr('src', '../../image/100/' + imagesList[prevIndex]);
            $('#modal-body-prevImg').attr('current-index', prevIndex);
        }
        if (imagesList[nextIndex] === '') {
            $('#modal-body-nextImg').hide();
        }
        else {
            $('#modal-body-nextImg').show();
            $('#modal-body-nextImg').attr('src', '../../image/100/' + imagesList[nextIndex]);
            $('#modal-body-nextImg').attr('current-index', nextIndex);
        }
        
        $('#imagemodal').modal('show');
    });

    $('.sender').on('mouseenter', function() {
        var classes = $(this).attr('class').split(' ');
        $('.sender').not('.' + classes[1]).fadeTo(0, 0.3);
    }).on('mouseleave', function() {
        $('.sender').fadeTo(0 , 1);
    });
});
