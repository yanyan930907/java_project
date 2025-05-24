package hug_fall_legs;
import javax.swing.*;
import java.awt.*;

public class testmainMadeBy13 extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private MainWindow mainWindow;
    private statisticWindow statisticWindow;

    public testmainMadeBy13() {
        setTitle("hug_fall_legs");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 卡片容器
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 初始化子畫面並傳入主 frame 作為參考

        cardPanel.add(new MainWindow(this), "main");
        cardPanel.add(new statisticWindow(this), "statistic");
        cardPanel.add(new ErrorCollectWindow(this), "error");
        cardPanel.add(new AllCardWindow(this), "allCard");
        cardPanel.add(new AllDataWindow(this), "allData");



        add(cardPanel);
        cardLayout.show(cardPanel, "main");
        setVisible(true);

    }
    public void showMain(){
        cardLayout.show(cardPanel, "main");
    }

    public void showStatistic(){
        System.out.println("showStatistic");
        cardLayout.show(cardPanel, "statistic");
    }
    public void showError(){
        System.out.println("showError");
        cardLayout.show(cardPanel, "error");
    }
    public void showAllCard(){
        System.out.println("showAllCard");
        cardLayout.show(cardPanel, "allCard");
    }
    public void showAllData(){
        System.out.println("showAllData");
        cardLayout.show(cardPanel, "allData");
    }

    // 切換畫面用的方法

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testmainMadeBy13::new);
    }
}
