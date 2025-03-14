/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public abstract class Item {
    protected String name;
    protected float value;
    protected int durability;
    public Entity owner;
    protected Item equippedItem;
    public Item(String itemName) {
        this.name = itemName;
        this.equippedItem = null;
    }
    public void initializeEquippedItem() {
        if (this.owner != null && this.owner.inventory != null) {
            this.equippedItem = owner.inventory.equippedItem;
        } else {
            this.equippedItem = null;
        }
    }
    public String getName() {
        return this.name;
    }
    public float getValue() {
        return value;
    }
}