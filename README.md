# CS180-Project-5-IMPROVEDSocialPlatform
## Submission 
Yagmur Onder - Submitted Report on Brightspace.  
  
Mahad Faruqi - Submitted Vocareum workspace.

## Overview
### The core target of project Social Platform (Option 3) is to create a Java program that permits different users to message each other. Users have the option to message 1 or more users, edit their account, edit their conversations, import a conversation or exit the program. File Input/Output techniques are also utilized widely to make sure data can persist even when the program shuts down or the user(s) is offline. As a social platform, each user who has existing account can message different accounts on the platform (identified as their friend list) or edit a message that has already been sent. 

### For organization and readability of the project, there are 6 classes ()
 
 
 
## 1. Message class
#### The message class contains methods mainly handling the process and save functions of each Message, which are saved and read in proper formation, under certain user who sends it.
### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |      :---  |      :---  |
| user | Users   | private    |   individual user   |
| users     | ArrayList<User>       | private      |   ArrayList of users on platform    |
| file | File   | private    |    existing file for saving messages        |


### Constructor
  
  | Parameters | Modifier |
  | :--- | :--- |
  | File file, ArrayList<User> users1, User user | public |
 
 Add the elements of Arraylist users1 to users ArrayList. 
  
### Methods
  
 
  | Name	 | Return Type	| Parameters | Modifier | Description |
  | :--- | :--- | :--- | :---| :---|
  | getTimestamp | String | void | public | returns timestamp for message sent |
  | addUser | void  | User user | public| add user to  ArrayList<User> users |
  | getFile | File | none | public |  return file |
  | printConvo | void | none | public | process the data file and return it as a String with buffedreader  |
  | printConvoLines | void | none | public | process the data file and return it as a String in mutiple lines with buffedreader |
  | addMessage | void | User user, String message | public | save the message input into user file under certain user in format USERNAME,MESSAGE,TIMESTAMP |
  | toString | String | ArrayList<User> users | public | adds usernames in users Arraylist to formatted string |
  | getUsers | ArrayList<User> | none | public | return users  |
 
## 2. DuplicateUserException class
### Constructor
  
  | Constructor | Description |
  | :--- | :--- |
  | DuplicateUserException() | throws exception when one attempts to create account with username that already exists; extends Exception |
 
## 3. User class
#### User class initialize the information of every registered user and several methods to return the needed information of one user.
  ### Fields
| Name | Type | Modifier | Description |
|  :---      |     :---   |         :--- |  :---  |
| username | String   | private    |   account username        |
| password     | String       | private      |   account password       |
| currentlyMessaging   | ArrayList<Message>    | private      |    current existing messages Arraylist    |
| messagingNames | File  | private    |    message csv file for each user     |
 
### Constructor
  | Parameters | Modifier |
  | :--- | :--- |
  | String username, String password | public |
 
### Methods
  | Name	 | Return Type	| Parameters | Modifier | Description |
  | :--- | :--- | :--- | :---| :---|
  | getUsername | String | none | public | return username|
  | getPassword | String | none | public | return password |
  | addMessage | void | Message m, String filename, boolean addToFile | public | add a new message conversation to the message array |
  | addUser | ArrayList<User> | File userFile, User user, ArrayList<User> users | public | adds a user to the users array list and it also writes to the file and stop when catches ducplicate username |
  | equals | boolean | Object o | public | check if certain user has same username as current one |
  | toString | String | none |  public | prints username and password |
  | getMessagingNames | File | none | public | return messagingNames|
  | setUsername | void | String username | public | set username |
  | setPassword | void | String password | public | set password   |
  | addMessage | void | Message m | public | adds message to currentlyMessage ArrayList   |
  | getMessages | ArrayList<Message> | none |  public  | return currentlyMessaging (messages in ArrayList)| 
 
  ## 4. Utils class
 #### Utils class contains important methods that are frequently used by other objects through the whole project.
  ### Methods
 | Name	 | Return Type	| Parameters | Modifier | Description |
 | :--- | :--- | :--- | :---| :---|
 | loginOrSignUp | int | none | public |  ask the user if they want to login or sign up and return their choice as integer |
 | reLogInOrSignUp | void | ArrayList<User> users |  public | log in / sign up menu for after starts running with list of users in ArrayList; throws FileNotFoundException, IOException, DuplicateUserException | 
 | menuForOneUser | void | User user, ArrayList<User> users | public |  process the input choice of menu shown when there is one existing user |
 | menu | void | User user, ArrayList<User> users |  public | process the input choice of menu shown when there are more than one existing users |
 | editMessages | void | User user, ArrayList<User> users | public | edit or delete message determined by what line user selects, updates changes to files |
 | editAccount | void | User user, ArrayList<User> users | public | edit the user name or password of the current account  |
 | signUp | ArrayList<User> | File userFile, ArrayList<User> users | public | create and save new user  |
 | logIn | ArrayList<User> | File userFile, ArrayList<User> users | public | log in to already-made account   |
 | changeUserFile | void | ArrayList<User> users | public | change the information of user on how they edited their account (change username, change password, delete account)  |
 | parseUsers | ArrayList<User> | File userFile, ArrayList<User> users | public | parses the users from userFile |
 | parseMessage | ArrayList<User> | ArrayList<User> users, User user | public | parse messages of a user  |
 | findDuplicate | User | ArrayList<User> users, User user1 | public | find the potential duplicate user |
 | showFriends | void | User user, ArrayList<User> users | public | shows other existing users |
 | showExistingFriends | void | User user, ArrayList<User> users | public | shows users with precious chat record  |
 | existingChat | void | User user, ArrayList<User> users | public | print the contact list, process the input option to send one or more user messages and save those messages sent  |
 | createConvo | void | User user, ArrayList<User> users | public | creates new conversation; notifies user when they want to view existing chats if no previous chat exists  |
 | getNumber | int | Scanner in, String prompt, int min, int max | public | robust input for integer. If user does not type an integer, it tells them to put in an integer with accurate boundaries  |
 | getMatch | String | Scanner in, String prompt, String match | public | checks if password is valid when logging in |
 | importFile | void | User currentUser, ArrayList<User> userList | public | user imports CSV file and file updates conversations |
 | getMessageWithUserList | Message | User currentUser, ArrayList<User> otherUsersList | public | returns new Message object with file, users list (with removed current user), and current user |
 


