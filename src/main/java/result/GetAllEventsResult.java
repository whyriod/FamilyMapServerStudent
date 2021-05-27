package result;

public class GetAllEventsResult {

//    {
//        "data": [  /* Array of Event objects */  ]
//        “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	        “message”: “Error: [Description of the error]”
//          “success”:false		// Boolean identifier
//    }

    private String data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public GetAllEventsResult(String data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /***
     * Error Constructor
     */
    public GetAllEventsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
