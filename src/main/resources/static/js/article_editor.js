$(function() {
	const id = $("input[name=id]").val();
	if (id != null && id != '') {

		$.post({
			url : '/article/getArticleByKey',
			data : {
				'id' : id
			},
			success : function(e) {
				
				$("input[name=articleTitle]").val(e.articleTitle);
				if(e.editorType == 0){
					editor.txt.html(e.articleHtmlContent);
				}else{
					document.getElementById("my-editormd-markdown-doc").innerHTML = e.articleContent;
				}
				
				$(".article_type").val(e.articleType.id);
				const labels = e.labels;
				for (let i = 0; i < labels.length; i++) {
					$("#tInputContainer").append(
						"<div class='tag_tx' value='"
						+ labels[i].id
						+ "'>"
						+ labels[i].name
						+ "<a href='javascript:void(0)' class='tag_close'>×</a></div>");
				}

			}
		})

	}

	$(".w_blog_type").click(function() {
		$("#myModal").modal("show");
	})

	$("#tInputContainer").on("click",function() {
						const that = $(this);
						const children = that.children("input");
						if (children.length < 1) {
							that
									.append("<input id='article_lable_input' style='outline:none;width:auto' class='tag_add'></input>");
						}
						$("#article_lable_input").focus();

					})
	$(document).on(
					'blur',
					'.tag_add',
					function() {
						const that = $(this);
						const name = that.val();
						if (name.length > 10) {
							alert("标签长度不能超过10");
							return;
						}
						var labels = $('.tag_tx');
						for (let i = 0; i < labels.length; i++) {
							let label = $(labels[i]).text();
							label = label.substring(0, label.length - 1);
							if (label == name) {
								alert("标签已存在！！！");
								return;
							}
							;
						}

						if (name != null && name != "") {
							$
									.post({
										url : '/label/save',
										data : {
											'name' : name
										},
										success : function(e) {
											$("#tInputContainer")
													.append(
															"<div class='tag_tx' value='"
																	+ e.id
																	+ "'>"
																	+ e.name
																	+ "<a href='javascript:void(0)' class='tag_close'>×</a></div>");
										}
									})

						}
						that.remove();
					})

	$(document).on('keypress', '.tag_add', function(event) {

		if (event.keyCode == 13) {
			this.blur();

		}
		;
	})

	$(document).on('click', '.tag_close', function() {
		$(this).parent(".tag_tx").remove();
	})

	$("#type_create").click(function() {
		const name = $("input[name=name]").val();
		$.post({
			url : '/articleType/saveType',
			data : {
				'name' : name
			},
			success : function(e) {

				$("#myModal").modal("hide");
				$(".article_type").html(e);

				$('.article_type').each(function(i, j) {

					$(j).find("option").first().attr("selected", true);
				})
			}
		})

	})

})

function submit() {
	
	const article = {};
	const articleTitle = $("input[name='articleTitle']").val();
	const editorType = $("input[name='editorType']").val();
	const isprivacy = $("input[name='isprivacy']:checked").length;
	
	if (articleTitle == null || articleTitle == '') {
		alert('文章标题不能为空');
		return;
	}
	const editortype = $("input[name='editorType']").val();
	
	const articleContent = md_edit.getMarkdown();
	const articleHtmlContent = md_edit.getHTML();
	if (articleContent == null || articleContent == '') {
		alert('文章内容不能为空');
		return;
	}
	var articleType = $('.article_type option:selected').val();// 选中的值
	var labels = $('.tag_tx');
	if (labels.length <= 0) {
		alert('标签不能为空');
		return;
	}
	const articleLabels = '';
	const list = [];

	for (let i = 0; i < labels.length; i++) {
		let label = {};
		label['id'] = $(labels[i]).attr("value");
		list.push(label);
	}
	const id = $("input[name=id]").val();
	article['isprivacy'] = isprivacy;
	article['id'] = id;
	article['articleType'] = {
		'id' : articleType
	}
	article['articleContent'] = articleContent;
	article['articleTitle'] = articleTitle;
	article['articleHtmlContent'] = articleHtmlContent;
	article['editorType'] = editorType;
	//草稿
	article['isdraft'] = 0;
	article['labels'] = list;
	
	$.post({
		'url' : '/article/save',
		'data' : JSON.stringify(article),// 将对象序列化成JSON字符串
		'dataType' : "json",
		'contentType' : 'application/json;charset=utf-8', // 设置请求头信息
		'success' : function(e) {
			if (e.cstatus == 0) {
				tip(e.article);
				$("#myModal").modal("hide");
			}
		}
	})
}


function draft() {
	
	const article = {};
	const articleTitle = $("input[name='articleTitle']").val();
	const editorType = $("input[name='editorType']").val();
	console.log($("input[name='isprivacy']:checked"));
	const isprivacy = $("input[name='isprivacy']:checked").length;
	console.log(isprivacy);
	const editortype = $("input[name='editorType']").val();
	const articleContent = md_edit.getMarkdown();
	const articleHtmlContent = md_edit.getHTML();
	var articleType = $('.article_type option:selected').val();// 选中的值
	var labels = $('.tag_tx');
	const articleLabels = '';
	const list = [];

	for (let i = 0; i < labels.length; i++) {
		let label = {};
		label['id'] = $(labels[i]).attr("value");
		list.push(label);
	}
	const id = $("input[name=id]").val();
	article['isprivacy'] = isprivacy;
	article['id'] = id;
	article['articleType'] = {
		'id' : articleType
	}
	article['articleContent'] = articleContent;
	article['articleTitle'] = articleTitle;
	article['articleHtmlContent'] = articleHtmlContent;
	article['editorType'] = editorType;
	//草稿
	article['isdraft'] = 1;
	article['labels'] = list;
	
	$.post({
		'url' : '/article/save',
		'data' : JSON.stringify(article),// 将对象序列化成JSON字符串
		'dataType' : "json",
		'contentType' : 'application/json;charset=utf-8', // 设置请求头信息
		'success' : function(e) {
			if (e.cstatus == 0) {
				tip(e.article);
				$("#myModal").modal("hide");
			}
		}
	})
}

function cancle(){
	$("#myModal").modal("hide");
}
function tip(article) {
	console.log(article);
	swal({
		title : article.articleTitle,
		text : "<a target='_blank' href='/article/toArticleUI?id=" + article.id
				+ "'>发布成功并前往查看<a>",
		html : true,
		confirmButtonText : "返回首页！",
		showCancelButton : true,
		cancelButtonText : "写新文章！",
		cancelButtonColor : "#8cd4f5",
		closeOnConfirm : false,
		closeOnCancel : true
	}, function(isConfirm) {
		if (isConfirm) {
			location.href = '/index';
		} else {
			location.href = '/article/wangeditorUI';
		}
	});
}