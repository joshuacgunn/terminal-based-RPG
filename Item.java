/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public class Item {
    protected String name;
    protected double value;
    protected int durability;

    public Item(String itemName, double value, int durability) {
        this.name = itemName;
        this.value = value;
        this.durability = durability;
    }

    public String getName() {
        return this.name;
    }
    public int getDurability() {
        return this.durability;
    }
}