/**
 * FileName: JwtUtils
 * Author:   liuws
 * Date:     2022/6/4 17:37
 */
package cn.edu.nhic.nhtj.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "nh.jwt")
public class JwtUtils {
    // 后台秘钥

    private static  String adminkey ="";
    //前台秘钥
    private static String userkey="";
    //过期时间，单位为小时
    private static  Integer expire=2;
    // 签名是有谁生成
    private static  String issuser="nhcar";
    // 签名的主题
    private static  String subject="nhcar token";
    // 签名的观众
    private static  String client="nhcarapp";

    public void setAdminkey(String key){
        JwtUtils.adminkey =key;
    }
    public void setUserkey(String key){JwtUtils.userkey=key;}
    public void setExpire(Integer expire){
        JwtUtils.expire=expire;
    }

    public static String createAdminToken(Integer id){
        return createToken(adminkey,id);
    }
    public static String createUserToken(Integer id){
        return createToken(userkey,id);
    }

    public static String createToken(String key, Integer id){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            // 过期时间：2小时
            Date expireDate = expireDate();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("id", id)
                    .withIssuer(issuser)
                    .withSubject(subject)
                    .withAudience(client)
                    // 生成签名的时间
                    .withIssuedAt(nowDate)
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public static Integer verifyAdminTokenAndGetId(String token){
        return verifyTokenAndGetId(adminkey,token);
    }

    public static Integer verifyUserTokenAndGetId(String token){
        return verifyTokenAndGetId(userkey,token);
    }

    public static Integer verifyTokenAndGetId(String key,String token) {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuser)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("id");
            return claim.asInt();
    }

    private static Date expireDate(){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, expire);
        return nowTime.getTime();
    }

    public static void main(String[] args) {

    }
}
