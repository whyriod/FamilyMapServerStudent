package result;

import model.Event;

/***
 * Creates a GetAllEventsResult object to return an array of event objects
 * based on db operation.
 */
public class GetAllEventsResult {

    private Event[] data;
    private String message;
    private boolean success;



    /***
     * Success Constructor
     * @param data - Array of Event Objects
     * @param success - Boolean of success
     */
    public GetAllEventsResult(Event[] data, boolean success) {
        this.data = data;
        this.success = success;
    }



    /***
     * Error Constructor
     * @param message - “Error: [Description of the error]”
     * @param success - Boolean of success
     */
    public GetAllEventsResult(String message,boolean success) {
        this.message = message;
        this.success = success;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
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
