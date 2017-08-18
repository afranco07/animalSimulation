# Welcome #

This small app simulates animals wandering on a grid.
```
& - Represents an herbivore

@ - Represents a Carnivore

\* - Represents a plant
```

Herbivores eat plants. Carnivores eat the herbivores. Each animal starts with the same amount of health, and their health decrements each time they move to a new position on the grid. In order to replenish their health, the animals must eat. The animals die once their health reaches 0 and they are removed from the grid.

The animals are also able to create more of themselves. To do this they must have a high amount of health and must have been alive for some time. Creating more of themselves also uses some of their health.

### Instructions For Compiling ###

Make sure all the files are in the same directory. Compile the GameMain.java. Then run the output file after compiling. 

### After Compiling ###

On the screen will be a large grid with plants, herbivores, and carnivores randomly placed on the grid. The "Next Iteration" button simulates the movement of all the animals. One press of this button moves them to an adjacent point on the grid or remain where they are. Keep pressing until all of the animals eventually die off.

The "Skip 10 Iterations" button simulates 10 button presses of the "Next Iteration" button. It takes some time for this action to be completed.

### Demo of Program ###
![animal simulation demo](https://github.com/afranco07/animalSimulation/blob/master/animalSimDemo.gif?raw=true)
