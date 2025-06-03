package hug_fall_legs;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.lang.reflect.Type;
public class SubjectManager {
    private static Formatter output;
    private ArrayList<String> subjectList = new ArrayList<>();
    private String fileName = "yourSubject.txt"; // the target file name
    private Scanner input;

    public void addSub(String sub) {
        openFile();
        addSubject(sub);
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
    public void openFiles() {
        try {
            input = new Scanner(Paths.get(fileName));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    public static void closeFile() {
        if (output != null)
            output.close();
    }
    public void addSubject(String sub) {
        try {
            output.format("\t%s",sub);

        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }
    public ArrayList<String> readSubject() {
        openFiles();
        ArrayList<String> list = readRecords();
        closeFile();
        return list;
    }
    public ArrayList<String> readSubject(String sig) {
        openFiles();
        ArrayList<String> list = readRecords(sig);
        closeFile();
        return list;
    }
    public ArrayList<String> readRecords() {
        ArrayList<String> list = new ArrayList<String>();
        //System.out.printf("%-12s%-12s%10s%n", "First Name", "Last Name", "Balance");
        list.add("全部");
        try {
            while (input.hasNext()) // while there is more to read
            {
                String now = input.next();
                list.add(now);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }

        return list;
    }
    public ArrayList<String> readRecords(String sig) {
        ArrayList<String> list = new ArrayList<String>();
        //System.out.printf("%-12s%-12s%10s%n", "First Name", "Last Name", "Balance");
        try {
            while (input.hasNext()) // while there is more to read
            {
                String now = input.next();
                list.add(now);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }

        return list;
    }
}
