package Server;

import java.net.*;

public class LocalhostChecker {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 3000)) {
            // Connection successful, localhost is in use
            System.out.println("Localhost is in use.");
        } catch (ConnectException e) {
            // Connection failed, localhost is not in use
            System.out.println("Localhost is not in use.");
        } catch (Exception e) {
            System.err.println("Error checking localhost: " + e.getMessage());
        }
    }
}

