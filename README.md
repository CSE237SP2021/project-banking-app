# cse237-project - Banking App

## Authors
* Thomas Cornell
* Evan Molyneaux
* Jason Ren

## Iteration I Release
* Upon running the program, the user is prompted to input a command, or type help to see the available commands. The supported commands are as follows:
  * Deposit: deposit money into your account
  * Withdraw: take money from the account
  * Balance: view the current balance of the account
  * Exit: exit the program
* While this appears to be quite simple, there were extensive efforts made behind the scenes to make this iteration scalable in order to increase efficiency of development later o to support features we'd eventually like to implement. When the program begins, an instance of the Person class is automatically created, an instance of the Account class is added to that Person, and then that Account is passed to an AccountsMenu instance. We'd eventually like to support multiple users and persistent storage, which our code could easily support later on.
