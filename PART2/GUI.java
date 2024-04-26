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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////HORSE SETTINGS PANEL/////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // Declare the components for the horse panel
        JTextField[] horseTempFields = new JTextField[5];
        JTextField[] horseTempConfidenceFields = new JTextField[5];
        JTextField[] horseTempSymbolFields = new JTextField[5];
        JTextField[] horseTempLaneFields = new JTextField[5];
        JSlider[] horseTempSliderColor = new JSlider[5];
        JSlider[] horseTempSliderAccessory = new JSlider[5];
        JTextField[] horseTempBreedFields = new JTextField[5];
        
        //horse panel layout
        horsePanel.setLayout(null);
        horsePanel.setBounds(0, 600, 1000, 250);
        horsePanel.setBackground(new Color(0xFFE5B4)); 
        horsePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //horse panel title
        JLabel titleHorsePanel = new JLabel("Horse Settings:");
        titleHorsePanel.setBounds(20, 10, 400, 50);
        horsePanel.add(titleHorsePanel);
        titleHorsePanel.setFont(new Font("Arial", Font.BOLD, 20));
        horsePanel.add(titleHorsePanel); 
        
        //save button
        JButton start = new JButton("Save");
        start.setBounds(780, 120, 100, 50);
        horsePanel.add(start);
        
        submit.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                getSliderValues();
            }

            public void getSliderValues() {
                int horses = sliderHorses.getValue();
                int length = sliderLength.getValue();
                raceDistance = length;
                trackSelected = true;
                
                for (int i=0; i<horses; i++) { // Loop through the number of horses, adding labels and text fields
                    JLabel horseInput = new JLabel("Horse " + (i+1) + ": "); 
                    horseInput.setBounds(20, 50 + (i * 40), 100, 30);  //horse label
                    horseTemp = new JTextField();
                    horseTemp.setBounds(100, 50 + (i * 40), 100, 30);  //horse textfield
                    horseTempFields[i] = horseTemp;
                    JLabel horseConfidence = new JLabel("Confidence: ");
                    horseConfidence.setBounds(250, 50 + (i * 40), 150, 30); //confidence label
                    horseTempConfidence = new JTextField();
                    horseTempConfidenceFields[i] = horseTempConfidence;
                    horseTempConfidence.setBounds(350, 50 + (i * 40), 50, 30); //confidence textfield
                    JLabel horseSymbol = new JLabel("Symbol: ");
                    horseSymbol.setBounds(450, 50 + (i * 40), 100, 30); //symbol label
                    horseTempSymbol = new JTextField();
                    horseTempSymbolFields[i] = horseTempSymbol;
                    horseTempSymbol.setBounds(550, 50 + (i * 40), 50, 30); //symbol textfield
                    JLabel horseLane = new JLabel("Lane: ");
                    horseLane.setBounds(650, 50 + (i * 40), 100, 30); //lane label
                    horseTempLane = new JTextField();
                    horseTempLaneFields[i] = horseTempLane;
                    horseTempLane.setBounds(700, 50 + (i * 40), 50, 30); //lane textfield

                    // add the components to the horse panel
                    horsePanel.add(horseInput);
                    horsePanel.add(horseTemp);
                    horsePanel.add(horseConfidence);
                    horsePanel.add(horseTempConfidence);
                    horsePanel.add(horseSymbol);
                    horsePanel.add(horseTempSymbol); 
                    horsePanel.add(horseLane);
                    horsePanel.add(horseTempLane);
                    
                
                }
                // revalidate and repaint the horse panel, updating it with the new components
                horsePanel.revalidate(); 
                horsePanel.repaint(); 
            }
        });
        start.addActionListener(new ActionListener() { // Save button action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trackSelected==true){
                    processValues();
                }
            }

            public void processValues() { // Process the values entered by the user
                //flags for validation
                boolean validName = false;
                boolean validConfidence = false;
                boolean validSymbol = false;
                boolean validLane = false;

                // Get the values from the sliders
                int horses = sliderHorses.getValue();
                int distance = sliderLength.getValue();
                int lanes = sliderLanes.getValue();

                // Create a race instance
                Race race = new Race(distance, horses, lanes); 

                // Create arrays to store the values of the horses
                boolean[] validHorse = new boolean[horses];
                String[] horseNames = new String[horses];
                double[] horseConfidences = new double[horses];
                char[] horseSymbols = new char[horses];
                int[] horseLanes = new int[horses];

                // Loop through the number of horses, validating the values entered
                for (int i=0; i<horses; i++) { 
                    if (validName==false || validConfidence==false || validSymbol==false || validLane==false){  //if any of the values are invalid, set the flag to false
                        //name validation
                        try{ 
                            horseNames[i] = horseTempFields[i].getText();
                            if (horseNames[i].equals("")){
                                horseTempFields[i].setText("");
                                horseTempFields[i].setBackground(Color.RED);
                                validName = false;
                            } else {
                                validName = true;
                                horseTempFields[i].setBackground(Color.WHITE);
                            } 
                        } catch (NullPointerException e) {
                            horseTempFields[i].setText("");
                            horseTempFields[i].setBackground(Color.RED);
                            validName = false;
                        }
                        //confidence validation
                        try{
                            horseConfidences[i] = Double.parseDouble(horseTempConfidenceFields[i].getText());
                            if (horseConfidences[i]<=0 || horseConfidences[i]>1){
                                horseTempConfidenceFields[i].setText("");
                                horseTempConfidenceFields[i].setBackground(Color.RED);
                                validConfidence = false;
                            } else {
                                validConfidence = true;
                                horseTempConfidenceFields[i].setBackground(Color.WHITE);
                            }
                        } catch (NumberFormatException e) {
                            validConfidence = false;
                            horseTempConfidenceFields[i].setText("");
                            horseTempConfidenceFields[i].setBackground(Color.RED);
                        }
                        //symbol validation
                        try{
                            horseSymbols[i] = horseTempSymbolFields[i].getText().charAt(0);
                            if (horseSymbols[i]==' '){
                                validSymbol = false;
                                horseTempSymbolFields[i].setText("");
                                horseTempSymbolFields[i].setBackground(Color.RED);
                            } else {
                                horseTempSymbolFields[i].setBackground(Color.WHITE);
                                validSymbol = true;
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            validSymbol = false;
                            horseTempSymbolFields[i].setText("");
                            horseTempSymbolFields[i].setBackground(Color.RED);
                        } 
                        //lane validation
                        try{
                            horseLanes[i] = Integer.parseInt(horseTempLaneFields[i].getText());
                            if (horseLanes[i]>lanes || horseLanes[i]<=0){
                                validLane = false;
                                horseTempLaneFields[i].setText("");
                                horseTempLaneFields[i].setBackground(Color.RED);
                            } else {
                                validLane = true;
                                horseTempLaneFields[i].setBackground(Color.WHITE);
                            }
                        } catch (NumberFormatException e) {
                            
                            validLane = false;
                            horseTempLaneFields[i].setText("");
                            horseTempLaneFields[i].setBackground(Color.RED);
                        }

                        // If all values are valid, set the flag to true
                        if (validName && validConfidence && validSymbol && validLane) {
                            validHorse[i] = true;
                        }

                        // If any of the values are invalid, set the flag to false, move onto next horse with all false to check again
                        validName =false; 
                        validConfidence =false;
                        validSymbol =false;
                        validLane =false;
                    }  else {
                        validHorse[i] = true;
                    }
                }

                // Check if all horses are valid
                boolean allHorsesValid = true;
                for (int i = 0; i < horses; i++) {
                    if (!validHorse[i]) {
                        allHorsesValid = false;
                        break;
                    }
                }

                // If all horses are valid and reset the text fields
                if (allHorsesValid && !customized){
                    JOptionPane.showMessageDialog(null, "All horses are valid");
                    horsesEntered = true;
                    for (int j=0; j<horses; j++) {
                        horseTempFields[j].setBackground(Color.WHITE);
                        horseTempConfidenceFields[j].setBackground(Color.WHITE);
                        horseTempSymbolFields[j].setBackground(Color.WHITE);
                        horseTempLaneFields[j].setBackground(Color.WHITE);
                    }
                } else if (!allHorsesValid) {
                    JOptionPane.showMessageDialog(null, "Please enter valid values for all horses, see help for more information.");
                }

                // Add the horses
                int horsesAdded = 0;
                if (horsesEntered==true){
                    if (!customized){ //cant add horses after theyve been customised, only bfeore
                        for (int j=1; j<=lanes; j++) {
                            for (int k=0; k<horses; k++) {
                                if (horseLanes[k] == j) {
                                    Horse h = new Horse(horseSymbols[k], horseNames[k], horseConfidences[k]);
                                    horsesArray[horsesAdded] = h;
                                    horsesAdded++;
                                    race.addHorse(h, j);
                                } 
                            }
                        }

                        //customise button
                        JButton customizeButton = new JButton("Customize");
                        customizeButton.setBounds(780, 50, 100, 50);

                        //add the customize button to the horse panel if horses have been entered
                        if (horsesEntered==true){
                            horsePanel.add(customizeButton);
                            horsePanel.revalidate();
                            horsePanel.repaint();
                            customizeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    customiseHorses(horsesArray, race);
                                    horsePanel.revalidate();
                                    horsePanel.repaint();
                                }
                            });

                            //start button for the race
                            JButton startTheRace = new JButton("Start");
                            startTheRace.setBounds(780, 190, 100, 50);
                            horsePanel.add(startTheRace);
                            horsePanel.revalidate();
                            horsePanel.repaint();
                            startTheRace.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!customized){
                                        JOptionPane.showMessageDialog(null, "Please customize the horses before starting.");
                                    } else {
                                        Thread raceThread = new Thread(() -> {
                                        raceEnded = race.startRace(textArea); });
                                        raceThread.start();
                                        raceEnded = true; //race has ended after it is executed, betting can be accessed
                                    }
                                };
                            });
                        }                       
                    }
                } 
            } // END of processValues method
            public void customiseHorses(Horse[] horsesArray, Race race) {
                // Create a new empty frame for horse customization and layout
                JFrame customizeFrame = new JFrame("Horse Customization");
                customizeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customizeFrame.setSize(900, 600);
                customizeFrame.setLayout(null);
                customizeFrame.setBackground(new Color(0xFFE5B4));
               
                // Loop through the horses array
                for (int i = 0; i < horsesArray.length; i++) {
                    if (horsesArray[i] == null) {
                        continue;
                    } else {
                        Horse current = horsesArray[i];

                        // Create labels for the horse customization
                        JLabel horseCurrent = new JLabel("Horse " + (i + 1) + ": ");
                        horseCurrent.setBounds(20, 10 + (i * 100), 100, 30);
                        JLabel horseNameLabel = new JLabel("Name: " + (current.getName())); //name
                        horseNameLabel.setBounds(20, 30 + (i * 100), 200, 30);
                        JLabel horseSymbolLabel = new JLabel("Symbol: " + (current.getSymbol())); //symbol
                        horseSymbolLabel.setBounds(20, 50 + (i * 100), 100, 30);
                        JLabel horseColorLabel = new JLabel("Color: "); //color label
                        horseColorLabel.setBounds(150, 5, 100, 30);
                        JLabel horseAccLabel = new JLabel("Accessory: "); //accessory label
                        horseAccLabel.setBounds(400, 5, 100, 30);
                        JLabel horseBreedLabel= new JLabel("Breed: "); //accessory label
                        horseBreedLabel.setBounds(650, 5, 100, 30);
            
                        // Create sliders for the color and accessory
                        JSlider sliderColor = new JSlider(JSlider.HORIZONTAL, 0, 2, 0); // Assuming 3 options (0, 1, 2)
                        sliderColor.setMajorTickSpacing(1);
                        sliderColor.setPaintTicks(true);
                        sliderColor.setPaintLabels(true);
                        sliderColor.setSnapToTicks(true);
                        sliderColor.setBounds(150, 30 + (i * 100), 200, 50);
                        horseTempSliderColor[i] = sliderColor;

                        // Create labels for the slider colour
                        Hashtable<Integer, JLabel> labelTableColor = new Hashtable<>();
                        labelTableColor.put(0, new JLabel("Black"));
                        labelTableColor.put(1, new JLabel("Dun"));
                        labelTableColor.put(2, new JLabel("Bay"));
                        sliderColor.setLabelTable(labelTableColor);
                        

                        // Create sliders for the accessory
                        JSlider sliderAccessory = new JSlider(JSlider.HORIZONTAL, 0, 1, 0); // Assuming 3 options (0, 1, 2)
                        sliderAccessory.setMajorTickSpacing(1);
                        sliderAccessory.setPaintTicks(true);
                        sliderAccessory.setPaintLabels(true);
                        sliderAccessory.setSnapToTicks(true);
                        sliderAccessory.setBounds(400, 30 + (i * 100), 200, 50);
                        horseTempSliderAccessory[i] = sliderAccessory;

                         // Create labels for the slider
                        Hashtable<Integer, JLabel> labelTableAccessory = new Hashtable<>();
                        labelTableAccessory.put(0, new JLabel("Hat"));
                        labelTableAccessory.put(1, new JLabel("Saddle"));
                        sliderAccessory.setLabelTable(labelTableAccessory);
                         

                        // Create text fields for the breed
                        JTextField breedField = new JTextField();
                        breedField.setBounds(650,  30 + (i * 100), 200, 30);
                        horseTempBreedFields[i] = breedField;

                        // Add the components to the frame
                        customizeFrame.add(sliderColor);
                        customizeFrame.add(sliderAccessory);
                        customizeFrame.add(horseColorLabel);
                        customizeFrame.add(horseNameLabel);
                        customizeFrame.add(horseSymbolLabel);
                        customizeFrame.add(horseCurrent);
                        customizeFrame.add(horseAccLabel); 
                        customizeFrame.add(horseBreedLabel);
                        customizeFrame.add(breedField);
                       
                        // Create a save button
                        JButton saveButton = new JButton("Save");
                        saveButton.setBounds(400, 500, 100, 30);
                        customizeFrame.add(saveButton);

                        // Create arrays to store the choices
                        String[] colourChoice = new String[horsesArray.length];
                        String[] accessoryChoice = new String[horsesArray.length];  

                        
                        saveButton.addActionListener(new ActionListener() {
                            @Override
                                public void actionPerformed(ActionEvent e) {
                                // Loop through the horses array                
                                for (int j=0; j<horsesArray.length; j++) {
                                    if (horsesArray[j] == null) {
                                        continue;
                                    } else {
                                        int valueColor = horseTempSliderColor[j].getValue();
                                        switch (valueColor) {
                                            case 0:
                                                colourChoice[j] = "Black";
                                                break;
                                            case 1:
                                                colourChoice[j] = "Dun";
                                                break;
                                            case 2:
                                                colourChoice[j] = "Bay";
                                                break;
                                        }
                                        int valueAccessory = horseTempSliderAccessory[j].getValue();
                                        switch (valueAccessory) {
                                            case 0:
                                                accessoryChoice[j] = "Hat";
                                                break;
                                            case 1:
                                                accessoryChoice[j] = "Saddle";
                                                break;
                                        }
                                    
                                        Horse h = horsesArray[j];
                                        h.setAccesory(accessoryChoice[j]);
                                        h.setColour(colourChoice[j]);
                                        try{
                                            String breed = horseTempBreedFields[j].getText();
                                            if (breed.equals("")){
                                                horseTempBreedFields[j].setText("");
                                                horseTempBreedFields[j].setBackground(Color.RED);
                                            } else {
                                                h.setBreed(breed);
                                            }
                                        } catch (NullPointerException e1) {
                                            horseTempBreedFields[j].setText("");
                                        }
                                        
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Horse customization saved. Please click start to being.");
                                customizeFrame.dispose();
                                customized = true;
                            }
                        });
                    }
                }
                customizeFrame.setVisible(true);
            }
        }); // END of start button action listener
    }

    
}