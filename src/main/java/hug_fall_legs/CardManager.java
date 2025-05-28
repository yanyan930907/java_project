package hug_fall_legs;



import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;

public class CardManager {
    private static Formatter output;
    private List<Card> cardList = new ArrayList<>();
    private String fileName = "yourCard.txt"; // the target file name

    public void addCard(Card card) {
        openFile();
        cardList.add(card);
        addRecord(card);
        closeFile();
    }
    public void openFile() {
        try {
            FileWriter fw = new FileWriter(fileName, true);
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
    public void addRecord(Card card) {
        try {
            output.format("%s\t%s\t%s\t%s\t%s%n",card.getFrontText(),card.getImagePath(),card.getBackHint(),card.getCategory(),card.getLinkedFilePath(),card.getCategory(),card.getLinkedFilePath());

        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }
    public static void closeFile() {
        if (output != null)
            output.close();
    }



    public List<Card> getCardList() {
        return cardList;
    }
}
