package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get11 extends GoRestBaseUrl {
        /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Devasree Mehra DC", "Ujjawal Malik", "Ananda Agarwal" are among the users
        And
            The female users are more than or equals to male users
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","users");

        //2. Step: Set the expected Data

        //3. Step: Send the Request get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        response.then().assertThat()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name",hasItems("Devasree Mehra DC", "Ujjawal Malik", "Ananda Agarwal"));

        //Kadin ve erkek sayilarini karsilastirma
            //1.Yol: Tum cinsiyetleri cekip kadin sayisi ile karsilastiralim
        JsonPath json= response.jsonPath();
        List<String> genders = json.getList("data.gender");
        System.out.println(genders);

        int numOfFemales=0;
        for (String w: genders) {
            if (w.equalsIgnoreCase("female")){
                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);

        assertTrue(numOfFemales >= genders.size()-numOfFemales);

            //2.Yol: Tum kadin ve erkekleri ayri ayri Groovy ile cekelim
        List<String> femaleList = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size() >= maleList.size());

    }

}
