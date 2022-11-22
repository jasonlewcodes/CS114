// Jason Lew
// CS 114 H02

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class MazeProject
{
   public static void main(String[] args) throws Exception
   {
      File in = new File("maze.dat"); // opens maze.dat file
      Scanner scan = new Scanner(in);
      
      int rows = Integer.parseInt(scan.next());
      int cols = Integer.parseInt(scan.next());
   
      char[][] maze = createMaze(rows, cols, scan);
      
      int[] position = findStart(rows, cols, maze);
      
      solveMaze(position[0], position[1], maze, "none", position);
   }
   public static char[][] createMaze(int rows, int cols, Scanner scan)
   {
      char[][] maze = new char[rows][cols]; // initialize maze 
      maze[0] = scan.nextLine().toCharArray(); // add the first line to maze
      for (int i = 0; i < rows; i++)
      {
         maze[i] = scan.nextLine().toCharArray(); // add each line of file as an array of chars to maze
      }
      return maze;      
   }
   public static int[] findStart(int rows, int cols, char[][] maze)
   {
      int startRow = 0;
      int startCol = 0; 
      for (int i = 0; i < rows; i++)
      {
         for (int j = 0; j < cols; j++)
         {
            if (maze[i][j] == '+') // checks each character of the char array until + is found
            {
               startRow = i;
               startCol = j;
            }
         }
      }
      return new int[] {startRow, startCol}; // returns the indexes
   }
   public static boolean solveMaze(int row, int col, char[][] maze, String parameter, int[] originalPosition)
   {
      if (parameter == "backtrack" && row == originalPosition[0] && col == originalPosition[1]) // if we backtrack to the beginning of the maze and we reach original position, the maze is unsolvable
      {
         solveMaze(row, col, maze, "no exit", originalPosition);
      }
      
      if (parameter == "no exit") // maze is unsolvable
      {
         System.out.println("The maze cannot be solved.");
         print(maze);
         System.exit(0);
      }
      
      if (maze[row][col] == '+' && parameter == "test") // prevents going backwards after finding a new position
      {
         return false;
      }
      
      if (row < maze.length && row >= 0 && col < maze[0].length && col >= 0) // ensures that the conditions stay within the boundaries of the maze
      {
         if (maze[row][col] == '-') // exit is found
         {
            System.out.println("The maze has been solved.");
            print(maze);
            System.exit(0);
         }
         else if (maze[row][col] == 'X' || maze[row][col] == '.') // not an option to move forward
         {
            return false;
         }
         else if (maze[row][col] == ' ') // finds open space to move forward
         {
            return true;
         }
      }

      if (solveMaze(row-1, col, maze, "test", originalPosition) && parameter != "south") // check north
      {
         maze[row-1][col] = '+';
         solveMaze(row-1, col, maze, "north", originalPosition);         
      }
      else if (solveMaze(row+1, col, maze, "test", originalPosition) && parameter != "north") // check south
      {
         maze[row+1][col] = '+';
         solveMaze(row+1, col, maze, "south", originalPosition);
      }
      else if (solveMaze(row, col+1, maze, "test", originalPosition) && parameter != "west") // check east
      {
         maze[row][col+1] = '+';
         solveMaze(row, col+1, maze, "east", originalPosition);
      }
      else if (solveMaze(row, col-1, maze, "test", originalPosition) && parameter != "east") // check west
      {
         maze[row][col-1] = '+';
         solveMaze(row, col-1, maze, "west", originalPosition);
      }
      else // when north, south, east, and west are all not possible
      {
         maze[row][col] = '.'; // mark the position as not possible, find what direction to backtrack to
         if (maze[row-1][col] == '+') 
         {
            solveMaze(row-1, col, maze, "backtrack", originalPosition);
         }
         else if (maze[row+1][col] == '+')
         {
            solveMaze(row+1, col, maze, "backtrack", originalPosition);  
         }
         else if (maze[row][col+1] == '+')
         {
            solveMaze(row, col+1, maze, "backtrack", originalPosition);
         }
         else if (maze[row][col-1] == '+')
         {
            solveMaze(row, col-1, maze, "backtrack", originalPosition);
         }
      }
      return false;
   }
   public static void print(char[][] maze)
   {
      for (int i = 0; i < maze.length; i++)
      {
         for (int j = 0; j < maze[0].length; j++)
         {
            System.out.print(maze[i][j]); // prints every character of the array
         }
         System.out.println(""); // prints a new line
      }
   }
}