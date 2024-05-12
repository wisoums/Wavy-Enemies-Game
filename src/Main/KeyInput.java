package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//This class deals with the keyboard input made by the users when trying to control their Player Icon
public class KeyInput extends KeyAdapter {

    //Attributes :
    private Handler handler;
    private Boolean[] keyDown = new Boolean[4];

    //Constructor :
    public KeyInput(Handler handler){
        this.handler=handler;
        for (int i =0; i<keyDown.length;i++){
            keyDown[0]=false;
        }
    }

    //What happens when the keys are pressed :
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i =0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //when the player presses the keys W, S, D, A, UP, DOWN, RIGHT and LEFT the velocity is "activated" to 5:
            if(tempObject.getId()== ID.Player){
                if(key==KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0]=true;} //go up
                if(key==KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[1]=true;} //go right
                if(key==KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[2]=true;} //go down
                if(key==KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[3]=true;} //go left
                if(key==KeyEvent.VK_UP) {tempObject.setVelY(-5);  keyDown[0]=true;}//go up
                if(key==KeyEvent.VK_DOWN) {tempObject.setVelY(5); keyDown[1]=true;} //go right
                if(key==KeyEvent.VK_RIGHT) {tempObject.setVelX(5);  keyDown[2]=true;}//go down
                if(key==KeyEvent.VK_LEFT) {tempObject.setVelX(-5); keyDown[3]=true;} //go left
            }
        }

        if (key ==KeyEvent.VK_ESCAPE) System.exit(1); //click the "esc" key to exit
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i =0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //when the player releases the keys W, S, D, A, UP, DOWN, RIGHT and LEFT the velocity becomes null making it stop moving:
            if(tempObject.getId()== ID.Player){ //key event for player
                if(key==KeyEvent.VK_W) keyDown[0]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_S) keyDown[1]=false;//tempObject.setVelX(0);
                if(key==KeyEvent.VK_D) keyDown[2]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_A) keyDown[3]=false;//tempObject.setVelX(0);
                if(key==KeyEvent.VK_UP) keyDown[0]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_DOWN) keyDown[1]=false;//tempObject.setVelX(0);
                if(key==KeyEvent.VK_RIGHT) keyDown[2]=false;//tempObject.setVelY(0);
                if(key==KeyEvent.VK_LEFT) keyDown[3]=false;//tempObject.setVelX(0);

                //Make the movements more fluent :
                if (!keyDown[0]&&!keyDown[1]) tempObject.setVelY(0);
                if (!keyDown[2]&&!keyDown[3]) tempObject.setVelX(0);
            }
        }

    }
}
