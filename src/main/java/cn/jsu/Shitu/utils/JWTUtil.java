package cn.jsu.projectmanage.utils;

/**
 * @author Mo
 * @createTime 2022/1/4 23:03
 * @descripiton
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil
{
    //Token的私钥
    private static final String TOKEN = "jsuOJ";
    //Token的过期时间
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    //生成token
    public static String getTOKEN(Map<String, String> map)
    {
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) ->
        {
            builder.withClaim(k, v);
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 1000*60*60);//设置过期时间  时间延后60分钟
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN)).toString(); //算法加密
    }

    //验证token
    public static void verify(String token)
    {
        System.out.println(token);
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    //获取token中的payload
    public static DecodedJWT getToken(String token)
    {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}