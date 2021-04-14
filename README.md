# cse237-project - Banking App

## Authors

-   Thomas Cornell
-   Evan Molyneaux
-   Jason Ren

## Iteration II Release

-   Upon running the program, the user is prompted to either login or register with the bank. After successfully logging in, they are prompted to either create an account, or select an existing an account with the bank (we were envisioning that a user might have several different accounts, such as savings and checkings). After selecting an account, the user can interact with their account with the following commands
    -   Deposit: deposit money into your account
    -   Withdraw: take money from the account
    -   Balance: view the current balance of the account
    -   Transactions: view a transaction history.
    -   Exit: return to the account selection menu.

## What user stories were completed this iteration?

-   A user should be able to register.
-   A user should be able to login.
-   A user should be able to create new bank accounts.
-   A user should be able to switch bank accounts.
-   A user should be able to see a log of their transaction history.
-   A user should be able to see their data from a previous session (as mentioned below, this is mostly complete -- the only thing that isn't fully supported is transaction history).

## What user stories do you intend to complete next iteration?

-   We would like to work on a GUI, finish the persistent storage to work with the transaction history feature, as well as support other features such as transferring funds or deleting accounts.

## Is there anything that you implemented but doesn't currently work?

-   The transaction history feature currently does not persist between program runs. It will work fine as long as you don't close the program -- however, once you restart it, the transaction history will be wiped.

## What commands are needed to compile and run your code from the command line?

-   To run our program first switch to the development branch in your command line. Then run the run.sh script by typing in "./run.sh". After you should be greated with a welcome message and you may begin interacting with your bank account using the commands described above.
-   NOTE: There is a known issue with the bash script. If you get the error: "/bin/bash^M: bad interpreter: No such file or directory" please run the following commands instead:
    -   cd BankingApp/src/main
    -   javac -cp ../ Main.java
    -   cd ..
    -   java main.Main
-   The problem appears to be due to the difference in Linux and Windows line breaks. The script was written on a Mac and the error occurs on Windows. We hope to have this fixed by the next iteration.
