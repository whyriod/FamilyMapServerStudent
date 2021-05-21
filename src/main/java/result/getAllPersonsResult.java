package result;

import passoffmodels.Person;

public class getAllPersonsResult {
    private Person[] data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public getAllPersonsResult(Person [] data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /***
     * Error Constructor
     */
    public getAllPersonsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
