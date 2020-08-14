package com.lanxin.dao;

import java.util.Set;

public interface RolesDao {
    public String userspassword(String username);
    public Set<String> rolesname();//查角色
    public Set<String> permissions(String username);//查权限
}
