package cn.lionl.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

public class JwtUtil {
    // 过期时间为 30
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    // 秘钥
    private static final String TOKEN_SECRET = "cnwqui^&*^!hufdqwuif147hBYG78*g86&";

    /**
     * 创建token
     *
     */
    public static String sign(long userId){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证token
     *
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (IllegalArgumentException | JWTVerificationException e){
            return false;
        }

        return true;
    }
}
