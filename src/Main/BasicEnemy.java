package Main;

import java.awt.*;

//This class for the conception of the lightGreen basic enemies (normal speed and no special particularities in their movements)
public class BasicEnemy  extends GameObject{
    private Handler handler;
    public BasicEnemy (int x, int y, ID id, Handler handler){
        super (x,y,id);
        this.handler=handler;
        //Setting the velocity of those enemies
        velX=5;
        velY=5;
    }

    //What happens while the game runs:
    public void tick(){
        //Making the enemies move
        x+= velX;
        y+=velY;

        //Setting the window frame dimension as limits so the enemies don't go out of it
        if (y<=0 || y>= Game.HEIGHT -32 ) velY *= -1;
        if (x<=0 || x>= Game.WIDTH - 16 ) velX *= -1;

        //Adding a trail to the enemies:
        handler.addObject(new Trail(x,y,ID.Trail,new Color(144,238,144), 16,16,0.04f, handler));
    }

    //Setting the graphics (visual) of those enemies :
    public void render(Graphics graphics){
        graphics.setColor(new Color(144,238,144)); //setting them in lightGreen
        graphics.fillRect((int)x,(int)y,16,16); //dimension of color filling (use same dimensions of the one set to have them plainly colored)
    }

    //Get the information of this enemy as a rectangle to determine when there are collisions (in Player Class)
    public Rectangle getBounds(){
        return  new Rectangle((int)x,(int)y,16,16);
    }
}
