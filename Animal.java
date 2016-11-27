import java.awt.*;

/**
 * Created by Alberto on 11/11/2015.
 */
public abstract class Animal
{
    public final int NORTH = 0;
    public final int SOUTH = 1;
    public final int EAST = 2;
    public final int WEST = 3;

    private int energy;
    private int age;
    private Point location;

    public Animal()
    {
        energy = 10;
        age = 1;
        location = new Point();
    }

    public Animal(int x, int y)
    {
        energy = 10;
        age = 1;
        location = new Point(x, y);
    }

    /*Eats one of its prey if it is in its surroundings
    * takes the preys energy and adds it to its own energy
    * */
    abstract void eat(Object obj);

    /*Searches its immediate surroundings.
    if a prey is found, then it moves into the preys location,
    eats the prey, and its energy is added to the animal
     */
    abstract boolean targetedMove(Environment environment);

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Point getLocation() {
        return location;
    }

    public void setNewLocation(Point point)
    {
        this.location.setLocation(point);
    }

    public void increaseAge() {
        age++;
    }

    public void decreaseEnergy() {
        energy--;
    }

    /*First searches its immediate surrounding area for its prey.
    if its prey is not found, then it moves to a random location
     */
    public void randomMove(Environment environment)
    {
        Point temporaryPoint = location;

        boolean didMove = targetedMove(environment);

        if (didMove == false)
        {
            int x = (int) location.getX();
            int y = (int) location.getY();

            int randomMove = (int) (Math.random() * 4);

            switch (randomMove)
            {
                case NORTH:
                    location.setLocation(x, y + 1);
                    break;
                case SOUTH:
                    location.setLocation(x, y - 1);
                    break;
                case WEST:
                    location.setLocation(x - 1, y);
                    break;
                case EAST:
                    location.setLocation(x + 1, y);
                    break;
            }

            if (location.getX() > environment.getX() - 1 || location.getY() > environment.getY() - 1 || location.getX() < 0 || location.getY() < 0)
                location.setLocation(x, y);
        }
    }
}