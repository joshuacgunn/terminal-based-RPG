import java.util.ArrayList;

public class Inventory{
    protected ArrayList<Item> inventoryList = new ArrayList<>();
    protected Item equippedItem;
    protected Entity owner;
    public Inventory(Entity owner) {
        this.owner = owner;
    }
    public void addtoInventory(Item item) {
        if (item.owner == null) {
            item.owner = this.owner;
        }
        if (inventoryList.size() >= 20 + (owner.strength * 5)) {
            System.out.println("Your inventory is full!");
        }
        else {
            inventoryList.add(item);
        }
    }
    public Item getEquippedItem() {
        return equippedItem;
    }
    public int getInventorySize() {
        return inventoryList.size();
    }
    public float getDamage() {
        return ((Weapon) equippedItem).getDamage();
    }
    public Entity getOwner() {
        return this.owner;
    }
    public float getArmorPenetration() {
        return ((Weapon) equippedItem).getArmorPenetration();
    }
}
