package com.lanxin.controller;

import com.lanxin.lombok.MyResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RolesController {

    @RequestMapping("/login")
    public MyResult login(String username,String pass){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token= new UsernamePasswordToken(username,pass);

        try {
            subject.login(token);
            return MyResult.ok("登录成功");
        } catch (IncorrectCredentialsException e) {
            return MyResult.fail(1001,"密码错误");
        } catch (AuthenticationException e){
           return MyResult.fail(1002,"用户不存在");
        }
    }

    @RequestMapping("/unauth")//退出
    public MyResult unauth(){
        return MyResult.fail(10010,"你还没有登录");
    }

    @RequestMapping("/logout")//退出
    public MyResult logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return MyResult.ok("退出成功");
    }

    @RequiresPermissions("user:select")
    @RequestMapping("/select")
    public MyResult select(){
        return MyResult.ok("查询成功");
    }
    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public MyResult add(){
        return MyResult.ok("添加成功");
    }
    @RequiresPermissions("user:update")
    @RequestMapping("/update")
    public MyResult update(){
        return MyResult.ok("修改成功");
    }
    @RequiresPermissions("user:delete")
    @RequestMapping("/delete")
    public MyResult delete(){
        return MyResult.ok("删除成功");
    }

}
