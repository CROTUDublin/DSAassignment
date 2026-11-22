//I based on the examples found in the canvaspanel folder and the video demo
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
//Using JPanel as per the brief

public class MazeCanvas extends JPanel{
    Cell [][] grid;
    Cell current; //Based on lecture 6
    public MazeCanvas(Cell [][] grid_arg)
    {
        grid = grid_arg;
    }

    private void drawCell(Graphics g, Cell c, int left, int top, int cell_dim){
       //Slightly different from notes as im using getters and setters
        //New addition to mark cells on winning route
        top=top+1;//For future reference

        //Changed from c.getTruePath for visual states. Using the logic found in the HouseCanvas example
        //Im using colors also from HouseCanvas and some presets
        if (c == current)//To meet actively explored requirement
            g.setColor(Color.RED);
            else if (c.getTruePath())
                g.setColor(new Color(34, 139, 34));
            else if (c.getExplored())
                g.setColor(new Color(135, 206, 235));
            else if (c.getVisited())
                g.setColor(Color.WHITE);
            else g.setColor(Color.DARK_GRAY);
            g.fillRect(left, top, cell_dim, cell_dim);

        g.setColor(Color.BLACK);

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
        //Added for live animation, clears last frame
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
//Size of cell on screen
        int cell_dim;
        //Changed during testing stage for clean scaling
        //For non square mazes and dividing by columns
        if (w / grid[0].length < h / grid.length)
            cell_dim = w / grid[0].length;
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

