# Maze-Walk-Solving-
This repository includes two different methods to traversing and solving mazes, one using Graphs (optimal) and the other one using Recursion (non-optimal).

The maze walk algorithm using Recursion involves two letters 'H' and 'G', H stands for Hansel and G for Gretel. Hansel is the destinatation and Gretel is the starting point, Gretel's location is randomly selected everytime the program is run and and so is Hansel's location. This program also uses ASCII characters and also outputs the location of Hansel and Gretel as a boolean and character array is used for that. The program solves the maze non-optimally and does not account for the fastest path. 

The maze solving algorithm uses Graphs and path-finding within them. The program allows the user to enter multiple levels of a text-based map, consisting of a
'start', an 'exit', 'walls', and 'floors'. The letter S represents the start. E is the exit. O (the letter 'oh') represents a 'floor' (meaning a path you can follow). X is a wall (meaning a block you cannot pass). The program will accept this ASCII-based map from Standard Input (System.in), and convert it into a graph. For this program, adjacency lists were using the form of an ArrayList. A report is also included that analyses the methods used to write the program.
