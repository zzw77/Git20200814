package com.lanxin.realm;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Tset {
    public static void main(String[] args) {
        Md5Hash md5Hash=new Md5Hash("123","#$$%$^",1);
        System.out.println(md5Hash.toString());
    }
}
