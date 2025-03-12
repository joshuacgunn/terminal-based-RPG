/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Weapon extends Item {
    private final float damage;
    private int durability;
    protected float armorPenetration;
    private boolean isUnbreakable = false;
    private boolean isBroken;
    public Weapon(String itemName, float value, float damage, int durability, float armorPenetration) {
        super(itemName, value);
        this.damage = damage;
        this.armorPenetration = armorPenetration;
        this.durability = durability;
        if (this.durability == 0) {
            isUnbreakable = true;
        }
    }
    // Returns the value of armorPenetration for use in other classes.
    public float getArmorPenetration() {
        return this.armorPenetration;
    }
    public float getDamage() {
        return this.damage;
    }
    public void multiplyArmorPenetration(float amt) {
        this.armorPenetration *= amt;
    }
    public int getDurability() {
        return this.durability;
}