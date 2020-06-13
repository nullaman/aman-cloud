package com.aman.cloud.entities;

import java.io.Serializable;

/**
 * 接口公共返回编码，如业务模块拥有多种返回编码，请继承此类实现自己不同的业务编码
 *
 * @author aman
 * @date 2020/06/08
 **/
public class ResultCode implements Serializable {

    /*====================================================请求成功=====================================================*/

    /**
     * 请求成功
     */
    public final static Integer SUCCESS = 200;

    /*====================================================认证失败=====================================================*/

    /**
     * 参数验证失败
     */
    public final static Integer ERROR_PARAMETER = 400;

    /**
     * 未登录
     */
    public final static Integer ERROR_NOT_LOGIN = 401;

    /**
     * 其它地方登录，被强迫下线
     */
    public final static Integer ERROR_FORCED_OFFLINE = 402;

    /**
     * 认证失败
     */
    public final static Integer ERROR_AUTHENTICATION_FAILED = 403;

    /**
     * 公众号未授权
     */
    public final static Integer ERROR_NOT_AUTHORIZE = 404;


    /**
     * 不允许访问
     */
    public final static Integer NOT_ALLOW = 405;


    /**
     * 不允许访问
     */
    public final static Integer NOT_CHOOSE_EXAM = 406;


    /**
     * 没有token
     */
    public final static Integer NOT_ACCESS_TOKEN = 407;

    /*====================================================服务器错误===================================================*/

    /**
     * 服务端错误
     */
    public final static Integer ERROR_SERVER = 500;

    /*================================================================================================================*/
}
