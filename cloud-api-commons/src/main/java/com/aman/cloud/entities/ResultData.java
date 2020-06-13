package com.aman.cloud.entities;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 接口公共返回编码，如业务模块拥有多种返回编码，请继承此类实现自己不同的业务编码
 *
 * @author aman
 * @date 2020/06/08
 **/
@ApiModel(value = "响应信息", description = "返回响应信息")
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = -1517272643101952846L;

    /**
     * 接口返回编码，不同的业务模块需自己定义
     */
    @ApiModelProperty(value = "响应状态码")
    private Integer code;
    /**
     * 接口返回消息，供前端参考
     */
    @ApiModelProperty(value = "描述信息")
    private String message;
    /**
     * 接口返回数据
     */
    @ApiModelProperty(value = "返回对象")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData() {
    }

    /**
     * 接口调用成功返回的结果
     *
     * @return
     */
    public static <T> ResultData<T> success() {
        return new ResultData<>(ResultCode.SUCCESS, "操作成功");
    }


    /**
     * 接口调用成功返回的结果（无数据，自定义消息）
     *
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> success(String message) {
        return new ResultData<>(ResultCode.SUCCESS, message);
    }

    /**
     * 接口调用成功返回的结果(文本)
     *
     * @param data 携带的数据
     * @return
     */
    public static <T> ResultData<T> successForStr(T data) {
        return new ResultData<>(ResultCode.SUCCESS, "", data);
    }

    /**
     * 接口调用成功返回的结果（无数据，自定义消息）
     *
     * @param data 数据
     * @return
     */
    public static <T> ResultData<T> data(T data) {
        return new ResultData<>(ResultCode.SUCCESS, "操作成功", data);
    }

    /**
     * 接口调用成功返回的结果（无数据，自定义消息，自定义编码）
     *
     * @param code    返回的编码
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> success(Integer code, String message) {
        return new ResultData<>(code, message);
    }

    /**
     * 接口调用成功返回的结果
     *
     * @param data 携带的数据
     * @return
     */
    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(ResultCode.SUCCESS, "操作成功", data);
    }

    /**
     * 接口调用成功返回的结果（自定义消息）
     *
     * @param data    携带的数据
     * @param message 返回给请求者的消息
     * @return
     */
    public static <T> ResultData<T> success(T data, String message) {
        return new ResultData<>(ResultCode.SUCCESS, StringUtils.isNotBlank(message) ? message : "", data);
    }

    /**
     * 接口调用成功返回的结果（自定义消息，自定义编码）
     *
     * @param data    携带的数据
     * @param code    返回的编码
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> success(T data, Integer code, String message) {
        return new ResultData<>(code, StringUtils.isNotBlank(message) ? message : "", data);
    }

    /**
     * 接口调用失败返回的结果（自定义编码，自定义消息）
     *
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> error(String message) {
        return new ResultData<>(ResultCode.ERROR_PARAMETER, StringUtils.isNotBlank(message) ? message : "请求异常");
    }

    /**
     * 接口调用失败返回的结果（自定义编码，自定义消息）
     *
     * @return
     */
    public static <T> ResultData<T> error() {
        return error("操作失败");
    }

    /**
     * 接口调用失败返回的结果（自定义编码，自定义消息）
     *
     * @param code    返回的编码
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> error(Integer code, String message) {
        return new ResultData<>(code, StringUtils.isNotBlank(message) ? message : "请求异常");
    }


    /**
     * 用户请登录
     *
     * @return
     */
    public static <T> ResultData<T> notLogin() {
        return ResultData.error(ResultCode.ERROR_NOT_LOGIN, "请先登录");
    }

    /**
     * 用户请登录
     *
     * @return 401
     */
    public static <T> ResultData<T> notLogin(String msg) {
        return ResultData.error(ResultCode.ERROR_NOT_LOGIN, msg);
    }

    /**
     * 请选择考试
     *
     * @return 406
     */
    public static ResultData notChooseExam() {
        return ResultData.error(ResultCode.NOT_CHOOSE_EXAM, "请选择考试");
    }

    /**
     * 没有token
     *
     * @return 406
     */
    public static ResultData notAccessToken() {
        return ResultData.error(ResultCode.NOT_ACCESS_TOKEN, "请先获取accessToken");
    }

    /**
     * 用户没有权限访问
     *
     * @return
     */
    public static <T> ResultData<T> notAllow() {
        return ResultData.error(ResultCode.NOT_ALLOW, "没有权限访问");
    }


    /**
     * 用户还没有进行公众号授权
     *
     * @return
     */
    public static <T> ResultData<T> notAuthorize() {
        return ResultData.error(ResultCode.ERROR_NOT_AUTHORIZE, "请先授权");
    }

    /**
     * 接口调用失败返回的结果（带数据，自定义编码，自定义消息）
     *
     * @param data    携带的数据
     * @param code    返回的编码
     * @param message 返回的消息
     * @return
     */
    public static <T> ResultData<T> error(T data, Integer code, String message) {
        return new ResultData<>(code, StringUtils.isNotBlank(message) ? message : "请求异常", data);
    }

    /**
     * 500 异常
     *
     * @return {@link ResultData}
     */
    public static ResultData error500(int errorId) {
        return new ResultData<>(ResultCode.ERROR_SERVER, "访问失败，请稍后重试", errorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultData<?> that = (ResultData<?>) o;
        return Objects.equal(code, that.code) &&
                Objects.equal(message, that.message) &&
                Objects.equal(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, message, data);
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
