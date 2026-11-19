//I based on the examples found in the canvaspanel folder and the video demo
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
//Using JPanel as per the brief

public class MazeCanvas extends JPanel{
    Cell [][] grid;
    public MazeCanvas(Cell [][] grid_arg)
    {
        grid = grid_arg;
    }

    private void drawCell(Graphics g, Cell c, int left, int top, int cell_dim){
       //Slightly different from notes as im using getters and setters
        g.setColor(Color.BLACK);
        top=top+1;//For future reference
        if (c.getTop())
            g.drawLine(left,top,left+cell_dim,top);
        if (c.getLeft())
            g.drawLine(left,top,left,top+cell_dim);
        if (c.getBottom())
            g.drawLine(left,top+cell_dim,left+cell_dim,top+cell_dim);
        if (c.getRight())
            g.drawLine(left+cell_dim,top,left+cell_dim,top+cell_dim);
    }
    @Override
    //JPanel uses protected paintComponent
    protected void paintComponent (Graphics g) {
        int w = getWidth();
        int h = getHeight();
//Size of cell on screen
        int cell_dim;
        if (w < h)
            cell_dim = w / grid.length;
        else cell_dim = h / grid.length;

        //i = row, j = col
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //Fixed bug present in original code with help from lecturer video demo
                drawCell(g, grid[i][j], j * cell_dim, i * cell_dim, cell_dim);
            }

        }
    }
    }

