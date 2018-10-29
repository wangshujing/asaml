<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>流程化验证</title>
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
						  <li class="active">流程化验证</li>
						</ol>
		    		</div>
		    		<div class="row">
						<div class="col-md-9 col-sm-7 col-xs-12">
							<div class="headline">
								<h4><i class="fa fa-question-circle"></i>&nbsp;流程验证用于对功能流程设计通过求值进行验证：1）前端接口类型与后端接口类型之间的可调用关系，2）接口对象求值后的结果是否正确。</h4>
								<p>方法可调用关系图验证方式：1.选择任意两个接口类型，点击验证以验证其可调用关系；2.选择一条可达路径，点击验证可调用关系</p>
								<p>接口对象求值后结果确验证方式：上传输入输出参数类型及实参EXCEL文件，点击验证以验证该EXCEL中所有接口的求值，选择一条可达路径，点击验证λ项求值</p>
							</div>
						</div>
					
					</div>
					<br />
		    		<div class="row">
						<div class="col-md-5">
							<h4><i class="fa fa-star"></i>&nbsp;请先在右侧选择项目，再进行后续操作</h4>
									
						</div>
						<div class="col-md-4" >
									<select id="pList" class="form-control">
										<option value="0">&nbsp;</option>
									</select>
						</div>			
					</div>
					
					
					<br />
				 	<!-- <div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12" >
							
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;待验证公式<button  class="btn btn-md btn-primary pull-right"  id="verifyAll" style="margin-left:5%;" onclick="verifyDemandOrRelation(0)" disabled="disabled">验证全部性质</button></h4>
							</div>
							<div id="vefiryExp" class="demandInfo">
								<p style='text-align:center;'>将可调用关系待验证公式记为P<sub>b1</sub>，其公式定义如下，<br />
									P<sub>b1</sub> = R<sub>s112</sub>∧…∧R<sub>s121</sub>∧R<sub>s25</sub>∧…∧R<sub>s27</sub>∧R<sub>s45</sub>∧…∧R<sub>s59</sub>
								</p>
								<p style='text-align:center;'>将接口对象求值验证公式记为P<sub>b2</sub>，其公式定义如下，<br />
									P<sub>b2</sub> = {P<sub>k</sub>|eval(<i style="font-family:'lucida calligraphy';">L</i><sub>ij</sub>)=E<sub>i</sub>},其中1&lt;=i&lt;=63,1&lt;=j&lt;=i,k=1,2,...
								</p>
							</div>
			        	</div>
	    			</div>  -->
	    			<div class="row">
	    				<div class="col-md-8 col-sm-7 col-xs-12" >
							
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;选择并验证接口间调用关系</h4>
							</div>
							 <div  >
							  	<label for="inputPara" class="col-sm-1 control-label" style="margin-top: 5px;">接口1</label>
							  	<div class="col-md-5">
							  		<input type="text" class="form-control inputPara" id="inputPara" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList1">
							  		<datalist id="inputParaList1" class="paraList">
									</datalist>
						    	</div>
							</div>
							<div class="" >
							  	<label for="inputPara2" class="col-sm-1 control-label" style="margin-top: 5px;">接口2</label>
							  	<div class="col-md-5">
							  		<input type="text" class="form-control inputPara" id="inputPara2" placeholder="输入关键字或点击右侧箭头选择" list="inputParaList2">
							  		<datalist id="inputParaList2" class="paraList">
									</datalist>
						    	</div>
							</div>
			        	</div>
			        	<div class="col-md-1" style="margin-top: 39px;margin-left:5px;">
							<div >
								 <button  class="btn btn-md btn-primary pull-right"  id="verifyInvocation"  >验证可调用关系</button> 
							</div>
						</div>
	    			</div>
	    			<div class="row" >
						<div class="col-md-12 col-sm-7 col-xs-12">
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;上传输入输出参数EXCEL</h4>
							</div>
							
							<div class="row">
							<div class="col-md-8 col-sm-7 col-xs-12">
								<form class="form-horizontal" id="myForm2" action="<%=request.getContextPath() %>/fileHandler/uploadFile?type=2" method="POST" enctype="multipart/form-data">
								    <div class="form-group">
									    <label for="p_file2" class="col-sm-2 control-label">请上传EXCEL文件</label>
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
									 <button  class="btn btn-md btn-primary pull-right"  id="submitEXCEL"  disabled="disabled">验证接口方法λ项求值</button> 
								</div>
							</div>
						</div>
							
						
						</div>
						
						
					</div>
	    			
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12" style="display:none;">
							<div class="headline">
								<h4><i class="fa fa-tasks"></i>&nbsp;验证结果</h4>
							</div>
							<div id="vefiryResult" class="demandInfo"></div>
			        	</div>
	    			</div>
		    		<div class="row">
		    			<div class="col-md-9 col-sm-7 col-xs-12">
			    			<div class="headline">
									<h4><i class="fa fa-table"></i>&nbsp;待验证可达路径列表  </h4>
							</div>
						</div>
						<div class="col-md-9 col-sm-7 col-xs-12">
			    			<div class="row" style="margin-left:10px;margin-top:20px;">
									<div class="table-responsive">
											<table id="pathList" class="table table-bordered" style="table-layout: fixed;word-wrap:break-word;">
												<thead>
													<tr>
														<th width="50px;">编号</th>
														<th >表达式</th>
														<th width="150px;">操作</th>
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
	    
        <!-- Javascripts -->
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/utils.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/page.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootbox.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootstrap-treeview.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/jquery-form.js"></script>
		<script type="text/javascript">
        $("#ProcessVeBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
        var keyword = "";
		var proid = 0; //用户记录用户初始选择的项目id，以便进行后续操作
		var path = "";
		getProjectCatalog();
		
        $("#pList").change(function(){
     	   	proid = $("#pList").val();
     	   	$("#vefiryResult").parent().hide();
			$("#submitXML").attr("disabled",true);
     	  	if(proid!=0){
     	  		getResultInfo();
     	  		//getEnvExtendedTypeCatalog();
     	  		getInvokableNodeCatalog();
     	  		getPathList();
     	  	}
     		   
    	    
        });
    
        $("#verifyInvocation").click(function(){
        	var para1 = $("#inputPara").val();
        	var para2 = $("#inputPara2").val();
        	if(para1==""||para2==""){
        		bootbox.alert({  
    	            buttons: {  
    	               ok: {  
    	                    label: '确认',  
    	                }  
    	            },  
    	            callback:function(){
    	            	
    	            },
    	            message: '请选择两个接口！', 
    	            title: "验证提醒",  
    	        });
        		return;
        	}
        		
        	$.ajax({
        		type : "POST",
				url : root + "/verify/verifyInvocation",
				data : JSON.stringify({para1:para1,para2:para2,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						$("#vefiryResult").html("<p style='color:red;'>验证通过！</p>");
						$("#vefiryResult").parent().show();
					}else if(data.errorcode ==1010){
						$("#vefiryResult").html("<p style='color:red;'>验证不通过!调用图中无此可达路径要求。</p>");
						$("#vefiryResult").parent().show();
					}else {
						$("#vefiryResult").html("<p style='color:red;'>验证不通过！"+data.result.info+"</p>");
						$("#vefiryResult").parent().show();
					}
				}
        	});
        });
        
        $("#submitEXCEL").click(function(){
        	path2 = $("#filepath2").val();
        	$.ajax({
        		type : "POST",
				url : root + "/verify/verEvaluationByExcel",
				data : JSON.stringify({EXCELpath: path2,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						$("#vefiryResult").html("<p style='color:red;'>验证通过！</p>");
						$("#vefiryResult").parent().show();
					}
				}
        	});
        });
        function getPathList(){
        	$.ajax({
        		type : "POST",
				url : root + "/verify/getPathList",
				data : JSON.stringify({limit:limit,page:page,keyword:keyword,projectId:proid}),
				success : function(data) {
					$("tbody",$("#pathList")).empty();
					if (data.errorcode == 1000) {
						var reachablePath = data.result.info.reachablePath;
						var total = data.result.info.total;
						if(total){
							for(var i=0;i<reachablePath.length;i++){
									
								var temp = '<td  rid="'+reachablePath[i].id+'"><button class="verifyDemand btn btn-xs btn-primary" style="margin-right:5px;">可调用验证</button><button style="margin-top:5px;" class="verifyEva btn btn-xs btn-info" style="margin-right:5px;">λ求值验证</button></td>';
								
								var str = '<tr ><td >P'+reachablePath[i].id+'</td>'+
								'<td >'+reachablePath[i].pathExp+'</td>'+
								temp+'</tr>';
								$("tbody",$("#pathList")).append(str);
							}
							
							var a = function(p) {
								page = Number(p);
								getPathList(); 
								
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
						
							$(".verifyDemand").click(function(){
								var rid = $(this).parent().attr("rid");
								verifyPath(rid);
							});
							$(".verifyEva").click(function(){
								var rid = $(this).parent().attr("rid");
								verifyEva(rid);
							});
							
						}else{
							$("tbody",$("#getPathList")).append('<tr><td colspan="3">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
					}
				}
        	});
        }
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
								$("#file2_error").html("参数EXCEL文件已存在，请直接点击验证如需修改，请重新上传！");
								$("#file2_error").show();
								$("#submitEXCEL").attr("disabled",false);
							}
							}
						}
					
	        	});
		 }
 		function getEnvExtendedTypeCatalog(){
			 
			 $(".paraList").empty();		
			 $.ajax({
	        		type : "POST",
					url : root + "/samlType/getSamlTypeCatalog",
					data : JSON.stringify({projectId:Number(proid),level:3}),
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
        
        //验证，id=0表示验证全部
        function verifyPath(rid){
        	$.ajax({
        		type : "POST",
				url : root + "/verify/verifyInvocationByPath",
				data : JSON.stringify({rid:rid,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						$("#vefiryResult").html("<p style='color:red;'>验证通过！</p>");
						$("#vefiryResult").parent().show();
					}else if(data.errorcode ==1010){
						$("#vefiryResult").html("<p style='color:red;'>验证不通过!调用图中无此可达路径要求。</p>");
						$("#vefiryResult").parent().show();
					}else {
						$("#vefiryResult").html("<p style='color:red;'>验证不通过！"+data.result.info+"</p>");
						$("#vefiryResult").parent().show();
					}
				}
        	});
        	
        }
        
        
        function verifyEva(rid){
        	$.ajax({
        		type : "POST",
				url : root + "/verify/verEvaluationByPath",
				data : JSON.stringify({rid:rid,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						$("#vefiryResult").html("<p style='color:red;'>验证通过！</p>");
						$("#vefiryResult").parent().show();
					}else if(data.errorcode ==1010){
						$("#vefiryResult").html("<p style='color:red;'>验证不通过!调用图中无此可达路径要求。</p>");
						$("#vefiryResult").parent().show();
					}else {
						$("#vefiryResult").html("<p style='color:red;'>验证不通过！"+data.result.info+"</p>");
						$("#vefiryResult").parent().show();
					}
				}
        	});
        	
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