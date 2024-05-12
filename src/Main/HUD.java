package Main;

import java.awt.*;
//Head-Up Display. This class is to add additional information to the user like their score or level
public class HUD {

    //Attributes :
    public static float HEALTH = 100;
    private float greenValue=255;
    private int score=0;
    private int level=0;


    //What happens while the game runs :
    public void tick(){
        //Health goes from 0 to 100
        HEALTH=Game.clamp((int)HEALTH, 0, 100);
        //greenValue= Game.clamp(greenValue,0,255);
        //The green value decreases to the red color ad the health also decreases
        greenValue=(int)HEALTH*2;
        //The score keeps increasing :
        score++;

    }

    //Setting the graphics :
    public void render (Graphics graphics){

        //Setting the top left box color and dimensions
        graphics.setColor(Color.gray); //color
        graphics.fillRect(15,15,200,32); //dimension of color filling

        //setting the color to greenValue variable that keeps updating as the Health decreases
        graphics.setColor(new Color(75, (int)greenValue, 0)); //color (gradient)
        graphics.fillRect(15,15,2*(int)HEALTH,32); //dimensions of the color filling of the RECTangle

        graphics.setColor(Color.white); //puts a white border
        graphics.drawRect(15,15,200,32); //drawRect is used to draw a border to the RECTangle

        //write score and level on the game window in the top left corner
        graphics.drawString("Score: " + score, 15, 64); //drawString is used to write text
        graphics.drawString("Level: " + level, 15, 80);
    }

    //Getters & Setters :
    public void setScore(int score){
        this.score=score;
    }

    public int getScore (){
        return  score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level=level;
    }
}
