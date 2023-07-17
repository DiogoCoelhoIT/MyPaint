import grid.Grid;
import grid.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Player {

    private int x;
    private int y;
    private GridPosition player;
    private boolean reseted;

    public Player()
    {

        x = 0;
        y = 0;
       player = new GridPosition(x,y);
       player.getGridPos().setColor(Color.RED);

    }
    public Color drawColor(int i)
    {
        switch (i)
        {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.RED;
        }
        return Color.BLACK;
    }
    public void draw()
    {
        player.fill();
    }

    public void moveUp()
    {

        if(y>0) {
            y -= 1;
            player.getGridPos().translate(0,-Grid.CELLSIZE);
        }
    }
    public void moveDown()
    {

        if(y<Grid.ROW-1) {
            y += 1;
            player.getGridPos().translate(0,Grid.CELLSIZE);
        }
    }
    public void moveLeft()
    {
        if(x>0) {
            x -= 1;
            player.getGridPos().translate(-Grid.CELLSIZE,0);
        }
    }
    public void moveRight()
    {
        if(x<Grid.COL-1) {
            x += 1;
            player.getGridPos().translate(Grid.CELLSIZE,0);
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY() {
        return y;
    }

    public void automaticLoad(Game game) {
        while (!reseted)
        {
            resetPos();
        }
        if (!game.getPastMovement().isEmpty()) {
            Movement g = game.getPastMovement().get(0);
            switch (g.getName())
            {
                case "MOVE_UP":
                    moveUp();
                break;
                case "MOVE_DOWN":
                    moveDown();
                    break;
                case "MOVE_LEFT":
                    moveLeft();
                    break;
                case "MOVE_RIGHT":
                    moveRight();
                    break;
                case "PRESS_SPACE":
                    game.spacePressed();
                    break;
                case "RELEASE_SPACE":
                    game.spaceNotPressed();
                    break;
                case "PRESS_0":
                    game.setColor(drawColor(0));
                    game.notRndColor();
                    break;
                case "PRESS_1":
                    game.setColor(drawColor(1));
                    game.notRndColor();
                    break;
                case "PRESS_2":
                    game.setColor(drawColor(2));
                    game.notRndColor();
                    break;
                case "PRESS_3":
                    game.setColor(drawColor(3));
                    game.notRndColor();
                    break;
                case "PRESS_4":
                    game.setColor(drawColor(4));
                    game.notRndColor();
                    break;
                case "PRESS_5":
                    game.setColor(drawColor(5));
                    game.notRndColor();
                    break;
                case "PRESS_6":
                    game.rndColor();
                    break;
            }
            game.getPastMovement().remove(g);
        }
        if (game.getPastMovement().isEmpty())
        {
            game.loadDone();
            reseted = false;
        }
    }
    public void resetPos()
    {
            if (x > 0) {
                moveLeft();
            }
            if (y > 0) {
                moveUp();
            }
            if (x == 0 && y == 0) {
                reseted = true;
            }
        }

    public boolean isReseted() {
        return reseted;
    }
    public void notReseted()
    {
        reseted = false;
    }
}
