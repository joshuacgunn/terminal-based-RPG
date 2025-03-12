import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Entity {
    private final String name;
    private float armorRating;
    private float hp;
    protected float attackSpeed;
    protected float attackSpeedMax;
    protected Dungeon dun; // Current dungeon the entity is in, only useful for players but need it in this class
    protected ArrayList<Effect> activeEffects = new ArrayList<>();
    protected ArrayList<Entity> allEntities = new ArrayList<>();
    protected ArrayList<Armor> eqArmors = new ArrayList<>();
    protected ArrayList<Item> inventory = new ArrayList<>();
    public Entity(String name) {
        this.name = name;
    }
    // This is AI written. I could not think of a good way to calculate damage with armor.
    public void takeDamage(float amount) {
        // Apply armor penetration (percentage-based)
        float effectiveArmor = armorRating * (1.0f - amount);
        
        // Damage formula (same as before)
        float damageMultiplier = 1.0f - (effectiveArmor / (effectiveArmor + 10.0f));
        float finalDamage = (amount * damageMultiplier);
        
        // Ensure minimum damage
        finalDamage = Math.max(1, finalDamage);
        if (finalDamage > this.hp && this instanceof Player) {
            System.out.println("You died!");
        }
        else if (this instanceof Player) {
            this.hp -= finalDamage;
        }
        else if (this instanceof Monster) {
            if (amount > this.hp) {
                allEntities.remove(this);
                System.out.println(this.getName() + " has died!");
            }
            else {
                this.hp -= amount;
            }
        }
        System.out.println(name + " took " + finalDamage + " damage!");
    }
    public float getHp(Entity target) {
        return target.hp;
    }
    public void setArmorRating(int amount) {
        this.armorRating = amount;
    }
    public void addStatusEffect(Effect effect) {
        activeEffects.add(effect);
    }
    public void processEndOfTurnEffects() {
        List<Effect> expiredEffects = new ArrayList<>();
        
        for (Effect effect : activeEffects) {
            effect.applyEffect(this); // Apply damage/effect
            effect.decrementDuration(); // Reduce duration
            
            if (effect.isExpired()) {
                expiredEffects.add(effect); // Mark for removal
            }
        }
        // Remove expired effects
        activeEffects.removeAll(expiredEffects);
    }
    public String getName() {
        return this.name;
    }
    public void slowAttackSpeed(int magnitude) {
        this.attackSpeed = this.attackSpeed / magnitude;
    }
    public void enterDungeon(Dungeon d) {
        this.dun = d;
        d.playersInDungeon.add(this);
    }
    public void leaveDungeon() {
        dun.playersInDungeon.clear();
        this.dun = null;
    }
    // Returns all entities in the dungeon
    public ArrayList<Entity> returnAllEntities() { 
        for (DungeonFloor n : dun.getFloorList()) {
            allEntities.addAll(n.getEntities());
        }
        return allEntities;
    }
    private void equipArmorValidation(Armor newArmor) {
        OptionalInt existingIndex = IntStream.range(0, eqArmors.size())
            .filter(i -> eqArmors.get(i).getSlot() == newArmor.getSlot())
            .findFirst();
        if (existingIndex.isPresent()) {
            int index = existingIndex.getAsInt();
            System.out.println("Unequipped" + eqArmors.get(index).getName() + " and put on " + newArmor.getName() + " in slot " + newArmor.getSlot().toString());
            eqArmors.remove(eqArmors.get(index));
            this.inventory.add(eqArmors.get(index));
        }
        else {
            eqArmors.add(newArmor);
        }
    }
    public void equipArmor(Armor armor) {
        this.equipArmorValidation(armor);
    }
    public void unEquipArmor(Armor armor) {
        this.unEquipArmorValidation(armor);
    }
    private void unEquipArmorValidation(Armor oldArmor) {
        boolean doesExist = eqArmors.stream()
            .anyMatch(armor -> armor.getSlot() == oldArmor.getSlot());
        if (doesExist) {
            eqArmors.remove(oldArmor);
        }
        else {
            System.out.println("You don't have that equipped!");
        }
    }
}
