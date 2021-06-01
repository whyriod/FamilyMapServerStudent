package cereal;

import java.io.*;

/***
 * Read String from example notes
 */
public class Cereal {

    /***
    * The readString method shows how to read a String from an InputStream.
    */
    public String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
