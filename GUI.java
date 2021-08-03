import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project 5 - GUI Class
 * This class handles the creation of GUI and the way users will interact with them
 *
 * @author Mahad Faruqi, Yagmur Onder, Kasidit Muenprasitivej, Haohan Wu
 * @version August 3, 2021
 */
public class GUI implements Runnable {

    /**
     * Fields Declaration for variable storing users' information
     */
    private User user;
    private static ArrayList<User> users;
    private static Utils u = new Utils();
    private static Object o = new Object();

    /**
     * Constructor for GUI class
     *
     * @param user
     * @param users
     */
    public GUI(User user, ArrayList<User> users) {
        this.user = user;
        for (int i = 0; i < user.getMessages().size(); i++) {
            System.out.println("Existing convo " + i + 1);
            System.out.println(user.getMessages().get(i).toString());
            System.out.println(user.getMessages().get(i).getUser().toString());

        }
        this.users = users;
        System.out.println(user.toString());
        System.out.println(users.toString());
    }

    /**
     * Fields Declaration for all interface used in this app
     */
    //FIRST PANEL - MENU
    JPanel menuPanel;           //Menu panel for displaying menu page
    JLabel welcomeHeader;       //Welcome Message header in mean page
    JLabel usernameDisplay;     //display user's username
    JButton editAcc;            //Button option to bring the user to Edit Account page
    JButton messageSomeone;     //Button option to bring the user to Messeging page
    JButton importConversation; //Button option to bring the user to Import Conversation page
    JButton logout;             //Button option to log the user out

    //MENU -> EDIT ACCOUNT PANEL
    JButton editAccBack;        //Button Options to bring to user back to Menu Page
    JPanel panelEdit;           //Edit Account panel for displaying edit account page
    JLabel editAccHeader;       //Header Message in edit account page
    JButton editFirstName;      //Button option to bring the user to edit First Name page
    JPanel editFirstNamePanel;  //Panel for displaying edit First Name Page
    JPanel editFirstNameSubPanel;   //Sub-Panel for better organized interface
    JButton editLastName;       //Button option to bring the user to edit last Name page
    JPanel editLastNamePanel;   //Panel for displaying edit Last Name Page
    JPanel editLastNameSubPanel;    //Sub-Panel for better organized interface
    JButton editPassword;       //Button option to bring the user to edit password page
    JPanel editPasswordPanel;   //Panel for displaying edit password Page
    JPanel editPasswordSubPanel;    //Sub-Panel for better organized interface
    JButton editUsername;       //Button option to bring the user to edit username page
    JPanel editUsernamePanel;   //Panel for displaying edit username Page
    JPanel editUsernameSubPanel;    //Sub-Panel for better organized interface
    JLabel firstNameHeader;     //Header message for edit First Name page
    JLabel lastNameHeader;      //Header message for edit Last Name page
    JLabel usernameHeader;      //Header message for edit username page
    JLabel passwordHeaader;     //Header message for edit password page
    JTextField usernameText;    //TextField for user to enter their new username
    JTextField passwordText;    //TextField for user to enter their new password
    JTextField firstNameText;   //TextField for user to enter their new first name
    JTextField lastNameText;    //TextField for user to enter their new last name
    JButton usernameConfirm;    //Button to confirm changing username
    JButton passwordConfirm;    //Button to confirm changing password
    JButton firstNameConfirm;   //Button to confirm changing first name
    JButton lastNameConfirm;    //Button to confirm changing last name

    //MENU -> MESSAGE SOMEONE PANEL
    JPanel messagePanel;        //Panel for displaying messeging page
    JLabel optionText;          //Let the user choose to create new chat or view existing chat
    JButton newChat;            //Button option to bring to user to create new chat page
    JButton backToMenu;         //Button option to bring to user to view existing chat page
    JPanel messageBottomPanel;  //Bottom Panel for better organized GUI

    //MESSAGE -> CREATE NEW CHAT
    JPanel listOfFriendsPanel;  //Panel for displaying list of user's friends
    JLabel listOfFriendsText;   //Label providing user instructions
    JPanel bottomPanel;         //Bottom Panel for better organized GUI
    JPanel otherOptionPanel;    //Option Panel for better organized GUI
    JButton backToMessageMenu;  //Button option to bring te user back to menu page
    JButton startChat;          //Button option to let the user create new chat with selected friends
    JPanel displayTextPanel;    //Panel for better organized GUI
    JLabel displayingText;      //Label to display which other users the user has selected so far
    JButton[] selectFriend;     //Button array signifying each other users in this program
    String selectedUser;        //String to keep track of which other users the user has selected so far

    //MESSAGE -> VIEW EXISTING CHAT
    JPanel existingChatPanel;   //Panel for displaying the existing chat page
    JScrollPane usersListScrollable;    //JScrollPane applied to existingChatPanel
    JLabel instructionText;     //Label provide the user instructions
    JButton[] selectChat;       //Button array signifying each existing chats

    //MESSAGE -> DELETE CONVERSATION
    JButton deleteConvo;        //Button option to bring user to delete conversation page
    JPanel deleteListOfChat;    //Panel for displaying delete conversation page
    JLabel deleteInstruction;   //Label provide the user instructions
    JButton[] selectDeleteChat; //Button array signifying each existing chats to be deleted
    JScrollPane deleteScrollable;  //JScrollPane applied to deleteListOfChat
    JButton backToMessageMenu2; //Button option to bring the user back to Messaging page

    //MESSAGE -> SEND MESSAGE
    JPanel topChatPanel;        //Header bar of the inbox page
    JLabel chatMemberHeader;    //Label displaying all users in this chat
    JPanel sendPanel;           //Panel containing necessary buttons for user messaging interaction
    JTextField messageBox;      //TextFields where the user can enter their message
    JButton sendMessage;        //Button option to send the message
    JButton editThisMessage;    //Button option to edit the message
    JButton backFromMessage;    //Button option to bring the user back to Messaging page

