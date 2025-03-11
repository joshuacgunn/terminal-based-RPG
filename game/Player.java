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
    private int xp;
    protected String name;
    protected String Class;
    protected Weapon currentItem;
    protected int level;
    protected XPCalculator xpCalculator;

    public Player(String name, String Class) {
        super(name);
        this.name = name;
        this.hp = 100;
        this.level = 1;
        this.xp = 0;
        this.setArmorRating(1);
        if (Class.equals("1")) {
            this.Class = "Mage";
        } else if (Class.equals("2")) {
            this.Class = "Paladin";
        } else if (Class.equals("3")) {
            this.Class = "Rogue";
        }
        this.xpCalculator = new XPCalculator();
    }
    public void addXP(int xp) {
        this.xp += xp;
        checkLevelUp();
    }
    private void checkLevelUp() {
        int xpNeeded = xpCalculator.calculateXPRequired(level);
        
        while (this.xp >= xpNeeded) {
            this.xp -= xpNeeded; // Carry over excess XP
            level++;
            System.out.println("Level up! Now level " + level);
            xpNeeded = xpCalculator.calculateXPRequired(level);
        }
    }
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