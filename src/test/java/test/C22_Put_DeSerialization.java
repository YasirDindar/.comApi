package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data :
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

        specJsonPlace.pathParams("pp1","posts","pp2","70");

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        HashMap<String,Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        //2-exp data hazirla

        HashMap<String,Object> expDataMap = testDataJsonPlaceHolder.requestBodyOlusturMap();

        // 3- response i kaydet

        Response response = given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .put("/{pp1}/{pp2}");

         HashMap<String,Object>respMap = response.as(HashMap.class);

        // Not : Bizim hazirlamis oldugumuz Expected Data Map formatinda.
        // Bize response'dan donen Response Body ise Json formatinda
        // Ikisini Assert methodlari icerisinde kiyaslayabilmemiz icin oncelikle
        // response'i map formatina parse etmemiz gerekiyor.

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(expDataMap.get("title"),respMap.get("title"));
        Assert.assertEquals(expDataMap.get("body"),respMap.get("body"));
        Assert.assertEquals(expDataMap.get("userId"),respMap.get("userId"));
        Assert.assertEquals(expDataMap.get("id"),respMap.get("id"));


    }
}
