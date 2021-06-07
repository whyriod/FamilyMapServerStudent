package cereal;

import java.io.*;

/***
 * Reads an input stream of Json into a string.
 * From: Class notes
 */
public class Cereal {

    /***
     * Takes an inputStream and creates an InputStream read. Reads input Json
     * to a string.
     *
     * @param is - Input input stream.
     * @return - The stringified Json.
     * @throws IOException - Ignore.
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



    /***
     * Takes a string, and output string and writes the string to the output.
     * used for preparing json for reqBodies.
     *
     * @param str - The string to write
     * @param os - Output stream to write to.
     * @throws IOException - Ignore.
     */
    public static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
