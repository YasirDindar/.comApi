package testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class TestDataHerokuapp {

    public JSONObject bookingDatesOlusturJson(){

        JSONObject bookingdatesJson = new JSONObject();
        bookingdatesJson.put("checkin","2021-06-01");
        bookingdatesJson.put("checkout","2021-06-10");


        return bookingdatesJson;
    }

    public JSONObject bookingOlusturJson(){

        JSONObject bookingJson = new JSONObject();
        bookingJson.put("firstname","Ali");
        bookingJson.put("lastname","Bak");
        bookingJson.put("totalprice",500);
        bookingJson.put("depositpaid",false);
        bookingJson.put("additionalneeds","wi-fi");
        bookingJson.put("bookingdates",bookingDatesOlusturJson());


        return bookingJson;
    }

    public JSONObject expBodyOlusturJson(){

        JSONObject expBodyJson = new JSONObject();
        expBodyJson.put("bookingid",24);
        expBodyJson.put("booking",bookingOlusturJson());

        return expBodyJson;
    }

 public HashMap bookingDatesOlusturMap(){

     HashMap<String, Object>bookingdates = new HashMap<>();
     bookingdates.put("checkin","2021-06-01");
     bookingdates.put("checkout","2021-06-10");


     return bookingdates;
 }
    public HashMap bookingOlusturMap(){

        HashMap<String, Object>booking = new HashMap<>();
        booking.put("firstname","Ali");
        booking.put("lastname","Bak");
        booking.put("totalprice",500.0);
        booking.put("depositpaid",false);
        booking.put("additionalneeds","wi-fi");
        booking.put("bookingdates",bookingDatesOlusturMap());


        return booking;
    }

    public HashMap expBodyOlusturMap(){

        HashMap<String, Object>expBodyMap = new HashMap<>();
        expBodyMap.put("bookingid",24.0);
        expBodyMap.put("booking",bookingOlusturMap());

        return expBodyMap;
    }
}
