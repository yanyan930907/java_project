package hug_fall_legs;
import javax.swing.*;
import java.awt.*;

public class testmainMadeBy13 extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private MainWindow mainWindow;
    private statisticWindow statisticWindow;
    private AllDataWindow allDataWindow;

    public testmainMadeBy13() {
        setTitle("GUI 切換範例");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 卡片容器
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 初始化子畫面並傳入主 frame 作為參考
        statisticWindow = new statisticWindow(this);
        mainWindow= new MainWindow(this);
        allDataWindow=new AllDataWindow(this);

        cardPanel.add(mainWindow, "main");
        cardPanel.add(statisticWindow, "statistic");
        cardPanel.add(allDataWindow, "allData");


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
    public void showAllDataWindow(){
        System.out.println("showAllDataWindow");
        cardLayout.show(cardPanel, "allData");
    }

    // 切換畫面用的方法

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testmainMadeBy13::new);
    }
}
