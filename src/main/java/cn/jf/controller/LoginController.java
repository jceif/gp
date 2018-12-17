package cn.jf.controller;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/")
public class LoginController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        if(request.getSession().getAttribute("user")!=null){
            return "redirect:/data/";
        }else {
            return "redirect:/login/";
        }
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request,String userName, String userPwd, String rememberMe, String type) {
        String error = "";
        if(userName==null || userPwd==null){
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null && subject.getPrincipal()!=null){
            return "redirect:/data/";
        }else {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
            if (rememberMe != null && "true".equals(rememberMe))
                token.setRememberMe(true);    // 记住我
            try {
                subject.login(token);
            } catch (UnknownAccountException | IncorrectCredentialsException e1) {
                error = "用户名或密码错误";
            } catch (ExcessiveAttemptsException e) {
                //userService.lockAccountByNo(no);     // 锁定账户
                error = "超过了尝试登录的次数，您的账户已经被锁定。";
            } catch (AuthenticationException e) {    // 其他错误
                if (e.getMessage() != null)
                    error = "发生错误：" + e.getMessage();
                else
                    error = "发生错误，无法登录。";
            }
        }
        if(StringUtils.isEmpty(error)){
            return "redirect:/data/";
        }else{
            return "redirect:/login/";
        }
    }
}
