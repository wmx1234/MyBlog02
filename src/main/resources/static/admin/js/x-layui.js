/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/


function x_admin_show(type,title,url,w,h,data){
	debugger;
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: type,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shadeClose: true,
		shade:0.4,
		title: title,
		content: $(url)
	});

	if (data != null && data != '') {

		jQuery.each(data, function(i, val) {
			$("input[name='"+ i +"']").val(val);
		});

	}else{
		$(':input','form')

			.not(':button,:submit,:reset,:hidden')   //将myform表单中input元素type为button、submit、reset、hidden排除

			.val('')  //将input元素的value设为空值

			.removeAttr('checked')

	}

}

/*关闭弹出框口*/
function x_admin_close(){
	//var index = layer.getFrameIndex(window.name);
	//layer.close(index);
	layer.closeAll('page'); //关闭所有页面层
}