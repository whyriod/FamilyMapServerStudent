package result;

import passoffmodels.Person;

public class GetAllPersonsResult {

//    {
//        "data": [  /* Array of Person objects */  ]
//        “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	    “message”: “Error: [Description of the error]”
//        “success”:false		// Boolean identifier
//    }


    private Person[] data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public GetAllPersonsResult(Person [] data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /***
     * Error Constructor
     */
    public GetAllPersonsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
