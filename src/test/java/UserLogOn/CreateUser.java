package UserLogOn;

import TestData.TestDataCreateUser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Helpers.GettersSetters.getCreateUserRequestId;
import static Helpers.GettersSetters.setCreateUserRequestId;
import static io.restassured.RestAssured.given;

public class CreateUser {
    private TestDataCreateUser createUserLoginData = new TestDataCreateUser();
    private Date date = new Date();;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private File uploadedFile_meteringPhoto = new File("D://RemoteWork//APITestData//image.jpg");
    private File uploadedFile_connectingPhoto = new File("D://RemoteWork//APITestData//image_1.jpg");

    private String xAppKey = createUserLoginData.getCreateXAppKey();
    private String logon_name = createUserLoginData.getCreateLoginName();
    private String edrpou = createUserLoginData.getCreateEDRPOU();
    private String eic_x = createUserLoginData.getCreateEIC();
    private String phone = createUserLoginData.getCreatePhone();

    private String create_user_request_id = null;



    @BeforeClass
    public void setBaseUrl() {

        RestAssured.baseURI = "http://sho-be03-t.ent.ukrgas.com.ua/billing/";
        //uploadedFile.mkdirs();

    }

    @Test(priority = 0)
    public void testCreateUserRequest(){
        ValidatableResponse response;
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
        System.out.println("Create_user_request_id is: " + getCreateUserRequestId());
        Assert.assertNotNull(getCreateUserRequestId());
    }

}
