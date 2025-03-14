/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author <a href="https://github.com/joshuacgunn">Author</a>
 * @version Mar 10, 2025
 */
public class Player extends Entity {
    protected float critChance = this.calculateCritChance();
    protected int xp;
    protected String name;
    protected PlayerClass playerClass;
    protected int level;
    protected XPCalculator xpCalculator;
    private final Inventory myInventory = super.inventory;
    public enum PlayerClass {
        MAGE(ClassMage.class),
        PALADIN(ClassPaladin.class),
        ROGUE(ClassRogue.class);
        private final Class<? extends Player> currentClass;

        PlayerClass(Class<? extends Player> clazz) {
            this.currentClass = clazz;
        }
        public Class<? extends Player> getAssociatedClass() {
            return currentClass;
        }
    }
    public Player(String name, PlayerClass playerClass) {
        super(name);
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.playerClass = playerClass;
        this.xpCalculator = new XPCalculator();
        this.hp = 100f;
    }
    public PlayerClass isClass() {
        return this.playerClass;
    }
    public void setPlayerClass(PlayerClass Class) {
        this.playerClass = Class;
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
    public void levelUp() {
        this.checkLevelUp();
    }
    public int getLevel() {
        return this.level;
    }
    public void addToInv(Item item) {
        if (super.getInventorySize() == 50) {
            System.out.println("Your inventory is full!");
        }
        else {
            myInventory.addtoInventory(item);
        }
    }
    public void showInventory() {
        for (Item n : this.getItemsInInventory()) {
            System.out.print(n.getName() + ", ");
        }
    }
    public float calculateCritChance() {
        float baseCrit = 0.1f;
        float agilityBonus = (float) Math.sqrt(super.agility) * 0.015f; // Raises crit chance by 1.5% per level of agility
        return Math.min(baseCrit + agilityBonus, 0.30f);
    }
    public float calculateCritDamage() {
        float baseMultiplier = 1.5f;
        float bonus = this.level * 0.01f; // Raises crit damage by 1% per level
        return baseMultiplier + Math.min(bonus, 0.5f);
    }
    public float calculateDamage() {
        PlayerClass playerClass = this.playerClass;
        switch (playerClass) {
            case ROGUE:
                float baseDamageRogue = myInventory.getDamage();
                if (Math.random() < critChance) {
                    float critMultiplier = this.calculateCritDamage();
                    return baseDamageRogue * critMultiplier;
                }
                return baseDamageRogue;
            case MAGE:
                return myInventory.getDamage();
                }
        return 0.0f;
        }
    }