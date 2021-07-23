package UserLogOn;

import TestData.TestDataLogonUser;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Helpers.GettersSetters.*;
import static Helpers.Helpers.receiveDecodedJWTAsString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class LogonUser {
    private TestDataLogonUser testDataLogonUser = new TestData.TestDataLogonUser();
    private ValidatableResponse response;

    private String xAppKey = testDataLogonUser.getXAppKey();
    private String logon_name = testDataLogonUser.getLoginName();
    private String device_id = testDataLogonUser.getDeviceId();
    private String access_mode = testDataLogonUser.getAccessMode();
    private String userEDRPOU = testDataLogonUser.getUserEDRPOU();
    protected String authorizationJWT = null;
    protected String accountNumber = null;
    protected String accountEDRPOU = null;
    protected String contractID = null;
    protected String contractNumber = null;


    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "http://sho-be03-t.ent.ukrgas.com.ua/billing/";
        //uploadedFile.mkdirs();

    }

    @Test(priority = 0)
    public void testUserAuthorisation(){
        response = given()
                .header("X-Application-Key", xAppKey)
                .param("device_id", device_id)
                .param("access_mode", access_mode)
                .when()
                .post("api/v2/legal/users/" + logon_name + "/sessions/by/appkey")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("\"data\""));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        setAuthJWT(authorizationJWT = jsonResponse.getString("data"));
        jsonResponse = new JSONObject(receiveDecodedJWTAsString(getAuthJWT()));
        setDecodedSessionId(jsonResponse.getString("session_id"));
        setDecodedLogonName(jsonResponse.getString("logon_name"));
        Assert.assertEquals(getDecodedLogonName(), "41800745@mailinator.com");
    }

    @Test(priority = 1)
    public void testGetLegalConsumerByEDRPOU(){
        response = given()
                .header("X-Application-Key", xAppKey)
                .queryParam("edrpou", userEDRPOU)
                .when()
                .get("api/v2/legal/consumer")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("\"id\""));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        JSONObject jsonId = jsonArray.getJSONObject(0);
        setAccountNumber(accountNumber = jsonId.getString("id"));
        setAccountEDRPOU(accountEDRPOU = jsonId.getString("edrpou"));
        Assert.assertEquals(getAccountEDRPOU(), "41800745");
    }

    @Test(priority = 2)
    public void testGetLegalUserContracts(){
        response = given()
                .header("X-Application-Key", xAppKey)
                .header("X-Session-Id", getDecodedSessionId())
                .when()
                .get("api/v2/legal/users/" + logon_name + "/contracts")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("\"id\""));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        JSONObject jsonId = jsonArray.getJSONObject(0);
        setContractID(contractID = jsonId.getString("id"));
        setContractNumber(contractNumber = jsonId.getString("number"));
        Assert.assertEquals(getContractNumber(), "42DB490-1036-19");
    }

    @Test(priority = 3)
    public void testGetLegalUserAccountingUnits(){
        response = given()
                .header("X-Application-Key", xAppKey)
                .header("X-Session-Id", getDecodedSessionId())
                .when()
                .get("api/v2/legal/users/" + logon_name + "/units")
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body(containsString("\"id\""));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        System.out.println(jsonArray);
        /*
        JSONObject jsonId = jsonArray.getJSONObject(2);
        setContractID(contractID = jsonId.getString("id"));
        setContractNumber(contractNumber = jsonId.getString("number"));
        Assert.assertEquals(getContractNumber(), "42DB490-1036-19");
        */
    }
}
