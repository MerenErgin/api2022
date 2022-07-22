package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
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

        //Hard Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

        //Soft Assertion
        //1.Adim: SoftAssert objesi olustur
        SoftAssert softAssert = new SoftAssert();

        //2.Adim: Assertion yap
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"Firstname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"Totalprice uyusmadi");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),"Additionalneeds uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Checkin uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"Checkout uyusmadi");

        //3.Adim: assertAll() methodu calistir
        softAssert.assertAll();

        // Hard Assertion ve Soft assertion arasindaki fark;
        // Soft Asserion da hata oldugu zaman devam eder ve kac tanesinde hata varsa gosterir
        // Hard assertionda ilk hatada kod calismayi durdurur ve sadece o hatayi goruruz

    }

}
