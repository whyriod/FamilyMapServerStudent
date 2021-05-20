package result;

public class getAllPersonsResult {
    private String data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public getAllPersonsResult(String data, boolean success) {
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