    //MESSAGE -> EDIT MESSAGE
    JPanel editTopPanel;        //Panel for better organized GUi
    JLabel editHeader;          //Header text providing instruction to users
    JScrollPane editScrollPane; //JScrollPane applied to editTextPanel
    JPanel editTextPanel;       //Panel displaying list of users' message history
    JTextField editTextBox;     //TextFields where user can enter their edited text
    JButton doneWithEdit;       //Button option indicating the user s done with editing
    JButton backFromEdit;       //Button option to bring user back to messagin page
    JButton deleteMessage;      //Button option indicating that the user want to delete this line of text
    ArrayList<JButton> messageButtons;  //Button signifying each line of message sent by users
    JPanel editMessagePanel;    //Panel for better organized GUI

    //MENU -> IMPORT MESSAGE
    JPanel importPanel;         //Panel for displaying import converation page
    JPanel interactPanel;       //Panel for better organized GUI
    JTextField fileNameBox;     //TextFields for user to enter their import file name
    JLabel instructionTextForImport;    //Label providing instruction to user
    JButton open;               //Open Button options to open the file
    JButton back;               //Button options to bring the user back to menu page


    /**
     * run() method for Event Dispatch Threads
     */
    @Override
    public void run() {

        //Creatr JFrame and Containre
        JFrame frame = new JFrame("Messenger");
        Container content = frame.getContentPane();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.print("CHECK PRINT");
        frame.setVisible(true);

        //Initialize the Menu GUI and set formatting
        menuPanel = new JPanel(); //make menu panel
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        welcomeHeader = new JLabel("Welcome "
                + user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1) + " "
                + user.getLastName().substring(0, 1).toUpperCase() + user.getLastName().substring(1) + "!");
        welcomeHeader.setFont(new Font("Calibri", Font.BOLD, 20));
        welcomeHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameDisplay = new JLabel("Username: " + user.getUsername());
        usernameDisplay.setFont(new Font("Calibri", Font.ITALIC, 14));
        usernameDisplay.setForeground(Color.GRAY);
        usernameDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        //menu panel buttons
        editAcc = new JButton("     Edit Account      ");
        editAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        editAcc.setPreferredSize(new Dimension(600, 100));
        messageSomeone = new JButton("         Message         ");
        messageSomeone.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageSomeone.setPreferredSize(new Dimension(600, 100));
        importConversation = new JButton("Import conversation");
        importConversation.setAlignmentX(Component.CENTER_ALIGNMENT);
        importConversation.setPreferredSize(new Dimension(600, 100));
        logout = new JButton("          Logout           ");
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setPreferredSize(new Dimension(600, 100));

        //add buttons to menu panel
        menuPanel.add(welcomeHeader);
        menuPanel.add(usernameDisplay);
        menuPanel.add(editAcc);
        menuPanel.add(messageSomeone);
        menuPanel.add(importConversation);
        menuPanel.add(logout);

        content.add(menuPanel);

