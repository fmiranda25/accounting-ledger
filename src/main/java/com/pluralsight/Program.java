package com.pluralsight;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
    static LocalDate today = LocalDate.now();
    static Scanner input = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();
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
                R) Reports - Display reports by month, year, or vendor
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
        for (Transaction transaction : transactions) {
            printTransactions(transaction);
        }
        promptHomeScreen();
    }

    public static void displayLedgerDeposits() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
    }

    public static void displayLedgerPayments() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
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
                
                Enter: 
                """);
        int ledgerReportsInput = input.nextInt();
        input.nextLine();
        switch (ledgerReportsInput) {
            case 1:
                displayMonthToDate();
                break;
            case 2:
                displayFromPreviousMonth();
                break;
            case 3:
                displayYearToDate();
                break;
            case 4:
                displayFromPreviousYear();
                break;
            case 5:
                displayByVendor();
                break;
            case 0:
                displayLedgerScreen();
                break;
        }
    }

    public static void displayMonthToDate() {
        LocalDate currentMonthStart = today.withDayOfMonth(1);
        LocalDate currentMonthEnd = today.with(TemporalAdjusters.lastDayOfMonth());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().compareTo(String.valueOf(currentMonthStart)) >= 0 && transaction.getDate().compareTo(String.valueOf(currentMonthEnd)) <= 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
    }

    public static void displayFromPreviousMonth() {
        LocalDate previousMonthStart = today.minusMonths(1).withDayOfMonth(1);
        LocalDate previousMonthEnd = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().compareTo(String.valueOf(previousMonthStart)) >= 0 && transaction.getDate().compareTo(String.valueOf(previousMonthEnd)) <= 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();

    }

    public static void displayYearToDate() {
        LocalDate currentYearStart = today.withDayOfYear(1);
        LocalDate currentYearEnd = today.with(TemporalAdjusters.lastDayOfYear());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().compareTo(String.valueOf(currentYearStart)) >= 0 && transaction.getDate().compareTo(String.valueOf(currentYearEnd)) <= 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
    }

    public static void displayFromPreviousYear() {
        LocalDate previousYearStart = today.minusYears(1).withDayOfYear(1);
        LocalDate previousYearEnd = today.minusYears(1).with(TemporalAdjusters.lastDayOfYear());

        for (Transaction transaction : transactions) {
            if (transaction.getDate().compareTo(String.valueOf(previousYearStart)) >= 0 && transaction.getDate().compareTo(String.valueOf(previousYearEnd)) <= 0) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
    }

    public static void displayByVendor() {

        System.out.println("Enter vendor name: ");
        String allVendorEntries = input.nextLine();

        for (Transaction transaction : transactions) {
            if (allVendorEntries.equals(transaction.getVendorName())) {
                printTransactions(transaction);
            }
        }
        promptHomeScreen();
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
                transactions.sort(Comparator.comparing(Transaction::getDate));
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static void printTransactions(Transaction transaction) {

        System.out.printf("                                                  ||%15s %15s %25s %25s %25s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getItemDescription(),
                    transaction.getVendorName(),
                    transaction.getAmount()
                            + "     ||\n");
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
