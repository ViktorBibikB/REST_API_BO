package Helpers;

public class GettersSetters {
    private static String createUserRequestId;
    private static String authJWT;
    private static String decodedJWT;
    private static String decodedSessionId;
    private static String decodedLogonName;
    private static String contractID;
    private static String contractNumber;
    private static String accountNumber;
    private static String accountEDRPOU;

    // AuthJWT
    public static String getCreateUserRequestId() {

        return createUserRequestId;
    }
    public static void setCreateUserRequestId(String authCreateUserRequestId) {

        createUserRequestId = authCreateUserRequestId;
        createUserRequestId = createUserRequestId.replace("\"", "");

    }
    // AuthJWT
    public static String getAuthJWT() {

        return authJWT;
    }
    public static void setAuthJWT(String authenticationJWT) {

        authJWT = authenticationJWT;
    }
    // DecodedJWT
    public static String getDecodedJWT() {

        return decodedJWT;
    }
    public static void setDecodedJWT(String authDecodedJWT) {

        decodedJWT = authDecodedJWT;
    }
    // DecodedSessionId
    public static String getDecodedSessionId() {

        return decodedSessionId;
    }
    public static void setDecodedSessionId(String authDecodedLogonName) {

        decodedSessionId = authDecodedLogonName;
    }
    // DecodedLogonName
    public static String getDecodedLogonName() {

        return decodedLogonName;
    }
    public static void setDecodedLogonName(String authDecodedLogonName) {

        decodedLogonName = authDecodedLogonName;
    }
    // ContractID
    public static String getContractID() {

        return contractID;
    }
    public static void setContractID(String authContractID) {

        contractID = authContractID;
    }
    // ContractNumber
    public static String getContractNumber() {

        return contractNumber;
    }
    public static void setContractNumber(String authContractNumber) {

        contractNumber = authContractNumber;
    }
    // AccountNumber
    public static String getAccountNumber() {

        return accountNumber;
    }
    public static void setAccountNumber(String authAccountNumber) {

        accountNumber = authAccountNumber;
    }
    //AccountEDRPOU
    public static String getAccountEDRPOU() {

        return accountEDRPOU;
    }
    public static void setAccountEDRPOU(String authAccountEDRPOU) {

        accountEDRPOU = authAccountEDRPOU;
    }





}
