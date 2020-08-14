package com.lanxin.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor//所有参的构造方法
public class errors extends RuntimeException{
    private Integer code;//返回码
    private String msg;//返回信息
}
