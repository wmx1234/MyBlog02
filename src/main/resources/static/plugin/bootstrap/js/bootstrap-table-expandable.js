(function ($) {
    $(function () {
        $('.table-expandable').each(function () {
            var table = $(this);
            table.children('thead').children('tr').append('<th style="text-align: center">操作</th>');
            table.children('tbody').children('tr').filter(':odd').hide();
            //table.children('tbody').children('tr').filter(':even')
            $(".user_show_close").click(function () {
            	alert(1);
            	var element = $(this);
                //toggle()如果获取到的元素显示则变为隐藏，如果隐藏则显示
                element.next('tr').toggle();
                var className = element.find(".glyphicon")[2].classList[1];
                if(className == 'glyphicon-chevron-up'){
                	element.find(".glyphicon-chevron-up").addClass("glyphicon-chevron-down");
                    element.find(".glyphicon-chevron-down").removeClass("glyphicon-chevron-up");
                }else{
                	element.find(".glyphicon-chevron-down").addClass("glyphicon-chevron-up");
                    element.find(".glyphicon-chevron-up").removeClass("glyphicon-chevron-down");
                }
                
            });
            table.children('tbody').children('tr').filter(':even').each(function () {
                var element = $(this);
                
                element.append('<td style="text-align: center">'+
                		'<span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;'+
                		'<a href="#" class="user_delete"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;&nbsp;'+
                		'<span class="user_detail glyphicon glyphicon-chevron-down"></td>');
            });
        });
        
        $(".user_delete").click(function(){
        	const r= confirm("确定删除该用户吗？？？");
        	if(r == false) return;
        	var td = $(this).parent().parent().children()[0];
        	$.post({
        		url:'/user/delete',
        		data:{'id':$(td).html()},
        		success:function(){
        			location.reload();
        		}
        	})
        })
        
        $(".user_detail").click(function () {
        	
        	var element = $(this);
        	//toggle()如果获取到的元素显示则变为隐藏，如果隐藏则显示
        	element.parent().parent().next('tr').toggle();
           
            var className = this.classList[2];
            if(className == 'glyphicon-chevron-up'){
            	element.addClass("glyphicon-chevron-down");
                element.removeClass("glyphicon-chevron-up");
            }else{
            	element.addClass("glyphicon-chevron-up");
                element.removeClass("glyphicon-chevron-down");
            }
            
        });
    });
})(jQuery); 