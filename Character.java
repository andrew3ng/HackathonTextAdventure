package player;

import item.Item;
import map.Map;

public class Character {
    protected int x, y;
    protected Stats stats;
    protected Map currentMap;
    protected Item[] inventory;
    protected int inventorySpace;
    protected int items;

    public Character() {
        inventorySpace = 8;
        inventory = new Item[inventorySpace];
        items = 0;
    }

    public String moveCharacter(int x, int y) {
        this.x = x;
        this.y = y;
        return null;
    }

    public Map getMap() {
        return currentMap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
