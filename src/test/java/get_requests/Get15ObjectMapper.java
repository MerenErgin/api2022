package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get15ObjectMapper extends HerokuappBaseUrl {
        /*
        Given
	            https://restful-booker.herokuapp.com/booking/4501
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                    "firstname": "Dagmar",
                    "lastname": "Afkir",
                    "totalprice": 448,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2022-07-22",
                        "checkout": "2022-08-01"
                    },
                    "additionalneeds": "Newspaper"
                }
     */

    @Test
    public void get01(){
        //1.Step: Set the url
        spec.pathParams("first", "booking", "second", 4501);

        //2.Step: Set the expected data
        String expectedData= "                {\n" +
                "                    \"firstname\": \"Dagmar\",\n" +
                "                    \"lastname\": \"Afkir\",\n" +
                "                    \"totalprice\": 448,\n" +
                "                    \"depositpaid\": false,\n" +
                "                    \"bookingdates\": {\n" +
                "                        \"checkin\": \"2022-07-22\",\n" +
                "                        \"checkout\": \"2022-08-01\"\n" +
                "                    },\n" +
                "                    \"additionalneeds\": \"Newspaper\"\n" +
                "                }";

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3.Step: Send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertion
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());


    }

}
