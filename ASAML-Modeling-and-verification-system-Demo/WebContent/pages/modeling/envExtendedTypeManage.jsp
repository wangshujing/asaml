<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>带标签的映射类型管理</title>
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
						  <li class="active">带标签的映射类型管理</li>
						</ol>
		    		</div>
	    			<div class="row">
	    				<div class="col-md-11 col-sm-7 col-xs-12">
							<div class="col-md-12 col-sm-9 col-xs-12 input-group" style="">
									<input type="text" class="form-control" id="searchKeyword">
							     	<span class="input-group-btn">
							        	<button class="btn btn-info" type="button" id="searchEnvExtendedType">搜索类型</button>
							     		<button  class="btn btn-md btn-primary"  id="addEnvExtendedTypePanel" style="margin-left:5%;">添加类型</button>
							     	</span>
							</div>
							<div class="headline">
								<h4>带标签的映射类型列表</h4>
								
							</div>
							<div class="row" style="margin-left:20px;">
								<div class="table-responsive">
										<table id="EnvExtendedTypeList" class="table table-bordered" style="table-layout: fixed;word-wrap:break-word;">
											<thead>
												<tr>
													<th class="center" width="45px;">编号</th>
													<th width="120px;">类型名称 </th>
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
		        <h4 class="modal-title" id="myModalLabel">添加基础类型</h4>
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
						  	<label for="inputPara" class="col-sm-3 control-label">输入参数类型1</label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control inputPara" id="inputPara_1" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_1">
						  		<datalist id="inputParaList_1" class="paraList">
								</datalist>
						    </div>
						</div>
						<a class="btn btn-info" type="button"  id="addPara"><i class="fa fa-plus"></i></a>
					    <div class="form-group" style="margin-top: -30px;">
						  	<label for="outputPara" class="col-sm-3 control-label">输出参数类型</label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control" id="outputPara" placeholder="输入关键字或点击右侧箭头选择" list="outputParaList">
						  		<datalist id="outputParaList" class="paraList">
								</datalist>
						    </div>
						</div>
						 <div class="form-group" style="" id="demandDiv_1">
						  	<label for="demand_1" class="col-sm-3 control-label">需求编号1<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control demand" id="demand_1" placeholder="请输入需求编号" list="demandList_1">
						  		<datalist id="demandList_1" class="demandList">
								</datalist>
						    	<span id="demand_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
					    <a class="btn btn-info" type="button"  id="addDemand"><i class="fa fa-plus"></i></a>
					    
					    <div class="form-group" style="margin-top: -30px;">
							<label for="type" class="col-sm-3 control-label" >所属类型<span style="color: red;">*</span></label>
							<div class="col-md-6 ">
								<select id="type" class="form-control">
									<option value="0">请选择类别</option>
									<option value="1">前端</option>
									<option value="2">后端</option>
									
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
		        <button type="button" class="btn btn-primary" onclick="addEnvExtendedTypeInfo()" id="addEnvExtendedType">添加</button>
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
        $("#EnvExtendedTypeBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
		var keyword = "";
		$("#searchEnvExtendedType").click(function(){
			keyword = $.trim($("#searchKeyword").val());
			getEnvExtendedTypeList();
		});
		getProjectCatalog();
		getEnvExtendedTypeList();
		$("#project").change(function(){
			$(".inputPara").val("");
			$("#outputPara").val("");
			if($("#project").val()!=""||$("#project").val()!=null){
				getEnvBasicTypeCatalog($("#project").val());
				getDemandList($("#project").val());
			}
				
		})
		$("#addPara").click(function(e){
			var i = $("#addPara").prev().attr("id").split("_")[1];
        	var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
			  				'<label for="inputPara" class="col-sm-3 control-label">输入参数类型'+(Number(i)+1)+'</label>'+
			  				'<div class="col-md-6">'+
			  					'<input type="text" class="form-control inputPara" id="inputPara_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_'+(Number(i)+1)+'">'+
			  					'<datalist id="inputParaList_'+(Number(i)+1)+'" class="paraList">'+
								'</datalist>'+
			    			'</div>'+
			   			'</div>'; 
			   			$("#addPara").before(str);
			   			$("#inputParaList_"+(Number(i)+1)).append($("#inputParaList_1").html());
		});
		$("#addDemand").click(function(e){
			var i = $("#addDemand").prev().attr("id").split("_")[1];
        	var str = '<div class="form-group" id="demandDiv_'+(Number(i)+1)+'">'+
			  				'<label for="demand_'+(Number(i)+1)+'" class="col-sm-3 control-label">需求编号'+(Number(i)+1)+'</label>'+
			  				'<div class="col-md-6">'+
			  					'<input type="text" class="form-control demand" id="demand_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="demandList_'+(Number(i)+1)+'">'+
			  					'<datalist id="demandList_'+(Number(i)+1)+'" class="demandList">'+
								'</datalist>'+
			    			'</div>'+
			   			'</div>'; 
			   			$("#addDemand").before(str);
			   			$("#demandList_"+(Number(i)+1)).append($("#demandList_1").html());
		});
		 function getDemandList(proId){
	        	$.ajax({
	        		type : "POST",
					url : root + "/demand/getDemandListbyLevel",
					data : JSON.stringify({level:6,projectId:proId}),
					success : function(data) {
						$("#demandList_1").empty();
						if (data.errorcode == 1000) {
							var demands = data.result.info.demands;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<demands.length;i++){
									str += '<option value="'+demands[i].id+'" label="'+demands[i].demandName+'" />';
								}
								$("#demandList_1").append(str);
							}
						}
					}
	        	});
	        }
        var a = function(p) {
			page = Number(p);
			getEnvExtendedTypeList(); 
			
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
		 function getEnvBasicTypeCatalog(proId){
			 
			 $(".paraList").empty();		
			 $.ajax({
	        		type : "POST",
					url : root + "/samlType/getSamlTypeCatalog",
					data : JSON.stringify({projectId:Number(proId),level:1}),
					success : function(data) {
						
						if (data.errorcode == 1000) {
							var envBasicTypes = data.result.info.samlType;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<envBasicTypes.length;i++){
									str += '<option value="'+envBasicTypes[i].id+'" label="'+envBasicTypes[i].typeName+'" />';
								}
								$(".paraList").append(str);
								
							}
						}
					}
	        	});
	        }
        function getEnvExtendedTypeList(){
        	$.ajax({
        		type : "POST",
				url : root + "/samlType/getSamlTypeList",
				data : JSON.stringify({limit:limit,page:page,keyword:keyword,level:2}),
				success : function(data) {
					$("tbody", $("#EnvExtendedTypeList")).empty();
					if (data.errorcode == 1000) {
						var envExtendedTypes = data.result.info.samlType;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<envExtendedTypes.length;i++){
								var name = envExtendedTypes[i].typeName.replace(/</,"&lt;").replace(/>/,"&gt;");
								var exp = envExtendedTypes[i].typeExpression.replace(/</,"&lt;").replace(/>/,"&gt;");
								var temp = '<td bid="'+envExtendedTypes[i].id+'"><button class="editEnvExtendedType btn btn-xs btn-info" style="margin-right:5px;"><i class="fa fa-edit "></i></button>'+
								'<button class="deleteEnvExtendedType btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-remove"></i></button>';
								var str = '<tr><td class="center">'+((page-1)*limit+i+1)+'</td><td>'+name+'</td>'+
								'<td style="">'+exp+'</td><td>'+envExtendedTypes[i].typeRules+'</td><td>'+envExtendedTypes[i].projectName+'</td>'+temp+'</tr>';
								$("tbody",$("#EnvExtendedTypeList")).append(str);
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
							
						
							$(".editEnvExtendedType").click(function(){
								$("#myModalLabel").html('编辑类型');
								var bid = $(this).parent().attr("bid");
								$("#bid").val(bid);
								$.ajax({
									type:"POST",
									url:root+"/samlType/getSamlTypeInfo",
									data:JSON.stringify({id:bid,level:2}),
									success:function(data1){
										if (data1.errorcode == 1000){
											var EnvExtendedType = data1.result.info.samlType;
											var total = data1.result.info.total;
											var para = data1.result.info.para;
											var demands = data1.result.info.demand;
											$("#name").val(EnvExtendedType.typeName);
											$("#remark").val(EnvExtendedType.remark);
											$("#type").val(EnvExtendedType.typeClassification);
											$("#project").val(EnvExtendedType.projectId);
											$("#outputPara").val(para[total-1]);
											getEnvBasicTypeCatalog(EnvExtendedType.projectId);
											getDemandList(EnvExtendedType.projectId);
											for(var i=0;i<total-1;i++){
												if(i!=0){
													console.log("!!!!"+i);
													var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
									  				'<label for="inputPara" class="col-sm-3 control-label">输入参数类型'+(Number(i)+1)+'</label>'+
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
											for(var i=0;i<demands.length;i++){
												if(i!=0){
													console.log("!!!!"+i);
													var str = '<div class="form-group" id="demandDiv_'+(Number(i)+1)+'">'+
									  				'<label for="demand_'+(Number(i)+1)+'" class="col-sm-3 control-label">需求编号'+(Number(i)+1)+'</label>'+
									  				'<div class="col-md-6">'+
									  					'<input type="text" class="form-control demand" id="demand_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="demandList_'+(Number(i)+1)+'">'+
									  					'<datalist id="demandList_'+(Number(i)+1)+'" class="demandList">'+
														'</datalist>'+
									    			'</div>'+
									   				'</div>'; 
									   				$("#addDemand").before(str);
									   				$("#demandList_"+(Number(i)+1)).append($("#demandList_1").html());
									   			
											}
												$("#demand_"+(i+1)).val(demands[i].demandId);
											
										}
											$("#addEnvExtendedType").html("编辑");
											$("#myModal").modal("show");;
										}
									},
									datatype:"json"
								});
							});
							$(".deleteEnvExtendedType").click(function(){
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
												data:JSON.stringify({id:bid,level:2}),
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
							$("tbody",$("#EnvExtendedTypeList")).append('<tr><td colspan="6">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
					}
				}
        	});
        }
        
        $("#addEnvExtendedTypePanel").click(function(){
			$("#myModalLabel").html("添加映射类型");
			$("#bid").val("0");
			$("#name").val("");
			$("#type").val("0");
			$("#project").val("");
			$("#remark").val("");
			$(".demand").val("");
			$("#outputPara").val("");
			$(".inputPara").val("");
			$(".paraList").empty();
			$("#addEnvExtendedType").html("添加");
			$("#myModal").modal("show");;
		});  
       function addEnvExtendedTypeInfo(){
    	   var bid = $("#bid").val();
			var name = $("#name").val();
			var type = $("#type").val();
			var project = $("#project").val();
			var remark = $("#remark").val();
			var outputPara = $("#outputPara").val();
			var list = $(".inputPara");
			var inputPara=[];
			for(var i=0;i<list.length;i++){
				if($(list[i]).val()!=0&&$(list[i]).val()!=null&&$(list[i]).val()!="")
					inputPara.push($(list[i]).val());
			}
			var list2 = $(".demand");
			var demand=[];
			for(var i=0;i<list2.length;i++){
				if($(list2[i]).val()!=0&&$(list2[i]).val()!=null&&$(list2[i]).val()!="")
					demand.push($(list2[i]).val());
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
						typeClassification: type,
						demand:demand,
						remark: remark,
						outputPara : outputPara,
						inputPara: inputPara,
						level:2
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
						level:2
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