package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyRestPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Post05 extends DummyRestApiBaseUrl {
         /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
       Test Case: Type by using Gherkin Language
       Assert:
                    {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                     }
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/create
           {
           "employee_name": "Tom Hanks",
           "employee_salary": 111111,
           "employee_age": 23,
           "profile_image": "Perfect image",
           "id": 4891
            }
    When
        User sends the post request
    Then
        Status code is 200
    And
             {
               "status": "success",
               "data": {
                   "employee_name": "Tom Hanks",
                   "employee_salary": 111111,
                   "employee_age": 23,
                   "profile_image": "Perfect image",
                   "id": 4891
               },
               "message": "Successfully! Record has been added."
              }

     */

    @Test
    public void post01(){
        spec.pathParam("first","create");

        DummyDataPojo dummyDataPojo = new DummyDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyRestPojo expectedPojo = new DummyRestPojo("success",dummyDataPojo,"Successfully! Record has been added.");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyDataPojo).when().post("/{first}");
        response.prettyPrint();

        DummyRestPojo actualPojo = JsonUtil.convertJsonToJavaObject(response.asString(),DummyRestPojo.class);
        System.out.println(actualPojo);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedPojo.getMessage(),actualPojo.getMessage());
        assertEquals(expectedPojo.getStatus(),actualPojo.getStatus());
        assertEquals(dummyDataPojo.getEmployee_name(),actualPojo.getData().getEmployee_name());
        assertEquals(dummyDataPojo.getEmployee_salary(),actualPojo.getData().getEmployee_salary());
        assertEquals(dummyDataPojo.getEmployee_age(),actualPojo.getData().getEmployee_age());
        assertEquals(dummyDataPojo.getProfile_image(),actualPojo.getData().getProfile_image());


    }


}
