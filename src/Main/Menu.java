package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import Main.Game.STATE;

//This class is to interpret the mouse click in the menu
public class Menu extends MouseAdapter{

    //Attributes :
    private Game game;
    private Handler handler;
    private Random r;
    private HUD hud;

    //Constructor :
    public Menu(Game game, Handler handler, HUD hud){
        this.game=game;
        this.hud=hud;
        this.handler=handler;
    }

    //When the mouse clicks on one of the menu option
    public void mousePressed(MouseEvent e){

        int mx = e.getX(); //Mouse position in X
        int my = e.getY(); //Mouse position in Y

        r = new Random();

        //When "Start Playing" button is pressed :
        if (mouseOver(mx,my, 210, 210, 250, 64)){
            handler.object.clear();
            game.gameState= STATE.Game;
            handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler)); //place the player block in the middle of the window
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50,ID.BasicEnemy, handler)); //place the
        }

        //When "Quit" button is pressed :
        if (mouseOver(mx,my, 210, 310, 250, 64)){
            System.exit(1);
        }

        //When the player dies :
        if(game.gameState==STATE.End){
            handler.object.clear();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e){}

    //Tells if the mouse is over one defined section :
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx>x && mx<x+width){
            if (my>y && my<y+height){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public void tick(){}

    //Setting all the graphics (Menu visual)
    public void render(Graphics graphics){

        //Fonts
        Font font1 = new Font ("arial", 1, 50);
        Font font2 = new Font ("arial", 1, 30);
        Font font3 = new Font ("arial", 1, 25);

        //In the menu :
        if (game.gameState== STATE.Menu){

            //Title :
            graphics.setFont(font1);
            graphics.setColor(Color.white);
            graphics.drawString("Welcome WavyPro", 100, 120);

            //Start Playing button :
            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Start Playing", 242, 250);
            graphics.drawRect(210,210,250,64);

            //Quit button :
            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Quit", 300, 350);
            graphics.drawRect(210,310,250,64);

        //When the player dies
        }else if (game.gameState== STATE.End){

            //remove all object on the screen
            handler.object.clear();

            //Game Over
            graphics.setFont(font1);
            graphics.setColor(Color.red);
            graphics.drawString("Game Over", 190, 120);

            //Score
            graphics.setFont(font3);
            graphics.drawString("Score : " + hud.getScore(), 255, 220);
            graphics.setFont(font2);
            graphics.setColor(Color.white);

            //Quit button
            graphics.setFont(font2);
            graphics.setColor(Color.white);
            graphics.drawString("Quit", 300, 350);
            graphics.drawRect(210,310,250,64);
        }
    }
}
