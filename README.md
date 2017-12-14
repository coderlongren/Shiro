# Shrio 权限框架的学习
* Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
* Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用  
户是否能进行什么操作，如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户  
对某个资源是否具有某个权限；
* Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有  
信息都在会话中；会话可以是普通 JavaSE 环境，也可以是 Web 环境的；
* Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
* Web Support：Web 支持，可以非常容易的集成到Web 环境；
* Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可  
以提高效率；
*  Concurrency：Shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能
* 把权限自动传播过去；
* Testing：提供测试支持；
* Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
* Remember Me：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登
录了
## 密码的比对 
通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
1. 获取当前的Subject, 调用SecurityUtils.getSubject()
2. 测试当前的用户是否已经被认证，即是否已经登录
