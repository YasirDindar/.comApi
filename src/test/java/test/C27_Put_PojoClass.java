package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.POJOJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class C27_Put_PojoClass extends JsonPlaceHolderBaseUrl {

    /*
    C27_Put_PojoClass
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
    Expected Body
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

        POJOJsonPlaceHolder reqBody = new POJOJsonPlaceHolder("Ahmet","Merhaba",10,70);

        //2-exp data hazirla

        POJOJsonPlaceHolder expData = new POJOJsonPlaceHolder("Ahmet","Merhaba",10,70);

        //3-reponse i kaydet

        Response response = given()
                                    .spec(specJsonPlace)
                                    .contentType(ContentType.JSON)
                            .when()
                                    .body(reqBody)
                                    .put("/{pp1}/{pp2}");

        //4-assertion

        POJOJsonPlaceHolder respPOJO = response.as(POJOJsonPlaceHolder.class);

        Assert.assertEquals(expData.getBody(),respPOJO.getBody());
        Assert.assertEquals(expData.getId(),respPOJO.getId());
        Assert.assertEquals(expData.getUserId(),respPOJO.getUserId());
        Assert.assertEquals(expData.getTitle(),respPOJO.getTitle());
    }
}
