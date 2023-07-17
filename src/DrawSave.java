
import org.academiadecodigo.simplegraphics.graphics.Color;

import java.io.*;

public class DrawSave {


    private Game game;

    public DrawSave(Game game)
    {
        this.game = game;
    }
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter("save.txt");
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        for (Movement move: game.getAllMovement() )
        {
            bWriter.write(move.getName()+"\n");
        }
        bWriter.close();

    }
    public void load(String filepath) throws IOException {
        FileReader fileReader = new FileReader(filepath);
        BufferedReader bReader = new BufferedReader(fileReader);
        String line = "";
        game.getGrid().unPaintAll();
        game.getPastMovement().clear();
        game.setColor(Color.BLACK);
        game.notRndColor();
        boolean isEmpty = game.getAllMovement().isEmpty();
        game.setErase(false);
        while ((line = bReader.readLine())!=null)
        {
            game.getPastMovement().add(Movement.valueOf(line));
            if(isEmpty)
            {
                game.getAllMovement().add(Movement.valueOf(line));
            }
        }
        bReader.close();
    }
}
