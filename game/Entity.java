import java.util.*;
import java.util.stream.IntStream;

/**
 * Represents a generic entity in the game, serving as a base class for other specific types
 * of entities such as players, NPCs, and monsters. The Entity class encapsulates core
 * properties and behaviors such as health, inventory management, and interaction with the
 * world (e.g., dungeons, towns, and shops).
 *
 * This class provides a blueprint for managing attributes like armor rating, health points,
 * attack speed, and effects applied to the entity. It also contains methods to perform
 * actions such as taking damage, healing, equipping or unequipping armor, processing active
 * effects, and managing interactions with locations.
 */
public class Entity {
    protected String name;
    protected float hp;
    protected float attackSpeed;
    protected Dungeon currentdungeon; // Current dungeon the entity is in
    protected Town currentTown;
    protected Shop currentShop;
    public int agility;
    public int strength;
    protected ArrayList<Effect> activeEffects = new ArrayList<>();
    protected ArrayList<Entity> allEntities = new ArrayList<>();
    protected ArrayList<ArmorPiece> equippedArmorPieces = new ArrayList<>();
    protected float currentDefenseValue;
    protected final Inventory inventory;
    private final UUID uuid = UUID.randomUUID();
    protected EnemyType enemyType;
//    protected final float attackSpeedMax = ((Weapon) this.inventory.equippedItem).attackSpeed;
    public Entity(String name) {
        this.name = name;
        this.inventory = new Inventory(this);
    }
    public UUID getUUID() {
        return this.uuid;
    }
    // The formula for this is made by AI. I could not think of a good way to do this.
    public void takeDamage(float baseDamage, float armorPenetration) {
        // 1. Calculate effective armor after penetration
        float effectiveArmor = (currentDefenseValue*10) * (1.0f - armorPenetration);

        // 2. Calculate damage multiplier (0-100% of base damage)
        float damageMultiplier = 1.0f - (effectiveArmor / (effectiveArmor + 10.0f));

        // 3. Apply base damage with multiplier
        float finalDamage = baseDamage * damageMultiplier;

        // 4. Ensure minimum damage of 1
        finalDamage = Math.max(1, finalDamage);

        // 5. Apply damage and handle death
        if (finalDamage >= hp) {
            hp = 0;
            System.out.println(name + " took " + String.format("%.1f", finalDamage) + " damage and died!");
            entityDeathEvent();
        } else {
            hp -= finalDamage;
            System.out.println(name + " took " + String.format("%.1f", finalDamage) + " damage!");
        }
    }
    public float getHp() {
        return this.hp;
    }
    public void addStatusEffect(Effect effect) {
        activeEffects.add(effect);
    }
    public void processEffects() {
        List<Effect> expiredEffects = new ArrayList<>();
        for (Effect effect : activeEffects) {
            effect.applyEffect(this); // Apply damage/effect
            effect.decrementDuration(1); // Reduce duration
            
            if (effect.isExpired()) {
                expiredEffects.add(effect); // Mark for removal
            }
        }
        // Remove expired effects
        activeEffects.removeAll(expiredEffects);
    }
    public boolean isAlive() {
        return hp != 0;
    }
    public String getName() {
        return this.name;
    }
    public void slowAttackSpeed(float magnitude) {
        if (this instanceof Player && ((Player) this).playerClass == Player.PlayerClass.MAGE) {
            return;
        }
        this.attackSpeed = this.attackSpeed / magnitude;
    }
    public void heal(float amt) {
        float MAX_HEALTH = 100f;
        this.hp = Math.min(this.hp + amt, MAX_HEALTH);
    }
    public Entity getEntity(String name) {
        return allEntities.stream()
            .filter(entity -> entity.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
    public void enterDungeon(Dungeon d) {
        this.currentdungeon = d;
        d.playersInDungeon.add(this);
    }
    public void leaveDungeon() {
        if (this.currentdungeon != null) {
            this.currentdungeon.playersInDungeon.remove(this);
            this.currentdungeon = null;
        }
    }
    public void enterTown(Town town) {
        this.currentTown = town;
    }
    public void leaveTown() {
        this.currentTown = null;
    }
    public void enterShop(Shop shop) {
        if (shop.currentTown != this.currentTown) {
            System.out.println("You aren't in a town");
        }
        else {
            this.currentShop = shop;
        }
    }
    public void leaveShop() {
        this.currentShop = null;
    }
    // Returns all entities in the dungeon
    public ArrayList<Entity> returnAllEntities() { 
        for (DungeonFloor n : currentdungeon.getFloorList()) {
            allEntities.addAll(n.getEntities());
        }
        return allEntities;
    }
    public void generateArmor(int pieces, ArmorPiece.ArmorQuality quality) {
        ArrayList<ArmorPiece> generatedArmor = new ArrayList<>();
        int i = 0;
        outerLoop: while (i < pieces) {
            final Random rand = new Random();
            ArmorPiece.ArmorSlots slot = ArmorPiece.ArmorSlots.values()[rand.nextInt(0, 4)];
            ArmorPiece newArmorPiece = new ArmorPiece(this.name + "'s " + quality + " " + slot.toString(), slot, quality);
            for (ArmorPiece armorPiece : equippedArmorPieces) {
                if (armorPiece.getSlot() == newArmorPiece.getSlot()) {
                    continue outerLoop;
                }
            }
            generatedArmor.add(newArmorPiece);
            System.out.println("Generated armor piece: " + newArmorPiece.getName());
            this.equipArmor(newArmorPiece);
            i++;
        }
    }
    public void generateWeapon(float armorPenetraton, float damage) {
        Weapon newWeapon = new Weapon(this.enemyType.toString() + "'s sword" , damage, 0, armorPenetraton);
        this.equipItem(newWeapon);
        this.inventory.addtoInventory(newWeapon);
    }
    public void equipItem(Item item) {
        this.inventory.equippedItem = item;
    }
    private boolean validateAndEquipArmor(ArmorPiece newArmorPiece) {
        OptionalInt existingIndex = IntStream.range(0, equippedArmorPieces.size())
            .filter(i -> equippedArmorPieces.get(i).getSlot() == newArmorPiece.getSlot())
            .findFirst();
        if (existingIndex.isPresent()) {
            return false; // Make this print something when GUI has been implemented
        }
        else {
            equippedArmorPieces.add(newArmorPiece);
            return true;
        }
    }
    public void equipArmor(ArmorPiece armorPiece) {
        if (this.validateAndEquipArmor(armorPiece)) {
            this.currentDefenseValue += armorPiece.getPieceRating();
        }
    }
    public void unEquipArmor(ArmorPiece armorPiece) {
        if (this.unEquipArmorValidation(armorPiece)) {
            this.currentDefenseValue -= armorPiece.getPieceRating();
        }
    }
    private boolean unEquipArmorValidation(ArmorPiece oldArmorPiece) {
        boolean doesExist = equippedArmorPieces.stream()
            .anyMatch(armorPiece -> armorPiece.getSlot() == oldArmorPiece.getSlot());
        if (doesExist) {
            equippedArmorPieces.remove(oldArmorPiece);
            return true;
        }
        else {
            System.out.println("You don't have that equipped!");
            return false;
        }
    }
    public int getInventorySize() {
        return this.inventory.getInventorySize();
    }
    public ArrayList<Item> getItemsInInventory() {
        return this.inventory.inventoryList;
    }
    public void entityDeathEvent() {
        for (Item i : this.getItemsInInventory()) {
            System.out.println(i.getName() + " was dropped by " + this.name);
        }
        for (ArmorPiece a : this.equippedArmorPieces) {
            System.out.println(a.getName() + " was dropped by " + this.name);
        }
    }
}
