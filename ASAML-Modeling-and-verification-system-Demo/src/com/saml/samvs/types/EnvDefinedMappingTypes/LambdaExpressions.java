package com.saml.samvs.types.EnvDefinedMappingTypes;

import java.beans.MethodDescriptor;
import java.io.InputStream;
import java.lang.invoke.ConstantCallSite;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import net.sf.json.JSONObject;

import com.saml.samvs.libraries.callbacks;
import com.saml.samvs.libraries.coreDistributeMatch;
import com.saml.samvs.libraries.forumOwners;
import com.saml.samvs.libraries.httpServletRequests;
import com.saml.samvs.libraries.outputs;
import com.saml.samvs.libraries.pictures;
import com.saml.samvs.libraries.postInfos;
import com.saml.samvs.libraries.postTmpInfos;
import com.saml.samvs.libraries.themeInfos;
import com.saml.samvs.libraries.users;
import com.saml.samvs.types.EnvDefinedBasicTypes.*;

import com.saml.util.Printer;
/**
 * 
 * @author EW
 * Class LambdaExpressions defines all of the lambda expressions.
 * The initiate() method performs the assignments to all of the λ-terms.
 * 定义系统中所有的λ表达式，并通过initiate方法对它们初始化为具体的λ项。
 *
 */
public class LambdaExpressions {

	public P1R1Function<feedback, output> coreFODistribute;
	public P1R1Function<input, callback> coreICDistribute;
	public P1R1Function<input, output> coreIODistribute;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getPostInfo;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getPostInfoByOwner;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getForumList;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getThemeList;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> addPost;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getTheme;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> editPost;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> deletePost;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> addComment;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> deleteComment;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getOwnThemeList;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getForumReportList;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> getForumReportInfo;
	public P2R1Function<HttpServletRequest ,  HttpServletResponse, Map<String, Object>> handleForumReport;
	public P3R1Function<Integer ,  HttpServletRequest ,  HttpServletResponse, Map<String, Object>> uploadFile;
	public P3R1Function<String ,  Integer ,  User, String> moveFile;
	public P1R1Function<Integer, ArrayList<String>> getAlbumList;
	public P1R1Function<JSONObject, Map<String,Object>> ApiValidateUserLogin;
	public P0R1Function<List<ForumInfo>> selectAll;
	public P2R1Function<Integer ,  Integer, ForumOwner> ifOwner;
	public P1R1Function<Integer, List<Integer>> selectKeyByForumID;
	public P1R1Function<Integer, List<Integer>> selectByThemeID;
	public P1R1Function<Integer, List<ForumInfo>> selectByOwnerID;
	public P1R1Function<Integer, List<ThemeInfo>> selectThemeListByForumID;
	public P1R1Function<Map<String, Object>, List<PostInfo>> selectPostListByForumID;
	public P1R1Function<Integer, ThemeInfo> selectThemeInfoByPrimaryKey;
	public P1R1Function<Integer, PostInfo> selectPostInfoByPrimaryKey;
	public P1R1Function<Integer, PostTmpInfo> selectPostTmpInfoByPrimaryKey;
	public P1R1Function<Integer, PostCommentInfo> selectPostCommentInfoByPrimaryKey;
	public P1R1Function<Integer, ForumReportInfo> selectForumReportInfoByPrimaryKey;
	public P1R1Function<PostInfo, Integer> updatePostInfoByPrimaryKeySelective;
	public P1R1Function<PostTmpInfo, Integer> updatePostTmpInfoByPrimaryKeySelective;
	public P1R1Function<PostCommentInfo, Integer> updatePostCommentInfoByPrimaryKeySelective;
	public P1R1Function<ForumReportInfo, Integer> updateForumReportInfoByPrimaryKeySelective;
	public P1R1Function<PostInfo, Integer> insertPostInfoSelective;
	public P1R1Function<PostTmpInfo, Integer> insertPostTmpInfoSelective;
	public P1R1Function<PostCommentInfo, Integer> insertPostCommentInfoSelective;
	public P1R1Function<ForumReportInfo, Integer> insertForumReportInfoSelective;
	public P1R1Function<Integer, PostTmpInfo> selectRecent;
	public P1R1Function<Integer, PostTmpInfo> selectByPostInfoId;
	public P1R1Function<Integer, List<PostTmpInfo>> selectPostTmpInfoListByPostInfoId;
	public P1R1Function<Integer, List<PostCommentInfo>> selectPostCommentInfoListByPostInfoId;
	public P1R1Function<Integer, List<ForumReportInfo>> selectListByContentId;
	public P1R1Function<Map<String,Object>, List<ForumReportInfo>> selectListPagePost;
	public P1R1Function<Map<String,Object>, List<ForumReportInfo>> selectListPageComment;
	public P1R1Function<Map<String,Object>, List<ForumReportInfo>> selectCommentReport;
	public P1R1Function<Map<String,Object>, List<ForumReportInfo>> selectListPagePostNew;
	public P1R1Function<Map<String,Object>, List<ForumReportInfo>> selectListPageCommentNew;
	public P0R1Function<Integer> getTotalResult;
	public P1R0Function<Integer > setShowCount;
	public P1R0Function<Integer > setCurrentResult;
	public P0R1Function<Integer> getId;
	public P0R1Function<Integer> getForumID;
	public P0R1Function<Integer> getThemeID;
	public P0R1Function<Integer> getPostID;
	public P0R1Function<Integer> getPostInfoId ;
	public P0R1Function<Integer> getContentID;
	public P0R1Function<Integer> getCreaterID;
	public P0R1Function<Integer> getReplierID;
	public P0R1Function<Integer> getReasonType;
	public P0R1Function<Integer> getContentType ;
	public P0R1Function<Integer> getVisitNumber;
	public P0R1Function<Integer> getCommentNumber;
	public P0R1Function<String> getTitle;
	public P0R1Function<String> getText;
	public P0R1Function<Integer> getState;
	public P0R1Function<String> getInfo;
	public P0R1Function<String> getReporterPortrait;
	public P1R0Function<Integer> setThemeID;
	public P1R0Function<Integer> setPostID;
	public P1R0Function<Integer> setPostInfoId;
	public P1R0Function<Integer> setCommentID;
	public P1R0Function<Integer> setContentID;
	public P1R0Function<Integer> setCreaterID;
	public P1R0Function<Integer> setReplierID;
	public P1R0Function<Integer> setHandlerID;
	public P1R0Function<Integer> setReasonType;
	public P1R0Function<Integer> setContentType;
	public P1R0Function<Integer> setThemeNum;
	public P1R0Function<Integer> setPostNum;
	public P1R0Function<Integer> setVisitNumber;
	public P1R0Function<Integer> setCommentNumber;
	public P1R0Function<Integer> setState;
	public P1R0Function<Integer> setIsPop;
	public P1R0Function<String> setTitle;
	public P1R0Function<String> setText;
	public P1R0Function<String> setTags;
	public P1R0Function<String> setInfo;
	public P1R0Function<String> setPostTitle;
	public P1R0Function<String> setCommentInfo;
	public P1R0Function<String> setHandleInfo;
	public P1R0Function<String> setReporterPortrait;
	public P1R0Function<Date> setPublishTime;
	public P1R0Function<Date> setReportTime;
	public P1R0Function<Date> setAddTime;
	public P1R0Function<Date> setHandleTime;
	public P1R0Function<List<ThemeInfo>> setThemeList;




	
	
