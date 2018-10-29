<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>应用体系结构建模和验证系统首页</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
		<!--[if lt IE 9]>
	      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
    </head>
    <body>
		<jsp:include page="nav.jsp"></jsp:include>
	    <div class="container-fluid">
	    	<div class="row" style="min-height: 400px;">
	    		<div class="col-md-2 col-sm-3 col-xs-12 sidebar">
	    			<jsp:include page="siderbar.jsp"></jsp:include>
	    		</div>
	    		<div class="col-md-10 col-sm-9 col-xs-12">
	    			<div class="col-md-12" style="text-align: center;">
		    			<br/>
		    				<h2>欢迎使用应用体系结构建模和验证系统（ASAMVS）</h2>
		    		</div>
		    		<br/>
		    		<br/>
		    		<div class="col-md-12" style="margin-top:50px;margin-bottom:50px;">
			    		<h4><i class="fa fa-leaf"></i>系统简介</h4>
				    		<div class="col-md-9 col-md-offset-1" >
				    			<span style="font-size:14px;">可验证的基于高阶类型理论的轻量级的形式化方法—应用体系结构建模语言ASAML，对应用体系结构中的构成要素定义为类型，对构成要素之间的关系定义为类型关系，不仅可以对应用体系结构进行建模，还可以进一步细化到业务功能和流程，逐层定义应用体系结构中的类型及其关系，形成分层的应用体系结构，同时采用该语言可以描述业务功能方面需求和业务流程方面需求期望的性质，从而可以实现对应用体系结构的建模与验证，以期在设计阶段验证应用体系结构的正确性。</span>
			    			</div>
	    			</div>
	    			<div class="col-md-12">
	    				<h4><i class="fa fa-leaf"></i>系统使用流程 </h4>
		    			<div class="col-md-3 col-md-offset-1 systemSector"><a class="btn btn-danger" href="<%=request.getContextPath() %>/policyIndex" target="_blank">应用体系结构需求分析</a></div>
	    				<div class="col-md-3 systemSector"><a class="btn btn-info" href="<%=request.getContextPath() %>/EnvBasicTypesManage" target="_blank">应用体系结构建模</a></div>
	    				<div class="col-md-3 systemSector"><a class="btn btn-primary" href="<%=request.getContextPath() %>/cultureIndex" target="_blank">应用体系结构验证</a></div>
    				</div>
				</div>
	    	</div>
    		</div>
    		<jsp:include page="footer.jsp"></jsp:include>
	    
        <!-- Javascripts -->
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        $("#welcomeBar").addClass("active");
        </script>
</body>
</html>