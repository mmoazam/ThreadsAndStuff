package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String data = "85671 34262 92143 50984 24515 68356 77247 12348 56789 98760";
        ArrayList<Integer> integers = getIntegerListfromStringArray(data);




    } // end of main

    private static ArrayList<Integer> getIntegerListfromStringArray(String data) {
        String[] intStringArray = data.split(" ");
        ArrayList<Integer> ints = new ArrayList<>();
        int integer = 0;
        for (String s : intStringArray){
            try {
               integer = Integer.parseInt(s);
                ints.add(integer);
            } catch (IllegalArgumentException e){
                e.getStackTrace();
            }
        }
        return ints;
    }


    private static BigInteger calculateFactorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }


}