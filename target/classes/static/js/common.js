var $backToTopEle=$('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="返回顶部" alt="返回顶部" style="display:none">^^</a>').appendTo($("body")).click(function(){
	$("html, body").animate({ scrollTop: 0 }, 120);
});
var backToTopFun = function() {
	var st = $(document).scrollTop(), winh = $(window).height();
	(st > 0)? $backToTopEle.show(): $backToTopEle.hide();
	/*IE6下的定位*/
	if(!window.XMLHttpRequest){
		$backToTopEle.css("top", st + winh - 166);
	}
};

var contextPath = function(){
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

$(function(){
	
	

		
	$(window).on("scroll",backToTopFun);
	backToTopFun();
	
	const url = {
		home:contextPath()+'/home',
		article:'/selectArticles',
		to_login:'/toLoginUI'
	}
		

	const active = {
		
		
	    
	  
	    to_loginUI(){
	    	$(".w_container").load(url.to_login);
	    },
	    registered(){
	    	$(".w_container").load('toRegistered');
	    },
	    to_registeredUI(){
	    	$(".w_container").load('/toRegisteredUI');
	    },
	    logout(){
	    	$.post({
	            url:"/logout",
	            method:'POST',
	            success:function(e){
	            	window.location.href="/index"; 
	            }
	        })
	    },
	    
	    
	}
    $(".xiao_btn").on("click",function(){
    	const that = $(this);
    	const method = that.attr("method-data");
    	active[method]?active[method].call(this,that):"";
    })
});
