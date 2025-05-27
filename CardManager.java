package hug_fall_legs;



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



    public List<Card> getCardList() {
        return cardList;
    }
}
