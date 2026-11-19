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
        //Heres my adapted code for get/set
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
    //Using ListArrayBased to return a list of the neighbours of c the builder hasnt visited yet
    //Based on the code in SPA example video
    private ListArrayBased getUnvisitedNeighbours(Cell c){
        //Exact same as lab 1
        ListArrayBased neighbours = new ListArrayBased();
        int row = c.getRow();
        int col = c.getCol();

        //Another case of a minor adjustment for my get/set approach, logic still the same as SPA example
        if (col > 0 && !grid[row][col-1].getVisited())
            neighbours.add(neighbours.size()+1, grid[row][col-1]);
        if (row > 0 && !grid[row-1][col].getVisited())
            neighbours.add(neighbours.size()+1, grid[row-1][col]);
        if (col < grid[0].length-1 && !grid[row][col+1].getVisited())
            neighbours.add(neighbours.size()+1, grid[row][col+1]);
        if (row < grid.length-1 && !grid[row+1][col].getVisited())   // from c.row < grid.length-1 && ... code
            neighbours.add(neighbours.size()+1, grid[row+1][col]);

        return neighbours;

    }

    //Now to actually build the maze, using depth-first search
    //Found reusing lab 3 work and logic from an example on lecture 6 worked best
    private void generateMazeRandomiseDFS(int start_row, int start_col){
     StackReferenceBased stack = new StackReferenceBased();
     Cell start = grid[start_row][start_col];
     start.setVisited(true);
     stack.push(start);
     //Needed counter for next check
     int wallsKnocked = 0;
     while (!stack.isEmpty()){//Vertex top of stack
         Cell current = (Cell) stack.peek();
         ListArrayBased neighbours = getUnvisitedNeighbours(current);

         if (neighbours.isEmpty()){
             stack.pop();
         }
         //Pick at random rather than smallest label like in example
         else{
             int pick = (int)(Math.random() * neighbours.size()) + 1;// Llist starts at 1

             Cell next = (Cell) neighbours.get(pick);
             //Open passage between
             knockDownWall(current, next);
             wallsKnocked++;
             next.setVisited(true); //mark u as visited, s.push(u) in lecture
             stack.push(next);

         }
     }
    }
    public static void main(String[] args){
        new MazeFrame().setVisible(true);
    }
}
