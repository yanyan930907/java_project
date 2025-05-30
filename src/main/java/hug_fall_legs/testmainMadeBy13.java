package hug_fall_legs;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class testmainMadeBy13 extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private MainWindow mainWindow;
    private statisticWindow statisticWindow;
    private AllDataWindow allDataWindow;
    private AllCardWindow allCardWindow;
    private ErrorCollectWindow errorWindow;


    public testmainMadeBy13() {
        setTitle("馬尚讀!!!!!");
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
        ErrorCollectWindow errorWindow=new ErrorCollectWindow(this);
        allCardWindow = new AllCardWindow(this);

        cardPanel.add(mainWindow, "main");
        cardPanel.add(statisticWindow, "statistic");
        cardPanel.add(allDataWindow, "allData");
        cardPanel.add(allCardWindow, "allCard");
        cardPanel.add(errorWindow, "error");
        add(cardPanel);
        cardLayout.show(cardPanel, "main");
        setVisible(true);

    }
    public void showMain(){
        cardLayout.show(cardPanel, "main");

    }
    public void showAllDataWindow(){
        System.out.println("showAllDataWindow");
        cardLayout.show(cardPanel, "allData");
    }
    public void showErrorWindow(){
        System.out.println("showErrorWindow");
        cardLayout.show(cardPanel, "error");
    }
    public void showAllCardWindow(){
        System.out.println("showAllCardWindow");
        cardLayout.show(cardPanel, "allCard");
    }
    public void showStatisticWindow(){
        System.out.println("showStatisticWindow");
        cardLayout.show(cardPanel, "statistic");
    }

    // 切換畫面用的方法

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testmainMadeBy13::new);
    }
}
