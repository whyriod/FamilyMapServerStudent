package result;

public class getAllEventsResult {
    private String data;
    private String message;
    private boolean success;

    /***
     * Success Constructor
     */
    public getAllEventsResult(String data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /***
     * Error Constructor
     */
    public getAllEventsResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
