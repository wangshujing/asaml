<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>框架类型管理</title>
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
						  <li class="active">框架类型管理</li>
						</ol>
		    		</div>
	    			<div class="row">
	    				<div class="col-md-11 col-sm-7 col-xs-12">
							<div class="col-md-12 col-sm-9 col-xs-12 input-group" style="">
									<input type="text" class="form-control" id="searchKeyword">
							     	<span class="input-group-btn">
							        	<button class="btn btn-info" type="button" id="searchTypeInterface">搜索类型</button>
							     		<button  class="btn btn-md btn-primary"  id="addTypeInterfacePanel" style="margin-left:5%;">添加类型</button>
							     	</span>
							</div>
							<div class="headline">
								<h4>框架类型列表</h4>
								
							</div>
							<div class="row" style="margin-left:20px;">
								<div class="table-responsive">
										<table id="TypeInterfaceList" class="table table-bordered" style="table-layout: fixed;word-wrap:break-word;">
											<thead>
												<tr>
													<th class="center" width="45px;">编号</th>
													<th width="150px;">类型名称</th>
													<th width="600px;">类型表达式</th>
													<th width="80px;">规则数</th>
													<th>项目</th>
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
		        <h4 class="modal-title" id="myModalLabel">添加框类型</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" >
		      		<fieldset>
		    			<div class="form-group">
						  	<label for="name" class="col-sm-3 control-label">类型名称<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						    	<input type="text" class="form-control" id="name" placeholder="输入名称…">
						    	<span id="name_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
					    <div class="form-group">
						  	<label for="project" class="col-sm-3 control-label">项目编号<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control" id="project" placeholder="请输入项目编号" list="projectList">
						  		<datalist id="projectList">
								</datalist>
						    	<span id="project_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
					    <div class="form-group" id="paraDiv_1">
						  	<label for="inputPara" class="col-sm-3 control-label">框1</label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control inputPara" id="inputPara_1" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_1">
						  		<datalist id="inputParaList_1" class="paraList">
								</datalist>
						    </div>
						</div>
						<a class="btn btn-info" type="button"  id="addPara"><i class="fa fa-plus"></i></a>
					    <div class="form-group">
						  	<label for="demand" class="col-sm-3 control-label">需求编号<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control" id="demand" placeholder="请输入需求编号" list="demandList">
						  		<datalist id="demandList">
								</datalist>
						    	<span id="demand_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
					    <div class="form-group">
							<label for="type" class="col-sm-3 control-label" >所属类型<span style="color: red;">*</span></label>
							<div class="col-md-6 ">
								<select id="type" class="form-control">
									<option value="0">请选择类别</option>
									<option value="1">框架</option>
									
								</select>
												
								<span id="type_error" style="display: none;color: red;font-size:10px;"></span>
												
							</div>
						</div>
					   <div class="form-group">
						    <label for="remark" class="col-sm-3 control-label">备注</label>
						    <div class="col-md-6">
						    	<textarea rows="3" class="form-control" id="remark" ></textarea>
						    </div>
					    </div>
					    <div class="form-group" style="display:none;">
						    <label for="bid" class="col-sm-3 control-label"></label>
						    <div class="col-md-6">
						    	<input class="form-control" id="bid" ></input>
						    </div>
					    </div>
					</fieldset>
					
				    
				</form>
			  </div>
		      <div class="modal-footer">
		        
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="addTypeInterfaceInfo()" id="addTypeInterface">添加</button>
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
        $("#FrameworkBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
		var keyword = "";
		$("#searchTypeInterface").click(function(){
			keyword = $.trim($("#searchKeyword").val());
			getTypeInterfaceList();
		});
		getProjectCatalog();
		getTypeInterfaceList();
		$("#project").change(function(){
			$(".inputPara").val("");
			if($("#project").val()!=""||$("#project").val()!=null){
				getEnvExtendedTypeCatalog($("#project").val());
				getDemandList($("#project").val());
			}
				
		})
		$("#addPara").click(function(e){
			var i = $("#addPara").prev().attr("id").split("_")[1];
        	var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
			  				'<label for="inputPara" class="col-sm-3 control-label">框类型'+(Number(i)+1)+'</label>'+
			  				'<div class="col-md-6">'+
			  					'<input type="text" class="form-control inputPara" id="inputPara_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_'+(Number(i)+1)+'">'+
			  					'<datalist id="inputParaList_'+(Number(i)+1)+'" class="paraList">'+
								'</datalist>'+
			    			'</div>'+
			   			'</div>'; 
			   			$("#addPara").before(str);
			   			$("#inputParaList_"+(Number(i)+1)).append($("#inputParaList_1").html());
		});
        
       
        var a = function(p) {
			page = Number(p);
			getTypeInterfaceList(); 
			
		};
		 function getProjectCatalog(){
	        	$.ajax({
	        		type : "POST",
					url : root + "/project/getProjectCatalog",
					data : JSON.stringify({keyword:""}),
					success : function(data) {
						$("#projectList").empty();
						if (data.errorcode == 1000) {
							var projects = data.result.info.project;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<projects.length;i++){
									str += '<option value="'+projects[i].id+'" label="'+projects[i].projectName+'" />';
								}
								$("#projectList").append(str);
							}
						}
					}
	        	});
	        }
		 function getDemandList(proId){
	        	$.ajax({
	        		type : "POST",
					url : root + "/demand/getDemandListbyLevel",
					data : JSON.stringify({level:1,projectId:proId}),
					success : function(data) {
						$("#demandList").empty();
						if (data.errorcode == 1000) {
							var demands = data.result.info.demands;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<demands.length;i++){
									str += '<option value="'+demands[i].id+'" label="'+demands[i].demandName+'" />';
								}
								$("#demandList").append(str);
							}
						}
					}
	        	});
	        }
		 function getEnvExtendedTypeCatalog(proId){
			 
			 $(".paraList").empty();		
			 $.ajax({
	        		type : "POST",
					url : root + "/samlType/getSamlTypeCatalog",
					data : JSON.stringify({projectId:Number(proId),level:6}),
					success : function(data) {
						
						if (data.errorcode == 1000) {
							var envExtendedTypes = data.result.info.samlType;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<envExtendedTypes.length;i++){
									str += '<option value="'+envExtendedTypes[i].id+'" label="'+envExtendedTypes[i].typeName+'" />';
								}
								$(".paraList").append(str);
								
							}
						}
					}
	        	});
	        }
        function getTypeInterfaceList(){
        	$.ajax({
        		type : "POST",
				url : root + "/samlType/getSamlTypeList",
				data : JSON.stringify({limit:limit,page:page,keyword:keyword,level:7}),
				success : function(data) {
					$("tbody", $("#TypeInterfaceList")).empty();
					if (data.errorcode == 1000) {
						var typeInterfaces = data.result.info.samlType;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<typeInterfaces.length;i++){
								var name = typeInterfaces[i].typeName.replace(/</,"&lt;").replace(/>/,"&gt;");
								var exp = typeInterfaces[i].typeExpression.replace(/</,"&lt;").replace(/>/,"&gt;");
								var temp = '<td bid="'+typeInterfaces[i].id+'"><button class="editTypeInterface btn btn-xs btn-info" style="margin-right:5px;"><i class="fa fa-edit "></i></button>'+
								'<button class="deleteTypeInterface btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-remove"></i></button>';
								var str = '<tr><td class="center">'+((page-1)*limit+i+1)+'</td><td>'+name+'</td>'+
								'<td style="width:700px;">'+exp+'</td><td>'+typeInterfaces[i].typeRules+'</td><td>'+typeInterfaces[i].projectName+'</td>'+temp+'</tr>';
								$("tbody",$("#TypeInterfaceList")).append(str);
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
							
						
							$(".editTypeInterface").click(function(){
								$("#myModalLabel").html('编辑框架类型');
								var bid = $(this).parent().attr("bid");
								$("#bid").val(bid);
								$.ajax({
									type:"POST",
									url:root+"/samlType/getSamlTypeInfo",
									data:JSON.stringify({id:bid,level:7}),
									success:function(data1){
										if (data1.errorcode == 1000){
											var TypeInterface = data1.result.info.samlType;
											var total = data1.result.info.total;
											var para = data1.result.info.para;
											$("#name").val(TypeInterface.typeName);
											$("#remark").val(TypeInterface.remark);
											$("#demand").val(TypeInterface.demandId);
											$("#type").val(TypeInterface.typeClassification);
											$("#project").val(TypeInterface.projectId);
											getEnvExtendedTypeCatalog(TypeInterface.projectId);
											for(var i=0;i<total;i++){
												if(i!=0){
													console.log("!!!!"+i);
													var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
									  				'<label for="inputPara" class="col-sm-3 control-label">框类型'+(Number(i)+1)+'</label>'+
									  				'<div class="col-md-6">'+
									  					'<input type="text" class="form-control inputPara" id="inputPara_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_'+(Number(i)+1)+'">'+
									  					'<datalist id="inputParaList_'+(Number(i)+1)+'" class="paraList">'+
														'</datalist>'+
									    			'</div>'+
									   			'</div>'; 
									   			$("#addPara").before(str);
									   			console.log($("#inputParaList_1").html());
									   			$("#inputParaList_"+(Number(i)+1)).append($("#inputParaList_1").html());
												}
												$("#inputPara_"+(i+1)).val(para[i]);
											}
											$("#addTypeInterface").html("编辑");
											$("#myModal").modal("show");;
										}
									},
									datatype:"json"
								});
							});
							$(".deleteTypeInterface").click(function(){
								var bid = $(this).parent().attr("bid");
								bootbox.confirm({  
							        buttons: {  
							            confirm: {  
							                label: '确认',  
							            },  
							            cancel: {  
							                label: '取消',  
							            }  
							        },  
							        message: '确定要删除该类型吗？',  
							        callback: function(result) {  
							            if(result) {  
							            	$.ajax({
												type:"POST",
												url:root+"/samlType/deleteSamlType",
												data:JSON.stringify({id:bid,level:7}),
												success:function(data2){
													if (data2.errorcode == 1000){
														window.location.reload();
													}
												},
												datatype:"json"
											});
							            }
							        },  
							        title: "删除类型",  
							        });
							});
							
						}else{
							$("tbody",$("#TypeInterfaceList")).append('<tr><td colspan="6">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
					}
				}
        	});
        }
        
        $("#addTypeInterfacePanel").click(function(){
			$("#myModalLabel").html("添加框架类型");
			$("#bid").val("0");
			$("#name").val("");
			$("#type").val("0");
			$("#demand").val("");
			$("#project").val("");
			$("#remark").val("");
			$(".inputPara").val("");
			$(".paraList").empty();
			$("#addTypeInterface").html("添加");
			$("#myModal").modal("show");;
		});  
       function addTypeInterfaceInfo(){
    	   var bid = $("#bid").val();
			var name = $("#name").val();
			var type = $("#type").val();
			var project = $("#project").val();
			var demand = $("#demand").val();
			var remark = $("#remark").val();
			var outputPara = $("#outputPara").val();
			var list = $(".inputPara");
			var inputPara=[];
			for(var i=0;i<list.length;i++){
				inputPara.push($(list[i]).val());
			}
			
			var has_error = 0;
			if(!name){
				has_error = 1;
				$("#name_error").html("类型名称不能为空！");
				$("#name_error").show();
			}else{
				$("#name_error").html("");
				$("#name_error").hide();
			}
			if(type==0){
				has_error = 1;
				$("#type_error").html("所属类别不能为空！");
				$("#type_error").show();
			}else{
				$("#type_error").html("");
				$("#type_error").hide();
			}
			if(project==0){
				has_error = 1;
				$("#project_error").html("项目编号不能为空！");
				$("#project_error").show();
			}else{
				$("#project_error").html("");
				$("#project_error").hide();
			}
			if(has_error){
				return;
			}
			
			 if(bid=="0"){
				$.ajax({
					type : "POST",
					url : root + "/samlType/addSamlType",
					data : JSON.stringify({
						typeName : name,
						projectId : project,
						demand:demand,
						typeClassification: type,
						remark: remark,
						outputPara : outputPara,
						inputPara: inputPara,
						level:7
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
					url : root + "/samlType/editSamlType",
					data : JSON.stringify({
						id: bid,
						typeName : name,
						projectId : project,
						demand:demand,
						typeClassification: type,
						remark: remark,
						outputPara : outputPara,
						inputPara: inputPara,
						level:7
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