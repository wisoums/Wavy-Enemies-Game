package Main;

import java.awt.*;
//This class is for the conception of the purple fast enemies (faster speed than the basic enemies and their velocity directions are a bit different)
public class FastEnemy extends GameObject{

    //Attributes :
    private Handler handler;

    //Constructor :
    public FastEnemy (float x, float y, ID id, Handler handler){
        super (x,y,id);
        this.handler=handler;
        //Setting the velocity (higher in Y)
        velX=2;
        velY=9;
    }
    //What happens when the game runs :
    public void tick(){
        //Making the enemies move
        x+= velX;
        y+=velY;

        //Setting the window frame dimension as limits so the enemies don't go out of it
        if (y<=0 || y>= Game.HEIGHT -32 ) velY *= -1;
        if (x<=0 || x>= Game.WIDTH - 16 ) velX *= -1;

        //Adding a trail to the enemies:
        handler.addObject(new Trail(x,y,ID.Trail, new Color(221,160,221), 16,16,0.04f, handler));
    }

    //Setting the graphics (visual of Fast Enemies)
    public void render(Graphics graphics){
        graphics.setColor(new Color(221,160,221)); //setting them in purple
        graphics.fillRect((int)x,(int)y,16,16); //dimension of color filling (use same dimensions of the one set to have them plainly colored)
    }

    //Get the information of this enemy as a rectangle to determine when there are collisions (in Player Class)
    public Rectangle getBounds(){
        return  new Rectangle((int)x,(int)y,16,16);
    }
}
