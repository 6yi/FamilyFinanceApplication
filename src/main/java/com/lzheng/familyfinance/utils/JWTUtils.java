package com.lzheng.familyfinance.utils;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.playLoad;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @ClassName JWTUtils
 * @Author 6yi
 * @Date 2020/5/23 14:48
 * @Version 1.0
 * @Description:
 */

@Component
public class JWTUtils {
    public static final String SALT="lzheng";
    private static  Digester digester=new Digester(DigestAlgorithm.MD5);
    public static JWT getNewJwt(String type){
        JWT jwt = new JWT();
        String head=Base64.encode("{\n" +
                "  \"type\": \"JWT\",\n" +
                "  \"alg\": \"MD5\"\n" +
                "}");
        Date date = new Date();
        Long expDate=date.getTime()+86400000;
        String x="{\n" +
                "  \"type\": \""+type+"\",\n" +
                "  \"Expired\":"+expDate+"\n" +
                "}";
        String playLoad=Base64.encode(x);
        String signal=digester.digestHex(head+playLoad+SALT);
        jwt.setHead(head);
        jwt.setPlayLoad(playLoad);
        jwt.setSignal(signal);
        return jwt;
    }

    public static String toJWT(String token){
        String[] jwts = token.split("\\.");
        try{
            if(!digester.digestHex(jwts[0]+jwts[1]+SALT).equals(jwts[2])){
                return  null;
            }else{
                String decodeStr=Base64.decodeStr(jwts[1]);
                playLoad t =JSON.parseObject(decodeStr).toJavaObject(playLoad.class);
                long time = System.currentTimeMillis();
                if(t.getExpired()<time){
                    return null;
                }
                return t.getType();
            }
        }catch (Exception e){
            return null;
        }
    }

}
