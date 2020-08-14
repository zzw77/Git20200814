package com.lanxin.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//无参的构造方法
public class MyResult {
    private Integer code;//返回码
    private String msg;//返回信息
    private Object data;//返回对象

    public MyResult(Integer code, String msg, Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }


    public static MyResult ok(Object data){

        return new MyResult(200,"操作成功",data);
    }

    public static MyResult fail(){

        return new MyResult(500,"操作失败",null

        );
    }

    public static MyResult fail(Integer code, String msg){

        return new MyResult(code,msg,null);
    }
}
