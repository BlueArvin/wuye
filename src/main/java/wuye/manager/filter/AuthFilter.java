package wuye.manager.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wuye.manager.enums.RoleAuthEnum;
import wuye.manager.menu.logic.LoginLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.SpringContextHolder;


public class AuthFilter implements Filter {

    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
    	try {
            HttpServletRequest req = (HttpServletRequest)arg0;
            HttpServletResponse response = (HttpServletResponse)arg1;
            response.setContentType("text/html;charset=UTF-8");
            String path = req.getContextPath();  
            String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
            
            System.out.println("req==="+basePath);
            String serverPath = req.getServletPath();
            System.out.println("serverPath=="+req.getServletPath());
            
            if(serverPath.indexOf("login")>0){
            	arg2.doFilter(req, response);
            	return;
            }
            
            HttpSession session = req.getSession();
            UserBean ub = (UserBean) session.getAttribute("user");
            
            if(ub!=null){
            	arg2.doFilter(req, response);
            	
            	//获取返回
            	//判断是否需要刷新数据库version
            	
            	boolean version = RoleAuthEnum.AreaVersionEnum.include(serverPath);
            	boolean checkversion = RoleAuthEnum.normVersionEnum.include(serverPath);
            	System.out.println("version:"+version+"------------checkversion:"+checkversion);
            	
            	//更新t_sys 版本
            	if(version||checkversion){
            		LoginLogic loginLogic = SpringContextHolder.getBean("loginLogic");
            		loginLogic.updateSysVersion(version, checkversion);
            	}
            	return;
            }else{
            	response.setHeader("Cache-Control", "no-store");  
            	response.setDateHeader("Expires", 0);  
            	response.setHeader("Prama", "no-cache");  
            	response.sendRedirect(basePath+"/wuye/login.jsp");  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    @Override
    public void destroy() {
    	
    }

}
