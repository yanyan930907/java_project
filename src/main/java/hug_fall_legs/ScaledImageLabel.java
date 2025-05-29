package hug_fall_legs;

import javax.swing.*;
import java.awt.*;

class ScaledImageLabel extends JLabel {
    private Image originalImage;

    public void setImage(Image image) {
        this.originalImage = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (originalImage != null) {
            int labelWidth = getWidth();
            int labelHeight = getHeight();
            int imgWidth = originalImage.getWidth(null);
            int imgHeight = originalImage.getHeight(null);

            // 計算等比例縮放後的大小
            float ratio = Math.min((float)labelWidth / imgWidth, (float)labelHeight / imgHeight);
            int newWidth = (int)(imgWidth * ratio);
            int newHeight = (int)(imgHeight * ratio);

            int x = (labelWidth - newWidth) / 2;
            int y = (labelHeight - newHeight) / 2;

            g.drawImage(originalImage, x, y, newWidth, newHeight, this);
        }
    }
}