        //Add actionListener
        ActionListener actionListener = new ActionListener() {

            //create all panels at the start, just like with menuPanel
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editAcc) {
                    //content.remove(panel);
                    content.add(editAcc(content, menuPanel));
                    menuPanel.setVisible(false);

                }
                if (e.getSource() == messageSomeone) {
                    content.add(message(content, menuPanel));
                    menuPanel.setVisible(false);

                }
                if (e.getSource() == importConversation) {
                    content.add(importMessage(content, menuPanel));
                    menuPanel.setVisible(false);
                    // import conversation
                }
                if (e.getSource() == logout) {
                    int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Log Out",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    }
                }
            }
        };

        //Add actionListener
        editAcc.addActionListener(actionListener);
        messageSomeone.addActionListener(actionListener);
        importConversation.addActionListener(actionListener);
        logout.addActionListener(actionListener);
    }

    /**
     * Create a messaging page, and handle all messaging interaction
     *
     * @param content
     * @param menuPanel
     * @return
     */
    public JScrollPane message(Container content, JPanel menuPanel) {

        //creates message panel to choose either create new chat or view existing chat
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));

        optionText = new JLabel("Create a New Chat");
        optionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionText.setFont(new Font("Calibri", Font.BOLD, 20));

        newChat = new JButton("Create new chat");
        newChat.setAlignmentX(Component.CENTER_ALIGNMENT);
        newChat.setPreferredSize(new Dimension(200, 80));

        messageBottomPanel = new JPanel();

        backToMenu = new JButton("Back");

        messagePanel.add(optionText);
        messagePanel.add(newChat);

        messageBottomPanel.add(backToMenu);


        //__________________________________________________

        //CREATE GUI FOR STARTING NEW CONVERSATION AND SELECTING USERS

        //__________________________________________________

        //creating new panel to show list of all user's friends
        listOfFriendsPanel = new JPanel();
        listOfFriendsPanel.setLayout(new BoxLayout(listOfFriendsPanel, BoxLayout.PAGE_AXIS));

        //Add Header to the panel
        listOfFriendsText = new JLabel("Your List of Friends:");
        listOfFriendsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        listOfFriendsText.setFont(new Font("Calibri", Font.BOLD, 20));
        listOfFriendsPanel.add(listOfFriendsText);

        //Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        //Panel for confirming Start Chat or Go Back
        otherOptionPanel = new JPanel();
        backToMessageMenu = new JButton("Back");
        startChat = new JButton("Start Chat with Selected User(s)");

        otherOptionPanel.add(startChat);
        otherOptionPanel.add(backToMessageMenu);

        //__________________________________________________

        //Add list of friends, of which the user can select
        selectedUser = "Selected User: ";
        selectFriend = new JButton[users.size()];
        String tempUsername;
        ArrayList<User> listChat = new ArrayList<User>();

        for (int i = 0; i < selectFriend.length; i++) {
            System.out.println("Size " + users.size());
            System.out.println("User " + users.get(i).toString());
            if (!users.get(i).equals(user)) {
                tempUsername = users.get(i).getFirstName().substring(0, 1).toUpperCase() +
                        users.get(i).getFirstName().substring(1)
                        + " " + users.get(i).getLastName().substring(0, 1).toUpperCase() +
                        users.get(i).getLastName().substring(1);
                selectFriend[i] = new JButton(tempUsername);
                selectFriend[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                selectFriend[i].setPreferredSize(new Dimension(200, 80));
                selectFriend[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String tempUserName = ((JButton) e.getSource()).getText();
                        selectedUser += tempUserName + "  ";
                        String[] split = tempUserName.split(" ");

                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getFirstName().equalsIgnoreCase(split[0]) &&
                                    users.get(i).getLastName().equalsIgnoreCase(split[1])) {
                                listChat.add(users.get(i));
                            }
                        }

                        ((JButton) e.getSource()).setVisible(false);

                        listOfFriendsPanel.repaint();
                        displayingText.setText(selectedUser);
                        displayTextPanel.repaint();

                        System.out.println(listChat + " list chat");

                    }
                });

                listOfFriendsPanel.add(selectFriend[i]);
                System.out.println("Button is being added " + selectFriend[i].getText());
            }

            System.out.println(users.get(i).getUsername());
            System.out.println(" + ");
            System.out.println(selectFriend[i]);
        }

        //Panel to display Selected User that user want to chat with
        displayTextPanel = new JPanel();
        displayingText = new JLabel();
        displayTextPanel.add(displayingText);

        bottomPanel.add(otherOptionPanel);
        bottomPanel.add(displayTextPanel);

        //__________________________________________________

        //CREATE GUI FOR VIEW EXISTING CHATS

        //__________________________________________________

        existingChatPanel = new JPanel();
        existingChatPanel.setLayout(new BoxLayout(existingChatPanel, BoxLayout.PAGE_AXIS));

        instructionText = new JLabel("Select a conversation:");
        instructionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionText.setFont(new Font("Calibri", Font.BOLD, 20));
        existingChatPanel.add(instructionText);

        usersListScrollable = new JScrollPane(existingChatPanel);

        content.add(messagePanel, BorderLayout.NORTH);
        content.add(usersListScrollable, BorderLayout.CENTER);
        content.add(messageBottomPanel, BorderLayout.SOUTH);

        selectChat = new JButton[user.getMessages().size()];
        System.out.println(user.getMessages().size() + " USER MESSAGES SIZE");
        String tempButtonLabel;
        int counter = 1;

        System.out.println(user.getMessages().toString() + " User's existing Chat list");

        for (int i = 0; i < selectChat.length; i++) {
            tempButtonLabel = "Chat " + counter + ": " + user.getMessages().get(i).toString();
            counter++;
            selectChat[i] = new JButton(tempButtonLabel);
            selectChat[i].setVisible(true);
            selectChat[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            selectChat[i].setPreferredSize(new Dimension(200, 80));
            existingChatPanel.add(selectChat[i]);

            selectChat[i].addActionListener(new ActionListener() {

                ArrayList<User> groupChatMembers = new ArrayList<User>();

                @Override
                public void actionPerformed(ActionEvent e) {
                    String tempUsername = ((JButton) e.getSource()).getText();
                    tempUsername = tempUsername.substring(8, tempUsername.length());
                    chatMemberHeader = new JLabel("     " + tempUsername.replace(",", ", "));
                    chatMemberHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
                    System.out.println(tempUsername);
                    //tempUsername = tempUsername.replace(" ", "");
                    String[] usernameList = tempUsername.split(", ");

                    String firstName;
                    String lastName;
                    for (int i = 0; i < usernameList.length; i++) {
                        String[] names = usernameList[i].split(" ");
                        firstName = names[0];
                        lastName = names[1];
                        for (int j = 0; j < users.size(); j++) {
                            if (users.get(j).getFirstName().equalsIgnoreCase(firstName) &&
                                    users.get(j).getLastName().equalsIgnoreCase(lastName)) {
                                groupChatMembers.add(users.get(j));
                            }
                        }
                    }

                    ArrayList<User> tempGroupChatMembers = (ArrayList<User>) groupChatMembers.clone();
                    try {
                        JScrollPane chatHistory = createNewChat(content, messagePanel, tempGroupChatMembers);
                        content.add(chatHistory);
                        messagePanel.setVisible(false);
                        messageBottomPanel.setVisible(false);
                        usersListScrollable.setVisible(false);
                        content.repaint();

                        groupChatMembers.clear();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }

        deleteConvo = new JButton("Delete Conversation");
        deleteConvo.setAlignmentX(Component.CENTER_ALIGNMENT);
        existingChatPanel.add(deleteConvo);


        //__________________________________________________

        //HANDLE THE REST OF ACTIONLISTENERES IN THE MESSAGING PAGE

        //__________________________________________________


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == newChat) {
                    messagePanel.setVisible(false);
                    messageBottomPanel.setVisible(false);
                    usersListScrollable.setVisible(false);

                    content.add(listOfFriendsPanel, BorderLayout.CENTER);
                    content.add(bottomPanel, BorderLayout.SOUTH);

                    if (users.size() == 1) {
                        listOfFriendsText.setText("You currently have no friends.");
                    }

                    listOfFriendsPanel.setVisible(true);
                    bottomPanel.setVisible(true);
                    displayTextPanel.setVisible(true);

                    listOfFriendsPanel.repaint();
                    bottomPanel.repaint();

                }


                if (e.getSource() == backToMenu) {
                    messagePanel.setVisible(false);
                    messageBottomPanel.setVisible(false);
                    usersListScrollable.setVisible(false);

                    menuPanel.setVisible(true);
                }

                if (e.getSource() == deleteConvo) {
                    messagePanel.setVisible(false);
                    messageBottomPanel.setVisible(false);
                    usersListScrollable.setVisible(false);

                    content.add(deleteConversation(content));
                }

                if (e.getSource() == startChat) {

                    if (listChat.size() == 0) {
                        JOptionPane.showMessageDialog(null, "You must select at least one " +
                                "user to start a chat.", "ERROR!", JOptionPane.ERROR_MESSAGE);

                    } else {
                        listOfFriendsPanel.setVisible(false);
                        bottomPanel.setVisible(false);

                        String chatMemberLine = selectedUser.substring(15, selectedUser.length());
                        chatMemberHeader = new JLabel("     " + chatMemberLine);

                        try {
                            ArrayList<User> tempListChat = (ArrayList<User>) listChat.clone();
                            listChat.clear();

                            content.add(createNewChat(content, messagePanel, tempListChat));

                            for (int i = 0; i < selectFriend.length; i++) {
                                if (!users.get(i).equals(user)) {
                                    selectFriend[i].setVisible(true);
                                }
                            }


                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                }

                if (e.getSource() == backToMessageMenu) {
                    messagePanel.setVisible(true);
                    messageBottomPanel.setVisible(true);
                    usersListScrollable.setVisible(true);

                    listOfFriendsText.setText("Your List of Friends:");
                    listOfFriendsPanel.setVisible(false);
                    bottomPanel.setVisible(false);
                    displayTextPanel.setVisible(false);

                    listChat.clear();
                    for (int i = 0; i < selectFriend.length; i++) {

                        if (!users.get(i).equals(user)) {
                            selectFriend[i].setVisible(true);
                        }
                    }

                    selectedUser = "Selected User: ";
                    displayingText.setText(selectedUser);

                    listOfFriendsPanel.repaint();
                    displayTextPanel.repaint();
                    bottomPanel.repaint();
                }
            }
        };

        newChat.addActionListener(actionListener);
        startChat.addActionListener(actionListener);
        backToMenu.addActionListener(actionListener);
        deleteConvo.addActionListener(actionListener);
        backToMessageMenu.addActionListener(actionListener);

        for (int i = 0; i < selectFriend.length; i++) {
            if (selectFriend[i] != null) {
                selectFriend[i].addActionListener(actionListener);
            }
        }

        return usersListScrollable;
    }

    /**
     * Create GUI for deleting Conversation Page and Handle all user's interaction
     *
     * @param content
     * @return
     */
    public JScrollPane deleteConversation(Container content) {

        //Initialize and set formatting for all GUIS
        deleteListOfChat = new JPanel();
        deleteListOfChat.setLayout(new BoxLayout(deleteListOfChat, BoxLayout.Y_AXIS));
        deleteInstruction = new JLabel("Please Select a Chat to Delete:");
        deleteInstruction.setFont(new Font("Calibri", Font.BOLD, 20));
        deleteInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        deleteListOfChat.add(deleteInstruction);

        backToMessageMenu2 = new JButton("Back");
        backToMessageMenu2.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMessageMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
                deleteListOfChat.setVisible(false);
                deleteScrollable.setVisible(false);
                content.repaint();
            }
        });

        //Displaying options of exisitng chats for selection to delete
        selectDeleteChat = new JButton[user.getMessages().size()];
        System.out.println(user.getMessages().size() + " USER MESSAGES SIZE");
        String tempButtonLabel;
        int counter = 1;

        for (int i = 0; i < selectDeleteChat.length; i++) {
            tempButtonLabel = "Chat " + counter + ": " + user.getMessages().get(i).toString();
            counter++;
            selectDeleteChat[i] = new JButton(tempButtonLabel);
            selectDeleteChat[i].setVisible(true);
            selectDeleteChat[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            selectDeleteChat[i].setPreferredSize(new Dimension(200, 80));
            deleteListOfChat.add(selectDeleteChat[i]);

            selectDeleteChat[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<User> chatMemberToDelete = new ArrayList<User>();

                    //Ask user for confirmation
                    int result = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete this conversation?\n" +
                                    "Other user(s) will still be able to see this conversation.",
                            "Messenger", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {

                        //Set the Button to be visibility to false after delete the chat
                        ((JButton) e.getSource()).setVisible(false);

                        //Find the name of all users in the chat to be deleted
                        String tempUsername = ((JButton) e.getSource()).getText();
                        tempUsername = tempUsername.substring(8, tempUsername.length());
                        String[] usernameList = tempUsername.split(", ");

                        String firstName;
                        String lastName;
                        for (int i = 0; i < usernameList.length; i++) {
                            String[] names = usernameList[i].split(" ");
                            firstName = names[0];
                            lastName = names[1];
                            for (int j = 0; j < users.size(); j++) {
                                if (users.get(j).getFirstName().equalsIgnoreCase(firstName) &&
                                        users.get(j).getLastName().equalsIgnoreCase(lastName)) {
                                    chatMemberToDelete.add(users.get(j));
                                }
                            }
                        }

                        //Delete Chat from user's ArrayList of Message
                        chatMemberToDelete.add(user);
                        Message deleteMessage = u.getMessageWithUserList(user, chatMemberToDelete);

                        String deleteFileName = deleteMessage.getFile().getName();

                        deleteMessage.getFile().delete();
                        user.getMessages().remove(deleteMessage);

                        //Delete this Chat from UserFile
                        File userCurrentlyMessaging = user.getMessagingNames();

                        FileReader fr = null;
                        try {
                            fr = new FileReader(userCurrentlyMessaging);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        BufferedReader bfr = new BufferedReader(fr);

                        //Construct the new file that will later be renamed to the original filename
                        File tempFile = new File("tempFile.csv");
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(tempFile);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        PrintWriter pw = new PrintWriter(fos);

                        String line = "";
                        while (true) {

                            try {
                                line = bfr.readLine();
                            } catch (IOException ioException){
                                ioException.printStackTrace();
                            }

                            if (line == null) {
                                break;
                            }

                            String[] info = line.split(",");

                            //If this is not the line to delete, write this line into tempFile
                            if (!info[0].equals(deleteFileName)) {
                                pw.println(line);
                                pw.flush();
                            }
                        }
                        pw.close();
                        try {
                            bfr.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        //renamed tempFile to the original File
                        tempFile.renameTo(userCurrentlyMessaging);
                    }
                }
            });
        }

        deleteListOfChat.add(backToMessageMenu2);
        //Add ScrollPane to the Panel
        deleteScrollable = new JScrollPane(deleteListOfChat);

        return deleteScrollable;
    }


    /**
     * Handle the behind-the-curtain
     * for when user wish to create new chat with other users of their choice
     * and ensure all files are updated correctly
     *
     * @param content
     * @param messagePanel
     * @param listChat
     * @return
     * @throws IOException
     */
    public JScrollPane createNewChat(Container content, JPanel messagePanel, ArrayList<User> listChat) throws IOException {

        editThisMessage = new JButton("Edit");
        listChat.add(user);
        for (int i = 0; i < listChat.size(); i++) {
            Message m;
            ArrayList<User> messageUsers = new ArrayList<User>();

            //Add each User into the group chat "messageUsers"
            messageUsers.add(listChat.get(i));

            //Add the selected users into the group chat with the user
            for (int j = 0; j < listChat.size(); j++) {
                if (!listChat.get(j).equals(listChat.get(i))) {
                    messageUsers.add(listChat.get(j));
                }
            }

            //Create ArrayList of Username String
            boolean fileIsCreated = true;
            ArrayList<String> allUserNames = new ArrayList<String>();
            for (int k = 0; k < listChat.size(); k++) {
                if (!listChat.get(k).equals(listChat.get(i))) {
                    allUserNames.add(listChat.get(k).getFileUsername());
                }
            }

            //Sort username String in alphabetical order
            Collections.sort(allUserNames, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });

            //Create unique file name for each user
            String fileName = listChat.get(i).getFileUsername();
            for (int j = 0; j < allUserNames.size(); j++) {
                fileName += allUserNames.get(j);
            }
            fileName += ".csv";

            //Create/Retrieve file from created file name
            File file1 = new File(fileName);
            if (file1.exists()) {
                fileIsCreated = false;
            }
            file1.createNewFile();

            //Create new message with the created file ("___.csv") and Users array ("messageUsers")
            //Then assign this message to the user's Arraylist of Message Object
            m = new Message(file1, messageUsers, listChat.get(i));
            listChat.get(i).addMessage(m, fileName, fileIsCreated);
        }

        //ArrayList of String for storing each line of message
        ArrayList<String> chats = new ArrayList<String>();
        Message m = getMessageWithUserList(user, listChat);

        //Initialize the file
        File f = m.getFile();
        FileReader fr;
        BufferedReader bfr;

        //Initialize JTextArea and JScrollPane
        JTextArea jta = new JTextArea();
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        if (f.exists()) {
            fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            while (true) {
                String line = bfr.readLine();
                if (line == null) {
                    break;
                }
                String[] tempString = line.split(",");
                tempString[1] = tempString[1].replaceAll("/", ",");
                if (user.getUsername().equals(tempString[0]) || user.getFileUsername().equals(tempString[0])) {
                    jta.append("<You>" + " : " + tempString[1] + " " + tempString[2] + "\n");
                } else {
                    String newUsername = tempString[0];
                    synchronized (o) {
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getFileUsername().equals(tempString[0])) {
                                newUsername = users.get(i).getUsername();
                            }
                        }
                    }
                    jta.append("<" + newUsername + ">" + " : " + tempString[1] + " " + tempString[2] + "\n");
                }

            }
        }

        //TOP PANEL OF THE CHAT INBOX
        topChatPanel = new JPanel();
        topChatPanel.setLayout(new BoxLayout(topChatPanel, BoxLayout.X_AXIS));
        backFromMessage = new JButton("ᐊ Back");
        backFromMessage.setAlignmentX(Component.LEFT_ALIGNMENT);

        topChatPanel.add(backFromMessage);
        topChatPanel.add(chatMemberHeader);

        //BOTTOM PANEL OF THE CHAT INBOX
        sendPanel = new JPanel();
        messageBox = new JTextField(30);
        messageBox.setText("");
        sendMessage = new JButton("Send");

        sendPanel.add(messageBox);
        sendPanel.add(sendMessage);
        sendPanel.add(editThisMessage);

        content.add(topChatPanel, BorderLayout.NORTH);
        content.add(jsp, BorderLayout.CENTER);
        content.add(sendPanel, BorderLayout.SOUTH);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == backFromMessage) {
                    topChatPanel.setVisible(false);
                    chatMemberHeader.setText("");
                    jsp.setVisible(false);
                    sendPanel.setVisible(false);
                    messagePanel.setVisible(false);
                    messageBottomPanel.setVisible(false);
                    usersListScrollable.setVisible(false);


                    selectedUser = "Selected User: ";
                    menuPanel.setVisible(true);
                    content.repaint();


                }
                if (e.getSource() == editThisMessage) {
                    topChatPanel.setVisible(false);
                    jsp.setVisible(false);
                    sendPanel.setVisible(false);
                    try {
                        content.add(editMessages(content, getMessageWithUserList(user, listChat)));
                        content.repaint();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                if (e.getSource() == sendMessage) {
                    String text = messageBox.getText();

                    if (text != null || !text.equals("")) {

                        text = text.replaceAll(",", "/");

                        messageBox.setText("");
                        Message tempMessage;
                        System.out.println(listChat.size());

                        for (int i = 0; i < listChat.size(); i++) {
                            tempMessage = getMessageWithUserList(listChat.get(i), listChat);
                            System.out.println(listChat.get(i) + " USER");
                            System.out.println(tempMessage.getFile().getName() + " file name");
                            try {
                                tempMessage.addMessage(user, text);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        chats.clear();
                        jta.setText("");

                        if (f.exists()) {
                            FileReader fr = null;
                            try {
                                fr = new FileReader(f);

                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            BufferedReader bfr = new BufferedReader(fr);
                            while (true) {
                                String line = null;
                                try {
                                    line = bfr.readLine();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                if (line == null) {
                                    break;
                                }
                                String[] tempString = line.split(",");
                                tempString[1] = tempString[1].replaceAll("/", ",");
                                if (user.getUsername().equals(tempString[0]) || user.getFileUsername().equals(tempString[0])) {
                                    jta.append("<You>" + " : " + tempString[1] + " " + tempString[2] + "\n");
                                } else {
                                    String newUsername = tempString[0];
                                    synchronized (o) {
                                        for (int i = 0; i < users.size(); i++) {
                                            if (users.get(i).getFileUsername().equals(tempString[0])) {
                                                newUsername = users.get(i).getUsername();
                                            }
                                        }
                                    }
                                    jta.append("<" + newUsername + ">" + " : " + tempString[1] + " " + tempString[2] + "\n");
                                }
                            } // end of while loop
                        } // end of if file exists
                    } // end of if textField is empty
                } // end of send button ActionListener
            }
        };

        editThisMessage.addActionListener(actionListener);
        backFromMessage.addActionListener(actionListener);
        sendMessage.addActionListener(actionListener);

        return jsp;
    }

    /**
     * Create GUI for editing/deleting messages and handle all user's interactions
     *
     * @param content
     * @param m
     * @return
     * @throws IOException
     */
    public JScrollPane editMessages(Container content, Message m) throws IOException {

        //Initialize and set Formatting for the GUI
        editTopPanel = new JPanel();
        editTopPanel.setLayout(new BoxLayout(editTopPanel, BoxLayout.X_AXIS));
        editHeader = new JLabel("                       Select the Line of Message You Wish to Edit");
        editHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        backFromEdit = new JButton("ᐊ Back");
        backFromEdit.setAlignmentX(Component.LEFT_ALIGNMENT);

        editMessagePanel = new JPanel();
        editMessagePanel.setLayout(new BoxLayout(editMessagePanel, BoxLayout.Y_AXIS));
        editScrollPane = new JScrollPane(editMessagePanel);
        editScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        editTextPanel = new JPanel();
        editTextBox = new JTextField(20);
        doneWithEdit = new JButton("Submit Change");
        deleteMessage = new JButton("Delete");

        //read each line of text form files
        BufferedReader bfr = new BufferedReader(new FileReader(m.getFile()));
        ArrayList<String> chat = new ArrayList<String>();
        messageButtons = new ArrayList<JButton>();

        while (true) {
            String line = bfr.readLine();
            if (line == null) {
                break;
            }
            chat.add(line);
        }
        bfr.close();

        //Handle all ActionListerners for user's interaction
        ActionListener actionListener = new ActionListener() {
            int index = -1;
            JButton button = null;
            boolean yourMessage = false;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == backFromEdit) {
                    editTopPanel.setVisible(false);
                    editTextPanel.setVisible(false);
                    editMessagePanel.setVisible(false);
                    editScrollPane.setVisible(false);
                    menuPanel.setVisible(true);
                    content.repaint();

                } else if (e.getSource() == doneWithEdit || e.getSource() == deleteMessage) {
                    if (index != -1 && yourMessage) {

                        //Change message
                        if (e.getSource() == doneWithEdit) {
                            String[] formattedLine = chat.get(index).split(",");
                            String editTextStr = editTextBox.getText();
                            editTextStr = editTextStr.replaceAll(",", "/");

                            //add edited message back to chat
                            String complete = formattedLine[0] + "," + editTextStr + "," + m.getTimeStamp() + " (Edited)";
                            chat.set(index, complete);

                            //rewrite all chats into the file
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(m.getFile());
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            PrintWriter pw = new PrintWriter(fos);
                            for (int i = 0; i < chat.size(); i++) {
                                pw.println(chat.get(i));
                            }

                            //Display edited message to user
                            editTextStr = editTextStr.replaceAll("/", ",");

                            if (formattedLine[0].equals(user.getUsername()) || formattedLine[0].equals(user.getFileUsername())) {
                                complete = "<You>" + " : " + editTextStr + " " + m.getTimeStamp() + " (Edited)";

                            } else {
                                String newUsername = formattedLine[0];
                                synchronized (o) {
                                    for (int i = 0; i < users.size(); i++) {
                                        if (users.get(i).getFileUsername().equals(formattedLine[0])) {
                                            newUsername = users.get(i).getUsername();
                                        }
                                    }
                                }
                                complete = "<" + newUsername + ">" + " : " + editTextStr + " " + m.getTimeStamp() + " (Edited)";

                            }
                            messageButtons.get(index).setText(complete);
                            content.repaint();

                            //Delete message
                        } else {

                            chat.remove(index);
                            messageButtons.get(index).setVisible(false);
                            messageButtons.remove(index);
                            content.repaint();
                        }

                        //rewrite chat after deleting message
                        FileOutputStream fos = null;

                        try {
                            fos = new FileOutputStream(m.getFile());
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        PrintWriter pw = new PrintWriter(fos);
                        for (int i = 0; i < chat.size(); i++) {
                            pw.println(chat.get(i));
                        }
                        pw.flush();
                        pw.close();

                        Message tempMessage;
                        for (int i = 0; i < m.getUsers().size(); i++) {
                            if (!m.getUsers().get(i).equals(user)) {
                                tempMessage = getMessageWithUserList(m.getUsers().get(i), m.getUsers());
                                try {
                                    tempMessage.addEditedMessage(user, chat);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }

                        }
                        index = -1;
                        yourMessage = false;
                    }

                    //if user try to edit/deleted message that is not their
                } else {
                    index = messageButtons.indexOf((JButton) e.getSource());
                    button = ((JButton) e.getSource());
                    String[] formattedLine = chat.get(index).split(",");
                    System.out.println(index + " the index");
                    if (formattedLine[0].equals(user.getFileUsername())) {
                        yourMessage = true;
                        editTextBox.setText(formattedLine[1]);
                        editTextPanel.setVisible(true);
                        editMessagePanel.setVisible(true);
                        content.repaint();

                    } else {
                        JOptionPane.showMessageDialog(null, "You do not have permission to edit this message!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        };

        editTopPanel.add(backFromEdit);
        editTopPanel.add(editHeader);

        editTextPanel.add(editTextBox);
        editTextPanel.add(doneWithEdit);
        editTextPanel.add(deleteMessage);

        doneWithEdit.addActionListener(actionListener);
        deleteMessage.addActionListener(actionListener);
        backFromEdit.addActionListener(actionListener);

        String res = "";
        for (int i = 0; i < chat.size(); i++) {
            String[] formattedLine = chat.get(i).split(",");
            formattedLine[1] = formattedLine[1].replaceAll("/", ",");
            if (formattedLine[0].equals(user.getFileUsername())) {
                res = "<You>" + " : " + formattedLine[1] + " " + formattedLine[2];

            } else {

                String newUsername = formattedLine[0];
                synchronized (o) {
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getFileUsername().equals(formattedLine[0])) {
                            newUsername = users.get(j).getUsername();
                        }
                    }
                }
                res = "<" + newUsername + ">" + " : " + formattedLine[1] + " " + formattedLine[2];
            }

            messageButtons.add(new JButton(res));
            messageButtons.get(i).setOpaque(false);
            messageButtons.get(i).setContentAreaFilled(false);
            messageButtons.get(i).setBorderPainted(false);
            messageButtons.get(i).addActionListener(actionListener);
            editMessagePanel.add(messageButtons.get(i));
        }

        content.add(editTopPanel, BorderLayout.NORTH);
        content.add(editScrollPane, BorderLayout.CENTER);
        content.add(editTextPanel, BorderLayout.SOUTH);

        return editScrollPane;
    }

    /**
     * Create GUI for editing the user's account and handle all user's interaction
     *
     * @param content
     * @param menuPanel
     * @return
     */
    public JPanel editAcc(Container content, JPanel menuPanel) {

        //Initialize and set formatting for GUI
        panelEdit = new JPanel();
        panelEdit.setLayout(new BoxLayout(panelEdit, BoxLayout.PAGE_AXIS));
        editPasswordPanel = new JPanel();
        editPasswordPanel.setLayout(new BoxLayout(editPasswordPanel, BoxLayout.Y_AXIS));
        editPasswordSubPanel = new JPanel();
        editUsernamePanel = new JPanel();
        editUsernamePanel.setLayout(new BoxLayout(editUsernamePanel, BoxLayout.Y_AXIS));
        editUsernameSubPanel = new JPanel();
        editFirstNamePanel = new JPanel();
        editFirstNamePanel.setLayout(new BoxLayout(editFirstNamePanel, BoxLayout.Y_AXIS));
        editFirstNameSubPanel = new JPanel();
        editLastNamePanel = new JPanel();
        editLastNamePanel.setLayout(new BoxLayout(editLastNamePanel, BoxLayout.Y_AXIS));
        editLastNameSubPanel = new JPanel();

        editAccHeader = new JLabel("Select fields to edit:");
        editAccHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        editAccHeader.setFont(new Font("Calibri", Font.BOLD, 20));

        editFirstName = new JButton("Edit First Name");
        editFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
        editFirstName.setPreferredSize(new Dimension(200, 80));
        editLastName = new JButton("Edit Last Name");
        editLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
        editLastName.setPreferredSize(new Dimension(200, 80));
        editUsername = new JButton(" Edit Username ");
        editUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        editUsername.setPreferredSize(new Dimension(200, 80));
        editAccBack = new JButton("Back");
        editPassword = new JButton(" Edit Password ");
        editPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        editPassword.setPreferredSize(new Dimension(200, 80));
        editAccBack = new JButton("        Back        ");
        editAccBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        editAccBack.setPreferredSize(new Dimension(200, 80));

        firstNameHeader = new JLabel("Change Your First Name:");
        firstNameHeader.setFont(new Font("Calibri", Font.BOLD, 17));
        firstNameHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lastNameHeader = new JLabel("Change Your Last Name:");
        lastNameHeader.setFont(new Font("Calibri", Font.BOLD, 17));
        lastNameHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameHeader = new JLabel("Change Your Username:");
        usernameHeader.setFont(new Font("Calibri", Font.BOLD, 17));
        usernameHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordHeaader = new JLabel("Change Your Password (Case-Sensitive):");
        passwordHeaader.setFont(new Font("Calibri", Font.BOLD, 17));
        passwordHeaader.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameText = new JTextField(10);
        passwordText = new JTextField(10);
        firstNameText = new JTextField(10);
        lastNameText = new JTextField(10);
        usernameText.setText(user.getUsername());
        passwordText.setText(user.getPassword());
        firstNameText.setText(user.getFirstName());
        usernameText.setText(user.getLastName());
        usernameConfirm = new JButton("Confirm");
        passwordConfirm = new JButton("Confirm");
        firstNameConfirm = new JButton("Confirm");
        lastNameConfirm = new JButton("Confirm");

        editFirstNameSubPanel.add(firstNameText);
        editFirstNameSubPanel.add(firstNameConfirm);
        editFirstNamePanel.add(firstNameHeader);
        editFirstNamePanel.add(editFirstNameSubPanel);

        editLastNameSubPanel.add(lastNameText);
        editLastNameSubPanel.add(lastNameConfirm);
        editLastNamePanel.add(lastNameHeader);
        editLastNamePanel.add(editLastNameSubPanel);

        editUsernameSubPanel.add(usernameText);
        editUsernameSubPanel.add(usernameConfirm);
        editUsernamePanel.add(usernameHeader);
        editUsernamePanel.add(editUsernameSubPanel);

        editPasswordSubPanel.add(passwordText);
        editPasswordSubPanel.add(passwordConfirm);
        editPasswordPanel.add(passwordHeaader);
        editPasswordPanel.add(editPasswordSubPanel);

        panelEdit.add(editAccHeader);
        panelEdit.add(editFirstName);
        panelEdit.add(editLastName);
        panelEdit.add(editUsername);
        panelEdit.add(editPassword);
        panelEdit.add(editAccBack);

        //Add ActionListener for User interaction
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == editFirstName) {
                    panelEdit.setVisible(false);
                    firstNameText.setText(user.getFirstName());
                    content.add(editFirstNamePanel, BorderLayout.CENTER);
                    editFirstNamePanel.setVisible(true);
                    content.repaint();
                }

                if (e.getSource() == editLastName) {
                    panelEdit.setVisible(false);
                    lastNameText.setText(user.getLastName());
                    content.add(editLastNamePanel, BorderLayout.CENTER);
                    editLastNamePanel.setVisible(true);
                    content.repaint();
                }

                if (e.getSource() == editUsername) {

                    panelEdit.setVisible(false);
                    usernameText.setText(user.getUsername());
                    content.add(editUsernamePanel, BorderLayout.CENTER);
                    editUsernamePanel.setVisible(true);
                    content.repaint();
                }

                if (e.getSource() == editPassword) {
                    panelEdit.setVisible(false);
                    passwordText.setText(user.getPassword());
                    content.add(editPasswordPanel, BorderLayout.CENTER);
                    editPasswordPanel.setVisible(true);
                    content.repaint();
                }

                if (e.getSource() == passwordConfirm) {

                    String password = passwordText.getText();

                    if (u.passwordValidation(password) && !password.contains(" ")) {

                        synchronized (o) {

                            user.setPassword(password);
                            try {
                                u.changeUserFile(users);
                                System.out.println(users.toString() + " Array after changing password");

                            } catch (IOException | DuplicateUserException ioException) {
                                ioException.printStackTrace();
                            }
                        }

                        System.out.println(users.toString() + "Password Changed");

                        editPasswordPanel.setVisible(false);
                        panelEdit.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Password should contain only alphabet characters and numbers and no space.\n"
                                        + "Please try again.",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                }

                ///if user change their username
                if (e.getSource() == usernameConfirm) {

                    String username = usernameText.getText();
                    username = username.toLowerCase();
                    boolean goOn = true;

                    //Check if entered username is already taken
                    synchronized (o) {
                        for (int i = 0; i < users.size(); i++) {
                            if (!users.get(i).equals(user)) {
                                if (users.get(i).getUsername().equals(username) ||
                                        users.get(i).getFileUsername().equals(username)) {

                                    JOptionPane.showMessageDialog(null,
                                            "This username is already taken\n" +
                                                    "Please try again.",
                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                    goOn = false;
                                }
                            }
                        }
                    }
                    System.out.println(username + " Entered username");

                    //validate the format of entered username
                    if (goOn && (!u.passwordValidation(username) || username.contains(" "))) {
                        JOptionPane.showMessageDialog(null,
                                "Username should contain only alphabet characters and numbers and no space.\n" +
                                        "Please try again.",
                                "ERROR", JOptionPane.ERROR_MESSAGE);

                    } else if (goOn) {

                        synchronized (o) {

                            try {
                                user.setUsername(username);
                                usernameDisplay.setText("Username: " + username);

                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            System.out.println(user.toString() + " User after changing username");

                            try {
                                u.changeUserFile(users);

                            } catch (IOException | DuplicateUserException ioException) {
                                ioException.printStackTrace();
                            }
                        }

                        System.out.println(users.toString() + "Username Changed");
                        editUsernamePanel.setVisible(false);
                        panelEdit.setVisible(true);
                        content.repaint();
                    }
                }

                //if user change their first name
                if (e.getSource() == firstNameConfirm) {
                    String firstName = firstNameText.getText();

                    if (!u.nameValidation(firstName) || firstName.contains(" ")) {

                        JOptionPane.showMessageDialog(null,
                                "First Name should contain only alphabet and no space.\n" +
                                        "Please try again.",
                                "Create New Account", JOptionPane.ERROR_MESSAGE);

                    } else {
                        user.setFirstName(firstName);
                        editFirstNamePanel.setVisible(false);
                        panelEdit.setVisible(true);
                        welcomeHeader.setText("Welcome "
                                + user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1) + " "
                                + user.getLastName().substring(0, 1).toUpperCase() + user.getLastName().substring(1) + "!");
                        content.repaint();
                    }
                }

                //if user change their last name
                if (e.getSource() == lastNameConfirm) {
                    String lastName = lastNameText.getText();

                    if (!u.nameValidation(lastName) || lastName.contains(" ")) {

                        JOptionPane.showMessageDialog(null,
                                "Last Name should contain only alphabet and no space.\n" +
                                        "Please try again.",
                                "Create New Account", JOptionPane.ERROR_MESSAGE);

                    } else {
                        user.setLastName(lastName);
                        editLastNamePanel.setVisible(false);
                        panelEdit.setVisible(true);
                        welcomeHeader.setText("Welcome "
                                + user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1) + " "
                                + user.getLastName().substring(0, 1).toUpperCase() + user.getLastName().substring(1) + "!");
                        content.repaint();
                    }
                }

                if (e.getSource() == editAccBack) {
                    panelEdit.setVisible(false);
                    menuPanel.setVisible(true);
                    content.repaint();
                }
            }
        };

        editFirstName.addActionListener(actionListener);
        editLastName.addActionListener(actionListener);
        editUsername.addActionListener(actionListener);
        editPassword.addActionListener(actionListener);
        firstNameConfirm.addActionListener(actionListener);
        lastNameConfirm.addActionListener(actionListener);
        usernameConfirm.addActionListener(actionListener);
        editPassword.addActionListener(actionListener);
        passwordConfirm.addActionListener(actionListener);
        editAccBack.addActionListener(actionListener);

        return panelEdit;
    }

    /**
     * This method find a specific message with users that is passed in
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
            if (messages.get(i).getUsers().containsAll(otherUsersList) && otherUsersList.containsAll(messages.get(i).getUsers())) {
                return messages.get(i);
            }
        }
        return new Message(newFile, otherUsersList, user);
    }

    /**
     * Create GUI for Import Message page and handle all user's interaction
     *
     * @param content
     * @param menuPanel
     * @return
     */
    public JPanel importMessage(Container content, JPanel menuPanel) {

        //Initialize and set formatting for the GUIs
        importPanel = new JPanel();
        importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.PAGE_AXIS));

        instructionTextForImport = new JLabel("Enter the name of the file to import");
        instructionTextForImport.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionTextForImport.setFont(new Font("Calibri", Font.BOLD, 20));

        interactPanel = new JPanel();
        fileNameBox = new JTextField(10);
        open = new JButton("Open");
        interactPanel.add(fileNameBox);
        interactPanel.add(open);

        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        importPanel.add(instructionTextForImport);
        importPanel.add(interactPanel);
        importPanel.add(back);

        //Add ActionListener for users' interaction
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == open) {
                    String fileName = fileNameBox.getText();
                    File f = new File(fileName);

                    for (int i = 0; i < user.getMessages().size(); i++) {
                        if (user.getMessages().get(i).getFile().getName().equals(f.getName())) {
                            JOptionPane.showMessageDialog(null, "Repeated File Name. You Cannot Import Existed File",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (!fileName.substring(fileName.length() - 4, fileName.length()).equals(".csv")) {
                        JOptionPane.showMessageDialog(null, "Invalid File Name! Please re-enter" +
                                " a csv file.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else if (!f.exists()) {
                        JOptionPane.showMessageDialog(null, "Invalid File Name! File Does Not Exist",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            u.importFile(user, users, fileName);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                } else if (e.getSource() == back) {
                    importPanel.setVisible(false);
                    menuPanel.setVisible(true);
                }
            }
        };
        open.addActionListener(actionListener);
        back.addActionListener(actionListener);

        return importPanel;
    }

} // end of GUI class


