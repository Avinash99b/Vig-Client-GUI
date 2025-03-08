package com.avinash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    private static final String CONFIG_PATH = "C:\\ProgramData\\Avinash-Vignan-Info\\config.txt";

    public static void main(String[] args) {
        File configFile = new File(CONFIG_PATH);
        String systemId = "";
        String serverUrl = "";

        // Read existing config if available
        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                systemId = reader.readLine();
                serverUrl = reader.readLine();
            } catch (IOException e) {
                System.err.println("Error reading config file: " + e.getMessage());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Configuration:");
        System.out.println("1. System ID: " + (systemId != null ? systemId : "Not Set"));
        System.out.println("2. Server URL: " + (serverUrl != null ? serverUrl : "Not Set"));

        boolean modified = false;

        // Ask if user wants to change URL
        System.out.print("Do you want to change the Server URL? (yes-y/no-n): ");
        String input = scanner.nextLine().trim();
        if (input.startsWith("y")) {
            System.out.print("Enter new Server URL: ");
            serverUrl = scanner.nextLine().trim();
            modified = true;
        }

        // Ask if user wants to change System ID
        System.out.print("Do you want to change the System ID? (yes-y/no-n): ");
        input = scanner.nextLine().trim();
        if (input.startsWith("y")) {
            System.out.print("Enter new System ID: ");
            systemId = scanner.nextLine().trim();
            modified = true;
        }

        // Save new configuration if modified
        if (modified) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write(systemId);
                writer.newLine();
                writer.write(serverUrl);
                System.out.println("Configuration updated successfully!");
            } catch (IOException e) {
                System.err.println("Error writing config file: " + e.getMessage());
            }
        } else {
            System.out.println("No changes made.");
        }

        scanner.close();
    }
}

