package com.yipeng.baseservice.authority.utils;
import com.yipeng.baseservice.authority.configuration.JwtCofnig;
import com.yipeng.framework.core.exception.ErrorCode;
import com.yipeng.framework.core.exception.ExceptionUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * jwt token生成，信息获取
 * @author: yibingzhou
 */
@Slf4j
public class JwtTokenUtil  {

    /**
     * 解析jwt
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            throw ExceptionUtil.doThrow(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e){
            throw ExceptionUtil.doThrow(ErrorCode.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 构建jwt
     * @param userId
     * @param appId
     * @param roles
     * @param jwtCofnig
     * @return
     */
    public static String createToken(String userId, String appId, List<String> roles, JwtCofnig jwtCofnig) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtCofnig.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken")
                    // 可以将基本不重要的对象信息放到claims
                    // 代表这个JWT的签发主体；
                    .claim("role", roles)
                    .setSubject(userId)
                    .setIssuer(jwtCofnig.getIssuer())
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(appId)
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = jwtCofnig.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                // 是一个时间戳，代表这个JWT的过期时间；
                builder.setExpiration(exp)
                        // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
                        .setNotBefore(now);
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            throw ExceptionUtil.doThrow(ErrorCode.SIGNATURE_FAILED);
        }
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security){
        return parseJWT(token, base64Security).getSubject();
    }

    public static String getAppId(String token, String base64Security) {
        return parseJWT(token, base64Security).getAudience();
    }

    public static List<String> getRoles(String token, String base64Security) {
        Object o = parseJWT(token, base64Security).get("role");
        return (List) o;
    }

    /**
     * 是否已过期
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}
