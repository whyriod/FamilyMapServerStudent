package result;

public class ClearResult {

//    Success Response Body:
//    {
//	      “message”: “Clear succeeded.”
//        “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	      “message”: “Error: [Description of the error]”
//        “success”:false		// Boolean identifier
//    }

    private String message;
    private boolean success;

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
