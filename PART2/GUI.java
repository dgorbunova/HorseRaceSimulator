import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;


public class GUI extends JFrame{
    JTextField lane, horses, horseTemp, horseTempConfidence, horseTempSymbol, horseTempLane;
    JButton start, submit;
    JMenuBar menuBar;
    public static boolean trackSelected = false;
    public static boolean customized = false;
    public static boolean horsesEntered = false;
    public static Horse[] horsesArray = new Horse[5];
    public static int horseBetOn = -1;
    public static double betWinnings = 0;
    public static boolean betPlaced = false;
    public static boolean raceEnded = false;
    public static int raceDistance =-1;
    
    /**
     * 
     */
    public GUI(){
        setLayout(null); 
        //declaring pannels used in frame
        JPanel racePanel = new JPanel(); 
        JPanel horsePanel = new JPanel();
        JPanel trackPanel = new JPanel();
    
        //menu bar and its components
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1000, 30);

        JMenu customizeMenu = new JMenu("Horses");
        JMenuItem viewHorses = new JMenuItem("View Horses");

        JMenu statsMenu = new JMenu("Statistics");
        JMenuItem statsItem = new JMenuItem("View Statistics");

        JMenu betMenu = new JMenu("Virtual Betting");
        JMenuItem betItem = new JMenuItem("Place a bet");

        //adding components to menu bar and menu bar to frame
        betMenu.add(betItem);
        customizeMenu.add(viewHorses);
        statsMenu.add(statsItem);
        menuBar.add(customizeMenu);
        menuBar.add(statsMenu);
        menuBar.add(betMenu);
        add(menuBar);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////RACE  PANEL//////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //race panel layout 
        racePanel.setLayout(null);
        racePanel.setBackground(Color.WHITE); 
        racePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        racePanel.setBounds(0, 10, 700, 600); 

        //race panel title
        JLabel titleRacePanel = new JLabel("The Race:");
        titleRacePanel.setFont(new Font("Arial", Font.BOLD, 20));
        titleRacePanel.setBounds(20, 10, 400, 50);
        racePanel.add(titleRacePanel);

        //race panel text area
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(5, 150, 690, 200);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        textArea.setAutoscrolls(true);
        racePanel.add(textArea);

        //print stream to print to text area
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        System.setOut(printStream); // Redirect System.out to the text area
        
        add(racePanel); //add race panel to frame


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////TRACK SETTINGS PANEL/////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // track panel layout
        trackPanel.setLayout(null);
        trackPanel.setBounds(700, 10, 300, 600);
        trackPanel.setBackground(new Color(0xF5F5DC)); 

        // track panel title
        JLabel titleTrackPanel = new JLabel("Track Settings:");
        titleTrackPanel.setFont(new Font("Arial", Font.BOLD, 20));
        titleTrackPanel.setBounds(50, 30, 400, 50);
        trackPanel.add(titleTrackPanel);

        // number of lanes slider
        JLabel laneLabel = new JLabel("Number of lanes for the race: ");
        JSlider sliderLanes = new JSlider(JSlider.HORIZONTAL, 2, 5, 2); 

        laneLabel.setBounds(30, 80, 400, 50);
        sliderLanes.setBounds(30, 130, 150, 50);

        sliderLanes.setMinorTickSpacing(1);  
        sliderLanes.setMajorTickSpacing(1);
        sliderLanes.setPaintTicks(true);  
        sliderLanes.setPaintLabels(true);  
        sliderLanes.setSnapToTicks(true);

        // number of horses slider
        JLabel horsesLabel = new JLabel("Number of horses for the race: ");
        JSlider sliderHorses = new JSlider(JSlider.HORIZONTAL, 2, 2, 2); 

        horsesLabel.setBounds(30, 180, 400, 50);
        sliderHorses.setBounds(30, 230, 150, 50);

        sliderHorses.setMinorTickSpacing(1);  
        sliderHorses.setMajorTickSpacing(1);
        sliderHorses.setPaintTicks(true);  
        sliderHorses.setPaintLabels(true);  
        sliderHorses.setSnapToTicks(true);

        // set the maximum number of horses to the number of lanes, consistency
        sliderLanes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderLanes.getValue();
                sliderHorses.setMaximum(value);
            }
        });

        // length of track slider
        JLabel lengthLabel = new JLabel("Length of track: ");
        JSlider sliderLength = new JSlider(JSlider.HORIZONTAL, 10, 40, 10); 

        lengthLabel.setBounds(30, 280, 400, 50);
        sliderLength.setBounds(30, 330, 250, 50);

        sliderLength.setMinorTickSpacing(5);  
        sliderLength.setMajorTickSpacing(5);
        sliderLength.setPaintTicks(true);  
        sliderLength.setPaintLabels(true);  
        sliderLength.setSnapToTicks(true);

        // submit button
        submit = new JButton("Submit"); 
        submit.setBounds(110, 450, 100, 50);
       
        // add components to track panel
        trackPanel.add(titleTrackPanel);
        trackPanel.add(laneLabel);
        trackPanel.add(sliderLanes);
        trackPanel.add(horsesLabel);
        trackPanel.add(sliderHorses);
        trackPanel.add(lengthLabel);
        trackPanel.add(sliderLength);
        trackPanel.add(submit);

        // add track panel to frame
        add(trackPanel);

        
    }

    
}