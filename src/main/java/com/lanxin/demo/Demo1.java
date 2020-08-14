package com.lanxin.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

public class Demo1 {//认证  授权

    public static void main(String[] args) {
        SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
        //模拟用户
        simpleAccountRealm.addAccount("zzw","123456","add");

        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("zzw","123456");
        subject.login(token);
        //检查是否有用户
        System.out.println(subject.isAuthenticated());

        //检查是否有权限
        subject.checkRoles("add");//没有就抛异常
    }
}
