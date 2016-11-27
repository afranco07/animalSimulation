import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alberto on 11/11/2015.
 */
public class GameMain
{
    public static void main(String[] args)
    {
        /* size of the map */
        final int xCoordinate = 34;
        final int yCoordinate = 29;
        Environment environment = new Environment(xCoordinate, yCoordinate);

        /*
        adding herbivores, carnivores, and plants to the environment

        Plants
        */
        for (int i = 0; i <= 55; i++)
        {
            int x = (int)(Math.random() * xCoordinate);
            int y = (int)(Math.random() * yCoordinate);

            environment.addToObjList(new Plant(x,y));
        }

        /* Carnivores */
        for (int i = 0; i <= 10; i++)
        {
            int x = (int)(Math.random() * xCoordinate);
            int y = (int)(Math.random() * yCoordinate);

            environment.addToObjList(new Carnivore(x,y));
        }

        /* Herbivores */
        for (int i = 0; i <= 25; i++)
        {
            int x = (int)(Math.random() * xCoordinate);
            int y = (int)(Math.random() * yCoordinate);

            environment.addToObjList(new Herbivore(x,y));
        }

        /* print the starting map to the screen */
        environment.listToMap();
        System.out.println();
        GuiDisplay gui = new GuiDisplay(environment);
        gui.setMap(environment.printToString());
    }
}
