package hug_fall_legs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;

public class CardManager {
    private List<Card> cardList = new ArrayList<>();

    public void addCard(Card card) {
        cardList.add(card);
    }

    public void saveToJson(String filename) {   // 存Card List成json
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(cardList, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromJson(String filename) { // 將json還原成Card List
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            Type cardListType = new TypeToken<ArrayList<Card>>(){}.getType();
            cardList = gson.fromJson(reader, cardListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
