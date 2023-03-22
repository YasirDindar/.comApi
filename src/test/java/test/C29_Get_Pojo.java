package test;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyData;
import pojos.PojoDummyExpectedBody;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;

public class C29_Get_Pojo extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    Response Body
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully!Record has been fetched."
    }
     */
    @Test
    public void get01() {

        //1-url hazirla

        specDummy.pathParams("pp1", "employee", "pp2",3);

        //2-exp data hazirla

        PojoDummyData data = new PojoDummyData(3,"Ashton Cox",86000,66,"");

        PojoDummyExpectedBody expData = new PojoDummyExpectedBody("success",data,"Successfully! Record has been fetched.");

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");

        PojoDummyExpectedBody respPojo = response.as(PojoDummyExpectedBody.class);

        Assert.assertEquals(expData.getStatus(),respPojo.getStatus());
        Assert.assertEquals(expData.getMessage(),respPojo.getMessage());
        Assert.assertEquals(expData.getData().getEmployee_age(),respPojo.getData().getEmployee_age());
        Assert.assertEquals(expData.getData().getEmployee_salary(),respPojo.getData().getEmployee_salary());
        Assert.assertEquals(expData.getData().getProfile_image(),respPojo.getData().getProfile_image());
        Assert.assertEquals(expData.getData().getId(),respPojo.getData().getId());
        Assert.assertEquals(expData.getData().getEmployee_name(),respPojo.getData().getEmployee_name());

    }
}
