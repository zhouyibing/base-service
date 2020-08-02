import com.google.common.collect.Lists;
import com.fido.baseservice.authority.configuration.JwtCofnig;
import com.fido.baseservice.authority.utils.JwtTokenUtil;

/**
 * @author: yibingzhou
 */
public class Test {
    public static void main(String[] args) {
        JwtCofnig jwtConfig = new JwtCofnig();
        jwtConfig.setIssuer("fido");
        jwtConfig.setBase64Secret("eWlwZW5nODg4OA==");
        jwtConfig.setExpiresSecond(1800000);
        String token = JwtTokenUtil.createToken("183493", "001001", Lists.newArrayList("MEMBER","USER"), jwtConfig);
        System.out.println("token="+token);
        System.out.println(JwtTokenUtil.getUserId(token, jwtConfig.getBase64Secret()));
        System.out.println(JwtTokenUtil.getAppId(token, jwtConfig.getBase64Secret()));
        System.out.println(JwtTokenUtil.getRoles(token, jwtConfig.getBase64Secret()));
    }
}
