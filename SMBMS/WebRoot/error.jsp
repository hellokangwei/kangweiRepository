<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>页面访问错误</title>
    <link href="statics/css/404.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="statics/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	$(function() {
		var h = $(window).height();
		$('body').height(h);
		$('.mianBox').height(h);
		centerWindow(".tipInfo");
	});

	//2.将盒子方法放入这个方，方便法统一调用
	function centerWindow(a) {
		center(a);
		//自适应窗口
		$(window).bind('scroll resize',
				function() {
					center(a);
				});
	}

	//1.居中方法，传入需要剧中的标签
	function center(a) {
		var wWidth = $(window).width();
		var wHeight = $(window).height();
		var boxWidth = $(a).width();
		var boxHeight = $(a).height();
		var scrollTop = $(window).scrollTop();
		var scrollLeft = $(window).scrollLeft();
		var top = scrollTop + (wHeight - boxHeight) / 2;
		var left = scrollLeft + (wWidth - boxWidth) / 2;
		$(a).css({
			"top": top,
			"left": left
		});
	}
</script>
</head>

<body>
<div class="mianBox">
	<img src="statics/images/yun0.png" alt="" class="yun yun0" />
	<img src="statics/images/yun1.png" alt="" class="yun yun1" />
	<img src="statics/images/yun2.png" alt="" class="yun yun2" />
	<img src="statics/images/bird.png" alt="" class="bird" />
	<img src="statics/images/san.png" alt="" class="san" />
	<div class="tipInfo">
		<div class="in">
			<div class="textThis">
				<h2>无法访问此网站!</h2>
				<span>找不到服务器 DNS 地址。</span>
				<p><span>页面自动<a id="href" href="javascript:history.back()">跳转</a></span><span>等待<b id="wait">30</b>秒</span></p>
				<script type="text/javascript">                            (function() {
						var wait = document.getElementById('wait'), href = document.getElementById('href').href;
						var interval = setInterval(function() {
							var time = --wait.innerHTML;
							if (time <= 0) {
								
								clearInterval(interval);
								location.href = history.back();
								/* alert("开始跳转提示"); */
							}
							;
						}, 1000);
					})();
				</script>
			</div>
		</div>
	</div>
</div>
</body>
</html>