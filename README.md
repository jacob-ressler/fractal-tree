# Fractal Tree Generator
Make some trees with ease.

## About the Project
This is a user-friendly application developed using Java's Swing library. It allows users to generate fractal trees based on eight different parameters. The application supports two modes of tree generation based on the same symmetric fractal tree algorithm, as well as the ability to color the trees and save them as images. 

## Features

### Eight Modifiable Parameters
- **Branching Factor** (number of new branches created at a split)
- **Generations** (number of iterations for branch creation)
- **Tilt** (determines whether a tree will lean right or left)
- **Branching Angle** (angle between any 2 adjacent branches in a split)
- **Branch Length** (initial length of the branches)
- **Branch Shrink Rate** (rate at which branch length will decrease per split)
- **Stroke Weight** (initial thickness of the branches)
- **Stroke Shrink Rate** (rate at which stroke weight will decrease per split)

### Two Tree Generation Modes
Trees can be generated using either single values for all parameters or random values within a set range. The intent of the latter was to produce more natural-looking trees, but results may vary...

### Tree Coloring (kind of)
This feature is somewhat limited in its functionality currently, and will be improved in the future. As it stands, the user can choose 5 different colors to use for the tree. There cannot be more or less than that, and the user must use hex values to modify the colors.

### Image Saving
If the user generates a tree they want to save to an image, it can be done with the press of a button. It uses Swing's native file save dialog to allow the user to name and place the image however and wherever they choose.

## Development History

| DATE  | MILESTONE |
| :---: | :-------- |
| 3/31  | Program can generate a basic fractal tree |
| 4/15  | Program can generate trees using parameters |
| 4/17  | Program can save trees as images |
| 4/20  | Program supports range-value tree generation |
| 4/23  | Program supports tree coloring |
| 4/28  | Optimization and refactoring |

## Project Information

- Developed by Anthony Lantz & Jacob Ressler
- CIS 457 Computer Graphics
- Cleveland State University
- Spring 2019
