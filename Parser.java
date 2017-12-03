package main;

import java.util.Scanner;

import map.Place;
import map.TerrainType;
import player.Player;

public class Parser {
    public Scanner s;

    public Parser() {
        s = new Scanner(System.in);
    }

    public void play(Player p) {
        System.out.print("What should I do? (Move/Look/Map");
        if (p.getMap().getMap()[p.getX()][p.getY()].getType() == TerrainType.PLACE) {
            System.out.print("/Enter");
        }
        System.out.println(")");
        String cmd = s.nextLine().toLowerCase();
        System.out.println();
        if (cmd.contains("quit")) {
            System.exit(0);
        }
        if (cmd.contains("move")) {
            if (cmd.contains("north")) {
                System.out.println(p.moveCharacter(p.getX() - 1, p.getY()));
            }
            else if (cmd.contains("west")) {
                System.out.println(p.moveCharacter(p.getX(), p.getY() - 1));
            }
            else if (cmd.contains("south")) {
                System.out.println(p.moveCharacter(p.getX() + 1, p.getY()));
            }
            else if (cmd.contains("east")) {
                System.out.println(p.moveCharacter(p.getX(), p.getY() + 1));
            }
            else {
                System.out.println("Move where? That's nonsense.");
            }
        }
        else if (cmd.contains("look")) {
            System.out.println(p.look());
        }
        else if (cmd.contains("enter")) {
            if (p.getMap().getMap()[p.getX()][p.getY()] instanceof Place) {
                System.out.println(
                        p.switchMaps(((Place) p.getMap().getMap()[p.getX()][p.getY()]).getMap()));
            }
            else {
                System.out.println("Where can I enter here? There's no door anywhere.");
            }
        }
        else if (cmd.contains("map")) {
            System.out.println(p.getMap().toString());
        }
        else {
            System.out.println("What?");
        }
        System.out.println();
    }

}
