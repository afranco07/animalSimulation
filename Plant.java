import java.awt.*;

/**
 * Created by Alberto on 11/11/2015.
 */
public class Plant
{
    private int energy; //how much energy it will give to the herbivore
    private int life; //how much life before the plant dies
    private Point location;

    public Plant()
    {
        energy = (int)(Math.random() * 20);
        life = 5;
        location = new Point();
    }

    public Plant(int x, int y)
    {
        energy = (int)(Math.random() * 20);
        life = 5;
        location = new Point(x, y);
    }

    public int getEnergy()
    {
        return energy;
    }

    public int getLife()
    {
        return life;
    }

    public Point getLocation()
    {
        return location;
    }

    public void decreaseLife()
    {
        life--;
    }

}
