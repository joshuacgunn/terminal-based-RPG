/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Weapon extends Item {
    private double damage;
    private int durability;
    protected double armorPenetration;
    public Weapon(String itemName, double value, int damage, int durability, double armorPenetration) {
        super(itemName, value, durability);
        this.damage = damage;
        this.armorPenetration = armorPenetration;
    }
    // Returns the value of armorPenetration for use in other classes.
    public double getArPen() {
        return this.armorPenetration;
    }
    public double getDam() {
        return this.damage;
    }
    public void setArPen(double amt) {
        this.armorPenetration *= amt;
    }
}