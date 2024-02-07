package com.todo.company.hai704.restapi.service.trash;

import com.todo.company.hai704.restapi.service.models.NodeService;

import java.io.*;

public class NodeInformationSerializationService {

    public static  void serializeNodeInformation(NodeService nodeService, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(nodeService);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  NodeService deserializeNodeInformation(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            NodeService restoredNodService = (NodeService) inputStream.readObject();
            System.out.println("Restored NodeService: \n{\n" + restoredNodService.toString() + "\n}\n");
            return restoredNodService;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
