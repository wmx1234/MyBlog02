/**
 * 业务工具类
 * @type {{}}
 */

const businessUtils = {

    showWindow:function(type,title,url,w,h,data){
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
    },
    closeWindow:function(){
        layer.closeAll('page'); //关闭所有页面层
    },
    post : function(url,data){
        $.ajax({
            url:url,
            data:data,
            success:function(data){
                layer.msg('保存成功');
            }
        })
    }

}