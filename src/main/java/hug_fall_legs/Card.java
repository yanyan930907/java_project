package hug_fall_legs;

import javax.swing.ImageIcon;

public class Card {
    private String frontText; // 正面文字（可加圖片連結）
    private String imagePath;
    private String backHint;  // 背面提示
    private String category;  // 分類，例如：「錯題」
    private String linkedFilePath;  // 關聯檔案的路徑或URL
    private boolean remember;   // 是否熟讀

    
    public Card(String frontText, String imagePath, String backHint, String category, String linkedFilePath) {
        this.frontText = frontText;
        this.imagePath = imagePath;
        this.backHint = backHint;
        this.category = category;
        this.linkedFilePath = linkedFilePath;
        this.remember = false;
    }

    public void setFrontText(String text){ this.frontText = text; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setBackHint(String hint) { this.backHint = hint; }
    public void setCategory(String category) { this.category = category; }
    public void setLinkedFilePath(String linkedFilePath) { this.linkedFilePath = linkedFilePath; }
    public void setRemember(boolean remember) { this.remember = remember; }

    public String getFrontText() { return frontText; }
    public String getImagePath() { return imagePath; }
    public String getBackHint() { return backHint; }
    public String getCategory() { return category; }
    public String getLinkedFilePath() {return linkedFilePath; }
    public ImageIcon getImageIcon() {
        if (imagePath != null) {
            return new ImageIcon(imagePath);
        }
        return null;
    }
    public boolean getRemember() { return remember; }
    
}
