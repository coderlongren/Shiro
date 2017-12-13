package com.coderlong.shrio.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		
		System.out.println("[SeconRealm] doGetAuthenticationInfo");
		//1. �� AuthenticationToken ת��Ϊ UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. �� UsernamePasswordToken ������ȡ username
		String username = upToken.getUsername();
		
		//3. �������ݿ�ķ���, �����ݿ��в�ѯ username ��Ӧ���û���¼
		System.out.println("�����ݿ��л�ȡ username: " + username + " ����Ӧ���û���Ϣ.");
		
		//4. ���û�������, ������׳� UnknownAccountException �쳣
		if("unknown".equals(username)){
			throw new UnknownAccountException("�û�������!");
		}
		
		//5. �����û���Ϣ�����, �����Ƿ���Ҫ�׳������� AuthenticationException �쳣. 
		if("monster".equals(username)){
			throw new LockedAccountException("�û�������");
		}
		
		//6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
		//������Ϣ�Ǵ����ݿ��л�ȡ��.
		//1). principal: ��֤��ʵ����Ϣ. ������ username, Ҳ���������ݱ��Ӧ���û���ʵ�������. 
		Object principal = username;
		//2). credentials: ����. 
		Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
		if("admin".equals(username)){
			credentials = "13215500ae4ea87b410201e85837af7f";
		}else if("user".equals(username)){
			credentials = "169f8cc66024d24306331acfe1064381";
		}
		else {
			System.out.println("����Ĳ����û���");
		}
		
		//3). realmName: ��ǰ realm ����� name. ���ø���� getName() ��������
		String realmName = getName();
		//4). ��ֵ. 
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "12345";
		Object salt = ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
	//��Ȩ�ᱻ shiro �ص��ķ���
//	doGetAuthenticationInfo
		protected AuthorizationInfo doGetAuthorizationInfo(
				PrincipalCollection principals) {
			//1. �� PrincipalCollection ������ȡ��¼�û�����Ϣ
			Object principal = principals.getPrimaryPrincipal();
			
			//2. ���õ�¼���û�����Ϣ���û���ǰ�û��Ľ�ɫ��Ȩ��(������Ҫ��ѯ���ݿ�)
			Set<String> roles = new HashSet<>();
			roles.add("user");
			if("admin".equals(principal)){
				roles.add("admin");
			}
			
			//3. ���� SimpleAuthorizationInfo, �������� reles ����.
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
			
			//4. ���� SimpleAuthorizationInfo ����. 
			return info;
		}
	
}
