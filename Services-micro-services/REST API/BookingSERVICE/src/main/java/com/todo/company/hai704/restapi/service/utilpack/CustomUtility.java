package com.todo.company.hai704.restapi.service.utilpack;

import com.todo.company.hai704.restapi.service.entity.Image;
import com.todo.company.hai704.restapi.service.entity.Room;
import com.todo.company.hai704.restapi.service.services.PersistanceService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.UUID;

public class CustomUtility {

    public CustomUtility() {
    }


    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static int generateRandomInt(int min, int max) {
        // Generate a random number between min (inclusive) and max (inclusive)
        int random = (int) (Math.random() * (max - min + 1)) + min;

        // Return the final random integer
        return random;
    }

    public static double generateRandomDouble(double min, double max) {
        // Generate a random number between 0 and 1
        double random = Math.random();

        // Scale the random number to fit within the specified range
        double range = max - min;
        double scaledRandom = random * range;

        // Shift the scaled random number to the correct starting point
        double shiftedRandom = scaledRandom + min;

        // Return the final random number
        return shiftedRandom;
    }


    public static Image insertInitImg(PersistanceService service,
                               int roomType, int imgNumber, Room room) {

        String imgPath = "img/room/"+Integer.toString(roomType)+"/"+Integer.toString(imgNumber)+".jpg";
//        boolean rowInsertion =false;
//        int key=-1;

        try {
            System.out.println("Image path: "+imgPath);
            service.saveImage(room.getImgName(),imgPath);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("===>> No image inserted because file not found");
        }

        return service.findImageByImgName(room.getImgName());
    }

    public static Room generateNewRoom(PersistanceService service, int roomNumber, double stars, int roomIndex) {
        Room genRoom = new Room();
        genRoom.setRoomNumber(roomNumber);  // roomNumber
        String roomImBase = "roomNumber"+roomIndex;
        genRoom.setImgName(roomImBase);  // imgName
        if(stars<=3.0d)
        {
            genRoom.setNumberBed(generateRandomInt(1, 3)); //   numberBed
            if (genRoom.getNumberBed() == 1 ) {
                genRoom.setBasePrice(generateRandomDouble(10.0, 20.0));  //     basePrice

            } else {
                genRoom.setBasePrice(generateRandomDouble(11.0, 27.0));
            }
        } else {
            genRoom.setNumberBed(generateRandomInt(1, 2));
            if (stars == 4) {
                if (genRoom.getNumberBed() == 1 ) {
                    genRoom.setBasePrice(generateRandomDouble(25.0, 35.0));
                } else {
                    genRoom.setBasePrice(generateRandomDouble(28.99, 39.99));
                }
            } else {
                if (genRoom.getNumberBed() == 1 ) {
                    genRoom.setBasePrice(generateRandomDouble(44.99, 75.99));
                } else {
                    genRoom.setBasePrice(generateRandomDouble(55.99, 99.99));
                }
            }
        }

        int imgNumber = generateRandomInt(1, 20);
        int roomType = genRoom.getNumberBed();
        Image roomImage = insertInitImg(service, roomType, imgNumber, genRoom);
        System.err.println("Insertion of image: Image: Id"+roomImage.getId()+"  : imgName:"+roomImage.getImgName());
        genRoom.setImage(roomImage); // room image

        return genRoom;
    }

    public static boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            // Port available
            return true;
        } catch (IOException e) {
            // Port not available
            return false;
        }
    }

    public static int getAvailablePort() {
        int startPort = 8090;
        int endPort = 9080;

        int currentPort = startPort;

        while (currentPort<=endPort) {
            if(isPortAvailable(currentPort)) {
                break;
            }
            currentPort++;
        }

        return currentPort;
    }

    public static String ensurePortNumberOrSingleEndingSlash(String inputString) {
        // Remove trailing slashes and spaces
        inputString = inputString.replaceAll("[/\\s]+(?=:\\d{1025,65531}$)|[/\\s]+$", "");

        // Add a single slash at the end
        return inputString + "/";
    }

    public static boolean matchMysqlJdbcUrlWithDBName(String jdbcUrl) {
        // Define the regular expression for a MySQL JDBC URL
        String mysqlRegex = "jdbc:mysql://([^:/]+):(\\d+)/([^?]+)";

        if (jdbcUrl.matches(mysqlRegex)) {
            System.out.println("L'URL Semble correcte (Avec le nom de la base donnee) ");
            return true;
        } else {
            System.out.println("L'URL ne semble pas valide");
            return false;
        }
    }

    public static boolean matchMysqlJdbcUrlWithoutDBName(String jdbcUrl) {
        // Define the regular expression for a MySQL JDBC URL
        String jdbcUrlPattern = "jdbc:mysql://[^:/]+:(\\d+)/?";

        if (jdbcUrl.matches(jdbcUrlPattern)) {
            System.out.println("L'URL Semble correcte");
            return true;
        } else {
            System.out.println("L'URL ne semble pas valide");
            return false;
        }
    }

    public static int extractPort(String urlString) {
        try {
            URL url = new URL(urlString);
            // Get the port number
            int port = url.getPort();
            // If no port is explicitly specified, get the default port for the protocol
            if (port == -1) {
                port = url.getDefaultPort();
            }
            return port;
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
            return -1; // or a default port you wish to return in case of error
        }
    }



}
