package com.saml.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saml.inter.DTMappingMapper;
import com.saml.inter.ElementMapper;
import com.saml.inter.SamlTypeMapper;
import com.saml.inter.TypeRuleMapper;
import com.saml.model.DTMapping;
import com.saml.model.Demand;
import com.saml.model.Element;
import com.saml.model.SamlType;
import com.saml.model.TypeRule;
import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;
import com.saml.util.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/samlType")
public class SamlTypeController {
	@Autowired 
	SamlTypeMapper samlTypeMapper;
	@Autowired
	TypeRuleMapper typeRuleMapper;
	@Autowired
	DTMappingMapper dTMappingMapper;
	@Autowired
	ElementMapper elementMapper;
	@RequestMapping(value="/getSamlTypeCatalog",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSamlTypeCatalog(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
			int level = obj.getInt("level");
			List<SamlType> list = samlTypeMapper.selectAllSamlTypeByLevel(projectId,level);
			if(level==2){
				for(int i=0;i<list.size();i++){
					String exp = list.get(i).getTypeExpression();
					exp = transformIdtoName(exp,level);
					list.get(i).setTypeExpression(list.get(i).getTypeName()+":"+exp);
				}
			}
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("samlType", list);
			map.put("total", list.size());
			return JsonHandler.writeJsontoResponse(1000, map);
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	@RequestMapping(value="/getTypeCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTypeCount(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
			int total = 0;
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0 ; i<7;i++){
				int count = samlTypeMapper.selectSamlTypeCountByLevel(projectId,i+1);
				list.add(count);
				total += count;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("counts", list);
			map.put("total", total);
			return JsonHandler.writeJsontoResponse(1000, map);
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	@RequestMapping(value="/getSamlTypeList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSamlTypeList(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int limit = obj.getInt("limit");
			int currentPage = obj.getInt("page");
			int level = obj.getInt("level");
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
			List<SamlType> list = samlTypeMapper.selectSamlTypeListPageByLevel(page,keyword,level);
			for(int i=0;i<list.size();i++){
				String exp = list.get(i).getTypeExpression();
				exp = transformIdtoName(exp,level);
				if(level==3)
					exp = transformIdtoName(exp,2);
				list.get(i).setTypeExpression(exp);
			}
			
			
			int total = page.getTotalResult();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("samlType", list);
			map.put("total", total);
			return JsonHandler.writeJsontoResponse(1000, map);
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	
	@RequestMapping(value="/getSamlTypeInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSamlTypeInfo(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			Integer id = obj.getInt("id");
			int level = obj.getInt("level");
			List<String> para = new ArrayList<String>();
			SamlType st = samlTypeMapper.selectByPrimaryKey(id);
			if(level!=1){
				String exp = st.getTypeExpression();
				para = getParaFromExp(exp);
				/*int begin = -1;
				int end = -1;
				for(int j=0;j<exp.length();j++){
					if(exp.charAt(j)<48||exp.charAt(j)>57){
						if(begin<end){
							int bid = Integer.parseInt(exp.substring(begin+1,end+1));
							 para.add(bid) ;
							}
						begin = j;
					}
					else{
						end = j;
					}
				}
				if(begin>end){
					//newExp += exp.substring(end+1,begin+1);
				}else{
					int bid = Integer.parseInt(exp.substring(begin+1,end+1));
					para.add(bid) ;
				}*/
			}
			List<DTMapping> dtList = dTMappingMapper.selectByTypeId(id);
			
			
					
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("samlType", st);
			map.put("para", para);
			map.put("demand", dtList);
			map.put("total", para.size());
			if(level==3){
				String[] exp = elementMapper.selectByTypeId(id).getElement().split(",");
				List<String> elements = java.util.Arrays.asList(exp);
				map.put("elements", elements);
			}
			
			return JsonHandler.writeJsontoResponse(1000, map);
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	@RequestMapping(value="/addSamlType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSamlType(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int level = obj.getInt("level");
			String typeName = obj.getString("typeName");
			Integer projectId = obj.getInt("projectId");
			Integer typeClassification = obj.getInt("typeClassification");
			String remark = obj.getString("remark");
			SamlType st = new SamlType();
			st.setProjectId(projectId);
			st.setTypeName(typeName);
			st.setTypeClassification(typeClassification);
			st.setTypelevel(level);
			st.setRemark(remark);
			st.setAddTime(new Date());
			st.setState(1);
			if(level==1){//basicType
				st.setTypeRules(1);
				st.setTypeExpression(typeName);
				samlTypeMapper.insertSelective(st);
				//每种类型都要建立子类型规则
				TypeRule t = new TypeRule();
				t.setRuleName("RuleOf"+st.getId());
				t.setProjectId(projectId);
				t.setContentId(st.getId());
				t.setRuleType(3);
				t.setRuleConclusion("("+st.getId()+","+st.getId()+",Subtype)");
				t.setState(1);
				t.setAddTime(new Date());
				typeRuleMapper.insertSelective(t);
			}else if(level==2){//extendedType
				JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
				
				String outPara = obj.getString("outputPara");
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				System.out.println(inParaList.size());
				String inpara = getExpFromPara(inParaList);
				String exp = "("+inpara+","+outPara+",Mapping)";
				if(outPara!=null&&!outPara.equals(""))
					inParaList.add(outPara);
				
				st.setTypeRules(3+getTypeRulesCount(inParaList));//即将建立的子类型规则1个+输入关系1个+输出关系1个+所有用到的level1的类型的子类型关系
				st.setTypeExpression(exp);
				samlTypeMapper.insertSelective(st);
				//每种类型都要建立子类型规则
				TypeRule t = new TypeRule();
				t.setRuleName("RuleOf"+st.getId());
				t.setProjectId(projectId);
				t.setContentId(st.getId());
				t.setRuleType(3);
				t.setRuleConclusion("("+st.getId()+","+st.getId()+",Subtype)");
				t.setState(1);
				t.setAddTime(new Date());
				typeRuleMapper.insertSelective(t);
				//输入参数关系规则
				TypeRule t3 = new TypeRule();
				t3.setRuleName("RuleOf"+st.getId());
				t3.setProjectId(projectId);
				t3.setContentId(st.getId());
				t3.setRuleType(4);
				t3.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
				t3.setRuleConclusion("("+inpara+","+st.getId()+",inPara)");
				t3.setState(1);
				t3.setAddTime(new Date());
				typeRuleMapper.insertSelective(t3);
				//输出参数关系规则
				TypeRule t2 = new TypeRule();
				t2.setRuleName("RuleOf"+st.getId());
				t2.setProjectId(projectId);
				t2.setContentId(st.getId());
				t2.setRuleType(5);
				t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
				t2.setRuleConclusion("("+outPara+","+st.getId()+",outPara)");
				t2.setState(1);
				t2.setAddTime(new Date());
				typeRuleMapper.insertSelective(t2);
				
				for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(st.getId());
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}
				
			}else if(level==3){//typeInterface
				JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
				
				JSONArray jsonarray3 = obj.getJSONArray("element");
				@SuppressWarnings("unchecked")
				List<String> elementList = (List<String>) jsonarray3;
				elementList = removeDuplicate(elementList);//去掉重复的映射类型
				
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				inParaList = removeDuplicate(inParaList);//去掉重复的映射类型
				String exp = "";
				if(inParaList.size()==1)
					exp = "{[l:T]*,methods:("+getExpFromPara(inParaList)+")}";
				
				else 
					exp = "{[l:T]*,methods:"+getExpFromPara(inParaList)+"}";
				int level1RulesRalation = getTypeRulesCount(getParaFromHighLevel(inParaList));//level1类型的子类型规则数量
				int level2RulesRalation = getTypeRulesCount(inParaList);//level2类型的规则数量
				st.setTypeRules(level1RulesRalation+level2RulesRalation+1+inParaList.size());//已有相关所有level的规则数量+即将创建的子类型规则1个+函数关系规则数量
				st.setTypeExpression(exp);
				samlTypeMapper.insertSelective(st);
				
				//创建对应的成员类型变量记录并插入表中
				String eleExp = "";
				for(String exp2 :elementList){
					eleExp+=exp2+",";
				}
				Element e = new Element(); 
				e.setAddTime(new Date());
				e.setElement(eleExp);
				e.setProjectId(projectId);
				e.setState(1);
				e.setTypeId(st.getId());
				elementMapper.insertSelective(e);
				
				//每种类型都要建立子类型规则
				TypeRule t = new TypeRule();
				t.setRuleName("RuleOf"+st.getId());
				t.setProjectId(projectId);
				t.setContentId(st.getId());
				t.setRuleType(3);
				t.setRuleConclusion("("+st.getId()+","+st.getId()+",Subtype)");
				t.setState(1);
				t.setAddTime(new Date());
				typeRuleMapper.insertSelective(t);
				for(int i=0;i<inParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
					//函数关系规则
					
					TypeRule t2 = new TypeRule();
					t2.setRuleName("RuleOf"+st.getId());
					t2.setProjectId(projectId);
					t2.setContentId(st.getId());
					t2.setRuleType(6);
					t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
					t2.setRuleConclusion("("+inParaList.get(i)+","+st.getId()+",method)");
					t2.setState(1);
					t2.setAddTime(new Date());
					typeRuleMapper.insertSelective(t2);
					
					//函数可调用规则
					for(String ele: elementList){//遍历每个成员类型
						SamlType eleType = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(ele));
						List<String> eleExtendTypeList= getParaFromExp(eleType.getTypeExpression());//从成员类型的表达式获取其带映射类型的列表
						for(String eleExtendType:eleExtendTypeList){//遍历该映射类型列表，添加对应可调用规则
							TypeRule t3 = new TypeRule();
							t3.setRuleName("RuleOf"+st.getId());
							t3.setProjectId(projectId);
							t3.setContentId(st.getId());
							t3.setRuleType(7);
							t3.setRuleCondition("("+inParaList.get(i)+","+st.getId()+",method);("+eleExtendType+","+ele+",method);("+ele+","+st.getId()+",element)");
							t3.setRuleConclusion("("+inParaList.get(i)+","+eleExtendType+",invoke)");
							t3.setState(1);
							t3.setAddTime(new Date());
							typeRuleMapper.insertSelective(t3);
						}
						
						
					}
				}
				
				
				
				
				for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(st.getId());
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}
				
				
				
			}else if(level>=4){//component及以上
				/*JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
*/				
				Integer demand = obj.getInt("demand");
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				inParaList = removeDuplicate(inParaList);//去掉重复的接口类型
				String exp = getExpFromPara(inParaList);
				int level1RulesRalation = getTypeRulesCount(getParaFromHighLevel(getParaFromHighLevel(inParaList)));//level1类型的子类型规则数量
				int level2RulesRalation = getTypeRulesCount(getParaFromHighLevel(inParaList));//level2类型的规则数量
				int level3RulesRalation = getTypeRulesCount(inParaList);//level3类型的规则数量
				st.setTypeRules(level1RulesRalation+level2RulesRalation+level3RulesRalation+1+inParaList.size());//已有相关所有level的规则数量+即将创建的子类型规则1个+函数关系规则数量
				st.setTypeExpression(exp);
				samlTypeMapper.insertSelective(st);
				//每种类型都要建立子类型规则
				TypeRule t = new TypeRule();
				t.setRuleName("RuleOf"+st.getId());
				t.setProjectId(projectId);
				t.setContentId(st.getId());
				t.setRuleType(3);
				t.setRuleConclusion("("+st.getId()+","+st.getId()+",Subtype)");
				t.setState(1);
				t.setAddTime(new Date());
				typeRuleMapper.insertSelective(t);
				
				for(int i=0;i<inParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
					//聚合关系规则
					TypeRule t2 = new TypeRule();
					t2.setRuleName("RuleOf"+st.getId());
					t2.setProjectId(projectId);
					t2.setContentId(st.getId());
					t2.setRuleType(1);
					t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
					t2.setRuleConclusion("("+inParaList.get(i)+","+st.getId()+",AggRelation)");
					t2.setState(1);
					t2.setAddTime(new Date());
					typeRuleMapper.insertSelective(t2);
					//类关联联系规则
					for(int j=i+1;j<inParaList.size();j++){
						TypeRule t3 = new TypeRule();
						t3.setRuleName("RuleOf"+st.getId());
						t3.setProjectId(projectId);
						t3.setContentId(st.getId());
						t3.setRuleType(2);
						t3.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
						t3.setRuleConclusion("("+inParaList.get(i)+","+inParaList.get(j)+","+st.getId()+",TypeAssociation)");
						t3.setState(1);
						t3.setAddTime(new Date());
						typeRuleMapper.insertSelective(t3);
					}	
				}
				DTMapping dtm = new DTMapping();
				dtm.setDemandId(demand);
				dtm.setProjectId(projectId);
				dtm.setTypeId(st.getId());
				dtm.setAddTime(new Date());
				dtm.setState(1);
				dTMappingMapper.insertSelective(dtm);
				/*for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(st.getId());
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}*/
				
				
			}
			
			
			
			
			return JsonHandler.writeJsontoResponse(1000, st.getId());
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	
	@RequestMapping(value="/editSamlType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editSamlType(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			Integer id = obj.getInt("id");
			String typeName = obj.getString("typeName");
			Integer projectId = obj.getInt("projectId");
			Integer typeClassification = obj.getInt("typeClassification");
			String remark = obj.getString("remark");
			SamlType st = samlTypeMapper.selectByPrimaryKey(id);
			int level = st.getTypelevel();
			st.setProjectId(projectId);
			st.setTypeName(typeName);
			st.setTypeClassification(typeClassification);
			st.setRemark(remark);
			st.setAddTime(new Date());
			st.setState(1);
			if(level==1){//basicType
				st.setTypeExpression(typeName);
				samlTypeMapper.updateByPrimaryKeySelective(st);
				
			}else if(level==2){
				JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
				
				String outPara = obj.getString("outputPara");
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				String inpara = getExpFromPara(inParaList);
				String exp = "("+inpara+","+outPara+",Mapping)";
				if(outPara!=null&&!outPara.equals(""))
					inParaList.add(outPara);
				
				st.setTypeRules(3+getTypeRulesCount(inParaList));//即将建立的子类型规则1个+输入关系1个+输出关系1个+所有用到的level1的类型的子类型关系
				st.setTypeExpression(exp);
				samlTypeMapper.updateByPrimaryKeySelective(st);
				
				//输入参数关系规则
				TypeRule t3 = typeRuleMapper.selectByContentIdAndRuleType(id, 4);
				t3.setProjectId(projectId);
				t3.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
				t3.setRuleConclusion("("+inpara+","+st.getId()+",inPara)");
				t3.setAddTime(new Date());
				typeRuleMapper.updateByPrimaryKeySelective(t3);
				//输出参数关系规则
				TypeRule t2 = typeRuleMapper.selectByContentIdAndRuleType(id, 5);
				t2.setProjectId(projectId);
				t2.setContentId(st.getId());
				t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
				t2.setRuleConclusion("("+outPara+","+st.getId()+",outPara)");
				t2.setAddTime(new Date());
				typeRuleMapper.updateByPrimaryKeySelective(t2);
				
				//删除需求-类型对照表中相关原有记录并，加入新的记录
				dTMappingMapper.logicalDeleteByTypeId(id);
				for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(id);
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}
			}else if(level==3){//typeInterface 
				JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
				
				JSONArray jsonarray3 = obj.getJSONArray("element");
				@SuppressWarnings("unchecked")
				List<String> elementList = (List<String>) jsonarray3;
				elementList = removeDuplicate(elementList);//去掉重复的映射类型
				
				
				
				
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				inParaList = removeDuplicate(inParaList);//去掉重复的映射类型
				List<String> oldInParaList = getParaFromExp(st.getTypeExpression());
				if(!oldInParaList.equals(inParaList)){//如果参数列表改变了
					String exp = "";
					if(inParaList.size()==1)
						exp = "{[l:T]*,methods:("+getExpFromPara(inParaList)+")}";
					else 
						exp = "{[l:T]*,methods:"+getExpFromPara(inParaList)+"}";
					int level1RulesRalation = getTypeRulesCount(getParaFromHighLevel(inParaList));//level1类型的子类型规则数量
					int level2RulesRalation = getTypeRulesCount(inParaList);//level2类型的规则数量
					st.setTypeRules(level1RulesRalation+level2RulesRalation+1+inParaList.size());//已有相关所有level的规则数量+即将创建的子类型规则1个+函数关系规则数量
					st.setTypeExpression(exp);
					List<String> deleteParaList = new ArrayList<String>(oldInParaList);
					List<String> addParaList = new ArrayList<String>(inParaList);
					deleteParaList.removeAll(inParaList);
					addParaList.removeAll(oldInParaList);
					//删除不再用到的函数关系规则及函数可调用该规则
					for(int i=0;i<deleteParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
						typeRuleMapper.logicalDeleteByContentIdAndRuleTypeAndKeyId(id, 6,("%"+deleteParaList.get(i)+",%"));
					}
					//添加新的函数关系规则
					for(int i=0;i<addParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
						TypeRule t2 = new TypeRule();
						t2.setRuleName("RuleOf"+st.getId());
						t2.setProjectId(projectId);
						t2.setContentId(st.getId());
						t2.setRuleType(6);
						t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
						t2.setRuleConclusion("("+addParaList.get(i)+","+st.getId()+",method)");
						t2.setState(1);
						t2.setAddTime(new Date());
						typeRuleMapper.insertSelective(t2);
						
						
					}
				}
				samlTypeMapper.updateByPrimaryKeySelective(st);
				
				//创建对应的成员类型变量记录并插入表中
				Element e = elementMapper.selectByTypeId(id); 
				String eleExp = "";
				for(String exp2 :elementList){
					eleExp+=exp2+",";
				}
				String oldElement = e.getElement();
				if(!oldElement.equals(elementList.toString())||!oldInParaList.equals(inParaList)){
					if(!oldElement.equals(eleExp)){
						e.setAddTime(new Date());
						e.setElement(eleExp);
						elementMapper.updateByPrimaryKeySelective(e);
					}
					//处理以该类型为成员类型的可调用关系的改变
					//List<Element> elist = elementMapper.selectByElement(id+"");
					
					
					//处理该类型的可调用关系
					typeRuleMapper.logicalDeleteByContentIdAndRuleTypeAndKeyId(id, 7, "%%");
					for(int i=0;i<inParaList.size();i++){
					//添加新的函数可调用规则
					for(String ele: elementList){//遍历每个成员类型
						SamlType eleType = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(ele));
						List<String> eleExtendTypeList= getParaFromExp(eleType.getTypeExpression());//从成员类型的表达式获取其带映射类型的列表
						for(String eleExtendType:eleExtendTypeList){//遍历该映射类型列表，添加对应可调用规则
							TypeRule t3 = new TypeRule();
							t3.setRuleName("RuleOf"+id);
							t3.setProjectId(projectId);
							t3.setContentId(id);
							t3.setRuleType(7);
							t3.setRuleCondition("("+inParaList.get(i)+","+id+",method);("+eleExtendType+","+ele+",method);("+ele+","+id+",element)");
							t3.setRuleConclusion("("+inParaList.get(i)+","+eleExtendType+",invoke)");
							t3.setState(1);
							t3.setAddTime(new Date());
							typeRuleMapper.insertSelective(t3);
						}
						
						
					}
					}
				}
				
				//删除需求-类型对照表中相关原有记录并，加入新的记录
				dTMappingMapper.logicalDeleteByTypeId(id);
				for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(id);
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}
				
				
			}else if(level==4){//component及以上 
				JSONArray jsonarray2 = obj.getJSONArray("demand");
				@SuppressWarnings("unchecked")
				List<String> demandList = (List<String>) jsonarray2;
				demandList = removeDuplicate(demandList);//去掉重复的映射类型
				
				
				JSONArray jsonarray = obj.getJSONArray("inputPara");
				@SuppressWarnings("unchecked")
				List<String> inParaList = (List<String>) jsonarray;
				inParaList = removeDuplicate(inParaList);//去掉重复的映射类型
				List<String> oldInParaList = getParaFromExp(st.getTypeExpression());
				if(!oldInParaList.equals(inParaList)){//如果参数列表改变了
					String exp = getExpFromPara(inParaList);
					int level1RulesRalation = getTypeRulesCount(getParaFromHighLevel(getParaFromHighLevel(inParaList)));//level1类型的子类型规则数量
					int level2RulesRalation = getTypeRulesCount(getParaFromHighLevel(inParaList));//level2类型的规则数量
					int level3RulesRalation = getTypeRulesCount(inParaList);//level3类型的规则数量
					st.setTypeRules(level1RulesRalation+level2RulesRalation+level3RulesRalation+1+inParaList.size());//已有相关所有level的规则数量+即将创建的子类型规则1个+函数关系规则数量
					st.setTypeExpression(exp);
					List<String> deleteParaList = new ArrayList<String>(oldInParaList);
					List<String> addParaList = new ArrayList<String>(inParaList);
					List<String> existParaList = new ArrayList<String>(oldInParaList);
					deleteParaList.removeAll(inParaList);
					addParaList.removeAll(oldInParaList);
					existParaList.removeAll(deleteParaList);
					for(int i=0;i<deleteParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
						//删除不再用到的聚合关系规则
						typeRuleMapper.logicalDeleteByContentIdAndRuleTypeAndKeyId(id, 1,("%"+deleteParaList.get(i)+",%"));
						//删除不再用到的类关联关系规则
						typeRuleMapper.logicalDeleteByContentIdAndRuleTypeAndKeyId(id, 2,("%"+deleteParaList.get(i)+",%"));
					}
					//添加新的函数关系规则
					for(int i=0;i<addParaList.size();i++){//由于已经去重，所以这里不会出现重复的现象
						//聚合关系规则
						TypeRule t2 = new TypeRule();
						t2.setRuleName("RuleOf"+st.getId());
						t2.setProjectId(projectId);
						t2.setContentId(st.getId());
						t2.setRuleType(1);
						t2.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
						t2.setRuleConclusion("("+addParaList.get(i)+","+st.getId()+",AggRelation)");
						t2.setState(1);
						t2.setAddTime(new Date());
						typeRuleMapper.insertSelective(t2);
						//类关联关系规则
						for(int j=i+1;j<addParaList.size();j++){
							TypeRule t3 = new TypeRule();
							t3.setRuleName("RuleOf"+st.getId());
							t3.setProjectId(projectId);
							t3.setContentId(st.getId());
							t3.setRuleType(2);
							t3.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
							t3.setRuleConclusion("("+addParaList.get(i)+","+addParaList.get(j)+","+st.getId()+",TypeAssociation)");
							t3.setState(1);
							t3.setAddTime(new Date());
							typeRuleMapper.insertSelective(t3);
						}
						for(int j=0;j<existParaList.size();j++){
							TypeRule t3 = new TypeRule();
							t3.setRuleName("RuleOf"+st.getId());
							t3.setProjectId(projectId);
							t3.setContentId(st.getId());
							t3.setRuleType(2);
							t3.setRuleCondition("("+st.getId()+"="+st.getTypeExpression()+")");
							t3.setRuleConclusion("("+addParaList.get(i)+","+existParaList.get(j)+","+st.getId()+",TypeAssociation)");
							t3.setState(1);
							t3.setAddTime(new Date());
							typeRuleMapper.insertSelective(t3);
						}	
					}
				}
				samlTypeMapper.updateByPrimaryKeySelective(st);
				//删除需求-类型对照表中相关原有记录并，加入新的记录
				dTMappingMapper.logicalDeleteByTypeId(id);
				for(String d:demandList){
					//新建需求-类型对照记录并添加到dtmapping表中
					DTMapping dtm = new DTMapping();
					dtm.setDemandId(Integer.parseInt(d));
					dtm.setProjectId(projectId);
					dtm.setTypeId(id);
					dtm.setAddTime(new Date());
					dtm.setState(1);
					dTMappingMapper.insertSelective(dtm);
				}
				
				
			}
			return JsonHandler.writeJsontoResponse(1000, st.getId());
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	@RequestMapping(value="/deleteSamlType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteSamlType(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			Integer id = obj.getInt("id");
			Integer level = samlTypeMapper.selectByPrimaryKey(id).getTypelevel();
			samlTypeMapper.logicalDeleteByPrimaryKey(id);
			typeRuleMapper.logicalDeleteTypeRuleListByContentId(id);
			//！！！！！根据level判断是否有上层类型也需要逻辑删除，这里目前还没有进行处理，只能从高level的类型到低的删除，不能低到高
			dTMappingMapper.logicalDeleteByTypeId(id);
			if(level==3){
				elementMapper.logicalDeleteByTypeId(id);
			}
			return JsonHandler.writeJsontoResponse(1000, "ok");
			
		} catch (JSONException e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1003, "");
		}catch (Exception e) {
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	private String  transformIdtoName(String exp, int level){//输入带id的表达式以及level,输出将id替换为名称或表达式的表达式
		
		if(level==1)//level为1的id不需要替换
			return exp;
		String newExp = new String();
		int begin = -1;
		int end = -1;
		System.out.println(exp);
		for(int j=0;j<exp.length();j++){
			System.out.println(newExp);
			if(exp.charAt(j)<48||exp.charAt(j)>57){
				if(begin<end){
					int id = Integer.parseInt(exp.substring(begin+1,end+1));
					if(level==3){
						newExp += samlTypeMapper.selectByPrimaryKey(id).getTypeName()+":"+samlTypeMapper.selectByPrimaryKey(id).getTypeExpression();	
					}else
						newExp += samlTypeMapper.selectByPrimaryKey(id).getTypeName();
				}
				System.out.println(newExp);
				
				newExp += exp.charAt(j);
				System.out.println(newExp);
				
				begin = j;
			}
			else{
				end = j;
			}
		}
		if(begin>end){
			//newExp += exp.substring(end+1,begin+1);
		}else{
			int id = Integer.parseInt(exp.substring(begin+1,end+1));
			if(level==2||level==3)
				newExp += samlTypeMapper.selectByPrimaryKey(id).getTypeName()+":"+samlTypeMapper.selectByPrimaryKey(id).getTypeExpression();
			else
				newExp += samlTypeMapper.selectByPrimaryKey(id).getTypeName();
		}
		System.out.println(newExp);
		
		return newExp;
	}
	private  String getExpFromPara(List<String> inParaList){//输入参数id的list，输出id的表达式
		String exp = "";
		if(inParaList.size()==1){
			exp += inParaList.get(0);
		}else if(inParaList.size()>1){
			exp += "(";
			for(int i=0;i<inParaList.size();i++){
				exp += inParaList.get(i);
				if(i<inParaList.size()-1){
					exp += ",";
				}else{
					exp += ",Aggregation)";
				}
					
			}
		}
		
		return exp;
	}
	
	private  List<String> getParaFromExp(String exp){
		List<String> paraList = new ArrayList();
		int begin = -1;
		int end = -1;
		for(int j=0;j<exp.length();j++){
			if(exp.charAt(j)<48||exp.charAt(j)>57){
				if(begin<end){
					String id = exp.substring(begin+1,end+1);
					paraList.add(id);
				}
				begin = j;
			}
			else{
				end = j;
			}
		}
		if(begin>end){
			;
		}else{
			String id = exp.substring(begin+1,end+1);
			paraList.add(id);
		}
		return paraList;
	}
	private List<String>  getParaFromHighLevel(List<String> inParaList){//输入高Level id的list，输出低一层level id的list
		List<String> paraList = new ArrayList();
		
		for(int i = 0; i< inParaList.size();i++){
			String exp = samlTypeMapper.selectByPrimaryKey(Integer.parseInt(inParaList.get(i))).getTypeExpression();
			paraList.addAll(getParaFromExp(exp));
			//paraList.add(inParaList.get(i));
		}
		return paraList;
	}
	private int getTypeRulesCount(List<String> ParaList){//输入参数id的list，输出与其直接相关（即contentid字段为该id列表中的某一）的规则数量
		//去掉重复内容
		ParaList = removeDuplicate(ParaList);
		int count = 0;
		for(int i = 0; i<ParaList.size();i++){
			count += typeRuleMapper.selectRuleCountByContentId(Integer.parseInt(ParaList.get(i)));
		}
		return count;  
	}
	public static List<String> removeDuplicate(List<String> list) {//去重   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}
}
