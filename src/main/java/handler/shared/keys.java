package handler.shared;

import java.net.URI;

public class keys {

    public String[] getKeys(String path){
        String[] segments = path.split("/");
        return segments;
    }
}
