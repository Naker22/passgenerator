package com.steven;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        char[] upperCaseLetters = "ABCDEFGHYJKLMNOPQRSTUVWXYZ".toCharArray();
        char [] lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] digits = "01234567890".toCharArray();
        char[] symbols = "!@#$%^&*()_+=<>;:?/,".toCharArray();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter count of chars: ");
        byte countOfChars = scan.nextByte();
        while(countOfChars<=6) {
            System.out.print("Weak password chars must be more than 6. " +
                    "Your input is "+countOfChars+".\nEnter count of chars: ");
            countOfChars = scan.nextByte();
        }
        System.out.println("Your count of chars: "+ countOfChars);
        String[] types = {"upperCaseLetters","lowerCaseLetters","digits","symbols"};
        char []result= new char[countOfChars];
        int []checkForAllChars = new int[countOfChars];
        int []allOptionOfChars = {0,1,2,3};

        for(int i=0;i<countOfChars;i++){
            int indexType = (int)(Math.random()*4);
            checkForAllChars[i] = indexType;
            String type = types[indexType];
            if(type.equals(types[0])){
             result[i] = upperCaseLetters[(int)(Math.random()*upperCaseLetters.length)];
            }else if(type.equals(types[1])){
                result[i]= lowerCaseLetters[(int)(Math.random()*lowerCaseLetters.length)];
            }else if(type.equals(types[2])){
                result[i]= digits[(int)(Math.random()*digits.length)];
            }else{
                result[i] = symbols[(int)(Math.random()*symbols.length)];
            }
        }
        int changingIndex = countOfChars/2;
        System.out.println(changingIndex);
        result[changingIndex] = upperCaseLetters[(int)(Math.random()*upperCaseLetters.length)];
        changingIndex = (int)(countOfChars-(countOfChars/3));
        System.out.println(changingIndex);
        result[changingIndex] =lowerCaseLetters[(int)(Math.random()*lowerCaseLetters.length)];
        changingIndex = (int)(countOfChars-(countOfChars/3)-countOfChars/2);
        System.out.println(changingIndex);
        result[changingIndex]= digits[(int)(Math.random()*digits.length)];
        changingIndex = (int)(countOfChars-(countOfChars/3)+countOfChars/5);
        System.out.println(changingIndex);
        result[changingIndex] = symbols[(int)(Math.random()*symbols.length)];

        StringBuilder password = new StringBuilder();
        password.append(result);
        System.out.println("Your password is: "+password);
        boolean wrongInput = false;
        String valueInput;
        do {
            wrongInput = false;
            System.out.println("Write it to file(yes/no): ");
            valueInput = scan.next().toLowerCase();
            if (valueInput.equals("yes")) {
                while (true) {
                    boolean isDefault;
                    do {
                        System.out.println("Would you like to use default path(C:\\Users\\lEGION\\OneDrive\\Desktop\\pass.txt)?(yes/no)");
                        wrongInput = false;
                        valueInput = scan.next().toLowerCase();
                        if (valueInput.equals("yes")) {
                            isDefault = true;

                        } else if (valueInput.equals("no")) {
                            isDefault = false;
                        } else {
                            System.out.println("Wrong Input");
                            wrongInput = true;
                            isDefault = false;
                        }
                    }while(wrongInput);
                    String path = "C:\\Users\\lEGION\\OneDrive\\Desktop\\pass.txt";
                    if (!isDefault) {
                        System.out.println("Enter your path: ");
                        path = scan.next();
                    }


                    try (FileWriter file = new FileWriter(path, true)) {
                        System.out.println("Enter password appointment: ");
                        String passwordAppointment = scan.next();
                        file.append(passwordAppointment);
                        file.append(": ");
                        file.append(password);
                        file.append("\n");
                        file.flush();
                        System.out.println("You successfully write your password to: " + path);
                        System.out.println("Success!!!");
                        break;
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                        System.out.println("Check your path: ");
                    }
                }
            } else if (valueInput.equals("no")) {
                System.exit(0);
            }
            else wrongInput = true;
        }while (wrongInput);
        scan.close();
    }
}
