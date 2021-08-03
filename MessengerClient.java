import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Project 5 - MessengerClient Class
 * This class is for extra methods that are useful to make the Social Platform class look cleaner.
 *
 * @author Mahad Faruqi, Yagmur Onder, Kasidit Muenprasitivej, Haohan Wu
 * @version Aug 3, 2021
 */
public class MessengerClient {

    /**
     * This main method just makes a socket and calls the server class to make new threads for multiple users.
     * @param args the arguments to this program
     * @throws UnknownHostException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NotSerializableException
     */
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, NotSerializableException {
            Socket socket = new Socket("localhost", 4242);
    }
}
