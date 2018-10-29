<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

	 <ul class="nav nav-sidebar">
	 	<li id="welcomeBar"><a href="<%=request.getContextPath() %>/index">首页</a></li>
	   	<li id="DemandBar"><a data-toggle="collapse" href="#DemandSubList" aria-expanded="true">需求描述</a>
	 		<ul id="DemandSubList" class="collapse sub-list in"  aria-expanded="true">
				<li id="ProjectBar"><a href="<%=request.getContextPath() %>/projectManage">项目管理</a></li>
				<li id="OriginalDemandBar"><a href="<%=request.getContextPath() %>/originalDemandManage">原始需求录入</a></li>
				<li id="DemandAnalysisBar"><a href="<%=request.getContextPath() %>/demandAnalysisManage">用户需求整理</a></li>
				<li id="DemandBasedStrBar"><a href="<%=request.getContextPath() %>/demandBasedStrManage">基于体系结构的分析</a></li>
			</ul>
		</li>
	   	<li id="ModelingBar"><a data-toggle="collapse" href="#ModelingSubList" aria-expanded="true">体系结构建模</a>
	 		<ul id="ModelingSubList" class="collapse sub-list in"  aria-expanded="true">
				<li id="EnvBasicTypeBar"><a href="<%=request.getContextPath() %>/envBasicTypeManage">数据类型管理</a></li>
				<li id="FrameworkBar"><a href="<%=request.getContextPath() %>/frameworkManage">扩展基本数据类型管理</a></li>
				<li id="EnvExtendedTypeBar"><a href="<%=request.getContextPath() %>/envExtendedTypeManage">带标签的映射类型管理</a></li>
				<li id="TypeInterfacesBar"><a href="<%=request.getContextPath() %>/typeInterfaceManage">接口类型管理</a></li>
				<li id="ComponentBar"><a href="<%=request.getContextPath() %>/componentManage">设备类型管理</a></li>
				<li id="ContainerBar"><a href="<%=request.getContextPath() %>/containerManage">应用系统体系结构类型管理</a></li>
				<!-- <li id="FrameBar"><a href="<%=request.getContextPath() %>/frameManage">框架管理</a></li> -->
				
			</ul>
		</li>
		<li id="VerificationBar"><a data-toggle="collapse" href="#VerificationSubList" aria-expanded="true">体系结构验证</a>
	 		<ul id="VerificationSubList" class="collapse sub-list in"  aria-expanded="true">
				<li id="StructureVeBar"><a href="<%=request.getContextPath() %>/structureVe">连通性验证</a></li>
				<li id="ProcessVeBar"><a href="<%=request.getContextPath() %>/processVe">互操作性验证</a></li>
				
			</ul>
		</li>
		<li id="resultBar"><a href="<%=request.getContextPath() %>/result">结果展示</a></li>
	   	
	 </ul>
