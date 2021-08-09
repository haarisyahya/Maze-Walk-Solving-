package Assign4;


import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions
import java.util.Scanner;        // for scanning the strings and producing values
import java.util.Random;         // for random number/char generation

/** This class will recursively solve a maze in the format of the .txt files provided for Assignment 4.
  *
  * @author Haaris Yahya
  * @version 1.0 (03/17/2021)                                                        */

public class MazeWalk {
  
  private boolean[][] inspected; //boolean type 2-D array which checks to see if the maze has been checked
  private char[][] maze; // char type 2-D array, this is the maze
  private ASCIIDataFile start; // reads the input file
  private ASCIIOutputFile end; // creates a new output file with the solved maze
  private int randomX, randomY, startX, startY, endX, endY, mxRows, mxCols; //mx = max
  private boolean completed = false; // checks if the maze has been completed
  
  
  public MazeWalk ( ) {
    getStart(); //get the data file
    getMaze(); // reads the maze from a .txt file
    placeChar('G'); // places the char 'G' for Gretel randomly in the maze
    placeChar('H'); // places the char 'H' for Hansel randomly in the maze
    printMaze(); //Prints the maze, in this case, the maze with the randomly placed chars 'G' and 'H'
    System.out.println(); //Adds a space between the two mazes for clarity
    decipher(); // deciphers the maze
    
    //Prints the location of Hansel onto the console
    System.out.println("Hansel found at location " + " " + "("+ endX + ","+ endY + ")"); 
    
  }; 
  
  /* This method is recursively called in order to find a path through the maze. The base cases are 'H', 
   * '.' and '#'. The recursive cases are '>','<','^','V'.
   * @param r The current row (vertical) of the 2D array
   * @param c The current column (horizontal) of the 2D array
   * @return null Do nothing if the conditions are met
   */
  private void findPath(int r, int c){
    if ( r < 0 || r >= mxRows || c < 0 || c >= mxCols){ //if the move is out of the bounds of the maze
      return; 
    }
    
    //if the move is already made
    if (maze[r][c] == '^' || maze[r][c] == '>' || maze[r][c] == '<' || maze[r][c] == 'v'){ 
      return;
    }
   
    //if the move has already been checked
    if (inspected[r][c]){
      return;
    } 
    //if the move is a period char, in this case all the moves have been exhausted
    if (maze[r][c] == '.'){
      return;
    }
    //if the move is the wall of the maze
    if (maze[r][c] == '#'){
      return;
    }
    
    //if the move is the location of Hansel
    if (maze[r][c] == 'H'){
      end = new ASCIIOutputFile(); //initializes new OutputFile
      completed = true; // sets the maze as solved
      maze[startX][startY] = 'G'; //sets the starting location to G, 'G' is still a directional char :(
      for (int i =0; i <mxRows; i++){
        for (int j = 0; j<mxCols; j++){
          System.out.print(maze[i][j]); //prints the completed maze
          end.writeC(maze[i][j]); //writes the chars into the ASCIIOutput File
        }
        end.newLine();
        System.out.println();
      }
    }
    
    inspected[r][c] = true; //sets the space as inspected  
    maze[r][c] = '^'; //inspects right
    findPath(r-1,c); //moving one col right
    maze[r][c] = '>'; //inspects left
    findPath(r,c+1); //moving one col left
    maze[r][c] = '<'; //inspects up
    findPath(r, c-1); //moving one row up
    maze[r][c] = 'v'; //inspects down
    findPath(r+1, c); //moving one row down
    maze[r][c] = ' '; //gives us blank space if it is not the path
    maze[r][c] = '.'; //gives us a period '.' if all the possible moves have been exhausted
    
  }
  
  
  // This method uses readLine() and a Scanner to read the input .txt file line by line 
  private void getMaze(){
    String line = start.readLine();
    Scanner dim = new Scanner(line); // initializes the scanner
    mxRows = dim.nextInt();
    mxCols = dim.nextInt();
    dim.close();
    
    maze = new char[mxRows][mxCols]; // Init the 2D char array
    for (int i = 0; i < mxRows; i++){
      line = start.readLine();
      for (int j = 0; j < mxCols; j++){
        maze[i][j] = line.charAt(j); 
        
      }
    }  
  }
  /* This method solves the maze by using a 2D boolean array and sets all coordinates to uninspected calling the 
   * findPath method defined above 
   */
  private void decipher(){
    inspected = new boolean[mxRows][mxCols]; // init the 2D boolean array 
    for(int i = 0; i<mxRows; i++){
      for(int j = 0; j<mxCols; j++){
        inspected[i][j] = false; 
      }
    }
    findPath(startX,startY);
    if(completed){
      System.out.println("Gretel has successfully found Hansel! :)");
    }else{
      System.out.println("Gretel could not find Hansel. :(");
      
    }
  }
  
  //This method simply initializes the input file retriever, the ASCIIDataFile
  private void getStart(){
    start = new ASCIIDataFile();
  }
  
  //This method prints the blank maze onto the console
  private void printMaze() {
    for(int i = 0; i<mxRows; i++){
      for(int j = 0; j<mxCols; j++){
        System.out.print(maze[i][j]); 
      }
      System.out.println();
    }
  }
  
  //This method randomly places the chars 'G' and 'H' in a maze of any size. 
  private void placeChar(char c){
    Random random = new Random(); // init the random generator
    
    int randomX = random.nextInt(mxRows-2) + 1;
    int randomY = random.nextInt(mxCols-2) + 1;
    
    while( maze[randomX][randomY] != ' '){
      randomX = random.nextInt(mxRows-2) + 1;
      randomY = random.nextInt(mxCols-2) + 1;
    }
    maze[randomX][randomY] = c;
    
    if( c == 'G' ){
      startX = randomX;
      startY = randomY;
      System.out.println("Gretel's starting location is" + " " + "("+ startX + ","+ startY + ")");
    }
    else if (c == 'H'){
      endX = randomX;
      endY = randomY;
    }
  }
  
  //main method
  public static void main ( String[] args ) { MazeWalk c = new MazeWalk(); };
} 
