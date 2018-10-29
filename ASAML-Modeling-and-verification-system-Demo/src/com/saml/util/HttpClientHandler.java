package com.saml.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.saml.util.ApiServerEnum;
import com.saml.util.JsonHandler;
/*import com.saml.util.OAuthHandler;*/

import net.sf.json.JSONObject;

public class HttpClientHandler {
	
	
	/**
	 * the http post method
	 * @param apiServer  the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param json  the post parameters of JSON type.
	 * @param reqPath the request relative path, such as "/search".
	 * @return the JSON String type result.
	 */
	public static String doPost(ApiServerEnum apiServer, JSONObject json, String reqPath){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//Map<String, Object> result = null;
		String result = null;
		try {
			URI uri = generateUri(apiServer, reqPath);
			HttpPost post = new HttpPost(uri);
			StringEntity s = new StringEntity(json.toString(), "utf-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(post);
			try{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					//result = JSONObject.fromObject(EntityUtils.toString(entity, "utf-8"));// 返回json格式：
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
			}finally{
				response.close();
			}
		}catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(result == null){
			JSONObject jsonObject = JSONObject.fromObject(JsonHandler.writeJsontoResponse(6001, "")); 
			result = jsonObject.toString();
		}
		return result;
	}
	
	/**
	 * the http post method
	 * @param apiServer  the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param nvps  the post parameters of NameValuePair type.
	 * @param reqPath the request relative path, such as "/search".
	 * @return the JSON String type result.
	 */
	public static String doPost(ApiServerEnum apiServer, List <NameValuePair> nvps, String reqPath){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//Map<String, Object> result = null;
		String result = null;
		try {
			URI uri = generateUri(apiServer, reqPath);
			HttpPost post = new HttpPost(uri);
			/*StringEntity s = new StringEntity(json.toString(), "utf-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(post);*/
			post.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response = httpclient.execute(post);
			try{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					//result = JSONObject.fromObject(EntityUtils.toString(entity, "utf-8"));// 返回json格式：
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
			}finally{
				response.close();
			}
		}catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(result == null){
			JSONObject jsonObject = JSONObject.fromObject(JsonHandler.writeJsontoResponse(6001, "")); 
			result = jsonObject.toString();
		}
		return result;
	}
	
	/**
	 * the http post method
	 * @param apiServer  the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param request  the request parameters of HttpServletRequest type.
	 * @param reqPath the request relative path, such as "/search".
	 * @return the JSON String type result.
	 */
	public static String doPost(ApiServerEnum apiServer, HttpServletRequest request, String reqPath){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String jsonStr = JsonHandler.readJsonFromRequest(request);
		JSONObject obj = JSONObject.fromObject(jsonStr);
		/*Map<String,Object> tmap = OAuthHandler.getOAuthInfo(request);
		if(tmap.get("token")!=null && tmap.get("userType")!=null){
			obj.put("token", tmap);
		}*/
		String result = null;
		try {
			URI uri = generateUri(apiServer, reqPath);
			HttpPost post = new HttpPost(uri);
			StringEntity s = new StringEntity(obj.toString(), "utf-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(post);
			try{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					//result = JSONObject.fromObject(EntityUtils.toString(entity, "utf-8"));// 返回json格式：
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
			}finally{
				response.close();
			}
		}catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(result == null){
			JSONObject jsonObject = JSONObject.fromObject(JsonHandler.writeJsontoResponse(4001, "")); 
			result = jsonObject.toString();
		}
		return result;
	}
	
	
	/**
	 * download the file from remote file server
	 * @param apiServer the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param response the servlet response
	 * @param localFileName the download file name to be save
	 * @param reqPath the request relative path, such as "/search".
	 * 
	 */
	public static void downloadFile(ApiServerEnum apiServer, HttpServletResponse response, String localFileName, int fid, String reqPath){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ServletOutputStream out = null;
        InputStream in = null;
		try {
			URI uri = generateUri(apiServer, reqPath);
			//封装请求参数  
	        List<NameValuePair> params = new ArrayList<NameValuePair>();  
	        params.add(new BasicNameValuePair("id", String.valueOf(fid)));  
	        String str = "";  
	        str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)); 
			HttpGet get = new HttpGet(uri.toString()+"?"+str);
			HttpResponse httpResponse = httpclient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();
         // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
 			response.setContentType("application/OCTET-STREAM;charset=UTF-8");
 			// 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
 			response.setHeader("Content-Disposition", "attachment;fileName="
 					+ localFileName);
 			// 3.通过response获取ServletOutputStream对象(out)
 			out = response.getOutputStream();
 			byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            
            out.flush();
 		} catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
 		}
    }
	
	
	/**
	 * generate URI by apiServer and request path.
	 * @param apiServer the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param path the request relative path, such as "/search".
	 * @return the URI Object.
	 * @throws URISyntaxException
	 */
	public static URI generateUri(ApiServerEnum apiServer, String path) throws URISyntaxException{
		URI uri = null;
		HttpClientHandler httpClientHandler = new HttpClientHandler();
		String scheme = null;
		String host = null;
		String basePath = null;
		switch (apiServer){
			
			case SAML:
				scheme = httpClientHandler.readApiServerConfig("C_SCHEME");
				host = httpClientHandler.readApiServerConfig("C_HOST");
				basePath = httpClientHandler.readApiServerConfig("C_BASE_PATH");
				break;
			
		}
		String fullPath = basePath + path;
		uri = new URIBuilder().setScheme(scheme)
				.setHost(host)
				.setPath(fullPath)
				.build();
		return uri;
	}
	
	
	/**
	 * generate URI by apiServer and request path and parameter.
	 * @param apiServer the type of apiServer ,include onlineSacrifice, technology, culture, u3d, recommend servers.
	 * @param path the request relative path, such as "/search".
	 * @return the URI Object.
	 * @throws URISyntaxException
	 */
	public static URI generateUri(ApiServerEnum apiServer, String path , List<NameValuePair> para) throws URISyntaxException{
		URI uri = null;
		HttpClientHandler httpClientHandler = new HttpClientHandler();
		String scheme = null;
		String host = null;
		String basePath = null;
		switch (apiServer){
			
			case SAML:
				scheme = httpClientHandler.readApiServerConfig("C_SCHEME");
				host = httpClientHandler.readApiServerConfig("C_HOST");
				basePath = httpClientHandler.readApiServerConfig("C_BASE_PATH");
				break;
			
		}
		String fullPath = basePath + path;
		uri = new URIBuilder().setScheme(scheme)
				.setHost(host)
				.setPath(fullPath).setParameters(para)
				.build();
		return uri;
	}
	
	/**
	 * read the apiServer config file to get the config value.
	 * @param name   the item of properties file content.
	 * @return the item's value
	 */
	public String readApiServerConfig(String name) {
		try {
			InputStream in = this.getClass().getResourceAsStream(
					"/com/saml/config/apiServer.properties");
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
