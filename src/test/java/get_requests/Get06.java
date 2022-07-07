package get_requests;

public class Get06 {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
     */
}
