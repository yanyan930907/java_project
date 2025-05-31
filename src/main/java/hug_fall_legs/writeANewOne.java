package hug_fall_legs;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.lang.reflect.Type;
public class writeANewOne {
    private static Formatter output;
    private ArrayList<String> subjectList = new ArrayList<>();
    private  String fileName = "yourCard.txt"; // the target file name
    private Scanner input;


    public void update(ArrayList<Card> cardList) {
        openfile();
        reRead(cardList);
        closeFile();
    }

    private void openfile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            output = new Formatter(fw);
        } catch (SecurityException securityException) {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1); // terminate the program
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1); // terminate the program
        } catch (IOException e) {
            System.err.println("I/O error. Terminating.");
            System.exit(1); // terminate the program
        }
    }
    public static void closeFile() {
        if (output != null)
            output.close();
    }
    private void reRead(ArrayList<Card> cardList) {
        try {
            for (Card card : cardList) {
                output.format("%s\t%s\t%s\t%s\t%s\t%b%n", card.getFrontText(), card.getImagePath(), card.getBackHint(), card.getCategory(), card.getLinkedFilePath(), card.getRemember());
            }
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }
}
