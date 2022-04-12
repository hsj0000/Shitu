package cn.jsu.projectmanage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: suixuexue
 * @date: 2020/12/17 15:12
 * describe:
 * json封装体
 * <p>
 * | *状态码* | *含义*                 | *说明*
 * | --------| --------------------- | -------------------------------------------
 * | 200     | OK                    | 请求成功
 * | 201     | CREATED               | 创建成功
 * | 204     | DELETED               | 删除成功
 * | 400     | BAD REQUEST           | 请求的地址不存在或者包含不支持的参数
 * | 401     | UNAUTHORIZED          | 未授权
 * | 403     | FORBIDDEN             | 被禁止访问
 * | 404     | NOT FOUND             | 请求的资源不存在
 * | 444     | Fail                  | 失败
 * | 422     | Unprocesable entity   | [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误
 * | 500     | INTERNAL SERVER ERROR | 内部错误
 */
@ApiModel(value = "返回结果json封装体")
public class CommonResult<T> implements Serializable
{
    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    @ApiModelProperty(value = "消息")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

    public CommonResult()
    {
    }

    public CommonResult(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    private static <T> CommonResult<T> restResult(T data,Integer code,String msg){
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(msg);
        return result;
    }

    public static <T> CommonResult<T> failed(Integer code,String msg){
        return restResult(null,code,msg);
    }

    public static <T> CommonResult<T> ok(T data){
        if(data instanceof Boolean && Boolean.FALSE.equals(data)){
            return restResult(data,0,"执行失败");
        }
        return restResult(data,200,"执行成功");
    }
}