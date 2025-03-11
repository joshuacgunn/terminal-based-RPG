public class Wand extends Weapon{
    private int wandPower;
    private Element element;
    private int manaCost;

    public Wand(String name, double value, int wandPower, Element element, int durability, double armorPenetration, int manaCost) {
        super(name, value, wandPower, durability, armorPenetration);
        this.element = element;
        this.wandPower = wandPower;
        this.manaCost = manaCost;
    }
}
