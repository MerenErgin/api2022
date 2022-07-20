package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {
        /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){
        //1.Step: Set the url
        spec.pathParams("first","todos","second",198);

        //2.Step: Set the expected data
        Map<String, Object> expectedDataMap = new HashMap<>();

        //3.Step: Send the Delete Request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do Assertion
        //1.yol
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(actualDataMap,expectedDataMap);

        //2.yol
        assertTrue(actualDataMap.size()==0);
        assertTrue(actualDataMap.isEmpty());//Tavsiye edilen

        //Delete Request yapmadan once "Post Request" yapip o datayi silmeliyiz

    }

}
