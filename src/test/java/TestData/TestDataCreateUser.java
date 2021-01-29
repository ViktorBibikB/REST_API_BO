package TestData;

import java.util.HashMap;

public class TestDataCreateUser {

    private static HashMap<String, String> getCreateUserLoginData(){
        HashMap<String, String> map = new HashMap<>();

        map.put("X-Application-Key", "584fe2e750315eaa9803afd09b9137679da835de");
        map.put("create_user_logon_name", "02010936@mailinator.com"); // logon name as email
        map.put("create_user_edrpou", "02010936"); // user EGRPOU
        map.put("create_user_eic", "56XS000020AQ000J"); // user EIC-X code
        map.put("create_user_phone", "+380661138434"); // user phone number

        return map;
    }

    public String getCreateLoginName(){

        return getCreateUserLoginData().get("create_user_logon_name");
    }
    public String getCreateEDRPOU(){

        return  getCreateUserLoginData().get("create_user_edrpou");
    }
    public String getCreateEIC(){

        return getCreateUserLoginData().get("create_user_eic");
    }
    public String getCreatePhone(){

        return getCreateUserLoginData().get("create_user_phone");
    }
    public String getCreateXAppKey(){

        return getCreateUserLoginData().get("X-Application-Key");
    }
}

