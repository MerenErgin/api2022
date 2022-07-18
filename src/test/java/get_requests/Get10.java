package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2526
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
        {
            "meta": null,
            "data": {
                "id": 2526,
                "name": "Mrs. Goswamee Gandhi",
                "email": "mrs_goswamee_gandhi@little.org",
                "gender": "female",
                "status": "inactive"
            }
        }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","users","second",2526);

        //2. Step: Set the expected data
                    //id 'yi sistem belirledigi icin secmemize gerek yok
        GoRestTestData dataKey= new GoRestTestData();
        Map<String, String> dataKeyMap = dataKey.dataHeyMap("Mrs. Goswamee Gandhi","mrs_goswamee_gandhi@little.org","female","inactive");

        Map<String, Object> expectedData = dataKey.expectedDataMap(null,dataKeyMap);


        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        //4. Step: Do Assertion
        assertEquals(expectedData.get("meta"), actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));


    }
}
