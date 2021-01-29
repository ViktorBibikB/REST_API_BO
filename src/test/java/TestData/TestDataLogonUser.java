package TestData;

import java.util.HashMap;

public class TestDataLogonUser {
    private static HashMap<String, String> getUserLoginData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("X_Application_Key", "584fe2e750315eaa9803afd09b9137679da835de");
        map.put("logon_name", "41800745@mailinator.com"); // logon name as email
        map.put("device_id", "X-GAZOLINA-API"); // deviceID
        map.put("access_mode", "readwrite"); // user access_mode
        map.put("edrpou", "41800745");
        return map;
    }

    public String getXAppKey(){

        return getUserLoginData().get("X_Application_Key");
    }
    public String getLoginName(){

        return getUserLoginData().get("logon_name");
    }
    public String getDeviceId(){

        return  getUserLoginData().get("device_id");
    }
    public String getAccessMode(){

        return getUserLoginData().get("access_mode");
    }
    public String getUserEDRPOU(){

        return  getUserLoginData().get("edrpou");
    }

}
