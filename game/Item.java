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
    protected float value;
    protected int durability;

    public Item(String itemName, float value) {
        this.name = itemName;
        this.value = value;
    }
    public String getName() {
        return this.name;
    }
    public float getValue() {
        return value;
    }
}