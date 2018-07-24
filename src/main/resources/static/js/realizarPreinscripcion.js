$(function () {
    // Animations initialization
    new WOW().init();
    if($('#idioma').find('.select-options ul li').hasClass('selected')){
        $('#idioma .inputSelect').text($('#idioma .select-options li.selected > span:first-child').text());
        $('#idioma input').val($('#idioma .select-options li.selected > span:first-child').text());
    } else {
    	$('#idioma .inputSelect').text($('#idioma .select-options li:first-child > span:first-child').text());
    	$('#idioma input').val($('#idioma .select-options li:first-child > span:first-child').text());
    }
    $('#idioma').click(function(){
        $(this).find('.select-options').toggleClass('visible');
    });
    $('#idioma .select-options li').click(function(){
        $('#idioma .selected').removeClass('selected');
        $(this).addClass('selected');
        $('#idioma .inputSelect').text($(this).find('span:first-child').text());
        $('#idioma input').val($(this).find('span:first-child').text());
    });
    
    if($('#tipo').find('.select-options ul li').hasClass('selected')){
    	$('#tipo .inputSelect').text($('#tipo .select-options li.selected > span:first-child').text());
    	$('#tipo input').val($('#tipo .select-options li.selected > span:first-child').text());
    } else {
    	$('#tipo .inputSelect').text($('#tipo .select-options li:first-child > span:first-child').text());
    	$('#tipo input').val($('#tipo .select-options li:first-child > span:first-child').text());
    }
    $('#tipo').click(function(){
        $(this).find('.select-options').toggleClass('visible');
    });
    $('#tipo .select-options li').click(function(){
        $('#tipo .selected').removeClass('selected');
        $(this).addClass('selected');
        $('#tipo .inputSelect').text($(this).find('span:first-child').text());
        $('#tipo input').val($(this).find('span:first-child').text());
    });
    
    if($('#jornada').find('.select-options ul li').hasClass('selected')){
    	$('#jornada .inputSelect').text($('#jornada .select-options li.selected > span:first-child').text());
    	$('#jornada input').val($('#jornada .select-options li.selected > span:first-child').text());
    } else {
    	$('#jornada .inputSelect').text($('#jornada .select-options li:first-child > span:first-child').text());
    	$('#jornada input').val($('#jornada .select-options li:first-child > span:first-child').text());
    }
    $('#jornada').click(function(){
        $(this).find('.select-options').toggleClass('visible');
    });
    $('#jornada .select-options li').click(function(){
        $('#jornada .selected').removeClass('selected');
        $(this).addClass('selected');
        $('#jornada .inputSelect').text($(this).find('span:first-child').text());
        $('#jornada input').val($(this).find('span:first-child').text());
    });
})