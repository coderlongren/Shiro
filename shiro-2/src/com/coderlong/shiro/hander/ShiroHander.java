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
* @author ���� : coderlong
* @version ����ʱ�䣺2017��12��10�� ����8:33:45
* ��˵��: test
*/

//shiro/login
@Controller
@RequestMapping("/shiro")
public class ShiroHander {
	@RequestMapping("/login")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password")String password){
		// ��ȡ��ǰ�� Subject. ���� SecurityUtils.getSubject();
		System.out.println("���뵽hander������");

        Subject currentUser = SecurityUtils.getSubject();
		
        // let's login the current user so we can check against roles and permissions:
        // ���Ե�ǰ���û��Ƿ��Ѿ�����֤. ���Ƿ��Ѿ���¼. 
        // ���� Subject �� isAuthenticated() 
        if (!currentUser.isAuthenticated()) {
        	// ���û����������װΪ UsernamePasswordToken ����
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println("hander�����token hashcode" + token.hashCode());
            
            // rememberme
            token.setRememberMe(true);
            try {
            	// ִ�е�¼. 
                currentUser.login(token); 
            } 
            // ��û��ָ�����˻�, �� shiro �����׳� unknowAccountexception
            catch (UnknownAccountException uae) {
//                log.info("----> There is no user with username of " + token.getPrincipal());
//                return; 
            	System.out.println("��¼ʧ��" + uae.getMessage());
            } 
        }
		System.out.println("���뵽hander������");
		
		return "redirect:/list.jsp";
	}
}
