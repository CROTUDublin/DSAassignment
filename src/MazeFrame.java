//Adapted from lecture notes and videos
import javax.swing.*;
public class MazeFrame extends JFrame{

    int M = 10; int N = 10;
    Cell [][] grid = new Cell [M][N];

    public MazeFrame(){
      setTitle("My Perfect Maze!");
      setSize(600, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      initialiseGrid();

      //Adding tests here, adapted from lectures with adaption for getter setter method
        //Test 1, Open 2x2 room
        //Heres adapted code for get/set
       // grid[4][4].setTop(false);
        //grid[3][4].setBottom(false);
        //grid[4][4].setLeft(false);
       // grid[4][3].setRight(false);

       // grid[3][4].setLeft(false);
       // grid[3][3].setRight(false);
       // grid[3][3].setBottom(false);
       // grid[4][3].setTop(false);

        //Test 2: remove every wall with only border
        //tearDownAllWalls(); commented out from lecture notes

        add (new MazeCanvas(grid));

    }
    private void initialiseGrid()
    {
        for (int i = 0;i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = new Cell(i,j,M,N);
            }
        }
    }
    //Knocking down the wall shared by two neighbours, both sides
    //Adapted from the SPA grid based checks, left, top, right, bottom
    //Again, code adjusted for my getter setter approach for both methods but logic is the same as grid[c.row][c.col-1], was c.col > 0etc
    public void tearDownAllWalls(){
for(int i =0; i<grid.length; i++){
    for (Cell c: grid[i]){
        //getter setter adjustment
        if (c.getCol() > 0)
            c.setLeft(false);
        if (c.getRow() > 0)
            c.setTop(false);
        if (c.getCol()< grid[0].length-1) //c.col < grid[0].length-1 etc
            c.setRight(false);
        if (c.getRow() < grid.length-1)
            c.setBottom(false);
    }
}
    }
    //Inspired by tearDownAllWalls from lecture/lab notes, ease of use
    private void knockDownWall(Cell a, Cell b){
        if (b.getCol() == a.getCol() - 1)
        {
            a.setLeft(false);
            b.setRight(false);
        }
        if (b.getRow() == a.getRow() - 1)
        {
            a.setTop(false);
            b.setBottom(false);
        }
        if (b.getCol() == a.getCol() + 1)
        {
            a.setRight(false);
            b.setLeft(false);
        }
        if (b.getRow() == a.getRow() + 1)
        {
            a.setBottom(false);
            b.setTop(false);
        }

    }
    public static void main(String[] args){
        new MazeFrame().setVisible(true);
    }
}
