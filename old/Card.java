package hug_fall_legs;
import javax.swing.*;
public class Card{

        private final String hint;
        private ImageIcon image;
        private String image_name;
        private String category;
        public Card(String hint,ImageIcon image,String image_name,String category){
            this.hint=hint;
            this.image=image;
            this.image_name=image_name;
            this.category=category;
        }
        public String getHint() {
            return hint;
        }
        public ImageIcon getImage() {
            return image;
        }
        public String getImageName() {
            return image_name;
        }
        public String getCategory() {
            return category;
        }
    
}

