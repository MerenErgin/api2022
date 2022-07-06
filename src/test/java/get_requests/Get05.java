package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuappBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","booking").
                queryParams("firstame","Aaron",
                        "lastname", "Chen");
        //https://restful-booker.herokuapp.com/booking?firstame=Aaron&lastname=Chen

        //2. Step: Set the expected data

        //3. Step: Send the rwuest and get the response

        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.then().assertThat().statusCode(200);

        //1.yol
        assertTrue(response.asString().contains("bookingid"));

        //2.yol
        assertFalse(response.asString().isEmpty());


    }
}
