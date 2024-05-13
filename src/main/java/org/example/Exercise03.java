package org.example;

import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class Exercise03 {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        sayHelloWorld(2,5);
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
            combineHelloWithWorld.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();

        }

    } // end of static void sayHelloWorld(int secondsDelayHello, int secondsDelayWorld)
}
