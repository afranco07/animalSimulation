import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Alberto on 12/24/2015.
 */
public class GuiDisplay extends JFrame
{
    private JFrame frame;
    private JPanel northPanel;
    private JPanel southPanel;

    private Environment environment;

    private JButton nextIteration;
    private JButton runAutomatically;
    private JButton stopButton;

    private JLabel currentIteration;
    private JTextArea map;

    int numberIteration;

    /*sets up the window */
    public GuiDisplay(Environment e)
    {
        frame = new JFrame();
        northPanel = new JPanel();
        southPanel = new JPanel();

        frame.setTitle("Animal Simulation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(710, 740);

        nextIteration = new JButton("Next Iteration");
        runAutomatically = new JButton("skip 10 iterations");
        stopButton = new JButton("Stop!");

        nextIteration.addActionListener(new ButtonListener());
        runAutomatically.addActionListener(new ButtonListener());
        stopButton.addActionListener(new ButtonListener());
        stopButton.setEnabled(false);

        southPanel.setBackground(Color.CYAN);
        southPanel.add(nextIteration);
        southPanel.add(runAutomatically);

        numberIteration = 0;
        currentIteration = new JLabel("Current iteration: " + numberIteration);
        southPanel.add(currentIteration);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        map = new JTextArea(29, 68);
        Font bigFont = new Font("monospaced", Font.BOLD, 16);
        map.setFont(bigFont);
        map.setEditable(false);
        northPanel.add(map);
        frame.getContentPane().add(BorderLayout.NORTH, northPanel);

        frame.setVisible(true);

        environment = e;
    }

    /*set the text field to match the map and
    output to the screen
     */
    public void setMap(String s)
    {
        map.setText(s);
    }

    public void increaseNumberIteration()
    {
        numberIteration++;
        currentIteration.setText("Current iteration: " + numberIteration);
    }

    /*This method moves the animals in the environment.
    First it checks if the animal has an energy of zero or less.
    If it is true it removes the animal.
    If the energy is greater than zero, then move the animal to a new location.
    Then it checks is the energy and age of the animal is greater than 10.
    If it is greater than 10, then the animal creates a new animal of the same type (give birth to a new animal)
     */
    public void moveAnimal()
    {
        environment.clearMap();
        ArrayList list = environment.getObjList();

        for (int i = 0; i < environment.objListSize(); i++)
        {
            /*
            if the animal has an energy of 0, then the animal is dead
            remove it from the list
            */
            if (list.get(i) instanceof Animal)
            {
                if (((Animal) list.get(i)).getEnergy() <= 0)
                {
                    list.remove(i);
                }
                else
                {
                    ((Animal) list.get(i)).randomMove(environment);
                    try
                    {
                        if (list.get(i) instanceof Animal)
                        {
                            ((Animal) list.get(i)).increaseAge();
                            ((Animal) list.get(i)).decreaseEnergy();

                            /*If the energy and age of the animal is greater than 10,
                            then the animal will give birth to a new animal of its type.
                             */
                            if (((Animal) list.get(i)).getAge() >= 10 && ((Animal) list.get(i)).getEnergy() >= 10)
                            {
                                if (list.get(i) instanceof Herbivore)
                                {
                                    Point birthCoordinate = ((Herbivore) list.get(i)).getLocation();
                                    environment.addToObjList(new Herbivore((int) birthCoordinate.getX(),
                                            (int) birthCoordinate.getY()));
                                }
                                else if (list.get(i) instanceof Carnivore)
                                {
                                    Point birthCoordinate = ((Carnivore) list.get(i)).getLocation();
                                    environment.addToObjList(new Carnivore((int) birthCoordinate.getX(),
                                            (int) birthCoordinate.getY()));
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException e)
                    {
                        i--;
                        if (list.get(i) instanceof Animal)
                        {
                            ((Animal) list.get(i)).increaseAge();
                            ((Animal) list.get(i)).decreaseEnergy();
                        }
                    }
                }

            }
        }
        updateGUI();
    }

    /*Update the contents of the text field
    to show the new positions of the animals
    on the screen.
     */
    public void updateGUI()
    {
        environment.listToMap();
        setMap(environment.printToString());
        increaseNumberIteration();
        environment.clearMap();
    }

    /*controls what happens when the buttons are pressed*/
    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == nextIteration)
            {
                moveAnimal();
            }

            else if (e.getSource() == runAutomatically)
            {
                stopButton.setEnabled(true);

                for (int i = 0; i < 10; i++)
                {
                    moveAnimal();

                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }

            else if (e.getSource() == stopButton)
            {
                nextIteration.setEnabled(true);
                stopButton.setEnabled(false);
            }
        }
    }
}
