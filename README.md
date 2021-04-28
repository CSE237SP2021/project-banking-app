# cse237-project - Banking App

## Authors

-   Thomas Cornell
-   Evan Molyneaux
-   Jason Ren

## Iteration III Release

-   Upon running the program, the user is prompted to either login or register with the bank. After successfully logging in, they are prompted to either create an account, or select an existing an account with the bank (we were envisioning that a user might have several different accounts, such as savings and checkings). After selecting an account, the user can interact with their account with the following commands
    -   Deposit: deposit money into your account
    -   Withdraw: take money from the account
    -   Balance: view the current balance of the account
    -   Transactions: view a transaction history.
    -   Exit: return to the account selection menu.
    -   NewAccount: creates a neww account with the specified name
    -   SelectAccount: Select the specified account to interact with (deposit,withdraw, etc)
    -   DeleteAccount: deletes the specified account.
    -   Transfer: transfer money between 2 existing accounts.

## What user stories were completed this iteration?

-   A user should be able to transfer money between 2 of their existing accounts
-   A user should be able to see their data from a previous session. This was worked on during iteration 2 but finished during iteration 3
-   A user should be able to delete an account. Also deletes the folder containing the account info

## Is there anything that you implemented but doesn't currently work?

-   We attempted to add functionality for transferring money between different people's accounts. However, due to the way our code is organized, we couldn't achieve that without violating OOP/clean code principles or rewriting our entire code base. We felt it would be better to focus our manpower on fleshing out other features.

## What commands are needed to compile and run your code from the command line?

-   To run our program first switch to the development branch in your command line. Then run the run.sh script by typing in "./run.sh". After you should be greated with a welcome message and you may begin interacting with your bank account using the commands described above.
