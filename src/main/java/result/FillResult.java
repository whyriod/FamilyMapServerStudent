package result;

public class FillResult {


//    Success Response Body:
//    {
//	    “message”: “Successfully added X persons and Y events to the database.”
//      “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	    “message”: “Error: [Description of the error]”
//      “success”:false		// Boolean identifier
//    }

    private String message;
    private boolean success;

    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
