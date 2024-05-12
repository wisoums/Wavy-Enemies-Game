package Main;

import javax.swing.*;
import java.awt.*;
//Basic Window Class like in any Game to set the dimensions and other simple features not related to the game itself
public class Window extends Canvas {

    //Constructor :
    public Window (int width, int height, String title, Game game){
        //Creating a jFrame
        JFrame jFrame = new JFrame(title);

        //setting the dimensions of the frame
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setMaximumSize(new Dimension(width, height));
        jFrame.setMinimumSize(new Dimension(width, height));

        //Basics :
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows the red button to quit the program
        jFrame.setResizable(false); //the user can't resize the frame dimension (eg. extend it)
        jFrame.setLocationRelativeTo(null); //frame appears in the middle of the screen
        jFrame.add(game); //adding the game we're building in the frame we just built
        jFrame.setVisible(true); //be able to see the game (make it visible)
        game.start();
    }
}
