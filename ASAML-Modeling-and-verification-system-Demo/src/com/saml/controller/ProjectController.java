package com.saml.controller;

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

import com.saml.inter.DTMappingMapper;
import com.saml.inter.DemandMapper;
import com.saml.inter.DemandRelationMapper;
import com.saml.inter.ProjectMapper;
import com.saml.inter.ReachablePathMapper;
import com.saml.inter.SamlTypeMapper;
import com.saml.inter.TypeRuleMapper;
import com.saml.inter.VerifyResultMapper;
import com.saml.model.Project;
import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;
import com.saml.util.PageInfo;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	SamlTypeMapper samlTypeMapper;
	@Autowired
	TypeRuleMapper typeRuleMapper;
	@Autowired
	DemandMapper demandMapper;
	@Autowired
	DemandRelationMapper demandRelationMapper;
	@Autowired
	DTMappingMapper dTMappingMapper;
	@Autowired
	VerifyResultMapper verifyResultMapper;
	@Autowired
	ReachablePathMapper reachablePathMapper;
	@RequestMapping(value="/getProjectCatalog",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getProjectCatalog(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			String keyword = "%%";
			if (obj.containsKey("keyword"))
				keyword = "%" + obj.getString("keyword") + "%";
			List<Project> list = projectMapper.selectAllProject(keyword);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("project", list);
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
	@RequestMapping(value="/getProjectList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getProjectList(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int limit = obj.getInt("limit");
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
			List<Project> list = projectMapper.selectProjectListPage(page,keyword);
			int total = page.getTotalResult();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("project", list);
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
	
	@RequestMapping(value="/getProjectInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getProjectInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				Integer id = obj.getInt("id");
				Project p = projectMapper.selectByPrimaryKey(id);
					
				return JsonHandler.writeJsontoResponse(1000,p);
				
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
	
	@RequestMapping(value="/addProject",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProject(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				String projectName = obj.getString("projectName");
				String projectIntro = obj.getString("projectIntro");
				Project p = new Project();
				p.setProjectName(projectName);
				p.setProjectIntro(projectIntro);
				p.setAddTime(new Date());
				p.setState(1);
				projectMapper.insertSelective(p);
					
				return JsonHandler.writeJsontoResponse(1000,p.getId());
				
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
	@RequestMapping(value="/editProject",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editProject(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				Integer id = obj.getInt("id");
				String projectName = obj.getString("projectName");
				String projectIntro = obj.getString("projectIntro");
				Project p = new Project();
				p.setId(id);
				p.setProjectName(projectName);
				p.setProjectIntro(projectIntro);
				p.setAddTime(new Date());
				p.setState(1);
				projectMapper.updateByPrimaryKeySelective(p);	
				return JsonHandler.writeJsontoResponse(1000,p.getId());
				
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
	@RequestMapping(value="/deleteProject",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProject(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				String jsonStr = JsonHandler.readJsonFromRequest(request);
				JSONObject obj = JSONObject.fromObject(jsonStr);
				Integer id = obj.getInt("id");
				projectMapper.logicalDeleteByPrimaryKey(id);
				samlTypeMapper.logicalDeleteSamlTypeListByProjectId(id);
				typeRuleMapper.logicalDeleteTypeRuleListByProjectId(id);
				demandMapper.logicalDeleteListByProjectId(id);
				demandRelationMapper.logicalDeleteListByProjectId(id);
				dTMappingMapper.logicalDeleteListByProjectId(id);
				reachablePathMapper.logicalDeleteListByProjectId(id);
				verifyResultMapper.logicalDeleteByProjectId(id);
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
