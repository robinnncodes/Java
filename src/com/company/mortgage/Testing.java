package com.company.mortgage;
/*
 * principal: 100,000
 * annual interest rate: 3.92 --> / 100(turn to percent) / 12 (monthly)
 * period 30 yrs (x 12 to get numberPayments monthly)
 * M = principal * ((annualRate * Math.pow(1+annualRate, payments)
 *                  / (Math.pow(1+annualRate, payments) - 1)
 */

import java.text.NumberFormat;
import java.util.Scanner;

public class Testing {
    final static int MONTHS = 12;
    final static int PERCENT = 100;
    public static void main(String[] args) {
        int userPrincipal;
        float userRate;
        int years;

        userPrincipal = (int) readInputs("Principal: ", 1000, 1_000_000);
        userRate = (float) readInputs("Annual Interest Rate: ", 1, 30);
        years = (int) readInputs("Period(years): ", 1, 30);

        printMonthlyPayments(userPrincipal, userRate, years);
        printPayments(userPrincipal, userRate, years);
    }

    private static void printMonthlyPayments(int userPrincipal, float userRate, int years) {
        double mortgage = calcMortgage(userPrincipal, userRate, years);
        System.out.println("Monthly payments: " +
                NumberFormat.getCurrencyInstance().format(mortgage));
        System.out.println();
    }

    private static void printPayments(int userPrincipal, float userRate, int years) {
        System.out.println("Payment Schedule: " );
        System.out.println("----------------");
        calcPayments(userPrincipal, userRate, years);
    }

    public static double readInputs (String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double userInput;

        while(true) {
            System.out.println(prompt);
            userInput = scanner.nextDouble();
            if (userInput > min && userInput < max) {
                break;
            } else {
                System.out.printf("enter digit between %d and %d\n", min, max);
            }
        }
        return userInput;
    }

    public static void calcPayments (int userPrincipal, float userRate, int years) {
        float rate = userRate / PERCENT / MONTHS;
        int numOfPayments = years * MONTHS;
        int paymentsMade = 0;
        for (int monthsLeft = numOfPayments; monthsLeft >=0; monthsLeft-- ) {
            double loanBalance =  ((userPrincipal * (Math.pow(1+rate, numOfPayments) - Math.pow(1+rate, paymentsMade)))
                    / (Math.pow(1+rate, numOfPayments) - 1)
            );
            paymentsMade++;
            System.out.println(NumberFormat.getCurrencyInstance().format(loanBalance));
            if (monthsLeft == 0) {
                System.out.println("all paid for!");
            }
        }
    }

    public static double calcMortgage (int userPrincipal, float userRate, int years) {
        float rate = userRate / PERCENT / MONTHS;
        int numOfPayments = years * MONTHS;

        return userPrincipal *
                (rate * (Math.pow(1 + rate, numOfPayments))
                        / (Math.pow(1 + rate, numOfPayments) - 1)
                );
    }
}

