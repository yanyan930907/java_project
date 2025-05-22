package card;

public class cardMain {
    public static void main(String[] args) {
        CardManager manager = new CardManager();

        // 建立幾張卡片
        manager.addCard(new Card("貓是？", "images/cat.png", "動物", "生物", "notes/note1.pdf"));
        manager.addCard(new Card("太陽在哪？", "images/sun.png", "天文", "自然", "notes/note2.pdf"));

        // 儲存到 JSON 檔
        manager.saveToJson("cards.json");

        // 清空，再從檔案讀回
        manager = new CardManager();
        manager.loadFromJson("cards.json");

        // 印出每張卡片的 frontText
        for (Card card : manager.getCardList()) {
            System.out.println(card.getFrontText());
        }
    }
}
