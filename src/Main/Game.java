package Main;

import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    //Attributes :
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    //State of the program :
    public enum STATE{
        Menu,
        Game,
        End
    }

    //Setting initial state :
    public STATE gameState = STATE.Menu;

    //Constructor :
    public Game(){

        //hud, handler and menu initialised to be used further in this class :
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);

        //Mouse listener for the user's mouse use in the menu :
        this.addMouseListener(menu);

        //Key listener for the user's keyboard use during the game:
        this.addKeyListener(new KeyInput(handler));

        //Name :
        new Window(WIDTH, HEIGHT, "Wis Wave Game", this);


        //Spwaner, and random initialised to be used further in this class :
        spawner= new Spawn(handler,hud);
        r=new Random();

        //setting our initial components (1 Player & 1 Basic Enemy) if the program is in a state of "Game" :
        if (gameState==STATE.Menu){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH)-50,r.nextInt(Game.HEIGHT)-50,ID.BasicEnemy, handler));
        }if (gameState==STATE.Game){

            handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler)); //place the player block in the middle of the window
        }
    }

    //When the game starts running (creates a thread) :
    public synchronized void start(){
        this.thread = new Thread(this);
        this.thread.start(); //Start the thread
        this.running = true; //it's running
    }

    //When the game stops running :
    public synchronized void stop(){
        try{
            this.thread.join(); //terminates it
            this.running = false; //it's not running
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // When the game runs
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta =0;
        long timer = System.currentTimeMillis();
        int frames =0;
        while (running){
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            while(delta>=1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                System.out.println("FPS: " + frames);
                frames=0;
            }
        }
        stop();
    }

    //Calling the tick methods
    private void tick(){
        handler.tick();
        if (gameState==STATE.Game){
            hud.tick();
            spawner.tick();
            if(hud.HEALTH<=0){
                gameState=STATE.End;
                HUD.HEALTH=100;
            }
        }else if (gameState==STATE.Menu||gameState==STATE.End){
            menu.tick();
            //hud.setLevel(0);
            //hud.setScore(0);
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(graphics);

        if (gameState==STATE.Game){
            hud.render(graphics);
        }else if (gameState==STATE.Menu||gameState==STATE.End){
            menu.render(graphics);
        }

        graphics.dispose();
        bs.show();
    }

    //Set limits, for now we used it for the player to make him move between the window frame limits
    public static int clamp( int var, int min, int max){
        if (var>=max)return var=max;
        else if (var<=min) return var=min;
        else return var;
    }

    //MAIN :
    public static void main(String args[]) {
        new Game ();
    }
}