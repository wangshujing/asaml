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
import com.saml.inter.DemandMapper;
import com.saml.inter.DemandRelationMapper;
import com.saml.inter.InvokableNodeMapper;
import com.saml.model.DNMapping;
import com.saml.model.Demand;
import com.saml.model.DemandAndRelation;
import com.saml.model.DemandInfo;
import com.saml.model.DemandRelation;
import com.saml.model.InvokableNode;
import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;
import com.saml.util.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/demand")
public class DemandController {
	@Autowired
	DemandMapper demandMapper;
	@Autowired
	DemandRelationMapper demandRelationMapper;
	@Autowired
	InvokableNodeMapper invokableNodeMapper;
	@Autowired
	DNMappingMapper dNMappingMapper;
	
	@RequestMapping(value="/getInvokableNodeCatalog",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInvokableNodeCatalog(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
			int type = obj.getInt("type");
			List<InvokableNode> list = invokableNodeMapper.selectInvokableNodeListByProjectIdAndType(projectId,type);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("invokableNode", list);
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
	@RequestMapping(value="/getInvokableNodeList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInvokableNodeList(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int limit = obj.getInt("limit");
			int projectId = obj.getInt("projectId");
			int parentId = obj.getInt("parentId");
			int currentPage = obj.getInt("page");
			int type = obj.getInt("type");
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
			List<InvokableNode> list = invokableNodeMapper.selectInvokableNodeListPageByProjectIdAndType(page,projectId,type,keyword);
			for(InvokableNode n:list){
				String parentNodeId = n.getNodeId().split("\\.")[0];
				String parentNodeName =invokableNodeMapper.selectByNodeId(parentNodeId,projectId).getNodeName();
				n.setParentNodeName(parentNodeName);
				n.setParentNodeId(parentNodeId);
			}
			//查找该parentId的所有子需求列表，即所有函数列表
			List<Demand> demandList = demandMapper.selectDemandListByParentId(parentId);
			//利用demandList检查是nodelist中有已选用的方法结点，如果有，修改其备选状态以及其他参数。
			if(demandList.size()!=0){
				for(InvokableNode node:list){
					for(Demand d:demandList){
						DNMapping dnm = dNMappingMapper.selectByDemandIdAndNodeId(d.getId(),node.getId());
						if(dnm!=null){
							node.setIsSelected(1);
							node.setDemandId(d.getId());
							node.setDnmId(dnm.getId());
							break;
						}
					}
					
				}
			}
			
			int total = page.getTotalResult();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("invokableNode", list);
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
	@RequestMapping(value="/getDemandCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDemandCount(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
			int total = 0;
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0 ; i<6;i++){
				int count = demandMapper.selectDemandCountByLevel(projectId,i+1);
				list.add(count);
				total += count;
			}
			int count = demandRelationMapper.selectDemandRelationCount(projectId);
			list.add(count);
			total += count;
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
	//用于获取树形结构的demandlist
	@RequestMapping(value="/getDemandList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDemandList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
				List<Demand> firstDemand = demandMapper.selectDemandListByLevel(projectId,1);
				List<DemandInfo> demandInfos = new ArrayList<DemandInfo>();
				for(Demand d: firstDemand){
					DemandInfo demandInfo = getChilds(d.getId());
					if (demandInfo != null)
						demandInfos.add(demandInfo);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("demandInfos", demandInfos);
				map.put("total", demandInfos.size());
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
	//用于不区分需求之间的层次关系，只列出列表即可，支持关键字与页数
	@RequestMapping(value="/getAllDemandList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllDemandList(HttpServletRequest request,
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
				List<Demand> list = demandMapper.selectDemandListPage(page,projectId,keyword);
				List<DemandAndRelation> demandAndRelations = new ArrayList<DemandAndRelation>();
				List<Integer> counts = new ArrayList<Integer>();
				for(Demand d:list){
					List<DemandRelation> dr =demandRelationMapper.selectDemandRelationListByCombinationId(d.getId());
					DemandAndRelation dar = new DemandAndRelation(d);
					dar.setRelations(dr);
					demandAndRelations.add(dar);
				}
				int total = page.getTotalResult();
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("demandAndRelation", demandAndRelations);
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
	@RequestMapping(value="/getChildDemandListbyId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getChildDemandListbyId(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int limit = obj.getInt("limit");
				int currentPage = obj.getInt("page");
				int id = obj.getInt("id");
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
				List<Demand> list = demandMapper.selectDemandListPageByParentId(page,id,keyword);
				int total = page.getTotalResult();
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("demands", list);
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
	@RequestMapping(value="/getDemandListbyLevel",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDemandListbyLevel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				
				int level = obj.getInt("level");
				int projectId = obj.getInt("projectId");
				List<Demand> list = demandMapper.selectDemandListByLevel(projectId,level);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("demands", list);
				map.put("total", list.size());
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
	@RequestMapping(value="/getDemandInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDemandInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int id = obj.getInt("id");
				Demand d = demandMapper.selectByPrimaryKey(id);
				ArrayList<Integer> para = new ArrayList<Integer>() ;
				List<DNMapping> list= dNMappingMapper.selectListByDemandId(id);
				for(DNMapping dnm : list){
					para.add(dnm.getNodeId());
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("demand", d);
				map.put("para", para);
				map.put("total", para.size());
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
	@RequestMapping(value="/addDemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDemand(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				String demandName = obj.getString("demandName");
				String demandInfo = obj.getString("demandInfo");
				int demandLevel =obj.getInt("demandLevel");
				int parentId = obj.getInt("parentId");
				int projectId = obj.getInt("projectId");
				Demand d = new Demand();
				d.setProjectId(projectId);
				d.setDemandName(demandName);
				d.setDemandInfo(demandInfo);
				d.setDemandLevel(demandLevel);
				d.setParentId(parentId);
				d.setAddTime(new Date());
				d.setState(1);
				demandMapper.insertSelective(d);
				
				DemandRelation dr = new DemandRelation();
				dr.setElementId(d.getId());
				dr.setCombinationId(parentId);
				dr.setProjectId(projectId);
				dr.setState(1);
				dr.setAddTime(new Date());
				dr.setExpression("("+d.getId()+","+parentId+",AggRelation)");
				demandRelationMapper.insertSelective(dr);
				if(demandLevel==5){
					JSONArray jsonarray = obj.getJSONArray("inputPara");
					@SuppressWarnings("unchecked")
					List<String> inParaList = (List<String>) jsonarray;
					for(String para: inParaList){
						if(!para.equals("")&&!para.equals(null)){
							DNMapping dnm = new DNMapping();
							dnm.setAddTime(new Date());
							dnm.setDemandId(d.getId());
							dnm.setNodeId(Integer.parseInt(para));
							dnm.setProjectId(projectId);
							dnm.setState(1);
							dNMappingMapper.insertSelective(dnm);
						}
						
					}
					
				}
				
				return JsonHandler.writeJsontoResponse(1000,d.getId());
				
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
	@RequestMapping(value="/addDemandByNode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDemandByNode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int parentId = obj.getInt("parentId");
				int projectId = obj.getInt("projectId");
				int nodeId = obj.getInt("nodeId");
				InvokableNode node =invokableNodeMapper.selectByPrimaryKey(nodeId);
				Demand d = new Demand();
				d.setProjectId(projectId);
				d.setDemandName(node.getNodeName());
				d.setDemandInfo(node.getNodeId());
				d.setDemandLevel(6);
				d.setParentId(parentId);
				d.setAddTime(new Date());
				d.setState(1);
				demandMapper.insertSelective(d);
				
				DemandRelation dr = new DemandRelation();
				dr.setElementId(d.getId());
				dr.setCombinationId(parentId);
				dr.setProjectId(projectId);
				dr.setState(1);
				dr.setAddTime(new Date());
				dr.setExpression("("+d.getId()+","+parentId+",AggRelation)");
				demandRelationMapper.insertSelective(dr);
				
				DNMapping dnm = new DNMapping();
				dnm.setAddTime(new Date());
				dnm.setDemandId(d.getId());
				dnm.setNodeId(nodeId);
				dnm.setProjectId(projectId);
				dnm.setState(1);
				dNMappingMapper.insertSelective(dnm);
						
				return JsonHandler.writeJsontoResponse(1000,d.getId());
				
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
	@RequestMapping(value="/deletedemandByNode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletedemandByNode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int demandId = obj.getInt("demandId");
				int dnmId = obj.getInt("dnmId");
				
				demandMapper.logicalDeleteByPrimaryKey(demandId);
				demandRelationMapper.logicalDeleteListByCombinationIdOrElementId(demandId);
				dNMappingMapper.logicalDeleteByPrimaryKey(dnmId);
						
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
	@RequestMapping(value="/editDemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editDemand(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int id = obj.getInt("id");
				String demandName = obj.getString("demandName");
				String demandInfo = obj.getString("demandInfo");
				int demandLevel =obj.getInt("demandLevel");
				int parentId = obj.getInt("parentId");
				int projectId = obj.getInt("projectId");
				Demand d = demandMapper.selectByPrimaryKey(id);
				d.setProjectId(projectId);
				d.setDemandName(demandName);
				d.setDemandInfo(demandInfo);
				d.setDemandLevel(demandLevel);
				d.setParentId(parentId);
				d.setAddTime(new Date());
				d.setState(1);
				demandMapper.updateByPrimaryKeySelective(d);
				if(demandLevel==5){
					JSONArray jsonarray = obj.getJSONArray("inputPara");
					@SuppressWarnings("unchecked")
					List<String> inParaList = (List<String>) jsonarray;
					dNMappingMapper.logicalDeleteListByDemandId(id);
					for(String para: inParaList){
						if(!para.equals("")&&!para.equals(null)){
							DNMapping dnm = new DNMapping();
							dnm.setAddTime(new Date());
							dnm.setDemandId(d.getId());
							dnm.setNodeId(Integer.parseInt(para));
							dnm.setProjectId(projectId);
							dnm.setState(1);
							dNMappingMapper.insertSelective(dnm);
						}
					}
					
				}	
				return JsonHandler.writeJsontoResponse(1000,d.getId());
				
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
	@RequestMapping(value="/deletedemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deletedemand(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				int id = obj.getInt("id");
				demandMapper.logicalDeleteByPrimaryKey(id);
				demandRelationMapper.logicalDeleteListByCombinationIdOrElementId(id);
				dNMappingMapper.logicalDeleteListByDemandId(id);
				
				//是否要删除dtmapping表中的相关记录？？？
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
	
	private DemandInfo getChilds(int id) {
		Demand demand = demandMapper.selectByPrimaryKey(id);
		DemandInfo demandInfo = null;
		if (demand != null) {//
			demandInfo = new DemandInfo(demand);
			// 获取子标题
			List<Demand> listdemand = demandMapper.selectDemandListByParentId(id);
			List<DemandInfo> listDemandInfos = new ArrayList<DemandInfo>();
			for (Demand d : listdemand) {
				DemandInfo ci = getChilds(d.getId());
				if (ci != null)
					listDemandInfos.add(ci);
			}
			demandInfo.setItems(listDemandInfos);
		}
		return demandInfo;
	}
}
