
//分页
//

(function($){
	if(typeof $ =='undefined'){
		return;
	}
	
	var create_pagination = function(pagination,options){
	
	var name = 'pagination_' + new Date().getTime()+ '_' + parseInt(Math.random()*100+1);
	window[name]={};
	var id,totalpage,pagesize,cpage,count,curcount,outstr,countShow; 
	//初始化
	cpage = options.selectedPage; //初始化从哪页开始
	totalpage = options.total; //总共的页数
	pagesize = options.pageLength-1; //初始化显示几页.
	var pageNum= options.showLocation; //当前页数在总体页数中的位置
	window[name].gotopage=function(page)
	{
		if(page<1||page>totalpage)
		{
			return false;
		}
		options.callback?options.callback(page): null;
		window[name].setpage(page);
	}
	
	window[name].setpage= function (cpage) 
	{
		var window_name = window[name]
		outstr = ""; 
			 if(totalpage<=pagesize+1){   
			 outstr = outstr + "<a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage-1)+")'> < </a>";     
			for (count=1;count<=totalpage;count++) 
			{    if(count!=cpage) 
				{ 
					 outstr = outstr + "<a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+count+")'>"+count+"</a>"; 
				}else{ 
					outstr = outstr + "<span class='current' >"+count+"</span>"; 
				} 
			} 
			outstr = outstr + "<a href='javascript:void(0)'" + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage+1)+")'> > </a>"; 
		} 
		if(totalpage>pagesize+1){        
			if(cpage<=totalpage-(pagesize-1)) 
			{      
			 outstr = outstr + "<a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage-1)+")'> < </a>"; 
			 count=1;
			 if(cpage>pageNum){
				 count++;
				 outstr+=" <a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage(1)'>1</a><span>..</span>";
				 }       
				for (;count<=pagesize;count++) 
				{   
				cpage>pageNum?countShow=(cpage-pageNum+count):countShow=count;
				
				 if(countShow!=cpage) 
					{ 
						outstr = outstr + "<a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+countShow+")'>"+countShow+"</a>"; 
					}else{ 
						 outstr = outstr + "<span class='current'>"+countShow+"</span>"; 
					} 
				
				} 
				//显示最后两个页码
				// outstr+=" <li><span>...</span></li><li><a href='javascript:void(0)' onclick='gotopage("+(totalpage-1)+")'>"+(totalpage-1)+"</a></li><li><a href='javascript:void(0)' onclick='gotopage("+totalpage+")'>"+totalpage+"</a></li>";
				 outstr+="<span>..</span><a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+totalpage+")'>"+totalpage+"</a>";		 		  
				 outstr = outstr + "<a href='javascript:void(0)'" + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage+1)+")'> > </a>"; 
			} 
			else{
				 outstr = outstr + "<a href='javascript:void(0)'" + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage-1)+")'> < </a>"; 
				  outstr+=" <a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage(1)'>1</a><span>..</span>";  
					count=2;
				for (;count<=pagesize+1;count++) 
				{  
				 cpage<(totalpage-(pagesize-pageNum)-1)?countShow=(cpage-pageNum+count):countShow=(totalpage-pagesize+count-1);
				if(countShow==2)
				{
					countShow=3;
					count++;
				}
				//countShow=totalpage-10+count;
				 if(countShow!=cpage) 
					{ 			
						outstr = outstr + "<a href='javascript:void(0)'" + " onclick='window["+'"'+name+'"'+"].gotopage("+countShow+")'>"+countShow+"</a>"; 
					}else{ 
					   outstr = outstr + "<span class='current'>"+countShow+"</span>"; 
					} 		
				} 
				if(cpage<(totalpage-(pagesize-pageNum)-1))
				{
					 outstr+="<span>..</span><a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+totalpage+")'>"+totalpage+"</a>";	
					}
				outstr = outstr + "<a href='javascript:void(0)' " + " onclick='window["+'"'+name+'"'+"].gotopage("+(cpage+1)+")'> > </a>"; 
				}   
		}  
			
		pagination.innerHTML = "<div id='"+pagination.id+"'>" + outstr + "<\/div>"; 
		outstr = ""; 
		}
		window[name].setpage(cpage);
		return window[name];
	}
	
	 var defaults = {
		selectedPage: 1,
		total: 100,
		pageLength: 7,
		showLocation: 4,
		callback:function(){}
	};

	$.fn.Pagination=function(options,param){
		if (typeof options == 'string') {
			return $.fn.Pagination.methods[options](this, param);
		}
		if((typeof options == 'object')&&param==undefined)
		{
			$.fn.Pagination.methods['init'](this, options);
		}
		
	};
	$.fn.Pagination.methods = {
		init: function(jq,options){
			//beginPage,total,pageSize,pageCurrent,callback
			options = $.extend({},defaults, options);
			new create_pagination(jq[0],options);  
		}
	};
})(window.jQuery);