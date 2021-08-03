import javax.swing.*;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Project 5 - Utils Class
 * This class is for extra methods that are useful to make the Social Platform class look cleaner.
 *
 * @author Mahad Faruqi, Yagmur Onder, Kasidit Muenprasitivej, Haohan Wu
 * @version Aug 3, 2021
 */
public class Utils {

    public Scanner scanner = new Scanner(System.in); // one scanner declared for input
    private Object o = new Object();                 //object for synchronization

    /**
     * This method is for asking the user if they want to login or sign up.
     * Most likely we need to make this method like a menu along with a
     * sign up message.
     * We need to have like after they login, edit their account, message, etc.
     * Also need to make this method so that if they do not put a valid input re prompt.
     *
     * @return 1 if they want to login, 2 if they want to sign up.
     */
    public int loginOrSignUp() {

        String[] options = {"Login", "Sign up"};
        int result = JOptionPane.showOptionDialog(null,
                "Welcome to IDE Messenger!",
                "IDE Messenger", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        return result;
    }

    /**
     * This method is for signing up a user, if a username is duplicate it throus a DuplicateUserException.
     * Eventually we have to make that reprompt if it is the same user but for now this is good.
     * Creates a new user. The add user method is the one that throws exception if it is same.
     * It also writes to the file with all the users, to parse since it is a sign up and new.
     *
     * @param userFile the file to write to(done in the addUser method in User class).
     * @param users    the list of users.
     * @return the list of users.
     * @throws FileNotFoundException  if file is not found.
     * @throws IOException            if it cannot be written to.
     * @throws DuplicateUserException if the username is already taken.
     */
    public ArrayList<User> signUp(File userFile, ArrayList<User> users) throws FileNotFoundException, IOException, DuplicateUserException {

        String username;
        String password;
        String firstName;
        String lastName;

        username:
        while (true) {
            //Prompt user to enter existed username to login
            username = JOptionPane.showInputDialog(null, "Please enter your username.",
                    "Create New Account", JOptionPane.QUESTION_MESSAGE);

            //If user decide to exit the sign up window
            if (username == null) {
                return users;
            }

            username = username.toLowerCase(Locale.ROOT);

            //Check if the entered username is already taken
            for (int i = 0; i < users.size(); i++) {
                if (username.equals(users.get(i).getUsername()) || username.equals(users.get(i).getFileUsername())) {
                    System.out.println(users.get(i).getUsername() + " USER USER USER");
                    JOptionPane.showMessageDialog(null,
                            "The entered username is already taken.\nPlease try again!",
                            "Create New Account", JOptionPane.ERROR_MESSAGE);
                    continue username;
                }
            }

            //Validate given username for a correct format
            if (passwordValidation(username) && !username.contains(" ")) {
                username = username.toLowerCase(Locale.ROOT);
                break username;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Username should contain only alphabet characters and numbers and no space.\n" +
                                "Please try again.",
                        "Create New Account", JOptionPane.ERROR_MESSAGE);
            }

        }

        password:
        while (true) {
            //Prompt user to enter existed username to login
            password = JOptionPane.showInputDialog(null,
                    "Please enter your password (Case-Sensitive).",
                    "Create New Account", JOptionPane.QUESTION_MESSAGE);

            //If user decide to exit the sign up window
            if (password == null) {
                return users;
            }

            //Validate given password for a correct format
            if (passwordValidation(password) && !password.contains(" ")) {
                break password;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Password should contain only alphabet characters and numbers and no space.\n" +
                                "Please try again.",
                        "Create New Account", JOptionPane.ERROR_MESSAGE);
            }

        }

        firstName:
        while(true) {
            firstName = JOptionPane.showInputDialog(null,
                    "Please enter your first name.",
                    "Create New Account", JOptionPane.QUESTION_MESSAGE);

            //If user decide to exit the sign up window
            if (firstName == null) {
                return users;
            }

            firstName = firstName.toLowerCase(Locale.ROOT);

            //Validate given first name for a correct format
            if (nameValidation(firstName) && !firstName.contains(" ")) {
                break firstName;

            } else {
                JOptionPane.showMessageDialog(null,
                        "First name should contain only alphabet and no space.\n" +
                                "Please try again.",
                        "Create New Account", JOptionPane.ERROR_MESSAGE);
            }

        }

