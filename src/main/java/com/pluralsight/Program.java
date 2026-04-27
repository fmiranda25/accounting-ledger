package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Transaction> transactions = getTransactions();
    public static void main(String[] args) {

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
        // get current date and time, enter name, item, and amount, write to csv
        System.out.print("Enter the amount you would like to deposit: ");
        double depositAmount = input.nextDouble();

        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + (int) depositAmount);
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }
        displayAllLedgerEntries();

    }

    public static void displayPaymentScreen() {
        // get current date and time, enter name, item, and amount, write to csv
        System.out.println("Enter the payment amount: ");
        double paymentAmount = input.nextDouble();
        System.out.println(paymentAmount);

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
    } /////////////////////////////////////////////////////////////////////////////////// end of displayAllLedgerEntries

    public static void displayLedgerDeposits() {

    }

    public static void displayLedgerPayments() {

    }

    public static void displayLedgerReports() {
        System.out.println("""
                Select which reports to show
                
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search By Vendor
                0) Back
                
                
                
                """);
        int ledgerReportsInput = input.nextInt();
        switch (ledgerReportsInput) {
            case 1:
                // month to date function
                break;
            case 2:
                // previous month function
            case 3:
                // year to date function
            case 4:
                // previous year function
            case 5:
                // check if equal to vendorName
            case 0:
                displayLedgerScreen();
                break;
        }

    }
    public static void returnToHomeScreen() {
        System.out.println("");
    }


    public static ArrayList<Transaction> getTransactions() {
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while(line != null) {
                String[] transactionAttributes = line.split("\\|");
                Transaction currentTransaction = new Transaction();


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return transactions;
    }


}
















