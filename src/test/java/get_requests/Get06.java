package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get06 extends HerokuappBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Itzel",
            "lastname": "Villanueva",
            "totalprice": 593,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-07-13",
                "checkout": "2022-07-26"
            },
            "additionalneeds": "Lunch"
        }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","booking","second",555);

        //2,Step: Set the expected data

        //3. Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //4. Step: Do Assertion
        //1.yol:
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Itzel")
                        ,"lastname",equalTo("Villanueva")
                        ,"totalprice",equalTo(593)
                        ,"depositpaid",equalTo(true)
                        ,"bookingdates.checkin",equalTo("2022-07-13")
                        ,"bookingdates.checkout",equalTo("2022-07-26")
                );

        //2.yol: JsonPath Class kullanilir
        JsonPath json = response.jsonPath();

        assertEquals("Itzel",json.getString("firstname"));
        assertEquals("Villanueva",json.getString("lastname"));
        assertEquals(593,json.getInt("totalprice"));
        assertEquals(true,json.getBoolean("depositpaid"));
        assertEquals("2022-07-13",json.getString("bookingdates.checkin"));
        assertEquals("2022-07-26",json.getString("bookingdates.checkout"));

    }

}
