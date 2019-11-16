(function($){
	
    
    
	$(".comment_del").click(function(){
		
	})
	$(".comment_like").click(function(){
		
	})
	function crateCommentInfo(obj){
		var el = "<div class='comment-info'><header><img src='"+obj.commentUser.avatar+"'></header><div class='comment-right'>";
			
		el = el+"<p class='content'><input type='hidden' name='id' value='"+obj.id+"'/><input type='hidden' name='commentUserId' value='"+obj.commentUser.id+"'/><span style='color:blue'>"+obj.commentUser.userName+':</span>'+obj.commentContent+"</p><div class='comment-content-footer'><div class='row'><div class='col-md-9'>";
		el = el+"<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>"+obj.createDate+"</span></div>";
		
		el = el + "</div><div class='col-md-3'>"+
		"<span class='comment_del'>删除</span>"+
		"<span class='reply-btn'>回复</span>"+
		"<span class='comment_like'>赞（"+obj.likes+"）</span>" +
				"</div></div></div><div class='reply-list'>";
		if(obj.comments != "" && obj.comments.length > 0){
			var arr = obj.comments;
			for(var j=0;j<arr.length;j++){
				var replyObj = arr[j];
				el = el+createReplyComment(replyObj);
			}
		}
		el = el+"</div></div></div>";
		return el;
	}
	
	
	
	//返回每个回复体内容
	function createReplyComment(reply){
		var replyEl = "<div class='reply'><div style='margin:5px'><input type='hidden' name='id' value='"+reply.id+"'/><a href='javascript:void(0)' class='replyname'>"+reply.commentUser.userName+"</a>:<a href='javascript:void(0)'>@"+reply.repayUser.userName+"</a><span>"+reply.commentContent+"</span></div>"
						+ "<p><span>"+reply.createDate+"</span> <span class='reply-list-btn'>回复</span></p></div>";
		return replyEl;
	}
	function filterNum(num){
		if(num < 10){
			return "0"+num;
		}else{
			return num;
		}
	}
	function replyClick(el){
		el.parent().parent().append("<div class='replybox'><textarea cols='80' rows='3' placeholder='来说几句吧......' class='mytextarea' ></textarea><span class='send'>发送</span></div>")
		.find(".send").click(function(){
			var content = $(this).prev().val();
			if(content != ""){
				var parentEl = $(this).parent().parent().parent().parent();
				var obj = new Object();
				obj.articleId = $("input[name='articleId']").val();;
				obj.parentId = parentEl.find("input[name='id']").val();
				obj.commentContent = content;
				
				if(el.parent().parent().hasClass("reply")){
					obj.repayUserId = el.parent().parent().find("input[name='commentUserId']").val();
				}else{
					obj.repayUserId=parentEl.find("input[name='commentUserId']").val();
				}
				$.post({
	                url:'/comment/save',
	                data: JSON.stringify(obj),
	            	dataType: "json", //表示返回值类型，不必须
	            	contentType:"application/json;charset=UTF-8",
	                success:function(e){
	                	location.reload();
	                }
	            })
				
				obj.content=content;
				$(".replybox").remove();
			}else{
				alert("空内容");
			}
		});
	}
	
	$(".pull-right").click(function(){
    	const articleId = $("input[name='articleId']").val();
        const commentContent = $("textarea").val();
        if(commentContent == null || commentContent == ''){
    	    alert('评论内容不能为空！！！');
    	    return;
        }
        $.post({
            url:'/comment/save',
            data: JSON.stringify({'articleId':articleId,'commentContent':commentContent}),
        	dataType: "json", //表示返回值类型，不必须
        	contentType:"application/json;charset=UTF-8",
            
            success:function(e){
            	location.reload();
            }
        })
    });
	
	
	$(".reply-btn").click(function(){
		if($(this).parent().parent().find(".replybox").length > 0){
			$(".replybox").remove();
		}else{
			$(".replybox").remove();
			replyClick($(this));
		}
	});
	
	$(".reply-list-btn").click(function(){
		if($(this).parent().parent().find(".replybox").length > 0){
			$(".replybox").remove();
		}else{
			$(".replybox").remove();
			replyClick($(this));
		}
	})
	
	
	
})(jQuery);