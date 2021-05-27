package result;

public class LoadResult {

//    Success Response Body:
//    {
//	    “message”: “Successfully added X users, Y persons, and Z events to the database.”
//        “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//        “message”: “Error: [Description of the error]”
//        “success”:false		// Boolean identifier
//    }

    private String message;
    private boolean success;

    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
