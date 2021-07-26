package com.welcomeToTheMilitary.gui;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gamecontroller.GameController;
import com.welcomeToTheMilitary.tutorial.NewWelcome;
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
    private static Font titleFont = new Font("Arial", Font.PLAIN,50);
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
    private static Font tutorialFont = new Font("Arial", Font.PLAIN,26);

    //New Player Form
    private static JPanel newPlayerTitlePanel;
    private static JLabel newPlayerTitleLabel;
    private static JTextField newPlayerNameField;
    private static JLabel special1Label;
    private static JLabel special2Label;
    private static JLabel special3Label;
    private static JLabel special4Label;
    private static JRadioButton special1Button;
    private static JRadioButton special2Button;
    private static JRadioButton special3Button;
    private static JRadioButton special4Button;
    private static ButtonGroup specialsButtonGroup;


    //Main Game Fields
    private static JPanel mainTextPanel;
    private static JTextArea mainTextArea;
    private static Font mainTextFont = new Font("Arial", Font.PLAIN,20);
    private static Font sideTextFont = new Font("Arial", Font.PLAIN,18);
    private static Font sideTextSmallFont = new Font("Arial", Font.PLAIN,16);

    private static JPanel mapAreaPanel;
    private static JTextArea mapAreaText;

    private static JPanel statsAreaPanel;
    private static JTextArea statsAreaText;

    private static JPanel userActionPanel;
    private static JTextField userActionBox;
    private static String userAction;

    //Colors
    private static Color mainTextAreaColor = new Color(97,96,81);
    private static Color mainBackgroundColor = new Color(82,102,70);
    private static Color sidePanelColor = new Color(97,96,81);
    private static Color sidePanelTextColor = Color.WHITE;

    //grab service member after gui builds
    private static String name;
    private static String special;



    public static  void titleScreen() {


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
        titleImagePanel.setBounds(350,230, 100,97);
        //titleImagePanel.setBackground(Color.BLUE);

        ImageIcon icon = new ImageIcon("lib/pvt_small.png");
        titleImage = new JLabel(icon);

        //Start Button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,350,200,75);
        startButtonPanel.setBackground(mainBackgroundColor);

        //Start Button
        startButton = new JButton("Click to Start");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Arial", Font.PLAIN,26));



        //Adds title screen to main container
        titleImagePanel.add(titleImage);
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        container.add(startButtonPanel);
        container.add(titleNamePanel);
        container.add(titleImagePanel);

        startButton.addActionListener(tsButton); // sends button click to TSButtonAction

        frame.revalidate();
        frame.repaint();

    }

    public  static void gameScreen(){
        container.remove(startButtonPanel);//
        container.remove(titleNamePanel);// Not working for some reason..
        container.remove(startButton);
        container.remove(titleImagePanel);
        container.remove(titleNameLabel);
        container.remove(titleImage);

        //Main Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(30,35,500,430);
        mainTextPanel.setBackground(mainTextAreaColor);
        mainTextPanel.setForeground(Color.WHITE);

        //Main Text Area
        mainTextArea = new JTextArea(" ");
        mainTextArea.setBounds(30,35,500,430);//Same as mainTextPanel
        mainTextArea.setBackground(sidePanelColor);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(mainTextFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        //Map Area
        mapAreaPanel = new JPanel();
        mapAreaPanel.setBounds(550,35,210,250);
        mapAreaPanel.setBackground(sidePanelColor);
        mapAreaPanel.setForeground(Color.WHITE);

        //Map Area Text
        mapAreaText = new JTextArea("");
        mapAreaText.setBounds(550,35,210,250);
        mapAreaText.setBackground(sidePanelColor);
        mapAreaText.setForeground(Color.WHITE);
        mapAreaText.setFont(sideTextFont);
        mapAreaText.setLineWrap(true);
        mapAreaPanel.add(mapAreaText);

        //Stats Area
        statsAreaPanel = new JPanel();
        statsAreaPanel.setBounds(550,300,210, 275);
        statsAreaPanel.setBackground(sidePanelColor);
        statsAreaPanel.setForeground(Color.WHITE);

        //Stats Area Text
        statsAreaText = new JTextArea("");
        statsAreaText.setBounds(550,300,210, 275);
        statsAreaText.setBackground(sidePanelColor);
        statsAreaText.setForeground(Color.WHITE);
        statsAreaText.setFont(sideTextSmallFont);
        statsAreaText.setLineWrap(true);
        statsAreaPanel.add(statsAreaText);

        //Input Text Area
        userActionPanel = new JPanel();
        userActionPanel.setBounds(30,500,500,75);
        userActionPanel.setBackground(sidePanelColor);
        userActionPanel.setForeground(Color.YELLOW);

        //Input Text Box
        userActionBox = new JTextField(40);
        userActionBox.setBounds(30,500,500,75);
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
    //Welcome Screen Tutorial GUI
    public static void tutorialScreen(){
        //Doing this to not run tutorial before start button is pushed


        //Tutorial Title Panel
        tutorialTitlePanel = new JPanel();
        tutorialTitlePanel.setBounds(75,30, 650, 50);
        tutorialTitlePanel.setBackground(Color.yellow);
        tutorialTitlePanel.setForeground(Color.white);
        //Label
        tutorialTitleLabel = new JLabel("Military Tutorial");
        tutorialTitleLabel.setFont(tutorialFont);
        tutorialTitleLabel.setForeground(Color.white);
        tutorialTitlePanel.add(tutorialTitleLabel);

        //Tutorial Main Panel

        //Add stuff to container
        container.add(tutorialTitlePanel);

    }
// Handler
    public static class TitleScreenButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gameScreen();
            //tutorialScreen();
            setStartButtonClicked(true);
        }
    }

//Getters & Setters for text areas
    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public static void setMainTextArea(String mainTextString) {
        mainTextArea.setText(mainTextString);
//        frame.revalidate();
//        frame.repaint();
    }

    public  static JTextArea getMapAreaText() {
        return mapAreaText;
    }

    public static void setMapAreaText(String mapAreaString) {
        mainDisplay.mapAreaText.setText(mapAreaString);
//        frame.revalidate();
//        frame.repaint();
    }

    public  static JTextArea getStatsAreaText() {
        return statsAreaText;
    }

    public static void setStatsAreaText(String statsAreaTextString) {
        mainDisplay.statsAreaText.setText(statsAreaTextString);
//        frame.revalidate();
//        frame.repaint();
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

    public  static String getSpecial() {
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

    public static void main(String[] args)  throws IOException, ParseException, InterruptedException{

        frame.setPreferredSize(new Dimension(800, 650));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(mainBackgroundColor);
        frame.setLayout(null);
        frame.pack();
        container = frame.getContentPane();

        //Sends our JFrame to title screen
        frame.setVisible(true);

        GameController game = new GameController();


        while(true) {
            if (!isStartButtonClicked()) {
                titleScreen();
            }

            while(isStartButtonClicked()) {
                game.mainGame();
            }
            frame.revalidate();
            frame.repaint();
        }


    }
}
