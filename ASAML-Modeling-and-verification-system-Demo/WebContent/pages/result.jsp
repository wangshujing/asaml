<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>结果展示</title>
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
	    			<div class="page-location">
		    			<ol class="breadcrumb">
						  <li><a href="<%=request.getContextPath() %>/index">首页</a></li>
						  <li class="active">结果展示</li>
						</ol>
		    		</div>
		    		<h2 >模型统计</h2>
		    		<hr />
	    			<div class="col-md-6 col-sm-6" >
	    				<div id="typeChart"   style="border:3px solid #9d9d9d;width: 550px;height:300px;" >  
                			 
            			</div>  
       					   
    				</div>
    				<div class="col-md-6 col-sm-6">
	    				<div id="typeRuleChart"   style="border:3px solid #9d9d9d;width: 550px;height:300px;" >  
                			 
            			</div> 
       				</div>
       				<div class="col-md-offset-3 col-md-6 col-sm-6" style="margin-top:10px;">
	    				<div id="demandChart"   style="border:3px solid #61b562;width: 550px;height:300px;" >  
                			 
            			</div> 
       				</div> 
    				<!-- <div class="col-md-6 col-sm-6" style="margin-top:10px;">
	    				<div id="resultChart"   style="border:3px solid #9d9d9d;width: 550px;height:300px;" >  
                			 
            			</div> 
       				</div>  -->
	    			
				</div>
	    	</div>
    		</div>
    		<jsp:include page="footer.jsp"></jsp:include>
	    
        <!-- Javascripts -->
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath() %>/static/js/echarts.js"></script>
        <script type="text/javascript">
        $("#resultBar").addClass("active");
        var root = "<%=request.getContextPath() %>";
       	var typeChart = echarts.init(document.getElementById('typeChart'));
    	var typeRuleChart = echarts.init(document.getElementById('typeRuleChart'));
    	var demandChart = echarts.init(document.getElementById('demandChart'));
    	//var resultChart = echarts.init(document.getElementById('resultChart'));
        /* // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        }; */

        // 使用刚指定的配置项和数据显示图表。
        
       	
        getTypeCount();
       	getTypeRuleCount();
       	getDemandCount();
        getResultCount();
        function getTypeCount(){
        	$.ajax({
        		type : "POST",
				url : root + "/samlType/getTypeCount",
				data : JSON.stringify({projectId:3}),
				success : function(data) {
					if (data.errorcode == 1000) {
						var counts = data.result.info.counts;
						var total = data.result.info.total;
						if(total){
							typeChart.setOption({
								title:{
									 text: '类型数量统计:总计 329 '
								},
							 	series : [
							           {
							               type: 'pie',
							               radius: '55%',
							               roseType: 'angle',
							               label:{            //饼图图形上的文本标签
					                            normal:{
					                                show:true,
					                                position:'bottom', //标签的位置
					                               	formatter:'{b}:{c}({d}%)'
												}
					                        },
							               data:[
							                   {value:128, name:'Mbd层基本数据类型'},
							                   {value:71, name:'Mbd层扩展基本数据类型'},
							                   {value:91, name:'Mbd层带标签的映射类型'},
							                   {value:26, name:'Mbti层基本接口类型'},
							                   {value:12, name:'Mdev层设备类型'},
							                   {value:1, name:'Mfrwk层应用系统体系结构类型'},
							                   /* {value:192, name:'初始环境'},
							                   {value:42, name:'环境固有类型'},
							                   {value:28, name:'自定义类型'},
							                   {value:122, name:'自定义映射类型'},
							                   {value:73, name:'接口类型'},
							                   {value:28, name:'组件类型'},
							                   {value:18, name:'容器类型'},
							                   {value:6, name:'框类型'},
							                   {value:2, name:'框架类型'} */
							               ]
							           }
							       ]
						 });
						}
						 
					}
				}
        	});
        }
        
        function getTypeRuleCount(){
        	$.ajax({
        		type : "POST",
				url : root + "/typeRule/getTypeRuleCount",
				data : JSON.stringify({projectId:3}),
				success : function(data) {
					if (data.errorcode == 1000) {
						var counts = data.result.info.counts;
						var total = data.result.info.total;
						if(total){
							typeRuleChart.setOption({
								title:{
									 text: '关系数量统计:总计 1034'
								},
							 	series : [
							           {
							               type: 'pie',
							               radius: '55%',
							               roseType: 'angle',
							               label:{            //饼图图形上的文本标签
					                            normal:{
					                                show:true,
					                                position:'bottom', //标签的位置
					                               	formatter:'{b}:{c}({d}%)'
												}
					                        },
							               data:[
							                   {value:24, name:'Mbd层参数关联关系'},
							                   {value:91, name:'Mbd层聚合关系'},
							                   {value:178, name:'Mbti层参数关联关系'},
							                   {value:130, name:'Mbti层成员关联关系'},
							                   {value:93, name:'Mbti层方法关联关系'},
							                   {value:125, name:'Mdev层类型关联关系'},
							                   {value:52, name:'Mdev层聚合关联关系'},
							                   {value:329, name:'Mfrwk层子类型关系'},
							                   {value:12, name:'Mfrwk层聚合关联关系'}
							                   
							                   /* {value:258, name:'参数关联规则'},
							                   {value:187, name:'聚合关系规则'},
							                   {value:182, name:'方法关联规则'},
							                   {value:185, name:'可调用关系规则'} */
							               ]
							           }
							       ]
						 });
						}
					}
				}
        	});
        }
        function getDemandCount(){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getDemandCount",
				data : JSON.stringify({projectId:3}),
				success : function(data) {
					if (data.errorcode == 1000) {
						var counts = data.result.info.counts;
						var total = data.result.info.total;
						if(total){
							demandChart.setOption({
								title:{
									 text: '验证结果统计 '
								},
								tooltip: {},
					            legend: {
					            	left: 'center',
					            	bottom:'10px',
					                data: ['P可连通性', 'P可互操作性', 'P实际可互操作性']
					            },
					            xAxis: {
					            	min: 0,
					                type: 'category',
					                data: ["待验证单元总数","待验证关系单元总数","验证通过关系总数"]
					            },
					            yAxis: {},
					            series: [//一组显示多条柱形图
					                     {
					                       barMaxWidth: '30',
					                       itemStyle: {normal: {color: '#999', label: {show: true}}},
					                       name: 'P可连通性',
					                       type: 'bar',
					                       data: [9,18,8]
					                     }, {
					                       barMaxWidth: '30',
					                       itemStyle: {normal: {label: {show: true}}},
					                       name: 'P可互操作性',
					                       type: 'bar',
					                       data: [54,87,12]
					                     }, {
					                       barMaxWidth: '30',
					                       itemStyle: {normal: {label: {show: true}}},
					                       name: 'P实际可互操作性',
					                       type: 'bar',
					                       data: [54,87,12]
					                     }/* , {
					                       barMaxWidth: '30',
					                       name: 'Pb2',
					                       itemStyle: {normal: {label: {show: true}}},
					                       type: 'bar',
					                       data: [16,79,79]
					                     } */
					                   ]
						 });
						}
					}
				}
        	});
        }
        function getResultCount(){
        	$.ajax({
        		type : "POST",
				url : root + "/demand/getDemandCount",
				data : JSON.stringify({projectId:3}),
				success : function(data) {
					if (data.errorcode == 1000) {
						var counts = data.result.info.counts;
						var total = data.result.info.total;
						if(total){
							resultChart.setOption({
								title: {
					                text: '模型利用率 ' 
					            },
					            tooltip: {},
					            legend: {
					            	left: 'center',
					            	bottom:'10px',
					                data: ['相关类型总数', '相关类型关系总数'/* , 'Pb2' */]
					            },
					            xAxis: {
					            	min: 0,
					                type: 'category',
					                data: ["Ps","Pb1","Pb2","模型"]
					            },
					            yAxis: {},
					            series: [//一组显示多条柱形图
					                     {
					                       barMaxWidth: '30',
					                       itemStyle: {normal: {color: '#999', label: {show: true}}},
					                       name: '相关类型总数',
					                       type: 'bar',
					                       data: [119,183,183,319]
					                     }, {
					                       barMaxWidth: '30',
					                       itemStyle: {normal: {label: {show: true}}},
					                       name: '相关类型关系总数',
					                       type: 'bar',
					                       data: [554,554,554,812]
					                     }/* , {
					                       barMaxWidth: '30',
					                       name: 'Pb2',
					                       itemStyle: {normal: {label: {show: true}}},
					                       type: 'bar',
					                       data: [16,79,79]
					                     } */
					                   ]
					           
					
						 });
						}
					}
				}
        	});
        }
        </script>
</body>
</html>