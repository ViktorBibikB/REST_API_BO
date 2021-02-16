package UserLogOn;

import Helpers.DataBaseConnection;
import TestData.TestDataCreateUser;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.sql.SQLException;


import static Helpers.DataBaseConnection.*;
import static Helpers.GettersSetters.*;
import static Helpers.Helpers.receiveDecodedJWTAsString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.StringContains.containsString;

public class CreateUser {
    private TestDataCreateUser createUserLoginData = new TestDataCreateUser();
    private ValidatableResponse response = null;

    private String xAppKey = createUserLoginData.getCreateXAppKey();
    private String logon_name = createUserLoginData.getCreateLoginName();
    private String edrpou = createUserLoginData.getCreateEDRPOU();
    private String eic_x = createUserLoginData.getCreateEIC();
    private String phone = createUserLoginData.getCreatePhone();

    protected String create_user_request_id;
    protected String authorizationJWT;
    protected String selectFromDatabase = "SELECT * FROM web02_billing.gas_user_legal WHERE edrpou = '02010936'";
    protected String deleteFromDataBase = "DELETE FROM web02_billing.gas_user_legal WHERE edrpou = '02010936'";





    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "http://sho-be03-t.ent.ukrgas.com.ua/billing/";

    }

    @AfterClass
    public void closeDBConnection() throws SQLException {
        closeBDConnection();
        deleteFromDB(deleteFromDataBase);
    }

    @Test(priority = 0)
    public void testCreateUserRequest(){
        response = given()
                .header("X-Application-Key", xAppKey)
                .param("logon_name", logon_name)
                .param("edrpou", edrpou)
                .param("eic", eic_x)
                .param("phone", phone)
                .when()
                .post("api/v2/legal/users")
                .then()
                .assertThat().statusCode(200);
        setCreateUserRequestId(create_user_request_id = response.extract().asString());
        Assert.assertNotNull(getCreateUserRequestId());
    }

    @Test(priority = 1)
    public void testConfirmCreateUserRequest() throws SQLException {
         response = given()
                .header("X-Application-Key", xAppKey)
                .when()
                .post("api/v2/legal/users/confirm/" + getCreateUserRequestId())
                .then()
                .assertThat().statusCode(201)
                .and()
                .assertThat().body(containsString("\"jwt\""));
        JSONObject jsonResponse = new JSONObject(response.extract().asString());
        JSONObject data = jsonResponse.getJSONObject("data");;
        setAuthJWT(authorizationJWT = data.getString("jwt"));
        jsonResponse = new JSONObject(receiveDecodedJWTAsString(getAuthJWT()));
        setDecodedSessionId(jsonResponse.getString("session_id"));
        setDecodedLogonName(jsonResponse.getString("logon_name"));
        Assert.assertEquals(getDecodedLogonName(), "02010936@mailinator.com");
        Assert.assertEquals(selectFromDB(selectFromDatabase, "logon_name"), "02010936@mailinator.com");
        System.out.println("User name (after method) is: " + selectFromDB(selectFromDatabase, "logon_name"));
    }

}