	public LambdaExpressions() {
		super();
	}
	
	public void print(){
		//Printer.printMethod("getChilds",this.getChilds);
	}
	
	public void initiate() {
		this.coreFODistribute = (feedback) ->  { 
			for (coreDistributeMatch cdmatch: coreDistributeMatch.values()){
				if(cdmatch.getType()==1&&cdmatch.getPara1().equals(feedback.getName())){
					for (outputs ot : outputs.values()){
						if(ot.getName().equals(cdmatch.getPara2())&&ot.getPara().equals(feedback.getPara()))
							return new output(ot); 
					}
				}
			}
			
			return null; 
		};
		this.coreICDistribute = (input) ->  {
			for (coreDistributeMatch cdmatch: coreDistributeMatch.values()){
				if(cdmatch.getType()==2&&cdmatch.getPara1().equals(input.getName())){
					for (callbacks cb : callbacks.values()){
						if(cb.getName().equals(cdmatch.getPara2())&&cb.getPara().equals(input.getPara()))
							return new callback(cb); 
					}
					
				}
			}
			return null;
		};
		//this.coreIODistribute = (input) ->  { return new output(); };
		this.getPostInfo = (httpservletrequest ,  httpservletresponse) ->  { 
			Map<String,Object> map = new HashMap<String, Object>(); 
			for(httpServletRequests rq:httpServletRequests.values()){
				if(rq.getName().equals(httpservletrequest.getName())&&rq.getPara().equals(httpservletrequest.getPara())){
					map.put("para", rq.getPara());
					map.put("result", rq.getResult());
					return map;
				}
			}
			
			return null;
		};
		this.getPostInfoByOwner = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getForumList = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getThemeList = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.addPost = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getTheme = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.editPost = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.deletePost = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.addComment = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.deleteComment = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getOwnThemeList = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getForumReportList = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.getForumReportInfo = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.handleForumReport = (httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.uploadFile = (integer ,  httpservletrequest ,  httpservletresponse) ->  { return new HashMap<String, Object>(); };
		this.moveFile = (string ,  integer ,  user) ->  { return new String(); };
		this.getAlbumList = (integer) ->  { 
			ArrayList<String> paths = new ArrayList<String>();
			int key = 0;
			for(pictures p:pictures.values()){
				if(p.getPostID().equals(integer)){
					paths.add(p.getName());
					key =1;
				}
			}
			if(key==1) 
				return paths;
			else
				return null;
		};
		this.ApiValidateUserLogin = (jsonobject) ->  {
			Map<String,Object> map = new HashMap<String, Object>(); 
			String id = jsonobject.getString("userId");
			for(users u: users.values()){
				if(u.getId().equals(Integer.parseInt(id)))
				{
					map.put("para", Integer.parseInt(id));
					map.put("result", "OK");
					return map;
				}
			}
			return null;
			
		};
		this.selectAll = () ->  { return new ArrayList<ForumInfo>(); };
		this.ifOwner = (integer1 ,  integer2) ->  {
			for (forumOwners fo: forumOwners.values()){
				if(fo.getForumID().equals(integer1) && fo.getOwnerID().equals(integer2)){
					System.out.println(fo);
					return new ForumOwner(fo); 
				}
			}
			return null;
		};
		this.selectKeyByForumID = (integer) ->  { return new ArrayList<Integer>(); };
		this.selectByThemeID = (integer) ->  { return new ArrayList<Integer>(); };
		this.selectByOwnerID = (integer) ->  { return new ArrayList<ForumInfo>(); };
		this.selectThemeListByForumID = (integer) ->  { return new ArrayList<ThemeInfo>(); };
		this.selectPostListByForumID = (map) ->  { return new ArrayList<PostInfo>(); };
		this.selectThemeInfoByPrimaryKey = (integer) ->  { 
			for(themeInfos ti:themeInfos.values()) {
				if(ti.getId().equals(integer))
					return new ThemeInfo(ti);
			}
			return null;
		};
		this.selectPostInfoByPrimaryKey = (integer) ->  { 
			for(postInfos pi:postInfos.values()){
				if(pi.getId().equals(integer))
					return new PostInfo(pi);
			}
			return null;
		};
		//this.selectPostTmpInfoByPrimaryKey = (integer) ->  { return new PostTmpInfo(); };
		this.selectPostCommentInfoByPrimaryKey = (integer) ->  { return new PostCommentInfo(); };
		this.selectForumReportInfoByPrimaryKey = (integer) ->  { return new ForumReportInfo(); };
		this.updatePostInfoByPrimaryKeySelective = (postinfo) ->  { 
			//TODO 修改enum中的该项纪录的内容
			return postinfo.getId(); 
		};
		this.updatePostTmpInfoByPrimaryKeySelective = (posttmpinfo) ->  { return new Integer(0); };
		this.updatePostCommentInfoByPrimaryKeySelective = (postcommentinfo) ->  { return new Integer(0); };
		this.updateForumReportInfoByPrimaryKeySelective = (forumreportinfo) ->  { return new Integer(0); };
		this.insertPostInfoSelective = (postinfo) ->  { return new Integer(0); };
		this.insertPostTmpInfoSelective = (posttmpinfo) ->  { return new Integer(0); };
		this.insertPostCommentInfoSelective = (postcommentinfo) ->  { return new Integer(0); };
		this.insertForumReportInfoSelective = (forumreportinfo) ->  { return new Integer(0); };
		this.selectRecent = (integer) ->  {
			for(postTmpInfos pt:postTmpInfos.values()){
				if(pt.getPostInfoId().equals(integer))
					return new PostTmpInfo(pt);
			}
			return null;
		};
		//this.selectByPostInfoId = (integer) ->  { return new PostTmpInfo(); };
		this.selectPostTmpInfoListByPostInfoId = (integer) ->  { return new ArrayList<PostTmpInfo>(); };
		this.selectPostCommentInfoListByPostInfoId = (integer) ->  { return new ArrayList<PostCommentInfo>(); };
		this.selectListByContentId = (integer) ->  { return new ArrayList<ForumReportInfo>(); };
		this.selectListPagePost = (map) ->  { return new ArrayList<ForumReportInfo>(); };
		this.selectListPageComment = (map) ->  { return new ArrayList<ForumReportInfo>(); };
		this.selectCommentReport = (map) ->  { return new ArrayList<ForumReportInfo>(); };
		this.selectListPagePostNew = (map) ->  { return new ArrayList<ForumReportInfo>(); };
		this.selectListPageCommentNew = (map) ->  { return new ArrayList<ForumReportInfo>(); };
		this.getTotalResult = () ->  { return new Integer(0); };
		this.setShowCount = (integer ) ->  {; };
		this.setCurrentResult = (integer ) ->  {; };
		this.getId = () ->  { return new Integer(0); };
		this.getForumID = () ->  { return new Integer(0); };
		this.getThemeID = () ->  { return new Integer(0); };
		this.getPostID = () ->  { return new Integer(0); };
		this.getPostInfoId  = () ->  { return new Integer(0); };
		this.getContentID = () ->  { return new Integer(0); };
		this.getCreaterID = () ->  { return new Integer(0); };
		this.getReplierID = () ->  { return new Integer(0); };
		this.getReasonType = () ->  { return new Integer(0); };
		this.getContentType  = () ->  { return new Integer(0); };
		this.getVisitNumber = () ->  { return new Integer(0); };
		this.getCommentNumber = () ->  { return new Integer(0); };
		this.getTitle = () ->  { return new String(); };
		this.getText = () ->  { return new String(); };
		this.getState = () ->  { return new Integer(0); };
		this.getInfo = () ->  { return new String(); };
		this.getReporterPortrait = () ->  { return new String(); };
		this.setThemeID = (integer) ->  {; };
		this.setPostID = (integer) ->  {; };
		this.setPostInfoId = (integer) ->  {; };
		this.setCommentID = (integer) ->  {; };
		this.setContentID = (integer) ->  {; };
		this.setCreaterID = (integer) ->  {; };
		this.setReplierID = (integer) ->  {; };
		this.setHandlerID = (integer) ->  {; };
		this.setReasonType = (integer) ->  {; };
		this.setContentType = (integer) ->  {; };
		this.setThemeNum = (integer) ->  {; };
		this.setPostNum = (integer) ->  {; };
		this.setVisitNumber = (integer) ->  {; };
		this.setCommentNumber = (integer) ->  {; };
		this.setState = (integer) ->  {; };
		this.setIsPop = (integer) ->  {; };
		this.setTitle = (string) ->  {; };
		this.setText = (string) ->  {; };
		this.setTags = (string) ->  {; };
		this.setInfo = (string) ->  {; };
		this.setPostTitle = (string) ->  {; };
		this.setCommentInfo = (string) ->  {; };
		this.setHandleInfo = (string) ->  {; };
		this.setReporterPortrait = (string) ->  {; };
		this.setPublishTime = (date) ->  {; };
		this.setReportTime = (date) ->  {; };
		this.setAddTime = (date) ->  {; };
		this.setHandleTime = (date) ->  {; };
		this.setThemeList = (listti) ->  {; };//list<ThemeInfo>

	}

	
	 private static Object getStancefromClass(Class<?> InType,String[] strin){
		 Constructor[] cs = InType.getDeclaredConstructors();
		 System.out.println(InType.getName());
			for (Constructor constructor : cs) {
		        Class[] paramTypes = constructor.getParameterTypes();
		            if(paramTypes.length==strin.length){
		            	try {
							return constructor.newInstance(strin);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            }
		     }
			return null;
	 }
	
	
	public void readParameters() {
		// TODO 从文件lmXML中读取参数
		
	}
	
	public Map<String,Object> evaluate(Map<String,Object> para) {
		// TODO 根据调用图解析获取lambda项列表，并依次调用
		
		// TODO 将每一步的调用结果存入数据库
		
		// TODO 并将最终结果与lmXML中的expectation比较
		
		
		//Example2
		//以下为利用反射方法调用一个输入一个输出的λ项的例子，其它情况类似。
		//注意类名得用全名，因此，ltXML文件中应标记好全称。
		
		Map<String, Object> rs = new HashMap<String, Object>();
		String inputMethodName = (String) para.get("inputMethodName");
		String[] str1 = ((String) para.get("strin")).split(";");
		String[] str2 = ((String) para.get("strout")).split(";");
		/*String[][] strin = str1.split(";");
		String[][] strout = ;*/
		Object[] objin = new Object[str1.length];
		Object[] objout = new Object[str2.length];
		String[] clazIn = ((String) para.get("clazIn")).split(";");
		String[] clazOut = ((String) para.get("clazOut")).split(";");
		
		Class<?> testInType=null,testOutType=null;
		try {
			testInType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
			if(testInType!=null){//所调用方法为类的get方法
				Method m = null;
				try {
					m = testInType.getMethod(inputMethodName);
					if(m!=null){
						
						Class<?> RtnType = null;
						if(clazOut[0].equals("Integer")||clazOut[0].equals("String")){
							try {
								RtnType = Class.forName("java.lang." + clazOut[0]);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								
							}
							
							if(clazOut[0].equals("Integer"))
								objout[0] = new Integer(str2[0].split(",")[0]);
							else
								objout[0] = str2[0].split(",")[0];
						}
						objin[0]=getStancefromClass(testInType,str1[0].split(","));
						Object result=null;
						try {
							System.out.println("Invoking the method " + inputMethodName);
							result = m.invoke(objin[0]);
							rs.put("realOutPara", result);
							rs.put("realOutParaType", result.getClass().getName());
							
							if(result.equals(objout[0])){
								System.out.println("Expectation match reality!");
								rs.put("result", 1);
							}else{
								System.out.println("Expectation not match reality!");
								rs.put("result", 2);
							}
							return rs;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							
						}
						
						
					}
					
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					
				}
				
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			
		}
		try {
			testOutType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			if(testOutType!=null){
				Method m = null;
				Class<?> InType = null;
				if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
					try {
						InType = Class.forName("java.lang." + clazIn[0]);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						
					}
					
					if(clazIn[0].equals("Integer"))
						objin[0] = new Integer(str1[0].split(",")[0]);
					else
						objin[0] = str1[0].split(",")[0];
				}
				try {
					m = testOutType.getMethod(inputMethodName,InType);
					if(m!=null){
						
						objout[0]=getStancefromClass(testOutType,str2[0].split(","));
						Object result=null;
						try {
							System.out.println("Invoking the method " + inputMethodName);
							result = m.invoke(objout[0],objin[0]);
							System.out.println("Invoking succeed!");
							rs.put("realOutPara", result);
							if(result==null)
								rs.put("realOutParaType", "φ");
							else
								rs.put("realOutParaType", result.getClass().getName());
							rs.put("result", 5);
							return rs;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							
						}
						
					}
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					
				}
					
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			
		}
		 
		
		if ((clazIn.length == 0) && (clazOut.length == 1)) {
			Class<?> RtnType = null;
			try {
				
				RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP0R1(inputMethodName, RtnType, objout);
		}else if ((clazIn.length == 1) && (clazOut.length == 0)) {
			Class<?> InType = null;
			try {
				
				InType = Class.forName("java.lang." + clazIn[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP1R0(inputMethodName, InType, objin);
		}else if ((clazIn.length == 1) && (clazOut.length == 1)) {
			Class<?> InType = null, RtnType = null;
			try {
				if(clazIn[0].equals("JSONObject")){
					JSONObject obj = new JSONObject();
				    obj.put("userId", str1[0].split(",")[0]);
				    objin[0] = obj;
				}else {
					if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
						InType = Class.forName("java.lang." + clazIn[0]);
						if(clazIn[0].equals("Integer"))
							objin[0] = new Integer(str1[0].split(",")[0]);
						else
							objin[0] = str1[0].split(",")[0];
					}
						
					else{
						InType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
						objin[0]=getStancefromClass(InType,str1[0].split(","));
					}
				}
				 if(clazOut[0].equals("Map<String, Object>")){
				    	Map<String, Object> map = new HashMap<String, Object>();
				    	map.put("para",Integer.parseInt(str2[0].split(",")[0]));
				    	map.put("result",str2[0].split(",")[1]);
				    	objout[0]= map;
				}else if(clazOut[0].equals("ArrayList<String>")){
					ArrayList<String> paths = new ArrayList<String>();
					for(String p:str2[0].split(",") ){
						paths.add(p);
					}
					objout[0]= paths;
				}else if(clazOut[0].equals("Integer")||clazOut[0].equals("String")){
					RtnType = Class.forName("java.lang." + clazOut[0]);
					if(clazOut[0].equals("Integer"))
						objout[0] = new Integer(str2[0].split(",")[0]);
					else
						objout[0] = str2[0].split(",")[0];
				}else{
				    RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
					objout[0]=getStancefromClass(RtnType,str2[0].split(","));
				}
			    
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.printStackTrace();
			}  catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			rs = reflectLambdaCalculusP1R1(inputMethodName, InType, objin, RtnType, objout);
		}else if ((clazIn.length == 2) && (clazOut.length == 0)) {
			Class<?> InType1 = null,InType2 = null;
			try {
				InType1 = Class.forName("java.lang." + clazIn[0]);
				InType2 = Class.forName("java.lang." + clazIn[1]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP2R0(inputMethodName, InType1, InType2, objin);
		}else if ((clazIn.length == 2) && (clazOut.length == 1)) {
			Class<?> InType1 = null,InType2 = null, RtnType = null;
			try {
				if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
					InType1 = Class.forName("java.lang." + clazIn[0]);
					if(clazIn[0].equals("Integer"))
						objin[0] = new Integer(str1[0].split(",")[0]);
					else
						objin[0] = str1[0].split(",")[0];
				}
				else{
					InType1 = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
					objin[0]=getStancefromClass(InType1,str1[0].split(","));
				}
					
			     
				if(clazIn[1].equals("Integer")||clazIn[1].equals("String")){
					InType2 = Class.forName("java.lang." + clazIn[1]);
					if(clazIn[1].equals("Integer"))
						objin[1] = new Integer(str1[1].split(",")[0]);
					else
						objin[1] = str1[1].split(",")[0];
				}else{
					InType2 = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[1]);
					objin[1]=getStancefromClass(InType2,str1[1].split(","));
				}
					
				
			    if(clazOut[0].equals("Map<String, Object>")){
			    	Map<String, Object> map = new HashMap<String, Object>();
			    	map.put("para",Integer.parseInt(str2[0].split(",")[0]));
			    	map.put("result",str2[0].split(",")[1]);
			    	objout[0]= map;
			    }else{
			    	RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
					objout[0]=getStancefromClass(RtnType,str2[0].split(","));
			    }   
			    	
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = reflectLambdaCalculusP2R1(inputMethodName, InType1, InType2, objin, RtnType, objout);
		}else if ((clazIn.length == 3) && (clazOut.length == 1)) {
			Class<?> InType1 = null,InType2 = null,InType3 = null, RtnType = null;
			try {
				InType1 = Class.forName("java.lang." + clazIn[0]);
				InType2 = Class.forName("java.lang." + clazIn[1]);
				InType3 = Class.forName("java.lang." + clazIn[2]);
				RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP3R1(inputMethodName, InType1, InType2,InType3, objin, RtnType, objout);
		}
		return rs;
		
		
	}
	
	
	public Map<String,Object> evaluateJustForResult(Map<String,Object> para) {
		// TODO 根据调用图解析获取lambda项列表，并依次调用
		
		// TODO 将每一步的调用结果存入数据库
		
		// TODO 并将最终结果与lmXML中的expectation比较
		
		
		//Example2
		//以下为利用反射方法调用一个输入一个输出的λ项的例子，其它情况类似。
		//注意类名得用全名，因此，ltXML文件中应标记好全称。
		
		Map<String, Object> rs = new HashMap<String, Object>();
		String inputMethodName = (String) para.get("inputMethodName");
		String[] str1 = ((String) para.get("strin")).split(";");
		
		
		Object[] objin = new Object[str1.length];
		String[] clazIn = ((String) para.get("clazIn")).split(";");
		String[] clazOut = ((String) para.get("clazOut")).split(";");
		
		Class<?> testInType=null,testOutType=null;
		try {
			testInType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
			if(testInType!=null){//所调用方法为类的get方法
				Method m = null;
				try {
					m = testInType.getMethod(inputMethodName);
					if(m!=null){
						
						
						objin[0]=getStancefromClass(testInType,str1[0].split(","));
						Object result=null;
						try {
							System.out.println("Invoking the method " + inputMethodName);
							result = m.invoke(objin[0]);
							rs.put("realOutPara", result);
							rs.put("realOutParaType", result.getClass().getName());
							return rs;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							
						}
						
						
					}
					
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					
				}
				
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			
		}
		try {
			testOutType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			if(testOutType!=null){
				Method m = null;
				Class<?> InType = null;
				if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
					try {
						InType = Class.forName("java.lang." + clazIn[0]);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						
					}
					
					if(clazIn[0].equals("Integer"))
						objin[0] = new Integer(str1[0].split(",")[0]);
					else
						objin[0] = str1[0].split(",")[0];
				}
				try {
					m = testOutType.getMethod(inputMethodName,InType);
					if(m!=null){
						String[] str2 = ((String) para.get("strout")).split(";");
						Object[] objout = new Object[str2.length];
						
						objout[0]=getStancefromClass(testOutType,str2[0].split(","));
						Object result=null;
						try {
							System.out.println("Invoking the method " + inputMethodName);
							result = m.invoke(objout[0],objin[0]);
							System.out.println("Invoking succeed!");
							rs.put("realOutPara", result);
							if(result==null)
								rs.put("realOutParaType", "φ");
							else
								rs.put("realOutParaType", result.getClass().getName());
							rs.put("result", 5);
							return rs;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							
						}
						
					}
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					
				}
					
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			
		}
		 
		
		if ((clazIn.length == 0) && (clazOut.length == 1)) {
			Class<?> RtnType = null;
			try {
				
				RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP0R1NoObjout(inputMethodName, RtnType);
		}else if ((clazIn.length == 1) && (clazOut.length == 0)) {
			Class<?> InType = null;
			try {
				
				InType = Class.forName("java.lang." + clazIn[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP1R0(inputMethodName, InType, objin);
		}else if ((clazIn.length == 1) && (clazOut.length == 1)) {
			Class<?> InType = null, RtnType = null;
			try {
				if(clazIn[0].equals("JSONObject")){
					JSONObject obj = new JSONObject();
				    obj.put("userId", str1[0].split(",")[0]);
				    objin[0] = obj;
				}else {
					if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
						InType = Class.forName("java.lang." + clazIn[0]);
						if(clazIn[0].equals("Integer"))
							objin[0] = new Integer(str1[0].split(",")[0]);
						else
							objin[0] = str1[0].split(",")[0];
					}
						
					else{
						InType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
						objin[0]=getStancefromClass(InType,str1[0].split(","));
					}
				}
				
			    
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.printStackTrace();
			}  catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			rs = reflectLambdaCalculusP1R1NoObjout(inputMethodName, InType, objin, RtnType);
		}else if ((clazIn.length == 2) && (clazOut.length == 0)) {
			Class<?> InType1 = null,InType2 = null;
			try {
				InType1 = Class.forName("java.lang." + clazIn[0]);
				InType2 = Class.forName("java.lang." + clazIn[1]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP2R0(inputMethodName, InType1, InType2, objin);
		}else if ((clazIn.length == 2) && (clazOut.length == 1)) {
			Class<?> InType1 = null,InType2 = null, RtnType = null;
			try {
				if(clazIn[0].equals("Integer")||clazIn[0].equals("String")){
					InType1 = Class.forName("java.lang." + clazIn[0]);
					if(clazIn[0].equals("Integer"))
						objin[0] = new Integer(str1[0].split(",")[0]);
					else
						objin[0] = str1[0].split(",")[0];
				}
				else{
					InType1 = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[0]);
					objin[0]=getStancefromClass(InType1,str1[0].split(","));
				}
					
			     
				if(clazIn[1].equals("Integer")||clazIn[1].equals("String")){
					InType2 = Class.forName("java.lang." + clazIn[1]);
					if(clazIn[1].equals("Integer"))
						objin[1] = new Integer(str1[1].split(",")[0]);
					else
						objin[1] = str1[1].split(",")[0];
				}else{
					InType2 = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazIn[1]);
					objin[1]=getStancefromClass(InType2,str1[1].split(","));
				}
					
				
			    
			    	
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = reflectLambdaCalculusP2R1NoObjout(inputMethodName, InType1, InType2, objin, RtnType);
		}else if ((clazIn.length == 3) && (clazOut.length == 1)) {
			Class<?> InType1 = null,InType2 = null,InType3 = null, RtnType = null;
			try {
				InType1 = Class.forName("java.lang." + clazIn[0]);
				InType2 = Class.forName("java.lang." + clazIn[1]);
				InType3 = Class.forName("java.lang." + clazIn[2]);
				RtnType = Class.forName("com.saml.samvs.types.EnvDefinedBasicTypes." + clazOut[0]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reflectLambdaCalculusP3R1NoObjout(inputMethodName, InType1, InType2,InType3, objin, RtnType);
		}
		return rs;
		
		
	}
	
	/**
	 * 
	 * @author EW
	 * Check the method @methodName result with the expected result.
	 * 根据方法 @methodName 的调用结果与预期结果进行比较。
	 *
	 */
	public boolean checkExptResult(String methodName, Object[] objout,Object[] expt) {
		if ((objout==null) || (expt == null)) {
			System.out.println("Evaluated " + " ≠ Expected. ");
			return false;
		}else {
			//TODO  获取方法名对应的期望值
			System.out.println("Evaluated " + objout.toString() + " = Expected " + expt.toString());
			return (objout.equals(expt)? true: false);
		}
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P1R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P1R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有一个输入和一个输出的λ项的调用。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType, RtnType> Map<String,Object> reflectLambdaCalculusP1R1(String methodName, InType in, Object[] objin, RtnType rtn, Object[] objout) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				Map<String, Object> r = new HashMap<String, Object>();
				if (field.get(this) instanceof P1R1Function<?, ?>) {
					RtnType trueResult =((P1R1Function<InType, RtnType>) field.get(this)).lambdaFunction((InType)objin[0]);
					
					if(trueResult==null){
						r.put("result", 3);
						System.out.println("No data with input conditions!");
					}else{
						r.put("realOutPara", trueResult);
						r.put("realOutParaType", trueResult.getClass().getName());
						if(objout.length==0){
							System.out.println("No expectation input!");
							r.put("result", 4);
						}else if(trueResult.equals((RtnType)objout[0])){
							r.put("result", 1);
							System.out.println("Expectation match reality!");
						}else{
							r.put("result", 2);
							System.out.println("Expectation not match reality!");
						}
					}
					
				}
				return r;
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
			
	}

	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P0R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P0R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有零个输入和一个输出的λ项的调用。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <RtnType> void reflectLambdaCalculusP0R1(String methodName, RtnType rtn, Object[] objout) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P0R1Function<?>) {
					RtnType trueResult = (RtnType) ((P0R1Function<RtnType>) field.get(this)).lambdaFunction();
					if(trueResult.equals(null))
						System.out.println("No data with input conditions!");
					else if(objout.length==0)
						System.out.println("No expectation input!");
					else if(trueResult.equals(objout[0])){
						System.out.println("Expectation match reality!");
					}else{
						System.out.println("Expectation not match reality!");
					}
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P1R0Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P1R0Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有一个输入和零个输出的λ项的调用。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType1> void reflectLambdaCalculusP1R0(String methodName, InType1 inType1, Object[] objin) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P1R0Function<?>) {
					((P1R0Function<InType1>) field.get(this)).lambdaFunction((InType1)objin[0]);
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P2R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P2R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有两个输入和一个输出的λ项的调用。
	 * @return 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType1, InType2, RtnType> Map<String, Object> reflectLambdaCalculusP2R1(String methodName, InType1 in1, InType2 in2, Object[] objin, RtnType rtn, Object[] objout) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				Map<String, Object> r = new HashMap<String, Object>();
				if (field.get(this) instanceof P2R1Function<?, ?, ?>) {
					RtnType trueResult = ((P2R1Function<InType1, InType2, RtnType>) field.get(this)).lambdaFunction((InType1)objin[0], (InType2)objin[1]);
					
					
					if(trueResult==null){
						System.out.println("No data with input conditions!");
						r.put("result", 3);
					}else{
						r.put("realOutPara", trueResult);
						r.put("realOutParaType", trueResult.getClass().getName());
						if(objout.length==0){
							r.put("result", 4);
							System.out.println("No expectation input!");
						}else if(trueResult instanceof Map){
							if(((Map) trueResult).get("para").equals(((Map)objout[0]).get("para"))&&
							((Map) trueResult).get("result").equals(((Map)objout[0]).get("result"))){
								r.put("result", 1);
								System.out.println("Expectation match reality!");
							}else{
								r.put("result", 2);
								System.out.println("Expectation not match reality!");
							}
						}else{
							if(trueResult.equals(objout[0])){
								r.put("result", 1);
								System.out.println("Expectation match reality!");
							}else{
								r.put("result", 2);
								System.out.println("Expectation not match reality!");
							}
						}
						
					}
					return r;
						
						
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 	
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P2R0Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P2R0Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有两个输入和零个输出的λ项的调用。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType1, InType2> void reflectLambdaCalculusP2R0(String methodName, InType1 in1, InType2 in2, Object[] objin) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P2R0Function<?, ?>) {
					((P2R0Function<InType1, InType2>) field.get(this)).lambdaFunction((InType1)objin[0], (InType2)objin[1]);
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P3R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P3R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有三个输入和一个输出的λ项的调用。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType1, InType2, InType3, RtnType> void reflectLambdaCalculusP3R1(String methodName, InType1 in1, InType2 in2, InType3 in3, Object[] objin, RtnType rtn, Object[] objout) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P3R1Function<?, ?, ?, ?>) {
					RtnType trueResult = ((P3R1Function<InType1, InType2, InType3, RtnType>) field.get(this)).lambdaFunction((InType1)objin[0], (InType2)objin[1], (InType3)objin[2]);
					if(trueResult.equals(null))
						System.out.println("No data with input conditions!");
					else if(objout.length==0)
						System.out.println("No expectation input!");
					else if(trueResult.equals(objout[0])){
						System.out.println("Expectation match reality!");
					}else{
						System.out.println("Expectation not match reality!");
					}
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P0R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P0R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有零个输入和一个输出的λ项的调用。
	 * 函数的参数不包括输出对象
	 */
	@SuppressWarnings("unchecked")
	public <RtnType> void reflectLambdaCalculusP0R1NoObjout(String methodName, RtnType rtn) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P0R1Function<?>) {
					RtnType trueResult = (RtnType) ((P0R1Function<RtnType>) field.get(this)).lambdaFunction();
					
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P1R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P1R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有一个输入和一个输出的λ项的调用。
	 * 函数的参数不包括输出对象
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType, RtnType> Map<String,Object> reflectLambdaCalculusP1R1NoObjout(String methodName, InType in, Object[] objin, RtnType rtn) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				Map<String, Object> r = new HashMap<String, Object>();
				if (field.get(this) instanceof P1R1Function<?, ?>) {
					RtnType trueResult =((P1R1Function<InType, RtnType>) field.get(this)).lambdaFunction((InType)objin[0]);
					
					if(trueResult==null){
						r.put("result", 3);
						System.out.println("No data with input conditions!");
					}else{
						r.put("realOutPara", trueResult);
						r.put("realOutParaType", trueResult.getClass().getName());
						
					}
					
				}
				return r;
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
			
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P2R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P2R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有两个输入和一个输出的λ项的调用。
	 * 函数的参数不包括输出对象
	 * @return 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <InType1, InType2, RtnType> Map<String, Object> reflectLambdaCalculusP2R1NoObjout(String methodName, InType1 in1, InType2 in2, Object[] objin, RtnType rtn) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				Map<String, Object> r = new HashMap<String, Object>();
				if (field.get(this) instanceof P2R1Function<?, ?, ?>) {
					RtnType trueResult = ((P2R1Function<InType1, InType2, RtnType>) field.get(this)).lambdaFunction((InType1)objin[0], (InType2)objin[1]);
					
					
					if(trueResult==null){
						System.out.println("No data with input conditions!");
						r.put("result", 3);
					}else{
						r.put("realOutPara", trueResult);
						r.put("realOutParaType", trueResult.getClass().getName());
						
						
					}
					return r;
						
						
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 	
	}
	
	/**
	 * 
	 * @author EW
	 * Invoking the input @methodName method, by reflect it to the particular λ-term
	 * of P3R1Function and execute its lambdaFunction method.
	 * 根据输入方法名称 @methodName，通过反射调用P3R1Function对应的λ项的lambdaFunction方法。
	 * 适用于所有只有三个输入和一个输出的λ项的调用。
	 * 函数的参数不包括输出对象
	 */
	@SuppressWarnings("unchecked")
	public <InType1, InType2, InType3, RtnType> void reflectLambdaCalculusP3R1NoObjout(String methodName, InType1 in1, InType2 in2, InType3 in3, Object[] objin, RtnType rtn) {
		Field field = null;
		try {
			field = this.getClass().getField(methodName);
			try {
				System.out.println("Invoking the method " + methodName);
				
				if (field.get(this) instanceof P3R1Function<?, ?, ?, ?>) {
					RtnType trueResult = ((P3R1Function<InType1, InType2, InType3, RtnType>) field.get(this)).lambdaFunction((InType1)objin[0], (InType2)objin[1], (InType3)objin[2]);
					
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
		
}
