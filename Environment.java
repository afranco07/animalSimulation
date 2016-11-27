import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alberto on 11/11/2015.
 */
public class Environment
{
    private final int x;
    private final int y;
    private Object[][] map;
    private ArrayList objList;

    //constructor
    public Environment(int x, int y)
    {
        this.x = x;
        this.y = y;
        map = new Object[y][x];
        objList = new ArrayList();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int objListSize()
    {
        return objList.size();
    }

    public ArrayList getObjList()
    {
        return objList;
    }

    /* adds an animal to the objList array */
    public void addToObjList(Object obj)
    {
        objList.add(obj);
    }

    public void removeFromObjList(int index)
    {
        objList.remove(index);
    }

    /* clears the map to be ready to be written again */
    public void clearMap()
    {
        for (int i = 0; i < this.y; i++)
        {
            for (int j = 0; j < this.x; j++)
            {
                map[i][j] = null;
            }
        }
    }

    /* adds all the animals from the objList to the Map */
    public void listToMap()
    {
        for (int i = 0; i < objList.size(); i++)
        {
            if (objList.get(i) instanceof Carnivore)
            {
                Point coord = ((Carnivore) objList.get(i)).getLocation();

                map[(int)coord.getY()][(int)coord.getX()] = objList.get(i);
            }

            else if (objList.get(i) instanceof Herbivore)
            {
                Point coord = ((Herbivore) objList.get(i)).getLocation();

                map[(int)coord.getY()][(int)coord.getX()] = objList.get(i);
            }

            else if (objList.get(i) instanceof Plant)
            {
                Point coord = ((Plant) objList.get(i)).getLocation();

                if(map[(int)coord.getY()][(int)coord.getX()] instanceof Carnivore)
                {
                    //Don't do anything if there is a carnivore in the spot already
                }

                else
                    map[(int)coord.getY()][(int)coord.getX()] = objList.get(i);
            }
        }
    }

    /* print everything in the map to the screen */
    public void print()
    {
        for (int i = 0; i < this.y; i++)
        {
            for (int j = 0; j < this.x; j++)
            {
                if (map[i][j] instanceof Plant)
                    System.out.print(" *");

                else if (map[i][j] instanceof Herbivore)
                    System.out.print(" &");

                else if (map[i][j] instanceof Carnivore)
                    System.out.print(" @");

                else if (map[i][j] == null)
                    System.out.print(" .");

                else
                    System.out.print(" .");
            }
            System.out.println();
        }
    }

    /*Turns the map into a string */
    public String printToString()
    {
        String printedMap = "";

        for (int i = 0; i < this.y; i++)
        {
            for (int j = 0; j < this.x; j++)
            {
                if (map[i][j] instanceof Plant)
                    printedMap = printedMap + " *";
                else if (map[i][j] instanceof Herbivore)
                    printedMap = printedMap + " &";
                else if (map[i][j] instanceof Carnivore)
                    printedMap = printedMap + " @";
                else if (map[i][j] == null)
                    printedMap = printedMap + " .";
                else
                    printedMap = printedMap + " .";
            }
            printedMap = printedMap + "\n";
        }

        return printedMap;
    }
}
