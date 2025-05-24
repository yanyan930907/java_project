package java_final_project;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    public void saveCards(List<Card> cards, String filePath) { /* 儲存為 JSON */ }
    public List<Card> loadCards(String filePath) { return new ArrayList<>(); }

    public void saveReviewSessions(List<ReviewSession> sessions, String filePath) { }
    public List<ReviewSession> loadReviewSessions(String filePath) { return new ArrayList<>(); }
}
