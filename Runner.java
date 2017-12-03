package main;

import java.util.ArrayList;

import map.Map;
import player.Player;
import reader.Reader;

public class Runner {
    // private final static String DIRECTORY = System.getProperty("user.dir");
    private final static String GAME_DATA = "/gameData/";

    public static void main(String[] args) {
        Map map = null;
        ArrayList<String> fileList = Reader.fileList();
        for (String fileName : fileList) {
            if (fileName.contains("Map"))
                map = Reader.mapReader("." + GAME_DATA + fileName);
        }
        // System.out.println(map.toString());
        // if (map.getMap()[4][2] instanceof map.Place)
        // System.out.println(((map.Place)
        // map.getMap()[4][2]).getMap().toString());
        // if (map.getMap()[2][4] instanceof map.Place)
        // System.out.println(((map.Place)
        // map.getMap()[2][4]).getMap().toString());
        Player p = new Player(map);
        // System.out.println(p.toString());
        Parser parser = new Parser();
        System.out.print(p.look());
        while (true) {
            parser.play(p);
        }
    }

}
