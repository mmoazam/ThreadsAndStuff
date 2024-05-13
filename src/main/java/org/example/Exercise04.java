package org.example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Exercise04 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Random random = new Random();
        int helloDelay = random.nextInt(1,11);
        System.out.println("Hello delay: " + helloDelay);
        int worldDelay = random.nextInt(1,11);
        System.out.println("World delay: " + worldDelay);

        sayHelloWorld(helloDelay,worldDelay);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println(elapsedTime/ 1_000_000_000.0);

    }

    static void sayHelloWorld(int secondsDelayHello, int secondsDelayWorld)  {
        CompletableFuture<String> sayHello = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(secondsDelayHello);
            } catch (InterruptedException e){

            }
            return "Hello ";
        });

        CompletableFuture<String> sayWorld = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(secondsDelayWorld);
            } catch (InterruptedException e){

            }
            return "World";
        });

        CompletableFuture<String> combineHelloWithWorld =
                sayHello.thenCombine(sayWorld , ( s1,s2) -> s1 + s2);


        combineHelloWithWorld.thenAccept(p -> System.out.println(p));

        // add blocking code
        try {
            combineHelloWithWorld.get(10,TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();

        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    } // end of static void sayHelloWorld(int secondsDelayHello, int secondsDelayWorld)
}
