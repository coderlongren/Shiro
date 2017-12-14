# Shrio 权限框架的学习
## shiro的基本功能
* `Authentication`：身份认证/登录，验证用户是不是拥有相应的身份；
* `Authorization`：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用  
户是否能进行什么操作，如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户  
对某个资源是否具有某个权限；
*` Session Manager`：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有  
信息都在会话中；会话可以是普通 JavaSE 环境，也可以是 Web 环境的；
* Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
* Web Support：Web 支持，可以非常容易的集成到Web 环境；
* Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可  
以提高效率；
*  Concurrency：Shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能
* 把权限自动传播过去；
* Testing：提供测试支持；
* Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
* ` Remember Me`：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登
录了


## shiro的架构
* `Subject`应用代码直接交互的对象就是Subject,也就是说Shiro的对外API核心就是Subject，subject  
代表了Subject 代表了当前“用户”,与 Subject 的所有交互都会委托给 SecurityManager；
Subject 其实是一个门面，SecurityManager 才是实际的执行者；
* `SecurityManager`：安全管理器；即所有与安全有关的操作都会与
SecurityManager 交互；且其管理着所有 Subject；可以看出它是 Shiro
的核心，它负责与 Shiro 的其他组件进行交互，它相当于 SpringMVC 中
DispatcherServlet 的角色
* `Realm` Shiro 从 Realm 获取安全数据（如用户、角色、权限）

## 从内部看待Shiro
*  Authenticator：
*   Subject：
*   SecurityManager
*    Authorizer：
*     很少用到SessionManager(管理Session生命周期),CacheManager(缓存控制器),Crytography(密码模块)

### 和Web集成
Web.xml 必须要配置的 ShrioFilter
```
<filter>
    <filter-name>shiroFilter</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    <init-param>
      <param-name>targetFilterLifecycle</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>shiroFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```
zai Application.xml文件中 这里的Filter必须和Web.xml中保持一致
```
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/login.jsp"/>
        <property name="successUrl" value="/list.jsp"/>
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/><!-- 没有权限的页面 -->
        
         <property name="filterChainDefinitionMap" ref="filterChainDefinitionMap"></property>
        
        
        <!--  
            配置哪些页面需要受保护. 
        	以及访问这些页面需要的权限. 
        	1). anon 可以被匿名访问
        	2). authc 必须认证(即登录)后才可能访问的页面. 
        	3). logout 登出.
        	4). roles 角色过滤器         很重要!!!!
        -->
        
        <!-- <property name="filterChainDefinitions">
            <value>
            	/login.jsp = anon 匿名登录页面
            	/shiro/login = anon
                /shiro/logout = logout  登出
                
                /user.jsp = roles[user]
                /admin.jsp = roles[admin]
                
                
                # 除了上面配置的可以匿名访问的之外 都需要认证:
                /** = authc
            </value>
        </property> -->
    </bean>
    <bean id="shiroService" class="com.coderlong.service.ShiroService"></bean>
    <!-- 配置一个bean 应该是一个map，通过实例工厂方法的方式得到bean -->
    <bean id="filterChainDefinitionMap"
    	factory-bean="filterChainDefinitionMapBuilder" factory-method="buildFilterChainDefinitionMap">
    </bean>
```
URL 权限采取第一次匹配优先的方式 
### 认证
认证顺序是 Application-> Subject-> ShiroSecurityManager-> Realm
### 身份验证 
在Handler中 **Subject currentUser = SecurityUtils.getSubject()** 获得当前Subject  
**!currentUser.isAuthenticated())** 
用户需要提供 principals（身份）  credentials（凭证）来登录
### 身份验证基本的流程 
1. 收集用户身份/凭证，即如用户名/密码
2. 调用 Subject.login 进行登录
3. 创建自定义的 Realm 类，继承org.apache.shiro.realm.AuthorizingRealm 类，实现
doGetAuthenticationInfo() 方法


## 授权
应该是可以采用 编程授权，或者注解氏授权，


通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
1. 获取当前的Subject, 调用SecurityUtils.getSubject()
2. 测试当前的用户是否已经被认证，即是否已经登录