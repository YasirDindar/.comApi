package test;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;

public class C20_Get_TestDataKullanimi extends DummyBaseUrl {

    /*
     /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi oldugunu test edin.
    Expected Body
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */
    @Test
    public void get01(){
        specDummy.pathParams("pp1","employee","pp2",3);

        TestDataDummy testDataDummy = new TestDataDummy();

        JSONObject expData = testDataDummy.expectedBodyOlusturJSON();

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");

        JsonPath resJPath= response.jsonPath();

        Assert.assertEquals(testDataDummy.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(testDataDummy.contentType, response.getContentType());

        Assert.assertEquals(expData.get("status"),resJPath.get("status"));
        Assert.assertEquals(expData.get("message"),resJPath.get("message"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),resJPath.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),resJPath.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),resJPath.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),resJPath.get("data.employee_age"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),resJPath.get("data.profile_image"));
    }
}
