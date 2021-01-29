package Helpers;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javax.json.JsonObject;

import static Helpers.GettersSetters.*;

public class Helpers {

    public static String receiveDecodedJWTAsString(String jwtBody){
        String[] split_string = jwtBody.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String jwt = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : " + jwt);
        return jwt;
    }

}
