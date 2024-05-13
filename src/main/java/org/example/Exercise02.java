package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Exercise02 {

    public static void main(String[] args) {
        // Create two CompletableFutures whose tasks run concurrently: one printing
        // "Hello..." after 3 seconds, and the other printing "... World!" after 5 seconds.
        // Ensure they execute asynchronously.

       sayHelloWorldV1();
        System.out.println();
       sayHelloWorldV2(2,3);
        System.out.println();
       sayHelloWorldV2(3,3);
        System.out.println();
       sayHelloWorldV2(3,2);

    } // end of main

    static void sayHelloWorldV1 (){
        CompletableFuture printHello = CompletableFuture.runAsync(() -> {
            try {
                // print hello after 3 seconds
                TimeUnit.SECONDS.sleep(3);
                System.out.print("Hello ");

            } catch ( InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException();
            }
        });

        CompletableFuture printWorld = CompletableFuture.runAsync(() -> {
            try {
                // print hello after 3 seconds
                TimeUnit.SECONDS.sleep(5);
                System.out.print("World");

            } catch ( InterruptedException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException();
            }
        });

        printHello.join();
        printWorld.join();
    }

    static void sayHelloWorldV2(int hello, int world){
        CompletableFuture sayHello = CompletableFuture.runAsync(() -> {
           try {
                TimeUnit.SECONDS.sleep(hello);
           } catch (InterruptedException e){

           }
        }).thenAccept(s -> System.out.print(" Hello "));

        CompletableFuture sayWorld = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(world);
            } catch (InterruptedException e){

            }
        }).thenAccept(s -> System.out.print("Version 2, World!"));

        CompletableFuture sayHelloWorld = CompletableFuture.allOf(sayHello, sayWorld);

        sayHelloWorld.join();
    }

} // end of class
