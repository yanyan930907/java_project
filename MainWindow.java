package hug_fall_legs;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import hug_fall_legs.ReadingRoom;

public class MainWindow extends JFrame{
    //Button declare
    private JButton allCardsButton;
    private JButton statisticButton;
    private JButton allDataButton;
    private JButton errorButton;
    private JButton stopTimeButton;
    private JButton setTimeButton;
    //Label declare
    private JLabel titleLabel;
    public JLabel hoursLabel;
    public JLabel minutesLabel;
    public JLabel secondsLabel;

    public MainWindow(){
        super("hug_fall_legs");
        MainEventListner handler = new MainEventListner();

        // create buttons and add action listeners
        allCardsButton = new JButton("全部卡片");
        setTimeButton = new JButton("設定時間");
        statisticButton = new JButton("統計資料");
        errorButton = new JButton("錯誤整理");
        stopTimeButton = new JButton("暫停時間");
        allDataButton = new JButton("所有資料");

        allCardsButton.addActionListener(handler);
        setTimeButton.addActionListener(handler);
        statisticButton.addActionListener(handler);
        errorButton.addActionListener(handler);
        stopTimeButton.addActionListener(handler);
        allDataButton.addActionListener(handler);
        
        titleLabel = new JLabel("hug_fall_legs");
        hoursLabel = new JLabel("00");
        minutesLabel = new JLabel("00");
        secondsLabel = new JLabel("00");
        hoursLabel.setToolTipText("hour");
        hoursLabel.setToolTipText("minute");
        hoursLabel.setToolTipText("second");
        
        JPanel functionPanel = new JPanel();
        functionPanel.add(allCardsButton);
        functionPanel.add(statisticButton);
        functionPanel.add(allDataButton);
        functionPanel.add(errorButton);

        JPanel timePanel = new JPanel();
        timePanel.add(hoursLabel);
        timePanel.add(minutesLabel);
        timePanel.add(secondsLabel);
        timePanel.add(setTimeButton);
        timePanel.add(stopTimeButton);

        //Layout 待修改
        functionPanel.setLayout( new BoxLayout(functionPanel,BoxLayout.Y_AXIS));
        add(functionPanel, BorderLayout.CENTER);
        add(timePanel, BorderLayout.EAST);
        
    }
    
    private class MainEventListner implements ActionListener {
		// change font size when user clicks on a button
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource()==allCardsButton){
				
			}
			else if(event.getSource()==setTimeButton){
				
			}
            else if(event.getSource()==statisticButton){
				
			}
            else if(event.getSource()==errorButton){
				
			}
            else if(event.getSource()==stopTimeButton){
				
			}
            else if(event.getSource()==allDataButton){
				
			}
		}
	}
}
