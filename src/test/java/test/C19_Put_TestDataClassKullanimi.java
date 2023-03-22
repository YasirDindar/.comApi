package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in
    status kodunun 200, content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
  */
    @Test
    public void put01(){
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        JSONObject reqBody =testDataJsonPlaceHolder.requestBodyOlusturJSON();

        JSONObject expData =testDataJsonPlaceHolder.requestBodyOlusturJSON();

        Response response = given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                        .put("/{pp1}/{pp2}");

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(testDataJsonPlaceHolder.contentType,response.getContentType());
        Assert.assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response.getHeader("Connection"));

        JsonPath resJPath = response.jsonPath();

        assertEquals(expData.get("userId"),resJPath.get("userId"));
        assertEquals(expData.get("id"),resJPath.get("id"));
        assertEquals(expData.get("title"),resJPath.get("title"));
        assertEquals(expData.get("body"),resJPath.get("body"));
    }
}
