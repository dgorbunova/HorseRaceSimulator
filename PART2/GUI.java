import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;


public class GUI extends JFrame{
    
    
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
        
    }
}