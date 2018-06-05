package cn.jf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/")
public class LoginController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @RequestMapping("/login")
    public String login(HttpServletRequest request,String userName, String userPwd) {
        if(userName==null || userPwd==null){
            return "login";
        }
        else if(userName.equals("jingdong") && simpleDateFormat.format(Calendar.getInstance().getTime()).equals(userPwd)) {
            request.getSession().setAttribute("user", userName);
            return "redirect:/data/";
        } else {
            return "login";
        }
    }
}
