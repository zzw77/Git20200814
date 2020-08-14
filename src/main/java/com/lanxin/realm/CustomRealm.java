package com.lanxin.realm;

import com.lanxin.dao.RolesDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

   @Autowired
   RolesDao rolesDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
          //得到username
          String username=(String)principalCollection.getPrimaryPrincipal();
          /*查询权限和角色*/
          Set<String> roles=rolesDao.rolesname();
          Set<String> permissions=rolesDao.permissions(username);
          /*把查到的权限和角色存进simpleAuthorizationInfo 准备验证*/
          SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
          simpleAuthorizationInfo.setRoles(roles);
          simpleAuthorizationInfo.setStringPermissions(permissions);


        return simpleAuthorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String) authenticationToken.getPrincipal();
        String pass=rolesDao.userspassword(username);
        if(pass!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,pass,"customRealm");
            //加盐
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("#$$%$^"));
            return simpleAuthenticationInfo;
        }
        return null;
    }

   /* //验证
    public static void main(String[] args) {
        CustomRealm customRealm=new CustomRealm();
        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        *//*加密*//*
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();
        //加盐
        String str="#$$%$^";
        UsernamePasswordToken token=new UsernamePasswordToken("tom","123",str);
        subject.login(token);
        //返回true说明有这个用户
        System.out.println(subject.isAuthenticated());

        //验证角色 没有就抛出异常
        subject.checkRole("admin");
        //验证权限 没有就抛出异常
        subject.checkPermissions("user:add","user:select");
    }*/
}
