package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        String data = "85671 34262 92143 50984 24515 68356 77247 12348 56789 98760";
        ArrayList<BigInteger> bigIntegers = getIntegerListfromStringArray(data);
        long startTime = System.nanoTime();

        CompletableFuture<Void> allFactorialsFuture = calculateFactorialsAsync(bigIntegers)
                .thenAccept(result -> {
                    System.out.println("Factorials " + result);
                });

        allFactorialsFuture.join();
        // calculateFactorialsAsync(bigIntegers);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time using Async " + elapsedTime / 1_000_000_000.0);

        startTime = System.nanoTime();
        factorialTransformationForLoop(bigIntegers);
        elapsedTime =( System.nanoTime() - startTime);
        System.out.println("Time using for loop " + elapsedTime/1_000_000_000.0);
    } // end of main

    static CompletableFuture<List<BigInteger>> calculateFactorialsAsync(ArrayList<BigInteger> bigIntegers) {
       return CompletableFuture.supplyAsync(() -> {
          return bigIntegers.stream()
                  .map(Main::calculateFactorial)
                  .toList();
       });
    }

    static void factorialTransformationForLoop(ArrayList<BigInteger> bigIntegers) {

        for (BigInteger bi : bigIntegers){
            var answer = calculateFactorial(bi);
            System.out.println(answer);
        }
    }

    private static ArrayList<BigInteger> getIntegerListfromStringArray(String data) {
        String[] intStringArray = data.split(" ");
        ArrayList<BigInteger> bigIntegers = new ArrayList<>();
        BigInteger integer;
        for (String s : intStringArray) {
            try {
                integer = BigInteger.valueOf(Integer.parseInt(s));
                bigIntegers.add(integer);
            } catch (IllegalArgumentException e) {
                e.getStackTrace();
            }
        }
        return bigIntegers;
    }


    private static BigInteger calculateFactorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }


}