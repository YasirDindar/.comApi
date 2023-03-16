package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_Get_ApiSorgulama {
    /*
        https://restful-booker.herokuapp.com/booking/256884 url’ine
        bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
         */


    //1- Gonderecegimiz Request icin gerekli olan Url'i ve ihtiyacimiz varsa body'i hazirliyoruz.

    //2- Eger soruda bize verilmisse expected datayi hazirliyoruz

    //3- Bize donen Response'i Actual Data olarak kaydedecegiz.

    //4- Expecteed data ile actual datanin karsilastirilmasi - Assertion

    @Test
    public void get01(){
        //1- Gonderecegimiz Request icin gerekli olan Url'i ve ihtiyacimiz varsa body'i hazirliyoruz.

        String url ="https://restful-booker.herokuapp.com/booking/58";

        //2- Eger soruda bize verilmisse expected datayi hazirliyoruz

        //3- Bize donen Response'i Actual Data olarak kaydedecegiz.

        Response response = given().when().get(url);

        response.prettyPrint();

        //status koduna ulasmak icin
        System.out.println("status kod :" +response.getStatusCode());
        System.out.println("content type : " +response.contentType());
        System.out.println("get header : " +response.getHeader("Server"));
        System.out.println("status line : " +response.statusLine());
        System.out.println("response suresi  : " +response.getTime());

        //4- Expecteed data ile actual datanin karsilastirilmasi - Assertion



    }
}
