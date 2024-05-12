package Main;

import java.awt.*;
import java.util.Random;
//This class is for the player icon, the one controlled but the user :
public class Player extends GameObject{

    //Attributes :
    private Random r=new Random();
    Handler handler;

    //Constructor :
    public Player(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler= handler;
        //velX=r.nextInt(5)+1; //set the speed in x (the x position/coordinate will increase of 1 each time)
        //velY=r.nextInt(5); //set the speed in y (the y position/coordinate will increase of 1 each time)
    }

    //What happens when the game starts running :
    public void tick(){
        x+=velX; //updates the x position making the object move
        y+=velY; //updates the y position making the object move
        //set our player in the window frame limit
        x=Game.clamp((int)x,0,Game.WIDTH-37);
        y=Game.clamp((int)y,0,Game.HEIGHT-60);

        collision();
    }

    //Treat collisions and update the Health bar accordingly :
    private void collision(){
        for (int i=0; i<handler.object.size(); i++){
            GameObject tempObject=handler.object.get(i); //getting through all objects (tempObject=enemies)
            if (tempObject.getId()==ID.BasicEnemy || tempObject.getId()==ID.FastEnemy || tempObject.getId()==ID.SmartEnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    //collision happens
                    HUD.HEALTH-=2;
                }
            }
        }
    }

    //Setting the graphics (visual of Player)
    public void render(Graphics graphics){
        if (id == ID.Player) graphics.setColor(Color.white); //set the blocks color
        graphics.fillRect((int)x,(int)y,32,32);//creates the blocks

    }
    //Get the information of the player as a rectangle to determine when there are collisions (in Player Class)
    public Rectangle getBounds(){
        return  new Rectangle((int)x,(int)y,32,32);
    }


}
