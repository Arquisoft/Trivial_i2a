/*MENU*/



// -- RUN JS  -- 
var base_html = '';
var is_mobile = false;
// -- VARIABLES DEL SCROLL 
var pageOffsets = [];// cache pages' document position
var pageOffsetsInverso = [];// cache pages' document inverse position
var navPages;

var arePluginsLoaded = false;
/* SCROLL HIDDEN WHEN SCROLL DOWN */
var didScroll;
var lastScrollTop = 0;
var delta = 5;

function loadFunctions(base){	
	base_html = base;
	// GENERAL
	openMenu();	
	loadFadeLoad();		
	loader();	

	
	// LOAD PLUGINS
	$( window ).load(function(){
		if(!arePluginsLoaded){
			loadPlugins();
			arePluginsLoaded = true;
		}
	})	
}
function openWork(){
	var work = document.getElementById('work');
	var scroll_pos = $(window).scrollTop();
	if(scroll_pos > 3){
		work.style.visibility("visible");
	}
}

function openMenu(){
	$("html, body").addClass("overflow");
	$("nav .menu-button .button").click(function(){
		actionMenu();
	});
	$("body").keydown(function(e) {
		if($("nav .active").hasClass("active")){
			if(e.keyCode == 27) { // esc
				actionMenu();
			}
		}
	});
}

// FunciÃ³n auxiliar que maneja las acciones del menÃº
function actionMenu(){
	$("nav .menu-button").parents("header").toggleClass("active");
	$("nav").toggleClass("active");
	$("html, body").toggleClass("overflow");
	updateLogoWhitePage();
}

function homeAnimations(){
	if($(window).width() <= 1023){
		is_mobile = true;
	}

	$(document).scroll(function(){
		updateNavHome();
	});

	adjustTopHeight();
	animateFace();
	playVideoButton();
	scrollDownButton();
	barsClick();
}

function updateNavHome(){
	var scroll_pos = $(window).scrollTop();
	if($(".home").size() != 0){
		if($(window).width() <= 400){
			if(scroll_pos > 10){
				$("nav").addClass("dark");
			}else{
				$("nav").removeClass("dark");
			}
		}else{
			var bars_pos = $(".home .bars").offset().top - 90;
			if(bars_pos - scroll_pos < 0){
				$("nav").addClass("dark");
			}else{
				$("nav").removeClass("dark");
			}
		}
	}
}

function adjustTopHeight(){
	if(is_mobile){
		$(".home .top").height($(window).height());
	}
}


function playVideoButton(){
	var video = $('video').get(0);
	if(video != null){		
		$(".video-container .playvideo p").click(			
			function(){				
				if($(".video-container").hasClass("playing")){
					$("nav").removeClass("dark");
					$(".video-container").removeClass("playing");
					unlockHomeScroll();
					var teaser = $('video').attr('data-teaser');
					$('video').attr('src', teaser);
					$('video').attr('loop');
					video.pause();
				}else if($(".video-container").hasClass("restart")){
					$(".video-container").removeClass("restart");
					if(video.paused)
						video.play();					
				} else{			
					$("nav").addClass("dark").addClass('is-playing');
					lockHomeScroll();
					var src = $('video source.mp4').attr('src');
					$(".video-container").addClass("playing");
					$('video').attr('src', src);
					$('video').attr('controls', true);
					$('video').removeAttr('loop');
					if(video.paused)
						video.play();
					video.onended = function(e){			
						$("nav").removeClass("dark").removeClass('is-playing');
						$(".video-container").removeClass("playing");
						unlockHomeScroll();
						var teaser = $('video').attr('data-teaser');
						$('video').attr('src', teaser);
					}
				}
			}
		);
		$(".video-pulguche-container .playvideo p").click(			
			function(){				
				var $video = $('video#pulguche');		
				var video = $video.get(0);
				$('nav').addClass('fixed-section');
				$(".video-pulguche-container").toggleClass('is-playing');
				if(video.paused){
					video.play();					
					video.onended = function(e){			
						$(".video-pulguche-container").removeClass('is-playing');
						video.pause();
						video.currentTime = 0;
					}
				} else{			
					video.pause();
					video.currentTime = 0;
					
				}
			}
		);
	}
}

function scrollDownButton(){
	$(".home .top .scrolldown").click(
		function(){
			$(".home").attr("id", "pos2");
			var video = document.getElementsByTagName('video')[0];
			video.pause();
		}
	);
}

function lockHomeScroll(){
	$("html").addClass("block");
	$(window).unbind('mousewheel');
}
function unlockHomeScroll(){
	$("html").removeClass("block");
	if(!is_mobile){
		setTimeout(
			function(){	
				$(window).bind('mousewheel', function(e) {
					if(e.originalEvent.wheelDelta / 120 > 0) {
						homeMoveUp();
					} else {
						homeMoveDown();
					}
				});
			}, 
			1000
		);
	}
}

function homeMoveDown(){
	if($('.home').size() > 0){
		lockHomeScroll();
		var position = $(".home").attr("id").replace("pos", "");

		/* CAMBIAR AQUÃ PARA LO DEL FOOTER */
		if(position < 2){
			position++;
		}

		$(".home").attr("id", "pos"+position);
		unlockHomeScroll();

		var video = document.getElementsByTagName('video')[0];
		video.pause();
	}
}
function homeMoveUp(){
	if($('.home').size() > 0){
		lockHomeScroll();
		var position = $(".home").attr("id").replace("pos", "");

		if(position > 1){
			position--;
		}

		$(".home").attr("id", "pos"+position);
		unlockHomeScroll();

		var video = document.getElementsByTagName('video')[0];
		if(video.paused)
			video.play();
	}
}

// REMOVING COOKIES WHEN CLICK AND PUT IN SESSION
function removeCookies(){
	$('.cookies .close').on('click', function(){
		$.ajax({
			url		:	"lib/ajax/remove-cookies.php", 
			type	: 	"POST",
			async 	: 	false,
			success	: 	function(data) {
							$('#wrapper-cookies').html(data);
						}
		});
	})
}
