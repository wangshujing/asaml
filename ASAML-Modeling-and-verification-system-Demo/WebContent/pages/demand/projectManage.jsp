<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>项目管理</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
		<!--[if lt IE 9]>
	      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
    </head>
    <body>
		<jsp:include page="../nav.jsp"></jsp:include>
	    <div class="container-fluid">
	    	<div class="row" style="min-height: 400px;">
	    		<div class="col-md-2 col-sm-3 col-xs-12 sidebar">
	    			<jsp:include page="../siderbar.jsp"></jsp:include>
	    		</div>
	    		<div class="col-md-10 col-sm-9 col-xs-12">
	    			<div class="page-location">
		    			<ol class="breadcrumb">
						  <li><a href="<%=request.getContextPath() %>/index">首页</a></li>
						  <li class="active">项目管理</li>
						</ol>
		    		</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12">
							<div class="col-md-8 col-sm-9 col-xs-12 input-group" style="">
									<input type="text" class="form-control" id="searchKeyword">
							     	<span class="input-group-btn">
							        	<button class="btn btn-info" type="button" id="searchProject">搜索项目</button>
							     		<button  class="btn btn-md btn-primary"  id="addProjectPanel" style="margin-left:5%;">添加项目</button>
								   	</span>
							</div>
							<div class="headline">
								<h4>项目列表</h4>
								
							</div>
							<div class="row" style="margin-left:20px;">
								<div class="table-responsive">
										<table id="projectList" class="table table-bordered">
											<thead>
												<tr>
													<th class="center" >序号</th>
													<th>项目编号</th>
													<th>项目名称</th>
													<th>添加时间</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
											</tbody>
										</table>
									</div>
							</div>
				            <div class="row">
					            <div class="page">
						        	<span id="setpage" class="table_setpage"></span>
					        	</div>
				        	</div>
			        	</div>
	    			</div>
		    		
		    		
	    			
	    			
				</div>
	    	</div>
    		</div>
    		<jsp:include page="../footer.jsp"></jsp:include>
	    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加项目</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" >
		      		<fieldset>
		    			<div class="form-group">
						  	<label for="name" class="col-sm-3 control-label">项目名称<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						    	<input type="text" class="form-control" id="name" placeholder="输入名称…">
						    	<span id="name_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
					    <div class="form-group">
						    <label for="remark" class="col-sm-3 control-label">备注</label>
						    <div class="col-md-6">
						    	<textarea rows="3" class="form-control" id="remark" ></textarea>
						    </div>
					    </div>
					    <div class="form-group" style="display:none;">
						    <label for="pid" class="col-sm-3 control-label"></label>
						    <div class="col-md-6">
						    	<input class="form-control" id="pid" ></input>
						    </div>
					    </div>
					    
					</fieldset>
					
				    
				</form>
			  </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="addProjectInfo()" id="addProject">添加</button>
		      </div>
		    </div>
		  </div>
		</div>
        <!-- Javascripts -->
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/utils.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/page.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootbox.min.js"></script>
		<script type="text/javascript">
        $("#ProjectBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
		var keyword = "";
		$("#searchProject").click(function(){
			keyword = $.trim($("#searchKeyword").val());
			getProjectList();
		});
		getProjectList();
        $("#addProjectPanel").click(function(){
			$("#myModalLabel").html("添加项目");
			$("#pid").val("0");
			$("#name").val("");
			$("#remark").val("");
			$("#addProject").html("添加");
			$("#myModal").modal("show");;
		});
        var a = function(p) {
			page = Number(p);
			getProjectList(); 
			
		};
        //获取项目列表
        function getProjectList(){
        	$.ajax({
        		type : "POST",
				url : root + "/project/getProjectList",
				data : JSON.stringify({limit:limit,page:page,keyword:keyword}),
				success : function(data) {
					$("tbody", $("#projectList")).empty();
					if (data.errorcode == 1000) {
						var projects = data.result.info.project;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<projects.length;i++){
								var temp = '<td pid="'+projects[i].id+'"><button class="editProject btn btn-xs btn-info" style="margin-right:5px;"><i class="fa fa-edit "></i></button>'+
								'<button class="deleteProject btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-remove"></i></button>';
								
								var str = '<tr><td class="center">'+((page-1)*limit+i+1)+'</td><td>'+projects[i].id+'</td>'+
								'<td>'+projects[i].projectName+'</td><td>'+getFormatDateByLong(projects[i].addTime)+'</td>'+
								temp+'</tr>';
								$("tbody",$("#projectList")).append(str);
							}
							var total = data.result.info.total;
							var total_page=Math.ceil(total/limit);
							var opt={
									selectedPage:page,
									total:total_page,
									pageLength:7,
									showLocation:4,
									callback:a
									};
							
							$('#setpage').Pagination(opt);
							
						
							$(".editProject").click(function(){
								$("#myModalLabel").html('编辑项目');
								$("#name_error").hide();
								var pid = $(this).parent().attr("pid");
								$("#pid").val(pid);
								$.ajax({
									type:"POST",
									url:root+"/project/getProjectInfo",
									data:JSON.stringify({id:pid}),
									success:function(data1){
										if (data1.errorcode == 1000){
											var project = data1.result.info;
											$("#name").val(project.projectName);
											$("#remark").val(project.projectIntro);
											
											$("#addProject").html("编辑");
											$("#myModal").modal("show");;
										}
									},
									datatype:"json"
								});
							});
							$(".deleteProject").click(function(){
								var pid = $(this).parent().attr("pid");
								bootbox.confirm({  
							        buttons: {  
							            confirm: {  
							                label: '确认',  
							            },  
							            cancel: {  
							                label: '取消',  
							            }  
							        },  
							        message: '确定要删除该项目吗？',  
							        callback: function(result) {  
							            if(result) {  
							            	$.ajax({
												type:"POST",
												url:root+"/project/deleteProject",
												data:JSON.stringify({id:pid}),
												success:function(data2){
													if (data2.errorcode == 1000){
														window.location.reload();
													}
												},
												datatype:"json"
											});
							            }
							        },  
							        title: "删除项目",  
							        });
							});
							
						}else{
							$("tbody",$("#projectList")).append('<tr><td colspan="5">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
						
					}
				}
        	});
        }
     // 添加/编辑项目
		function addProjectInfo() {
			var pid = $("#pid").val();
			var name = $("#name").val();
			var remark = $("#remark").val();
			var has_error = 0;
			if(!name){
				has_error = 1;
				$("#name_error").html("项目名称不能为空！");
				$("#name_error").show();
			}else{
				$("#name_error").html("");
				$("#name_error").hide();
			}
			if(has_error){
				return;
			}
			
			if(pid=="0"){
				$.ajax({
					type : "POST",
					url : root + "/project/addProject",
					data : JSON.stringify({
						projectName : name,
						projectIntro: remark,
					}),
					success : function(data) {
						if(typeof data == "string"){
	    					data = JSON.parse(data);
	    				}
						if (data.errorcode == 1000) {
							window.location.reload();
						}else {
							alert("添加失败！");
						}
					},
					datatype : "json"
				});
			}else{
				$.ajax({
					type : "POST",
					url : root + "/project/editProject",
					data : JSON.stringify({
						id: pid,
						projectName : name,
						projectIntro: remark,	
					}),
					success : function(data) {
						if(typeof data == "string"){
	    					data = JSON.parse(data);
	    				}
						if (data.errorcode == 1000) {
							window.location.reload();
						}else {
							alert("编辑失败！");
						}
					},
					datatype : "json"
				});
			}
		} 
        
        </script>
</body>
</html>