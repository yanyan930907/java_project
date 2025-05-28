package hug_fall_legs;



import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.lang.reflect.Type;

public class CardManager {
    private static Formatter output;
    private ArrayList<Card> cardList = new ArrayList<>();
    private String fileName = "yourCard.txt"; // the target file name
    private Scanner input;

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
            output.format("%s\t%s\t%s\t%s\t%s\t%b%n",card.getFrontText(),card.getImagePath(),card.getBackHint(),card.getCategory(),card.getLinkedFilePath(),card.getRemember());

        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }
    public static void closeFile() {
        if (output != null)
            output.close();
    }

    public void openFiles() {
        try {
            input = new Scanner(Paths.get(fileName));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }

    public ArrayList<Card> readAllCards() {
        openFiles();
        ArrayList<Card> list = readRecords();
        closeFile();
        return list;
    }

    public ArrayList<Card> readRecords() {
        ArrayList<Card> list = new ArrayList<Card>();
        //System.out.printf("%-12s%-12s%10s%n", "First Name", "Last Name", "Balance");

        try {
            while (input.hasNext()) // while there is more to read
            {
                String frontText = input.next();
                String imagePath = input.next();
                String backHint = input.next();
                String category = input.next();
                String linkedFilePath = input.next();
                boolean rem = input.nextBoolean();

                list.add(new Card(frontText,imagePath,backHint,category,linkedFilePath,rem));
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }

        return list;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }
}
