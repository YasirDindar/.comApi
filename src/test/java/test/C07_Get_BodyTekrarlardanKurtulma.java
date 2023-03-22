package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;


public class C07_Get_BodyTekrarlardanKurtulma {

    /*
    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
 	status code’unun 200,
	ve content type’inin application-json,
	ve response body’sindeki
	"firstname“in,"Susan",
	ve "lastname“in, "Jackson",
	ve "totalprice“in,612,
	ve "depositpaid“in,false,
	ve "additionalneeds“in,"Breakfast"
    oldugunu test edin
     */

    @Test
    public void get01(){

        String url ="https://restful-booker.herokuapp.com/booking/312";

        Response response = given().when().get(url);

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Jane"),
                        "lastname", equalTo("Doe"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "additionalneeds", containsString("Extra pillows please"));

    }
}
