<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>基于体系结构的分析</title>
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
						  <li class="active">基于体系结构的分析</li>
						</ol>
		    		</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12">
							<div class="row">
								<div class="col-md-6">
									<h4> <i class="fa fa-question-circle"></i>&nbsp;请先在右侧选择项目，再进行后续操作<span style="font-size:16px;float:right;font-weight:bold;">&nbsp;项目列表</span></h4>
									
								</div>
								<div class="col-md-6" >
									<select id="pList" class="form-control">
										<option value="0">&nbsp;</option>
									</select>
								</div>			
							</div>
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;请选择树形菜单中的子模块，并按照体系结构需求添加功能与方法。</h4>
								
								
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
									<h4><i class="fa fa-table"></i>&nbsp;功能列表  <button  class="btn btn-md btn-primary pull-right"  id="addComponentPanel" style="margin-left:5%;" disabled="disabled">添加功能</button></h4>
							</div>
							<div class="row" style="margin-left:10px;margin-top:20px;">
									<div class="table-responsive">
											<table id="componentList" class="table table-bordered">
												<thead>
													<tr>
														<th class="center" >序号</th>
														<th>编号</th>
														<th>名称</th>
														<th>所属子模块</th>
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
		    		<div class="row" >
						<div class="col-md-12 col-sm-7 col-xs-12">
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;上传调用图</h4>
							</div>
							<div class="row">
								<div class="col-md-8 col-sm-7 col-xs-12">
									<form class="form-horizontal" id="myForm1" action="<%=request.getContextPath() %>/fileHandler/uploadFile?type=1" method="POST" enctype="multipart/form-data">
								    	<div class="form-group">
										    <label for="p_file1" class="col-sm-3 control-label">请上传调用图的XML文件</label>
										    <div class="col-md-9">
										    	<input type="file" class="form-control" id="p_file1"  size="45" name="fileToUpload" onchange="upload(1);" ></input>
												<input id="filepath1" style="display:none" value=""></input>
												<br/>
												<span id="file1_error" style="color: red;display: none; font-size: 10pt;"></span>
										    </div>
									    
								   		</div>
									</form>
								</div>
								
							</div> 
							<div class="row">
							<div class="col-md-8 col-sm-7 col-xs-12">
								<form class="form-horizontal" id="myForm2" action="<%=request.getContextPath() %>/fileHandler/uploadFile?type=2" method="POST" enctype="multipart/form-data">
								    <div class="form-group">
									    <label for="p_file2" class="col-sm-3 control-label">请上传EXCEL文件</label>
									    <div class="col-md-9">
									    	<input type="file" class="form-control" id="p_file2"  size="45" name="fileToUpload" onchange="upload(2);" ></input>
											<input id="filepath2" style="display:none" value=""></input>
											<br/>
											<span id="file2_error" style="color: red;display: none; font-size: 10pt;"></span>
									    </div>
									    
								    </div>
								</form>
							</div>
							<div class="col-md-1" style="margin-left:5px;">
								<div >
									 <button  class="btn btn-md btn-primary pull-right"  id="submitEXCEL"  disabled="disabled">确定</button> 
								</div>
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
		        <h4 class="modal-title" id="myModalLabel">添加功能</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-horizontal" >
		      		<fieldset>
		      			
					   <div class="form-group" id="paraDiv_1">
						  	<label for="inputPara" class="col-sm-3 control-label">结点编号1</label>
						  	<div class="col-md-6">
						  		<input type="text" class="form-control inputPara" id="inputPara_1" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_1">
						  		<datalist id="inputParaList_1" class="paraList">
								</datalist>
						    </div>
						</div>
						<a class="btn btn-info" type="button"  id="addPara"><i class="fa fa-plus"></i></a>
					    
		    			<div class="form-group" style= "    margin-top: -30px;">
						  	<label for="name" class="col-sm-3 control-label">名称<span style="color: red;">*</span></label>
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
						    	<input class="form-control" id="level" value="5"></input>
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
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" style="top:2em;">
		  <div class="modal-dialog" role="document" style="width: 800px;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel2">方法管理</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="row">
		    			<div class="col-md-11 col-sm-7 col-xs-12">
			    			<div class="headline">
									<h4><i class="fa fa-table"></i>&nbsp;方法列表</h4>
							</div>
							<div class="row" style="margin-left:10px;margin-top:20px;">
									<div class="table-responsive">
											<table id="methodList" class="table table-bordered">
												<thead>
													<tr>
														<th class="center" >序号</th>
														<th>编号</th>
														<th>名称</th>
														<th>父节点编号</th>
														<th>父节点名称</th>
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
							        	<span id="setpage2" class="table_setpage"></span>
						        	</div>
					        </div>
		    			</div>
		    		</div>
			  </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
		<script src="<%=request.getContextPath() %>/static/js/jquery-form.js"></script>
		<script type="text/javascript">
        $("#DemandBasedStrBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
        var methodLimit = 10;
        var methodPage = 1;
		var keyword = "";
		var proid = 0; //用户记录用户初始选择的项目id，以便进行后续操作
		var parentName = ""; 
		getProjectCatalog();
		$("#addPara").click(function(e){
			var i = $("#addPara").prev().attr("id").split("_")[1];
        	var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
			  				'<label for="inputPara" class="col-sm-3 control-label">结点编号'+(Number(i)+1)+'</label>'+
			  				'<div class="col-md-6">'+
			  					'<input type="text" class="form-control inputPara" id="inputPara_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_'+(Number(i)+1)+'">'+
			  					'<datalist id="inputParaList_'+(Number(i)+1)+'" class="paraList">'+
								'</datalist>'+
			    			'</div>'+
			   			'</div>'; 
			   			$("#addPara").before(str);
			   			$("#inputParaList_"+(Number(i)+1)).append($("#inputParaList_1").html());
		});
        $("#addComponentPanel").click(function(){
			$("#myModalLabel").html("添加功能");
			$("#did").val("0");
			$("#name").val("");
			$("#remark").val("");
			$(".inputPara").val("");
			$(".inputPara").parent().parent().remove();
			var str = '<div class="form-group" id="paraDiv_1">'+
				'<label for="inputPara" class="col-sm-3 control-label">结点编号1</label>'+
				'<div class="col-md-6">'+
					'<input type="text" class="form-control inputPara" id="inputPara_1" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_1">'+
					'<datalist id="inputParaList_1" class="paraList">'+
				'</datalist>'+
			'</div>'+
			'</div>'; 
			$("#addPara").before(str);
			getInvokableNodeCatalog();
			
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
			if(proid!=0){
				getDemandList();
				getResultInfo();
			}
			
     			
        });
        
        $("#submitEXCEL").click(function(){
        	path1 = $("#filepath1").val();
        	path2 = $("#filepath2").val();
        	$.ajax({
        		type : "POST",
				url : root + "/verify/generatePath",
				data : JSON.stringify({XMLpath:path1,EXCELpath: path2,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						bootbox.alert({  
		    	            buttons: {  
		    	               ok: {  
		    	                    label: '确认',  
		    	                }  
		    	            },  
		    	            callback:function(){
		    	            	
		    	            },
		    	            message: '表格已读入数据库，请进行下一步！', 
		    	            title: "表格读入提醒",  
		    	        });
					}
				}
        	});
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
								var str2='';
								for(var i=0;i<projects.length;i++){
									str2 += '<option value="'+projects[i].id+'">'+projects[i].projectName+'</option>';
								}
								$("#pList").append(str2);
							}
						}
					}
	        	});
	        }
		 function getResultInfo(){
			 $.ajax({
	        		type : "POST",
					url : root + "/verify/getResultInfo",
					data : JSON.stringify({projectId:proid}),
					success : function(data) {
						if (data.errorcode == 1000) {
							console.log(data.result.info.xmlPath);
							if(data.result.info.evaluationExcel!=""&&data.result.info.evaluationExcel!=null){
								$("#file2_error").html("XML、EXCEL文件已存在，如需修改，请重新上传两个文件！");
								$("#file2_error").show();
								
							}
							}
						}
					
	        	});
		 }
		 function getInvokableNodeCatalog(){
			 $(".paraList").empty();	
	        	$.ajax({
	        		type : "POST",
					url : root + "/demand/getInvokableNodeCatalog",
					data : JSON.stringify({projectId:proid,type:1}),
					success : function(data) {
						$("#nodeIdList").empty();
						if (data.errorcode == 1000) {
							var invokableNodes = data.result.info.invokableNode;
							var total = data.result.info.total;
							if(total){
								var str = '';
								for(var i=0;i<invokableNodes.length;i++){
									str += '<option value="'+invokableNodes[i].id+'" label="'+invokableNodes[i].nodeId+':'+invokableNodes[i].nodeName+'" />';
									}
								$(".paraList").append(str);
								
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
								var level1data = {icon:'fa fa-globe',text: demands[i].demandName, nodeId:demands[i].id ,state:{checked:false,disabled:false,expanded:false,selected:false}};
								var demandItems = demands[i].items;
								if(demandItems.length)
									level1data = {icon:'fa fa-globe',text: demands[i].demandName , nodeId:demands[i].id,state:{checked:false,disabled:false,expanded:true,selected:false},nodes:[],};
								for(var j = 0;j<demandItems.length;j++){
									var demandItemsInfo= demandItems[j].items;
									var level2data = {icon:'fa fa-cubes',text: demandItems[j].demandName , nodeId: demandItems[j].id,state:{checked:false,disabled:false,expanded:false,selected:false}};
									if(demandItemsInfo.length)
										level2data = {icon:'fa fa-cubes',text: demandItems[j].demandName , nodeId: demandItems[j].id,state:{checked:false,disabled:false,expanded:true,selected:false},nodes:[]};
									for(var k = 0;k<demandItemsInfo.length;k++){
										var level3data = {icon:'fa fa-cube',text: demandItemsInfo[k].demandName, nodeId: demandItemsInfo[k].id,state:{checked:false,disabled:false,expanded:false,selected:false}};
										var demandItemsInfoItems= demandItemsInfo[k].items;
										if(demandItemsInfoItems.length)
											level3data = {icon:'fa fa-cube',text: demandItemsInfo[k].demandName, nodeId: demandItemsInfo[k].id,state:{checked:false,disabled:false,expanded:true,selected:false},nodes:[]};
										for(var t=0;t<demandItemsInfoItems.length;t++){
												var level4data = {icon:'fa fa-asterisk',text: demandItemsInfoItems[t].demandName, nodeId: demandItemsInfoItems[t].id,state:{checked:false,disabled:false,expanded:false,selected:false}};
												if(t==demandItemsInfoItems.length-1){
													level4data = {icon:'fa fa-asterisk',text: demandItemsInfoItems[t].demandName, nodeId: demandItemsInfoItems[t].id,state:{checked:false,disabled:false,expanded:false,selected:true}};
													$("#parentid").val(demandItemsInfoItems[t].id); 
													parentName = demandItemsInfoItems[t].demandName;
													$("#demandInfo").html('<p>'+demandItemsInfoItems[t].demandInfo+'</p>');
													$("#demandInfo").parent().show();
													$("#addComponentPanel").attr("disabled",false);
													getComponentList(demandItemsInfoItems[t].id);
												}
												level3data["nodes"].push(level4data);
											  
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
						          data: data,
						          levels: 4,
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
								'<button class="deleteComponent btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-remove"></i></button>'+
								'<button class="manageMethod btn btn-xs btn-warning" style="margin-right:5px;">方法管理</button>';
								
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
							
							$(".manageMethod").click(function(){
								var cid = $(this).parent().attr("cid");
								getMethodList(cid);
									
								});
							$(".editComponent").click(function(){
								$("#myModalLabel").html('编辑功能');
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
											var total = data1.result.info.total;
											var para = data1.result.info.para;
											
											$("#name").val(demand.demandName);
											$("#remark").val(demand.demandInfo);
											$("#level").val(demand.demandLevel);
											$("#parentid").val(demand.parentId);
											$(".inputPara").parent().parent().remove();
											for(var i=0;i<total;i++){
												
													console.log("!!!!"+i);
													var str = '<div class="form-group" id="paraDiv_'+(Number(i)+1)+'">'+
									  				'<label for="inputPara" class="col-sm-3 control-label">结点编号'+(Number(i)+1)+'</label>'+
									  				'<div class="col-md-6">'+
									  					'<input type="text" class="form-control inputPara" id="inputPara_'+(Number(i)+1)+'" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList_'+(Number(i)+1)+'">'+
									  					'<datalist id="inputParaList_'+(Number(i)+1)+'" class="paraList">'+
														'</datalist>'+
									    			'</div>'+
									   			'</div>'; 
									   			$("#addPara").before(str);
									   			console.log($("#inputParaList_1").html());
									   			$("#inputParaList_"+(Number(i)+1)).append($("#inputParaList_1").html());
												
												$("#inputPara_"+(i+1)).val(para[i]);
												
											}
											getInvokableNodeCatalog();
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
														var se = $('#demandTreeview').treeview("getSelected");
														getComponentList(Number(se[0].Id));
														$("#myModal").modal("hide");;
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
        //获取方法列表
        function getMethodList(cid){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getInvokableNodeList",
				data : JSON.stringify({parentId:cid,projectId:proid,type:2,limit:methodLimit,page:methodPage,keyword:keyword}),
				success : function(data) {
					$("tbody",$("#methodList")).empty();
					if (data.errorcode == 1000) {
						var invokableNodes = data.result.info.invokableNode;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<invokableNodes.length;i++){
								var temp = '<td nid="'+invokableNodes[i].id+'" ><button class="addMethod btn btn-xs btn-info" style="margin-right:5px;"><i class="fa fa-plus"></i></button></td>';
								if(invokableNodes[i].isSelected==1)
									temp = '<td nid="'+invokableNodes[i].id+'" did="'+invokableNodes[i].demandId+'" dnmid="'+invokableNodes[i].dnmId+'"><button class="deleteMethod btn btn-xs btn-danger" style="margin-right:5px;"><i class="fa fa-minus-circle "></i></button></td>';
								var str = '<tr><td class="center">'+((methodPage-1)*methodLimit+i+1)+'</td><td>'+invokableNodes[i].nodeId+'</td>'+
								'<td>'+invokableNodes[i].nodeName+'</td><td>'+invokableNodes[i].parentNodeId+'</td><td>'+invokableNodes[i].parentNodeName+'</td>'+
								temp+'</tr>';
								$("tbody",$("#methodList")).append(str);
							}
							var b = function(p) {
								methodPage = Number(p);
								getMethodList(cid); 
								
							};
							var total_page=Math.ceil(total/methodLimit);
							var opt={
									selectedPage:methodPage,
									total:total_page,
									pageLength:7,
									showLocation:4,
									callback:b
									};
							
							$('#setpage2').Pagination(opt);
							$('#setpage2').parent().show();	
							$(".addMethod").click(function(){
								var nid = $(this).parent().attr("nid");
					 			$.ajax({
									type:"POST",
									url:root+"/demand/addDemandByNode",
									data:JSON.stringify({nodeId:nid,parentId:cid,projectId:proid}),
									success:function(data2){
										if (data2.errorcode == 1000){
											getMethodList(cid);
										}
									},
									datatype:"json"
								});
				          
							       
							});
							$(".deleteMethod").click(function(){
								var did = $(this).parent().attr("did");
								var dnmid = $(this).parent().attr("dnmid");
				            	$.ajax({
									type:"POST",
									url:root+"/demand/deletedemandByNode",
									data:JSON.stringify({demandId:did,dnmId:dnmid}),
									success:function(data2){
										if (data2.errorcode == 1000){
											getMethodList(cid);
										}
									},
									datatype:"json"
								});
							           
							});
						}else{
							$("tbody",$("#methodList")).append('<tr><td colspan="4">暂无记录！</td></tr>');
							$('#setpage2').parent().hide();
						}
							$("#myModalLabel2").html("方法管理");
							$("#myModal2").modal("show");
						}
					}
				
        	});
        }
     // 添加/编辑组件
		function addComponentInfo() {
			var id = $("#did").val();
			var level = $("#level").val();
			var parentid = $("#parentid").val();
			var name = $("#name").val();
			var remark = $("#remark").val();
			var list = $(".inputPara");
			var inputPara=[];
			for(var i=0;i<list.length;i++){
				if($(list[i]).val()!=0&&$(list[i]).val()!=null&&$(list[i]).val()!="")
					inputPara.push($(list[i]).val());
			}
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
						projectId:proid,
						inputPara: inputPara
					}),
					success : function(data) {
						if(typeof data == "string"){
	    					data = JSON.parse(data);
	    				}
						if (data.errorcode == 1000) {
							var se = $('#demandTreeview').treeview("getSelected");
							getComponentList(Number(se[0].Id));
							$("#myModal").modal("hide");;
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
						projectId:proid,
						inputPara: inputPara
					}),
					success : function(data) {
						if(typeof data == "string"){
	    					data = JSON.parse(data);
	    				}
						if (data.errorcode == 1000) {
							var se = $('#demandTreeview').treeview("getSelected");
							getComponentList(Number(se[0].Id));
							$("#myModal").modal("hide");;
						}else {
							alert("编辑失败！");
						}
					},
					datatype : "json"
				});
			}
		} 
		   function upload(num){
				var options = {
						success: function(data) {
								if(data.errorcode==1000){
						        	$("#filepath"+num).val(data.result.info.path);
							        if($("#p_file").val()!=""&&$("#p_file2").val()!="")
							        	$("#submitEXCEL").attr("disabled",false);
						     	  	
								}else if(data.errorcode==1025){
						        	$("#file_error").text("文件太大！");
									$("#file_error").css("display", "block");
						        }else if(data.errorcode==1024){
						        	$("#file_error").text("文件类型错误！");
									$("#file_error").css("display", "block");
						        }else{
						        	alert("上传失败！");
						        }
							}
						 };
				$('#myForm'+num).ajaxSubmit(options);
		}
        </script>
</body>
</html>