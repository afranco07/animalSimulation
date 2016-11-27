import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alberto on 11/11/2015.
 */
public class Herbivore extends Animal
{
    public Herbivore(int x, int y)
    {
        super(x,y);
    }

    /*Eat the plant and add its energy to its own energy*/
    public void eat(Object obj)
    {
       if (obj instanceof Plant)
       {
           Plant plant = (Plant) obj;

           int plantEnergy = plant.getEnergy();
           int herbEnergy = getEnergy();
           int total = plantEnergy + herbEnergy;

           setEnergy(total);
       }
    }

    /*Searches for an plant in its immediate surrounding area
    if an plant is found, then return true and set the animals location
    to the plant. Eat the plant and get its energy
     */
    public boolean targetedMove(Environment environment)
    {
        Point currentAnimal = getLocation();

        int lowerX = (int)currentAnimal.getX() - 1;
        int lowerY = (int)currentAnimal.getY() - 1;

        int upperX = (int)currentAnimal.getX() + 1;
        int upperY = (int)currentAnimal.getY() + 1;

        if(lowerX < 0)
            lowerX = 0;
        if(lowerY < 0)
            lowerY = 0;

        if (upperX >= environment.getX())
            upperX = environment.getX() - 1;

        if (upperY >= environment.getY())
            upperY = environment.getY() - 1;

        Point searchPoint = new Point(lowerX,lowerY);

        ArrayList objList = environment.getObjList();

        //search:
        for (int y = lowerY; y <= upperY; y++)
        {
            for (int x = lowerX; x <= upperX; x++)
            {
                searchPoint.setLocation(x, y);

                for (int i = 0; i < objList.size(); i++)
                {
                    if (objList.get(i) instanceof Plant)
                    {
                        if (((Plant) objList.get(i)).getLocation().equals(searchPoint))
                        {
                            eat(objList.get(i));
                            environment.removeFromObjList(i);
                            setNewLocation(searchPoint);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
