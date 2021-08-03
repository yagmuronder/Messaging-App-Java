
# CS180-Project-5- Test Cases

## Test 1 - User Sign-Up & Log-In

Steps: 
1.	User launches application (runs main in MessengerServer, then runs main in MessengerClient)
2.	User selects “Sign up” button
3.	User enters username via the keyboard into textbook and hits “OK” button
4.	User enters password via the keyboard into textbook and hits “OK” button
5.	User enters first name via the keyboard into textbook and hits “OK” button
6.	User enters last name via the keyboard into textbook and hits “OK” button
7.	JOptionPane saying “Your new account has been created!” with name, username, and password information. User clicks “OK”
8.	User selects “Logout” button, is asked confirmation if they want to log out and user selects “Yes”
9.	User runs main in MessengerClient again 
10.	User selects “Login” button
11.	User enters username, selects “OK”, then enters password, selects “OK”

Expected result: JOptionPane with “Login Successfully!” pops up. Once “OK” button is selected, main menu with options Edit Account, Message, Import Conversation, and Logout are shown. 

Test Status: Passed

## Test 2 - Two Users Message Each Other (Also: Editing Messages)

Steps:
1.	User 1 selects “Message” button. Selects “Create new chat”
2.	User 1 selects User 2 button under “Your List of Friends”. Then selects “Start Chat with Selected User(s)”. If no users are selected, error message as JOptionPane will pop up. 
3.	User 1 sends “Hi” message by typing in textbox then clicking on the “Send” button. Message for User 1 should appear as “<You> : Hi *TimeStamp of message sent*”
4.	User 2 selects “Message” button from main menu. 
5.	User 2 selects “Chat 1: User 1 Username” button underneath “Selection a Conversation: “
6.	User 2 can see message that User 1 sent as “<User 1 Username> Hi *Timestamp of message sent*” 
7.	User 2 sends “Hello” message by typing in textbox then clicking on the “Send” button. Message for User 2 should appear as “<You> : Hello *TimeStamp of message sent*”
8.	User 2 selects “Edit” button. Expected: Chat format with the “<User> : Message *TimeStamp of message sent*”. If any user selects a message that they did not send, error message pops up. 
9.	User 2 selects their “Hello” message and types in “Hello User” in textbox and selects “Submit Change” button. Expected result: “<You> : Hello User *TimeStamp of message sent* (Edited)”. No messages shows (user & timestamp still shows, however) if message is erased. 
10.	Note: messages are refreshed either when user sends message on their own account or user selects “Back” button and re-enters previous chat the same way User 2 entered chat (under “Select a Conversation: ”).

Test Status: Passed 

## Test 3 - Import Conversation Details
  
Steps:
1. Open previously made conversation (EX: User1UsernameUser2Username.csv) through TextEdit
2. Edit messages or add messages or delete messages with format <User>,Message,TimeStamp
3. Select "Import Conversation" button on Main Menu
4. Type in file name (EX: User1UsernameUser2Username.csv) and select "Open" button

Expected result: Changes made to respective conversation. 
  
Test Status: Passed

## Test 4 - Delete Conversations 
  

## Test 5 - Delete Account 
  
