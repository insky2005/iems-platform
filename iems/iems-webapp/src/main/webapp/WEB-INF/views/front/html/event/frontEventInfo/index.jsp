<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />

<title>${systemName } | EventInfo - Index</title>

<link href="${ctx }/assets/libs/GMU/dist/gmu.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/assets/libs/GMU/dist/reset.css" rel="stylesheet" type="text/css" />

<link href="${ctx }/css/iEvent-front.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="slider">
    <div>
        <a href="#"><img lazyload="${ctx }/images/bg.jpg"></a>
        <p></p>
    </div>
</div>

<div id="button" style="width: 100%; text-align: center;">
	<div class="button-container" style="margin: 0 auto;">
    <div class="button-items" style="display: inline-block; text-align:left;">
      <a class="button ui-btn ui-btn-text-only" href="#"><span class="ui-btn-text">会议签到</span></a>
      <a class="button ui-btn ui-btn-text-only" href="#"><span class="ui-btn-text">会议签到</span></a>
	    <a class="button ui-btn ui-btn-text-only" href="#"><span class="ui-btn-text">会议管理</span></a><br/>
	    <a class="button ui-btn ui-btn-text-only" href="#"><span class="ui-btn-text">会议直播</span></a>
    </div>
  </div>
</div>

<div id="gotop"></div>

<script src="${ctx }/assets/libs/GMU/dist/zepto.js"></script>
<script src="${ctx }/assets/libs/GMU/dist/gmu.js"></script>

<script type="text/javascript">
$('.button').button();
</script>

<script>
function reposition() {
  if ($('body').height() > $('body').width()) { // 竖屏
      $('#button').addClass("absolute").css({ 'top': $('body').height() - 100});
      $('#button .button-container').removeClass('right').addClass("center").css({'width': $('body').width()});
  } else { // 横屏
      $('#button').addClass("absolute").css({ 'top': 30});
      $('#button .button-container').removeClass('center').addClass("right").css({'width': $('body').width() * 0.8 - 20});
  }
}

function resize() {
  $('#slider .ui-slider-item').height($('body').height());
	if ($('body').height() > $('body').width()) { // 竖屏
	  $('#slider .ui-slider-item img').width($('body').width());
	} else { // 横屏
	  $('#slider .ui-slider-item img').width($('body').width() * 0.8);
	}
}
//创建slider组件
$('#slider')
.on('ready', function(e, $el, opts){
	resize();
  reposition();
})
.on('width.change', function(e){
	resize();
  reposition();
})
.slider( { imgZoom: false });

</script>

<script type="text/javascript">
$("#gotop").gotop();
</script>
</body>
</html>