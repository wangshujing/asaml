package com.saml.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonHandler {

	public static String readJsonFromRequest(HttpServletRequest req) {
		StringBuffer jsonBuf = new StringBuffer();
		char[] buf = new char[1024];
		int len = -1;
		try {
			BufferedReader reader = req.getReader();
			while ((len = reader.read(buf)) != -1) {
				jsonBuf.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonBuf.toString();
	}

	public static String getpath(String value) {
		JsonHandler jsonHandler = new JsonHandler();
		return jsonHandler.read(value, 2);
	}

	public static Map<String, Object> writeJsontoResponse(int errorcode,
			Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> info = new HashMap<String, Object>();
		JsonHandler jsonHandler = new JsonHandler();
		info.put("errorinfo", jsonHandler.read(String.valueOf(errorcode), 1));
		info.put("info", obj);
		map.put("errorcode", errorcode);
		map.put("result", info);
		return map;
	}
	public static void writeJsonStreamFromResponse(HttpServletResponse response, String result) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.append(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String read(String name, int type) {
		try {
			InputStream in = (type == 1) ? this.getClass().getResourceAsStream(
					"/com/saml/config/errorcode.properties") : this.getClass()
					.getResourceAsStream("/com/saml/config/file.properties");
			Properties p = new Properties();
			p.load(in);
			String ret = p.getProperty(name, "");
			in.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
