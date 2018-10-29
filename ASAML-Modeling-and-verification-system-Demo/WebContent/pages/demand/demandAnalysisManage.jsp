<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>用户需求整理</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/bootstrap-treeview.css">
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
						  <li class="active">用户需求整理</li>
						</ol>
		    		</div>
		    		<div class="row">
						<div class="col-md-4">
							<h4><i class="fa fa-question-circle"></i>&nbsp;请先在右侧选择项目，再进行后续操作<span style="font-size:14px;float:right;font-weight:bold;">&nbsp;项目列表</span></h4>
									
						</div>
						<div class="col-md-5" >
									<select id="pList" class="form-control">
										<option value="0">&nbsp;</option>
									</select>
						</div>			
					</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12">
							
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;请选择树形菜单中的模块，并按照该模块的需求描述进行分析，为其添加子模块。</h4>
								
								
							</div>
							<div id="demandTreeview" class="treeview"></div>
							
			        	</div>
	    			</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12" style="display:none;">
							
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;模块需求描述</h4>
								
								
							</div>
							<div id="demandInfo" class="demandInfo"></div>
			        	</div>
	    			</div>
		    		<div class="row">
		    			<div class="col-md-9 col-sm-7 col-xs-12">
			    			<div class="headline">
									<h4><i class="fa fa-table"></i>&nbsp;子模块列表  <button  class="btn btn-md btn-primary pull-right"  id="addComponentPanel" style="margin-left:5%;" disabled="disabled">添加子模块</button></h4>
							</div>
							<div class="row" style="margin-left:10px;margin-top:20px;">
									<div class="table-responsive">
											<table id="componentList" class="table table-bordered">
												<thead>
													<tr>
														<th class="center" >序号</th>
														<th>编号</th>
														<th>名称</th>
														<th>所属模块</th>
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
		        <h4 class="modal-title" id="myModalLabel">添加子模块</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" >
		      		<fieldset>
		      			<div class="form-group">
						  	<label for="project" class="col-sm-3 control-label">项目编号<span style="color: red;">*</span></label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control" id="project" placeholder="请输入项目编号" list="projectList">
						  		<datalist id="projectList">
								</datalist>
						    	<span id="project_error" style="display: none;color: red;font-size:10px;"></span>
						    </div>
					    </div>
		    			<div class="form-group">
						  	<label for="name" class="col-sm-3 control-label">子模块名称<span style="color: red;">*</span></label>
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
						    <label for="did" class="col-sm-3 control-label"></label>
						    <div class="col-md-6">
						    	<input class="form-control" id="did" ></input>
						    </div>
					    </div>
					    <div class="form-group" style="display:none;">
						    <label for="parentid" class="col-sm-3 control-label"></label>
						    <div class="col-md-6">
						    	<input class="form-control" id="parentid" ></input>
						    </div>
					    </div>
					     <div class="form-group" style="display:none;">
						    <label for="level" class="col-sm-3 control-label"></label>
						    <div class="col-md-6">
						    	<input class="form-control" id="level" value="4"></input>
						    </div>
					    </div>
					    
					</fieldset>
					
				    
				</form>
			  </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="addComponentInfo()" id="addComponent">添加</button>
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
		<script src="<%=request.getContextPath() %>/static/js/bootstrap-treeview.js"></script>
		<script type="text/javascript">
        $("#DemandAnalysisBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
		var keyword = "";
		var parentName = ""; 
		var proid = 0; //用户记录用户初始选择的项目id，以便进行后续操作
		getProjectCatalog();
        $("#addComponentPanel").click(function(){
			$("#myModalLabel").html("添加子模块");
			$("#did").val("0");
			$("#name").val("");
			$("#project").val("");
			$("#remark").val("");
			$("#addComponent").html("添加");
			$("#myModal").modal("show");
		});
        $("#pList").change(function(){
     	   	proid = $("#pList").val();
     	  	$('#demandTreeview').treeview('remove');
   	   		$("tbody",$("#componentList")).empty();
   			$("tbody",$("#componentList")).append('<tr><td colspan="6">暂无记录！</td></tr>');
			$('#setpage').parent().hide();
			$("#demandInfo").parent().hide();
     	  	if(proid!=0)
     		   	getDemandList();
    	    
        });
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
								var str2='';
								for(var i=0;i<projects.length;i++){
									str += '<option value="'+projects[i].id+'" label="'+projects[i].projectName+'" />';
									str2 += '<option value="'+projects[i].id+'">'+projects[i].projectName+'</option>';
								}
								$("#projectList").append(str);
								$("#pList").append(str2);
							}
						}
					}
	        	});
	        }
        //获取项目列表
        function getDemandList(){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getDemandList",
				data : JSON.stringify({projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						var demands = data.result.info.demandInfos;
						var total = data.result.info.total;
						if(total){
							var data=[];
							for(var i=0;i<demands.length;i++){
								var level1data = {text: demands[i].demandName, nodeId:demands[i].id ,state:{checked:false,disabled:false,expanded:false,selected:false}};
								var demandItems = demands[i].items;
								if(demandItems.length)
									level1data = {text: demands[i].demandName , nodeId:demands[i].id,state:{checked:false,disabled:false,expanded:true,selected:false},nodes:[],};
								for(var j = 0;j<demandItems.length;j++){
									var demandItemsInfo= demandItems[j].items;
									var level2data = {text: demandItems[j].demandName , nodeId: demandItems[j].id,state:{checked:false,disabled:false,expanded:false,selected:false}};
									if(demandItemsInfo.length)
										level2data = {text: demandItems[j].demandName , nodeId: demandItems[j].id,state:{checked:false,disabled:false,expanded:true,selected:false},nodes:[]};
									for(var k = 0;k<demandItemsInfo.length;k++){
										var level3data = {text: demandItemsInfo[k].demandName, nodeId: demandItemsInfo[k].id,state:{checked:false,disabled:false,expanded:false,selected:false}};
										if(k==demandItemsInfo.length-1){
											var level3data = {text: demandItemsInfo[k].demandName, nodeId: demandItemsInfo[k].id,state:{checked:false,disabled:false,expanded:false,selected:true}};
											 $("#parentid").val(demandItemsInfo[k].id); 
											$("#addComponentPanel").attr("disabled",false);
											$("#demandInfo").html('<p>'+demandItemsInfo[k].demandInfo+'</p>');
											$("#demandInfo").parent().show();
											parentName = demandItemsInfo[k].demandName;
											getComponentList(demandItemsInfo[k].id);
										}
											
										level2data["nodes"].push(level3data);
									}
									level1data["nodes"].push(level2data);
								}
								data.push(level1data);
								
							}
							
							
							$('#demandTreeview').treeview({
						          color: "#428bca",
						          expandIcon: 'glyphicon glyphicon-chevron-right',
						          collapseIcon: 'glyphicon glyphicon-chevron-down',
						          nodeIcon: 'glyphicon glyphicon-bookmark',
						          data: data,
						          levels: 3,
						          onNodeSelected: function (event, data) {
						        	  $("#parentid").val(data.Id);
						        	  $.ajax({
											type:"POST",
											url:root+"/demand/getDemandInfo",
											data:JSON.stringify({id:Number(data.Id)}),
											success:function(data1){
												if (data1.errorcode == 1000){
													var demand = data1.result.info.demand;
													$("#demandInfo").html('<p>'+demand.demandInfo+'</p>');
													$("#demandInfo").parent().show();
													parentName = demand.demandName;
											        	  
												}
											},
											datatype:"json"
										});  
						        	 
						        	  $("#addComponentPanel").attr("disabled",false);
						        	  getComponentList(data.Id);
				                  }
						     });
							
						}
						
					}
				}
        	});
        }
        
        function getComponentList(id){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getChildDemandListbyId",
				data : JSON.stringify({id:id,limit:limit,page:page,keyword:keyword}),
				success : function(data) {
					$("tbody",$("#componentList")).empty();
					if (data.errorcode == 1000) {
						var components = data.result.info.demands;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<components.length;i++){
								var temp = '<td cid="'+components[i].id+'"><button class="editComponent btn btn-xs btn-info" style="margin-right:5px;"><i class="fa fa-edit "></i></button>'+
								'<button class="deleteComponent btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-remove"></i></button>';
								
								var str = '<tr><td class="center">'+((page-1)*limit+i+1)+'</td><td>'+components[i].id+'</td>'+
								'<td>'+components[i].demandName+'</td><td>'+parentName+'</td><td>'+getFormatDateByLong(components[i].addTime)+'</td>'+
								temp+'</tr>';
								$("tbody",$("#componentList")).append(str);
							}
							var a = function(p) {
								page = Number(p);
								getComponentList(id); 
								
							};
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
							$('#setpage').parent().show();
						
							$(".editComponent").click(function(){
								$("#myModalLabel").html('编辑子模块');
								$("#name_error").hide();
								var cid = $(this).parent().attr("cid");
								$("#did").val(cid);
								$.ajax({
									type:"POST",
									url:root+"/demand/getDemandInfo",
									data:JSON.stringify({id:cid}),
									success:function(data1){
										if (data1.errorcode == 1000){
											var demand = data1.result.info.demand;
											$("#name").val(demand.demandName);
											$("#remark").val(demand.demandInfo);
											$("#level").val(demand.demandLevel);
											$("#parentid").val(demand.parentId);
											$("#project").val(demand.projectId);
											$("#addComponent").html("编辑");
											$("#myModal").modal("show");;
										}
									},
									datatype:"json"
								});
							});
							$(".deleteComponent").click(function(){
								var cid = $(this).parent().attr("cid");
								bootbox.confirm({  
							        buttons: {  
							            confirm: {  
							                label: '确认',  
							            },  
							            cancel: {  
							                label: '取消',  
							            }  
							        },  
							        message: '确定要删除该子模块吗？',  
							        callback: function(result) {  
							            if(result) {  
							            	$.ajax({
												type:"POST",
												url:root+"/demand/deletedemand",
												data:JSON.stringify({id:cid}),
												success:function(data2){
													if (data2.errorcode == 1000){
														window.location.reload();
													}
												},
												datatype:"json"
											});
							            }
							        },  
							        title: "删除子模块",  
							        });
							});
						}else{
							$("tbody",$("#componentList")).append('<tr><td colspan="6">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
					}
				}
        	});
        }
     // 添加/编辑项目
		function addComponentInfo() {
			var id = $("#did").val();
			var level = $("#level").val();
			var parentid = $("#parentid").val();
			var name = $("#name").val();
			var project = $("#project").val();
			var remark = $("#remark").val();
			var has_error = 0;
			if(!name){
				has_error = 1;
				$("#name_error").html("组件名称不能为空！");
				$("#name_error").show();
			}else{
				$("#name_error").html("");
				$("#name_error").hide();
			}
			if(has_error){
				return;
			}
			
			if(id=="0"){
				$.ajax({
					type : "POST",
					url : root + "/demand/addDemand",
					data : JSON.stringify({
						demandName : name,
						demandInfo: remark,
						demandLevel:level,
						parentId:parentid,
						projectId:project
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
					url : root + "/demand/editDemand",
					data : JSON.stringify({
						id: id,
						demandName : name,
						demandInfo: remark,
						demandLevel:level,
						parentId:parentid,
						projectId:project
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