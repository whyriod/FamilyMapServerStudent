package request;

public class GetAllEventsRequest {

    //Username is determined from the authtoken


    private String username;

    public GetAllEventsRequest(String username) {
        this.username = username;
    }
}
