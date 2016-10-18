package main;

import queue.Canal;
import queue.QueueSystem;
import queue.Source;
import queue.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Антон on 18.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter screening probability (for source - p)");
            double p =  Double.parseDouble(reader.readLine());
            System.out.println("Enter screening probability (for canal1 - p1)");
            double p1 = Double.parseDouble(reader.readLine());
            System.out.println("Enter screening probability (for canal2 - p2)");
            double p2 = Double.parseDouble(reader.readLine());
            System.out.println("Enter tact number");
            int tactNumber = Integer.parseInt(reader.readLine());
            Source source = new Source(p);
            Storage storage = new Storage(1);
            Canal firstCanal = new Canal(p1);
            Canal secondCanal = new Canal(p2);
            QueueSystem queueSystem = new QueueSystem(source, storage, firstCanal, secondCanal, tactNumber);
            queueSystem.processRequests();
            System.out.println("kek");
        } catch (IOException e) {
            System.out.print("Check the input data");
        }
    }
}
