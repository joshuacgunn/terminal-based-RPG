import java.util.ArrayList;
import java.util.List;

public class Entity {
    private String name;
    private double armorRating;
    private double hp;
    double attackSpeed;
    double attackSpeedMax;
    ArrayList<Effect> activeEffects = new ArrayList<>();
    public Entity(String name) {
        this.name = name;
    }
    public void takeDamage(double amount) {
        // Apply armor penetration (percentage-based)
        double effectiveArmor = armorRating * (1.0 - amount);
        
        // Damage formula (same as before)
        double damageMultiplier = 1.0 - (effectiveArmor / (effectiveArmor + 10.0));
        double finalDamage = (amount * damageMultiplier);
        
        // Ensure minimum damage
        finalDamage = Math.max(1, finalDamage);
        if (finalDamage > this.hp) {
            System.out.println("You died!");
        }
        else {
            this.hp -= finalDamage;
        }
        System.out.println(name + " took " + finalDamage + " damage!");
    }
    public double getHp(Entity target) {
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
}
