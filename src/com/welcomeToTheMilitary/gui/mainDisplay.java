package com.welcomeToTheMilitary.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainDisplay {
    private Container container;

    //Title Screen Fields
    private JPanel titleNamePanel;
    private JLabel titleNameLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN,50);
    private JLabel titleImage;
    private JPanel titleImagePanel;
    private JButton startButton;
    private JPanel startButtonPanel;
    private boolean ClickTitle = false;

    //Main Game Fields
    private JPanel rootPanel;
    private JTextField userActionBox;
    private JLabel mainText;
    private JLabel playerStats;
    private JLabel displayBaseMap;

    public mainDisplay() {
        JFrame frame = new JFrame("Welcome to the Military!");
        frame.setPreferredSize(new Dimension(700, 500));
        //frame.setContentPane(new mainDisplay().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(null);
        frame.pack();
        container = frame.getContentPane();

        //Sends our JFrame to title screen

        titleScreen(container);


        System.out.println("Success");


        frame.setVisible(true);
    }

    public void titleScreen(Container container) {


        //Title Screen
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 500, 100);
        titleNamePanel.setBackground(Color.DARK_GRAY);

        //Title Screen Label
        titleNameLabel = new JLabel("Welcome to the Military!");
        titleNameLabel.setFont(titleFont);
        titleNameLabel.setForeground(Color.white);

//        Title Image
        titleImagePanel = new JPanel();
        titleImagePanel.setBounds(295,230, 100,97);
        //titleImagePanel.setBackground(Color.BLUE);

        ImageIcon icon = new ImageIcon("lib/pvt_small.png");
        titleImage = new JLabel(icon);

        //Start Button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(250,350,200,75);
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

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                gameScreen(container);
            }
        });


    }

    public void gameScreen(Container container) {


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
    }

    public static void main(String[] args) {
        new mainDisplay();

    }


}
