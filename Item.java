package item;

public class Item {
    private String name;
    private Effect effect;
    private boolean equipable;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String effect, boolean equipable) {
        this.name = name;
        this.equipable = equipable;
        this.effect = new Effect(effect);
    }

    @Override
    public String toString() {
        return name;
    }

}
