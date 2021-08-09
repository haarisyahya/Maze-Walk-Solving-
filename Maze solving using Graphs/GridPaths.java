package Assign4;

import java.util.LinkedList; // to create the linked list
import java.util.Scanner; //to input the values


/** This class uses linked lists and queues to convert a 3D array into a graph which then uses the BFS algorithm to solve a maze
  * optimally.
  * @author <Haaris Yahya>
  * @version 1.0 (07/09/2021)
  */

public class GridPaths {
  static Scanner scn = new Scanner(System.in);
  static boolean visited;
  static int W,H,D;
  static char [][][] r,c,d; 
  // function to take grid input, a 3D array of characters
  public static char[][][] getGrid() {
    Scanner scn = new Scanner(System.in);
    
    // input W, D, H
    System.out.println("Enter width: ");
    int W = scn.nextInt();
    System.out.println("Enter height: ");
    int H = scn.nextInt();
    System.out.println("Enter depth: ");
    int D = scn.nextInt();
    
    System.out.println("Enter maze below. Only rows of width " + W + " will be accepted.");
    
    // create grid
    char[][][] grid = new char[D][H][W];
    
    // take input grid
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
       String s = scn.nextLine();
       if (s.length() == 0) {
         j--;
         continue;
        }
        grid[i][j] = s.toCharArray();
      }
    }
    
    // close scanner and return grid.
    scn.close();
    return grid;
  }
  
  // node class for the graph
  public static class Node {
    
    // row, column and depth co-ordinates
    int r;
    int c;
    int d;
    
    // cost to reach that point from starting point.
    int cost;
    
    // path followed
    String path;
    
    // constructor
    Node(int r, int c, int d, int cost, String path) {
      this.r = r;
      this.c = c;
      this.d = d;
      this.cost = cost;
      this.path = path;
    }
  }
  
  // getMinCost function to visited the min cost path and its cost using BFS
  // algorithm.
  public static Node getMinCost(char[][][] grid) {
    
    // boolean matrix to keep track of visited nodes.
    boolean[][][] visited = new boolean[grid.length][grid[0].length][grid[0][0].length];
    
    // visited the location of 'S' in the grid.
    int sr = 0;
    int sc = 0;
    int sd = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        for (int k = 0; k < grid[0][0].length; k++) {
          if (grid[i][j][k] == 'S') {
            sr = i;
            sc = j;
            sd = k;
            j = grid[0].length;
            i = grid.length;
            break;
          }
        }
      }
    }
    
    // create starting node.
    Node node = new Node(sr, sc, sd, 0, "");
    
    // create queue and then add starting node to it.
    
    LinkedList<Node> queue = new LinkedList<Node>();
    queue.addLast(node);
    
    // BFS algorithm.
    while (queue.size() > 0) {
      
      // remove first node from the queue, rem = removed node.
      Node rem = queue.removeFirst();
      
      // if node is not visited, mark it as visited, else skip the node.
      if (visited[rem.r][rem.c][rem.d] == true) {
        continue;
      } else {
        visited[rem.r][rem.c][rem.d] = true;
      }
      
      // if node is exit point, return the node.
      if (grid[rem.r][rem.c][rem.d] == 'E') {
        return rem;
      }
      
      // add the neighbors.
      
      // north
      if (rem.r - 1 >= 0 && grid[rem.r - 1][rem.c][rem.d] != 'X' && visited[rem.r - 1][rem.c][rem.d] == false) {
        Node newNode = new Node(rem.r - 1, rem.c, rem.d, rem.cost + 1, rem.path + "U ");
        queue.addLast(newNode);
      }
      
      // south
      if (rem.r + 1 < grid.length && grid[rem.r + 1][rem.c][rem.d] != 'X'
            && visited[rem.r + 1][rem.c][rem.d] == false) {
        Node newNode = new Node(rem.r + 1, rem.c, rem.d, rem.cost + 1, rem.path + "D ");
        queue.addLast(newNode);
      }
      
      // west
      if (rem.c - 1 >= 0 && grid[rem.r][rem.c - 1][rem.d] != 'X' && visited[rem.r][rem.c - 1][rem.d] == false) {
        Node newNode = new Node(rem.r, rem.c - 1, rem.d, rem.cost + 1, rem.path + "N ");
        queue.addLast(newNode);
      }
      
      // east
      if (rem.c + 1 < grid[0].length && grid[rem.r][rem.c + 1][rem.d] != 'X'
            && visited[rem.r][rem.c + 1][rem.d] == false) {
        Node newNode = new Node(rem.r, rem.c + 1, rem.d, rem.cost + 1, rem.path + "S ");
        queue.addLast(newNode);
      }
      
      // up
      if (rem.d - 1 >= 0 && grid[rem.r][rem.c][rem.d - 1] != 'X' && visited[rem.r][rem.c][rem.d - 1] == false) {
        Node newNode = new Node(rem.r, rem.c, rem.d - 1, rem.cost + 1, rem.path + "W ");
        queue.addLast(newNode);
      }
      
      // down
      if (rem.d + 1 < grid[0][0].length && grid[rem.r][rem.c][rem.d + 1] != 'X'
            && visited[rem.r][rem.c][rem.d + 1] == false) {
        Node newNode = new Node(rem.r, rem.c, rem.d + 1, rem.cost + 1, rem.path + "E ");
        queue.addLast(newNode);
      }
    }
    
    // return null if no path is possible.
    return null;
  }

  
  
  
  public static void main(String[] args) {
    
    // testing the functions.
    char[][][] grid = getGrid();
    Node minCost = getMinCost(grid);
    for(;;){
    System.out.println("1. Solve suboptimally ");
    System.out.println("2. Estimate optimal solution cost ");
    System.out.println("3. Solve optimally  ");
    System.out.println("4. Enter new puzzle  ");
    System.out.println("5. Quit ");
    
    
    int k = scn.nextInt();
    switch (k){
      case 1:
        System.out.println("Not implemented. Use optimal instead. ");
        break;
      case 2:
        if(minCost != null)
      {System.out.println("Optimal Path Cost:"+minCost.cost);
        minCost.cost=0;}
        else
          System.out.println("Optimal Path Cost: 0");
        break;
      case 3:
        System.out.println("Finding Solution ...");
        if(minCost != null)
        {
          System.out.println("Optimal Path: "+ minCost.path); }
        else
        {
          System.out.println("Exit not reachable.");} 
        switch(D){
          case 1:
          {for(int j = 0; j < H; j++)
            {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(r[i][j]+" ");
            }System.out.println();
          }break;}
          case 2:
          {for(int j = 0; j < H; j++)
            {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(r[i][j]+" ");
            }System.out.println();
          }
          for(int j = 0; j < H; j++)
          {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(c[i][j]+" ");
            }System.out.println();
          }break;}
          case 3:
          {for(int j = 0; j < H; j++)
            {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(r[i][j]+" ");
            }System.out.println();
          }
          for(int j = 0; j < H; j++)
          {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(c[i][j]+" ");
            }System.out.println();
          }for(int j = 0; j < H; j++)
          {
            for(int i = 0; i < W; i++)
            {
              System.out.printf(d[i][j]+" ");
            }System.out.println();
          }break;}}     
        break;
      case 4:
        System.out.println("Enter width: ");
        W = scn.nextInt();
        System.out.println("Enter height: ");
        H = scn.nextInt();
        System.out.println("Enter depth: ");
        D = scn.nextInt();
        System.out.println("Enter maze below. Only rows of width " + W + " will be accepted.");
    
        k = scn.nextInt();
        break;
      case 5:
        System.out.println("Good bye!");
        break;
      default :
        System.out.print("invaid value");
        break;
    }
    if(k==5) 
      break;
    
  }
  }
  public static boolean visited(int i, int j)
  {
    return i>=0 && j >=0 && i < W && j < H;
    }
}
    
  


