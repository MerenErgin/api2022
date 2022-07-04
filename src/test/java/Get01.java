import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
        - Postman manual API testi icin kullanilir
        - API otomasyon testi icin Resst-Assured Library kullaniyoruz
        - Otomasyon kodlarinin yazimi icin su adimlari izliyoruz:
            1- Gereksinimleri anlama

            2- Test case'i yazma
                - Test case yazimi icin 'Gherkin Language' kullaniyoruz
                    - 'Gherkin' bazi keywordlere sahip, bunlar:
                        -Given: On kosullar
                        -When: Aksiyonlar --> Get, Put, ...
                        -Then: Donutler --> Assert ...
                        -And: Coklu islemeler icin kullanilirlar.

            3- Test kodlarinin yazimi
                - Set the URL
                - Set the expected data(POST-PUT-PATCH)
                - Type code to send request
                - Do Assertion
     */

    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void get01(){
        //- Set the URL
        String url= "https://restful-booker.herokuapp.com/booking/151";

        //- Set the expected data(POST-PUT-PATCH)
        //(Bu gorevde sadece get kullandigimiz icin bu adimi atliyoruz)

        //- Type code to send request
        Response response = given().when().get(url);
        //response.prettyPrint();

        //- Do Assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //'status Code' nasil yazdirilir:
        System.out.println("Status Code: "+ response.statusCode());

        //'Content Type' nasil yazdirilir:
        System.out.println("Content Type "+ response.contentType());

        //'Status Line' nasil yazdirilir:
        System.out.println("Status Line "+ response.statusLine());


        //'Header' nasil yazdirilir:
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Host"));
        System.out.println(response.header("User-Agent"));

        System.out.println("---------------------");

        System.out.println("Headers:\n"+response.headers());

        System.out.println("---------------------");

        //'Time' nasil yazdirilir:
        System.out.println(response.getTime());

    }

}
