package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyRestPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get17 extends DummyRestApiBaseUrl {
         /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User send Get Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01(){
        spec.pathParams("first","employee","second",1);

        DummyDataPojo dataPojo =new DummyDataPojo("Tiger Nixon", 320800, 61, "");

        DummyRestPojo expectedBodyPojo = new DummyRestPojo("success", dataPojo, "Successfully! Record has been fetched.");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        DummyRestPojo actualBodyPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyRestPojo.class);
        System.out.println(actualBodyPojo);

        DummyDataPojo dummydataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyDataPojo.class);

        assertEquals(expectedBodyPojo.getStatus(),actualBodyPojo.getStatus());
        assertEquals(expectedBodyPojo.getMessage(),actualBodyPojo.getMessage());

        assertEquals(expectedBodyPojo.getData().getEmployee_name(),actualBodyPojo.getData().getEmployee_name());
        assertEquals(expectedBodyPojo.getData().getEmployee_salary(),actualBodyPojo.getData().getEmployee_salary());
        assertEquals(expectedBodyPojo.getData().getEmployee_age(),actualBodyPojo.getData().getEmployee_age());
        assertEquals(expectedBodyPojo.getData().getProfile_image(),actualBodyPojo.getData().getProfile_image());


    }



}
