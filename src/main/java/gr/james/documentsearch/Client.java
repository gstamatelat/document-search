package gr.james.documentsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: document-search words.txt query.txt");
            return;
        }
        try (
                Scanner words = new Scanner(new File(args[0]));
                Scanner query = new Scanner(new File(args[1]))
        ) {
            DocumentSearch.search(words, query);
        }
    }
}
