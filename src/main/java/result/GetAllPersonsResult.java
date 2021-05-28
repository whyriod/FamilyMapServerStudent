package result;

import passoffmodels.Person;

/***
 * Creates a GetAllPersonsResult object to return an array of person objects
 * based on db operation.
 */
public class GetAllPersonsResult {

//    Success Response Body:
//    {
//       "data": [  /* Array of Person objects */  ]
//       “success”:true		// Boolean identifier
//    }
//    Error Response Body:
//    {
//	     “message”: “Error: [Description of the error]”
//       “success”:false		// Boolean identifier
//    }

    private Person[] data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     * @param data - [Array of Person objects]
     * @param success - Boolean of success
     */
    public GetAllPersonsResult(Person [] data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /***
     * Error Constructor
     * @param message - “Error: [Description of the error]”
     * @param success - Boolean of success
     */
    public GetAllPersonsResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
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
