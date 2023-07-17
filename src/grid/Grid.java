package grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import java.util.ArrayList;
public class Grid {

    public static final int COL = 80;
    public static final int ROW = 40;
    public static final int CELLSIZE = 20;
    public static final int PADDING =  10;
    private ArrayList<GridPosition>grid;

    public Grid()
    {
        grid = new ArrayList<>();
    }
    public void showGrid() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {

                grid.add(new GridPosition(col, row));
            }
        }
    }
    public void paintGridPosition(int col,int row)
    {
        for (GridPosition gridPosition: grid)
        {
            if(gridPosition.getCol() == col && gridPosition.getRow() == row)
            {
                gridPosition.fill();
            }
        }
    }
    public void unPaintAll()
    {
        for (GridPosition gridPosition: grid) {
            gridPosition.getGridPos().setColor(Color.BLACK);
            gridPosition.unFill();
        }
    }
    public ArrayList<GridPosition> getGrid() {
        return grid;
    }
}
