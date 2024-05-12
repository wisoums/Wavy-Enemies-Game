package Main;

import java.util.Random;
//This class is what makes the enemies appear/spawn as the user increases in level in the game
public class Spawn {

    //Attributes :
    private Handler handler;
    private HUD hud;
    private Random r;
    private int scoreKeep;

    //Constructors :
    public Spawn (Handler handler, HUD hud){
        this.handler=handler;
        this.hud=hud;
    }


    //What happens when the game runs :
    public void tick(){
        scoreKeep++;
        r=new Random();

        //When
        if (scoreKeep>=300){
            scoreKeep=0;
            hud.setLevel(hud.getLevel()+1);

            //To every level increasing, a basic enemy spawns :
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH)-50, r.nextInt(Game.HEIGHT)-50, ID.BasicEnemy, handler));

            //To every even level increasing, a fast enemy spawns :
            if((hud.getLevel()%2)==0){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH)-50, r.nextInt(Game.HEIGHT)-50, ID.FastEnemy, handler));
            }
            //To every level increasing divisible by 3, a smart enemy spawns :
            if((hud.getLevel()%3)==0){
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH)-50, r.nextInt(Game.HEIGHT)-50, ID.SmartEnemy, handler));
            }

        }
    }
}
