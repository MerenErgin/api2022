package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get16 extends DummyRestApiBaseUrl {
            /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
            */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User send Get Request to Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
     */

    @Test
    public void get01(){
        //1.Step: Set the url
        spec.pathParam("first","employees");

        //2.Set the expected data

        //3.Step: Send the get request and get the response
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Step: Do Assertion

        response.then().assertThat().statusCode(200).//i) Status code is 200
                body("data.id",hasSize(24),//ii) There are 24 employees
                 "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));//iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        //iv) The greatest age is 66
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        System.out.println(ageList.get(ageList.size() - 1));
        assertEquals(66, (int)ageList.get(ageList.size() - 1));

        //v) The name of the lowest age is "Tatyana Fitzpatrick"
        String groovyString = "data.findAll{it.employee_age == "+ageList.get(0)+"}.employee_name";
        String minAgeEmployeeName = json.getString(groovyString);
        System.out.println(minAgeEmployeeName);
        assertEquals("[Tatyana Fitzpatrick]",minAgeEmployeeName);

        //vi) Total salary of all employees is 6,644,770
        List<Integer> salaryList = json.getList("data.findAll{it.id}.employee_salary");
        System.out.println(salaryList);

        //1.yol: foreach
        int sumfor = 0;
        for (int w:salaryList) {
            sumfor+=w;
        }
        System.out.println(sumfor);
        assertEquals(6644770,sumfor);

        //2.yol: Lambda
        int sumLambda = salaryList.stream().reduce(0,(t,u)->t+u);
        //int sumLambda01 = salaryList.stream().reduce(0,Math::addExact);
        assertEquals(6644770,sumLambda);

    }



}
