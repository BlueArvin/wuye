package wuye.api.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import wuye.api.bean.RetBean;

/**
 * 文件上传的处理
 * @author lujinfei
 *
 */
@Controller
@RequestMapping("/api/upload")
public class FileUploadServlet {
	
	private static Logger logger = Logger.getLogger("file");
	
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	private String dir;

	@RequestMapping("uploadpic")
	@ResponseBody
    public Object upload(HttpServletRequest request,HttpServletResponse response) {  
  
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
    	String newName = df.format(new Date());
    	
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
//        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "file" + newName + ".jpg";  
                        //定义上传路径  
                        String path = dir + fileName;
                        File localFile = new File(path);  
                        try {
							file.transferTo(localFile);
							logger.info("file upload:" + fileName);
							RetBean jsonRet = new RetBean(0, "");
							jsonRet.setValue(fileName);
							return jsonRet;
						} catch (IllegalStateException e) {
							logger.info("file upload:" + e.getMessage());
						} catch (IOException e) {
							logger.info("file upload:" + e.getMessage());
						}  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }
         RetBean jsonRet = new RetBean(99, "没有文件");
         return jsonRet; 
    }
	
	@RequestMapping("download")
    public void getUrlParam(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("name");
		OutputStream os = null;
		try {
		
			os = response.getOutputStream();
       
        	response.reset();
        	response.setHeader("Accept-Ranges", "bytes");  
            response.setHeader("Pragma", "no-cache");  
            response.setHeader("Cache-Control", "no-cache");  
            response.setDateHeader("Expires", 0); 
//        	response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//        	response.setContentType("application/octet-stream; charset=utf-8");
        	File file = new File(dir, fileName);
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
            
		}catch(Exception e){
			e.printStackTrace();
        } finally {
            if (os != null) {
                try {
					os.close();
				} catch (IOException e) {
				}
            }
        }
    }
}
