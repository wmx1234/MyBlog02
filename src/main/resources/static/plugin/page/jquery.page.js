//不能使用$(".blog_page").on('click',function(){}) 刷新页面后按钮重新生成，绑定事件同时被销毁
$(function(){
	
	const url = $("input[name=url]").val();
	const data = {}
	
	const getPage = function(data){
		const ids = $("input[name=ids]").val();
		const createDate = $("input[name=createDate]").val();
		
		data['ids'] = ids;
		data['createDate'] = createDate;
		console.log(url);
		$.ajax({
	    	  url:url,
	    	  type:"POST",
	    	  data:data,
	    	  success:function(e){
	    		  $(".contentList").html(e);
	    	  }
	    })
	}
	
	
	
	$(document).on('click','.blog_page',function(){
		data['currentPage'] = $(this).html();
		getPage(data);
	})
	$(document).on('click','.first',function(){
		var activepage = parseInt($('.activP').html());
        if (activepage == 1){
          	alert('当前已是第一页');
        	return;
        }
		$.post({
	    	  url:url,
	    	  data:{'currentPage':1},
	    	  success:function(e){
	    		  $(".contentList").html(e);
	    		  
	    	  }
	       })
	})
	
	$(document).on('click','.last',function(){
		var pages = parseInt($('.last').attr("value"));
		var activepage = parseInt($('.activP').html());
        if (pages == activepage){
          	alert('当前已是末页');
        	return;
        }//当前最后一页
		$.post({
	    	  url:url,
	    	  data:{'currentPage':pages},
	    	  success:function(e){
	    		  $(".contentList").html(e);
	    		  
	    	  }
	       })
	})	
	
	
	$(document).on('click','.next',function(){
		var pages = parseInt($('.last').attr("value"));
		var activepage = parseInt($('.activP').html());
        if (pages == activepage){
          	alert('当前已是末页');
        	return;
        }//当前最后一页
		$.post({
	    	  url:url,
	    	  data:{'currentPage':activepage+1},
	    	  success:function(e){
	    		  $(".contentList").html(e);
	    		  
	    	  }
	       })
	})	
	
	
	$(document).on('click','.prv',function(){
		
		var activepage = parseInt($('.activP').html());
        if (1 == activepage){
          	alert('当前已是首页');
        	return;
        }//当前最后一页
		$.post({
	    	  url:url,
	    	  data:{'currentPage':activepage-1},
	    	  success:function(e){
	    		  $(".contentList").html(e);
	    		  
	    	  }
	       })
	})	
	
	
	
})	
    