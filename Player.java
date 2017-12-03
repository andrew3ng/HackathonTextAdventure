package player;

import item.Item;
import map.Map;
import map.Place;
import map.Terrain;
import map.TerrainType;

public class Player extends Character {
    private boolean mainWorld;
    private boolean waterTransport, darkTransport;

    public Player(Map map) {
        super();
        currentMap = map;
        x = (map.getMap().length - 1) / 2;
        y = (map.getMap()[x].length - 1) / 2;
        mainWorld = true;
        waterTransport = false;
        darkTransport = false;
        double[] baseStats = new double[9];
        double[] growths = new double[9];
        for (int i = 0; i < baseStats.length; i++) {
            baseStats[i] = Math.max((Math.random() * 10) + 5, (Math.random() * 10) + 5);
            growths[i] = Math.max((Math.random() * 1.5) + .5, (Math.random() * 1.5) + .5);
        }
        stats = new Stats(baseStats, growths);
    }

    public String look() {
        String s = "";
        s += "Here, ";
        s += observe(x, y);
        for (int i = -1; i < 2; i = i + 2) {
            if (i == -1) {
                s += "To the north, " + observe(x + i, y);
                s += "To the west, " + observe(x, y + i);
            }
            else if (i == 1) {
                s += "To the south, " + observe(x + i, y);
                s += "To the east, " + observe(x, y + i);
            }
        }
        return s;
    }

    public String addItem(Item item) {
        if (items < inventorySpace) {
            inventory[items++] = item;
            return "I picked up the " + item;
        }
        else {
            return "I can't pick this up.";
        }
    }

    public String observe(int x, int y) {
        String s = "";
        if (x < 0 || x >= currentMap.getMap().length || y < 0
                || y >= currentMap.getMap()[x].length) {
            return "is a place covered by dense, magical fog. I can't go there.\n";
        }
        Terrain t = currentMap.getMap()[x][y];
        if (currentMap.getMap()[this.x][this.y] != t) {
            if (t.getType() == TerrainType.LAND) {
                s += "there's land I should be able to get to.\n";
            }
            else if (t.getType() == TerrainType.WATER) {
                s += "there's water I can't cross without a boat.\n";
            }
            else if (t.getType() == TerrainType.PLACE) {
                s += "I see some place that catches my eye. I'm intruiged.\n";
            }
            else if (t.getType() == TerrainType.HOLE) {
                s += "It's something I can't cross. I can't go there.\n";
            }
            else if (t.getType() == TerrainType.UNASSIGNED) {
                s += "It's...what is it? What is this terrible darkness?\n";
            }
        }
        else {
            if (t.getType() == TerrainType.PLACE) {
                s += "I can enter. Should I?\n";
            }
            else {
                s += "I'll fill it in with text later.\n";
            }
        }
        return s;
    }

    @Override
    public String moveCharacter(int x, int y) {
        if (x < 0 || x >= currentMap.getMap().length || y < 0
                || y >= currentMap.getMap()[x].length) {
            return "It's a place covered by dense, magical fog. I can't go there";
        }
        String s = "";
        Terrain t = currentMap.getMap()[x][y];
        if (t.getType() == TerrainType.LAND) {
            this.x = x;
            this.y = y;
            s += "You went.\n";
        }
        else if (t.getType() == TerrainType.WATER) {
            if (!waterTransport)
                s += "You need a boat.\n";
            else {
                this.x = x;
                this.y = y;
                s += "You sailed on your boat.\n";
            }
        }
        else if (t.getType() == TerrainType.PLACE) {
            this.x = x;
            this.y = y;
            s += "You went.\n";
        }
        else if (t.getType() == TerrainType.HOLE) {
            s += "It was too dangerous. You decided not to go.\n";
        }
        else if (t.getType() == TerrainType.UNASSIGNED) {
            s += "Do you want to die?\n";
        }
        return s;
    }

    public String switchMaps(Map map) {
        findWarp(map);
        currentMap = map;
        mainWorld = !mainWorld;
        if (mainWorld) {
            return "You exited the place.\n";
        }
        else {
            return "You entered the place.\n";
        }
    }

    // | | | Passive Actions | | | \\

    public void findWarp(Map map) {
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[i].length; j++) {
                if (map.getMap()[i][j] instanceof Place) {
                    if (((Place) map.getMap()[i][j]).getMap() != null
                            && ((Place) map.getMap()[i][j]).getMap() == currentMap) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "Main World: " + mainWorld + " (" + x + ", " + y + ")\n";
        s += stats.toString() + "Inventory: ";
        for (Item i : inventory) {
            if (i != null)
                s += i.toString() + "\n";
        }
        return s;
    }

    public void setWaterTransport() {
        waterTransport = !waterTransport;
    }

    public void setDarkTransport() {
        darkTransport = !darkTransport;
    }

}
