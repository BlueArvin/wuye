package wuye.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserServlet {

	@RequestMapping("/login")  
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {  
		response.getWriter().write("111111");
    }  
	
	@RequestMapping("/passwd")
    public void passwd(HttpServletRequest request, HttpServletResponse response) {  

    }
}
