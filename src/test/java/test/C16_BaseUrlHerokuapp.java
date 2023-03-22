package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuappBaseUrl {

    @Test
    public void get01(){
        //1-  https://restful-booker.herokuapp.com/booking endpointine bir GET request
        // gonderdigimizde donen response’un status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin

        specHerokuapp.pathParam("pp1","booking");
        Response response = given().spec(specHerokuapp).when().get("/{pp1}");
        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasItem(12));

    }
    @Test
    public void post01(){
  /*
    2- https://restful-booker.herokuapp.com/booking endpointine asagidaki body’ye sahip
    bir POST request gonderdigimizde donen response’un status code’unun 200 oldugunu ve “firstname” degerinin “Ahmet” oldugunu test edin
     */
          /*
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
        specHerokuapp.pathParam("pp1","booking");
        JSONObject innerBody = new JSONObject();
        innerBody.put("checkin" , "2021-06-01");
        innerBody.put("checkout" , "2021-06-10");

        JSONObject body = new JSONObject();
        body.put("firstname" , "Ahmet");
        body.put("lastname" , "Bulut");
        body.put("totalprice" , 500);
        body.put("depositpaid" , false);
        body.put("bookingdates" , innerBody);
        body.put("additionalneeds" , "wi-fi");

        Response response = given().contentType(ContentType.JSON).spec(specHerokuapp)
                .when().body(body.toString()).post("/{pp1}");

        response.then().assertThat().statusCode(200)
                .body("booking.firstname",Matchers.equalTo("Ahmet"));
    }
}
