$(function() {
	
	//得到查询的参数
	const queryParams = function(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			departmentname : $("#txt_search_departmentname").val(),
			statu : $("#txt_search_statu").val()
		};
		return temp;
	};
	
	//初始化表格
	$('#tb_departments').bootstrapTable({
        url: '/user/getUsers',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams:queryParams,//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 25,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: true
        }, {
            field: 'userName',
            title: '账号',
            align:'center',
        }, {
            field: 'name',
            title: '姓名',
            align:'center',
        }, {
            field: 'gender',
            title: '性别',
            align:'center',
            formatter: function (value, row, index){ // 单元格格式化函数
                var text = '-';
                if (value == 0) {
                    text = "男";
                } else if (value == 1) {
                    text = "女";
                } 
                return text;
            }

        }, {
            field: 'birthday',
            title: '生日',
            align:'center',
        }, {
            field: 'email',
            title: '邮箱',
            align:'center',
        }, {
            field: 'phoneNum',
            title: '电话',
            align:'center',
        }, {
            field: 'description',
            title: '描述',
            align:'center',
            visible:false
        
        }, ]
      
    });

	const setting = {
			check: {
                enable: true,
                chkStyle: "radio",
				radioType: "level"
            },
			view: {
	            dblClickExpand: false,
	            showLine: true,
	            selectedMulti: false
	        },
	        data: {
	            simpleData: {
	                enable:true,
	                idKey: "id",
	                pIdKey: "parentId",
	                rootPId: ""
	            }
	        }
	    };
	
	
	$("#btn_delete").click(function(){
    	const data = $("#tb_departments").bootstrapTable('getSelections');
    	
    	if(data.length == 1){
    	   const id = data[0].id;
    	   $.post({
    		   url:'/user/delete',
    		   data:{'id':id},
    		   success:function(){
    			   $('#tb_departments').bootstrapTable('refresh');
    		   }
    	   })
    	}else{
    		alert("请选中一行");
            return false;
    	}
    })
    
    $("#btn_reset").click(function(){
    	const data = $("table").bootstrapTable('getSelections');
        if (data.length == 1) {
        	$.post({
                url : '/user/updateUser',
                data : {'password':'123456','id':data[0].id,'userName':data[0].userName},
                method : "post",
                success : function(e) {
                	if(e.cstatus == 200){
                		alert("重置密码成功");
                    }else{
                        //alert(2);
                    }
                    
                }
            })
        } else {
            alert("请选择一个授权角色");
            return false;
        }
    })
    $("#btn_set_role").click(function(){
    	
    	const data = $("table").bootstrapTable('getSelections');
    	
        if (data.length == 1) {
        	$("#role_list").modal("show");
            $.post({
                url:'/role/getRoles',
                success:function(e){
                	for(let i=0;i<e.length;i++){
                		if(e[i].id == data[0].role.id){
                			e[i]['checked'] = true;
                		}
                	}
                	var t = $("#tree");
                    t = $.fn.zTree.init(t, setting, e);
                   
                }
            })
        } else {
            alert("请选择一个用户");
            return false;
        }
    })
    
    $("#set_role_modal").click(function(){
    	
    	const zTree = $.fn.zTree.getZTreeObj('tree');
        const checkCount = zTree.getCheckedNodes(true);
        const roleId = checkCount[0].id;
        const userId = $("#tb_departments").bootstrapTable('getSelections')[0].id;
        const data = {'roleId':roleId,'userId':userId};
        $.post({
        	url:'/user/setRole',
        	data: JSON.stringify(data),
        	dataType: "json", //表示返回值类型，不必须
        	contentType:"application/json;charset=UTF-8",
        	success:function(){
        		 alert("分配角色成功！！！")
        		$("#role_list").modal("hide");
        		$('#tb_departments').bootstrapTable('refresh');
        	}
        })
    })
})