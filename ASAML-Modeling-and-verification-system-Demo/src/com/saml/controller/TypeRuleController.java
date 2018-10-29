package com.saml.controller;

import java.util.ArrayList;
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

import com.saml.inter.TypeRuleMapper;
import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/typeRule")
public class TypeRuleController {
	@Autowired
	TypeRuleMapper typeRuleMapper;
	
	@RequestMapping(value="/getTypeRuleCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTypeRuleCount(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonStr = JsonHandler.readJsonFromRequest(request);
			JSONObject obj = JSONObject.fromObject(jsonStr);
			int projectId = obj.getInt("projectId");
			int total = 0;
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0 ; i<7;i++){
				int count = typeRuleMapper.selectTypeRuleCountByRuleType(projectId,i+1);
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
}
