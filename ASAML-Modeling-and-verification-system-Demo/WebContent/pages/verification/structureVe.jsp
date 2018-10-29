<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>结构验证</title>
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
						  <li class="active">结构验证</li>
						</ol>
		    		</div>
		    		<div class="row">
						<div class="col-md-9 col-sm-7 col-xs-12">
							<div class="headline">
								<h4><i class="fa fa-question-circle"></i>&nbsp;结构验证用于对功能构成进行验证，根据需求分析，将功能构成性质公式表示为类型序列及其关系集合，并通过验证该类型序列正确与否判断是否满足功能组成方面的需求。</h4>
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
	    			<div class="col-md-9 col-sm-9 col-xs-12 input-group" style="">
						<input type="text" class="form-control" id="searchKeyword">
				     	<span class="input-group-btn">
				        	<button class="btn btn-info" type="button" id="searchDemand">搜索功能或性质</button>
				     		
				     	</span>
					</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12" style="display:none;">
							
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;待验证公式<button  class="btn btn-md btn-primary pull-right"  id="verifyAll" style="margin-left:5%;" onclick="verifyDemandOrRelation(0)" disabled="disabled">验证全部性质</button></h4>
							</div>
							<div id="vefiryExp" class="demandInfo"></div>
			        	</div>
	    			</div>
	    			<div class="row">
	    				<div class="col-md-9 col-sm-7 col-xs-12" style="display:none;">
							<div class="headline">
								<h4><i class="fa fa-star"></i>&nbsp;验证结果</h4>
							</div>
							<div id="vefiryResult" class="demandInfo"></div>
			        	</div>
	    			</div>
		    		<div class="row">
		    			<div class="col-md-9 col-sm-7 col-xs-12">
			    			<div class="headline">
									<h4><i class="fa fa-table"></i>&nbsp;待验证性质列表  </h4>
							</div>
						</div>
						<div class="col-md-9 col-sm-7 col-xs-12">
			    			<div class="row" style="margin-left:10px;margin-top:20px;">
									<div class="table-responsive">
											<table id="demandList" class="table table-bordered">
												<thead>
													<tr>
														<th>功能编号</th>
														<th>功能名称</th>
														<th>性质编号</th>
														<th>性质表达式</th>
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
	    
        <!-- Javascripts -->
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/utils.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/page.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootbox.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootstrap-treeview.js"></script>
		<script type="text/javascript">
        $("#StructureVeBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
        var limit = 10;
        var page = 1;
        var keyword = "";
		var proid = 0; //用户记录用户初始选择的项目id，以便进行后续操作
		getProjectCatalog();
		$("#searchDemand").click(function(){
			keyword = $.trim($("#searchKeyword").val());
			getDemandList();
			});
        $("#pList").change(function(){
     	   	proid = $("#pList").val();
     	   	$("tbody",$("#demandList")).empty();
  			$("tbody",$("#demandList")).append('<tr><td colspan="5">暂无记录！</td></tr>');
			$('#setpage').parent().hide();
			$("#vefiryExp").parent().hide();
			$("#vefiryResult").parent().hide();
			$("#verifyAll").attr("disabled",true);
     	  	if(proid!=0){
     	  		getDemandList();
     	  		getvefiryExp();
     	  	}
     		   
    	    
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
        
        
        function getDemandList(){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getAllDemandList",
				data : JSON.stringify({limit:limit,page:page,keyword:keyword,projectId:proid}),
				success : function(data) {
					$("tbody",$("#demandList")).empty();
					if (data.errorcode == 1000) {
						var demandAndRelation = data.result.info.demandAndRelation;
						var total = data.result.info.total;
						if(total){
							var relationcount = 1;
							for(var i=0;i<demandAndRelation.length;i++){
								var str_dr1 = '<td>-</td><td>-</td>';
								var str_dr2 = '';
								var relations = demandAndRelation[i].relations;
								var rows = relations.length>0?relations.length:1;
								for(var j=1;j<relations.length;j++){
									str_dr2 += '<tr><td>R'+(relationcount+j)+'</td><td>'+relations[j].expression+'</td></tr>';
								}
								if(relations.length!=0){
									str_dr1 = '<td>R'+relationcount+'</td><td>'+relations[0].expression+'</td>';
								}
									
								relationcount+=relations.length;
								var temp = '<td rowspan='+rows+' cid="'+demandAndRelation[i].id+'"><button class="verifyDemand btn btn-xs btn-primary" style="margin-right:5px;">验证</button></td>';
								
								var str = '<tr rowspan='+rows+'><td class="center" rowspan='+rows+'>D'+((page-1)*limit+i+1)+'</td>'+
								'<td rowspan='+rows+'>'+demandAndRelation[i].demandName+'</td>'+
								str_dr1+temp+'</tr>'+str_dr2;
								console.log(str);
								$("tbody",$("#demandList")).append(str);
							}
							
							var a = function(p) {
								page = Number(p);
								getDemandList(); 
								
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
								var cid = $(this).parent().attr("cid");
								verifyDemandOrRelation(cid);
							});
							
						}else{
							$("tbody",$("#demandList")).append('<tr><td colspan="5">暂无记录！</td></tr>');
							$('#setpage').parent().hide();
						}
					}
				}
        	});
        }
        function getvefiryExp(){
        	$.ajax({
        		type : "POST",
				url : root + "/verify/getvefiryExp",
				data : JSON.stringify({projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						//$("#vefiryExp").html("<p style='text-align:center;'>"+data.result.info+"</p>");
						$("#vefiryExp").html("<p style='text-align:center;'>将功能构成应满足需求的性质记为Ps，其公式定义如下，<br />"+
								"P<sub>s</sub> = R<sub>s11</sub>∧…∧R<sub>s19</sub>∧R<sub>s110</sub>∧…∧R<sub>s114</sub>∧R<sub>s21</sub>∧…∧R<sub>s24</sub>∧R<sub>s31</sub>∧…∧R<sub>s45</sub></p>");
						
						$("#vefiryExp").parent().show();
						$("#verifyAll").attr("disabled",false);
						}
					}
        	});
        }
        //验证，id=0表示验证全部
        function verifyDemandOrRelation(id){
        	$.ajax({
        		type : "POST",
				url : root + "/verify/verifyDemand",
				data : JSON.stringify({id:id,projectId:proid}),
				success : function(data) {
					if (data.errorcode == 1000) {
						console.log(data.result.info);
						if(id==0){
							if(data.result.info!=""){
								$("#vefiryResult").parent().show();
								$("#vefiryResult").html("<span style='color:red;'>"+data.result.info+"</span>");
							}else{
								$("#vefiryResult").parent().show();
								$("#vefiryResult").html("<span style='color:red;'>全部验证通过</span>");
							}
						}else{
							$("[cid="+id+"]").append("<span style='color:red;'>检测通过</span>");
						}
						
					}else{
						$("[cid="+id+"]").append("<span style='color:red;'>检测不通过</span>");
					}
				}
			});
        	
        }
        </script>
</body>
</html>