# cse237-project - Banking App

## Authors

-   Thomas Cornell
-   Evan Molyneaux
-   Jason Ren

## Iteration I Release

-   Upon running the program, the user is prompted to input a command, or type help to see the available commands. The supported commands are as follows:
    -   Deposit: deposit money into your account
    -   Withdraw: take money from the account
    -   Balance: view the current balance of the account
    -   Exit: exit the program
-   While this appears to be quite simple, there were extensive efforts made behind the scenes to make this iteration scalable in order to increase efficiency of development later o to support features we'd eventually like to implement. When the program begins, an instance of the Person class is automatically created, an instance of the Account class is added to that Person, and then that Account is passed to an AccountsMenu instance. We'd eventually like to support multiple users and persistent storage, which our code could easily support later on.

## What user stories were completed this iteration?

-   Made an Account class to represent a user's bank account
-   Made a Person class to represent a person who can have multiple bank accounts
-   Made an AccountsMenu class that provides the functionality for the user to interact with their bank accounts
-   Made a Main class which acts as the entry point to our program. Currently this uses hardcoded Person, AccountsMenu, and Account objects to showcase our program

## What user stories do you intend to complete next iteration?

-   For the next iteration we plan to expand upon the available commands within the AccountsMenu class. This may include being able to switch the current account, adding new accounts, deleting accounts, etc. Additionaly we would like to start working on a GUI for our program to provide a better user experience

## Is there anything that you implemented but doesn't currently work?

-   No, for this iteration we made sure to layout the foundation for our project using object oriented principles to make sure adding functionality in later iterations will be a smooth process.

## What commands are needed to compile and run your code from the command line?

-   To run our program first switch to the development branch in your command line. Then run the run.sh script by typing in "./run.sh". After you should be greated with a welcome message and you may begin interacting with your bank account using the commands described above.
