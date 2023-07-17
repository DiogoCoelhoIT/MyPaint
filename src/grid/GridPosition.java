package grid;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GridPosition {

    private int col;
    private int row;
    private Rectangle gridPos;
    private boolean isFilled;

    public GridPosition(int col, int row)
    {


        this.col = col;
        this.row = row;
        gridPos = new Rectangle(Grid.PADDING+(col*Grid.CELLSIZE),Grid.PADDING+(row*Grid.CELLSIZE),Grid.CELLSIZE,Grid.CELLSIZE);
        gridPos.draw();
        isFilled = false;
    }
    public boolean isFilled()
    {
        return isFilled;
    }
    public void unFill()
    {
        getGridPos().setColor(Color.BLACK);
        gridPos.draw();
        isFilled = false;
    }
    public void fill()
    {
        if(!isFilled) {
            gridPos.fill();
            isFilled = true;
        }
    }



    public int getX() {
        return gridPos.getX();
    }

    public int getY() {
        return gridPos.getY();
    }

    public Rectangle getGridPos() {
        return gridPos;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Col: "+ col+" Row: "+row;
    }

}
