package java_final_project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardManager {

    private ArrayList<Card> cards;

    public CardManager() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(cards, writer);
            System.out.println("成功儲存到：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
