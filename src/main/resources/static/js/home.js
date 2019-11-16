$(function(){
	
    $(".article_title").on("click",function(){
    	const that = $(this);
    	const method = that.attr("method-data");
    	const id = that.attr("id");
    	$.post({
    		url:"/article/getArticleByKey",
    		data:{"id":id},
    		success:function(e){
    			
    		}
    	})
    })
});