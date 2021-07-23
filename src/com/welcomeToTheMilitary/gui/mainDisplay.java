package com.welcomeToTheMilitary.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainDisplay {
    JFrame frame = new JFrame("Welcome to the Military!");
    private Container container;

    //Title Screen Fields
    private JPanel titleNamePanel;
    private JLabel titleNameLabel;
    private Font titleFont = new Font("Arial", Font.PLAIN,50);
    private JLabel titleImage;
    private JPanel titleImagePanel;
    private JButton startButton;
    private JPanel startButtonPanel;
    private TitleScreenButton tsButton = new TitleScreenButton();


    //Main Game Fields
    private JPanel mainTextPanel;
    private static JTextArea mainTextArea;
    private Font mainTextFont = new Font("Arial", Font.PLAIN,20);
    private Font sideTextFont = new Font("Arial", Font.PLAIN,18);
    private Font sideTextSmallFont = new Font("Arial", Font.PLAIN,16);

    private JPanel mapAreaPanel;
    private JTextArea mapAreaText;

    private JPanel statsAreaPanel;
    private JTextArea statsAreaText;

    private JPanel userActionPanel;
    private JTextField userActionBox;
    private String userAction;

    //Colors
    private Color mainTextAreaColor = new Color(97,96,81);
    private Color mainBackgroundColor = new Color(82,102,70);
    private Color sidePanelColor = new Color(97,96,81);
    private Color sidePanelTextColor = Color.WHITE;

    public mainDisplay() {

        frame.setPreferredSize(new Dimension(800, 650));
        //frame.setContentPane(new mainDisplay().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(mainBackgroundColor);
        frame.setLayout(null);
        frame.pack();
        container = frame.getContentPane();

        //Sends our JFrame to title screen

        titleScreen();

        frame.setVisible(true);
    }

    public void titleScreen() {


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

    public void gameScreen() {
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
        mainTextArea = new JTextArea(" when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
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
        mapAreaText = new JTextArea("Testing the Text on the Map");
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
        statsAreaText = new JTextArea("Testing Stats:");
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
        this.userAction = "";

        //Listen for Enter


//        userActionBox.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                userActionBox.setText("");
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                super.focusLost(e);
//                userActionBox.setText("Enter action (noun + verb)");
//            }
//        });

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
// Handler
    public class TitleScreenButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            gameScreen();
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

    public JTextArea getMapAreaText() {
        return mapAreaText;
    }

    public void setMapAreaText(String mapAreaString) {
        this.mapAreaText.setText(mapAreaString);
        frame.revalidate();
        frame.repaint();
    }

    public JTextArea getStatsAreaText() {
        return statsAreaText;
    }

    public void setStatsAreaText(String statsAreaTextString) {
        this.statsAreaText.setText(statsAreaTextString);
        frame.revalidate();
        frame.repaint();
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }
}
