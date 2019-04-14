# Fractal Tree

## Current Status

Animation capabilities have been implemented at a rudimentary level. They will hopefully be improved to create a more fluid growing animation. You can test out the animation "engine" by clicking on the canvas (clicking again restarts the animation).

UI will soon be added to allow a more user-friendly experience for tweaking parameters. 
Upon full UI implementation there will be 3 tabs in the parameter pane:
- single-value parameters
- ranged-value parameters
- miscellaneous independent parameters (at this point, just coloring)

Each tab will have its own buttons, and toggles included within.

A toolbar will also be implemented for holding UI to save the canvas as an image.

## Gameplan

Develop a Java Swing application that will generate a fractal tree from a set of customizable parameters.

Customizable parameters to be included are as follows:

- **Branching Factor** (number of new branches created at a split)
- **Tree Depth** (maximum branches for any given path in the tree)
- **Split Angle** (determines whether a tree will lean right or left)
- **Branching Angle** (angle between any 2 adjacent branches in a split)
- **Branch Reduction Rate** (rate at which branch length will decrease per split)
- **Stroke Weight** (thickness of the branches)
- **Stroke Shrink Rate** (rate at which stroke weight will decrease per split)

Stretch features include:

- Customizable color pattern for branches
- Options to add variance in some of the parameters (i.e. branching factor with a range of 2-4)
- Ability to generate a tree with random settings
- Ability to save a generated tree as an image

## Tentative Development Schedule

| DATE  | MILESTONE                                                              |
| :---: | :--------------------------------------------------------------------- |
| 3/31  | Program is able to generate a basic fractal tree                       |
| 4/14  | Program is able to generate fractal trees from customizable parameters |
| 4/23  | Program is in working state with any additional features implemented   |

## Project Information

- Developed by Jacob Ressler & Anthony Lantz
- CIS 457 Computer Graphics
- Cleveland State University
- Spring 2019
