package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();
//    static LocalDate currentDate = LocalDate.now();
//    static LocalTime currentTime = LocalTime.now();

    public static void main(String[] args) {
        transactions = getTransactions();

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

    } ////////////////////////////////////////////////////////////////////////////////////////////////////// end of main

    public static void displayDepositScreen() {
        Transaction newTransaction = new Transaction();
        System.out.print("Enter the amount you would like to deposit: ");
        newTransaction.setAmount(input.nextDouble());

        input.nextLine();
        System.out.println("Enter the deposit category: ");
        newTransaction.setItemDescription(input.nextLine());

        System.out.println("Enter the deposit type: ");
        newTransaction.setVendorName(input.nextLine());

        newTransaction.setDate(getCurrentDate());
        newTransaction.setTime(getCurrentTime());

        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" +
                    newTransaction.getDate() + "|" +
                    newTransaction.getTime() + "|" +
                    newTransaction.getItemDescription() + "|" +
                    newTransaction.getVendorName() + "|" +
                    newTransaction.getAmount());
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }

        transactions.add(newTransaction);
        System.out.println("Deposit saved.");
        promptHomeScreen();




    } ////////////////////////////////////////////////////////////////////////////////////// end of displayDepositScreen

    public static void displayPaymentScreen() {
        Transaction newTransaction = new Transaction();
        System.out.print("Enter the amount you would like to pay: ");
        newTransaction.setAmount(input.nextDouble() * -1);

        input.nextLine();
        System.out.println("Enter the name of the item: ");
        newTransaction.setItemDescription(input.nextLine());

        System.out.println("Enter the name of the vendor: ");
        newTransaction.setVendorName(input.nextLine());

        newTransaction.setDate(getCurrentDate());
        newTransaction.setTime(getCurrentTime());


        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" +
                    newTransaction.getDate() + "|" +
                    newTransaction.getTime() + "|" +
                    newTransaction.getItemDescription() + "|" +
                    newTransaction.getVendorName() + "|" +
                    newTransaction.getAmount());
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }

        transactions.add(newTransaction);
        System.out.println("Payment saved.");
        promptHomeScreen();

    } ////////////////////////////////////////////////////////////////////////////////////// end of displayPaymentScreen

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

    public static void displayAllLedgerEntries() {
        printTransactions(transactions);
    }

    public static void displayLedgerDeposits() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | %s",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getItemDescription(),
                        transaction.getVendorName(),
                        transaction.getAmount()
                                + "\n");
            }
        }

    }

    public static void displayLedgerPayments() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.printf("%s | %s | %s | %s | %s",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getItemDescription(),
                        transaction.getVendorName(),
                        transaction.getAmount()
                                + "\n");
            }
        }

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
                displayMonthToDate();
                break;
            case 2:
                displayFromPreviousMonth();
            case 3:
                displayYearToDate();
            case 4:
                displayFromPreviousYear();
            case 5:
                displayByVendor();
            case 0:
                displayLedgerScreen();
                break;
        }

    }

    public static void displayMonthToDate() {
        // month to date function
    }

    public static void displayFromPreviousMonth() {
        // previous month function
    }

    public static void displayYearToDate() {
        // year to date function
    }

    public static void displayFromPreviousYear() {
        // previous year function
    }

    public static void displayByVendor() {
        // check if equal to vendorName
    }


    public static ArrayList<Transaction> getTransactions() {
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean checkFirstLine = true;
            while((line = bufferedReader.readLine()) != null) {
                if (checkFirstLine) {
                    checkFirstLine = false;
                    continue;
                }
                String[] transactionAttributes = line.split("\\|");
                Transaction currentTransaction = new Transaction();
                currentTransaction.setDate(transactionAttributes[0]);
                currentTransaction.setTime(transactionAttributes[1]);
                currentTransaction.setItemDescription(transactionAttributes[2]);
                currentTransaction.setVendorName(transactionAttributes[3]);
                currentTransaction.setAmount(Double.parseDouble(transactionAttributes[4]));
                transactions.add(currentTransaction);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static void printTransactions(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.printf("%s | %s | %s | %s | %s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getItemDescription(),
                    transaction.getVendorName(),
                    transaction.getAmount()
                            + "\n");
        }
    }

    public static void promptHomeScreen() {
        System.out.println("H) Return to home screen");
        String returnToHome = input.nextLine();
        returnToHome = returnToHome.toUpperCase();
        switch (returnToHome) {
            case "H":
                returnToHomeScreen();
        }
    }
    public static void returnToHomeScreen() {
        System.out.println("");
    }

    public static String getCurrentTime() {
        LocalDateTime unformattedTime = LocalDateTime.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        return unformattedTime.format(time);
    }

    public static String getCurrentDate() {
        LocalDateTime unformattedDate = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return unformattedDate.format(date);
    }
}
















