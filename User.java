import java.io.*;
import java.util.*;

/**
 * Project 5 - User Class
 * User is created with fields username, password,
 * 'currently messaging' inboxes, and their own file 'messagingNames'
 *
 * @author Mahad Faruqi, Yagmur Onder, Kasidit Muenprasitivej, Haohan Wu
 * @version Aug 3, 2021
 */
public class User implements Serializable {

    //Fields Declaration
    /**
     * This is the username of the User
     */
    private String username;

    /**
     * This is the file usernae for the user.
     */
    private final String fileUsername;

    /**
     * This is the password of the User
     */
    private String password;

    /**
     * This is the file password for the user
     */
    private final String filePassword;

    /**
     * This is the firstname of the user
     */
    private String firstName;

    /**
     * This is the last name of the user.
     */
    private String lastName;

    /**
     * ArrayList of Message object.
     * This is all messages that this User currently have in their inbox
     */
    private ArrayList<Message> currentlyMessaging;

    /**
     * User's file containing every name of the Message file that is created by User
     */
    private File messagingNames;


    /**
     * Constructor
     * <p>
     * - Instantiate User's username and password fields from given parameter
     * - Declare new currentlyMessaging ArrayList<Message>()
     * - If the username is 'invalid', Don't create a user File for this User, otherwise do so.
     *
     * @param username
     * @param password
     * @throws IOException
     */
    public User(String firstName, String lastName, String username, String password) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.fileUsername = username;
        this.password = password;
        this.filePassword = password;
        currentlyMessaging = new ArrayList<Message>();

        if (!username.equals("invalid")) {
            messagingNames = new File(fileUsername + "CurrentlyMessaging.csv");
            messagingNames.createNewFile();
        }

    }

    /**
     * Constructor
     * <p>
     * - Instantiate User's username and password fields from given parameter
     *
     * @param fileUsername
     * @param filePassword
     * @throws IOException
     */
    public User(String fileUsername, String filePassword) throws IOException {
        this.fileUsername = fileUsername;
        this.filePassword = filePassword;
        currentlyMessaging = new ArrayList<Message>();

    }

    /**
     * Constructor
     *  <p>
     * Instantiate User's username and password fields from given parameter
     * Declare new currentlyMessaging ArrayList<Message>()
     * If the username is 'invalid', Don't create a user File for this User, otherwise do so.
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param fileUsername
     * @param filePassword
     * @throws IOException
     */
    public User(String firstName, String lastName, String username, String password, String fileUsername, String filePassword) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.fileUsername = fileUsername;
        this.filePassword = filePassword;
        currentlyMessaging = new ArrayList<Message>();
        System.out.println(fileUsername);

        if (!username.equals("invalid")) {
            messagingNames = new File(fileUsername + "CurrentlyMessaging.csv");
            System.out.println(messagingNames.getName() + " new messaging names file");
            messagingNames.createNewFile();
        }

    }

    //Methods

    /**
     * This method return this User's username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method return this User's password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method rewrites the UserFile for when imported conversation overlaps with the existed one
     *
     * @throws FileNotFoundException
     */
    public void reWriteCurrentlyMessaging() throws FileNotFoundException {

        //Create new File Output Stream
        File f = new File(fileUsername + "CurrentlyMessaging.csv");
        messagingNames = f;
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);

        for (int i = 0; i < currentlyMessaging.size(); i++) {
            pw.print(currentlyMessaging.get(i).getFile().getName());

            for (int j = 0; j < currentlyMessaging.get(i).getUsers().size(); j++) {
                pw.print("," + currentlyMessaging.get(i).getUsers().get(j).getFileUsername() +
                        "," + currentlyMessaging.get(i).getUsers().get(j).getFilePassword());
            }
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    /**
     * This method gets the user file name
     * @return file username
     */
    public String getFileUsername() {
        return fileUsername;
    }

    /**
     * This method gets the file password
     * @return the file password
     */
    public String getFilePassword() {
        return filePassword;
    }

    /**
     * This method adds a new message conversation to the message array.
     *
     * @param m         the new message to add
     * @param filename  the filename to write to for parsing in the future
     * @param addToFile if we want to write to the file about this new message, if we are parsing we dont want to write again so it will be false.
     * @throws FileNotFoundException if file is not found
     */
    public void addMessage(Message m, String filename, boolean addToFile) throws FileNotFoundException {

        if (!currentlyMessaging.contains(m)) {
            currentlyMessaging.add(m);
        }
        if (addToFile) {
//            for (int i = 0; i < currentlyMessaging.size(); i++) {
//                if (currentlyMessaging.get(i).getUsers().containsAll(m.getUsers()) && m.getUsers().containsAll(currentlyMessaging.get(i).getUsers())) {
//                    currentlyMessaging.remove(i);
//                    reWriteCurrentlyMessaging();
//                    break;
//                }
//            }



            FileOutputStream fos = new FileOutputStream(messagingNames, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.print(filename);
            for (int j = 0; j < currentlyMessaging.get(currentlyMessaging.size() - 1).getUsers().size(); j++) {
                pw.print("," + currentlyMessaging.get(currentlyMessaging.size() - 1).getUsers().get(j).getFileUsername());
                pw.print("," + currentlyMessaging.get(currentlyMessaging.size() - 1).getUsers().get(j).getFilePassword());

            }
            pw.println();
            pw.flush();
            pw.close();
        }
    }

    /**
     * This method returns the last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method returns the first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method sets the first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method sets the last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method adds a user to the users array list and it also writes to the file
     * If it is a duplicate user it will throw and exception and not allow that to be written
     *
     * @param userFile the user file to be written to.
     * @param user     the user logged in.
     * @param users    the list of users.
     */
    public static ArrayList<User> addUser(File userFile, User user, ArrayList<User> users) throws FileNotFoundException, DuplicateUserException {
        boolean isEqual = false;
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i))) {
                throw new DuplicateUserException();
            }
        }

        //Add comma to make this a csv file
        FileOutputStream fos = new FileOutputStream(userFile, true);
        PrintWriter pw = new PrintWriter(fos);
        pw.print(user.getFirstName() + "," + user.getLastName() + "," + user.getUsername() + "," + user.getPassword() + ",");
        pw.print(user.getFileUsername() + "," + user.getFilePassword() + "\n");
        users.add(user);

        pw.close();
        return users;
    }

    /**
     * This methods compare this User with the Object o
     * and return true if both Users points to the same username
     *
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (o instanceof User) {
            User u = (User) o;
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a string indicating the User's information in the format of "username password"
     *
     * @return String
     */
    public String toString() {
        return username + " " + password;
    }

    /**
     * This method returns the User File with the list of all other message Files's name that User has created.
     *
     * @return messagingNames
     */
    public File getMessagingNames() {
        return messagingNames;
    }

    /**
     * This method allows the User to change their username
     *
     * @param username
     */
    public void setUsername(String username) throws IOException {
        this.username = username;
    }

    /**
     * This method allows the User to change their password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This methods returns the User's ArrayList of all Messages inboxes
     *
     * @return currentlyMessaging
     */
    public ArrayList<Message> getMessages() {
        return currentlyMessaging;
    }


}
