package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    //ArrayList<Item> items = new ArrayList<Item>();
    public static void main(String[] args) {
//        ArrayList<Item> items = new ArrayList<Item>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            boolean programIsRunning = true;

            while (programIsRunning) {
                System.out.print("""
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    } ////////////////////////////////////////////////////////////////////////////////////////////////////// end of main

    public static void displayDepositScreen() {
        System.out.print("Enter the amount you would like to deposit: ");
        double depositAmount = input.nextDouble();
        System.out.println(depositAmount);
    }

    public static void displayPaymentScreen() {
        System.out.println("Hello 2");
    }

    public static void displayLedgerScreen() {
        System.out.println("""
                Here is a list of options
                
                What would you like to do?
                A) All - Display all entries
                D) Deposits - Display only the entries that are deposits into the account
                P) Payments - Display only the negative entries (or payments)
                R) Reports - 
                H) Return to home screen
                
                Enter: 
                """);
        String ledgerScreenCommand = input.nextLine();
        ledgerScreenCommand = ledgerScreenCommand.toUpperCase();
        switch (ledgerScreenCommand) {
            case "A":
                displayAllLedgerEntries();
                break;
            case "D":
                displayLedgerDeposits();
                break;
            case "P":
                displayLedgerPayments();
                break;
            case "R":
                displayLedgerReports();
                break;
            case "H":
                returnToHomeScreen();
                break;
        }
    } /////////////////////////////////////////////////////////////////////////////////////// end of displayLedgerScreen

    public static void displayAllLedgerEntries() { ///// Bedtime stories
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            System.out.println("Press H to return");
            String whereAfterAllEntries = input.nextLine();
            whereAfterAllEntries = whereAfterAllEntries.toUpperCase();
            if (whereAfterAllEntries.equals("H")) {
                displayLedgerScreen();
            } else {
                displayAllLedgerEntries();
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayLedgerDeposits() {

    }

    public static void displayLedgerPayments() {

    }

    public static void displayLedgerReports() {

    }
    public static void returnToHomeScreen() {
        System.out.println("");
    }
//    public static ArrayList<Item> getItems() {
//        return new ArrayList<>();
//    }


}
