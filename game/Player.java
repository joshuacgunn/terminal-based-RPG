/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Player extends Entity {
    protected float critChance = this.calculateCritChance();
    private float hp;
    private int xp;
    protected String name;
    protected PlayerClass playerClass;
    protected Weapon currentItem;
    protected int level;
    protected XPCalculator xpCalculator;
    private int agility;
    public enum PlayerClass {
        MAGE(MageClass.class),
        PALADIN(PaladinClass.class),
        ROGUE(RogueClass.class);

        private final Class<? extends Player> currentClass;
        PlayerClass(Class<? extends Player> clazz) {
            this.currentClass = clazz;
            }
        public Class<? extends Player> getAssociatedClass() {
            return currentClass;
            }
        }
    public Player(String name) {
        super(name);
        this.name = name;
        this.hp = 100;
        this.level = 1;
        this.xp = 0;
        this.setArmorRating(1);
        switch(playerClass) {
            case MAGE:
                MageClass mage = new MageClass(name, playerClass);
            case ROGUE:
                RogueClass rogue = new RogueClass(name, playerClass);
            case PALADIN:

        }
        this.xpCalculator = new XPCalculator();
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
    public void heal(HealingPotion potion) {
        if (this.hp == 100) {
            System.out.println("Your health is already 100!");
        }
        else if (this.hp + potion.getHealingFactor() > 100){
            this.hp = 100;
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
        System.out.println("Xp: " + this.xp + ", " +"Hp: " + this.hp + ", " + "Name: " +  this.name + ", " + "Class: " + this.playerClass);
    }
    public void showInventory() {
        for (Item n : inventory) {
            System.out.print(n.getName() + ", ");
        }
    }
    public float calculateCritChance() {
        float baseCrit = 0.1f;
        float agilityBonus = (float) Math.sqrt(this.agility) * 0.015f; // Raises crit chance by 1.5% per level of agility
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
                float baseDamageRogue = this.currentItem.getDamage();
                if (Math.random() < critChance) {
                    float critMultiplier = this.calculateCritDamage();
                    return baseDamageRogue * critMultiplier;
                }
                return baseDamageRogue;
            case MAGE:
                if (this.isClass() == PlayerClass.MAGE) {
                    float baseDamageMage = ((Wand) currentItem).getDamage();
                }
                }
        return 0.1f;
        }
    }