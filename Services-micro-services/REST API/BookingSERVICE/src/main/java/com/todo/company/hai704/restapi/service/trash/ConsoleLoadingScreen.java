//package com.todo.company.hai704.restapi.service.trash;
//
//public class ConsoleLoadingScreen {
//
//    public static void main(String[] args) {
//        // Simulate a task that takes some time
//        simulateTask();
//    }
//
//    private static void simulateTask() {
//        // Display a loading message
//        System.out.print("Loading ");
//
//        // Define a spinner animation sequence
//        String[] spinner = { "|", "/", "-", "\\" };
//
//        // Simulate a task with a loop
//        for (int i = 0; i < 20; i++) {
//            try {
//                // Sleep for a short duration to simulate work
//                Thread.sleep(200);
//
//                // Print the spinner character
//                System.out.print("\b" + spinner[i % spinner.length]); /// i*5+"%"); ///spinner[i % spinner.length]);
//                System.out.flush();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        // Print a completion message
//        System.out.println("\nTask completed!");
//    }
//}
