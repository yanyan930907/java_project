public class TextImageContent extends CardContent {
    private String text;
    private String imagePath;

    public TextImageContent(String text, String imagePath) {
        this.text = text;
        this.imagePath = imagePath;
    }

    public String getText() { return text; }
    public String getImagePath() { return imagePath; }

    @Override
    public void render() {
        System.out.println("文字：" + text + " 圖片：" + imagePath);
    }

    @Override
    public String toJson() {
        return "{ \"text\": \"" + text + "\", \"imagePath\": \"" + imagePath + "\" }";
    }
}
