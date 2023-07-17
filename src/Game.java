import grid.Grid;
import grid.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements KeyListener {
    private Player player;
    private Grid grid;
    private DrawSave drawSave;
    private boolean spacePressed = false;
    private boolean load = false;
    private Color color;
    private ArrayList<Movement>currentMovement;
    private ArrayList<Movement>pastMovement;
    private boolean rndColor = false;
    private boolean erase;


    public Game()
    {
     grid = new Grid();
     player = new Player();
     drawSave = new DrawSave(this);
     currentMovement = new ArrayList<>();
     pastMovement = new ArrayList<>();
     erase = false;
     Canvas.getInstance().addKeyListener(this);
    }
    public void gameInit() {
        grid.showGrid();
        player.draw();
        startGame();
        color = player.drawColor(0);
    }

    public void startGame()
    {
        Thread paintThread = new Thread(this::paintT);
        Thread automaticMove = new Thread(this::autoL);

        paintThread.start();
        automaticMove.start();
    }

    public void paintT()
    {
        while(true) {
                paintGridPos();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                throw new IllegalThreadStateException();
            }
        }
    }
    public void autoL()
    {
        while (true)
        {
            if(load)
            {
                player.automaticLoad(this);
            }

            try{
                Thread.sleep(25);
            }catch (Exception e)
            {
                throw new IllegalThreadStateException();
            }
        }
    }
    public void loadDone()
    {
        load = false;
    }

    public void paintGridPos()
    {

        if (spacePressed)
        {
            if (!erase)
            {
                for (GridPosition rectangle : grid.getGrid()) {
                    if (player.getX() == rectangle.getCol() && player.getY() == rectangle.getRow()) {
                        if (!rectangle.isFilled()) {
                            rectangle.getGridPos().setColor(color);
                            if (rndColor) {
                                rectangle.getGridPos().setColor(player.drawColor((int) (Math.random() * 6)));
                            }
                            rectangle.fill();
                        }
                    }
                }
            }else
            {
                for (GridPosition rectangle : grid.getGrid()) {
                    if (player.getX() == rectangle.getCol() && player.getY() == rectangle.getRow()) {
                        if (rectangle.isFilled()) {
                            rectangle.unFill();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            player.moveLeft();
            currentMovement.add(Movement.MOVE_LEFT);
        }
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            player.moveRight();
            currentMovement.add(Movement.MOVE_RIGHT);
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            player.moveUp();
            currentMovement.add(Movement.MOVE_UP);
        }
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            player.moveDown();
            currentMovement.add(Movement.MOVE_DOWN);
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
              spacePressed();
                currentMovement.add(Movement.PRESS_SPACE);
            }

        if(e.getKeyCode() == KeyEvent.VK_C) {
            grid.unPaintAll();
            currentMovement.clear();
            color = player.drawColor(0);
            while (!player.isReseted())
            {
                player.resetPos();
            }
            player.notReseted();
        }
        if(e.getKeyCode() == KeyEvent.VK_V) {
            try {
                System.out.println("Saved");
                drawSave.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_L) {
            try {
                System.out.println("Loaded");
                drawSave.load("save.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            load = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_P) {
            try {
                System.out.println("Loaded");
                drawSave.load("pila.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            load = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_0)
        {
            color = player.drawColor(0);
            currentMovement.add(Movement.PRESS_0);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_1)
        {
            color = player.drawColor(1);
            currentMovement.add(Movement.PRESS_1);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_2)
        {
            color = player.drawColor(2);
            currentMovement.add(Movement.PRESS_2);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_3)
        {
            color = player.drawColor(3);
            currentMovement.add(Movement.PRESS_3);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_4)
        {
            color = player.drawColor(4);
            currentMovement.add(Movement.PRESS_4);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_5)
        {
            color = player.drawColor(5);
            currentMovement.add(Movement.PRESS_5);
            rndColor = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_6)
        {
            currentMovement.add(Movement.PRESS_6);
            rndColor = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

    }

    public ArrayList<Movement> getPastMovement() {
        return pastMovement;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
            currentMovement.add(Movement.RELEASE_SPACE);
        }
    }

    public ArrayList<Movement> getAllMovement() {
        return currentMovement;
    }

    public Grid getGrid() {
        return grid;
    }

    public void spacePressed() {
        if (!spacePressed) {
            for (GridPosition rectangle : grid.getGrid()) {
                if (player.getX() == rectangle.getCol() && player.getY() == rectangle.getRow()) {
                    if (rectangle.isFilled()) {
                        erase = true;
                    } else {
                        erase = false;
                    }
                }
            }
            spacePressed = true;
        }
    }
    public void spaceNotPressed()
    {
        spacePressed = false;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void rndColor() {
        rndColor = true;
    }
    public void notRndColor() {
        rndColor = false;
    }

    public void setErase(boolean erase) {
        this.erase = erase;
    }
}
