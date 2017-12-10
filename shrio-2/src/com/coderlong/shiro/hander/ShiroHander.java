package com.coderlong.shiro.hander;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author 作者 : coderlong
* @version 创建时间：2017年12月10日 下午8:33:45
* 类说明: test
*/

//shiro/login
@Controller
@RequestMapping("/shiro")
public class ShiroHander {
	@RequestMapping("/login")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password")String password){
		// 获取当前的 Subject. 调用 SecurityUtils.getSubject();
		System.out.println("进入到hander里面了");

        Subject currentUser = SecurityUtils.getSubject();
		
        // let's login the current user so we can check against roles and permissions:
        // 测试当前的用户是否已经被认证. 即是否已经登录. 
        // 调动 Subject 的 isAuthenticated() 
        if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println("hander里面的token hashcode" + token.hashCode());
            
            // rememberme
            token.setRememberMe(true);
            try {
            	// 执行登录. 
                currentUser.login(token); 
            } 
            // 若没有指定的账户, 则 shiro 将会抛出 unknowAccountexception
            catch (UnknownAccountException uae) {
//                log.info("----> There is no user with username of " + token.getPrincipal());
//                return; 
            	System.out.println("登录失败" + uae.getMessage());
            } 
        }
		System.out.println("进入到hander里面了");
		
		return "redirect:/list.jsp";
	}
}
