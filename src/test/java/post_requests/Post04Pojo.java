package post_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Post04Pojo extends HerokuappBaseUrl{
         /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void postPojo01(){
        //1.Step: Set the url
        spec.pathParam("first","booking");

        //2.Step: Set the expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo booking= new BookingPojo("Ali","Can",999,true,bookingdates,"Breakfast with white tea, Dragon juice");
        //bookingid Sistem atadigi icin degisiyor o yuzden bookingid'yi almaya gerek yok

        //3.Step: Send Post Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(booking).when().post("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion
        HerokuAppPojo actualPojo = response.as(HerokuAppPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(booking.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(booking.getLastname(),actualPojo.getBooking().getLastname());
        assertEquals(booking.getTotalprice(),actualPojo.getBooking().getTotalprice());
        assertEquals(booking.getDepositpaid(),actualPojo.getBooking().getDepositpaid());

        assertEquals(bookingdates.getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());

        assertEquals(booking.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());

    }


}