        lastName:
        while(true) {
            lastName = JOptionPane.showInputDialog(null,
                    "Please enter your last name.",
                    "Create New Account", JOptionPane.QUESTION_MESSAGE);

            //If user decide to exit the sign up window
            if (lastName == null) {
                return users;
            }

            lastName = lastName.toLowerCase(Locale.ROOT);

            //Validate given last name for a correct format
            if (nameValidation(lastName) && !lastName.contains(" ")) {
                break lastName;

            } else {
                JOptionPane.showMessageDialog(null,
                        "Last name should contain only alphabet characters and no space.\n" +
                                "Please try again.",
                        "Create New Account", JOptionPane.ERROR_MESSAGE);
            }

        }

        //Create new user
        User user = new User(firstName, lastName, username, password);
        users = user.addUser(userFile, user, users);
        JOptionPane.showMessageDialog(null,
                "Your new account has been created!\n" +
                        "Name: " + firstName.substring(0, 1).toUpperCase() + firstName.substring(1) +
                        " " + lastName.substring(0, 1).toUpperCase() + lastName.substring(1) + "\n" +
                        "Username: " + username + "\n" +
                        "Password: " + password, "IDE Messenger", JOptionPane.INFORMATION_MESSAGE);

        return users;
    }

    /**
     * This method validate the password for newly created account.
     * The password must only contain number and alphabets, and no special character
     * Reference: https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
     *
     * @param password
     * @return boolean
     */
    public static boolean passwordValidation(String password) {

        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!,@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        return (hasLetter.find() || hasDigit.find()) && !hasSpecial.find();
    }

    /**
     * This method validate the first anf last name for newly created account.
     * The name must only contain alphabets.
     * Reference: https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
     *
     * @param  name
     * @return boolean
     */
    public static boolean nameValidation(String name) {

        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!,@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letter.matcher(name);
        Matcher hasDigit = digit.matcher(name);
        Matcher hasSpecial = special.matcher(name);

        return hasLetter.find() && !hasDigit.find() && !hasSpecial.find();
    }

    /**
     * This method is for logging in the user.
     * It goes through the userFile to make sure that we have a useranme and password that matched up.
     * If it does it prints out success. **This method also adds the new logged in user to the end of the list.
     * This is because when we ask them who they would like to message, the user is at the end so the index will not be messed up.
     *
     * @param userFile the file that we are checking.
     * @param users    the users array.
     * @return array list of users.
     * @throws FileNotFoundException if file is not found.
     * @throws IOException           if it cannot be written to.
     */
    public User logIn(File userFile, ArrayList<User> users) throws FileNotFoundException, IOException {

        //Create null user
        User user = null;

        //Prompt user to enter existed username to login
        String username = JOptionPane.showInputDialog(null, "Please enter your username.",
                "Login", JOptionPane.QUESTION_MESSAGE);

        //If user decide to exit the login window
        if (username == null) {
            User invalid = new User("invalid", "invalid", "invalid", "invalid");
            return invalid;
        }

        //Make username lowercase
        username = username.toLowerCase(Locale.ROOT);

        //Initialize File Input Stream Object
        FileReader fr = new FileReader(userFile);
        BufferedReader bfr = new BufferedReader(fr);
        boolean loggedIn = false; // to know if it was a success or not

        readFile:
        while (true) { // while loop to go through the userFile
            String line = bfr.readLine();

            //break if there's no more line to read
            if (line == null) {
                break readFile;
            }
            String[] info = line.split(",");

            if (info[2].equals(username)) {

                String password;

                password:
                while (true) {

                    //If the entered username exists, prompt user to enter their password
                    password = JOptionPane.showInputDialog(null,
                            "Please enter your password (Case-Sensitive).",
                            "Login", JOptionPane.QUESTION_MESSAGE);

                    //If user decide to exit the login window
                    if (password == null) {
                        User invalid = new User("invalid", "invalid", "invalid", "invalid");
                        return invalid;
                    }

                    //validate the password
                    if (info[3].equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login Successfully!",
                                "Login", JOptionPane.INFORMATION_MESSAGE);
                        break password;

                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password!\n" +
                                        "Please try again.",
                                "Login", JOptionPane.ERROR_MESSAGE);
                    }
                }

                //Create new user with given existed username and password
                loggedIn = true;

                //Point the newly created users to the existed User object
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                        return users.get(i);
                    }
                }

            } // end of outer if statement
        } // end of while loop

        //If entered username doesn't match with any existed account
        if (!loggedIn) {
            JOptionPane.showMessageDialog(null,
                    "Couldn't find existed account from given username.\n" +
                            "Please try again.",
                    "Login", JOptionPane.ERROR_MESSAGE);

            User invalid = new User("invalid", "invalid", "invalid", "invalid");
            return invalid;
        }

        return user;
    }

    /**
     * This method updates the user file file, and writes all the users into it
     *
     * @param users the users in the program
     * @throws FileNotFoundException if file is not found
     * @throws IOException           if file cannot be written to
     */
    public void changeUserFile(ArrayList<User> users) throws FileNotFoundException, IOException, DuplicateUserException {

        FileOutputStream fos = new FileOutputStream("userFile.csv", false);
        PrintWriter pw = new PrintWriter(fos);

        for (int i = 0; i < users.size(); i++) {
            pw.println(users.get(i).getFirstName() + "," + users.get(i).getLastName() + "," +
                    users.get(i).getUsername() + "," + users.get(i).getPassword() + ","
                    + users.get(i).getFileUsername() + "," + users.get(i).getFilePassword());
        }
        pw.flush();
        pw.close();

    }

    /**
     * This method parses the users, to the array list every single time it runs.
     * This has to happen because the array list will become null or empty when its run again, since the program does not remember its old array list.
     * For this reason we save everything in the userFile and re parse the array list to be good every time it is run.
     *
     * @param userFile the file to read and parse the list with.
     * @param users    the list to be parsed
     * @return users the list that is parsed
     * @throws FileNotFoundException if file is not found.
     * @throws IOException           if it cannot be written to.
     */
    public ArrayList<User> parseUsers(File userFile, ArrayList<User> users) throws FileNotFoundException, IOException {

        if (userFile.exists()) { // this checks if the file exists so the first time it runs it doesnt create exceptions
            FileReader fr = new FileReader(userFile);
            BufferedReader bfr = new BufferedReader(fr);

            //If there is the first time the method is called,
            //read through the files and add all user to the ArrayList
            if (users.size() == 0) {

                while (true) {
                    String line = bfr.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] info = line.split(","); // this splits the line that is read into every space, ex. Coding Project, would be split into info[0] = Coding, info[1] = Project
                    // this is how it splits all the users into username + password.

                    //info[1] - username
                    //info[2] - password

                    try {
                        users.add(new User(info[0], info[1], info[2], info[3], info[4], info[5]));
                        System.out.println(Arrays.toString(info));

                    } catch (ArrayIndexOutOfBoundsException ignored) {
                        //move on
                    }
                }

                //If there is not the first time the method is called,
                //only add new users to the arraylists

            } else {

                return users;

            } //end of if-else statement
        }

        return users;
    }

    /**
     * This is the parse message method where it parses each users message history.
     * This method is important because without it the program cannot remember what files and what users we have messaged already or, is new.
     *
     * @param users                  the list of users.
     * @throws FileNotFoundException if file is not found.
     * @throws IOException           if it cannot be written to.
     */
    public ArrayList<User> parseMessage(ArrayList<User> users) throws FileNotFoundException, IOException {

        //Local variable
        FileReader fr;
        BufferedReader bfr;
        ArrayList<User> messageUsers = new ArrayList<>();

        //read file
        File f;

        //The first time, the first user ever join,
        //the program will parse all message file and update all users' message history
            for (int i = 0; i < users.size(); i++) {
                f = users.get(i).getMessagingNames(); // the file that all the message users + history is stored.
                fr = new FileReader(f);
                bfr = new BufferedReader(fr);

                while (true) {

                    String line = bfr.readLine();
                    if (line == null) {
                        break;
                    }

                    String[] info = line.split(",");

                    User user1;
                    for (int j = 1; j < info.length; j += 2) { // i starts at 1 because it is the username, and it increments by 2 so to get the username and password of each user.
                        user1 = new User(info[j], info[j + 1]);

                        messageUsers.add(findDuplicate(users, user1));
                        System.out.println(info[j] + " " + info[j + 1] + " user to parse with message file");
                        System.out.println(findDuplicate(users, user1) + " user to parse with message");
                    }

                    File f1 = new File(info[0]);
                    Message m = new Message(f1, messageUsers, users.get(i));

//                boolean addToFile = true;
//                for (int j = 0; j < users.get(i).getMessages().size(); j++) {
//                    if (users.get(i).getMessages().get(j).getUsers().containsAll(messageUsers)) {
//                        addToFile = false;
//                    }
//                }
                    users.get(i).addMessage(m, f1.getName(), false); // right here we add the message to the single user, because it is not necessary to do every user since only one is logged
                    // in at a time.
                    System.out.println(m.toString() + " new messages");

                    messageUsers.clear(); // this clears for each convo they have. So if they have two this loop runs twice because each message they have is 1 line. Username + username, and then user,
                    // pass of all the users
                }
                for(int j = 0; j < users.get(i).getMessages().size(); j++) {
                    System.out.println(users.get(i).getMessages().get(j).toString() + " MESSAGES");
                }
            }
        return users;
    }

    /**
     * This method finds the duplicate
     *
     * @param users the users list
     * @param user1 the user to find
     * @return the user that is the same
     */
    public User findDuplicate(ArrayList<User> users, User user1) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFileUsername().equals((user1.getFileUsername()))) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * This method is robust input for an integer. If the user does not type an integer it tells them to or if its not between the min or max.
     *
     * @param in     This is the scanner used to get the input
     * @param prompt This is the string you can use to ask whatever you want
     * @param min    This is the lowest number the user can type
     * @param max    This is the highest number the user can round to
     * @return Returns only the integer that the user types
     */
    public static int getNumber(Scanner in, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);
            if (!in.hasNextInt()) {
                in.next();
                System.out.println("You must enter an Integer between " + min + " and " + max + ".");
            } else {
                int num = in.nextInt();
                if (num < min || num > max) {
                    System.out.println("Your number needs to be between " + min + " and " + max + ".");
                } else {
                    return num;
                }
            }
        }
    }

    /**
     * This method imports a file that the user gives and if it can it will parse it
     *
     * @param currentUser the current user using the program
     * @param userList    the list of users in the program
     * @return returns 0 if it succesfully does it or else -1
     * @throws IOException if file cannot be written to
     */
    public void importFile(User currentUser, ArrayList<User> userList, String importedFile) throws IOException {


        //Create File from given file name
        File f = new File(importedFile);
        f.createNewFile();
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        String importUsername = "";
        ArrayList<String> importUsernameList = new ArrayList<String>();
        String[] formattedLine;

        try {
            while (true) {
                String line = bfr.readLine();


                if (line == null) {
                    break;
                }
                formattedLine = line.split(",");
                if (formattedLine.length != 3) {
                    JOptionPane.showMessageDialog(null, "This imported file is formatted " +
                            "incorrectly.\nThe correct formatted file should look like: username,message," +
                            "timestamp(HH:mm:ss)", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (line.contains(",")) {
                    importUsername = line.substring(0, line.indexOf(","));
                }

                if (!importUsername.equals(" ")) {
                    importUsernameList.add(importUsername);
                }

                importUsername = "";
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isValid = true;
        ArrayList<User> userInThisChat = new ArrayList<User>();
        userInThisChat.add(new User("", "", "", ""));


        outer:
        for (int i = 0; i < importUsernameList.size(); i++) {

            for (int j = 0; j < userList.size(); j++) {

                if (importUsernameList.get(i).equals(userList.get(j).getUsername())) {

                    for (int k = 0; k < userInThisChat.size(); k++) {
                        if (userInThisChat.get(k).equals(userList.get(j))) {
                            break;
                        }

                        if (k == userInThisChat.size() - 1) {
                            userInThisChat.add(userList.get(j));
                        }
                    }

                    continue outer;
                }

                if (j == userList.size() - 1) {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "File Import: FAILED\nImported file " +
                            "contains a user not currently registered", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        userInThisChat.remove(0);
        for (int i = 0; i < userInThisChat.size(); i++) {
            if (userInThisChat.get(i).equals(currentUser)) {
                User user = userInThisChat.get(i);
                userInThisChat.remove(userInThisChat.get(i));
                userInThisChat.add(0, user);
                break;

            }
        }
        importUsernameList.clear();
        System.out.println(Arrays.toString(new ArrayList[]{userInThisChat}));
        for (int i = 0; i < userInThisChat.size(); i++) {
            importUsernameList.add(userInThisChat.get(i).getUsername());
        }


        if (isValid) {

            for (int i = 0; i < userInThisChat.size(); i++) {
                String fileNameNew = userInThisChat.get(i).getUsername();
                Collections.sort(importUsernameList, new Comparator<String>() {
                    @Override
                    public int compare(String u1, String u2) {
                        return u1.compareToIgnoreCase(u2);
                    }
                });

                for (int j = 0; j < importUsernameList.size(); j++) {
                    if (!userInThisChat.get(i).getUsername().equals(importUsernameList.get(j))) {
                        fileNameNew += importUsernameList.get(j);
                    }
                }
                fileNameNew += ".csv";

                File newFile = new File(fileNameNew);
                System.out.println(i + " " + newFile.exists() + fileNameNew + " I and FILE");
                if (i == 0 && newFile.exists()) {

                    String[] options = {"Continue", "Quit"};
                    int result = JOptionPane.showOptionDialog(null,
                            "This will override previous conversation\nDo you wish to continue?",
                            "Override Conversation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);

                    if(result == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                FileReader fr1 = new FileReader(f);
                BufferedReader bfr1 = new BufferedReader(fr1);
                FileOutputStream fos = new FileOutputStream(newFile);
                PrintWriter pw = new PrintWriter(fos);
                while (true) {

                    String line1 = bfr1.readLine();
                    if (line1 == null) {
                        break;
                    }
                    pw.println(line1);
                }
                pw.flush();
                pw.close();
                bfr1.close();
                userInThisChat.get(i).addMessage(new Message(newFile, userInThisChat, userInThisChat.get(i)),
                        newFile.getName(), true);
            }
        }
        return;
    }

    /**
     * This method gets a message with users that is passed in
     *
     * @param currentUser    this is the current user
     * @param otherUsersList this is the list of users
     * @return the message that is shared with the users
     */
    public Message getMessageWithUserList(User currentUser, ArrayList<User> otherUsersList) {

        ArrayList<Message> messages = currentUser.getMessages();

        String fileName = "";
        for (int i = 0; i < otherUsersList.size(); i++) {
            fileName = otherUsersList.get(i).getFileUsername();
            for (int j = 0; j < otherUsersList.size(); j++) {
                if (!otherUsersList.get(j).equals(otherUsersList.get(i))) {
                    fileName += otherUsersList.get(j).getFileUsername();
                }
            }
            fileName += ".csv";
        }

        //Check if the created file name is already existed
        File newFile = new File(fileName);

        outer:
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getUsers().containsAll(otherUsersList) &&
                    otherUsersList.containsAll(messages.get(i).getUsers())) {
                return messages.get(i);
            }
        }
        return new Message(newFile, otherUsersList, currentUser);
    }
}
