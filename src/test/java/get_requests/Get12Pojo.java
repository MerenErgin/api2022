package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get12Pojo extends HerokuappBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/5226
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                        "firstname": "Samar",
                                        "lastname": "Blue",
                                        "totalprice": 111,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2018-01-01",
                                            "checkout": "2019-01-01"
                                        },
                                        "additionalneeds": "Breakfast"
                                    }
     */

    @Test
    public void getPojo01(){
        //1.Step: Set the url
        spec.pathParams("first","booking","second",5226);

        //2.Step: Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo booking = new BookingPojo("Samar","Blue",111,true,bookingDates,"Breakfast");

        //3.Step: Send the Get Request and get request
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4.Step: Do Assertion
        BookingPojo actualPojo = response.as(BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(booking.getFirstname(),actualPojo.getFirstname());
        assertEquals(booking.getLastname(),actualPojo.getLastname());
        assertEquals(booking.getTotalprice(),actualPojo.getTotalprice());
        assertEquals(booking.getDepositpaid(),actualPojo.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualPojo.getBookingdates().getCheckout());
        assertEquals(booking.getAdditionalneeds(),actualPojo.getAdditionalneeds());

    }

}
