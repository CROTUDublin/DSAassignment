//One square of the maze, foundational
//Growing out of Lecture notes and examples
public class Cell {
    private int row, col, vertexNum;
    private boolean top, bottom, left, right;

    //To say the builder has been here
    private boolean visited;
    //To say the solver has been here
    private boolean explored;
    //Cell marked as part of winning route
    private boolean truePath;

    //Declare a new cell with its four walls where no one has been
    public Cell(int r, int c, int M, int N) {
        row = r;
        col = c;
        //Following formula from lectures for ID
        vertexNum = r * N + c;
        top = bottom = left = right = true;
        visited = explored = truePath = false;

    }
    //Getter and setters

    //Getters only for position since cell doesn't move


    //How far down the cell is
    public int getRow() {
        return row;
    }

    //How far across the cell is
    public int getCol() {
        return col;
    }

    //Solver uses it to get cell ID, to mark cell
    public int getVertexNum() {
        return vertexNum;
    }
    //Get and set for the walls of the cell

    public boolean getTop() {
        return top;
    }

    public void setTop(boolean newTop) {
        top = newTop;
    }

    public boolean getBottom() {
        return bottom;
    }

    public void setBottom(boolean newBottom) {
        bottom = newBottom;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean newRight) {
        right = newRight;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean newLeft) {
        left = newLeft;
    }

    //Flags now to see what state each cell is in out of built, searched, and possibly on the correct path
    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean newVisited) {
        //Marks cell as having been carved
        visited = newVisited;
    }

    public boolean getExplored() {
        //True if solver has been here
        return explored;
    }

    public void setExplored(boolean newExplored) {
        //Solver marks as reached
        explored = newExplored;
    }

    public boolean getTruePath() {
        //True if part of the winning path
        return truePath;
    }

    public void setTruePath(boolean newTruePath) {
        //Marks it as part of the good route
        truePath = newTruePath;
    }
}
