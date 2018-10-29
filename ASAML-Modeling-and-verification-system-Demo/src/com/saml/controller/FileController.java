package com.saml.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.saml.util.JsonHandler;
import com.saml.util.LogRecord;
@Controller
@RequestMapping("/fileHandler")
public class FileController {
	
	/**
	 * 上传文件至缓存目录接口
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFile(@RequestParam("type") int type,
			HttpServletRequest request, HttpServletResponse response) {
		
		MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
		String newfilePath ="";
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			
			MultipartFile mf = multiReq.getFile("fileToUpload");    
            if(!mf.isEmpty()){
                    //取得当前上传文件的名称
                    String originFileName = mf.getOriginalFilename();
                    //如果名称不为""，说明该文件存在，否则说明文件不存在。
                    if(originFileName.trim()!=""){
                    	if (originFileName.lastIndexOf('.') < 0) {
        					return JsonHandler.writeJsontoResponse(1024, "");
        				}
                    	String filetype = originFileName.substring(originFileName
        						.lastIndexOf('.') + 1);
                    	if(type==1){
                    		if (!filetype.equals("xml")) {
            					return JsonHandler.writeJsontoResponse(1024, "");
            				}
                    	}else if(type==2){
                    		if (!filetype.equals("xlsx")&&!filetype.equals("xls")) {
            					return JsonHandler.writeJsontoResponse(1024, "");
            				}
                    	}
                    	
                    	//重命名上传后的文件
                        newfilePath = GenerateFileName()+"."+filetype;
                        //定义上传路劲
                        String path = new FileController().readLocalFilePathConfig("localFilePath")+newfilePath;
                        File localFile = new File(path);
                        mf.transferTo(localFile);
                    }   
                }    
            map.put("path", newfilePath);
			return JsonHandler.writeJsontoResponse(1000, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogRecord.ERROR.error(e.toString());
			return JsonHandler.writeJsontoResponse(1001, "");
		}
	}
	public static String GenerateFileName() {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化当前系统日期
		String name = dateFm.format(new Date())
				+ "_"// file.getOriginalFilename()获取原始文件名
				+ new Random(System.currentTimeMillis()).nextInt();
		return name;
	}
	public String readLocalFilePathConfig(String name) {
		try {
			InputStream in = this.getClass().getResourceAsStream(
					"/com/saml/config/localFilePath.properties");
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
