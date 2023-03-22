package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;

public class C21_Post_TestDataKullanimi extends HerokuappBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id haric asagidaki gibi oldugunu test edin.
     */

    /*
    Request body
       {
           "firstname" : "Ahmet",
           "lastname" : “Bulut",
           "totalprice" : 500,
           "depositpaid" : false,
           "bookingdates" : {
               "checkin" : "2021-06-01",
               "checkout" : "2021-06-10"
           },
           "additionalneeds" : "wi-fi"
       }
     */

    /*
    Response Body
{
        "bookingid":24,
        "booking":{
            "firstname":"Ahmet",
            "lastname":"Bulut",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                "checkin":"2021-06-01",
                "checkout":"2021-06-10"
                },
            "additionalneeds":"wi-fi"
            }
}
     */

    @Test
    public void post01(){

        //1-url ve body hazirla
        specHerokuapp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();

        JSONObject reqBody = testDataHerokuapp.bookingOlusturJson();

        //2-exp data hazirla

        JSONObject expData = testDataHerokuapp.expBodyOlusturJson();

        //3-response kaydet

        Response response = given().spec(specHerokuapp)
                .contentType(ContentType.JSON).when().body(reqBody.toString()).post("/{pp1}");

        //4-Assertion

        JsonPath reqJsonBody = response.jsonPath();

        Assert.assertEquals(expData.getJSONObject("booking").get("firstname"),reqJsonBody.get("booking.firstname"));
        Assert.assertEquals(expData.getJSONObject("booking").get("lastname"),reqJsonBody.get("booking.lastname"));
        Assert.assertEquals(expData.getJSONObject("booking").get("totalprice"),reqJsonBody.get("booking.totalprice"));
        Assert.assertEquals(expData.getJSONObject("booking").get("depositpaid"),reqJsonBody.get("booking.depositpaid"));
        Assert.assertEquals(expData.getJSONObject("booking").get("additionalneeds"),reqJsonBody.get("booking.additionalneeds"));
        Assert.assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),reqJsonBody.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),reqJsonBody.get("booking.bookingdates.checkout"));

    }
}
