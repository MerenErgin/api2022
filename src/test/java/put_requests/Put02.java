package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyRestPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put02 extends DummyRestApiBaseUrl {
         /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body:
                    {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/update/21
           {
              "employee_name": "Ali Can",
              "employee_salary": 111111,
              "employee_age": 23,
              "profile_image": "Perfect image"
           }
    When
        User sends put the request
    Then
        Status code is 200
    And
        Response body should be like the following
          {
              "status": "success",
              "data": {
                  "employee_name": "Ali Can",
                  "employee_salary": 111111,
                  "employee_age": 23,
                  "profile_image": "Perfect image"
              },
              "message": "Successfully! Record has been updated."
          }
     */

    @Test
    public void put01(){
        spec.pathParams("first","update", "second",21);

        DummyDataPojo dummyApiDataPojo = new DummyDataPojo("Ali Can", 111111, 23, "Perfect image");
        DummyRestPojo expectedPojo = new DummyRestPojo("success", dummyApiDataPojo,"Successfully! Record has been updated.");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().put("/{first}/{second}");
        response.prettyPrint();

        DummyRestPojo actualPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyRestPojo.class);

        assertEquals(expectedPojo.getStatus(),actualPojo.getStatus());
        assertEquals(expectedPojo.getMessage(),actualPojo.getMessage());
        assertEquals(expectedPojo.getData().getEmployee_name(),actualPojo.getData().getEmployee_name());
        assertEquals(expectedPojo.getData().getEmployee_salary(),actualPojo.getData().getEmployee_salary());
        assertEquals(expectedPojo.getData().getEmployee_age(),actualPojo.getData().getEmployee_age());
        assertEquals(expectedPojo.getData().getProfile_image(),actualPojo.getData().getProfile_image());

    }

}
