package com.javaTraining;

import java.util.Random;
import java.util.Scanner;

public class numberGuessGame {
    public static void main(String[] args) {
        Random randomNumber = new Random();
        int randomNumberInt = randomNumber.nextInt(100);
        System.out.println(randomNumberInt);
        System.out.println("Guess random number: ");
        Scanner sc=new Scanner(System.in);
        int guess=sc.nextInt();
        while(randomNumberInt != guess){
            if(randomNumberInt < guess){
                System.out.println("YOU are greater than the guess");
                guess=sc.nextInt();
            }else{
                System.out.println("YOU are less than the guess");
                guess=sc.nextInt();
            }
        }
        System.out.println("YOUR guess is correct");
    }
}
