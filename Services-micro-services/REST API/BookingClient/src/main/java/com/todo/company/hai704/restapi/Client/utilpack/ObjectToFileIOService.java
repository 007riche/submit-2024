package com.todo.company.hai704.restapi.Client.utilpack;

import java.io.*;

public class ObjectToFileIOService {

    public  void writeObjectToFile(Object obj, String filePath) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(obj);
            System.out.println(  "Written to file: " + filePath);
            System.out.println("Object: "+obj.toString());
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Object readObjectFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();
            System.out.println("Read from file: " + filePath);
            ois.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}