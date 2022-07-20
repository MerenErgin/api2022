package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponsePojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given

        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And https://gorest.co.in/public/v1/users/2245
            Response body should be like
            {
                "meta": null,
                "data": {
                    "id": 2245,
                    "name": "Gopi Bandopadhyay",
                    "email": "bandopadhyay_gopi@jerde.net",
                    "gender": "male",
                    "status": "inactive"
                }
            }
    */

    @Test
    public void getPojo01(){
        //1.Step: Set the url
        spec.pathParams("first","users","second",2245);

        //2.Step: Set the expected data
        GoRestDataPojo data = new GoRestDataPojo(2245,"Gopi Bandopadhyay","bandopadhyay_gopi@jerde.net", "male","inactive");
        GoRestResponsePojo responsePojo= new GoRestResponsePojo(null, data);

        //3.Step: Send the Get Request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertion
        GoRestResponsePojo actualPojo = response.as(GoRestResponsePojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(responsePojo.getMeta(),actualPojo.getMeta());
        assertEquals(data.getId(),actualPojo.getData().getId());
        assertEquals(data.getName(),actualPojo.getData().getName());
        assertEquals(data.getEmail(),actualPojo.getData().getEmail());
        assertEquals(data.getGender(),actualPojo.getData().getGender());
        assertEquals(data.getStatus(),actualPojo.getData().getStatus());

    }

}
