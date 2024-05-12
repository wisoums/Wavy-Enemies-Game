package Main;

import java.awt.*;
//This class is to create the tail behind the enemies
public class Trail extends GameObject{

    //Attributes :
    private float alpha = 1; //create a fading effect
    private Handler handler;
    private float life;
    private Color color;
    private int width;
    private int height;

    //Constructor :
    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler){
        super (x,y,id);
        this.handler=handler;
        this.color=color;
        this.width=width;
        this.height=height;
        this.life=life;
    }

    //What happens when the game runs :
    public void tick() {
        //Creates a fading effect in a way that the tail moves with the enemy, otherwise it would just let a permanent trail in the window frame of the game
        if (alpha>life){
            alpha-=(life-0.0001f);
        } else{ handler.removeObject(this);}
    }

    //Setting the graphics (visual of the Trail)
    public void render(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setComposite(makeTransparent(alpha));

        graphics.setColor(color); //color of the trail
        graphics.fillRect((int)x,(int)y,width,height); //color filling is the dimensions of the square composing the trail
        graphics2D.setComposite(makeTransparent(1));

    }

    //Will make the end of the trail transparent
    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }

    //No use here (but abstract method so has to be overwritten) since the collision between the player and the trail doesn't impact the Health bar
    public Rectangle getBounds() {
        return null;
    }
}
