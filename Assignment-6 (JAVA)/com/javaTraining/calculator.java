package com.javaTraining;

import java.math.BigInteger;
import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger result = BigInteger.valueOf(0);
        while (true) {
            System.out.println("CHOOSE YOUR OPTION:\n" +
                    "+ : ADDITION,\n" +
                    "- : SUBSTRACTION,\n" +
                    "* : MULTIPLICATION,\n" +
                    "/ : DIVISION,\n" +
                    "% : Modulus, \n" +
                    "C : CLOSE THE CALCULATOR. ");
            char option = sc.next().charAt(0);
            if(option=='c' || option=='C'){
                break;
            }
            System.out.println("Enter first number ");
            BigInteger number1 = sc.nextBigInteger();
            System.out.println("Enter second number ");
            BigInteger number2 = sc.nextBigInteger();

            switch (option) {
                case '+':
                    result = number1.add(number2);
                    break;
                case '-':
                    result = number1.subtract(number2);
                    break;
                case '*':
                    result = number1.multiply(number2);
                    break;
                case '/':
                    result = number1.divide(number2);
                    break;
                case '%':
                    result = number1.mod(number2);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if(option == '+' || option == '-' || option == '*' || option == '/' || option == '%') {
                System.out.println(result);
            };

        }
        System.out.println("Calculation closed.");
    }
}
