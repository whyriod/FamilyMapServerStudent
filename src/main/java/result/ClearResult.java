package result;

/***
 * Creates a ClearResult object to return a message based on db operation.
 */
public class ClearResult {

//    Success Response Body:
//    {
//	     “message”: “Clear succeeded.”
//       “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	     “message”: “Error: [Description of the error]”
//       “success”:false		// Boolean identifier
//    }

    private String message;
    private boolean success;

    /***
     * Constructor - Success and Failure
     * @param message - Success: "Clear succeeded."
     *                  Error:   "Error: ..." if failure
     * @param success - Boolean of success
     */
    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
