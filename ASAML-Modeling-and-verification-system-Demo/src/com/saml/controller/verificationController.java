package com.saml.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saml.inter.DNMappingMapper;
import com.saml.inter.DTMappingMapper;
import com.saml.inter.DemandMapper;
import com.saml.inter.DemandRelationMapper;
import com.saml.inter.EvaluationMapper;
import com.saml.inter.InvokableNodeMapper;
import com.saml.inter.ReachablePathMapper;
import com.saml.inter.SamlTypeMapper;
import com.saml.inter.TypeRuleMapper;
import com.saml.inter.VerifyResultMapper;
import com.saml.model.DNMapping;
import com.saml.model.DTMapping;
import com.saml.model.Demand;
import com.saml.model.DemandAndRelation;
import com.saml.model.DemandRelation;
import com.saml.model.Evaluation;
import com.saml.model.InvokableNode;
import com.saml.model.ReachablePath;
import com.saml.model.SamlType;
import com.saml.model.TypeRule;
import com.saml.model.VerifyResult;
import com.saml.samvs.types.EnvDefinedMappingTypes.LambdaExpressions;
import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;
import com.saml.util.PageInfo;
import com.saml.util.readEXCEL;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/** 
* @ClassName: verificationController 
* @Description: 验证Controller
* @author zhangsiqing
* @date 2018年7月13日 下午9:51:18 
*  
*/
@Controller
@RequestMapping("/verify")
public class verificationController {
	@Autowired
	DemandMapper demandMapper;
	@Autowired
	DemandRelationMapper demandRelationMapper;
	@Autowired
	DTMappingMapper dTMappingMapper;
	@Autowired 
	SamlTypeMapper samlTypeMapper;
	@Autowired
	VerifyResultMapper verifyResultMapper;
	@Autowired
	ReachablePathMapper reachablePathMapper;
	@Autowired
	InvokableNodeMapper invokableNodeMapper;
	@Autowired
	DNMappingMapper dNMappingMapper;
	@Autowired
	TypeRuleMapper typeRuleMapper;
	@Autowired
	EvaluationMapper evaluationMapper;
	
	
	public final static String inParaOfPath1 = "点击帖子超链接,5";
	public final static String outParaOfPath1 = "帖子详情div.output,5";
	
	
	
