package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C25_Put_DeSerialization extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request yolladigimizda
    donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz
     */

    /*
    Request Body

{
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
}
     */
    /*
     Expected Data

{
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
}
     */

    @Test
    public void put01(){

        //1-url ve body hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        HashMap<String,Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        //2-exp data hazirla

        HashMap<String,Object> expData = testDataJsonPlaceHolder.requestBodyOlusturMap();

        //3- Response i hazirla

        Response response = given()
                                    .spec(specJsonPlace)
                                    .contentType(ContentType.JSON)
                            .when()
                                    .body(reqBody)
                                    .put("/{pp1}/{pp2}");

        //4- Assertion

        HashMap<String,Object>respMap = response.as(HashMap.class);

        Assert.assertEquals(expData.get("title"),respMap.get("title"));
        Assert.assertEquals(expData.get("body"),respMap.get("body"));
        Assert.assertEquals(expData.get("userId"),respMap.get("userId"));
        Assert.assertEquals(expData.get("id"),respMap.get("id"));

    }
}
