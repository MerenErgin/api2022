package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

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

        //3.yol: Soft Assertion
        // Soft Assertion icin 3 adim izlenir

        //1) SoftAssert objesi olusturulur
        SoftAssert softAssert = new SoftAssert();

        //2) Obje araciligi ile assert yapilir
        softAssert.assertEquals(json.getString("firstname"),"Itzel","firstname uyusmadi");//mesaj assertion false olursa calisir
        softAssert.assertEquals(json.getString("lastname"),"Villanueva","lastname uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"),593,"totalprice uyusmadi");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"depositpaid uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-07-13","checkin uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-07-26","checkout uyusmadi");

        //3) assertAll() methodu kullanilir. Aksi takdirde kod her zaman pass olur
        softAssert.assertAll();

    }

}