	/** 
	* @Title: getvefiryExp 
	* @Description: 获取结构验证的验证公式 
	* @param @param request 传入projectId
	* @param @param response
	* @param @return  返回公式的字符串形式
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/getvefiryExp",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getvefiryExp(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				int demandtotal = 0;
				for(int i =0 ; i<4;i++){
					demandtotal +=  demandMapper.selectDemandCountByLevel(projectId,i+1);
				}
				int demandRelationtotal = demandRelationMapper.selectDemandRelationCount(projectId);
				String exp = "";
				if(demandtotal!=0||demandRelationtotal!=0)
					exp +="将功能构成应满足需求的性质记为P<sub>s</sub>，其公式定义如下，<br />P<sub>s</sub>&nbsp;=";
				else
					return JsonHandler.writeJsontoResponse(1006,exp);
				if(demandtotal>1){
					exp += "&nbsp;D<sub>1</sub>&nbsp;∧…∧&nbsp;D<sub>"+demandtotal+"</sub>";
				}else if(demandtotal==1){
					exp += "&nbsp;D<sub>1</sub>&nbsp;";
				}
				if(demandRelationtotal>1){
					exp += "∧&nbsp;R<sub>1</sub>&nbsp;∧…∧&nbsp;R<sub>"+demandRelationtotal+"</sub>";
				}else if(demandRelationtotal==1){
					exp += "∧&nbsp;R<sub>1</sub>&nbsp;";
				}
				return JsonHandler.writeJsontoResponse(1000,exp);
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	/** 
	* @Title: verifyDemand 
	* @Description: 结构验证
	* @param @param request 传入projectId和id(需求id)
	* @param @param response
	* @param @return  返回验证结果及错误信息
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/verifyDemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verifyDemand(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int id = obj.getInt("id");
				int projectId = obj.getInt("projectId");
				String errorResponse = "";
				if(id==0){//验证全部
					List<Demand> demandList = demandMapper.selectDemandList(projectId);
					for(Demand d : demandList){
						List<DTMapping>  dtList =dTMappingMapper.selectByDemandId(d.getId());
						//验证demand
						if(dtList.size()==0)
							errorResponse += "D"+d.getId()+"缺少对应类型，验证不通过;<br />";
						for(DTMapping dt:dtList){
							if(samlTypeMapper.selectByPrimaryKey(dt.getTypeId())==null){
								errorResponse += "D"+d.getId()+"对应类型不存在，验证不通过;<br />";
							}else{
								int state = samlTypeMapper.selectByPrimaryKey(dt.getTypeId()).getState();
								if(state!=1)
									errorResponse += "D"+d.getId()+"对应类型不存在，验证不通过;<br />";
							}
							
						}
						//验证demandRelation
						List<DemandRelation>  drList = demandRelationMapper.selectDemandRelationListByCombinationId(d.getId());
						for(DemandRelation dr : drList){
							List<DTMapping>  drtList =dTMappingMapper.selectByDemandId(dr.getElementId());
							if(drtList.size()==0)
								errorResponse += "D"+d.getId()+"功能构成D"+dr.getElementId()+"缺少对应类型，性质R"+dr.getId()+"验证不通过;<br />";
							for(DTMapping drt:drtList){
								if(samlTypeMapper.selectByPrimaryKey(drt.getTypeId())==null){
									errorResponse += "D"+d.getId()+"功能构成D"+dr.getElementId()+"对应类型不存在，性质R"+dr.getId()+"验证不通过;<br />";
								}else{
									int state = samlTypeMapper.selectByPrimaryKey(drt.getTypeId()).getState();
									if(state!=1)
										errorResponse += "D"+d.getId()+"功能构成D"+dr.getElementId()+"对应类型不存在，性质R"+dr.getId()+"验证不通过;<br />";	
								}
								
							}
						}
					}
				}else{
					//验证demand
					List<DTMapping>  dtList =dTMappingMapper.selectByDemandId(id);
					if(dtList.size()==0)
						return JsonHandler.writeJsontoResponse(1004,"");
					for(DTMapping dt:dtList){
						if(samlTypeMapper.selectByPrimaryKey(dt.getTypeId())==null){
							return JsonHandler.writeJsontoResponse(1005,"");
						}else{
							int state = samlTypeMapper.selectByPrimaryKey(dt.getTypeId()).getState();
							if(state!=1)
								return JsonHandler.writeJsontoResponse(1005,"");	
						}
						
					}
					//验证demandRelation
					
					List<DTMapping>  drtList =dTMappingMapper.selectByDemandId(id);
					if(drtList.size()==0)
						return JsonHandler.writeJsontoResponse(1007,"");
					for(DTMapping drt:drtList){
						if(samlTypeMapper.selectByPrimaryKey(drt.getTypeId())==null){
							return JsonHandler.writeJsontoResponse(1008,"");
						}else{
							int state = samlTypeMapper.selectByPrimaryKey(drt.getTypeId()).getState();
							if(state!=1)
								return JsonHandler.writeJsontoResponse(1008,"");
						}
						
							
					}
					
				}
				return JsonHandler.writeJsontoResponse(1000,errorResponse);
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	/** 
	* @Title: generatePath 
	* @Description: 生成可达路径及调用节点列表并存入数据库
	* @param @param request 传入projectId、调用图XML文件路径、调用图结点解释EXCEL文件路径
	* @param @param response
	* @param @return  错误代码
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/generatePath",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> generatePath(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				String path = obj.getString("XMLpath");
				String path2 = obj.getString("EXCELpath");
				int id = 0;//verifyResultId
				int sign = 0;//是否已经存在相关记录
				if(verifyResultMapper.selectByProjectId(projectId) != null){
					VerifyResult vr =verifyResultMapper.selectByProjectId(projectId) ;
					vr.setXmlPath(path);
					vr.setExcelPath(path2);
					vr.setAddTime(new Date());
					verifyResultMapper.updateByPrimaryKeySelective(vr);
					id= vr.getId();
					sign = 1;
				}else{
					VerifyResult vr = new VerifyResult();
					vr.setProjectId(projectId);
					vr.setXmlPath(path);
					vr.setExcelPath(path2);
					vr.setAddTime(new Date());
					vr.setState(1);
					verifyResultMapper.insertSelective(vr);
					id= vr.getId();
				}
				//生成可达路径列表，并存入数据库中
				ArrayList<String> testCases = SAMVSController.generatePath(path);
				if(sign==1)
					reachablePathMapper.logicalDeleteListByProjectId(projectId);
				for(String tc :testCases){
					ReachablePath rp = new ReachablePath();
					rp.setProjectId(projectId);
					rp.setVerifyresultId(id);
					rp.setAddTime(new Date());
					rp.setPathExp(tc);
					rp.setState(1);
					reachablePathMapper.insertSelective(rp);
				}
				path2 = new FileController().readLocalFilePathConfig("localFilePath")+path2;
				//从excel去读结点列表，存入数据库
				List<List<String>> list = readEXCEL.readExcel(path2);
				if(sign==1){
					invokableNodeMapper.logicalDeleteListByProjectId(projectId);
					dNMappingMapper.logicalDeleteListByProjectId(projectId);
					demandMapper.logicalDeleteListByProjectIdAndLevel(projectId,5);
					demandMapper.logicalDeleteListByProjectIdAndLevel(projectId,6);
				}
				for(List<String> l :list){
					InvokableNode node = new InvokableNode();
					node.setNodeId(l.get(0));
					node.setNodeName(l.get(1));
					node.setType(Integer.parseInt(l.get(2)));
					node.setProjectId(projectId);
					node.setVerifyresultId(id);
					node.setAddTime(new Date());
					node.setState(1);
					invokableNodeMapper.insertSelective(node);
				}
				return JsonHandler.writeJsontoResponse(1000,"ok");
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	
	/** 
	* @Title: verEvaluationByExcel 
	* @Description: 根据EXCEL文件验证λ求值 
	* @param @param request
	* @param @param response
	* @param @return  验证结果错误码
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/verEvaluationByExcel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verEvaluationByExcel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				int id = 0;//verifyResultId
				String path2 = obj.getString("EXCELpath");
				String error = "";
				VerifyResult vr = verifyResultMapper.selectByProjectId(projectId) ;
				int totalresult = 0;
				int total = 0;
				if(!path2.equals(""))//新上传了excel，删除验证参数表的相关记录，新读入文件并进行求值，存入到参数表。
				{
					if(vr.getEvaluationExcel()!=null&&vr.getEvaluationExcel()!="")//原有路径不为空，删除原有路径相关的数据表记录
						evaluationMapper.logicalDeleteListByProjectId(projectId);
					vr.setEvaluationExcel(path2);
					vr.setAddTime(new Date());
					verifyResultMapper.updateByPrimaryKeySelective(vr);
					id= vr.getId();
					//读取excel，并处理存入到数据库中
					path2 = new FileController().readLocalFilePathConfig("localFilePath")+path2;
					List<List<String>> list = readEXCEL.readExcel(path2);
				
					//实例化lambda表达式的对象
					LambdaExpressions lambdaExpressions = new LambdaExpressions();
					lambdaExpressions.initiate();
				
					total = list.size();
					for(List<String> l :list){
						Evaluation e = new Evaluation();
						e.setProjectId(projectId);
						e.setVerifyresultId(id);
						e.setLabel(l.get(0));
						e.setInParaType(l.get(1));
						e.setOutParaType(l.get(2));
						e.setInPara(l.get(3));
						e.setOutPara(l.get(4));
						if(l.get(1).equals("φ")||l.get(2).equals("φ")){
							e.setClassType(l.get(5));
						}
						e.setState(1);
						
						//获取并存入参数到参数map中
						Map<String, Object> para = new HashMap<String, Object>();
						para.put("inputMethodName", l.get(0));
						if(l.get(1).equals("φ")){//调用的是get方法
							para.put("clazIn", l.get(5));
						}else{
							para.put("clazIn", l.get(1));
						}
						if(l.get(2).equals("φ")){//调用的是set方法
							para.put("clazOut", l.get(5));
						}else{
							para.put("clazOut", l.get(2));
						}	
						para.put("strin", l.get(3));
						para.put("strout", l.get(4));
						//执行lambda演算函数
						Map<String, Object> rs = lambdaExpressions.evaluate(para);
						Integer result = (Integer) rs.get("result");
						if(result==1||result==5){
							totalresult+=1;
						}else if(result==2)
							error+=l.get(0)+" Expectation not match reality;";
						else if(result==3)
							error+=l.get(0)+" No data with input conditions;";
						else if(result==4)
							error+=l.get(0)+" No expectation input;";
						else if(result==6)
							error+=l.get(0)+" NoSuchMethod;";
						else if(result==6)
							error+=l.get(0)+" ClassNotFound;";
						/*System.out.println(result);
						System.out.println(rs.get("realOutPara").toString());
						System.out.println(rs.get("realOutParaType").toString());*/
						e.setResult(result);
						if(rs.get("realOutPara")!=null)
							e.setRealOutPara(rs.get("realOutPara").toString());
						if(rs.get("realOutParaType")!=null)
							e.setRealOutParaType(rs.get("realOutParaType").toString());
						evaluationMapper.insertSelective(e);
					}
				}else{//未上传新的excel，从参数表直接读取内容并验证，再将验证结果更新到数据表
					//实例化lambda表达式的对象
					LambdaExpressions lambdaExpressions = new LambdaExpressions();
					lambdaExpressions.initiate();
					List<Evaluation> elist = evaluationMapper.selectEvaluationListByProjectId(projectId);
					total = elist.size();
					for(Evaluation e :elist){
						
						//获取并存入参数到参数map中
						Map<String, Object> para = new HashMap<String, Object>();
						para.put("inputMethodName", e.getLabel());
						if(e.getInParaType().equals("φ")){//调用的是get方法
							para.put("clazIn", e.getClassType());
						}else{
							para.put("clazIn", e.getInParaType());
						}
						if(e.getOutParaType().equals("φ")){//调用的是set方法
							para.put("clazOut", e.getClassType());
						}else{
							para.put("clazOut", e.getOutParaType());
						}	
						para.put("strin", e.getInPara());
						para.put("strout",e.getOutPara());
						//执行lambda演算函数
						Map<String, Object> rs = lambdaExpressions.evaluate(para);
						Integer result = (Integer) rs.get("result");
						if(result==1||result==5){
							totalresult+=1;
						}else if(result==2)
							error+=e.getLabel()+" Expectation not match reality;";
						else if(result==3)
							error+=e.getLabel()+" No data with input conditions;";
						else if(result==4)
							error+=e.getLabel()+" No expectation input;";
						else if(result==6)
							error+=e.getLabel()+" NoSuchMethod;";
						else if(result==6)
							error+=e.getLabel()+" ClassNotFound;";
						e.setResult(result);
						if(rs.get("realOutPara")!=null)
							e.setRealOutPara(rs.get("realOutPara").toString());
						if(rs.get("realOutParaType")!=null)
							e.setRealOutParaType(rs.get("realOutParaType").toString());
						evaluationMapper.updateByPrimaryKeySelective(e);
					}
				}
				
				if(totalresult==total)
					return JsonHandler.writeJsontoResponse(1000,"ok");
				else
					return JsonHandler.writeJsontoResponse(1018,error);
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	/** 
	* @Title: verEvaluationByPath 
	* @Description: 根据可达路径验证λ求值
	* @param @param request
	* @param @param response
	* @param @return  验证错误代码及错误信息
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/verEvaluationByPath",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verEvaluationByPath(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				int rid = obj.getInt("rid");
				int sign = 0;
				String error = "";
				VerifyResult vr = verifyResultMapper.selectByProjectId(projectId) ;
				ReachablePath rp = reachablePathMapper.selectByPrimaryKey(rid);
				//实例化lambda表达式的对象
				LambdaExpressions lambdaExpressions = new LambdaExpressions();
				lambdaExpressions.initiate();
				String rpath =rp.getPathExp().replaceAll("\\s*", "");
				List<String> methods = new ArrayList<String>();
				String[] tem = rpath.split(";");
				for(int i =0;i<tem.length;i++){//获取可达路径的节点列表
					if(i>1&&i<tem.length-1){
						methods.add(tem[i]);
					}
						
				}
				
				List<List<Integer>> alltypeList= new  ArrayList<List<Integer>>();
				List<List<Integer>> allptypeList= new ArrayList<List<Integer>>();
				for(int i=0;i<methods.size();i++){
					List<Integer>typeList= new ArrayList<Integer>();
					List<Integer>ptypeList= new ArrayList<Integer>();
					System.out.println(methods.get(i));
					if(methods.get(i).split(",").length>1){
						String []  list = methods.get(i).split(",");
						for(String nodeid: list){
							InvokableNode node = invokableNodeMapper.selectByNodeId(nodeid, projectId);
							if(node == null){
								error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
								return JsonHandler.writeJsontoResponse(1011,error);
							}else{
								List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
								if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
									error += methods.get(i)+"缺少对应需求分析;";
									return JsonHandler.writeJsontoResponse(1012,error);
								}else{
									int tem1 = 0;
									for(DNMapping dnm:dnmList){
										Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
										List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
										List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
										if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
											tem1 = 0;
											error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
											continue;
											//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
										}else if(pdtmList.size()==0){
											tem1 = 0;
											error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
											continue;
											//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
										}else{
											tem1 =1;
											for(DTMapping dt:dtmList){
												if(!typeList.contains(dt.getTypeId()))
													typeList.add(dt.getTypeId());//同一node下所有demand对应的type都存入到这个list中，存入前需检查是否已经存在该类
											}
											for(DTMapping pdt:pdtmList){
												ptypeList.add(pdt.getTypeId());
											}
										}
										if(tem1==1){
											sign=1;
											error="";
										}
										
									}
									
									
								}
								
							}
						}
						
					}else{
						InvokableNode node = invokableNodeMapper.selectByNodeId(methods.get(i), projectId);
						if(node == null){
							error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
							return JsonHandler.writeJsontoResponse(1011,error);
						}else{
							List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
							if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
								error += methods.get(i)+"缺少对应需求分析;";
								return JsonHandler.writeJsontoResponse(1012,error);
							}else{
								int tem1 = 0;
								for(DNMapping dnm:dnmList){
									Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
									List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
									List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
									if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
										tem1 = 0;
										error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
										continue;
										//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
									}else if(pdtmList.size()==0){
										tem1 = 0;
										error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
										continue;
										//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
									}else{
										tem1 =1;
										for(DTMapping dt:dtmList){
											if(!typeList.contains(dt.getTypeId()))
												typeList.add(dt.getTypeId());
										}
										for(DTMapping pdt:pdtmList){
											ptypeList.add(pdt.getTypeId());
										}
									}
									if(tem1==1){
										sign=1;
										error="";
									}
								}
							}
						}
					}
					alltypeList.add(typeList);
					allptypeList.add(ptypeList);
				}
				if(sign==0){
					return JsonHandler.writeJsontoResponse(1013,error);
				}
				int allresult = 0;	
			/*	if(sign==1){
					List<List<String>> inparas = new ArrayList<List<String>>();
					List<String> inpara1 = new ArrayList<String>();
					inpara1.add(inParaOfPath1);
					inparas.add(inpara1);
					for(int i=0;i<alltypeList.size();i++){
								List<Integer> typeList = alltypeList.get(i);
								for(int j=0;j<typeList.size();j++ ){
									int ptypeid = allptypeList.get(i).get(j);
									SamlType pst = samlTypeMapper.selectByPrimaryKey(ptypeid);
									SamlType st = samlTypeMapper.selectByPrimaryKey(typeList.get(j));
									String exp = st.getTypeExpression();
									String clazIn = null;
									String clazOut = null;
									if(exp.indexOf("Aggregation")>=0){//输入参数为多个
										String temId = null;
										temId = exp.split(")")[1].split(",")[1];
										if(temId.equals("")){//输出参数为空，将父类型的typename传入为输出参数
											clazOut = pst.getTypeName();
										}else{//输出参数不为空
											clazOut = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(temId)).getTypeName();
										}
											
										temId = exp.split(")")[0].replaceAll("(", "").replaceAll(",Aggregation", "");
										String[] tid = temId.split(",");
										for(int k = 0;k<tid.length;k++){
											clazIn += samlTypeMapper.selectByPrimaryKey(Integer.parseInt(tid[k])).getTypeName();
											if(k<tid.length-1)
												clazIn +=";";
										}
									}else{//输入参数为单个
										exp = exp.replaceAll("(", "").replace(")","");
										String temId = null;
										temId= exp.split(",")[0];
										if(temId.equals("")){//输入参数为空，将父类型的typename传入为输出参数
											clazIn = pst.getTypeName();
										}else{//输出参数不为空
											clazIn = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(temId)).getTypeName();
										}
										
										temId = exp.split(",")[1];
										if(temId.equals("")){//输出参数为空，将父类型的typename传入为输出参数
											clazOut = pst.getTypeName();
										}else{//输出参数不为空
											clazOut = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(temId)).getTypeName();
										}
									}
									String label = st.getTypeName();
									for(String inpara: inparas.get(i)){
										Map<String,Object> para = new HashMap<String,Object>();
										para.put("inputMethodName", label);
										para.put("clazIn",clazIn);
										para.put("clazOut",clazOut);
										para.put("strin", inpara);
										if(pst.getTypeClassification()==4&&pst.getTypelevel()==3){//如果该类的父类型是接口层的model
											para.put("strout", ev.getOutPara());
										}
										
										
										//执行lambda演算函数
										Map<String, Object> rs = lambdaExpressions.evaluateJustForResult(para);
										Integer result = (Integer) rs.get("result");
										if(result==1||result==5)
											allresult+=1;
										else if(result==2)
											error+=label+"Expectation not match reality";
										else if(result==3)
											error+=label+"No data with input conditions";
										else if(result==4)
											error+=label+"No expectation input";
										else if(result==6)
											error+=label+"NoSuchMethod";
										else if(result==6)
											error+=label+"ClassNotFound";
										
										System.out.println(result);
										System.out.println(rs.get("realOutPara").toString());
										System.out.println(rs.get("realOutParaType").toString());
									}
									
									
								}			
						
						
					
					}
					
					
				}*/
				if(sign==1){
					//List<List<String>> inparas = new ArrayList<List<String>>();
					//inparas.get(0).add(inParaOfPath1);
					for(int i=0;i<alltypeList.size();i++){
								List<Integer> typeList = alltypeList.get(i);
								for(int j=0;j<typeList.size();j++ ){
									int ptypeid = allptypeList.get(i).get(j);
									SamlType pst = samlTypeMapper.selectByPrimaryKey(ptypeid);
									SamlType st = samlTypeMapper.selectByPrimaryKey(typeList.get(j));
									String label = st.getTypeName();
									//for(String inpara: inparas.get(0)){
										Map<String,Object> para = new HashMap<String,Object>();
										Evaluation ev = null;
										System.out.println(label);
										if(pst.getTypeClassification()==4&&pst.getTypelevel()==3){//如果该类的父类型是接口层的model
											ev  = evaluationMapper.selectByLabelAndClassType(label,pst.getTypeName());
										}else{
											ev = evaluationMapper.selectByLabel(label);
										}
										
										para.put("inputMethodName", label);
										if(ev.getInParaType().equals("φ")){//调用的是get方法
											para.put("clazIn",ev.getClassType());
										}else{
											para.put("clazIn", ev.getInParaType());
										}
										if(ev.getOutParaType().equals("φ")){//调用的是set方法
											para.put("clazOut", ev.getClassType());
										}else{
											para.put("clazOut", ev.getOutParaType());
										}	
										para.put("strin", ev.getInPara());
										para.put("strout", ev.getOutPara());
										//执行lambda演算函数
										Map<String, Object> rs = lambdaExpressions.evaluate(para);
										Integer result = (Integer) rs.get("result");
										if(result==1||result==5)
											allresult+=1;
										else if(result==2)
											error+=label+" Expectation not match reality;";
										else if(result==3)
											error+=label+" No data with input conditions;";
										else if(result==4)
											error+=label+" No expectation input;";
										else if(result==6)
											error+=label+" NoSuchMethod;";
										else if(result==6)
											error+=label+" ClassNotFound;";
										
										/*System.out.println(result);
										System.out.println(rs.get("realOutPara").toString());
										System.out.println(rs.get("realOutParaType").toString());*/
									//}
									
									
								}			
						
						
					
					}
					
					
				}	
					
				if(allresult<alltypeList.size())
					return JsonHandler.writeJsontoResponse(1018,error);
				else
					return JsonHandler.writeJsontoResponse(1000,"ok");
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	/** 
	* @Title: getResultInfo 
	* @Description: 获取对应项目的验证结果信息表的内容
	* @param @param request 传入projectId
	* @param @param response verifyResult表该projectId的所有信息
	* @param @return  参数说明 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/getResultInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResultInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				
					VerifyResult vr =verifyResultMapper.selectByProjectId(projectId) ;
					
				//生成测试样例列表，并存入数据库中
				
				return JsonHandler.writeJsontoResponse(1000,vr);
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	/** 
	* @Title: getPathList 
	* @Description: 获取可达路径列表
	* @param @param 传入projectId,当前页page,每页显示数量limit,关键字keyword
	* @param @param response
	* @param @return  符合该输入条件的可达路径列表
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/getPathList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPathList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int limit = obj.getInt("limit");
			int projectId = obj.getInt("projectId");
			int currentPage = obj.getInt("page");
			String keyword = "%%";
			if (obj.containsKey("keyword"))
				keyword = "%" + obj.getString("keyword") + "%";
			if (currentPage <= 0) {
				currentPage = 1;
			}
			int currentResult = (currentPage - 1) * limit;
			PageInfo page = new PageInfo();
			page.setShowCount(limit);
			page.setCurrentResult(currentResult);
			List<ReachablePath> list = reachablePathMapper.selectReachablePathListPage(page,keyword,projectId);
			
			int total = page.getTotalResult();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("reachablePath", list);
			map.put("total", total);
			return JsonHandler.writeJsontoResponse(1000,map);
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	/** 
	* @Title: verifyInvocation 
	* @Description: 验证两个接口间的可调用关系 
	* @param @param request 传入projectId,para1:接口1的Id,para2:接口2的Id
	* @param @param response
	* @param @return  验证结果和错误信息
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/verifyInvocation",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verifyInvocation(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				int para1 = obj.getInt("para1");
				int para2 = obj.getInt("para2");
				int sign = 0;
				String error = "";
				List<List<List<Integer>>> totaltypeList= new  ArrayList<List<List<Integer>>>();
				List<List<List<Integer>>> totalptypeList= new ArrayList<List<List<Integer>>>();
				String t1 = invokableNodeMapper.selectByPrimaryKey(para1).getNodeId();
				String t2 = invokableNodeMapper.selectByPrimaryKey(para2).getNodeId();
				String keyword = "%"+t1+".%"+t2+".%";
				System.out.println(keyword);
				List<ReachablePath> rpList = reachablePathMapper.selectReachablePathListByProjectId(keyword, projectId);
				if(rpList.size()==0){//两个节点之间在调用图中并未要求有通路
					return JsonHandler.writeJsontoResponse(1010,"");
				}else{
					for(ReachablePath rp:rpList){
						int key = 0;
						int key2= 0;
						int key3 = 0;
						System.out.println(rp.getPathExp());
						String rpath =rp.getPathExp().replaceAll("\\s*", "");
						List<String> methods = new ArrayList<String>();
						String[] tem = rpath.split(";");
						for(int i =0;i<tem.length;i++){//获取可达路径的节点列表
							if(i>1&&i<tem.length-1){
								methods.add(tem[i]);
							}
								
						}
						List<List<Integer>> alltypeList= new  ArrayList<List<Integer>>();
						List<List<Integer>> allptypeList= new ArrayList<List<Integer>>();
						
						for(int i=0;i<methods.size();i++){
							List<Integer>typeList= new ArrayList<Integer>();
							List<Integer>ptypeList= new ArrayList<Integer>();
							System.out.println(methods.get(i));
							if(methods.get(i).split(",").length>1){
								String []  list = methods.get(i).split(",");
								for(String nodeid: list){
									InvokableNode node = invokableNodeMapper.selectByNodeId(methods.get(i), projectId);
									if(node == null){
										error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
										key = 0;
										break;
										//return JsonHandler.writeJsontoResponse(1011,methods.get(i));
									}else{
										key = 1;
										List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
										if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
											error += methods.get(i)+"缺少对应需求分析;";
											key2 = 0;
											break;
											//return JsonHandler.writeJsontoResponse(1012,methods.get(i));
										}else{
											key2 =1;
											
											int tem1 = 0;
											for(DNMapping dnm:dnmList){
												
												Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
												List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
												List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
												if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
													tem1 = 0;
													error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
													continue;
													//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
												}else if(pdtmList.size()==0){
													tem1 = 0;
													error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
													continue;
													//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
												}else{
													
													tem1 =1;
													
													for(DTMapping dt:dtmList){
														typeList.add(dt.getTypeId());
													}
													for(DTMapping pdt:pdtmList){
														ptypeList.add(pdt.getTypeId());
													}
												}
												if(tem1==1){
													key3=1;
													error="";
												}
												
											}
											
											
										}
										
									}
								
								}
							}else{
								InvokableNode node = invokableNodeMapper.selectByNodeId(methods.get(i), projectId);
								if(node == null){
									error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
									key = 0;
									break;
									//return JsonHandler.writeJsontoResponse(1011,methods.get(i));
								}else{
									key = 1;
									List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
									if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
										error += methods.get(i)+"缺少对应需求分析;";
										key2 = 0;
										break;
										//return JsonHandler.writeJsontoResponse(1012,methods.get(i));
									}else{
										key2 =1;
										
										int tem1 = 0;
										for(DNMapping dnm:dnmList){
											
											Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
											List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
											List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
											if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
												tem1 = 0;
												error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
												continue;
												//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
											}else if(pdtmList.size()==0){
												tem1 = 0;
												error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
												continue;
												//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
											}else{
												
												tem1 =1;
												
												for(DTMapping dt:dtmList){
													typeList.add(dt.getTypeId());
												}
												for(DTMapping pdt:pdtmList){
													ptypeList.add(pdt.getTypeId());
												}
											}
											if(tem1==1){
												key3=1;
												error="";
											}
											
										}
										
										
									}
									
								}
							}
							alltypeList.add(typeList);
							allptypeList.add(ptypeList);
								
						}
						if(key==1){
							sign=2;
							if(key2==1){
								sign=3;
								if(key3==1){
									sign=4;
								}
							}
						}else if(key==0){
							sign=1;
						}
						totaltypeList.add(alltypeList);
						totalptypeList.add(allptypeList);
					}
					if(sign==1){
						return JsonHandler.writeJsontoResponse(1011,error);
					}else if(sign==2){
						return JsonHandler.writeJsontoResponse(1012,error);
					}else if(sign==3){
						return JsonHandler.writeJsontoResponse(1013,error);
					}
					
					if(sign==4){
						int key = 0;
						int key2 =0;
						for(int j =0;j<totaltypeList.size();j++){
							List<List<Integer>> alltypeList = totaltypeList.get(j);
							List<List<Integer>> allptypeList = totalptypeList.get(j);
							for(int i=0;i<alltypeList.size()-1;i++){
								List<Integer> typeList1 = alltypeList.get(i);
								List<Integer> typeList2 = alltypeList.get(i+1);
								List<Integer> ptypeList1 = allptypeList.get(i);
								List<Integer> ptypeList2 = allptypeList.get(i+1);
								List<TypeRule> trList = new ArrayList<TypeRule>();
								for(Integer type1: typeList1){
									for(Integer type2: typeList2){
										List<TypeRule> trl = typeRuleMapper.selectByConclusion("%"+type1+","+type2+",invoke%");
										if(trl.size()!=0)
											for(TypeRule tr: trl)
												trList.add(tr);
									}
								}
								if(trList.size()==0){
									error += "所建模型中无到期望的可调用关系";
									key = 0;
									break;
									//return JsonHandler.writeJsontoResponse(1015,methods.get(i)+","+methods.get(i+1));
								}else{
									key=1;
									error="";
									int tem=0;
									for(TypeRule tr:trList){
										System.out.println(tr.getRuleCondition());
										
										String[] condition = tr.getRuleCondition().split(";");
										Integer parentType1 =  Integer.valueOf(condition[0].split(",")[1]);
										Integer parentType2 =  Integer.valueOf(condition[1].split(",")[1]);
										Integer type1 =  Integer.valueOf(condition[0].split(",")[0].replace("(",""));
										Integer type2 =  Integer.valueOf(condition[1].split(",")[0].replace("(",""));
										Integer eleType2 =  Integer.valueOf(condition[2].split(",")[0].replace("(", ""));
										Integer eleType1 =  Integer.valueOf(condition[2].split(",")[1]);
										System.out.println(ptypeList1);
										System.out.println(ptypeList2);
										System.out.println(parentType1);
										System.out.println(parentType2);
										System.out.println(ptypeList1.contains(parentType1));
										System.out.println(ptypeList2.contains(parentType2));
										System.out.println(eleType1);
										System.out.println(eleType2);
										System.out.println(ptypeList1.contains(eleType1));
										System.out.println(ptypeList2.contains(eleType2));
										if(!ptypeList1.contains(parentType1)){
											System.out.println("11111");
											error += "所建模型中"+type1+"不是"+parentType1+"的方法";
											tem =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1016,type1+","+parentType1);
										}else if(!ptypeList2.contains(parentType2)){
											error += "所建模型中"+type2+"不是"+parentType2+"的方法";
											tem =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1016,type2+","+parentType2);
										}else if(!ptypeList1.contains(eleType1)||!ptypeList2.contains(eleType2)){
											System.out.println("22222");
											error += "所建模型中"+eleType1+"不是"+eleType2+"的相关成员类型";
											tem =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1017,eleType1+","+eleType2);
											
										}else{
											System.out.println("33333");
											tem =1;
										}
										System.out.println("444444");
										System.out.println("tem:"+tem);
										if(tem==1){
											key2 =1;
											error="";
										}
										System.out.println("key2:"+key2);
										
									}
									
									
									
								}
							}
							if(key==1){//有一条可达路径上结点的可调用关系是在规则库中的规则结果部分存在的
								sign=5;
								if(key2==1){//有一条可达路径上结点的可调用关系是在规则库中的规则结果部分存在的,并且其对应的规则条件也在所给到的数组中
									sign=6;
								}else{
									//有一条可达路径上结点的可调用关系是在规则库中的规则结果部分存在的,但其对应的规则条件不在所给到的数组中
								}
							}else{//没有任意一条可达路径上结点的可调用关系是在规则库中的规则结果部分存在的
								
							}
						}
						
					}
					
				}	
				if(sign==4)
					return JsonHandler.writeJsontoResponse(1015,"");
				else if(sign==5)
					return JsonHandler.writeJsontoResponse(1016,error);
				else
					return JsonHandler.writeJsontoResponse(1000,"ok");
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	/** 
	* @Title: verifyInvocationByPath 
	* @Description: 根据可达路径验证可调用性
	* @param @param request 传入projectId:项目id,rid:可达路径id
	* @param @param response
	* @param @return  验证结果错误代码及错误信息
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/verifyInvocationByPath",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verifyInvocationByPath(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int projectId = obj.getInt("projectId");
				int rid = obj.getInt("rid");
				int sign = 0;
				String error = "";
				ReachablePath rp = reachablePathMapper.selectByPrimaryKey(rid);
				String rpath =rp.getPathExp().replaceAll("\\s*", "");
				List<String> methods = new ArrayList<String>();
				String[] tem = rpath.split(";");
				for(int i =0;i<tem.length;i++){//获取可达路径的节点列表
					if(i>1&&i<tem.length-1){
						methods.add(tem[i]);
					}
						
				}
				List<List<Integer>> alltypeList= new  ArrayList<List<Integer>>();
				List<List<Integer>> allptypeList= new ArrayList<List<Integer>>();
				
				for(int i=0;i<methods.size();i++){
					List<Integer>typeList= new ArrayList<Integer>();
					List<Integer>ptypeList= new ArrayList<Integer>();
					System.out.println(methods.get(i));
					if(methods.get(i).split(",").length>1){
						String []  list = methods.get(i).split(",");
						for(String nodeid: list){
							InvokableNode node = invokableNodeMapper.selectByNodeId(nodeid, projectId);
							if(node == null){
								error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
								return JsonHandler.writeJsontoResponse(1011,error);
							}else{
								List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
								if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
									error += methods.get(i)+"缺少对应需求分析;";
									return JsonHandler.writeJsontoResponse(1012,error);
								}else{
									int tem1 = 0;
									for(DNMapping dnm:dnmList){
										Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
										List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
										List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
										if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
											tem1 = 0;
											error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
											continue;
											//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
										}else if(pdtmList.size()==0){
											tem1 = 0;
											error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
											continue;
											//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
										}else{
											
											tem1 =1;
											
											for(DTMapping dt:dtmList){
												typeList.add(dt.getTypeId());
											}
											for(DTMapping pdt:pdtmList){
												ptypeList.add(pdt.getTypeId());
											}
										}
										if(tem1==1){
											sign=1;
											error="";
										}
										
									}
									
									
								}
								
							}
						}
						
					}else{
						InvokableNode node = invokableNodeMapper.selectByNodeId(methods.get(i), projectId);
						if(node == null){
							error += methods.get(i)+"缺少在EXCEL文件的对应内容;";
							return JsonHandler.writeJsontoResponse(1011,error);
						}else{
							List<DNMapping> dnmList = dNMappingMapper.selectListByNodeId(node.getId());
							if(dnmList.size()==0){//调用图上路径中有结点并未进行需求设计
								error += methods.get(i)+"缺少对应需求分析;";
								return JsonHandler.writeJsontoResponse(1012,error);
							}else{
								int tem1 = 0;
								for(DNMapping dnm:dnmList){
									Demand d = demandMapper.selectByPrimaryKey(dnm.getDemandId());
									List<DTMapping> pdtmList  =  dTMappingMapper.selectByDemandId(d.getParentId());
									List<DTMapping> dtmList =  dTMappingMapper.selectByDemandId(dnm.getDemandId());  
									if(dtmList.size()==0){//调用图上路径中有结点对应需求未被建模
										tem1 = 0;
										error += methods.get(i)+"对应需求"+d.getId()+"缺少对应类型设计;";
										continue;
										//return JsonHandler.writeJsontoResponse(1013,methods.get(i)+","+d.getId());
									}else if(pdtmList.size()==0){
										tem1 = 0;
										error += methods.get(i)+"对应需求"+d.getId()+"的上级需求"+d.getParentId()+"缺少对应类型设计;";
										continue;
										//return JsonHandler.writeJsontoResponse(1014,methods.get(i)+","+d.getId()+","+d.getParentId());
									}else{
										
										tem1 =1;
										
										for(DTMapping dt:dtmList){
											typeList.add(dt.getTypeId());
										}
										for(DTMapping pdt:pdtmList){
											ptypeList.add(pdt.getTypeId());
										}
									}
									if(tem1==1){
										sign=1;
										error="";
									}
									
								}
								
								
							}
							
						}
					}
					
					alltypeList.add(typeList);
					allptypeList.add(ptypeList);
						
				}
				if(sign==0){
					return JsonHandler.writeJsontoResponse(1013,error);
				}
					
				if(sign==1){
					if(alltypeList.size()>1){
						for(int i=0;i<alltypeList.size()-1;i++){
								List<Integer> typeList1 = alltypeList.get(i);
								List<Integer> typeList2 = alltypeList.get(i+1);
								List<Integer> ptypeList1 = allptypeList.get(i);
								List<Integer> ptypeList2 = allptypeList.get(i+1);
								List<TypeRule> trList = new ArrayList<TypeRule>();
								for(Integer type1: typeList1){
									for(Integer type2: typeList2){
										System.out.println("type1:"+type1+".type2:"+type2);
										List<TypeRule> trl = typeRuleMapper.selectByConclusion("%"+type1+","+type2+",invoke%");
										if(trl.size()!=0)
											for(TypeRule tr: trl)
												trList.add(tr);
									}
								}
								if(trList.size()==0){
									error += "所建模型中无节点"+i+"到"+(i+1)+"期望的可调用关系";
									return JsonHandler.writeJsontoResponse(1015,error);
								}else{
									error="";
									int tem1=0;
									for(TypeRule tr:trList){
										System.out.println(tr.getRuleCondition());
										String[] condition = tr.getRuleCondition().split(";");
										Integer parentType1 =  Integer.valueOf(condition[0].split(",")[1]);
										Integer parentType2 =  Integer.valueOf(condition[1].split(",")[1]);
										Integer type1 =  Integer.valueOf(condition[0].split(",")[0].replace("(",""));
										Integer type2 =  Integer.valueOf(condition[1].split(",")[0].replace("(",""));
										Integer eleType2 =  Integer.valueOf(condition[2].split(",")[0].replace("(", ""));
										Integer eleType1 =  Integer.valueOf(condition[2].split(",")[1]);
										/*System.out.println(ptypeList1);
										System.out.println(ptypeList2);
										System.out.println(parentType1);
										System.out.println(parentType2);
										System.out.println(ptypeList1.contains(parentType1));
										System.out.println(ptypeList2.contains(parentType2));
										System.out.println(eleType1);
										System.out.println(eleType2);
										System.out.println(ptypeList1.contains(eleType1));
										System.out.println(ptypeList2.contains(eleType2));*/
										if(!ptypeList1.contains(parentType1)){
											System.out.println("11111");
											error += "所建模型中"+type1+"不是"+parentType1+"的方法";
											tem1 =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1016,type1+","+parentType1);
										}else if(!ptypeList2.contains(parentType2)){
											error += "所建模型中"+type2+"不是"+parentType2+"的方法";
											tem1 =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1016,type2+","+parentType2);
										}else if(!ptypeList1.contains(eleType1)||!ptypeList2.contains(eleType2)){
											System.out.println("22222");
											error += "所建模型中"+eleType1+"不是"+eleType2+"的相关成员类型";
											tem1 =0;
											continue;
											//return JsonHandler.writeJsontoResponse(1017,eleType1+","+eleType2);
											
										}else{
											System.out.println("33333");
											tem1 =1;
										}
										if(tem1==1){
											sign =2;
											error="";
										}
										
									}
									
									
									
								}
							}			
						
						
					}else{
						sign=2;
						error="";
					}	
					
					
				}
					
					
				if(sign==1)
					return JsonHandler.writeJsontoResponse(1016,error);
				else
					return JsonHandler.writeJsontoResponse(1000,"ok");
				
			} catch (JSONException e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1003, "");
			} catch (Exception e) {
					e.printStackTrace();
					LogRecord.ERROR.error(e.toString());
					return JsonHandler.writeJsontoResponse(1001, "");
			}
	}
	
	
}
