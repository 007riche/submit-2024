package com.todo.company.hai704.restapi.Client.utilpack;


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
