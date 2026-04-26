package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean programIsRunning = true;

        while (programIsRunning) {
            System.out.println("""
                    Welcome To The Accounting Ledger
                    
                    What would you like to do?
                    D). Add deposit
                    P). Make payment
                    L). Display the ledger
                    X). Exit the application
                    
                    Enter:
                    """);
            String command = input.nextLine();
            command = command.toUpperCase();
            switch (command) {
                case "D":
                    displayDepositScreen();
                    break;
                case "P":
                    displayPaymentScreen();
                    break;
                case "L":
                    displayLedgerScreen();
                    break;
                case "X":
                    programIsRunning = false;
                    break;
            }

        }
    } ////////////////////////////////////////////////////////////////////////////////////////////////////// end of main

    public static void displayDepositScreen() {
        System.out.println("Hello 1");
    }

    public static void displayPaymentScreen() {
        System.out.println("Hello 2");
    }

    public static void displayLedgerScreen() {
        System.out.println("Hello 3");
    }


}
