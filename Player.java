import java.util.ArrayList;

public class Player {
    private double hp;
    private int xp;
    private String name;
    private ArrayList<Item> inventory = new ArrayList<>();
    private double armorRating;
    private String Class;
    private Dungeon dun;
    private Item currentItem;

    public Player(int startingXp, String name, String playerClass) {
        this.xp = startingXp;
        this.name = name;
        this.hp = 100;
        this.armorRating = 1;
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
    public void takeDamage(Weapon weapon) {
        // Apply armor penetration (percentage-based)
        double effectiveArmor = armorRating * (1.0 - weapon.getArPen());
        
        // Damage formula (same as before)
        double damageMultiplier = 1.0 - (effectiveArmor / (effectiveArmor + 10.0));
        double finalDamage = (weapon.getDam() * damageMultiplier);
        
        // Ensure minimum damage
        finalDamage = Math.max(1, finalDamage);
        if (finalDamage > this.hp) {
            System.out.println("You died!");
        }
        else {
            hp -= finalDamage;
        }
        System.out.println(name + " took " + finalDamage + " damage!");
    }
    public void heal(double hpToAdd) {
        if (hpToAdd > 100) {
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