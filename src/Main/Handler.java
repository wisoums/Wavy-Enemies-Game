package Main;

import java.awt.*;
import java.util.LinkedList;

//Maintain and update all the objects since we have more than 1 objects in the game. (Object-Oriented)
public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //Runs through the entire list to get the tick() of the all the GameObjects
    public void tick(){
        for( int i =0; i<object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    //runs through the entire list to get the render() of all the GameObjects
    public void render(Graphics graphics){
        for( int i =0; i<object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(graphics);
        }
    }

    //To add an object to the Game (ex. A player or enemy) or to add an object to another object (ex. A trail to an enemy)
    public void addObject(GameObject objectToAdd){this.object.add(objectToAdd);}

    ////To remove an object to the Game (ex. An existing player or enemy) or to add an object to another object (ex. A trail from an enemy)
    public void removeObject(GameObject objectToRemove){
        this.object.remove(objectToRemove);
    }
}
