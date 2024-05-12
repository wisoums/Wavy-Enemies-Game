package Main;

import java.awt.*;

//Set all our objects with this class (Parent Class)
public abstract class GameObject {

    //Attributes
    protected float x;
    protected float y;

    protected ID id;
    protected float velX;
    protected float velY; //velocity : controls speed in X and Y


    //Getter & Setters
    public float getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    public float getVelX() {
        return velX;
    }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public float getVelY() {
        return velY;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }


    //Constructor :
    public GameObject(float x, float y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;
    }

    //Abstract methods :
    public abstract void tick(); //What happens while the game runs
    public abstract void render(Graphics graphics); //Setting the graphics particularities
    public abstract Rectangle getBounds(); //Get the information of the game object as a rectangle to determine when there are collisions (in Player Class)

}
