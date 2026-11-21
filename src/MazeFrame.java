//Adapted from lecture notes and videos
import javax.swing.*;
public class MazeFrame extends JFrame{

    int M = 10; int N = 10;
    Cell [][] grid = new Cell [M][N];
    //Added later for variables per brief for the canvas and a delay
    MazeCanvas canvas;
    int delay = 20;

    public MazeFrame(){
      setTitle("My Perfect Maze!");
      setSize(600, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      initialiseGrid();
      //generateMazeRandomiseDFS(0,0);
      //solveMazeBFS(0,0,M-1,N-1);

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

        //add (new MazeCanvas(grid));
        //Needed to change as pause needs to talk to the canvas
        //Now a reference for the canvas is kept
        canvas = new MazeCanvas(grid);
        add(canvas);

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
//Needed pause method, using repaint and sleep as most basic approach
    //Simply redraws woth c as the current cell so the user can watch
    //Try/catch based on my lab 3
    private void pause (Cell c){
        canvas.current = c;
        canvas.repaint();
        try {

                Thread.sleep(delay);

        }
        catch (InterruptedException e){

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
         //Added later using the pause method and the DFS trace from lecture 6
         pause(current);
         ListArrayBased neighbours = getUnvisitedNeighbours(current);

         if (neighbours.isEmpty()){
             stack.pop();
         }
         //Pick at random rather than smallest label like in lecture
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
    //The solver for shortest path from start row/col to end row/col
    //Based labwork for Queues, the video demo for DijkstrasSPA and logic from lecture 6
    private void solveMazeBFS(int start_row, int start_col, int end_row, int end_col){
        int startNode = start_row*grid[0].length+start_col;
        int endNode = end_row*grid[0].length+end_col;

        int [] parentTracker = new int[grid.length*grid[0].length];
       for (int i=0;i<parentTracker.length;i++){
           parentTracker[i] = startNode;
       }
       //Lecture 6 and lab 4 queue
       QueueArrayBased q = new QueueArrayBased();
       grid[start_row][start_col].setExplored(true);
       q.enqueue(grid[start_row][start_col]);

       //While loop from Dijkstra video used to do Lectrue 6 logic
        //Get/set again adapted for
       while (!q.isEmpty()){
           Cell c = (Cell)q.dequeue();
           //Added later, animation
           pause(c);
           //Im marking cells when enqueued like lecture 6

           if (c.getCol() > 0 && !grid[c.getRow()][c.getCol()-1].getExplored() && !c.getLeft())
           {
               Cell cLeft = grid[c.getRow()][c.getCol()-1];
               parentTracker[cLeft.getVertexNum()] = c.getVertexNum();
               cLeft.setExplored(true);
               q.enqueue(cLeft);
           }

           if (c.getRow() > 0 && !grid[c.getRow()-1][c.getCol()].getExplored() && !c.getTop())
           {
               Cell cTop = grid[c.getRow()-1][c.getCol()];
               parentTracker[cTop.getVertexNum()] = c.getVertexNum();
               cTop.setExplored(true);
               q.enqueue(cTop);
           }

           if (c.getCol() < grid[0].length-1 && !grid[c.getRow()][c.getCol()+1].getExplored() && !c.getRight())
           {
               Cell cRight = grid[c.getRow()][c.getCol()+1];
               parentTracker[cRight.getVertexNum()] = c.getVertexNum();
               cRight.setExplored(true);
               q.enqueue(cRight);
           }

           if (c.getRow() < grid.length-1 && !grid[c.getRow()+1][c.getCol()].getExplored() && !c.getBottom())
           {
               Cell cBottom = grid[c.getRow()+1][c.getCol()];
               parentTracker[cBottom.getVertexNum()] = c.getVertexNum();
               cBottom.setExplored(true);
               q.enqueue(cBottom);
           }
       }

displaySP(parentTracker, startNode, endNode, "BFS shortest path");
       //Added later, clear highlight and show the final path
pause(null);
    }
    //Lecture displaySP with added marks for exits and specific cells
    private void displaySP(int [] parentTracker, int startNode, int endNode, String info){
      System.out.println();
      System.out.println(info);

      int index = endNode;
      System.out.print(index);
      grid[index / grid[0].length][index % grid[0].length].setTruePath(true);
      while(index != startNode){
         //Get back to parent cell
          index = parentTracker[index];
          System.out.print("" + index);
          grid[index / grid[0].length][index % grid[0].length].setTruePath(true);
      }
      System.out.println();
    }
    public static void main(String[] args){
        //Changed from new MazeFrame().set... for animation
        MazeFrame frame = new MazeFrame();
        frame.setVisible(true);
        frame.generateMazeRandomiseDFS(0, 0);
        frame.solveMazeBFS(0,0,frame.M-1,frame.N-1);
    }
}
