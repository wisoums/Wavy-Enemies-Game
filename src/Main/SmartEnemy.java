package Main;

import java.awt.*;
//This class for the conception of the turquoise smart enemies (slower speed, and they follow the Player Icon)

public class SmartEnemy extends GameObject{

    //Attributes :
    private Handler handler;
    private GameObject player;

    //Constructor :
    public SmartEnemy (int x, int y, ID id, Handler handler){
        super (x,y,id);
        this.handler=handler;

        //setting the attribute player to the real object Player
        for (int i =0; i<handler.object.size(); i++){
            if (handler.object.get(i).getId()==ID.Player) player=handler.object.get(i);
        }

        //Setting the velocity
        velX=9;
        velY=9;
    }

    //What happens when the Game runs:

    public void tick(){

        //Making the enemies move
        x+= velX;
        y+=velY;

        //Algorithm to make the enemy follow the player
        float diffX = x-player.getX();
        float diffY = y-player.getY();
        float distance = (float)(Math.sqrt((x-player.getX())*(x- player.getX()) + (y-player.getY())*(y-player.getY())));

        velX = (float)((-1.0/distance)*diffX);
        velY = (float)((-1.0/distance)*diffY);

        //Setting the window frame dimension as limits so the enemies don't go out of it
        if (y<=0 || y>= Game.HEIGHT -32 ) velY *= -1;
        if (x<=0 || x>= Game.WIDTH - 16 ) velX *= -1;

        //Adding a trail to the enemies:
        handler.addObject(new Trail(x,y,ID.Trail,new Color(64,224,208), 16,16,0.04f, handler));

    }

    //Setting the graphics (visual of the Smart Enemies) :
    public void render(Graphics graphics){
        graphics.setColor(new Color(64,224,208));
        graphics.fillRect((int)x,(int)y,16,16);
    }

    //Get the information of this enemy as a rectangle to determine when there are collisions (in Player Class)
    public Rectangle getBounds(){
        return  new Rectangle((int)x,(int)y,16,16);
    }
}
