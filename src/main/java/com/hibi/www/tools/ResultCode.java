package com.hibi.www.tools;

/**
 * 请求响应返回码
 * 作者：penglei
 * 日期：2018年8月13日08:50:36
 */
public enum ResultCode {

    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//验证错误，未认证（签名错误）
    NOT_FOUND(404),//找不到文件，找不到资源错误
    INTERNAL_SERVER_ERROR(500);//服务器响应错误

    public  int code;
    ResultCode(int code){
        this.code = code;
    }

}
