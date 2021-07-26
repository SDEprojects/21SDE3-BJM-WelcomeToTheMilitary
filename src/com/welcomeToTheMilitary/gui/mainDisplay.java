package com.welcomeToTheMilitary.gui;

import com.welcomeToTheMilitary.gamecontroller.GameController;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class mainDisplay {
    private static JFrame frame = new JFrame("Welcome to the Military!");
    private static Container container;

    //Title Screen Fields
    private static JPanel titleNamePanel;
    private static JLabel titleNameLabel;
    private static Font titleFont = new Font("Arial", Font.PLAIN, 50);
    private static JLabel titleImage;
    private static JPanel titleImagePanel;
    private static JButton startButton;
    private static JPanel startButtonPanel;
    private static TitleScreenButton tsButton = new TitleScreenButton();
    private static boolean startButtonClicked = false;


    //Tutorial Screen Fields
    private static JPanel tutorialTitlePanel;
    private static JLabel tutorialTitleLabel;
    private static JPanel tutorialMainPanel;
    private static JTextArea tutorialMainText;
    private static Font tutorialFont = new Font("Arial", Font.PLAIN, 26);
    private static Font tutorialMainFont = new Font("Arial", Font.PLAIN, 18);
    private static JButton createCharacterButton;
    private static JPanel characterButtonPanel;
    private static TutorialScreenButton tutButton = new TutorialScreenButton();

    //New Player Form
    private static JPanel newPlayerTitlePanel;
    private static JLabel newPlayerTitleLabel;
    private static JPanel newPlayerNameTextPanel;
    private static JTextField newPlayerNameField;
    private static JPanel specialsPanel;
    private static JPanel specialDescriptionPanel;
    private static JTextArea specialDescriptionTextArea;
    private static JRadioButton special1Button;
    private static JRadioButton special2Button;
    private static JRadioButton special3Button;
    private static JRadioButton special4Button;
    private static ButtonGroup specialsButtonGroup;
    private static JPanel newPlayerButtonPanel;
    private static JButton newPlayerButton;



    //Main Game Fields
    private static JPanel mainTextPanel;
    private static JTextArea mainTextArea;
    private static Font mainTextFont = new Font("Arial", Font.PLAIN, 20);
    private static Font sideTextFont = new Font("Arial", Font.PLAIN, 18);
    private static Font sideTextSmallFont = new Font("Arial", Font.PLAIN, 16);

    private static JPanel mapAreaPanel;
    private static JTextArea mapAreaText;

    private static JPanel statsAreaPanel;
    private static JTextArea statsAreaText;

    private static JPanel userActionPanel;
    private static JTextField userActionBox;
    private static String userAction;

    //Colors
    private static Color mainTextAreaColor = new Color(97, 96, 81);
    private static Color mainBackgroundColor = new Color(82, 102, 70);
    private static Color sidePanelColor = new Color(97, 96, 81);
    private static Color sidePanelTextColor = Color.WHITE;

    //grab service member after gui builds
    private static String name;
    private static String special;
    private static String base = "Fort Sill";


    public static void titleScreen() {

        if (!startButtonClicked) {
            //Title Screen
            titleNamePanel = new JPanel();
            titleNamePanel.setBounds(150, 100, 550, 100);
            titleNamePanel.setBackground(mainBackgroundColor);

            //Title Screen Label
            titleNameLabel = new JLabel("Welcome to the Military!");
            titleNameLabel.setFont(titleFont);
            titleNameLabel.setForeground(Color.white);

//        Title Image
            titleImagePanel = new JPanel();
            titleImagePanel.setBounds(350, 230, 100, 97);
            //titleImagePanel.setBackground(Color.BLUE);

            ImageIcon icon = new ImageIcon("lib/pvt_small.png");
            titleImage = new JLabel(icon);

            //Start Button Panel
            startButtonPanel = new JPanel();
            startButtonPanel.setBounds(300, 350, 200, 75);
            startButtonPanel.setBackground(mainBackgroundColor);

            //Start Button
            startButton = new JButton("Click to Start");
            startButton.setBackground(Color.DARK_GRAY);
            startButton.setForeground(Color.white);
            startButton.setFont(new Font("Arial", Font.PLAIN, 26));


            //Adds title screen to main container
            titleImagePanel.add(titleImage);
            titleNamePanel.add(titleNameLabel);
            startButtonPanel.add(startButton);

            container.add(startButtonPanel);
            container.add(titleNamePanel);
            container.add(titleImagePanel);

            startButton.addActionListener(tsButton); // sends button click to TSButtonAction
        }
        frame.revalidate();
        frame.repaint();

    }



    //Welcome Screen Tutorial GUI
    public static void tutorialScreen() {


        container.remove(startButtonPanel);//
        container.remove(titleNamePanel);// Not working for some reason..
        container.remove(startButton);
        container.remove(titleImagePanel);
        container.remove(titleNameLabel);
        container.remove(titleImage);


        //Doing this to not run tutorial before start button is pushed
        String tutorialTextString = "You start off as a warrior. Your goal is to climb the ranks any way possible to" +
                "achieve the highest rank available to become the strongest Warrior in the Army.\n" +
                "The grades and ranks are as follows:  \n" +
                "E-1/Private\n" + "E-2/Private Second Class\n" +
                "E-3/Private First Class\n" +
                "E-4/Corporal or Specialist\n" +
                "E-5/Sergeant\n" +
                "E-6/Staff Sergeant\n" +
                "E-7/Sergeant First Class\n" +
                "E-8/Master Sergeant or First Sergeant\n" +
                "E-9/Command Sergeant Major or Sergeant Major of the Army\n" +
                "First Sergeant and Sergeant Major of the Army are both ranks that one must be appointed to and are limited to a select few.\n";


        //Tutorial Title Panel
        tutorialTitlePanel = new JPanel();
        tutorialTitlePanel.setBounds(75, 20, 650, 50);
        tutorialTitlePanel.setBackground(mainBackgroundColor);
        tutorialTitlePanel.setForeground(Color.white);
        //Label
        tutorialTitleLabel = new JLabel("Military Tutorial");
        tutorialTitleLabel.setFont(tutorialFont);
        tutorialTitleLabel.setForeground(Color.white);
        tutorialTitlePanel.add(tutorialTitleLabel);

        //Tutorial Main Panel
        tutorialMainPanel = new JPanel();
        tutorialMainPanel.setBounds(75, 80, 650, 400);
        tutorialMainPanel.setBackground(mainTextAreaColor);
        tutorialMainPanel.setForeground(Color.WHITE);
        //Tut Main Text
        tutorialMainText = new JTextArea(tutorialTextString);
        tutorialMainText.setBounds(75, 100, 650, 400);
        tutorialMainText.setBackground(mainTextAreaColor);
        tutorialMainText.setForeground(Color.WHITE);
        tutorialMainText.setFont(tutorialMainFont);
        tutorialMainText.setLineWrap(true);
        tutorialMainPanel.add(tutorialMainText);

        //Button Panel
        characterButtonPanel = new JPanel();
        characterButtonPanel.setBounds(270,550,215,75);
        characterButtonPanel.setBackground(mainBackgroundColor);

        //Create a Character Button
        createCharacterButton = new JButton("Create Character ");
        createCharacterButton.setBackground(Color.DARK_GRAY);
        createCharacterButton.setForeground(Color.white);
        createCharacterButton.setFont(new Font("Arial", Font.PLAIN, 26));
        characterButtonPanel.add(createCharacterButton);

        createCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCharacter();
            }
        });

        //Add stuff to container
        container.add(tutorialTitlePanel);
        container.add(tutorialMainPanel);
        container.add(characterButtonPanel);

        frame.revalidate();
        frame.repaint();
    }

    public static void createCharacter() {
        container.remove(tutorialTitlePanel);
        container.remove(tutorialMainPanel);
        container.remove(characterButtonPanel);

        //Remove Tutorial Main Panel Stuff
        //container.remove(tutorialMainPanel);

        String specialDescription = "Dog Tags: You always wear your dog tags outside fo your shirt. Your military prides deflects attacks.\n \n" +
                "Military SkullTattoo: After Basic Training you got a tattoo of your company. Army Strong! Queen of Battle!\n \n" +
                "High-n-Tight: Your hair is always within regulations and it increases your attacks.\n \n"+
                "Punisher Hat: You Always have your Punisher hat on because you want every one to know not to mess with you.";


        //Create Character Panel Title
        newPlayerTitlePanel = new JPanel();
        newPlayerTitlePanel.setBounds(75, 20, 650, 50);
        newPlayerTitlePanel.setBackground(mainTextAreaColor);
        newPlayerTitlePanel.setForeground(Color.WHITE);
        //Label
        newPlayerTitleLabel = new JLabel("Create Character");
        newPlayerTitleLabel.setFont(tutorialFont);
        newPlayerTitleLabel.setForeground(Color.white);
        newPlayerTitlePanel.add(newPlayerTitleLabel);

        //Name Panel
        newPlayerNameTextPanel = new JPanel();
        newPlayerNameTextPanel.setBounds(75, 80, 300, 50);
        newPlayerNameTextPanel.setBackground(mainTextAreaColor);
        newPlayerNameTextPanel.setForeground(Color.WHITE);
        //Field
        newPlayerNameField = new JTextField(15);
        newPlayerNameField.setBounds(75, 80, 300, 50);
        newPlayerNameField.setText("Enter Player Name");
        newPlayerNameTextPanel.add(newPlayerNameField);

        //Specials Panel
        specialsPanel = new JPanel();
        specialsPanel.setBounds(75, 150, 300, 100);
        specialsPanel.setBackground(mainTextAreaColor);
        specialsPanel.setForeground(Color.white);

        //Specials
        special1Button = new JRadioButton("Dog Tags");
        special1Button.setActionCommand("Dog Tags");
        special2Button = new JRadioButton("Military SkullTattoo");
        special2Button.setActionCommand("Military SkullTattoo");
        special3Button = new JRadioButton("High-n-Tight");
        special3Button.setActionCommand("High-n-Tight");
        special4Button = new JRadioButton("Punisher Hat");
        special4Button.setActionCommand("Punisher Hat");

        specialsButtonGroup = new ButtonGroup();
        specialsButtonGroup.add(special1Button);
        specialsButtonGroup.add(special2Button);
        specialsButtonGroup.add(special3Button);
        specialsButtonGroup.add(special4Button);
        specialsPanel.add(special1Button);
        specialsPanel.add(special2Button);
        specialsPanel.add(special3Button);
        specialsPanel.add(special4Button);

        //Specials Description Panel
        specialDescriptionPanel = new JPanel();
        specialDescriptionPanel.setBounds(400, 80, 350, 375);
        specialDescriptionPanel.setBackground(mainTextAreaColor);
        specialDescriptionPanel.setForeground(Color.white);

        //Specials Description Text Area
        specialDescriptionTextArea = new JTextArea();
        specialDescriptionTextArea.setBounds(400, 80, 350, 375);
        specialDescriptionTextArea.setText(specialDescription);
        specialDescriptionTextArea.setLineWrap(true);
        specialDescriptionTextArea.setBackground(mainTextAreaColor);
        specialDescriptionTextArea.setForeground(Color.WHITE);
        specialDescriptionTextArea.setFont(sideTextSmallFont);
        specialDescriptionPanel.add(specialDescriptionTextArea);

        //Submit
        newPlayerButtonPanel = new JPanel();
        newPlayerButtonPanel.setBounds(75, 300, 200, 60);
        newPlayerButtonPanel.setBackground(mainTextAreaColor);
        newPlayerButtonPanel.setForeground(Color.white);

        //Submit Button
        newPlayerButton = new JButton("Create");
        newPlayerButton.setBounds(75, 300, 200, 60);
        newPlayerButton.setBackground(mainTextAreaColor);
        newPlayerButton.setForeground(Color.white);
        newPlayerButtonPanel.add(newPlayerButton);

        //Add Stuff to container
        container.add(newPlayerTitlePanel);
        container.add(newPlayerNameTextPanel);
        container.add(specialsPanel);
        container.add(specialDescriptionPanel);
        container.add(newPlayerButtonPanel);

        //Some listeners here
        //Delete default text on name field
        newPlayerNameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newPlayerNameField.setText("");
            }

        });

        newPlayerButton.addActionListener(e -> {
            name = newPlayerNameField.getText();
            special = specialsButtonGroup.getSelection().getActionCommand(); // Grabs text string from radio buttons

            gameScreen();
            setStartButtonClicked(true);
        });
    }

    public static void gameScreen() {


        //Main Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(30, 35, 500, 430);
        mainTextPanel.setBackground(mainTextAreaColor);
        mainTextPanel.setForeground(Color.WHITE);

        //Main Text Area
        mainTextArea = new JTextArea(" ");
        mainTextArea.setBounds(30, 35, 500, 430);//Same as mainTextPanel
        mainTextArea.setBackground(sidePanelColor);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(mainTextFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        //Map Area
        mapAreaPanel = new JPanel();
        mapAreaPanel.setBounds(550, 35, 210, 250);
        mapAreaPanel.setBackground(sidePanelColor);
        mapAreaPanel.setForeground(Color.WHITE);

        //Map Area Text
        mapAreaText = new JTextArea("");
        mapAreaText.setBounds(550, 35, 210, 250);
        mapAreaText.setBackground(sidePanelColor);
        mapAreaText.setForeground(Color.WHITE);
        mapAreaText.setFont(sideTextFont);
        mapAreaText.setLineWrap(true);
        mapAreaPanel.add(mapAreaText);

        //Stats Area
        statsAreaPanel = new JPanel();
        statsAreaPanel.setBounds(550, 300, 210, 275);
        statsAreaPanel.setBackground(sidePanelColor);
        statsAreaPanel.setForeground(Color.WHITE);

        //Stats Area Text
        statsAreaText = new JTextArea("");
        statsAreaText.setBounds(550, 300, 210, 275);
        statsAreaText.setBackground(sidePanelColor);
        statsAreaText.setForeground(Color.WHITE);
        statsAreaText.setFont(sideTextSmallFont);
        statsAreaText.setLineWrap(true);
        statsAreaPanel.add(statsAreaText);

        //Input Text Area
        userActionPanel = new JPanel();
        userActionPanel.setBounds(30, 500, 500, 75);
        userActionPanel.setBackground(sidePanelColor);
        userActionPanel.setForeground(Color.YELLOW);

        //Input Text Box
        userActionBox = new JTextField(40);
        userActionBox.setBounds(30, 500, 500, 75);
        userActionBox.setBackground(Color.WHITE);
        userActionBox.setForeground(Color.BLACK);
        userActionBox.setText("");
        userActionPanel.add(userActionBox);

        //Add Panels to Container
        container.add(mainTextPanel);
        container.add(mapAreaPanel);
        container.add(statsAreaPanel);
        container.add(userActionPanel);

        //Clear user action first
        userAction = "";

        //Listen for Enter

        //Handler for text field.  Sets UserAction string for textParser Processing
        userActionBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setUserAction(userActionBox.getText());
                    userActionBox.setText("");
                }
            }
        });

        frame.revalidate();
        frame.repaint();


    }

    // Handlers
    public static class TitleScreenButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tutorialScreen();


        }
    }
    public static class TutorialScreenButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createCharacter();
            System.out.println("button clicked");
        }
    }

    //Getters & Setters for text areas
    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public static void setMainTextArea(String mainTextString) {
        mainTextArea.setText(mainTextString);
        frame.revalidate();
        frame.repaint();
    }

    public static JTextArea getMapAreaText() {
        return mapAreaText;
    }

    public static void setMapAreaText(String mapAreaString) {
        mainDisplay.mapAreaText.setText(mapAreaString);
        frame.revalidate();
        frame.repaint();
    }

    public static JTextArea getStatsAreaText() {
        return statsAreaText;
    }

    public static void setStatsAreaText(String statsAreaTextString) {
        mainDisplay.statsAreaText.setText(statsAreaTextString);
        frame.revalidate();
        frame.repaint();
    }

    public static String getUserAction() {
        return userAction;
    }

    public static void setUserAction(String _userAction) {
        userAction = _userAction;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        mainDisplay.name = name;
    }

    public static String getSpecial() {
        return special;
    }

    public static void setSpecial(String special) {
        mainDisplay.special = special;
    }

    public static boolean isStartButtonClicked() {
        return startButtonClicked;
    }

    public static void setStartButtonClicked(boolean startButtonClicked) {
        mainDisplay.startButtonClicked = startButtonClicked;
    }

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        frame.setPreferredSize(new Dimension(800, 650));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(mainBackgroundColor);
        frame.setLayout(null);
        frame.pack();
        container = frame.getContentPane();

        //Sends our JFrame to title screen
        frame.setVisible(true);

        GameController game = new GameController();
        boolean runTitle = true;
        boolean runTutorial = true;
        boolean runCharacterScreen = true;


        while (true) {

            if (runTitle) {
                titleScreen();
                runTitle = false;
            }

//
//            while (runTutorial) {
//                tutorialScreen();
//                runTutorial = false;
//            }

            if (isStartButtonClicked()) {

                game.mainGame();
                frame.revalidate();
                frame.repaint();
            }
            frame.revalidate();
            frame.repaint();


        }
    }
}
