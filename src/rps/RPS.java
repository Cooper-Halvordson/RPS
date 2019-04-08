package rps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//This class inherits the JPanel class so other components can be added to it.
public class RPS extends JPanel {

    //There is work to be done in the constructor and the determineWinner methods.

    //ENUMERATION created for switch statements
    //It is similar to a variable/object and can be used as such
    private enum Outcome {
        WIN,
        LOSE,
        TIE
    }

    //Values of game pieces
    private final static int ROCK = 0;
    private final static int PAPER = 1;
    private final static int SCISSORS = 2;

    //User choice
    private int userValue;
    //Comp choice
    private int compValue;

    //This is a button. It will be used to set the userValue to ROCK.
    JButton rockButton;
    //Create a button for PAPER
    //Create a button of SCISSORS

    //This is an image. It is used as a visual representation for ROCK.
    ImageIcon rockIcon = createImageIcon("images/rock.jpg");
    //Create an image for PAPER
    //Create an image for SCISSORS

    //This is used to determine if the player has entered a value and makes
    //sure another value cannot be entered.
    boolean enteredValue = false;

    //This is a constructor for RPS.
    public RPS() {
        //This is a Layout format used to arrange the components of a panel.
        //This sets the layout to be added in the shape of a column.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //These are panels used to store data.
        //The buttonPane holds the buttons.
        JPanel buttonPane = new JPanel();
        JPanel enteredPane = new JPanel();

        //Create a BoxLayout object with the name buttonLayout.
        //In the constructor set the to (buttonPane, BoxLayout.X_AXIS)
        
        // This sets the layout of buttonPane to follow buttonLayout.
        //Uncomment this out after the BoxLayout is declared and initialized.
        //buttonPane.setLayout(buttonLayout);

        JLabel txt = new JLabel("");
        txt.setVisible(false);

        //This sets up the button for the Rock button.
        //The image is displayed on top of the text.
        rockButton = new JButton("Rock", rockIcon);
        rockButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        rockButton.setHorizontalTextPosition(AbstractButton.CENTER);
        //This is an action listener. Everytime this button is pressed this code is ran.
        rockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //The code from here is ran when the button is pressed.
                if (!enteredValue) {
                    //Set the user value.
                    setUserValue(ROCK);
                    txt.setVisible(true);
                    enteredValue = true;
                    //Run the code to determine the winner.
                    //This means getting the computer value and comparing them.
                    determineWinner();
                }
            }
        });

        //Create a button for PAPER 
        //Create a button for SCISSORS.
        
        //Add the button to the pane.
        buttonPane.add(rockButton);
        //Add the paper button to buttonPane.
        //Add the scissors button to buttonPane.

        //Add buttonPane to the RPS class.
        add(buttonPane);

        enteredPane.add(txt);
        add(enteredPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Rock Paper Scissors");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                RPS rps = new RPS();
                rps.setOpaque(true);
                frame.setContentPane(rps);
                frame.setSize(360, 250);
                
                frame.setVisible(true);

            }
        });
       
    }

    public void determineWinner() {
        //This does the part of doing the rest of the game
        //Get computer result
        //Display Results
        setCompValue();

        //Create a panel to determine the winner.
        //Create a JPanel called vsLine. Declare and initialize the variable.

        //<panelName>.setLayout(new BoxLayout(vsLine, BoxLayout.LINE_AXIS));setLayout(new BoxLayout(vsLine, BoxLayout.LINE_AXIS));
        //Add a JLabel for the images of the user and computer hands.
        //<panelName.add(new JLabel(getImage(userValue)));
        //The getImage method is a method created for this program.
        //<panelName>.add(new JLabel("VS"));
        //Add a JLabel for the computer's value image.

        //add the panel for the vs line to the RPS class.

        //Use the enumeration and the compareHands method to determine
        //who the winner is.
        Outcome o = compareHands(userValue, compValue);
        //This label is used to display the outcome.
        JLabel results = new JLabel();
        switch(o) {
            case WIN:
                results.setText("YOU WIN");
                break;
            case LOSE:
                results.setText("YOU LOSE");
                break;
            case TIE:
                //Set the results text value to "YOU TIE".
                break;
        }
        add(results);
    }
    public void displayOptions() {
        System.out.printf("Enter %d for rock\nEnter %d for paper\nEnter %d for scissors\n",ROCK,PAPER,SCISSORS);
    }
    public void setUserValue(int i) {
        if (i <= 2 && i >= 0) {
            userValue = i;
        }
        else {
            userValue = 0;
        }
    }
    public boolean inputUserValue() {
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int i = input.nextInt();
            input.close();
            if (i >= 0 && i <= 2) {
                userValue = i;
                return true;
                
            }
            else {
                System.out.println("Entered value not in valid range");
                return false;
            }
        }
        else {
            System.out.println("Not a valid input");
            input.close();
            return false;
        }
    }
    public void setCompValue() {
        compValue = (int)(Math.random() * 3);
    }
    public int getUserValue() {
        return userValue;
    }

    public int getCompValue() {
        return compValue;
    }

    public String translate(int hand){
        if (hand == 0) {
            return "ROCK";
        }
        else if (hand == 1) {
            return "PAPER";
        }
        else if (hand == 2) {
            return "SCISSORS";
        }
        else {
            return "INVALID";
        }
    }

    public ImageIcon getImage(int hand) {
        if (hand == 0) {
            return rockIcon;
        }
        else if (hand == 1) {
            return paperIcon;
        }
        else if (hand == 2) {
            return scissorsIcon;
        }
        else {
            return null;
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = RPS.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private Outcome compareHands(int handOne, int handTwo) {
        /*
         * Use subtraction to determine winner
         * -  0| 1| 2
         * 0  0|-1|-2
         * 1  1| 0|-1
         * 2  2| 1| 0
         * 
         * Using the values set for Rock, Paper, and Scissors, using the subtraction of the left comumn to
         * the top row, when the value is 1 or -2, it is a win, the value is -1 or 2, it is a loss, and the
         * value is 0, it is a tie.
         */
        int outcome = handOne - handTwo;
        if (outcome == 1 || outcome == -2 ) {
            return Outcome.WIN;
        }
        else if (outcome == -1 || outcome == 2) {
            return Outcome.LOSE;
        }
        else {
            return Outcome.TIE;
        }
    }

}
