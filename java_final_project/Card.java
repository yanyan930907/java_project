package java_final_project;

import javax.swing.ImageIcon;

public class Card {
    private String frontText; // 正面文字（可加圖片連結）
    private ImageIcon image;
    private String backHint;  // 背面提示
    private String category;  // 分類，例如：「錯題」
    private String linkedFilePath;  // 關聯檔案的路徑或URL


    public Card(String frontText, ImageIcon image, String backHint, String category, String linkedFilePath) {
        this.frontText = frontText;
        this.image = image;
        this.backHint = backHint;
        this.category = category;
        this.linkedFilePath = linkedFilePath;
    }

    public void setFrontText(String text){ this.frontText = text; }
    public void setImage(ImageIcon image) { this.image = image; }
    public void setBackHint(String hint) { this.backHint = hint; }
    public void setCategory(String category) { this.category = category; }
    public void setLinkedFilePath(String linkedFilePath) { this.linkedFilePath = linkedFilePath; }

    public String getFrontText() { return frontText; }
    public ImageIcon getImage() { return image; }
    public String getBackHint() { return backHint; }
    public String getCategory() { return category; }
    public String getLinkedFilePath() {return linkedFilePath; }

    public void display(){  // 顯示卡片內容
        
    }
    
}
