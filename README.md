# Fractal Tree

## Things to Add Still
- Link parameters with their respective slider/selector.
	- change all parameters to `Integer`s and pass as a reference in `createSlider()` or `createRangeSlider()`.
- Add color pattern picker to Misc Tab
	- maybe just X number of text fields where you put in a hex value if a color selection dialog won't work
- 'Save Image' button in Misc Tab
	- try doing with a file dialog first (`JFileChooser` I think?)
	- otherwise, just have a text field where the user can put a file name.
- Use the parameters of only the last selected tab (ignoring Misc)
	- add a `ChangeListener` to `CustomizationPanel` that changes a parameter value to 0 or 1 based on the index of the selected tab (or do nothing if the active tab is misc)

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
