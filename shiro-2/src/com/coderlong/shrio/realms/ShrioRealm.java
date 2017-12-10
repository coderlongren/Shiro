package com.coderlong.shrio.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class ShrioRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println("doGetAuthenticationInfo:" + token.hashCode());
		// 1 ��Authenticationtoken ת��ΪusernamePasswordtoken
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		//2 ��usernamepsaawordtoken �л�ȡusername
		String username = uptoken.getUsername();
		//3  �������ݿ�ķ����������ݿ��в�ѯusername��Ӧ���û���¼ 
		// ������ �Ͳ��������ݿ��� 
		System.out.println("�����ݿ�ȡ������");
		//4 ����û������ھͿ����׳� UnknowAccountException �쳣
		 if("unknow".equals(username)){
			 throw new UnknownAccountException("�û�������");
		 }
		//5 �����û���Ϣ����������Ƿ���Ҫ�׳�������AuthenticationException�쳣 
		if ("monster".equals(username)){
			throw new UnknownAccountException("�û�������");
		}
		 

		//6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
		//������Ϣ�Ǵ����ݿ��л�ȡ��.
		//1). principal: ��֤��ʵ����Ϣ. ������ username, Ҳ���������ݱ��Ӧ���û���ʵ�������. 
		Object principal = username;
		//2). credentials: ����. 
		Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
		if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials = "098d2c478e9c11555ce2823231e02ec1";
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
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}
