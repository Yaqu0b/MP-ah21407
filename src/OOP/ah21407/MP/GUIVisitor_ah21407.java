package OOP.ah21407.MP;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Scanner;

public class GUIVisitor_ah21407 extends JFrame implements Visitor{

    private String out;
    private Scanner in;
    private int purse;
    private Item[] items;
    private int next;
    private int maximum = 500;
    private char userAnswer;
    private boolean answered = false;
    private JButton newButton;
    private JPanel panel1;

    public GUIVisitor_ah21407(String ps, InputStream is) {
        out = ps;
        in = new Scanner(is);
        purse = 0;
        items = new Item[1000];
        next = 0;
    }

    private static final char[] yOrN = { 'y', 'n'};

    public GUIVisitor_ah21407() {
        out = "h";
        in = new Scanner("f");
        purse = 0;
        items = new Item[1000];
        next = 0;
    }

    @Override
    public void tell(String messageForVisitor) {
        JOptionPane.showMessageDialog(null, formatMessage(messageForVisitor, maximum), "Message For Visitor", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public char getChoice(String descriptioOfChoices, char[] arrayOfPossibleChoices) {
        this.getContentPane().removeAll();
        answered = false;
        userAnswer = ' ';

        this.setTitle("Choice");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setLayout(new GridLayout(0, 1, 10, 10));

        Border border = BorderFactory.createLineBorder(Color.green, 4);

        JLabel purseLabel = new JLabel();
        purseLabel.setText("Gold: " + printGold());
        purseLabel.setForeground(Color.white);
        purseLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        purseLabel.setBorder(border);
        this.add(purseLabel);

        JLabel itemLabel = new JLabel();
        itemLabel.setText(printItems());
        itemLabel.setForeground(Color.white);
        itemLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        itemLabel.setBorder(border);
        this.add(itemLabel);

        JLabel label = new JLabel();
        label.setText(descriptioOfChoices);
        label.setForeground(Color.white);
        label.setFont(new Font("Montserrat", Font.BOLD, 20));
        label.setBorder(border);
        this.add(label);

        for (int i = 0; i < arrayOfPossibleChoices.length; i++) {
            JButton newButton = new JButton();
            newButton.setName(String.valueOf(arrayOfPossibleChoices[i]));
            newButton.setFocusable(false);
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton)e.getSource();
                    String name = buttonClicked.getName();
                    userAnswer = name.charAt(0);
                    answered = true;
                }
            });
            newButton.setText(String.valueOf(arrayOfPossibleChoices[i]));
            newButton.setFocusable(false);

            this.add(newButton);
        }

        this.pack();
        this.setVisible(true);

        while (!answered) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        return userAnswer;
    }

    @Override
    public boolean giveItem(Item itemGivenToVisitor) {
        if (items[0] == null) {
            JOptionPane.showMessageDialog(null, "You have no items");
        } else {
            JOptionPane.showMessageDialog(null, "You have: ");
            JOptionPane.showMessageDialog(null, printItems());
        }
        JOptionPane.showMessageDialog(null, "You are being offered: "+itemGivenToVisitor.name);

        if (next >= items.length) {
            JOptionPane.showMessageDialog(null, "But you have no space and must decline.");
            return false;
        }
        if (getChoice("Do you accept (y/n)?", yOrN) == 'y') {
            items[next] = itemGivenToVisitor;
            next++;
            return true;
        } else return false;
    }

    @Override
    public boolean hasIdenticalItem(Item itemToCheckFor) {
        for (int i=0; i<next;i++)
            if (itemToCheckFor == items[i])
                return true;
        return false;
    }

    @Override
    public boolean hasEqualItem(Item itemToCheckFor) {
        for (int i=0; i<next;i++)
            if (itemToCheckFor.equals(items[i]))
                return true;
        return false;
    }

    @Override
    public void giveGold(int numberOfPiecesToGive) {
        JOptionPane.showMessageDialog(null, "You are given "+numberOfPiecesToGive+" gold pieces.");
        purse += numberOfPiecesToGive;
        JOptionPane.showMessageDialog(null, "You now have "+purse+" pieces of gold.");
    }

    @Override
    public int takeGold(int numberOfPiecesToTake) {
        if (numberOfPiecesToTake<0) {
            JOptionPane.showMessageDialog(null, "A scammer tried to put you in debt to the tune off "+(-numberOfPiecesToTake)+"pieces of gold, but you didn't fall for it and returned the 'loan'.");
            return 0;
        }

        int t = 0;
        if (numberOfPiecesToTake > purse) t = purse;
        else t = numberOfPiecesToTake;

        JOptionPane.showMessageDialog(null, t+" pieces of gold are taken from you.");
        purse -= t;
        JOptionPane.showMessageDialog(null, "You now have "+purse+" pieces of gold.");

        return t;
    }

    private static String formatMessage(String message, int maxDialogWidth) {
        String string;

        JLabel label = new JLabel(message);
        if (label.getPreferredSize().width > maxDialogWidth) {
            string = "<html><body><p style='width:" + maxDialogWidth + "px;'>" + message + "</p></body></html>";
        } else {
            string = "<html><body><p>" + message + "</p></body></html>";
        }
        return string;
    }
    public String printGold() {
        return Integer.toString(purse);
    }
    public String printItems() {
        String returnStatement;
        if (items[0] == null) {
            returnStatement = "You have no items.";
        } else {
            returnStatement = "Items: ";
            int i = 0;
            while (items[i] != null) {
                returnStatement = returnStatement + items[i] + ", ";
                i++;
            }
        }

        return returnStatement;
    }
}