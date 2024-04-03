//package com.todo.company.hai704.restapi.service.trash;
//
//public class StringCuration {
//
//    public static String ensureSingleSlash(String inputString) {
//        // Remove trailing slashes and spaces
//        inputString = inputString.replaceAll("[/\\s]+(?=:\\d{1025,65531}$)|[/\\s]+$", "");
//
//        // Add a single slash at the end
//        return inputString + "/";
//    }
//
//    public static void main(String[] args) {
//        // Example usage:
////        String inputString1 = "example////path333553";
////        String inputString2 = "another/example:12345551356";
////        String inputString3 = "trailing/space   ";
////
////        String result1 = ensureSingleSlash(inputString1);
////        String result2 = ensureSingleSlash(inputString2);
////        String result3 = ensureSingleSlash(inputString3);
////
////        System.out.println(result1);
////        System.out.println(result2);
////        System.out.println(result3);
//
//        String initMsg = "Initialisation encours";
//        System.out.print(initMsg);
//        for (int i = 0; i < 10; i++) {
////            String cpy = initMsg;
////            System.out.print(cpy);
//            for (int j = 0; j < 5; j++) {
//
//                try {
//                    System.out.print(".");
//                    Thread.sleep(160);
//                    System.out.flush();
//                } catch (InterruptedException e) {
//                }
//            }
//
//            if (i<9) {
//                for (int j = 0; j < 5; j++) {
//                    System.out.print("\b");
//                }
//            }
//
//
//            System.out.flush();
//
//        }
//    }
//}
