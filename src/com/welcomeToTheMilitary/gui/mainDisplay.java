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
    private Font titleFont = new Font("Times New Roman", Font.PLAIN,50);
    private JLabel titleImage;
    private JPanel titleImagePanel;
    private JButton startButton;
    private JPanel startButtonPanel;
    private TitleScreenButton tsButton = new TitleScreenButton();

    //Main Game Fields
    private JPanel mainTextPanel;
    private JTextArea mainTextArea;
    private Font mainTextFont = new Font("Times New Roman", Font.PLAIN,24);
    private Font sideTextFont = new Font("Times New Roman", Font.PLAIN,18);

    private JPanel mapAreaPanel;
    private JTextArea mapAreaText;

    private JPanel statsAreaPanel;
    private JTextArea statsAreaText;

    private JPanel userActionPanel;
    private JTextField userActionBox;

    public mainDisplay() {

        frame.setPreferredSize(new Dimension(800, 650));
        //frame.setContentPane(new mainDisplay().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
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
        titleNamePanel.setBounds(150, 100, 500, 100);
        titleNamePanel.setBackground(Color.DARK_GRAY);

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
        startButtonPanel.setBackground(Color.DARK_GRAY);

        //Start Button
        startButton = new JButton("Click to Start");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Times New Roman", Font.PLAIN,26));



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
        mainTextPanel.setBounds(30,35,500,550);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        mainTextPanel.setForeground(Color.WHITE);

        //Main Text Area
        mainTextArea = new JTextArea("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        mainTextArea.setBounds(30,35,500,550);//Same as mainTextPanel
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(mainTextFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        //Map Area
        mapAreaPanel = new JPanel();
        mapAreaPanel.setBounds(550,35,210,250);
        mapAreaPanel.setBackground(Color.LIGHT_GRAY);
        mapAreaPanel.setForeground(Color.WHITE);

        //Map Area Text
        mapAreaText = new JTextArea("Testing the Text on the Map");
        mapAreaText.setBounds(550,35,210,250);
        mapAreaText.setBackground(Color.LIGHT_GRAY);
        mapAreaText.setForeground(Color.WHITE);
        mapAreaText.setFont(sideTextFont);
        mapAreaPanel.add(mapAreaText);

        //Stats Area
        statsAreaPanel = new JPanel();
        statsAreaPanel.setBounds(550,300,210, 275);
        statsAreaPanel.setBackground(Color.LIGHT_GRAY);
        statsAreaPanel.setForeground(Color.WHITE);

        //Stats Area Text
        statsAreaText = new JTextArea("Testing Stats:");
        statsAreaText.setBounds(550,300,210, 275);
        statsAreaText.setBackground(Color.LIGHT_GRAY);
        statsAreaText.setForeground(Color.WHITE);
        statsAreaText.setFont(sideTextFont);
        statsAreaPanel.add(statsAreaText);

        //Input Text Area
        userActionPanel = new JPanel();
        userActionPanel.setBounds(30,450,500,75);
        userActionPanel.setBackground(Color.CYAN);
        userActionPanel.setForeground(Color.YELLOW);

        //Input Text Box
        userActionBox = new JTextField(40);
        userActionBox.setBounds(30,450,500,75);
        userActionBox.setBackground(Color.BLACK);
        userActionBox.setForeground(Color.WHITE);
        userActionBox.setText("Enter action (noun + verb)");
        userActionPanel.add(userActionBox);

        //Add Panels to Container
        container.add(mainTextPanel);
        container.add(mapAreaPanel);
        container.add(statsAreaPanel);
        container.add(userActionPanel);

        userActionBox.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                JOptionPane.showMessageDialog(null, event);
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });

        userActionBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                userActionBox.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                userActionBox.setText("Enter action (noun + verb)");
            }
        });
        frame.revalidate();
        frame.repaint();
    }

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

    public void setMainTextArea(String mainTextString) {
        this.mainTextArea.setText(mainTextString);
    }

    public JTextArea getMapAreaText() {
        return mapAreaText;
    }

    public void setMapAreaText(String mapAreaString) {
        this.mapAreaText.setText(mapAreaString);
    }

    public JTextArea getStatsAreaText() {
        return statsAreaText;
    }

    public void setStatsAreaText(String statsAreaTextString) {
        this.statsAreaText.setText(statsAreaTextString);
    }

    public static void main(String[] args) {
        new mainDisplay();

    }



}
