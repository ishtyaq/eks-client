
import java.io.IOException;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException {
        SearchClient searchClient = new SearchClient();
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("exit")) {
            System.out.println("Enter a command (input, search, exit):");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "input":
                    System.out.println("Enter some text:");
                    String input = scanner.nextLine();
                    System.out.println("Text added in Elastic Search. " + input);
                    searchClient.AddText(input);
                    break;
                case "search":
                    System.out.println("Enter a search term:");
                    String searchTerm = scanner.nextLine();
                    System.out.println("Results for: " + searchTerm);
                    searchClient.SearchText(searchTerm);
                    // perform search logic here
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    searchClient.closeClient();
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }

        scanner.close();
    }


}
