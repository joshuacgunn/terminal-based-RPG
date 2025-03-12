public class Wand extends Weapon {
    private float damage;
    private Element element;
    private int manaCost;

    public Wand(String name, float value, float damage, Element element, int durability, float armorPenetration, int manaCost) {
        super(name, value, damage, durability, armorPenetration);
        this.element = element;
        this.damage = damage;
        this.manaCost = manaCost;
    }
}
