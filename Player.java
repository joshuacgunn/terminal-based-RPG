import java.util.ArrayList;
/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Player extends Entity {
    private double hp;
    private double xp;
    protected String name;
    private ArrayList<Item> inventory = new ArrayList<>();
    protected String Class;
    protected Dungeon dun;
    private Item currentItem;

    public Player(String name, String playerClass) {
        super(name);
        this.name = name;
        this.hp = 100;
        this.setArmorRating(1);
        if (playerClass.equals("1")) {
            this.Class = "Mage";
        } else if (playerClass.equals("2")) {
            this.Class = "Paladin";
        } else if (playerClass.equals("3")) {
            this.Class = "Rogue";
        }
    }

    public void enterDungeon(Dungeon d) {
        this.dun = d;
        d.pInDun.add(this);
    }
    // This is AI written. I could not think of a good way to calculate damage with armor.
    public void heal(double hpToAdd) {
        if ((hp + hpToAdd) > 100) {
            hp = 100;
        } else {
            hp += hpToAdd;
        }
    }
    public void addToInv(Item item) {
        if (inventory.size() == 20) {
            System.out.println("Your inventory is full!");
        }
        else {
            this.inventory.add(item);
        }
    }
    public void getAllInfo() {
        System.out.println("Xp: " + this.xp + ", " +"Hp: " + this.hp + ", " + "Name: " +  this.name + ", " + "Class: " + this.Class);
    }
    public void showInventory() {
        for (Item n : inventory) {
            System.out.println(n.getName());
        }
    }   
}